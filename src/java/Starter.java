import static org.slf4j.LoggerFactory.getLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.rahn.app.Application;

/**
 * Die Klasse zum Starten der Anwendung.
 * @author Frank W. Rahn
 */
public class Starter {

	private static final Logger logger = getLogger(Application.class);

	/**
	 * @param args die Argumente der Anwendung
	 */
	public static void main(String[] args) {
		// Initialisierung von Spring
		ApplicationContext ctx =
			new ClassPathXmlApplicationContext(
				"/META-INF/spring/context-app.xml");

		try {
			// Einlesen der Authentisierungsdaten
			BufferedReader in =
				new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter your username:");
			String name = in.readLine();
			System.out.println("Please enter your password:");
			String password = in.readLine();

			// Authentifizierung des Benutzers
			AuthenticationManager authenticationManager =
				ctx.getBean(BeanIds.AUTHENTICATION_MANAGER,
					AuthenticationManager.class);
			Authentication request =
				new UsernamePasswordAuthenticationToken(name, password);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (Exception exception) {
			logger.error("Fehler bei der Authentifizierung", exception);
			return;
		}

		// Aufruf der Anwendung
		Runnable service = ctx.getBean("application", Runnable.class);
		service.run();
	}

}
