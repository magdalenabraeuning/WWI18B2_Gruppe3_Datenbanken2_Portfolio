package data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "Mitarbeiter")
public class Mitarbeiter implements Serializable {
    /*CREATE TABLE Mitarbeiter
            (
                    Mitarbeiternummer INTEGER,
                    Vergütung DOUBLE(10, 2),
    Betriebliche Altersvorsorge DOUBLE(10, 2), *//* #Höhe der monatlichen Altervorsorge in Euro*//*
    Anschrift TEXT,                             //raus aus SQL
    Geburtsdatum DATE,
    Name TINYTEXT,
    Stundenzahl INTEGER,
    Anzahl_Ueberstunden DOUBLE,
    VZ-ID INTEGER,

    PRIMARY KEY(Mitarbeiternummer),
    FOREIGN KEY (VZ-ID) REFERENCES Versanddienstleister_Verteilzentrum_wählt(VZ-ID)
        FOREIGN KEY (Anschrift_ID) REFERENCES Anschrift(Anschrift_ID)                   //NEU in SQL
            );*/


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Mitarbeiternummer", nullable = false)
    protected Integer Mitarbeiternummer;

    @Column(name = "Vorname", nullable = false)
    protected String Vorname;

    @Column(name = "Nachname", nullable = false)
    protected String Nachname;

    @Column(name = "Geburtsdatum", nullable = false)
    protected Date Geburtsdatum;

    @Column(name = "Vergütung", nullable = false)               //welcher Datentyp hier? Currency?
    protected Double Verguetung;

    @Column(name = "Betriebliche_Altersvorsorge", nullable = false)        //welcher Datentyp hier? Currency?
    protected Double Betriebliche_Altersvorsorge;

    @Column(name = "Stundenzahl", nullable = false)
    protected Integer Stundenzahl;

    @Column(name = "Anzahl_Ueberstunden", nullable = false)
    protected Integer Anzahl_Ueberstunden;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VZ_ID", nullable = false)
    protected Verteilzentrum verteilzentrum;

    @OneToMany(mappedBy = "Bestellung", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Bestellung> bestellungen;


    public Integer getMitarbeiternummer() { return Mitarbeiternummer; }

    public void setMitarbeiternummer( Integer Mitarbeiternummer ) {
        this.Mitarbeiternummer = Mitarbeiternummer;
    }


    public String getVorname() { return Vorname; }

    public void setVorname( String Vorname ) {
        this.Vorname = Vorname;
    }


    public String getNachname() { return Nachname; }

    public void setNachname( String Nachname ) {
        this.Nachname = Nachname;
    }


    public Date getGeburtsdatum() { return Geburtsdatum; }

    public void setGeburtsdatum( Date Geburtsdatum ) {
        this.Geburtsdatum = Geburtsdatum;
    }


    public Double getVerguetung() { return Verguetung; }

    public void setVerguetung( Double Verguetung ) {
        this.Verguetung = Verguetung;
    }


    public Double getBetriebliche_Altersvorsorge() { return Betriebliche_Altersvorsorge; }

    public void setBetriebliche_Altersvorsorge( Double Betriebliche_Altersvorsorge ) {
        this.Betriebliche_Altersvorsorge = Betriebliche_Altersvorsorge;
    }

    public Integer getStundenzahl() { return Stundenzahl; }

    public void setStundenzahl( Integer Stundenzahl ) {
        this.Stundenzahl = Stundenzahl;
    }


    public Integer getAnzahl_Ueberstunden() { return Stundenzahl; }

    public void setAnzahl_Ueberstunden( Integer Anzahl_Ueberstunden ) {
        this.Anzahl_Ueberstunden = Anzahl_Ueberstunden;
    }


    // da Mitarbeiter 1 Verteilzentrum zugeordnet bekommt
    public Verteilzentrum getVerteilzentrum() {
        return verteilzentrum;
    }

    public void setVerteilzentrum( Verteilzentrum verteilzentrum ) {
        this.verteilzentrum = verteilzentrum;
    }

    // da Mitarbeiter n Bestellungen bearbeitet

    public Mitarbeiter() {
        bestellungen = new HashSet<>();
    }


    public Set<Bestellung> getBestellungen() {
        return Collections.unmodifiableSet( bestellungen );
    }

    public void setBestellungen( Set<Bestellung> bestellungen) {
        this.bestellungen = new HashSet<>( bestellungen );
    }


    public void addBestellung( Bestellung bestellung ) {
        if ( bestellung == null ) {
            throw new NullPointerException( "Can't add null Bestellungen" );
        }

        if ( !this.bestellungen.contains( bestellung ) ) {
            this.bestellungen.add( bestellung );
            //bestellung.setMitarbeiter( this );            //in Bestellungsklasse einfügen
        }
    }


    public void removeBestellung( Bestellung bestellung ) {
        if ( bestellung == null ) {
            throw new NullPointerException( "Can't remove null Bestellungen" );
        }

        if ( this.bestellungen.contains( bestellung ) ) {
            this.bestellungen.remove( bestellung );
            //bestellung.setMitarbeiter( null );            //in Bestellungsklasse einfügen
        }
    }



    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Mitarbeiter mitarbeiter = (Mitarbeiter) o;
        return Objects.equals( Mitarbeiternummer, mitarbeiter.Mitarbeiternummer );
    }

    @Override
    public int hashCode() {
        return Objects.hash( Mitarbeiternummer, Vorname, Nachname, Verguetung, Betriebliche_Altersvorsorge, Stundenzahl, Anzahl_Ueberstunden, verteilzentrum );
    }

}
