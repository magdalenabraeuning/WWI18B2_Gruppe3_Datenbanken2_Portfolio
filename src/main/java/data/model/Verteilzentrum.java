package data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Verteilzentrum")
public class Verteilzentrum {

/*
    Contractor-ID INTEGER,
    Firmenname TEXT,
    Bestellnummer INTEGER,
    Lieferzeit DATE,
    Lieferbestätigung BOOLEAN, *//* #Erhalten oder nicht erhalten? *//*
    Versandkosten DOUBLE(10, 2), *//* #Maximal 10 Stellen vor dem Komma und 2 Stellen nach dem Komma - Geldbetrag *//*
    Liefergebiet TINYTEXT,

    VZ-ID INTEGER,
    Bearbeitungsdauer TIMESTAMP, *//* #Bis zu welchem Datum + Uhrzeit? *//*
    Auslastung INTEGER(100), *//* #Auslastung in Prozent bis maximal 100 Prozent *//*
    Standort TINYTEXT,      // Ausgelagert zu Anschrift also weg
    Kapazität INTEGER,      // Wo unterschied zwischen Auslastung und Kapazität???
    Mitarbeiterzahl INTEGER,

    PRIMARY KEY(VZ_ID)  // in SQL-Datei nur als Rauten-Tabelle eingefügt
    FOREIGN KEY (Anschrift_ID) REFERENCES Anschrift(Anschrift_ID) //NEU muss in SQL_DAtei eingefügt werden
);*/



    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VZ_ID", nullable = false)
    protected Integer VZ_ID;

    @Column(name = "Bearbeitungsdauer", nullable = false)
    protected Timestamp Bearbeitungsdauer;

    @Column(name = "Auslastung", nullable = false)
    protected Integer Auslastung;

    @Column(name = "Kapazität", nullable = false)
    protected Integer Kapazitaet;

    @Column(name = "Mitarbeiteranzahl", nullable = false)
    protected Integer Mitarbeiteranzahl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Anschrift_ID", nullable = false)
    protected Anschrift anschrift;

    //Wird noch diskutiert vllt doch 1:1
    @OneToMany(mappedBy = "Versanddienstleister", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Versanddienstleister> versanddienstleister;


    public Verteilzentrum() {
        versanddienstleister = new HashSet<>();
    }


    public Integer getVZ_ID() {
        return VZ_ID;
    }
    public void setVZ_ID( Integer VZ_ID ) {
        this.VZ_ID = VZ_ID;
    }


    public Timestamp getBearbeitungsdauer() {
        return Bearbeitungsdauer;
    }

    public void setBearbeitungsdauer( Timestamp Bearbeitungsdauer ) {
        this.Bearbeitungsdauer = Bearbeitungsdauer;
    }


    public Integer getAuslastung() {
        return Auslastung;
    }

    public void setAuslastung( Integer Auslastung ) {
        this.Auslastung = Auslastung;
    }


    public Integer getKapazitaet() {
        return Kapazitaet;
    }

    public void setKapazitaet( Integer Kapazitaet ) {
        this.Kapazitaet = Kapazitaet;
    }


    public Integer getMitarbeiteranzahl() {
        return Mitarbeiteranzahl;
    }

    public void setMitarbeiteranzahl( Integer Mitarbeiteranzahl ) {
        this.Mitarbeiteranzahl = Mitarbeiteranzahl;
    }


    //da 1-VZ zu m-Versanddienstleister
    public Set<Versanddienstleister> getVersanddienstleister() {
        return Collections.unmodifiableSet( versanddienstleister );
    }

    public void setVersanddienstleister( Set<Versanddienstleister> versanddienstleister ) {
        this.versanddienstleister = new HashSet<>( versanddienstleister );
    }


    public void addVersanddienstleister( Session session ) {
        if ( session == null ) {
            throw new NullPointerException( "Can't add null Versanddienstleister" );
        }

        if ( !this.versanddienstleister.contains( versanddienstleister ) ) {
            //this.versanddienstleister.add( versanddienstleister );
            //versanddienstleister.setVerteilzentrum( this );           muss noch bei Versanddienstleister hinzugefügt werden
        }
    }


    public void removeVersanddienstleister( Versanddienstleister versanddienstleister ) {
        if ( versanddienstleister == null ) {
            throw new NullPointerException( "Can't remove null Versanddienstleister" );
        }

        if ( this.versanddienstleister.contains( versanddienstleister ) ) {
            this.versanddienstleister.remove( versanddienstleister );
            //versanddienstleister.setVerteilzentrum( null );           muss noch bei Versanddienstleister hinzugefügt werden
        }
    }

    // 1-Anschrift zu 1-Verteilzentrum oder 1-Anschrift zu n-Verteilzentren
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

        Verteilzentrum verteilzentrum = (Verteilzentrum) o;
        return Objects.equals( VZ_ID, verteilzentrum.VZ_ID );
    }


    @Override
    public int hashCode() {
        return Objects.hash( VZ_ID, Bearbeitungsdauer, Auslastung, Kapazitaet, Mitarbeiteranzahl, anschrift );
    }


}
