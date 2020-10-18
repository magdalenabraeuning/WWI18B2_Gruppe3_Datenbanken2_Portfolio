package data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "Lieferung")
public class Lieferung implements Serializable {

    // MUss noch zu SQL Datei hinzugefügt werden

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Lieferung_ID", nullable = false)
    protected Integer Lieferung_ID;

    @Column(name = "Lieferzeit", nullable = false)
    protected Time Lieferzeit;

}
