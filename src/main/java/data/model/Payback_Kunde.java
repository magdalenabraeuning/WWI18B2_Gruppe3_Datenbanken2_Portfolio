package data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Payback_Kunde")
public class Payback_Kunde extends Kunde {                            //EIGENTLICH PAYBACK-KUNDE und Vererbung -> änderung noch abklären

                                                            // NICHT IN RELATIONEN SCHREIBWEISE GEFUNDEN
                                                            // ABGELEITET AUS ER_MODELL
                                                            //in ER umändern oder hier stehen lassen wegen Bindestrich?

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Payback_ID", nullable = false)          //NEU bezeichnet -> Könnte man mit vererbung auch weglassen....
    protected Integer Payback_ID;

    @Column(name = "Payback_Punktestand", nullable = false)
    protected Integer Payback_Punktestand;

                                                                    //Anschrift und Name eigentlich bei Kundenkonto hinterlegt -> andere Attribute finden


}
