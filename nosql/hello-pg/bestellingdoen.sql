--Debug stuff, puur om te resetten
delete from besteldartikel ba where ba.bestnr = 789;
delete from bestelling b where b.bestnr = 789;


--Vanaf hier het 'echte' werk:
begin transaction;
insert into bestelling(bestnr, klantnr, fabnr, datum)
	values(789, 121, 124, current_date);

--postits om op te schrijven
insert into besteldartikel(bestnr, artnr, aantal, bestelprijs)
	values(789, 121, 100, 1.50);

--en een pen
insert into besteldartikel(bestnr, artnr, aantal, bestelprijs)
	values(789, 122, 2, 2.50);

--we hebben echt -alletwee- nodig

commit;
