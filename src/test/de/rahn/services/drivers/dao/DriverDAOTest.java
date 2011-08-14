package de.rahn.services.drivers.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.rahn.services.drivers.entity.Driver;

/**
 * Die Testklasse für {@link DriverDAO}.
 * @author Frank W. Rahn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/context-test.xml",
	"../drivers.xml" })
@Transactional
public class DriverDAOTest {

	@Autowired
	private DriverDAO driverDAO;

	/**
	 * Test method for {@link DriverDAO#getPrimaryKey(Driver)}.
	 */
	@Test
	public void testGetPrimaryKey() {
		Driver driver = new Driver();
		driver.setId(new Long(4711));

		Long id = driverDAO.getPrimaryKey(driver);
		assertEquals("Primärer Key unterschiedlich", driver.getId(), id);
	}

	/**
	 * Test method for {@link DriverDAO#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Driver> drivers = driverDAO.findAll();
		assertEquals("Anzahl der Treffer ungleich", 1, drivers.size());

		Driver driver = drivers.get(0);
		assertEquals("id ungleich", new Long(0), driver.getId());
		assertEquals("firstname ungleich", "Martin", driver.getFirstname());
		assertEquals("name ungleich", "Rahn", driver.getName());
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#create(Object)}.
	 */
	@Test
	public void testCreate() {
		Driver driver = new Driver();
		driver.setName("Rahn");
		driver.setFirstname("Frank");

		Long id = driverDAO.create(driver);
		assertNotNull("keine id geliefert", id);
		assertEquals("ungleiche id", id, driver.getId());

		Driver driver2 = driverDAO.findByPrimaryKey(id);
		assertNotNull("doch nicht gespeichert", driver2);
		assertEquals("ungleicher Fahrer", driver, driver2);
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#save(Object)}.
	 */
	@Test
	public void testSave() {
		Driver driver = driverDAO.findByPrimaryKey(0L);
		assertNotNull("kein Fahrer gefunden", driver);

		driver.setFirstname("Peter");
		driverDAO.save(driver);

		Driver driver2 = driverDAO.findByPrimaryKey(0L);
		assertNotNull("kein Fahrer gefunden", driver2);
		assertEquals("ungleicher Fahrer", driver, driver2);
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#remove(Object)}.
	 */
	@Test
	public void testRemoveEntity() {
		List<Driver> drivers = driverDAO.findAll();
		assertEquals("Anzahl der Treffer ungleich", 1, drivers.size());
		Driver driver = drivers.get(0);

		driverDAO.remove(driver);

		drivers = driverDAO.findAll();
		assertEquals("Anzahl der Treffer ungleich", 0, drivers.size());
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#findByPrimaryKey(Serializable)}
	 * .
	 */
	@Test
	public void testFindByPrimaryKey() {
		Driver driver = driverDAO.findByPrimaryKey(0L);
		assertEquals("id ungleich", new Long(0), driver.getId());
		assertEquals("firstname ungleich", "Martin", driver.getFirstname());
		assertEquals("name ungleich", "Rahn", driver.getName());
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.dao.AbstractGenericDAO#remove(Serializable)}.
	 */
	@Test
	public void testRemovePrimaryKey() {
		driverDAO.remove(0L);

		List<Driver> drivers = driverDAO.findAll();
		assertEquals("Anzahl der Treffer ungleich", 0, drivers.size());
	}

}