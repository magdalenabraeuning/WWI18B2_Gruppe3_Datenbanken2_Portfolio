CREATE DATABASE Einzelhandel;
USE Einzelhandel

/* #Tabelle Session wird erstellt */
[sql]CREATE TABLE Session
(
Session-ID INTEGER,
Uhrzeit TIME,
Dauer TIME,
Datum DATE,
IP-Adresse VARCHAR,

PRIMARY KEY(Session-ID),
FOREIGN KEY (IP-Adresse) REFERENCES Besucher(IP-Adresse)
);

/* #Tabelle Besucher wird erstellt */
CREATE TABLE Besucher
(
IP-Adresse VARCHAR,
Endgerät TINYTEXT,
Browser TINYTEXT,
Betriebssystem TINYTEXT,

PRIMARY KEY(IP-Adresse)
);

/* #Tabelle Produktbewertung wird erstellt */
CREATE TABLE Produktbewertung
(
Bewertungsnummer INTEGER,
Verfasser TINYTEXT,
Datum DATE,
Inhalt TEXT,
Sternzahl INTEGER(5),
IP-Adresse VARCHAR,
GTIN INTEGER,

PRIMARY KEY(Bewertungsnummer),
FOREIGN KEY (IP-Adresse) REFERENCES Besucher(IP-Adresse),
FOREIGN KEY (GTIN) REFERENCES Artikel_Produktempfehlung_empfiehlt (GTIN) /* <-- muss noch geändert werden*/
);


/* #Tabelle Merkliste wird erstellt */
CREATE TABLE Merkliste
(
Merklistennummer INTEGER,
Kategorienanzahl INTEGER,
Anzahl_entfernter_Artikel INTEGER,
Artikelanzahl INTEGER,
Conversion_Rate DOUBLE,
Hinzufügeort TINYTEXT,
Merklistennummer INTEGER,
IP-Adresse VARCHAR,

PRIMARY KEY(Merklistennummer),
FOREIGN KEY (IP-Adresse) REFERENCES Besucher(IP-Adresse)
);


/* #Tabelle Versanddienstleister_Verteilzentrum_wählt wird erstellt*/
CREATE TABLE Versanddienstleister_Verteilzentrum_wählt
(
Contractor-ID INTEGER,
Firmenname TEXT, 
Bestellnummer INTEGER, 
Lieferzeit DATE, 
Lieferbestätigung BOOLEAN, /* #Erhalten oder nicht erhalten? */
Versandkosten DOUBLE(10, 2), /* #Maximal 10 Stellen vor dem Komma und 2 Stellen nach dem Komma - Geldbetrag */
Liefergebiet TINYTEXT, 
VZ-ID INTEGER, 
Bearbeitungsdauer TIMESTAMP, /* #Bis zu welchem Datum + Uhrzeit? */ 
Auslastung INTEGER(100), /* #Auslastung in Prozent bis maximal 100 Prozent */
Standort TINYTEXT, 
Kapazität INTEGER, 
Mitarbeiterzahl INTEGER,

PRIMARY KEY(Contractor-ID)
);


/* #Tabelle Artikel_Produktempfehlung_empfiehlt wird erstellt */
CREATE TABLE Artikel_Produktempfehlung_empfiehlt
(
Recommendation-ID INTEGER,
Klickzahl INTEGER,
Viewzahl INTEGER,
Conversion_Rate DOUBLE,
Anspielungsort TINYTEXT,
GTIN INTEGER,
Rohertrag DOUBLE(10, 2), /* #Maximal 10 Stellen vor dem Komma und 2 Stellen nach dem Komma - Geldbetrag */
Bezeichnung TINYTEXT,
Herkunft TINYTEXT,
Beschreibung TEXT,
Marge DOUBLE(10, 2), /* #Maximal 10 Stellen vor dem Komma und 2 Stellen nach dem Komma - Geldbetrag */
Interne_Artikelnummer INTEGER,
Einkaufspreis DOUBLE(10, 2),
Lieferant TINYTEXT,
Merkmale TEXT,
Verkaufspreis DOUBLE(10, 2),  /* #Maximal 10 Stellen vor dem Komma und 2 Stellen nach dem Komma - Geldbetrag */
Verfügbarkeit BOOLEAN, /* #Verfügbar: ja oder nein. auf Lager, bald verfügbar etc nicht möglich */
VZ-ID INTEGER,
Kategorienummer INTEGER,
Cart-ID INTEGER,
Merklistennummer INTEGER,


PRIMARY KEY(Recommendation-ID),
FOREIGN KEY (VZ-ID) REFERENCES Versanddienstleister_Verteilzentrum_wählt(VZ-ID),
FOREIGN KEY (Kategorienummer) REFERENCES Kategorie(Kategorienummer),
FOREIGN KEY (Cart-ID) REFERENCES Kunde_Warenkorb_Hat(Cart-ID),
FOREIGN KEY (Merklistennummer) REFERENCES Merkliste(Merklistennummer)
);


