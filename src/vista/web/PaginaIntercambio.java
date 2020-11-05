package vista.web;

public class PaginaIntercambio extends PaginaTemplate {
	
	public PaginaIntercambio() {
		super(new ContenedorIntercambioForm());
		super.addRuntimeSection(new SeccionCopiaSeguridad());
	}

}
