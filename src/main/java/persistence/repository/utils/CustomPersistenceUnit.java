package persistence.repository.utils;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import lombok.Getter;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnit implements PersistenceUnitInfo {

    private final Properties dbProperties;
    @Getter
    private HikariDataSource dataSource;

    public CustomPersistenceUnit() {
        dbProperties = new Properties();
        try (InputStream input = CustomPersistenceUnit.class.getClassLoader().getResourceAsStream("db.properties")) {
            dbProperties.load(input);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
            System.out.println(e.getMessage());
        }

        dataSource = new HikariDataSource();
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
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
        properties.put("hibernate.javax.cache.provider", "com.github.benmanes.caffeine.jcache.spi.CaffeineCachingProvider");
        properties.put("hibernate.javax.cache.uri", "caffeine-config.conf");
        properties.put("hibernate.javax.cache.missing_cache_strategy", "create");
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
