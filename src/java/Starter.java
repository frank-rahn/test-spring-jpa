import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Die Klasse zum Starten der Anwendung.
 * @author Frank W. Rahn
 */
public class Starter {

	private static final Logger logger = getLogger(Starter.class);

	/**
	 * @param args die Argumente der Anwendung
	 */
	public static void main(String[] args) {
		// Initialisierung von Spring
		ApplicationContext ctx =
			new ClassPathXmlApplicationContext(
				"/META-INF/spring/context-app.xml");

		if (logger.isDebugEnabled()) {
			// Ausgabe der geladenen Beans
			List<String> zeilen = new ArrayList<String>();
			for (String name : ctx.getBeanDefinitionNames()) {
				Class<?> type = ctx.getType(name);
				StringBuilder builder =
					new StringBuilder("\n\t Class " + type.getName());
				builder.append(", Bean Id: " + name);

				String[] aliase = ctx.getAliases(name);

				if (aliase != null) {
					for (String alias : aliase) {
						builder.append(", Alias: " + alias);
					}
				}
				zeilen.add(builder.toString());
			}
			Collections.sort(zeilen);
			StringBuilder builder =
				new StringBuilder("##### ApplicationContext : START");
			for (String zeile : zeilen) {
				builder.append(zeile);
			}
			logger.debug(builder.append("\n##### ApplicationContext : END")
				.toString());
		}

		// Aufruf der Anwendung
		Runnable service = ctx.getBean("application", Runnable.class);
		service.run();
	}

}