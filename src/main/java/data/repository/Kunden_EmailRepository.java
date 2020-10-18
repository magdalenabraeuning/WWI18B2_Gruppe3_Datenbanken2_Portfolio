package data.repository;

import data.model.Kunden_Email;

public class Kunden_EmailRepository extends GenericRepository<Kunden_Email, Long>{
    public Kunden_EmailRepository() {
        super( Kunden_Email.class );
    }
}
