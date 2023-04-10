load csv with headers from 'file:///persons.csv' as line
create (:Person { id: line.id, name: line.naam  })

load csv with headers from 'file:///products.csv' as line
create (:Product { id: line.id, name: line.naam, price: line.prijs  })

load csv with headers from 'file:///orders.csv' as line
merge (o:Order { id: line.id })

load csv with headers from 'file:///orders.csv' as line
match (p:Person { name: line.person })
match (pr:Product { name: line.product })
match (o:Order { id: line.id })
merge (o)-[:ORDERED_BY]->(p)
merge (o)-[:ORDERED { nr: line.nr }]->(pr)

with 'Groene BBQ' as target
match (p:Person)<-[:ORDERED_BY]-(o:Order)-[:ORDERED]->(pr:Product {name: target}) 
match (p) <-[:ORDERED_BY]-(oo)-[:ORDERED]->(np:Product)
where np.name <> target
return np