/* #Tabelle zeigt_an wird erstellt */
CREATE TABLE zeigt_an
(
Recommendation-ID INTEGER,
Seitennummer INTEGER,

PRIMARY KEY(Recommendation-ID, Seitennummer)
);


/* #Tabelle Coupon wird erstellt */
CREATE TABLE Coupon 
(
Couponnummer INTEGER,
Gültigkeitszeitraum DATE, /* #Gültig bis Datum */
Wert INTEGER(100), /* #Prozentcoupon mit maximal 100 Prozent */
Gültigkeitsdauer INTEGER, /* #Dauer in Anzahl der Tage */
Mindestbestellwert DOUBLE(10, 2),
IP-Adresse VARCHAR,
Bestellnummer INTEGER,

PRIMARY KEY(Couponnummer),
FOREIGN KEY (IP-Adresse) REFERENCES Besucher(IP-Adresse),
FOREIGN KEY (Bestellnummer) REFERENCES Bestellung(Bestellnummer)
);


/* #Tabelle Bestellung wird erstellt */
CREATE TABLE Bestellung
(
Bestellnummer INTEGER,
Artikelanzahl INTEGER,
Erstbesteller BOOLEAN, /* #Erstbesteller: True or False */
Versandkosten DOUBLE(10, 2),
Zeitpunkt TIMESTAMP,
Payback-Punkte INTEGER,
Zahlart TINYTEXT,
Rechnungsbetrag DOUBLE(10, 2),
Lieferstatus TINYTEXT,
Mitarbeiternummer INTEGER,
IP-Adresse VARCHAR,
Cart-ID INTEGER,

PRIMARY KEY(Bestellnummer),
FOREIGN KEY (Mitarbeiternummer) REFERENCES Mitarbeiter(Mitarbeiternummer),
FOREIGN KEY (IP-Adresse) REFERENCES Besucher(IP-Adresse),
FOREIGN KEY (Cart-ID) REFERENCES Kunde_Warenkorb_Hat(Cart-ID)
);


/* #Tabelle Mitarbeiter wird erstellt */
CREATE TABLE Mitarbeiter
(
Mitarbeiternummer INTEGER,
Vergütung DOUBLE(10, 2),
Betriebliche Altersvorsorge DOUBLE(10, 2), /* #Höhe der monatlichen Altervorsorge in Euro*/
Anschrift TEXT,
Geburtsdatum DATE,
Name TINYTEXT,
Stundenzahl INTEGER,
Anzahl_Ueberstunden DOUBLE,
VZ-ID INTEGER,

PRIMARY KEY(Mitarbeiternummer),
FOREIGN KEY (VZ-ID) REFERENCES Versanddienstleister_Verteilzentrum_wählt(VZ-ID)
);


/* #Tabelle Sortiment wird erstellt */
CREATE TABLE Sortiment
(
Sortiments-ID INTEGER,
Thema TINYTEXT,
Bezeichnung TINYTEXT,
Zielgruppe TINYTEXT,
Umsatz DOUBLE(10, 2),
Artikelanzahl INTEGER,

PRIMARY KEY(Sortiments-ID)
);


