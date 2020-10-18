package data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TestKlasse")
public class TestKlasse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TestKlasse_ID", nullable = false)
    protected Integer TestKlasse_ID;

    @Column(name = "TestKlasse_Attribut", nullable = false)
    protected String TestKlasse_Attribut;
}
