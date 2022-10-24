package Model;

import java.time.LocalDateTime;

public class Record {
    private int weight, temperature, bloodPressure, recordID;
    private String note;
    private LocalDateTime timestamp;

    public Record(int weight, int temperature, int bloodPressure, String note) {
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.note = note;
        this.timestamp = LocalDateTime.now();
    }

    public Record(int recordID, int weight, int temperature, int bloodPressure, String note) {
        this.recordID = recordID;
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.note = note;
        this.timestamp = LocalDateTime.now();
    }

    public int getWeight() {
        return this.weight;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getBloodPressure() {
        return this.bloodPressure;
    }

    public String getNote() {
        return note;
    }

    public int getRecordID(){
        return this.recordID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
