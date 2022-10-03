const { MongoClient } = require('mongodb');
const { readData } = require('../data');


const url = 'mongodb://localhost:27017';
const client = new MongoClient(url);
const dbName = 'shop'

client.connect().then(() => {
    return readData('../raw')
}).then(async results => {
    let db = client.db(dbName);
    let collection = db.collection('orders');
    await collection.drop();
    await collection.insertMany(results.orders);
    let foundOrder = await collection.find({        
        id: 1
    }).toArray();
    console.log("Lekker makkelijk, maar is dit wel handig?", foundOrder)
    //TODO: netter mongo-model uittikken in import2
}).then(() => {
    client.close();
});
