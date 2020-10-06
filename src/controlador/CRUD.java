package controlador;

public interface CRUD {
	
	ListaVuelos getVuelos() throws Exception;
	void setVuelos(ListaVuelos vuelos) throws Exception;
	void actualizarVuelo(String codigo, Vuelo vuelo) throws Exception;
	void insertarVuelo (Vuelo vuelo) throws Exception;
	void borrarVuelo (String codigo) throws Exception;

}
