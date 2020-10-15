package data.repository;

import data.model.Session;

public class SessionRepository extends GenericRepository<Session, Long>{
    public SessionRepository() {
        super( Session.class );
    }
}
