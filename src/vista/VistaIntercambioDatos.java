package vista;

import java.util.LinkedList;
import java.util.List;
import app.InputController;
import controlador.CRUDManager;
import controlador.CRUDManager.MedioCRUD;

public class VistaIntercambioDatos implements Vista {
	
	CRUDManager crudMan;
	MedioCRUD[] medios;
	MedioCRUD medioA, medioB;
	Menu menuIntercambioA, menuIntercambioB;
	
	public VistaIntercambioDatos() 
	{
		crudMan = new CRUDManager();
		medios = MedioCRUD.values();
		String[] mediosMenu = new String[medios.length];
		for (int i = 0; i < mediosMenu.length; i++) {
			mediosMenu[i] = medios[i].toString();
		}
		menuIntercambioA = new Menu(mediosMenu);
	}
	
	@Override
	public void mostrar() 
	{
		System.out.println();
		System.out.println("MEDIO DE ORIGEN:");
		medioA = mostrarMenuOrigen();
		
		System.out.println();
		System.out.println("MEDIO DE DESTINO:");
		medioB = mostrarMenuDestino();
		

		try {
			
			crudMan.intercambioCrud(medioA, medioB);
			
			System.out.println("Copia de datos completada: " + medioA+" -> "+medioB);
			
		} catch (Exception e) {
			System.err.println("Se ha producido un error durante la copia de datos: " + e.getMessage());
		}
		
		InputController.waitFor(2000);
	}
	
	private MedioCRUD mostrarMenuOrigen()
	{
		menuIntercambioA.mostrar();
		int opcion = menuIntercambioA.seleccionado();
		return medios[opcion-1];
	}
	
	private MedioCRUD mostrarMenuDestino()
	{
		// Crear una lista sin el medio A
		List<MedioCRUD> mediosDestino = new LinkedList<MedioCRUD>();
	    for(MedioCRUD medio : medios)
	        if(medioA != medio)
	        	mediosDestino.add(medio);
	    
	    // Crear array de strings
	    String[] mediosDestinoTexto = new String[mediosDestino.size()];
		for (int i = 0; i < mediosDestinoTexto.length; i++) {
			mediosDestinoTexto[i] = mediosDestino.get(i).toString();
		}
		
		// Pedir medio y devolver seleccionado
		menuIntercambioB = new Menu(mediosDestinoTexto);
		menuIntercambioB.mostrar();
		int opcion = menuIntercambioB.seleccionado();
		return mediosDestino.get(opcion-1);
	}
}
