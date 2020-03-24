package Domain;

import java.util.Objects;

public class Doctor {
    private String firstName;
    private String lastName;
    private double age;
    private int identificationNumber;
    public Doctor(){}
    public Doctor(String firstName, String lastName, double age, int identificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.identificationNumber = identificationNumber;
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

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Override
    public String toString() {
        return "firstName=" + firstName +", lastName=" + lastName +", age=" + age + ", identificationNumber=" + identificationNumber ;
    }
    public String simpleToString(){
        return firstName +"-"+ lastName +"-"+ age + "-" + identificationNumber ;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return age == doctor.age &&
                identificationNumber == doctor.identificationNumber &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName);
    }
}

