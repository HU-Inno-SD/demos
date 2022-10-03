const { readData } = require('../data');
const axios = require('axios')

let localRiak = 'http://localhost:8098'

readData('../raw').then(async ({ products, persons, orders }) => {
    await Promise.all(products.map(pr => axios.put(`${localRiak}/riak/products/${pr.id}`, pr)));
    await Promise.all(persons.map(p => axios.put(`${localRiak}/riak/persons/${p.id}`, p)));
    let mappedOrders = orders.map(o => ({
        id: o.id,
        lines: o.lines.map(l => ({
            nr: l.nr,
            product: `${localRiak}/riak/products/${l.product.id}`,
        })),
        person: `${localRiak}/riak/persons/${o.person.id}`
    }));
    await Promise.all(mappedOrders.map(o => axios.put(`${localRiak}/riak/orders/${o.id}`, o)));
}).then(()=>{
    console.log("en nu kun je naar http://localhost:8098/riak/persons/1 bijv. (evenzo products, orders)")
})