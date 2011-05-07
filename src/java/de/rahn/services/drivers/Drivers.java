package de.rahn.services.drivers;

import de.rahn.services.drivers.entity.Car;
import de.rahn.services.drivers.entity.Driver;

/**
 * Das Interface zum Service Drivers.
 * @author Frank W. Rahn
 */
public interface Drivers {

	/**
	 * Hole einen Fahrer.
	 * @param id die Id eines Fahrers
	 * @return der Fahrer
	 */
	Driver getDriver(Long id);

	/**
	 * Lege einen Fahrer an.
	 * @param name der Name des Fahrers
	 * @param firstname der Vorname des Fahrers
	 * @return die Id des Fahrers
	 */
	Long createDriver(String name, String firstname);

	/**
	 * Füge ein Auto zum Fahrer hinzu
	 * @param id die Id des Fahrers
	 * @param car das Auto
	 * @return der komplette Fahrer
	 */
	Driver addCarToDriver(Long id, Car car);

}