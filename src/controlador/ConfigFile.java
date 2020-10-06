package controlador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFile {
	
	private  String fileName = "conf.ini";
	private Properties prop;
	
	public ConfigFile(String file) 
	{
		fileName = file;
		Load();
	}
	
	private void Load() 
	{
		prop = new Properties();
		InputStream is = null;
		try {
		    is = new FileInputStream(fileName);
		} catch (FileNotFoundException ex) {
		    System.err.println("Archivo de configuracion no encontrado: " + fileName);
		}
		try {
		    prop.load(is);
		} catch (IOException ex) {
			System.err.println("Error de lectura en archivo de configuracion: " + fileName);
		}
	}
	
	public String getProperty(String key) 
	{
		return prop.getProperty(key);
	}

}
