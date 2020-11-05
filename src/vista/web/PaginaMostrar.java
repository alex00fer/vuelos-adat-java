package vista.web;

public class PaginaMostrar extends PaginaTemplate {
	
	public PaginaMostrar() {
		super(new ContenedorMenuMedios());
		super.addRuntimeSection(new SeccionTablaVuelos());
	}

}
