# Shop

De shop heeft een eenvoudig Orderline gebaseerd domein-model.

```puml
@startuml
class Person {
    naam
}

class Order {
    date: LocalDate
    void addProduct(product, nr)
    void process()
}
Order --> Person
class OrderLine {
    aantal
}
Order "1" o--> "N" OrderLine
OrderLine --> Product

class Product {
    nrAvailable
}

@enduml
```

Het beheren van orders dient via de Order class te gebeuren.

De process methode() past de hoeveelheid beschikbare producten op Product aan.