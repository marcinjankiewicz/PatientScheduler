package pl.jankiewicz.patientscheduler.PatientScheduler.app.security.utils;


import pl.jankiewicz.patientscheduler.PatientScheduler.ui.utils.SchedulerConstants;

public class SecurityConstants {
    public static final String LOGIN_PROCESSING_URL = "/login";
    public static final String LOGIN_FAILURE_URL = "/login?error";
    public static final String LOGIN_URL = "/oauth_login";
    public static final String LOGIN_SUCCESS_URL = "/" + SchedulerConstants.ROUTE_BOARD;
    public static final String LOGIN_SUCCESS = "/loginSuccess";
    public static final String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";
    public static final String OA2_AUTHORIZE_CLIENT = "oauth2/authorize-client";
}
