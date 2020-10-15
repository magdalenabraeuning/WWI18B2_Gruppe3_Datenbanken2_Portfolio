package data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Kunde")
public class Kunde extends Besucher{                //Stimmt das???

    /*(
Von Vererbung Besucher:
IP-Adresse VARCHAR,

Name TINYTEXT,                                          //Vorname und Nachname!!!
Passwort TINYTEXT,
Newsletter-Anmeldung BOOLEAN,                           //Siehe weiter unten Problem
Anschrift TINYTEXT,
Geburtsdatum DATE,
E-Mail-Adresse TINYTEXT,
Payback-Verknüpfung BOOLEAN,                            // UMGEÄNDERT in Vererbung, wird nicht mehr benötigt
Zu irgendwas anderes:
Als_letztes_hinzugefügt TINYTEXT,
Versandkosten DOUBLE(10, 2),
Steuersatz INTEGER(100), /* #In Prozent
    Artikelanzahl INTEGER,
    Zahlart TINYTEXT,
    Gesamtsumme INTEGER,

Fremdschlüssel:
    Newsletter-ID INTEGER,                          //Nicht sicher ob n:m beziehung zwischen Newsletter und Kunde; bei Kunde würde so mehrere N-IDs stehen und wenn nicht gäbe es nur ein Newsletter womit die Tabelle unnötig ist
    Contractor-ID INTEGER,
    */







}
