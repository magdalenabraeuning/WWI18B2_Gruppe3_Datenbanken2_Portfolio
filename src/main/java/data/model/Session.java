package data.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;
/*
* /* #Tabelle Session wird erstellt
*[sql]CREATE TABLE Session
 *       (
  *      Session-ID INTEGER,
   *     Uhrzeit TIME,
        Dauer TIME,
        Datum DATE,
        IP-Adresse VARCHAR,
        PRIMARY KEY(Session-ID),
        FOREIGN KEY (IP-Adresse) REFERENCES Besucher(IP-Adresse)
        );
*
* */
    @Entity
    @Table(name = "Session")
    public class Session implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "Session-ID", nullable = false)
        protected Integer Session_ID;

        @Column(name = "Zeitstempel", nullable = false)
        protected Timestamp Zeitstempel;

        @Column(name = "Dauer", nullable = false)
        protected Time Dauer;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "IP_Adresse", nullable = false)
        protected Besucher besucher;


        public Session() {
            // Default constructor
        }


        public Integer getSession_ID() {
            return Session_ID;
        }

        public void setSession_ID( Integer Session_ID ) {
            this.Session_ID = Session_ID;
        }


        public Timestamp getZeitstempel() {
            return Zeitstempel;
        }

        public void setZeitstempel( Timestamp Zeitstempel ) {
            this.Zeitstempel = Zeitstempel;
        }


        public Time getDauer() {
            return Dauer;
        }

        public void setDauer( Time Dauer ) {
            this.Dauer = Dauer;
        }


        public Besucher getBesucher() {
            return besucher;
        }

        public void setBesucher( Besucher besucher ) {
            this.besucher = besucher;
        }

//        WOZUUU??????


        @Override
        public int hashCode() {
            return Objects.hash( Session_ID, Zeitstempel, Dauer, besucher );
        }


    }
