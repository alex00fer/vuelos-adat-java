package controlador.web;

import java.net.BindException;

import io.github.alex00fer.ehttpj.EHttpServer;
import io.github.alex00fer.ehttpj.EHttpStatic;
import vista.web.PaginaIntercambio;
import vista.web.PaginaMostrar;
import vista.web.PaginaPrincipal;

public class InterfazWeb {
	
	public InterfazWeb(int port) {
		try {
			EHttpServer s = new EHttpServer(port);
			s.addRouting("/", new PaginaPrincipal());
			s.addRouting("/mostrar", new PaginaMostrar());
			s.addRouting("/intercambio", new PaginaIntercambio());
			s.addRouting("/static", new EHttpStatic("/static", "webcontent"));
			s.start();
			
		} catch(BindException e) {
			System.err.println("El servidor ya está iniciado en localhost:6776");
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("El servidor web no se pudo iniciar");
		}
	}

}
