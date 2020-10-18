package data.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Kunde")
public class Kunde extends Besucher implements Serializable {                //Stimmt das???

    /*(
Von Vererbung Besucher:
IP-Adresse VARCHAR,

Name TINYTEXT,                                          //Vorname und Nachname!!!
Passwort TINYTEXT,
Newsletter-Anmeldung BOOLEAN,                           //Siehe weiter unten Problem
Anschrift TINYTEXT,
Geburtsdatum DATE,
E-Mail-Adresse TINYTEXT,                                // _ statt -
Payback-Verknüpfung BOOLEAN,                            // UMGEÄNDERT in Vererbung, wird nicht mehr benötigt
Zu irgendwas anderes:
Als_letztes_hinzugefügt TINYTEXT,
Versandkosten DOUBLE(10, 2),
Steuersatz INTEGER(100), /* #In Prozent
    Artikelanzahl INTEGER,
    Zahlart TINYTEXT,
    Gesamtsumme INTEGER,

Fremdschlüssel:
    Newsletter-ID INTEGER,                          //Fällt weg wegen Kundenemail zwischen Newsletter und Kunde, Kundennummer mapped by Kunden_Email
    Contractor-ID INTEGER,                          //Fällt weg da dazwischen Lieferung, Lieferung bekommt Kundennummer
    Anschrift_ID Integer;
    */


    @Id
    @Column(name = "Kundennummer", nullable = false)          //NEU bezeichnet -> Könnte man mit vererbung auch weglassen....
    protected Integer Kundennummer;

    @Column(name = "Vorname", nullable = false)
    protected String Vorname;

    @Column(name = "Nachname", nullable = false)
    protected String Nachname;

    @Column(name = "Passwort", nullable = false)
    protected String Passwort;

    @Column(name = "Email_Adresse", nullable = false)
    protected String Email_Adresse;

    @Column(name = "Newsletter_Anmeldung", nullable = false)
    protected Boolean Newsletter_Anmeldung;

    @Column(name = "Geburtsdatum", nullable = false)
    protected Date Geburtsdatum;

    @OneToMany(mappedBy = "Kunden_E_Mail", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Kunden_Email> kundenemails;

    @OneToMany(mappedBy = "Lieferung", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Lieferung> lieferungen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Anschrift_ID", nullable = false)
    protected Anschrift anschrift;


    public Kunde() {
        kundenemails = new HashSet<>();
        lieferungen = new HashSet<>();
    }


    public Integer getKundennummer() {
        return Kundennummer;
    }

    public void setKundennummer( Integer Kundennummer ) {
        this.Kundennummer = Kundennummer;
    }


    public String getVorname() {
        return Vorname;
    }

    public void setVorname( String Vorname ) {
        this.Vorname = Vorname;
    }


    public String getNachname() {
        return Nachname;
    }

    public void setNachname( String Nachname ) {
        this.Nachname = Nachname;
    }


    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort( String Passwort ) {
        this.Passwort = Passwort;
    }


    public String getEmail_Adresse() {
        return Email_Adresse;
    }

    public void setEmail_Adresse( String Email_Adresse ) {
        this.Email_Adresse = Email_Adresse;
    }


    public Boolean getNewsletter_Anmeldung() {
        return Newsletter_Anmeldung;
    }

    public void setNewsletter_Anmeldung( Boolean Newsletter_Anmeldung ) {
        this.Newsletter_Anmeldung = Newsletter_Anmeldung;
    }


    public Date getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum( Date Geburtsdatum ) {
        this.Geburtsdatum = Geburtsdatum;
    }


    //Alles zu Kundennummer als Sekundärschlüssel bei Kunden_Email
    public Set<Kunden_Email> getKundenemails() {
        return Collections.unmodifiableSet( kundenemails );
    }

    public void setKundenemails( Set<Kunden_Email> kundenemails ) {
        this.kundenemails = new HashSet<>( kundenemails );
    }


    public void addKundenemail( Kunden_Email kundenemail ) {
        if ( kundenemail == null ) {
            throw new NullPointerException( "Can't add null Kundenemails" );
        }

        if ( !this.kundenemails.contains( kundenemail ) ) {
            this.kundenemails.add( kundenemail );
            //kundenemail.setKunde( this );                             // in Kundenemail Klasse noch definieren
        }
    }


    public void removeKundenemail( Kunden_Email kundenemail ) {
        if ( kundenemail == null ) {
            throw new NullPointerException( "Can't remove null Kundenemails" );
        }

        if ( this.kundenemails.contains( kundenemail ) ) {
            this.kundenemails.remove( kundenemail );
            //kundenemail.setKunde( null );                             // in Kundenemail Klasse noch definieren
        }
    }

    //Alles zu Kundennummer als Sekundärschlüssel bei Lieferung
    public Set<Lieferung> getLieferungen() {
        return Collections.unmodifiableSet( lieferungen );
    }

    public void setLieferungen( Set<Lieferung> lieferungen ) {
        this.lieferungen = new HashSet<>( lieferungen );
    }


    public void addLieferung( Lieferung lieferung ) {
        if ( lieferung == null ) {
            throw new NullPointerException( "Can't add null Lieferungen" );
        }

        if ( !this.lieferungen.contains( lieferung ) ) {
            this.lieferungen.add( lieferung );
            //lieferung.setKunde( this );                             // in Lieferung Funktion noch definieren
        }
    }


    public void removeLieferung( Lieferung lieferung ) {
        if ( lieferung == null ) {
            throw new NullPointerException( "Can't remove null Lieferungen" );
        }

        if ( this.lieferungen.contains( lieferung ) ) {
            this.lieferungen.remove( lieferung );
            //lieferung.setKunde( null );                             // in Lieferung Funktion noch definieren
        }
    }


    //Alles zu Anschrift_ID als Sekundärschlüssel in dieser Klasse Kunde
    public Anschrift getAnschrift() {
        return anschrift;
    }

    public void setAnschrift( Anschrift anschrift ) {
        this.anschrift = anschrift;
    }



    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Kunde kunde = (Kunde) o;
        return Objects.equals( Kundennummer , kunde.Kundennummer );
    }


    @Override
    public int hashCode() {
        return Objects.hash( Kundennummer, Vorname, Nachname, Passwort, Email_Adresse, Newsletter_Anmeldung, Geburtsdatum, anschrift);
    }





}
