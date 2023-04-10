DROP TABLE IF EXISTS Voorraad;
DROP TABLE IF EXISTS BesteldArtikel; 
DROP TABLE IF EXISTS Bestelling;
DROP TABLE IF EXISTS Klant;
DROP TABLE IF EXISTS Fabriek; 
DROP TABLE IF EXISTS Artikel;
 
CREATE TABLE Klant(
 klantnr INTEGER NOT NULL, 
 naam    VARCHAR(30) NOT NULL, 
 adres   VARCHAR(30) NOT NULL, 
 plaats  VARCHAR(30) NOT NULL, 
 
 Primary Key(klantnr) 
); 
 
CREATE TABLE Fabriek 
( fabnr  INTEGER NOT NULL, 
  naam   CHAR(30) NOT NULL, 
  adres  CHAR(30) NOT NULL, 
  plaats CHAR(30) NOT NULL, 
 Primary Key(fabnr) 
); 
 
 
Create Table Artikel
( artnr       INTEGER        NOT NULL 
, naam        CHAR(30)       NOT NULL 
, adviesprijs DECIMAL(10, 2) NOT NULL 
, Primary Key(artnr) 
); 
 
CREATE TABLE Voorraad 
( fabnr  INTEGER NOT NULL, 
  artnr  INTEGER NOT NULL, 
  vrd    INTEGER NOT NULL, 
  minvrd INTEGER NOT NULL, 
  PRIMARY KEY(fabnr, artnr), 
  FOREIGN KEY(fabnr) REFERENCES Fabriek(fabnr), 
  FOREIGN KEY(artnr) REFERENCES Artikel(artnr)
); 
 
CREATE TABLE Bestelling
( bestnr  INTEGER NOT NULL, 
  klantnr INTEGER NOT NULL, 
  fabnr   INTEGER NOT NULL, 
  datum   DATE     NOT NULL, 
  PRIMARY KEY(bestnr), 
  FOREIGN KEY(klantnr) REFERENCES Klant(klantnr),
  FOREIGN KEY(fabnr) REFERENCES Fabriek(fabnr) 
); 
 
CREATE TABLE BesteldArtikel 
( bestnr      INTEGER       NOT NULL, 
  artnr       INTEGER       NOT NULL, 
  aantal      INTEGER       NOT NULL, 
  bestelprijs DECIMAL(10, 2) NOT NULL, 
  PRIMARY KEY(bestnr, artnr), 
  FOREIGN KEY(bestnr) REFERENCES Bestelling(bestnr),
  FOREIGN KEY(artnr) REFERENCES Artikel(artnr)
); 
 
 
INSERT INTO Klant VALUES (121, 'Smit'  , 'Hamburgerstraat 55'   , 'Utrecht'
); 
INSERT INTO Klant VALUES (122, 'Staal' , 'VerONesestraat 6'     ,
'Amsterdam' ); 
INSERT INTO Klant VALUES (123, 'Slager', 'Langestraat 3'        ,
'Amersfoort');  
INSERT INTO Klant VALUES (124, 'Snoek' , 'Neude 13'             , 'Utrecht'
); 
INSERT INTO Klant VALUES (125, 'Boer'  , 'Prinses Irenelaan 19' , 'Utrecht'
); 
INSERT INTO Klant VALUES (126, 'Groot' , 'Amsterdamseweg 99'    ,
'Amersfoort'); 
 
 
INSERT INTO Fabriek VALUES (121, 'Kantoor'  , 'Joubertstraat 2', 'Den Haag'  
); 
INSERT INTO Fabriek VALUES (122, 'Popke'    , 'Popkenstraat 7' , 
'GrONingen' ); 
INSERT INTO Fabriek VALUES (123, 'Broese'   , 'Biltstraat 41'  , 'Utrecht'   
); 
INSERT INTO Fabriek VALUES (124, 'Office'   , 'Rijnkade 12'    , 'Utrecht'   
); 
INSERT INTO Fabriek VALUES (451, 'Webster'  , 'Damrak 64'      , 
'Amsterdam' ); 
INSERT INTO Fabriek VALUES (452, 'Vermeulen', 'Lange Poten 42' , 'Den Haag'  
); 
INSERT INTO Fabriek VALUES (453, 'Ahrend'   , 'Utrechtseweg 87', 
'Amersfoort'); 
INSERT INTO Fabriek VALUES (454, 'Hagemeyer', 'Berkenweg 12'   , 
'Amersfoort'); 
INSERT INTO Fabriek VALUES (455, 'Office'   , 'Het Spui 12'    , 
'Amsterdam' ); 
INSERT INTO Fabriek VALUES (456, 'Buhrmann' , 'Taagdreef 56'   , 'Utrecht'   
); 
 
Insert Into Artikel Values (121, 'post-its'        ,  2.75);
Insert Into Artikel Values (122, 'high light pen'  ,  1.50);
Insert Into Artikel Values (123, 'diskettes 10pk'  ,  3.10);
Insert Into Artikel Values (124, 'nietmachine'     ,  4.75);
Insert Into Artikel Values (451, 'cd-rw'           ,  0.50);
Insert Into Artikel Values (452, 'bic pen'         ,  1.00);
Insert Into Artikel Values (453, 'tip-ex'          ,  2.10);
Insert Into Artikel Values (454, 'cd-rw 15pk'      ,  6.00);
Insert Into Artikel Values (456, 'bureaulamp'      , 10.00);
Insert Into Artikel Values (457, 'documenthouder'  , 22.50);
Insert Into Artikel Values (458, 'mONitorstandaard', 10.90);
Insert Into Artikel Values (459, 'antistofhoes'    ,  8.10);
 
