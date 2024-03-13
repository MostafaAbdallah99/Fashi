package persistence.repository.utils;

import jakarta.persistence.EntityManager;

import java.util.function.Consumer;
import java.util.function.Function;

public class TransactionUtil {
    private TransactionUtil() {
    }

    private static final CustomEntityManagerFactory emf =
            CustomEntityManagerFactory.getInstance(new CustomPersistenceUnit());

    public static <R> R doInTransaction(
            Function<EntityManager, R> returningTransactionFunction) {
        var entityManager = emf.getEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            R result = returningTransactionFunction.apply(entityManager);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public static void doInTransactionWithoutResult(
            Consumer<EntityManager> voidTransactionFunction) {
        var entityManager = emf.getEntityManager();
        var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            voidTransactionFunction.accept(entityManager);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}