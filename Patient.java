package Domain;

import java.util.Objects;

public class Patient {
    private String firstName;
    private String lastName;
    private double age;
    private String reason;
    public Patient(){}
    public Patient(String firstName, String lastName, double age, String reason) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.reason = reason;
    }
////getter and setter:

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getMinutesOfConsultation(){
        if(this.reason.equals("consultation"))
            return 30;
        else
        if(this.reason.equals("prescription"))
            return 20;
        else if(this.reason.equals("treatment"))
            return 40;
        else return 0;
    }
    public int getConsultationFee(){
        if(this.reason.equals("consultation"))
            return 50;
        if(this.reason.equals("prescription"))
            return 20;
        if(this.reason.equals("treatment"))
            return 35;
        return 0;
    }
    @Override
    public String toString() {
        return "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", reason=" + reason ;
    }
    public String simpleToString(){
        return firstName + "-" + lastName + "-" + age + "-" + reason ;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(reason, patient.reason);
    }

}
