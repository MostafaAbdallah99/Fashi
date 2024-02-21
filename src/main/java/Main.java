import entities.Address;
import entities.User;
import jakarta.persistence.EntityManager;
import utils.CustomEntityManagerFactory;
import utils.CustomPersistenceUnit;

public class Main {

    public static void main(String[] args) {
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setGovernorate("New York");
        address.setCountry("USA");

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setPhone("1234567890");
        user.setAddress(address);

        CustomEntityManagerFactory factory = CustomEntityManagerFactory.getInstance(new CustomPersistenceUnit());
        EntityManager entityManager = factory.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}