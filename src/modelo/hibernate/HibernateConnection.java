package modelo.hibernate;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controlador.ConfigFile;

public class HibernateConnection {
	
	private SessionFactory factory;
	private Session session;
	
	private final ConfigFile conf;
	private final String CONF_FILE_NAME = "config/conf_hibernate.ini"; 
	
	public HibernateConnection() 
	{
		conf = new ConfigFile(CONF_FILE_NAME);
		
		// https://www.beyondjava.net/hibernate-cheat-sheet
		
		File hmbConfigFile = new File("./modelo/hibernate/hibernate.cfg.xml");

		factory = new Configuration()
				.configure(hmbConfigFile) // location of hibernate.cfg.xml
				.setProperty("hibernate.connection.url", conf.getProperty("url")) // reads url
				.setProperty("hibernate.connection.username", conf.getProperty("user")) // reads user
				.setProperty("hibernate.connection.password", conf.getProperty("password")) // reads password
				.buildSessionFactory();
		
        session = factory.openSession();
	}
	
	public Session getSession() 
	{
		return session;
	}
	
	public void dispose() 
	{
		session.close();
		factory.close();
	}

}
