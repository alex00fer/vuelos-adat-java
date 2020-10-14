package modelo.datosFichero;

import controlador.CRUD;
import controlador.ConfigFile;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class FicheroCrud implements CRUD {
	
	private ConfigFile conf;
	private final String CONF_FILE_NAME = "config/conf_fichero.ini"; 
	private FicheroParse fichero;
	
	private ListaVuelos vuelosCache;
	
	public FicheroCrud() 
	{
		conf = new ConfigFile(CONF_FILE_NAME);
		
		fichero = new FicheroParse(conf.getProperty("filepath"));
		vuelosCache = fichero.cargarVuelos();
	}

	@Override
	public ListaVuelos getVuelos()
	{
		return (ListaVuelos) vuelosCache.clone();
	}
	
	@Override
	public void setVuelos(ListaVuelos vuelos) 
	{
		fichero.escribirVuelos(vuelos);
	}

	
	@Override
	public void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception 
	{
		vuelosCache.replace(codigo, vuelo);
		setVuelos(vuelosCache);
	}

	@Override
	public void insertarVuelo(Vuelo vuelo) throws Exception 
	{
		vuelosCache.add(vuelo);
		setVuelos(vuelosCache);
	}

	@Override
	public void borrarVuelo(String codigo) throws Exception 
	{
		vuelosCache.remove(codigo);
		setVuelos(vuelosCache);
	}

	@Override
	public void dispose() {
		// Nothing to do.	
	}

}
