package app;

import java.util.Scanner;

public class InputController {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static int readInteger() 
	{
		return readInteger(null, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static int readInteger(String message) 
	{
		return readInteger(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static int readInteger(int min, int max) 
	{
		return readInteger(null, min, max);
	}
	
	public static int readInteger(String message, int min, int max) 
	{
		String input = null;
	    int number = 0;
	    boolean valid = false;
	    
	    do {
		    try {
		    	if (message == null)
		    		System.out.print("Introduzca un número: ");
		    	else
		    		System.out.print(message + ": ");
		        input = scanner.nextLine();
		        number = Integer.parseInt(input);
		        if (number >= min && number <= max)
		        	valid = true;
		        else
		        	System.out.println("Entrada fuera de rango.");
		    } catch (NumberFormatException ex) {
		       System.out.println("Entrada inválida.");
		    }
	    } while (!valid);
	    
	    return number;
	}
	
	public static long readLong(String message, long min, long max) 
	{
		String input = null;
	    long number = 0;
	    boolean valid = false;
	    
	    do {
		    try {
		    	if (message == null)
		    		System.out.print("Introduzca un número: ");
		    	else
		    		System.out.print(message + ": ");
		        input = scanner.nextLine();
		        number = Long.parseLong(input);
		        if (number >= min && number <= max)
		        	valid = true;
		        else
		        	System.out.println("Entrada fuera de rango.");
		    } catch (NumberFormatException ex) {
		       System.out.println("Entrada inválida.");
		    }
	    } while (!valid);
	    
	    return number;
	}
	
	public static String readString(String message) 
	{
		return readString(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static String readString(String message, int min, int max) 
	{
		String input = null;
	    boolean valid = false;
	    
	    do {
	    	if (message == null)
		    	System.out.print("Entrada: ");
	    	else
	    		System.out.print(message + ": ");
		    input = scanner.nextLine().trim();
		    if (input != null && !input.isEmpty() && input.length() >= min && input.length() <= max)
		        	valid = true;
		    else
		        	System.out.println("Entrada inválida");
	    } while (!valid);
	    
	    return input;
	}
	
	public static void waitForEnter() {
		System.out.println();
		System.out.println("Presiona 'ENTER' para continuar...");		
		scanner.nextLine();
	}
	
	public static void waitFor(long ms) 
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}
	}

}
