package ua.nure.dlubovskyi.Clinic.web.commands;

import java.util.HashMap;

import ua.nure.dlubovskyi.Clinic.entity.managers.SortDoctorsCommand;
import ua.nure.dlubovskyi.Clinic.web.commands.AbstractCommand;

public class CommandManager {

	private static HashMap<String, AbstractCommand> commands = new HashMap<>();

	// init commands
	static {
		commands.put("login", new LoginCommand()); // TODO add filters
		commands.put("doctors", new DoctorListCommand());
		commands.put("doctorsBySpec", new DoctorsListBySpecializationCommand());
		commands.put("sortDoctors", new SortDoctorsCommand());
		commands.put("nurses", new NurseseListCommand()); // min version done
		commands.put("patients", new PatiensListCommand()); // TODO
		commands.put("addStaff", new AddStaffCommand());
		commands.put("addPatient", new AddPatientCommand());
		commands.put("logout", new LogOutCommand());
		commands.put("patientCard", new GetPatienMedicalCardCommand());

	}

	public AbstractCommand getCommand(String command) {
		return commands.get(command);

	}

}
