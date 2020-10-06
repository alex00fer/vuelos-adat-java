package vista;

public class VistaPrincipal implements Vista {
	
	Vista header, vistaCargar, vistaIntercambiar;
	Menu menuPrincipal;
	
	public VistaPrincipal() 
	{
		header = new VistaHeader();
		vistaCargar = new VistaCargarMedio();
		vistaIntercambiar = new VistaIntercambioDatos();
		menuPrincipal = new Menu("Cargar desde...", "Intercambio de datos", "Salir de la aplicación");
	}
	
	@Override
	public void mostrar() 
	{
		boolean exit = false;
		while (!exit) {
			header.mostrar();
			System.out.println();
			menuPrincipal.mostrar();
			int opcion = menuPrincipal.seleccionado();
			switch (opcion) {
			
			case 1:
				vistaCargar.mostrar();
				break;
				
			case 2:
				vistaIntercambiar.mostrar();
				break;
				
			case 3:
				exit = true;
				break;
	
			default:
				break;
			}
		}
		System.exit(0);
	}
}
