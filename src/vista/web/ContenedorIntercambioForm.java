package vista.web;

import controlador.CRUDManager.MedioCRUD;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlDropDown;
import io.github.alex00fer.ehttpj.html.EHtmlForm;

public class ContenedorIntercambioForm extends EHtmlContainer {
	
	public ContenedorIntercambioForm() {
		EHtmlForm form = new EHtmlForm("", "GET");
		EHtmlDropDown ddA = form.newLabeledDropdown("Medio de origen:", "origen");
		EHtmlDropDown ddB =form.newLabeledDropdown("Medio de destino:", "destino");
		
		MedioCRUD[] medios = MedioCRUD.values();
		String[] mediosMenu = new String[medios.length];
		for (int i = 0; i < mediosMenu.length; i++) {
			mediosMenu[i] = medios[i].toString();
			ddA.addOption(mediosMenu[i], i + "");
			ddB.addOption(mediosMenu[i], i + "");
		}
		
		form.newSubmitButton("Hacer copia");
		form.addStyle("padding", "10px");
		
		this.add(form);
		addStyle("text-align", "center");
	}
	
}
