import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Die Klasse zum Starten der Anwendung.
 * @author Frank W. Rahn
 */
public class Starter {

	/**
	 * @param args die Argumente der Anwendung
	 */
	public static void main(String[] args) {
		// Initialisierung von Spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/context-app.xml");

		// Aufruf der Anwendung
		Runnable service = ctx.getBean("application", Runnable.class);
		service.run();
	}

}