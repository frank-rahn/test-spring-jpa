package de.rahn.services.drivers;

import java.util.List;

import de.rahn.services.drivers.entity.Car;
import de.rahn.services.drivers.entity.Driver;

/**
 * Das Interface zum Service Drivers.
 * @author Frank W. Rahn
 */
public interface Drivers {

	/**
	 * Hole alle Fahrer.
	 * @return die Liste der Fahrer
	 */
	List<Driver> getDrivers();

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
	 * Lege einen Fahrer an.
	 * @param driver der Fahrer
	 * @return die Id des Fahrers
	 */
	Long create(Driver driver);

	/**
	 * Speichere den Fahrer.
	 * @param driver der Fahrer
	 */
	Driver save(Driver driver);

	/**
	 * Füge ein Auto zum Fahrer hinzu.
	 * @param id die Id des Fahrers
	 * @param car das Auto
	 * @return der komplette Fahrer
	 */
	Driver addCarToDriver(Long id, Car car);

	/**
	 * Lösche den Fahrer.
	 * @param id die Id eines Fahrers
	 */
	void deleteDriver(Long id);

}