package at.fhv.teamc.jazzers;

import at.fhv.teamc.jazzers.application.api.CollectionService;
import at.fhv.teamc.jazzers.application.impl.CollectionServiceImpl;
import at.fhv.teamc.jazzers.domain.repository.CollectionRepository;
import at.fhv.teamc.jazzers.infrastructure.HibernateCollectionRepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ServiceRegistry {
    private static EntityManager entityManager;
    private static CollectionService collectionService;
    private static CollectionRepository collectionRepository;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("DownloadMicroservice").createEntityManager();
        }
        return entityManager;
    }

    public static CollectionService collectionService() {
        if (collectionService == null) {
            collectionService = new CollectionServiceImpl();
        }
        return collectionService;
    }

    public static CollectionRepository collectionRepository() {
        if (collectionRepository == null) {
            collectionRepository = new HibernateCollectionRepository();
        }
        return collectionRepository;
    }
}
