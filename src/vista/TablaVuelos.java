package vista;

import java.util.Collection;
import app.AppUtils;
import controlador.ListaVuelos;
import controlador.Vuelo;

public class TablaVuelos implements Vista {
	
	private ListaVuelos vuelos;

	public TablaVuelos(ListaVuelos vuelos) 
	{
		this.vuelos = vuelos;
	}

	@Override
	public void mostrar() 
	{
		imprimirCabecera();
		imprimirContenido();
	}
	
	private void imprimirCabecera() 
	{
		System.out.println();
		System.out.format("| CÓDIGO | ORIGEN       | DESTINO      | FECHA      | HORA  | PLAZAS  |%n");
		System.out.format("-----------------------------------------------------------------------%n");
	}
	
	private void imprimirContenido()
	{
		Collection<Vuelo> arrayVuelos = vuelos.toCollection();

		String rowFormat = "| %-6s | %-12s | %-12s | %-8s | %-5s | %-7s |%n";
		
		for (Vuelo vuelo : arrayVuelos) {
		    System.out.format(rowFormat, 
		    		vuelo.getCodigo(), vuelo.getSalida(), 
		    		vuelo.getDestino(), AppUtils.dateToString(vuelo.getFecha()), vuelo.getHora(), 
		    		vuelo.getPlazasLibres() + "/" + vuelo.getPlazas());
		}
	}

}
