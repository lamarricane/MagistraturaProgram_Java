package lab_6.jdbc;

import org.postgresql.ds.PGSimpleDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationDataSource {
    private static final PGSimpleDataSource dataSource;
    private static final Connection connection;

    static {
        dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"localhost:port"});
        dataSource.setUser("user");
        dataSource.setPassword("password");
        dataSource.setDatabaseName("music_store");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

