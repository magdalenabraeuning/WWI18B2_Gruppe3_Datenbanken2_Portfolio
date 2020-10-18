package data.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Anschrift")
public class Anschrift implements Serializable {

    /* muss noch in SQL Datei eingebunden werden
    * */


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Anschrift_ID", nullable = false)
    protected Integer Anschrift_ID;

    @Column(name = "Hausnummer", nullable = false)
    protected Integer Hausnummer;

    @Column(name = "Strasse", nullable = false)
    protected String Strasse;

    @Column(name = "PLZ", nullable = false)
    protected Integer PLZ;

    @Column(name = "Ort", nullable = false)
    protected String Ort;


    //Zwischen Kunde/ MItarbeiter und Anschrift 1:n oder 1:1 oder n:m?
    @OneToMany(mappedBy = "Kunde", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Kunde> kunden;

    @OneToMany(mappedBy = "Mitarbeiter", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Mitarbeiter> mitarbeiter;

    @OneToOne(mappedBy = "Verteilzentrum", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Verteilzentrum> verteilzentren;

    //Hier verteilzentren noch einfügen? Ist ja eigentlich 1:1 und nicht 1:n
    public Anschrift() {
        kunden = new HashSet<>();
        mitarbeiter = new HashSet<>();
        //verteilzentren = new HashSet<>();
    }


    public Integer getAnschrift_ID() {
        return Anschrift_ID;
    }

    public void setAnschrift_ID( Integer Anschrift_ID ) {
        this.Anschrift_ID = Anschrift_ID;
    }


    public String getStrasse() {
        return Strasse;
    }

    public void setStrasse( String Strasse ) {
        this.Strasse = Strasse;
    }


    public Integer getPLZ() {
        return PLZ;
    }

    public void setPLZ( Integer PLZ ) {
        this.PLZ = PLZ;
    }


    public String getOrt() {
        return Ort;
    }

    public void setOrt( String Ort ) {
        this.Ort = Ort;
    }



    // HIer noch vielleicht zu Kunde und Mitarbeiter zeug; und Verteilzentrum?




    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Anschrift anschrift = (Anschrift) o;
        return Objects.equals( Anschrift_ID, anschrift.Anschrift_ID );
    }


    @Override
    public int hashCode() {
        return Objects.hash( Anschrift_ID, Hausnummer, Strasse, PLZ, Ort );
    }


}
