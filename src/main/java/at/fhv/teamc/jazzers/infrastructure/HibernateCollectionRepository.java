package at.fhv.teamc.jazzers.infrastructure;

import at.fhv.teamc.jazzers.ServiceRegistry;
import at.fhv.teamc.jazzers.domain.model.Collection;
import at.fhv.teamc.jazzers.domain.repository.CollectionRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class HibernateCollectionRepository implements CollectionRepository {
    private final EntityManager entityManager = ServiceRegistry.entityManager();

    @Override
    public Optional<Collection> getCollection(String username) {
        return entityManager
                .createQuery("SELECT c FROM Collection c WHERE c.ownerName = :username", Collection.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }
}
