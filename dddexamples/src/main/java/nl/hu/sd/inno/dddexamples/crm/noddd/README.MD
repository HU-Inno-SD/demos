# NoDDD

Hier zie je een voorbeeld waarbij we expres geen enkel DDD principe toepassen.

Elke property is een String, alles is Setbaar.

Dit betekent dat de 'ruimte' van mogelijke Contact-objecten gigantisch is. 
En het grootste deel van die ruimte is onzinnig (een contact met "34dsasdf" als TelNr, of met wel een Straat, maar geen Stad?).

Domain-Driven design wil ons helpen, door de ruimte van mogelijke contacts duidelijker te maken. Dit is misschien minder
relevant voor iets als een ContactPersoon, waar we als mens een vrij duidelijk beeld van hebben, maar kan snel belangrijker
worden als we minder expertise in het domein hebben.
