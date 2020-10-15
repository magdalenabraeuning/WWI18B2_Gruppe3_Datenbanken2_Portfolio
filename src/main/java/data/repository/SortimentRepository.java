package data.repository;

import data.model.Sortiment;

public class SortimentRepository extends GenericRepository<Sortiment, Long>{
    public SortimentRepository() {
        super( Sortiment.class );
    }
}
