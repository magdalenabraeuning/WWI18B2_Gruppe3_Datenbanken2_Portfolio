package data.repository;

import data.model.Anschrift;

public class AnschriftRepository extends GenericRepository<Anschrift, Long> {
    public AnschriftRepository() {
        super( Anschrift.class );
    }
}
