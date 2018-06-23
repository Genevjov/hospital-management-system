package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.dao.staff.Specialization;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

/**
 * Doctors list command realization
 * 
 * @author Dlubovskyi Oleg
 */
public class DoctorListCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(DoctorListCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing DoctorList command");
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request, response);
		}
		LOGGER.debug("DoctorList command has been executed");
		return path;
	}

	/**
	 * Method for getting doctors and specification lists using DoctorManaget
	 * 
	 * @see DoctorManager
	 * @see Doctor
	 * @see Specialization
	 * @param request
	 * @param response
	 * @return path to redirect
	 */
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		// getting spec id if present
		String stringSpecId = request.getParameter("spec");
		List<Doctor> doctors = null;
		int specId = 0;
		// validating
		if (!Objects.isNull(stringSpecId) && !stringSpecId.isEmpty()) {
			// if true getting doctors by spec
			specId = Integer.parseInt(stringSpecId);
			doctors = DoctorManager.getDoctorsBySpecId(specId);
			// else all doctors
		} else {
			doctors = DoctorManager.getAllDoctors();

		}
		// getting doctors and specificationsF
		List<Specialization> specializations = DoctorManager.getAllSpecification();
		// setting as attrib
		request.setAttribute("doctors", doctors);
		request.setAttribute("specializations", specializations);
		String sortOption = request.getParameter("sort");
		// sorting if required
		if (!Objects.isNull(sortOption) && !sortOption.isEmpty()) {
			sorter(doctors, sortOption);
		}
		// setting as attrib
		request.setAttribute("doctors", doctors);
		request.setAttribute("specializations", specializations);
		return Urls.PAGE_LIST_DOCTORS;
	}

	private void sorter(List<Doctor> doctors, String sortOption) {
		switch (sortOption) {
		case "firstName":
			doctors.sort(new Comparator<Doctor>() {
				public int compare(Doctor o1, Doctor o2) {
					return o1.getFirstName().compareTo(o2.getFirstName());
				};
			});
			break;
		case "secondName":
			doctors.sort(new Comparator<Doctor>() {
				public int compare(Doctor o1, Doctor o2) {
					return o1.getSecondName().compareTo(o2.getSecondName());
				};
			});
			break;
		case "login":
			doctors.sort(new Comparator<Doctor>() {
				public int compare(Doctor o1, Doctor o2) {
					return o1.getLogin().compareTo(o2.getLogin());
				};
			});
			break;
		case "specialization":
			doctors.sort(new Comparator<Doctor>() {
				public int compare(Doctor o1, Doctor o2) {
					return o1.getSpecialization().getName().compareTo(o2.getSpecialization().getName());
				};
			});
			break;
		}
	}
}
