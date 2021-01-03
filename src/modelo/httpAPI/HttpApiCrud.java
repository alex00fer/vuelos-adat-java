package modelo.httpAPI;

import java.util.Collection;

import org.bson.Document;
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

	@SuppressWarnings("unchecked")
	@Override
	public void setVuelos(ListaVuelos listaVuelos) throws Exception {
		// Borrar todos
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("codigo", "*");
		String response = api.deleteRequest(jsonObject.toJSONString());
		
		// Insertar todos
		Collection<Vuelo> vuelos = listaVuelos.toCollection();
		
		for (Vuelo vuelo : vuelos) {
			insertarVuelo(vuelo);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception {
		
		JSONObject jsonObject = vueloToJsonObject(vuelo);
		jsonObject.replace("codigo", codigo);
		jsonObject.put("nuevo_codigo", vuelo.getCodigo());
		
		String response = api.putRequest(jsonObject.toJSONString());
		
		JSONObject jsonRespuesta = (JSONObject) JSONValue.parse(response.toString());
		assertJsonCorrecto(jsonRespuesta); // verifica JSON respuesta
	}

	@Override
	public void insertarVuelo(Vuelo vuelo) throws Exception {
		
		JSONObject jsonObject = vueloToJsonObject(vuelo);
		
		String response = api.postRequest(jsonObject.toJSONString());
		
		JSONObject jsonRespuesta = (JSONObject) JSONValue.parse(response.toString());
		assertJsonCorrecto(jsonRespuesta); // verifica JSON respuesta
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void borrarVuelo(String codigo) throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("codigo", codigo);
		
		String response = api.deleteRequest(jsonObject.toJSONString());
		
		JSONObject jsonRespuesta = (JSONObject) JSONValue.parse(response.toString());
		assertJsonCorrecto(jsonRespuesta); // verifica JSON respuesta
	}

	@Override
	public void dispose() {
		// Nothing to dispose
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
	
	@SuppressWarnings("unchecked")
	private JSONObject vueloToJsonObject(Vuelo v) {
		JSONObject obj = new JSONObject();
		obj.put("codigo", v.getCodigo());
		obj.put("origen", v.getSalida());
		obj.put("destino",v.getDestino());
		obj.put("fecha",  v.getFecha().getTime());
		obj.put("hora", v.getHora());
		obj.put("plazas", v.getPlazas());
		obj.put("plazas_libres", v.getPlazasLibres());
		return obj;
	}

}
