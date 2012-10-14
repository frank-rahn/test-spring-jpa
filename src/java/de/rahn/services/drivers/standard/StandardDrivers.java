package de.rahn.services.drivers.standard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('ROLE_USER')")
public class StandardDrivers implements Drivers {

	@Autowired
	private DriverDAO driverDAO;

	/**
	 * {@inheritDoc}
	 * @see Drivers#getDrivers()
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Driver> getDrivers() {
		return driverDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see Drivers#getDriver(Long)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Driver getDriver(Long id) {
		return driverDAO.findByPrimaryKey(id);
	}

	/**
	 * {@inheritDoc}
	 * @see Drivers#createDriver(String, String)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Long createDriver(String name, String firstname) {
		Driver driver = new Driver();
		driver.setName(name);
		driver.setFirstname(firstname);

		return create(driver);
	}

	/**
	 * {@inheritDoc}
	 * @see Drivers#create(Driver)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Long create(Driver driver) {
		return driverDAO.create(driver);
	}

	/**
	 * {@inheritDoc}
	 * @see Drivers#save(Driver)
	 */
	@Override
	public Driver save(Driver driver) {
		driverDAO.save(driver);
		return driver;
	}

	/**
	 * {@inheritDoc}
	 * @see Drivers#addCarToDriver(Long, Car)
	 */
	@Override
	public Driver addCarToDriver(Long id, Car car) {
		Driver driver = driverDAO.findByPrimaryKey(id);
		driver.getCars().add(car);

		return save(driver);
	}

	/**
	 * {@inheritDoc}
	 * @see Drivers#deleteDriver(Long)
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteDriver(Long id) {
		driverDAO.remove(id);
	}

}
