package vista.web;

import controlador.CRUDManager.MedioCRUD;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlHyperlink;
import io.github.alex00fer.ehttpj.html.EHtmlList;

public class ContenedorMenuMedios extends EHtmlContainer {
	
	public ContenedorMenuMedios() {
		
		MedioCRUD[] medios = MedioCRUD.values();
		String[] mediosMenu = new String[medios.length];
		for (int i = 0; i < mediosMenu.length; i++) {
			mediosMenu[i] = medios[i].toString();
		}
		
		EHtmlList list = new EHtmlList(false);
		int id = 0;
		for (String string : mediosMenu) {
			list.newListItem().add(new EHtmlHyperlink("/mostrar?medio=" + id++, string, false));
		}
		add(list);
	}

}
