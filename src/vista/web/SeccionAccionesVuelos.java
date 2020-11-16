package vista.web;

import controlador.CRUD;
import controlador.CRUDManager;
import controlador.CRUDManager.MedioCRUD;
import controlador.web.EnumAcciones;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlForm;
import io.github.alex00fer.ehttpj.html.EHtmlParagraph;
import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;
import modelo.Vuelo;

public class SeccionAccionesVuelos implements RuntimeSection {
	
	CRUDManager crudMan = new CRUDManager();
	MedioCRUD[] medios = MedioCRUD.values();

	@Override
	public EHtmlElementRaw generate(EHttpExchange $) {
		EHtmlContainer cont = new EHtmlContainer();
		CRUD crud = null;
		if ($.hasGet("medio")) {
			
			try {
				crud = crudMan.getCrud(medios[Integer.parseInt($.get("medio"))]);
				String res = procesarAcciones($, crud);
				cont.add(new EHtmlParagraph(res));
			} catch (Exception e) {
				cont.add(new EHtmlParagraph("CRUD operation failed: " + e.getMessage()));
				e.printStackTrace();
			}
		
		}
		

		if ($.hasGet("medio") && $.hasGet("accion")) {

			EnumAcciones accion = EnumAcciones.fromValue(Integer.parseInt($.get("accion")));
			EHtmlForm form = new EHtmlForm("?medio=" + $.get("medio"), "POST");

			// BORRAR
			if (accion == EnumAcciones.BORRAR)
				if ($.hasGet("target"))
					generarFormBorrar(form, $.get("target"));

			// INSERTAR
			if (accion == EnumAcciones.INSERTAR)
				generarFormInsertar(form);

			// MODIFICAR
			if (accion == EnumAcciones.MODIFICAR)
				if ($.hasGet("target"))  {
					try {
						Vuelo vuelo = crud.getVuelos().get($.get("target"));
						generarFormModificar(form, $.get("target"), vuelo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					
				


			form.newSubmitButton(accion.toString().toUpperCase());
			form.addStyle("padding", "10px");

			cont.add(form);
			cont.addStyle("text-align", "center");

		}

		return cont;
	}

	private void generarFormBorrar(EHtmlForm form, String codigo) {
		form.newLabeledInput("Codigo", "text", "codigo", codigo);
	}

	private void generarFormInsertar(EHtmlForm form) {
		form.newLabeledInput("Codigo", "text", "codigo");
		form.newLabeledInput("Origen", "text", "origen");
		form.newLabeledInput("Destino", "text", "destino");
		form.newLabeledInput("Fecha (POSIX time)", "number", "fecha");
		form.newLabeledInput("Hora", "text", "hora");
		form.newLabeledInput("Plazas", "number", "plazas");
		form.newLabeledInput("Plazas libres", "number", "plazas_libres");
	}
	
	private void generarFormModificar(EHtmlForm form, String codigo, Vuelo v) {
		
		form.newLabeledInput("Codigo del vuelo a modificar", "text", "codigo_original", codigo);
		form.newLabeledInput("Nuevo codigo", "text", "codigo", codigo);
		form.newLabeledInput("Origen", "text", "origen", v.getSalida());
		form.newLabeledInput("Destino", "text", "destino", v.getDestino());
		form.newLabeledInput("Fecha (POSIX time)", "number", "fecha", v.getFecha().getTime() + "");
		form.newLabeledInput("Hora", "text", "hora", v.getHora());
		form.newLabeledInput("Plazas", "number", "plazas", v.getPlazas() + "");
		form.newLabeledInput("Plazas libres", "number", "plazas_libres", v.getPlazasLibres() + "");
	}
	
	private String procesarAcciones(EHttpExchange $, CRUD crud) throws Exception {
		if ($.hasPost("__action")) {
			
			
			
			if ($.post("__action").equalsIgnoreCase(EnumAcciones.BORRAR.toString())) {
				
				crud.borrarVuelo($.post("codigo"));
				return "Vuelo con codigo "+ $.post("codigo") + " borrado";
				
			}
			
			if ($.post("__action").equalsIgnoreCase(EnumAcciones.INSERTAR.toString())) {
				
				crud.insertarVuelo(
						new Vuelo(
								$.post("codigo"), 
								$.post("origen"), 
								$.post("destino"), 
								$.post("fecha"), 
								$.post("hora"), 
								$.post("plazas"), 
								$.post("plazas_libres")
								)
						);
				return "Vuelo con codigo "+ $.post("codigo") + " insertado";
				
			}
			
			if ($.post("__action").equalsIgnoreCase(EnumAcciones.INSERTAR.toString())) {
				
				crud.insertarVuelo(
						new Vuelo(
								$.post("codigo"), 
								$.post("origen"), 
								$.post("destino"), 
								$.post("fecha"), 
								$.post("hora"), 
								$.post("plazas"), 
								$.post("plazas_libres")
								)
						);
				return "Vuelo con codigo "+ $.post("codigo") + " insertado";
				
			}
			
			if ($.post("__action").equalsIgnoreCase(EnumAcciones.MODIFICAR.toString())) {
				
				crud.actualizarVuelo($.post("codigo_original"),
						new Vuelo(
								$.post("codigo"), 
								$.post("origen"), 
								$.post("destino"), 
								$.post("fecha"), 
								$.post("hora"), 
								$.post("plazas"), 
								$.post("plazas_libres")
								)
						);
				return "Vuelo con codigo "+ $.post("codigo_original") + " actualizado";
				
			}
			
			
			
		}
		
		return "";
	}
}
