package data.repository;

import data.model.Lieferung;

public class LieferungRepository extends  GenericRepository<Lieferung, Long>{
    public LieferungRepository() {
        super( Lieferung.class );
    }
}
