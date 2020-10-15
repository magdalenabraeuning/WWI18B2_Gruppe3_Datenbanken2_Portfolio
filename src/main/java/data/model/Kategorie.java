package data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Kategorie")
public class Kategorie implements Serializable {
   /* #Tabelle Kategorie wird erstellt
   CREATE TABLE Kategorie
            (
                    Kategorienummer INTEGER,
                    Sortierung INTEGER,
                    Bestseller TINYTEXT,
                    SEO_optimiert BOOLEAN, /* #Ist optimiert oder nicht?
                    Artikelanzahl INTEGER,
                    Bezeichnung TINYTEXT,
                    Sortiments-ID INTEGER,

                    PRIMARY KEY(Kategorienummer),
    FOREIGN KEY (Sortiments-ID) REFERENCES Sortiment(Sortiments-ID)
            )
*/

 // MÜSSTE NICHT KATEGORIE VON SORTIMENT ERBEN ODER DAS GLEICHE SEIN ODER SO? UND IST SORTIMENT NICHT EINFACH EINE BEZEICHNUNG FÜR ALLE ARTIKEL DAS HEIßt DA IST EINE "IS PART OF" BEZIEHUNG?

}
