package vista.web;

import java.util.Collection;

import app.AppUtils;
import controlador.CRUD;
import controlador.CRUDManager;
import controlador.CRUDManager.MedioCRUD;
import controlador.web.EnumAcciones;
import controlador.ListaVuelos;
import io.github.alex00fer.ehttpj.html.EHtmlButton;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlForm;
import io.github.alex00fer.ehttpj.html.EHtmlHyperlink;
import io.github.alex00fer.ehttpj.html.EHtmlImage;
import io.github.alex00fer.ehttpj.html.EHtmlList;
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
				
				cont.add(botonInsertar($.get("medio")));
				
				CRUD crud = crudMan.getCrud(medios[Integer.parseInt($.get("medio"))]);
				ListaVuelos vuelos = crud.getVuelos();
				if ($.hasPost("filter")) { 
					vuelos.filter($.post("filter"));
				}
				EHtmlTable tabla = new EHtmlTable();
				cont.add(tabla);
				tabla.newHeaderRow("###", "CODIGO", "ORIGEN", "DESTINO", "FECHA", "HORA", "PLAZAS");
				Collection<Vuelo> arrayVuelos = vuelos.toCollection();
				
				for (Vuelo vuelo : arrayVuelos) {
					tabla.newRow(
							iconoEditar($.get("medio"), vuelo.getCodigo()) + 
							iconoBorrar($.get("medio"), vuelo.getCodigo()),
							vuelo.getCodigo(), vuelo.getSalida(), 
				    		vuelo.getDestino(), AppUtils.dateToString(vuelo.getFecha()), vuelo.getHora(), 
				    		vuelo.getPlazasLibres() + "/" + vuelo.getPlazas());
				}
				
				cont.add(buscador($.get("medio"), $));
				
			} catch (Exception e) {
				cont.add(new EHtmlParagraph(e.toString()));
			}
			
		
		} else {
			cont.add(new EHtmlParagraph("Seleccione un medio para mostrar los datos."));
		}
		
		
		
		return cont;
	}
	
	private String iconoEditar(String medio, String codigo) {
		EHtmlHyperlink link = new EHtmlHyperlink("?medio="+medio+"&accion="+EnumAcciones.MODIFICAR.value()+"&target="+codigo, "", false);
		EHtmlImage img = new EHtmlImage("static/edit_icon.png", 20, 20);
		link.add(img);
		return link.toHtml();
	}
	
	private String iconoBorrar(String medio, String codigo) {
		EHtmlHyperlink link = new EHtmlHyperlink("?medio="+medio+"&accion="+EnumAcciones.BORRAR.value()+"&target="+codigo, "", false);
		EHtmlImage img = new EHtmlImage("static/delete_icon.png", 20, 20);
		link.add(img);
		link.addStyle("margin-left", "5px");
		return link.toHtml();
	}
	
	private EHtmlElementRaw botonInsertar(String medio) {
		EHtmlHyperlink l = new EHtmlHyperlink("?medio="+medio+"&accion="+EnumAcciones.INSERTAR.value(), "<b>+ Insertar vuelo</b>", false);
		l.addStyle("margin", "10px");
		l.addStyle("text-align", "center");
		l.addStyle("display", "block");
		return l;
	}
	
	private EHtmlElementRaw buscador(String medio, EHttpExchange $) {
		EHtmlContainer cont = new EHtmlContainer();
		cont.addStyle("margin", "10px");
		cont.addStyle("text-align", "center");
		cont.addStyle("display", "block");
		
		EHtmlForm form = new EHtmlForm("?medio="+medio, "POST");
		form.newLabeledInput("Filtrar campos con:", "text", "filter");
		form.newSubmitButton("Buscar");
		cont.add(form);
		
		if ($.hasPost("filter")) {
		
			EHtmlHyperlink l = new EHtmlHyperlink("?medio="+medio, "Borrar filtro de busqueda '" + $.post("filter") + "'", false);
			cont.add(l);
		
		}
		
		return cont;
	}

}
