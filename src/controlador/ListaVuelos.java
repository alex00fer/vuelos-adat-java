package controlador;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import modelo.Vuelo;

public class ListaVuelos implements Cloneable {
	HashMap<String, Vuelo> vuelos;

	public ListaVuelos(HashMap<String, Vuelo> vuelos) {
		this.vuelos = new HashMap<String, Vuelo>(vuelos);
	}

	public ListaVuelos() {
		this.vuelos = new HashMap<String, Vuelo>();
	}

	public void add(Vuelo v) 
	{
		String cod = v.getCodigo();
		if (vuelos.containsKey(cod))
			System.err.println("Se ha encontrado un vuelo con código duplicado: " + cod + ". Vuelo rechazado");
		else
			vuelos.put(cod, v);
	}
	
	public Vuelo get(String codigo) 
	{
		if (vuelos.containsKey(codigo))
			return vuelos.get(codigo);
		else
			return null;
	}
	
	public void remove(String codigo) throws Exception 
	{
		if (vuelos.containsKey(codigo))
			vuelos.remove(codigo);
		else
			throw new Exception("Código a borrar no encontrado");
	}
	
	public void replace(String codigo, Vuelo vuelo) throws Exception 
	{
		if (vuelos.containsKey(codigo)) {
			vuelos.remove(codigo);
			add(vuelo);
		}
		else
			throw new Exception("Código para reemplazar no encontrado");
	}
	
	public void filter (String filter) 
	{
		Iterator<Entry<String, Vuelo>> it = vuelos.entrySet().iterator();
	    while (it.hasNext())
	    {
	       Entry<String, Vuelo> item = it.next();
	       if (!item.getValue().contains(filter)) {
	    	   it.remove();
	    	   vuelos.remove(item.getKey());
	       }
	    }
	}
	
	public Collection<Vuelo> toCollection() 
	{
		return vuelos.values();
	}
	
	@Override
	  public Object clone() {
	  
		return new ListaVuelos (this.vuelos);
	  }
}
