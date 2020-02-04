package pl.jankiewicz.patientscheduler.PatientScheduler.backend.features;

public enum AppointmentDuration {
    MINUTES_15(15*1000),
    MINUTES_30(30*1000),
    MINUTES_45(45*1000),
    MINUTES_60(60*1000),
    MINUTES_90(90*1000),
    MINUTES_120(120*1000);

    private int timeInMilis;

    AppointmentDuration(int timeInMilis) {
        this.timeInMilis = timeInMilis;
    }

    public int getTimeInMilis() {
        return timeInMilis;
    }
}
