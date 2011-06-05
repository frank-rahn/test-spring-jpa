package de.rahn.services.drivers.standard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.rahn.services.drivers.Drivers;
import de.rahn.services.drivers.dao.DriverDAO;
import de.rahn.services.drivers.entity.Car;
import de.rahn.services.drivers.entity.Driver;

/**
 * Die Standard-Implementierung des Services {@link Drivers}.
 * @author Frank W. Rahn
 */
@Service("drivers")
@Transactional
public class StandardDrivers implements Drivers {

	@Autowired
	private DriverDAO driverDAO;

	/**
	 * {@inheritDoc}
	 * @see de.rahn.services.drivers.Drivers#getDrivers()
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Driver> getDrivers() {
		return driverDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.services.drivers.Drivers#getDriver(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Driver getDriver(Long id) {
		return driverDAO.findByPrimaryKey(id);
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.services.drivers.Drivers#createDriver(java.lang.String, java.lang.String)
	 */
	@Override
	public Long createDriver(String name, String firstname) {
		Driver driver = new Driver();
		driver.setName(name);
		driver.setFirstname(firstname);

		return driverDAO.create(driver);
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.services.drivers.Drivers#create(de.rahn.services.drivers.entity.Driver)
	 */
	@Override
	public Long create(Driver driver) {
		return driverDAO.create(driver);
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.services.drivers.Drivers#save(de.rahn.services.drivers.entity.Driver)
	 */
	@Override
	public Driver save(Driver driver) {
		driverDAO.save(driver);
		return driver;
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.services.drivers.Drivers#addCarToDriver(java.lang.Long, de.rahn.services.drivers.entity.Car)
	 */
	@Override
	public Driver addCarToDriver(Long id, Car car) {
		Driver driver = driverDAO.findByPrimaryKey(id);
		driver.getCars().add(car);

		driverDAO.save(driver);
		return driver;
	}

}