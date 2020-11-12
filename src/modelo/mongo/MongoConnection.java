package modelo.mongo;

import java.util.Arrays;
import org.bson.Document;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import controlador.ConfigFile;

public class MongoConnection {
	
	MongoClient mongo;
	MongoDatabase db;
	MongoCollection<Document> vuelosCollection;
	
	private final ConfigFile conf;
	private final String CONF_FILE_NAME = "config/conf_mongo.ini"; 
	
	public MongoConnection() {
		conf = new ConfigFile(CONF_FILE_NAME);
		
		ServerAddress address = new ServerAddress("localhost", 27017);
		MongoCredential credential = MongoCredential.createCredential(
				conf.getProperty("user"), 
				conf.getProperty("db"), 
				conf.getProperty("password").toCharArray()
				);
		
		if (conf.getProperty("auth").equalsIgnoreCase("true")) {
			mongo = MongoClients.create(
		        MongoClientSettings.builder()
		                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(address)))
		                .credential(credential)
		                .build()
		                );
		}
		else {
			mongo = MongoClients.create(
			        MongoClientSettings.builder()
			                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(address)))
			                .build()
			                );
		}
		
		db = mongo.getDatabase(conf.getProperty("db"));
		vuelosCollection = db.getCollection(conf.getProperty("collection"));
	}
	
	public MongoCollection<Document> getColeccionVuelos() {
		return vuelosCollection;
	}
	
	public void dispose() {
		mongo.close();
	}

}
