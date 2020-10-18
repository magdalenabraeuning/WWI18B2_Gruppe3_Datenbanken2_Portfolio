package data.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.net.BindException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*IP-Adresse VARCHAR,
Endgerät TINYTEXT,
Browser TINYTEXT,
Betriebssystem TINYTEXT,

PRIMARY KEY(IP-Adresse)
);*/

@Entity
@Table(name = "Besucher")
public class Besucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IP_Adresse", nullable = false)
    protected String IP_Adresse;                        //VARCHAR ALS STRING???

    @Column(name = "Endgerät", nullable = false)        //Endgerät = eigentlich schwierig Weil streng genommen Entität -> umbennen zu Displaygröße??
    protected String Endgeraet;                          // TinyText als String?? + Endgerät ohne ÄÄÄÄÄÄÄÄÄÄ

    @Column(name = "Browser", nullable = false)
    protected String Browser;

    @OneToMany(mappedBy = "Session", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<Session> sessions;


    public Besucher() {
        sessions = new HashSet<>();
    }


    public String getIP_Adresse() {
        return IP_Adresse;
    }

    public void setIP_Adresse( String IP_Adresse ) {
        this.IP_Adresse = IP_Adresse;
    }


    public String getEndgeraet() {
        return Endgeraet;
    }

    public void setEndgeraet( String Endgeraet ) {
        this.Endgeraet = Endgeraet;
    }


    public String getBrowser() {
        return Browser;
    }

    public void setBrowser( String Browser ) {
        this.Browser = Browser;
    }


    //da 1-Besucher zu m-Session
    public Set<Session> getSessions() {
        return Collections.unmodifiableSet( sessions );
    }

    public void setSessions( Set<Session> sessions ) {
        this.sessions = new HashSet<>( sessions );
    }


    public void addSession( Session session ) {
        if ( session == null ) {
            throw new NullPointerException( "Can't add null Session" );
        }

        if ( !this.sessions.contains( session ) ) {
            this.sessions.add( session );
            session.setBesucher( this );
        }
    }


    public void removeSession( Session session ) {
        if ( session == null ) {
            throw new NullPointerException( "Can't remove null Session" );
        }

        if ( this.sessions.contains( session ) ) {
            this.sessions.remove( session );
            session.setBesucher( null );
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

        Besucher besucher = (Besucher) o;
        return Objects.equals( IP_Adresse, besucher.IP_Adresse );
    }


    @Override
    public int hashCode() {
        return Objects.hash( IP_Adresse, Endgeraet, Browser );
    }

}
