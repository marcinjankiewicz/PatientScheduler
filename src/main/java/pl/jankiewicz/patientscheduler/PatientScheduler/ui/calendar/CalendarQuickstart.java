package pl.jankiewicz.patientscheduler.PatientScheduler.ui.calendar;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.service.UserService;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@Component
public class CalendarQuickstart {
    private static final String APPLICATION_NAME = "Patient Scheduler App";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/.patientScheduler/tokens";
    @Autowired
    private UserService userService;



    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/cred.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        File dataStoreDir = new File(TOKENS_DIRECTORY_PATH);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(dataStoreDir))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        AuthorizationCodeInstalledApp authorizationCodeInstalledApp = new AuthorizationCodeInstalledApp(flow, receiver);
        /*AuthorizationCodeRequestUrl url = authorizationCodeInstalledApp.getFlow().newAuthorizationUrl()
                .setRedirectUri(authorizationCodeInstalledApp.getReceiver().getRedirectUri());*/
//        System.out.println("URL to redirect: " + url.build());
        Credential cred = authorizationCodeInstalledApp.authorize("user");
        return cred;
    }

    public static Credential createCredentialWithAccessTokenOnly(HttpTransport transport, JsonFactory jsonFactory, TokenResponse tokenResponse) {
        return new Credential(BearerToken.authorizationHeaderAccessMethod()).setFromTokenResponse(tokenResponse);
    }

    public void calendarStart() throws IOException, GeneralSecurityException {
//        User user = userService.load(SecurityUtils.loggedUserId());
        // Build a new authorized API client service.
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);
        credential.refreshToken();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        Event event1 = new Event();
        event1.setSummary("Rehabilitacja");
        event1.setLocation("Gdańsk ul. Stolema 22");
        event1.setDescription("masaz");
        Event.Creator creator = new Event.Creator();
        creator.setDisplayName("MJn");
        event1.setCreator(creator);

        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        event1.setStart(new EventDateTime().setDateTime(new DateTime(dateTime.format(formatter))).setTimeZone("Europe/Warsaw"));
        event1.setEnd(new EventDateTime().setDateTime(new DateTime(dateTime.plusHours(1L).format(formatter))).setTimeZone("Europe/Warsaw"));

        // Ta zasada ustawia 2 spotkania dzień po dniu
        /**
         * https://developers.google.com/calendar/concepts/events-calendars
         */
        String[] recurrence = new String[]{"RRULE:FREQ=WEEKLY;COUNT=1"};
        event1.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[]{
                new EventAttendee().setEmail("marcin.jankiewicz@gmail.com"),
        };
        //event1.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event1.setReminders(reminders);

        String calendarId = "primary";
        /*service.events()
                .insert(user.getEmail(), event1).setSendNotifications(true)
                .execute();*/

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(calendarId)
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }
}
