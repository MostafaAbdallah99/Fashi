package listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import persistence.repository.utils.CustomEntityManagerFactory;
import persistence.repository.utils.CustomPersistenceUnit;
import utils.FireStorageManager;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Map<String, Integer> tagMap = Map.of(
                "Shirt", 1,
                "Pants", 2,
                "Bag", 3,
                "Hat", 4,
                "Sweater", 5
        );

        Map<String, Integer> categoryMap = Map.of(
                "Men" , 1,
                "Women", 2,
                "Kids", 3
        );
        
        CustomEntityManagerFactory.getInstance(new CustomPersistenceUnit());
        sce.getServletContext().setAttribute("categoryMap", categoryMap);
        sce.getServletContext().setAttribute("tagMap", tagMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomEntityManagerFactory.getInstance(new CustomPersistenceUnit()).close();
        FireStorageManager.getInstance().close();
        AbandonedConnectionCleanupThread.checkedShutdown();

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
