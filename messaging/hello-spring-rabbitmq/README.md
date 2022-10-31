# RabbitMQ & Spring Demo

Deze demo laat even de minimale setup zien die je nodig hebt om -iets- met messages te doen.

Opstartchecklist:
 - [ ] Maven Dependencies binnengehaald? (IntelliJ is niet onderin nog aan het laden?)
 - [ ] Docker-compose omgeving ge-up'd? (zie ../development map)
 - [ ] Run die `HelloSpringRabbitMain` class


De applicatie werkt als volgt:
 - De `Producer` gebruikt een `RabbitTemplate` class om een bericht naar RabbitMQ te sturen.
 - De `Runner` gebruikt deze producer om bij het opstarten elke 5 seconden daadwerkelijk een bericht te sturen.
 - De `Listener` gebruikt de `RabbitListener` annotatie om alle berichten die op die ene queue komen te verwerken.
 - Dit werkt omdat de RabbitConfig de `Queue` (in de *spring* namespace, en *NIET* de rabbitmq namespace) als `Bean` aanmeld.
 - De `MessageConverter` is nodig omdat je anders een error krijgt bij 'convertAndSend' (namelijk dat er geen converters beschikbaar zijn!)

## En nu?

Dit is 1 Producer die 1 Bericht op 1 Queue afvuurt waar 1 Listener naar luistert. Dat is een mooi begin, maar waarschijnlijk wil
je al vrij snel 1 Producer die 1 Bericht op meerdere Queues afvuurt, zodat meerdere listeners tegelijk ge-update kunnen worden.

Hiervoor zul je op een soortgelijke manier je eigen 'Topic Exchange' moeten configureren met de juiste bindings.  