/* #Tabelle Kategorie wird erstellt */
CREATE TABLE Kategorie
(
Kategorienummer INTEGER,
Sortierung INTEGER,
Bestseller TINYTEXT,
SEO_optimiert BOOLEAN, /* #Ist optimiert oder nicht?*/
Artikelanzahl INTEGER,
Bezeichnung TINYTEXT,
Sortiments-ID INTEGER,

PRIMARY KEY(Kategorienummer),
FOREIGN KEY (Sortiments-ID) REFERENCES Sortiment(Sortiments-ID)
);


/* #Tabelle Kunde_Warenkorb_Hat wird erstellt */
CREATE TABLE Kunde_Warenkorb_Hat
(
IP-Adresse VARCHAR,
Name TINYTEXT,
Passwort TINYTEXT,
Newsletter-Anmeldung BOOLEAN,
Anschrift TINYTEXT,
Geburtsdatum DATE,
E-Mail-Adresse TINYTEXT,
Payback-Verknüpfung BOOLEAN,
Cart-ID INTEGER,
Als_letztes_hinzugefügt TINYTEXT,
Versandkosten DOUBLE(10, 2),
Steuersatz INTEGER(100), /* #In Prozent */
Artikelanzahl INTEGER,
Zahlart TINYTEXT,
Gesamtsumme INTEGER,
Newsletter-ID INTEGER,
Contractor-ID INTEGER,

PRIMARY KEY(Payback_Kundennummer),
FOREIGN KEY (Newsletter-ID) REFERENCES Newsletter(Newsletter-ID),
FOREIGN KEY (Contractor-ID) REFERENCES Versanddienstleister_Verteilzentrum_wählt(Contractor-ID)
);


/* #Tabelle Paybackkonto_Kunde_hat wird erstellt */
CREATE TABLE Paybackkonto_Kunde_hat
(
Payback_Kundennummer INTEGER,
Payback_Punktestand DOUBLE,
Name TINYTEXT,
Anschrift TINYTEXT,
IP-Adresse VARCHAR,
Passwort VARCHAR,
Newsletter-Anmeldung BOOLEAN,
Geburtsdatum DATE,
E-Mail-Adresse VARCHAR,
Payback-Verknüpfung BOOLEAN,
Newsletter-ID INTEGER,
Contractor-ID INTEGER

PRIMARY KEY(Payback_Kundennummer),
FOREIGN KEY (Newsletter-ID) REFERENCES Newsletter(Newsletter-ID),
FOREIGN KEY (Contractor-ID) REFERENCES Versanddienstleister_Verteilzentrum_wählt(Contractor-ID)
);


/* #Tabelle Contentseite wird erstellt */
CREATE TABLE Contentseite
(
Seitennummer INTEGER,
Erstellungsdatum DATE,
Titel TINYTEXT,
Autor TINYTEXT,
Beschreibungstext TEXT,

PRIMARY KEY(Seitennummer)
);


/* #Tabelle verwendet wird erstellt */
CREATE TABLE verwendet
(
Seitennummer INTEGER,
Newsletter-ID INTEGER,

PRIMARY KEY(Seitennummer, Newsletter-ID)
);


/* #Tabelle Newsletter wird erstellt */
CREATE TABLE Newsletter
(
Newsletter-ID INTEGER,
Thema TINYTEXT,
Frequenz INTEGER, /* #Frequenz in Anzahl Wochen. Bsp: 2 = Alle 2 Wochen */
Zielgruppe TINYTEXT,

PRIMARY KEY(Newsletter-ID)
);


/* #Tabelle Oberkategorie wird erstellt */
CREATE TABLE Oberkategorie
(
Kategorienummer INTEGER,
Anzahl Unterkategorien INTEGER,

PRIMARY KEY(Kategorienummer)
);


/* #Tabelle Unterkategorie wird erstellt */
CREATE TABLE Unterkategorie
(
Kategorienummer INTEGER,
Filtermöglichkeiten TINYTEXT,
Filteranzahl INTEGER,

PRIMARY KEY(Kategorienummer)
);


