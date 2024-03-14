package persistence.repository.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;


public class CustomEntityManagerFactory {

    private static EntityManagerFactory entityManagerFactory;
    private static CustomEntityManagerFactory instance;

    private CustomEntityManagerFactory(CustomPersistenceUnit persistenceUnitInfo) {
        if (entityManagerFactory == null) {
            HibernatePersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
            entityManagerFactory = persistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, new HashMap<>());
        }
    }

    public static CustomEntityManagerFactory getInstance(CustomPersistenceUnit persistenceUnitInfo) {
        if (instance == null) {
            instance = new CustomEntityManagerFactory(persistenceUnitInfo);
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManagerFactory.close();
    }
}