package listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TransactionUtil.getInstance(new CustomPersistenceUnit());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Get your HikariDataSource instance
        HikariDataSource ds = (HikariDataSource) sce.getServletContext().getAttribute("myDataSource");

        // Close the HikariDataSource
        if (ds != null) {
            ds.close();
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
