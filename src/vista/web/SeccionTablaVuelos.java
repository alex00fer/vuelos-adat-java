package vista.web;

import java.util.Collection;

import app.AppUtils;
import controlador.CRUD;
import controlador.CRUDManager;
import controlador.CRUDManager.MedioCRUD;
import controlador.ListaVuelos;
import io.github.alex00fer.ehttpj.html.EHtmlButton;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlHyperlink;
import io.github.alex00fer.ehttpj.html.EHtmlParagraph;
import io.github.alex00fer.ehttpj.html.EHtmlTable;
import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;
import modelo.Vuelo;
import vista.Menu;

public class SeccionTablaVuelos implements RuntimeSection {
	
	CRUDManager crudMan = new CRUDManager();
	MedioCRUD[] medios = MedioCRUD.values();

	@Override
	public EHtmlElementRaw generate(EHttpExchange $) {
		EHtmlContainer cont = new EHtmlContainer();
		
		if ($.hasGet("medio")) {
			
			try {
				CRUD crud = crudMan.getCrud(medios[Integer.parseInt($.get("medio"))]);
				ListaVuelos vuelos = crud.getVuelos();
				EHtmlTable tabla = new EHtmlTable();
				cont.add(tabla);
				tabla.newHeaderRow("CODIGO", "ORIGEN", "DESTINO", "FECHA", "HORA", "PLAZAS");
				Collection<Vuelo> arrayVuelos = vuelos.toCollection();
				for (Vuelo vuelo : arrayVuelos) {
					tabla.newRow(
							vuelo.getCodigo(), vuelo.getSalida(), 
				    		vuelo.getDestino(), AppUtils.dateToString(vuelo.getFecha()), vuelo.getHora(), 
				    		vuelo.getPlazasLibres() + "/" + vuelo.getPlazas());
				}
				
			} catch (Exception e) {
				cont.add(new EHtmlParagraph(e.toString()));
			}
		
			
			
		
		} else {
			cont.add(new EHtmlParagraph("Seleccione un medio para mostrar los datos."));
		}
		
		
		
		return cont;
	}

}
