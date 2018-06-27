package ua.nure.dlubovskyi.Clinic.web.commands;

import java.util.HashMap;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class CommandManager {

	private static HashMap<String, AbstractCommand> commands = new HashMap<>();

	// init commands
	static {
		commands.put("login", new LoginCommand()); // TODO add filters
		commands.put("doctors", new DoctorListCommand());
		commands.put("nurses", new NurseseListCommand());
		commands.put("patients", new PatientsListCommand());
		commands.put("addStaff", new AddStaffCommand());
		commands.put("addPatient",new AddPatientCommand());
		commands.put("patientCard", new GetPatienMedicalCardCommand());
		commands.put("doctor", new GetDoctorInfoCommand());
		commands.put("changeLanguage", new ChangeLanguageCommand());
		commands.put("doctorsPatients", new GetPatientsByDoctor());
		commands.put("setProcedure", new SetProcedureToPatient());
		commands.put("setDiagnosis", new SetDiagnosisToPatientCommand());
		commands.put("nursePatients", new GetPatientsListForNurseCommand());
		commands.put("carryOutProcedure", new CarryOutTheProcedure());
		commands.put("discharge", new DischargePatientCommand());
		commands.put("doctorsProcedures", new ProceduresForDoctor());
	}

	public AbstractCommand getCommand(String command) {
		return commands.get(command);

	}

}
