package pl.jankiewicz.patientscheduler.PatientScheduler.backend.features;

public class Role {
    public static final String ADMIN = "ADMIN";
    public static final String PATIENT = "PATIENT";
    public static final String ANONYMOUS = "ANONYMOUS";


    private Role(){}
    public static String[] getAllRoles(){
        return new String[]{ADMIN, PATIENT, ANONYMOUS};
    }
}
