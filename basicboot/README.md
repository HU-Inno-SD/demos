# Spring Boot Basics

Basis repository met wat simpele voorbeelden voor de eerste sprint van Inno.

# Inhoud

TODO: Een simpel domein, dat ook even gebruikt kan worden om:

- Spring Annotaties
- JPA Annotaties (Cascades!)
- Loadbalancing

te demo'en.

# Gebruik

* ``mvn package``, of op een andere wijze het package doel draaien. Dit maakt de te-draaien-jar.
* ``docker-compose up``, dit maakt 2 containers van deze applicatie, en draait ze samen met een loadbalancer

Vervolgens kun je:
* Naar http://localhost:8090 om de ge-loadbalancede versie te zien
* Naar http://localhost:8091 om altijd naar de eerste container te gaan
* Naar http://localhost:8092 om altijd naar de tweede container te gaan
* Naar http://localhost:9000/stats om de gory details van de loadbalancer te bekijkens