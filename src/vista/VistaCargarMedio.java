package vista;

import controlador.CRUD;
import controlador.CRUDManager;
import controlador.CRUDManager.MedioCRUD;

public class VistaCargarMedio implements Vista {
	
	CRUDManager crudMan;
	MedioCRUD[] medios;
	Menu menuMedios;
	
	public VistaCargarMedio() 
	{
		crudMan = new CRUDManager();
		medios = MedioCRUD.values();
		String[] mediosMenu = new String[medios.length];
		for (int i = 0; i < mediosMenu.length; i++) {
			mediosMenu[i] = medios[i].toString();
		}
		menuMedios = new Menu(mediosMenu);
	}
	
	@Override
	public void mostrar() 
	{
		System.out.println();
		System.out.println("CARGAR DESDE MEDIO:");
		menuMedios.mostrar();
		int opcion = menuMedios.seleccionado();

		try {
			
			CRUD crud = crudMan.getCrud(medios[opcion-1]);
			Vista vista = new VistaAcciones(crud);
			vista.mostrar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
