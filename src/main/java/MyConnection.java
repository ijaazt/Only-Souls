import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.util.Properties;

public class MyConnection {
    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        DataSource dataSource = new DataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mitello");
        dataSource.setUsername("root");
        dataSource.setPassword("En7j6pur8v");
        return dataSource.getConnection();
    }
}
