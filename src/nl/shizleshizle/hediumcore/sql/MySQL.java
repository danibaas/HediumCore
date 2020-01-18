package nl.shizleshizle.hediumcore.sql;

import nl.shizleshizle.hediumcore.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to and uses a MySQL database
 *
 * @author -_Husky_-
 * @author tips48
 */
public class MySQL extends Database {
    private String user;
    private String database;
    private String password;
    private int port;
    private String hostname;

    private Connection conn;
    /*
     * Creates a new MySQL instance
     *
     * @param hostname
     *            Name of the host
     * @param port
     *            Port number
     * @param username
     *            Username
     * @param password
     *            Password
     */
	/*@Deprecated
	public MySQL(String hostname, String port, String username,
			String password) {
		this(hostname, port, null, username, password);
	}
	 * Creates a new MySQL instance for a specific database
	 *
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param database
	 *            Database name
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 *
	@Deprecated
	public MySQL(String hostname, String port, String database,
			String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;
	} */

    /*
     * Creates a new MySQL instance for a specific database
     *
     * @param hostname
     *            Name of the host
     * @param port
     *            Port number
     * @param database
     *            Database name
     * @param username
     *            Username
     * @param password
     *            Password
     */
    MySQL() {
        this.hostname = Main.databaseHost;
        this.port = Main.databasePort;
        this.database = Main.databaseName;
        this.user = Main.databaseUsername;
        this.password = Main.databasePassword;
        this.conn = openConnection();
    }

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database
                    + "?connectTimeout=0&socketTimeout=0&autoReconnect=true", this.user, this.password);
            conn = con;
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection getConnection() {
        return conn;
    }

    public boolean hasConnection() {
        return (conn != null);
    }
}