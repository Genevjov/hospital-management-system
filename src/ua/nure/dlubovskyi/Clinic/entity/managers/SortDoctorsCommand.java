package ua.nure.dlubovskyi.Clinic.entity.managers;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.dao.staff.NurseDao;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;
import ua.nure.dlubovskyi.Clinic.web.commands.AbstractCommand;

public class SortDoctorsCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(SortDoctorsCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request, response);
		}
		return path;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * getting list from current page like (Pediatrican, Surgeon.. or all doctors
		 * page) for sorting
		 * 
		 */
		@SuppressWarnings("unchecked")
		List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
		if (!Objects.isNull(doctors)) {
			LOGGER.debug("Got " + doctors.size() + " from sesion");
			String sortingOption = request.getParameter("sort");
			if (!Objects.isNull(sortingOption)) {

				switch (sortingOption) {
				case "firstName":
					request.setAttribute("doctors", doSortByName(doctors));
					break;
				}
			}
		}
		return Urls.PAGE_LIST_DOCTORS;
	}

	private List<Doctor> doSortByName(List<Doctor> inDoctors) {
		List<Doctor> outDoctors = inDoctors;
		outDoctors.sort(new Comparator<Doctor>() {
			public int compare(Doctor o1, Doctor o2) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			};
		});
		return outDoctors;

	}

}
