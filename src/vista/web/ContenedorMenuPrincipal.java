package vista.web;

import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlHyperlink;
import io.github.alex00fer.ehttpj.html.EHtmlList;

public class ContenedorMenuPrincipal extends EHtmlContainer{
	
	public ContenedorMenuPrincipal() {
		EHtmlList list = new EHtmlList(false);
		list.newListItem().add(new EHtmlHyperlink("/mostrar", "Cargar vuelos desde...", false));
		list.newListItem().add(new EHtmlHyperlink("/intercambio", "Copia de seguridad", false));
		add(list);
	}

}
