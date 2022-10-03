const redis = require('redis');
const { readData } = require('../data');

let client = redis.createClient({

})

client.connect().then(() => {
    return readData('../raw')
}).then(async results => {
    await Promise.all(results.persons.map(p => client.HSET(`person:${p.id}`, {
        naam: p.naam,
        id: p.id
    })));

    await Promise.all(results.products.map(p => client.HSET(`product:${p.id}`, {
        naam: p.naam,
        id: p.id
    })));

    for(let o of results.orders){
        let orderPlan = client
            .multi()
            .DEL(`order:${o.id}:lines`)
            .HSET(`order:${o.id}`, {
                id: o.id,
                person: `person:${o.person.id}`,
                lines: `order:${o.id}:lines`
            });

        for(let line of o.lines){
            orderPlan = orderPlan
                .LPUSH(`order:${o.id}:lines`, `${line.nr}-product:${line.product.id}`)
        }

        await orderPlan.exec();
    }
}).then(()=>{
    client.disconnect();
});
