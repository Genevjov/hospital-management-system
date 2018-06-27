package ua.nure.dlubovskyi.Clinic.dao;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.dlubovskyi.Clinic.dao.staff.DoctorDao;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;

public class DoctorDaoTest {

	@Test
	public void contructorTest() {
		Assert.assertNotNull(new DoctorDao());
	}

	@Test
	public void getAllDoctorsTest() {
		Assert.assertNotNull(DoctorManager.getAllDoctors());
	}

	@Test
	public void getAllSpec() {
		Assert.assertNotNull(DoctorManager.getAllSpecification());
	}

}
