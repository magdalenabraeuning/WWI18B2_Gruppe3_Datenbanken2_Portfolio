package data.repository;

import data.model.Payback_Kunde;

public class Payback_KundeRepository extends  GenericRepository<Payback_Kunde, Long>{
    public Payback_KundeRepository() {
        super( Payback_Kunde.class );
    }
}
