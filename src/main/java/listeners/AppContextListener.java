package listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TransactionUtil.getInstance(new CustomPersistenceUnit());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TransactionUtil.close();
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
