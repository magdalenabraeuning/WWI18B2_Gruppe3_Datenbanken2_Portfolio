package data.repository;

import data.model.Kunde;

public class KundeRepository extends  GenericRepository<Kunde, Long>{
    public KundeRepository() {
        super( Kunde.class );
    }
}
