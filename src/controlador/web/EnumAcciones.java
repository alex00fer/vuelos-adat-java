package controlador.web;

public enum EnumAcciones {
    INSERTAR(0), BORRAR(1), MODIFICAR(2);

    private final int value;
    private final static EnumAcciones[] values = EnumAcciones.values();
    
    
    private EnumAcciones(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
    
    public static EnumAcciones fromValue(int x) {
    	return values[x];
    }
}
