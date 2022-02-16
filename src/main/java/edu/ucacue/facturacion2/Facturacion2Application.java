package edu.ucacue.facturacion2;

import java.io.FileNotFoundException;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import edu.ucacue.facturacion2.controller.PrincipalUI;
import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class Facturacion2Application {

	public static void main(String[] args) throws FileNotFoundException, JRException {
		// SpringApplication.run(Facturacion2Application.class, args);

		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(Facturacion2Application.class)
				.headless(false).web(WebApplicationType.NONE).run(args);

		PrincipalUI ventana = contexto.getBean(PrincipalUI.class);
		ventana.setVisible(true);
	}

}
