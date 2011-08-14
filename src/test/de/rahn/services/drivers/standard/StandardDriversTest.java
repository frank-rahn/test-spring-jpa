package de.rahn.services.drivers.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.rahn.services.drivers.Drivers;
import de.rahn.services.drivers.entity.Car;
import de.rahn.services.drivers.entity.Driver;

/**
 * Die Testklasse für {@link StandardDrivers}.
 * @author Frank W. Rahn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/context-test.xml",
	"../drivers.xml" })
@Transactional
public class StandardDriversTest {

	/** CAR_TYPE */
	private static final String CAR_TYPE = "Audi A6";

	/** CAR_ID */
	private static final String CAR_ID = "K-XX 4711";

	@Autowired
	private Drivers drivers;

	/**
	 * Test method for {@link StandardDrivers#getDriver(Long)}.
	 */
	@Test
	public void testGetDriver() {
		Driver driver = drivers.getDriver(0L);
		assertEquals("id ungleich", new Long(0), driver.getId());
		assertEquals("firstname ungleich", "Martin", driver.getFirstname());
		assertEquals("name ungleich", "Rahn", driver.getName());
	}

	/**
	 * Test method for {@link StandardDrivers#createDriver(String, String)}.
	 */
	@Test
	public void testCreateDriver() {
		Long id = drivers.createDriver("Rahn", "Frank");
		assertNotNull("keine id geliefert", id);

		Driver driver = drivers.getDriver(id);
		assertNotNull("doch nicht gespeichert", driver);
		assertEquals("ungleiche id", id, driver.getId());
		assertEquals("firstname ungleich", "Frank", driver.getFirstname());
		assertEquals("name ungleich", "Rahn", driver.getName());
	}

	/**
	 * Test method for {@link StandardDrivers#addCarToDriver(Long, Car)}.
	 */
	@Test
	public void testAddCarToDriver() {
		Car car = new Car();
		car.setId(CAR_ID);
		car.setType(CAR_TYPE);
		Driver driver = drivers.addCarToDriver(0L, car);
		assertNotNull("doch nicht gespeichert", driver);
		assertEquals("id ungleich", new Long(0), driver.getId());
		assertEquals("firstname ungleich", "Martin", driver.getFirstname());
		assertEquals("name ungleich", "Rahn", driver.getName());
		assertFalse("anzahl cars ungleich", driver.getCars().isEmpty());
		car = driver.getCars().iterator().next();
		assertEquals("id ungleich", CAR_ID, car.getId());
		assertEquals("type ungleich", CAR_TYPE, car.getType());
	}

}