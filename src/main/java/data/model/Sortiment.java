package data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Sortiment")
public class Sortiment implements Serializable {
   /* #Tabelle Sortiment wird erstellt
   CREATE TABLE Sortiment
            (
                    Sortiments-ID INTEGER,
                    Thema TINYTEXT,
                    Bezeichnung TINYTEXT,
                    Zielgruppe TINYTEXT,
                    Umsatz DOUBLE(10, 2),
    Artikelanzahl INTEGER,

    PRIMARY KEY(Sortiments-ID)
);*/

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Sortiments_ID", nullable = false)
    protected Integer Sortiments_ID;

    @Column(name = "Thema", nullable = false)                   // hier auch wieder thema tinytext
    protected String Thema;                                     //NOTWENDIG?

    @Column(name = "Bezeichnung", nullable = false)
    protected String Bezeichnung;

    @Column(name = "Zielgruppe", nullable = false)              // NOTWENDIG?
    protected String Zielgruppe;

    @Column(name = "Umsatz", nullable = false)                  //EINFÜGEN: nur 2 Nachkommastellen-Regel und max 10 Stellen vorne
    protected Double Umsatz;

    @Column(name = "Artikelanzahl", nullable = false)
    protected Integer Artikelanzahl;

    @OneToMany(mappedBy = "Kategorie", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Kategorie> kategorien;

    public Sortiment() {
        kategorien = new HashSet<>();
    }


    public Integer getSortiments_ID() {
        return Sortiments_ID;
    }

    public void setSortiments_ID( Integer Sortiments_ID ) {
        this.Sortiments_ID = Sortiments_ID;
    }


    public String getThema() {
        return Thema;
    }

    public void setThema( String Thema ) {
        this.Thema = Thema;
    }


    public String getBezeichnung() {
        return Bezeichnung;
    }

    public void setBezeichnung( String Bezeichnung ) {
        this.Bezeichnung = Bezeichnung;
    }


    public String getZielgruppe() {
        return Bezeichnung;
    }

    public void setZielgruppe( String Zielgruppe ) {
        this.Zielgruppe = Zielgruppe;
    }


    public Double getUmsatz() {
        return Umsatz;
    }

    public void setUmsatz( Double Umsatz ) {
        this.Umsatz = Umsatz;
    }


    public Integer getArtikelanzahl() {
        return Artikelanzahl;
    }

    public void setArtikelanzahl( Integer Artikelanzahl ) {
        this.Artikelanzahl = Artikelanzahl;
    }


    public Set<Kategorie> getKategories() {
        return Collections.unmodifiableSet( kategorien );
    }

    public void setKategorien( Set<Kategorie> kategorien ) {
        this.kategorien = new HashSet<>( kategorien );
    }


    public void addKategorie( Kategorie kategorie ) {
        if ( kategorie == null ) {
            throw new NullPointerException( "Can't add null Kategorie" );
        }

        if ( !this.kategorien.contains( kategorie ) ) {
            this.kategorien.add( kategorie );
            kategorie.setSortiment( this );                                     //noch hinzufügen bei Kategorie klasse
        }
    }


    public void removeKategorie( Kategorie kategorie ) {
        if ( kategorie == null ) {
            throw new NullPointerException( "Can't remove null Kategorie" );
        }

        if ( this.kategorien.contains( kategorie ) ) {
            this.kategorien.remove( kategorie );
            kategorie.setSortiment( null );                                     //noch hinzufügen bei Kategorie klasse
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

        Sortiment sortiment = (Sortiment) o;
        return Objects.equals( Sortiments_ID, sortiment.Sortiments_ID );
    }


    @Override
    public int hashCode() {
        return Objects.hash( Sortiments_ID, Thema, Bezeichnung, Zielgruppe, Umsatz, Artikelanzahl );
    }





}
