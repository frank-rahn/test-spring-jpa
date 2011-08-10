package de.rahn.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.rahn.services.drivers.Drivers;
import de.rahn.services.drivers.entity.Car;
import de.rahn.services.drivers.entity.Driver;

/**
 * Die Anwendung zum Aufrufen des Taschenrechners.
 * @author Frank W. Rahn
 */
@Component
public class Application implements Runnable {

	private static final Logger logger = LoggerFactory
		.getLogger(Application.class);

	@Autowired(required = true)
	private Drivers drivers;

	/**
	 * {@inheritDoc}
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// Lege einen Fahrer an
		Long id = drivers.createDriver("Rahn", "Frank");
		logger.info("Einen Fahrer mit der Id '{}' angelegt", id);

		// Hole den Fahrer wieder
		Driver driver = drivers.getDriver(id);
		logger.info("Den Fahrer mit der Id '{}' geholt: {}", id, driver);

		// Lege ein Auto an
		Car car = new Car();
		car.setId("K-XX 4711");
		car.setType("Audi A6");
		driver = drivers.addCarToDriver(id, car);
		logger.info("Den Fahrer mit der Id '{}' geändert: {}", id, driver);

		// Alle Fahrer selektieren
		List<Driver> listDrivers = drivers.getDrivers();
		for (Driver driver2 : listDrivers) {
			logger.info(
				"Fahrer: Id '{}' Name: {} {}",
				new Object[] { driver2.getId(), driver2.getFirstname(),
					driver2.getName() });
		}
	}

}