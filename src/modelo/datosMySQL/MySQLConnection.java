package modelo.datosMySQL;

import java.sql.*;
import controlador.ConfigFile;

public class MySQLConnection {
	
	private final ConfigFile conf;
	private final String CONF_FILE_NAME = "config/conf_mysql.ini"; 
	
	private String DBDIR = "";
	private String DBNAME = "";
	private String DBUSER = "";
	private String DBPASSWORD = "";

	private String URL;

	private Connection conn;

	public MySQLConnection() {
		conf = new ConfigFile(CONF_FILE_NAME);		
		DBDIR = conf.getProperty("host");
		DBNAME = conf.getProperty("dbname");
		DBUSER = conf.getProperty("user");
		DBPASSWORD = conf.getProperty("password");
		setDatos (DBUSER, DBPASSWORD, DBDIR);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC driver no encontrado.");
			System.exit(1);
		}
	}
	
	public void setDatos(String user, String pwd, String dir) {
		DBDIR = dir;
		DBUSER = user;
		DBPASSWORD = pwd;
		URL = String.format("jdbc:mysql://%s/%s?serverTimezone=UTC", DBDIR, DBNAME);
	}
	
	
	/**
	 * Retorna la conexión actualmente activa con la base de datos
	 * o una nueva si esta se ha cerrado.
	 */
	Connection getConnection() throws Exception {
		try {
			if (conn != null && conn.isValid(5)) // 5 seconds of timeout to check conn
				return conn;
			else
				return (conn = DriverManager.getConnection(URL, DBUSER, DBPASSWORD));
		} 
		catch (SQLException e) {
			throw new Exception("Error de conexión a BBDD. Las credenciales son incorrectas o es inaccesible. \n" + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Excepción no controlada durante la conexión a BBDD.\n" + e.getMessage());
		}
	}
}