INSERT INTO Voorraad VALUES (121, 121, 200, 150); 
INSERT INTO Voorraad VALUES (121, 122, 300,  75); 
INSERT INTO Voorraad VALUES (121, 124, 150, 300); 
INSERT INTO Voorraad VALUES (121, 456, 125, 125); 
INSERT INTO Voorraad VALUES (122, 121, 700, 200); 
INSERT INTO Voorraad VALUES (122, 122, 875, 100); 
INSERT INTO Voorraad VALUES (122, 123,   0,  20); 
INSERT INTO Voorraad VALUES (122, 124, 210, 210); 
INSERT INTO Voorraad VALUES (122, 451, 370, 180); 
INSERT INTO Voorraad VALUES (123, 123,  15,  30); 
INSERT INTO Voorraad VALUES (123, 124, 175, 100); 
INSERT INTO Voorraad VALUES (123, 456,  75, 100); 
INSERT INTO Voorraad VALUES (124, 124, 300, 100); 
INSERT INTO Voorraad VALUES (124, 459, 144,  50); 
INSERT INTO Voorraad VALUES (451, 121, 200, 150); 
INSERT INTO Voorraad VALUES (451, 457, 120, 175); 
INSERT INTO Voorraad VALUES (451, 458,   0, 100); 
INSERT INTO Voorraad VALUES (452, 123,  35,  15); 
INSERT INTO Voorraad VALUES (452, 456,  80,  50); 
INSERT INTO Voorraad VALUES (453, 459,  95,  70); 
INSERT INTO Voorraad VALUES (454, 456, 555,  90); 
INSERT INTO Voorraad VALUES (454, 459,  60, 100); 
INSERT INTO Voorraad VALUES (455, 456, 300, 150); 
INSERT INTO Voorraad VALUES (455, 457, 500, 100); 
INSERT INTO Voorraad VALUES (455, 458, 499, 100); 
INSERT INTO Voorraad VALUES (455, 459,   5,  80); 
 
 
INSERT INTO Bestelling VALUES (121, 121, 124, '2003-04-16');
INSERT INTO Bestelling VALUES (122, 123, 123, '2003-08-25');
INSERT INTO Bestelling VALUES (123, 124, 122, '2003-10-17');
INSERT INTO Bestelling VALUES (124, 122, 121, '2003-05-04');
INSERT INTO Bestelling VALUES (125, 123, 455, '2003-07-19');
INSERT INTO Bestelling VALUES (451, 125, 452, '2003-04-25');
INSERT INTO Bestelling VALUES (452, 126, 451, '2003-08-02');
INSERT INTO Bestelling VALUES (453, 121, 122, '2003-10-17');
INSERT INTO Bestelling VALUES (454, 124, 454, '2003-05-04');
INSERT INTO Bestelling VALUES (455, 126, 455, '2003-07-19');
INSERT INTO Bestelling VALUES (456, 121, 455, '2003-10-17');
 
 
INSERT INTO BesteldArtikel VALUES (121, 124,  200,  4.50); 
INSERT INTO BesteldArtikel VALUES (122, 123,  150,  3.50); 
INSERT INTO BesteldArtikel VALUES (122, 124,   75,  4.75); 
INSERT INTO BesteldArtikel VALUES (122, 456,   50,  9.50); 
INSERT INTO BesteldArtikel VALUES (123, 121,  200,  2.75); 
INSERT INTO BesteldArtikel VALUES (123, 122,  100,  2.00); 
INSERT INTO BesteldArtikel VALUES (123, 123,  100,  3.50); 
INSERT INTO BesteldArtikel VALUES (123, 124,   50,  5.00); 
INSERT INTO BesteldArtikel VALUES (123, 451, 1000,  0.50); 
INSERT INTO BesteldArtikel VALUES (124, 122,  300,  2.00); 
INSERT INTO BesteldArtikel VALUES (124, 124,  100,  5.00); 
INSERT INTO BesteldArtikel VALUES (125, 121,  200,  2.75); 
INSERT INTO BesteldArtikel VALUES (125, 122,  300,  1.75); 
INSERT INTO BesteldArtikel VALUES (125, 451,  400,  0.50); 
INSERT INTO BesteldArtikel VALUES (451, 123,  250,  3.25); 
INSERT INTO BesteldArtikel VALUES (451, 456,   10,  9.75); 
INSERT INTO BesteldArtikel VALUES (452, 121,   50,  2.90); 
INSERT INTO BesteldArtikel VALUES (452, 457,   35, 22.50); 
INSERT INTO BesteldArtikel VALUES (452, 458,  150,  8.50); 
INSERT INTO BesteldArtikel VALUES (453, 121,  400,  2.50); 
INSERT INTO BesteldArtikel VALUES (453, 123,  200,  3.25); 
INSERT INTO BesteldArtikel VALUES (454, 121,   10,  2.50); 
INSERT INTO BesteldArtikel VALUES (454, 456,  100, 11.00); 
INSERT INTO BesteldArtikel VALUES (455, 458,    6, 10.00); 
INSERT INTO BesteldArtikel VALUES (455, 459,  300,  8.10); 
INSERT INTO BesteldArtikel VALUES (456, 452,  100,  0.90);
