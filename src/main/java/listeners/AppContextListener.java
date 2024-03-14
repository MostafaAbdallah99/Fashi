package listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import persistence.repository.utils.CustomEntityManagerFactory;
import persistence.repository.utils.CustomPersistenceUnit;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TransactionUtil.getInstance(new CustomPersistenceUnit());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomEntityManagerFactory.getInstance(new CustomPersistenceUnit()).close();
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