/* #Datensätze für Session erstellen */
INSERT INTO Session
(Session-ID, Uhrzeit, Dauer, Datum, IP-Adresse)
VALUES
(1, 13:23:44, 15:23:23, 2020-07-08, 172.16.254.1),
(2, 15:45:21, 15:55:01, 2020-08-18, 172.15.253.7),
(3, 14:56:59, 15:59:55, 2020-11-08, 172.11.234.6)
;


/* #Datensätze für Besucher erstellen */
INSERT INTO Besucher
(IP-Adresse, Endgerät, Browser, Betriebssystem)
VALUES
(172.16.254.1, ‚iPhone 8‘, ‚Safari‘, ‚iOS 14.0‘),
(172.15.253.7, ‚MacBook Pro‘, ‚Safari‘, ‚MacOS‘),
(172.11.234.6, ‚Surface Book‘, ‚Google Chrome′, ’Windows 10‘)
;


/* #Datensätze für Produktbewertung erstellen */
INSERT INTO Produktbewertung
(Bewertungsnummer, Verfasser, Datum, Inhalt, Sternzahl, IP-Adresse, GTIN)
VALUES
(1, ‚Andre Maier‘, 2020-07-08, ‚Sehr gutes Produkt, gerne wieder!‘, 5, 172.16.254.1, 12354),
(2, ‚Katharina Umlauf‘, 2020-11-06, ‚Alles top! Versand etwas langsam.‘, 4, 172.15.253.7, 38734),
(3, Max Mustermann, 2020-01-08, ‚Sehr netter Kundenservice! :)‘, 5, 172.11.234.6, 83874)
;


/* #Datensätze für Merkliste erstellen */
INSERT INTO Merkliste
(Merklistennummer, Kategorienanzahl, Anzahl_entfernter_Artikel, Artikelanzahl, Conversion_Rate, Hinzufügeort, IP-Adresse)
VALUES
(1, 4, 3, 8, 2, ‚Home‘, 172.16.254.1),
(2, 2, 0, 4, 4, ‚Putzartikel‘, 172.15.253.7),
(3, 1, 2, 1, 2, ‚Baby und Zubehör‘, 172.11.234.6)
;


/* #Datensätze für Versanddienstleister_Verteilzentrum_wählt erstellen */
INSERT INTO Versanddienstleister_Verteilzentrum_wählt
(Contractor-ID, Firmenname, Bestellnummer, Lieferzeit, Lieferbestätigung, Versandkosten, Liefergebiet, VZ-ID, Bearbeitungsdauer, Auslastung, Standort, Kapazität, Mitarbeiterzahl)
VALUES
(1, ‚Spedi GmbH‘, 3, 2020-07-09, true, 164.20, ‚Hamburg Stadtkreis‘, 23, 2020-09-23 13:23:44, 23, ‚Hamburg‘, 234, 220),
(2, ‚Standard Spedition Heinz‘, 45, 2020-07-09, true, 300.12, ‚Karlsruhe Landkreis‘, 1, 2020-07-27 12:15:04, 87, ‚Waghäusel‘, 2386, 2301),
(3, ‚Versandunternehmen Volker Versand‘, 4, 2020-07-24, true, 199.23, ‚Weiden Landkreis‘, 21, 2020-09-12 16:14:23, 45, ‚Eslarn‘, 520, 504)
;


/* #Datensätze für Artikel_Produktempfehlung_empfiehlt erstellen */
INSERT INTO Artikel_Produktempfehlung_empfiehlt
(Recommendation-ID, Klickzahl, Viewzahl, Conversion_Rate, Anspielungsort, GTIN, Rohertrag, Bezeichnung, Herkunft, Beschreibung, Marge, Interne_Artikelnummer, Einkaufspreis, Lieferant, Merkmale, Verkaufspreis, Verfügbarkeit, VZ-ID, Kategorienummer, Cart-ID, Merklistennummer)
VALUES
(1, 3444, 6502, 3, ‚Home‘, 12354, 100.00, ‚Pflegemittel Test‘, ‚Deutschland‘, ‚Beispielbeschreibung‘, 32437, 250.12, ‚Spedi GmbH‘, ‚Bestseller‘, 220, true, 34, 4, 22, 220),
(2, 94, 350, 4 ‚Home‘, 38734, 124.50, ‚Duschgel‘, ‚Deutschland‘, ‚Beispielbeschreibung‘, 32498, 140.65, ‚Beispiellieferant‘, ‚Frischeduft‘, 220, false, 0, 1, 54, 545),
(3, 365, 420, 2, ‚Putzartikel‘, 83874, 2.15, ‚Müsli‘, ‚Österreich‘, ‚Beispielbeschreibung‘, 52124, 5.48, ‚Lieferservice Beispiel‘, ‚Mit Nüssen‘, 220, true, 3, 2, 67, 123)
;


