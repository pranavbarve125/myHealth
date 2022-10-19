package myhealth.myhealth;

import java.time.LocalDateTime;

public class Record {
    private int weight, temperature, bloodPressure;
    private String note;
    private LocalDateTime timestamp;

    public Record(int weight, int temperature, int bloodPressure, String note, LocalDateTime timestamp) {
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getWeight() {
        return weight;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
