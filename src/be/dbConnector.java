package be;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class dbConnector {

    private final static String FILE_PATH = "src/Resources/dbinfo.settings";
    private SQLServerDataSource dataSource;

    public dbConnector() throws IOException {
        Properties dbProperties = new Properties();
        dbProperties.load(new FileInputStream(FILE_PATH));

        dataSource = new SQLServerDataSource();
        dataSource.setServerName(dbProperties.getProperty("Server"));
        dataSource.setDatabaseName(dbProperties.getProperty("Database"));
        dataSource.setUser(dbProperties.getProperty("User"));
        dataSource.setPassword(dbProperties.getProperty("Password"));
        dataSource.setPortNumber(Integer.parseInt(dbProperties.getProperty("Port")));
    }

    /**
     * Establish a connection to the database.
     * @return the connection to the data source.
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