/* #Datensätze für zeigt_an erstellen */
INSERT INTO zeigt_an
(Recommendation-ID, Seitennummer)
VALUES
(1, 1),
(2, 2),
(3, 3)
;


/* #Datensätze für Coupon erstellen */
INSERT INTO Coupon
(Couponnummer, Gültigkeitszeitraum, Wert, Gültigkeitsdauer, Mindestbestellwert, IP-Adresse, Bestellnummer)
VALUES
(1, 2020-09-23, 10, 30, 2, 20.00, 172.16.254.1, 23443),
(2, 2020-10-23, 5, 60, 4, 10.00, 172.15.253.7, 12343),
(3, 2020-12-03, 25, 90, 2, 75.00, 172.11.234.6, 43768)
;


/* #Datensätze für Bestellung erstellen */
INSERT INTO Bestellung
(Bestellnummer, Artikelanzahl, Erstbesteller, Versandkosten, Zeitpunkt, Payback-Punkte, Zahlart, Rechnungsbetrag, Lieferstatus, Mitarbeiternummer, IP-Adresse, Cart-ID)
VALUES
(23443, 4, true, 4.50, 2020-07-27 12:15:04, ‚PayPal‘, 144.25, ‚versendet‘, 4451, 172.16.254.1, 22),
(12343, 14, false, 4.50, 2020-07-24 17:25:04, ‚Visa/MasterCard‘, 26.33, ‚versendet‘, 576, 172.15.253.7, 545),
(43768, 9, true, 3.75, 2020-07-12 16:05:04, ‚Sofort-Überweisung‘, 58.17, ‚nicht versendet‘, 445, 172.11.234.6, 123)
;

/* #Datensätze für Mitarbeiter erstellen */
INSERT INTO Mitarbeiter
(Mitarbeiternummer, Vergütung, Betriebliche Altersvorsorge, Anschrift, Geburtsdatum, Name, Stundenzahl, Anzahl Überstunden, VZ-ID)
VALUES
(4451, 10.5, true, 4.50, 50, ‚Straße 12‘, 1998-07-27, ‚Andre Maier‘, 40, 7, 22),
(576, 14.5, false, 4.50, 65, ‚Straßebeispiel 14‘, 1986-06-12, ‚Katharina Umlauf‘, 40, 24, 545),
(445, 12.5, true, 3.75, 57.5, ‚Beispielstraße 123‘, 1966-08-20, ‚Max Mustermann‘, 40, -11, 123)
;


/* #Datensätze für Sortiment erstellen */
INSERT INTO Sortiment
(Sortiments-ID, Thema, Bezeichnung, Zielgruppe, Umsatz, Artikelanzahl)
VALUES
(4451, ‚Frühling‘, true, ‚Frühlings Kollektion‘, ‚Keine Angabe‘, 8465488.45, 12651),
(576, ‚Fasching‘, false, ‚Faschings Kollektion‘, ‚Faschingsbegeisterte‘, 557314.12, 4230),
(445, ‚Sommer‘, true, ‚Sommer Kollektion‘, ‚Keine Angabe‘, 15554657.15, 15498)
;


/* #Datensätze für Kategorie erstellen */
INSERT INTO Kategorie
(Kategorienummer, Sortierung, Bestseller, SEO optimiert, Artikelanzahl, Bezeichnung, Sortiments-ID)
VALUES
(44, 1, true, ‚Duschgel‘, true, 8445, ‚Duschgel‘, 12),
(56, 2, false, ‚Klopapier‘, true, 5573, ‚Duschgel‘, 576),
(45, 3, true, ‚Zahnbürste‘, true, 1555, ‚Duschgel‘, 445)
;

