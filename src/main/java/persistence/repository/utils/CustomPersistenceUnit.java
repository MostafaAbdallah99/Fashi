package persistence.repository.utils;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnit implements PersistenceUnitInfo {

    private final Properties dbProperties;

    public CustomPersistenceUnit() {
        dbProperties = new Properties();
        try (InputStream input = CustomPersistenceUnit.class.getClassLoader().getResourceAsStream("db.properties")) {
            dbProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getPersistenceUnitName() {
        return "my-persistence-unit";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        try {
            Class.forName(dbProperties.getProperty("database.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbProperties.getProperty("database.url"));
        dataSource.setUsername(dbProperties.getProperty("database.username"));
        dataSource.setPassword(dbProperties.getProperty("database.password"));
        return dataSource;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(
                "persistence.entities.Customer",
                "persistence.entities.Cart",
                "persistence.entities.Order",
                "persistence.entities.Product",
                "persistence.entities.OrderItem",
                "persistence.entities.OrderItemId",
                "persistence.entities.Category",
                "persistence.entities.Tag",
                "persistence.entities.CartItem",
                "persistence.entities.CartItemId"
        );

    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return properties;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
