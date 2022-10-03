const csv = require('csv');
const fs = require('fs');
const util = require('util');

readFileP = util.promisify(fs.readFile);
parseP = util.promisify(csv.parse);
stringifyP = util.promisify(csv.stringify);

module.exports = {
    readData: async function (basePath) {
        let [productsF, personsF, ordersF] = await Promise.all([
            readFileP(basePath + '/products.csv'),
            readFileP(basePath + '/persons.csv'),
            readFileP(basePath + '/orders.csv')
        ]);
        let options = {
            delimiter: ',',
            columns: true
        }

        let [products, persons, orders] = await Promise.all([
            parseP(productsF, options),
            parseP(personsF, options),
            parseP(ordersF, options)
        ])

        products = products.map(p => ({ id: parseInt(p.id), naam: p.naam, prijs: p.prijs }));
        persons = persons.map(p => ({ id: parseInt(p.id), naam: p.naam }));
        orders = orders.map(o => ({id: parseInt(o.id), person: o.person, nr: parseInt(o.nr)}));

        let objectOrders = []
        for (let o of orders) {
            let matchingOrder = objectOrders.find(or => or.id === o.id);
            if (!matchingOrder) {
                let person = null;
                for (let p of persons) {
                    if (p.naam === o.person) {
                        person = p;
                    }
                }
                matchingOrder = {
                    id: o.id,
                    person: person,
                    lines: []
                }
                objectOrders.push(matchingOrder)
            }
            let product = null;

            for (let p of products) {
                if (p.naam === o.product) {
                    product = p;
                }
            }
            matchingOrder.lines.push({
                product: product,
                nr: o.nr
            })
        }

        return { products, persons, orders: objectOrders };
    }
}