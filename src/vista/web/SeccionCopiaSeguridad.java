package vista.web;

import controlador.CRUD;
import controlador.CRUDManager;
import controlador.CRUDManager.MedioCRUD;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlParagraph;
import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;

public class SeccionCopiaSeguridad implements RuntimeSection {
	
	CRUDManager crudMan = new CRUDManager();
	MedioCRUD[] medios = MedioCRUD.values();

	@Override
	public EHtmlElementRaw generate(EHttpExchange $) {
		
		EHtmlContainer cont = new EHtmlContainer();
		
		if ($.hasGet("origen") && $.hasGet("destino")) {
			try {
				MedioCRUD origen = medios[Integer.parseInt($.get("origen"))];
				MedioCRUD destino = medios[Integer.parseInt($.get("destino"))];
				if (origen == destino)
					throw new Exception("El medio de origen y el medio de destino no pueden ser el mismo");
				crudMan.intercambioCrud(origen, destino);
				cont.add(new EHtmlParagraph("Copia de seguridad completada. " + origen.toString() + " -> " + destino.toString()));
			} catch (Exception e) {
				cont.add(new EHtmlParagraph("La copia de seguridad fue cancelada."));
				cont.add(new EHtmlParagraph(e.toString()));
				e.printStackTrace();
			}
		}
		
		return cont;
	}

}
