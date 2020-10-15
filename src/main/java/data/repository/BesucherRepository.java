package data.repository;

import data.model.Besucher;



public class BesucherRepository extends GenericRepository<Besucher, Long> {         //Stimmt hier long?

        public BesucherRepository() {
            super( Besucher.class );
        }

}
