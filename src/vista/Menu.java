package vista;

import app.InputController;

public class Menu implements Vista {
	
	private String[] options;
	private int seleccion = 1;
	
	public Menu (String... options) 
	{
		this.options = options;
	}
	
	@Override
	public void mostrar() 
	{
		System.out.println("/ ------------------------- \\");
		for (int i = 0; i < options.length; i++) {
			System.out.println("  "+(i+1) + " - " + options[i]);
		}
		System.out.println("\\ ------------------------- /");
		
		seleccion = InputController.readInteger(1, options.length);
	}
	
	
	/**
	 * Elemento seleccionado tras la llamada a mostrar()
	 */
	public int seleccionado() 
	{
		return seleccion;
	}

}
