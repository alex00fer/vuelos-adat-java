package vista;

import app.InputController;
import controlador.web.InterfazWeb;
import io.github.alex00fer.ehttpj.EHttpUtils;

public class VistaPrincipal implements Vista {
	
	Vista header, vistaCargar, vistaIntercambiar;
	Menu menuPrincipal;
	
	public VistaPrincipal() 
	{
		header = new VistaHeader();
		vistaCargar = new VistaCargarMedio();
		vistaIntercambiar = new VistaIntercambioDatos();
		menuPrincipal = new Menu("Cargar desde...", "Intercambio de datos", "Interfaz web *EN DESARROLLO*", "Salir de la aplicación");
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
			//opcion = 3;
			switch (opcion) {
			
			case 1:
				vistaCargar.mostrar();
				break;
				
			case 2:
				vistaIntercambiar.mostrar();
				break;
				
			case 3:
				InterfazWeb web = new InterfazWeb(6776);
				System.out.println("Accede a http://localhost:6776");
				EHttpUtils.tryOpenWebpage("http://localhost:6776");
				InputController.waitForEnter();
				break;
	
			default:
				exit = true;
				break;
			}
		}
		System.exit(0);
	}
}
