package modelo.httpAPI;

import org.json.simple.*;

import app.AppUtils;
import controlador.CRUD;
import controlador.ConfigFile;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class HttpApiCrud implements CRUD {
	
	private HttpApiRequests api;
	
	private ConfigFile conf;
	private final String CONF_FILE_NAME = "config/conf_httpapi.ini"; 
	
	public HttpApiCrud() {
		conf = new ConfigFile(CONF_FILE_NAME);
		api = new HttpApiRequests(conf.getProperty("url"));
	}

	@Override
	public ListaVuelos getVuelos() throws Exception {
		String response = api.getRequest();
		JSONObject jsonRespuesta = (JSONObject) JSONValue.parse(response.toString());
		
		assertJsonCorrecto(jsonRespuesta); // verifica JSON
		
		ListaVuelos vuelos = new ListaVuelos();
		JSONArray array = (JSONArray) jsonRespuesta.get("vuelos");
		
		for (Object objectJsonVuelo : array) {
			Vuelo vuelo = JsonObjectToVuelo((JSONObject)objectJsonVuelo);
			vuelos.add(vuelo);
		}

		return vuelos;
	}

	@Override
	public void setVuelos(ListaVuelos vuelos) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarVuelo(Vuelo vuelo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarVuelo(String codigo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Verifica que el JSON sea correcto y sea exitoso. En caso de no serlo
	 * lanza una Exepción
	 */
	private void assertJsonCorrecto(JSONObject jsonRespuesta) {
		if (jsonRespuesta == null) 
			throw new RuntimeException("Respuesta del servidor inválida");
		
		if (!(boolean)jsonRespuesta.get("success"))
			if (jsonRespuesta.containsKey("error"))
				throw new RuntimeException("El servidor respondió con un error: " + jsonRespuesta.get("error").toString());
			else
				throw new RuntimeException("El servidor respondió con un error desconocido");
	}
	
	private Vuelo JsonObjectToVuelo(JSONObject doc) {
		return new Vuelo(doc.get("codigo").toString(), doc.get("origen").toString(), doc.get("destino").toString(),
					AppUtils.StringDataToLong((String)doc.get("fecha")) + "" , doc.get("hora").toString(), (String)doc.get("plazas") + "",
					(String)doc.get("plazasLibres") + "");
	}

}
