# SOAP-demo

# Intro

SOAP (Simple Object Access Protocol) is een XML-gebaseerd protocol voor RPC (remote procedure call) web services.

Met Spring wordt er zowel aan de service-kant, als aan de client-kant, een document uit de resources-folder gelezen om code te genereren
(`countries.wsl` en `schema.xsd`).

Deze gegenereerde code wordt door IntelliJ niet altijd direct correct opgepikt. 
Er zijn een aantal mogelijke oplossingen hiervoor (en ze allemaal een keer proberen zal je laten zien wat ze gemeen hebben)

1. Start met het compilen van de code via Maven (dus `mvn compile` op een terminal, of met het menuutje rechts in IntelliJ)
2. Je ziet dat het via Maven allemaal netjes compile't, maar in IntelliJ zelf is hij nog een beetje de weg kwijt, je hebt nu 3 redelijke opties:
   1. Sluit IntelliJ nu af, smijt de .idea folder weg, en open het project opnieuw... dan herkent IntelliJ de gegenereerde code wel
   2. Zoek in de target folder van zowel de service, als de client, de folder target/generated-sources/jaxb, en via rechtsklik, `Mark Directory As`->`Generated Sources`
   3. Rechtsklik op zowel het service als client project en run `Maven`->`Generate Sources and Update Folders`