/* #Datensätze für Kunde_Warenkorb_Hat erstellen */
INSERT INTO Kunde_Warenkorb_Hat 
(IP-Adresse, Name, Passwort, Newsletter-Anmeldung, Anschrift, Geburtsdatum, E-Mail-Adresse, Payback-Verknüpfung, Newsletter-ID, Contractor-ID, Cart-ID, Als_letztes_hinzugefügt, Versandkosten, Steuersatz, Artikelanzahl, Zahlart, Gesamtsumme)
VALUES
(172.16.254.3, 'Andrea Maier', 'S2m34L2k', true,'Prinzenstraße 5' , 1978-06-21, 'a.maier@gmx.de',true, 1, 1, 3, 'Klopapier', 3.99, 16, 1, 'Rechnung', 5.99),
(172.15.253.9, 'Kathrin Bürkle', 'KU123nhB', false, 'Pirmienstraße 10', 1997-05-01, 'kathrin.b@yahoo.de',true, 2, 2, 2, 'Make-up' , 0.00, 16, 7, 'Sofort-Überweisung', 108.00),
(172.11.234.2, 'Stefan Silberer', '1M2kdH4', false , 'Luisenweg 3a', 1980-12-12, 'silberer_s80@yahoo.com',true, 3, 3, 1, 'Duschgel', 0.00, 16, 3, 'Visa/Mastercard', 35.99),
;

/* Datensätze für Paybackkonto_Kunde_Hat */
INSERT INTO Paybackkonto_Kunde_Hat
(Payback-Kundennummer, Payback_Punktestand, Name, Anschrift, IP-Adresse, Passwort, Newsletter_Anmeldung, Geburtsdatum, E-Mail-Adresse, Payback_Verknüpfung, Newsletter_ID,  Contractor_ID)
VALUES
(1, 2060, 'Lara Mayer','Mustermannstraße 4', 172.14.255.2, '12K3nmlI', true, 2000-01-01, 'mayer.lara@gmail.de', true, 8, 1),
(2,1000, 'Luisa Müller', 'Offostraße 22', 172.16.258.4, 'LuM34GklIp', false, 1990-08-28, 'mueller.l@yahoo.com', true, 10, 2),
(3, 6010, 'Kevin Blum', 'Unterdorfstraße 122', 172.12.235.7, 'erL23io0op', false, 1999- 03-04,'blum_kevin@gmx.de', true, 9, 3)
;

/* Datensätze für Contentseite */
INSERT INTO Contentseite
(Seitennummer, Erstellungsdatum, Titel, Autor, Beschreibungstext)
VALUES
(3, 2019-07-08,'Beispieltitel 1', 'Melanie Hüber', 'Beispielbeschreibung 1'  ),
(4, 2020-01-12,'Beispieltitel 2', 'Max Müller', 'Beispielbeschreibung 2' ),
(5, 2018-03-04,'Beispieltitel 3', 'Mario Menstell', 'Beispielbeschreibung 3'  )
;

/* Datensätze für verwendet */
INSERT INTO verwendet
(Seitennummer, Newsletter-ID)
VALUES
(3,1),
(4,2),
(5,3)
;

/* Datensätze für Newsletter */
INSERT INTO Newsletter
(Newsletter-ID, Thema, Frequenz, Zielgruppe)
VALUES
(1,'Angebote für Schüler', 3, 'Schüler' ),
(2,'Mutter-Kind-Begleiter',3,'Mütter' ),
(3,'Neue Produkte für Ihn',4,'Männer' )
;

/* Datensätze für Oberkategorie */
INSERT INTO Oberkategorie
(Kategorienummer, Anzahl_Unterkategorien)
VALUES
(1,1),
(2,2),
(3,2)
;

/* Datensätze für Unterkategorie */
INSERT INTO Unterkategorie
(Kategorienummer, Filtermöglichkeiten, Filteranzahl)
VALUES
(1,'Filter 1, FIlter 2',2),
(2,'Filter 1',1),
(3,'Filter 1, Filter 2',2)
;


COMMIT;