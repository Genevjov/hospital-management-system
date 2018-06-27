package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;
import ua.nure.dlubovskyi.Clinic.entity.staff.Specialization;

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
		String sortOption = request.getParameter("sort");
		List<Doctor> doctors = null;
		int specId = 0;
		// validating
		if (!Objects.isNull(stringSpecId)) {
			// if true getting doctors by spec and sorting if param is present
			specId = Integer.parseInt(stringSpecId);
			if (specId > 0) {
				if (!Objects.isNull(sortOption)) {
					if (sortOption.equals("patientsUp") || sortOption.equals("patientsDown")) {
						doctors = DoctorManager.getDoctorsBySpecId(specId);
						sortByPatientsCount(doctors, sortOption);
					} else {
						doctors = DoctorManager.getDoctorsBySpcSorted(specId, sortOption);
					}
				} else {
					doctors = DoctorManager.getDoctorsBySpecId(specId);
				}
			} else {
				if (!Objects.isNull(sortOption)) {
					if (sortOption.equals("patientsUp") || sortOption.equals("patientsDown")) {
						doctors = DoctorManager.getAllDoctors();
						sortByPatientsCount(doctors, sortOption);

					} else {
						doctors = DoctorManager.getAllDoctorsSorted(sortOption);
					}
				} else {
					doctors = DoctorManager.getAllDoctors();
				}
			}
		} else {
			doctors = DoctorManager.getAllDoctors();
		}

		// getting doctors and specialization
		List<Specialization> specializations = DoctorManager.getAllSpecification();
		// setting as attrib
		request.getSession().setAttribute("specId", specId);

		request.setAttribute("doctors", doctors);
		request.setAttribute("specializations", specializations);
		// setting as attrib
		request.setAttribute("doctors", doctors);
		request.setAttribute("specializations", specializations);
		return Urls.PAGE_LIST_DOCTORS;
	}

	private void sortByPatientsCount(List<Doctor> doctors, String option) {
		if (option.equals("patientsUp")) {
			doctors.sort(new Comparator<Doctor>() {
				@Override
				public int compare(Doctor o1, Doctor o2) {
					return o2.getPatientCount() - o1.getPatientCount();
				}
			});

		} else {
			doctors.sort(new Comparator<Doctor>() {
				@Override
				public int compare(Doctor o1, Doctor o2) {
					return o1.getPatientCount() - o2.getPatientCount();
				}
			});
		}
	}

}