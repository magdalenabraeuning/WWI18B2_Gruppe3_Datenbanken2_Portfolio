package data.repository;

import data.model.Kategorie;

public class KategorieRepository extends GenericRepository<Kategorie, Long>{
    public KategorieRepository() {
        super( Kategorie.class );
    }
}
