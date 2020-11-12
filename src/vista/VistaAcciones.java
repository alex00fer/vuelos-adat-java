package vista;

import app.InputController;
import controlador.CRUD;
import controlador.ListaVuelos;
import modelo.Vuelo;

public class VistaAcciones implements Vista {
	
	Vista tabla;
	Menu menuAcciones;
	CRUD crud;
	
	public VistaAcciones(CRUD crud) 
	{
		this.crud = crud;
		menuAcciones  = new Menu("Mostrar de nuevo", "Buscar", "Añadir", "Editar", "Borrar", "Salir al menu principal");
	}
	
	@Override
	public void mostrar() 
	{
		boolean mostrar = true, filter = false;
		String filterText = "";
		try {
		while (mostrar) {
				System.out.println("Cargando vuelos...");
				// mostrar vuelos
				ListaVuelos listaVuelos = crud.getVuelos();
				// si filtrado de datos activado filtrar resultados
				if (filter)
					listaVuelos.filter(filterText);
				
				tabla = new TablaVuelos(listaVuelos);
				tabla.mostrar();
				
				InputController.waitForEnter();
	
				// mostrar acciones
				System.out.println("  ELIGE LA ACCIÓN A REALIZAR");
				menuAcciones.mostrar();
				filter = false; // reseta la busqueda para la siguiente accion
				int opcion = menuAcciones.seleccionado();
				
				switch (opcion) {
				
					case 2: // Buscar
						filter = true;
						filterText = InputController.readString("Filtro de busqueda");
						break;
						
					case 3: // Añadir
						insertarVuelo();
						break;
					
					case 4: // Editar
						editarVuelo();
						break;
						
					case 5: // Borrar
						borrarVuelo();
						break;
						
					case 6: // Salir
						mostrar = false;
						break;
		
					default:
						break;
				}
			}
		} catch (Exception ex) 
		{
			//ex.printStackTrace(); // debug
			System.err.println("Error: " + ex.getCause() + ex.getMessage());
			InputController.waitFor(2000);
		}
	}
	
	
	private void editarVuelo() 
	{
		try {
			ListaVuelos listaVuelos = crud.getVuelos(); // obtiene una lista limpia
			
			System.out.println();
			System.out.println("Introduce el código de vuelo que quieres modificar");
			String codigo = InputController.readString(">Código del vuelo a editar", 5, 5);
			
			// comprobar que el codigo sea valido
			Vuelo vueloSeleccionado = listaVuelos.get(codigo);
			if (vueloSeleccionado == null) {
				throw new Exception("El código introducido no coincide con ningún vuelo");
			}
			
			System.out.println("A continuación introduce todos los nuevos valores");
			Vuelo nuevosDatos = pedirVuelo();
			
			vueloSeleccionado.copyFrom(nuevosDatos); // mantener objeto original con los nuevos datos
		
			crud.actualizarVuelo(codigo, vueloSeleccionado);
			
			System.out.println("Vuelo actualizado");
			
		} catch (Exception e) {
			System.err.println("Operacion modificar cancelada. " + e.getMessage());
			e.printStackTrace();
			InputController.waitFor(2000);
		}
	}
	
	private void insertarVuelo() 
	{
		try {
			System.out.println();
			System.out.println("A continuación introduce los valores para el nuevo vuelo");
			Vuelo vuelo = pedirVuelo();
		
			crud.insertarVuelo(vuelo);
			
			System.out.println("Vuelo insertado");
			
		} catch (Exception e) {
			System.err.println("Operacion insertar cancelada. " + e.getMessage());
			InputController.waitFor(2000);
		}
	}
	
	private Vuelo pedirVuelo() 
	{
		return new Vuelo(
				InputController.readString(">Código", 5, 5), 
				InputController.readString(">Origen", 1, 50), 
				InputController.readString(">Destino", 1, 50), 
				InputController.readLong(">Fecha (seconds since the Epoch) ", 0, Long.MAX_VALUE) + "", 
				InputController.readString(">Hora (00:00)", 5, 5), 
				InputController.readInteger(">Plazas", 0, 9999) + "", 
				InputController.readInteger(">Plazas libres", 0, 9999) + ""
				);
	}
	
	private void borrarVuelo() 
	{
		try {
			System.out.println();
			System.out.println("Introduce el código de vuelo que quieres BORRAR");
			String codigo = InputController.readString(">Código del vuelo a borrar", 5, 5);
		
			crud.borrarVuelo(codigo);
			
			System.out.println("Vuelo borrado.");
			
		} catch (Exception e) {
			System.err.println("Operacion de borrado cancelada. " + e.getMessage());
			InputController.waitFor(2000);
		}
	}
}
