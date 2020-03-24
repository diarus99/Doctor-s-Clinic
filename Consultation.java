package Domain;

import java.util.Date;
import java.util.Objects;

public class Consultation {
    private Doctor doctor;
    private Patient patient;
    private Room room;
    private String time;///the time the consultation takes place //separated by ":", e.g 13:30

    public Consultation(Doctor doctor, Patient patient, Room room) {
        this.doctor = doctor;
        this.patient = patient;
        this.room = room;
    }

    public Consultation(Doctor doctor, Patient patient, Room room, String time) {
        this.doctor = doctor;
        this.patient = patient;
        this.room = room;
        this.time = time;
    }
////getter and setter:
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return Objects.equals(doctor, that.doctor) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(room, that.room) &&
                Objects.equals(time, that.time);
    }

}
