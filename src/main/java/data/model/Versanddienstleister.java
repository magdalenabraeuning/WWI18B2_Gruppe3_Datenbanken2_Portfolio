package data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Versanddienstleister")
public class Versanddienstleister implements Serializable {
  /*  Contractor-ID INTEGER,
    Firmenname TEXT,
    Bestellnummer INTEGER, // Muss raus, da neue Entität "Lieferung"
    Lieferzeit DATE, // Muss raus, da neue Entität "Lieferung"
    Lieferbestätigung BOOLEAN, // Muss raus, da neue Entität "Lieferung"
    Versandkosten DOUBLE(10, 2), // Muss raus, enthalten in Warenkorb
    Liefergebiet TINYTEXT,

    PRIMARY KEY(Contractor-ID)
    SEKUNDARY KEY (VZ-ID)??????
    */

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Constractor_ID", nullable = false)
    protected Integer Contractor_ID;

    @Column(name = "Firmenname", nullable = false)
    protected String Firmenname;

    //Noch nicht fertig, aber keine lUst mehr :(

}
