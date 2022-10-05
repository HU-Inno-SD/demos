const { readData } = require('../data');
const cassandra = require('cassandra-driver');

// ScyllaDB is een concurrent van Cassandra met dezelfde protocollen
// De verschillen zijn voor ons niet relevant, maar in Docker gedraagt Scylla zich iets
// minder hongerig (mijn laptop kon bijv. geen 2 Cassandra containers aan:))
let localCassandra = 'http://localhost:9042'
let client = new cassandra.Client({
   contactPoints: ["127.0.0.1"] ,
   localDataCenter: 'datacenter1'
});

client.connect().then(
    () => readData('../raw')
).then(async ({ products, persons, orders }) => {
    await client.execute("drop keyspace orders").catch(()=>{});
    await client.execute(`
        create KEYSPACE orders 
            WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2 };`);
    await client.execute("use orders;")
    await client.execute(`
        create type line ( nr int, product_name text, product_id int );`)
    await client.execute(`
        create table orders ( id int, person_name text, person_id int, lines 
            frozen<list<line>>, PRIMARY KEY(person_id, id));`)
            //frozen betekent hier immutable. Dat is forward-compatible

    for(let o of orders){
        //TODO injection, even geen tijd gehad om dit voor Cassandra uit te zoeken!
        //(dit is ingewikkelder omdat list<UDT>s frozen moeten zijn tot Cassandra 4)
        
        lines = []
        for(let line of o.lines){
            let lineCQL = `{ 
                nr: ${line.nr}, 
                product_name: '${line.product.naam}',
                product_id: ${line.product.id}}`
            lines.push(lineCQL)
        }
        let allLinesCQL = `[${lines.join(',')}]`
        await client.execute(`insert into orders(person_id , id , person_name, lines) 
            values(${o.person.id},${o.id},'${o.person.naam}', ${allLinesCQL});`)
        
    }

}).then(()=>{
    console.log('docker container exec -it scylla1 cqlsh');
    console.log('docker container exec -it scylla2 cqlsh');
}).then(()=>{
    return client.shutdown();
});