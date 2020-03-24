package Controll;

import Domain.Consultation;
import Domain.Doctor;
import Domain.Patient;
import Domain.Room;
import Infrastructure.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyController {
    Repository<Doctor> doctors=new Repository<>();
    Repository<Patient>patients=new Repository<>();
    Repository<Room>rooms=new Repository<>();
    Repository<Consultation> consultations=new Repository<>();

    public MyController(Repository<Doctor> doctors, Repository<Patient> patients, Repository<Room> rooms, Repository<Consultation> consultations) throws IOException {
        this.doctors = doctors;
        this.patients = patients;
        this.rooms = rooms;
        this.consultations = consultations;
        this.generateDoctors();
        this.generatePatients();
        this.doctors.writeToFile("C:\\Users\\Diana\\IdeaProjects\\Doctor'sClinicJavaFX\\src\\doctors.txt");
        this.patients.writeToFile("C:\\Users\\Diana\\IdeaProjects\\Doctor'sClinicJavaFX\\src\\patients.txt");
    }

    public MyController() throws IOException {

    }

    public Repository<Doctor> getDoctors() {
        return doctors;
    }

    public Repository<Patient> getPatients() {
        return patients;
    }

    public Repository<Room> getRooms() {
        return rooms;
    }

    public Repository<Consultation> getConsultations() {
        return consultations;
    }

// add Operations:

    public void addDoctor(Doctor d) {
        this.doctors.addItem(d);
    }
    public void addPatient(Patient p) {
        this.patients.addItem(p);
    }
    public void addRoom(Room r){
        this.rooms.addItem(r);
    }
    public void addConsultation(Consultation c){
        Room room=c.getRoom();
        Patient patient=c.getPatient();
        Doctor doctor=c.getDoctor();

        if(room.getNumberOfMinutes()+patient.getMinutesOfConsultation()<=720) //there are 12h*60min=720min in the work day
        {
            if(this.findConsultionByPatientAndDoctor(c.getPatient(),c.getDoctor(),c.getRoom())==false) //if the same consultation is not programmed in another room
            {
                this.consultations.addItem(c); //then it is added
                int currentNoOfMinutes=room.getNumberOfMinutes();
                int index=this.rooms.findItem(room);
                this.rooms.updateItemAtIndex(new Room(room.getName(),currentNoOfMinutes+patient.getMinutesOfConsultation()),index); //updates the number of minutes from the used room
            }
            else
                throw new RuntimeException("Error! The consultation is already programmed for another room! ");

        }
        else  throw new RuntimeException("Error! The room is too busy. Select another one! ");

        //this.consultations.addItem(c);//the function verifies if the consultation already exists and returns exception if it does
    }
// remove Operations
    public void removeDoctor(Doctor d) {
        this.doctors.removeItem(d);
    }
    public void removePatient(Patient p) {
        this.patients.removeItem(p);
    }
    public void removeRoom(Room r){
        this.rooms.removeItem(r);
    }
    public void removeConsultation(Consultation c){
        this.consultations.removeItem(c);
    }

    /*
        Finds a doctor by Identification Number
        input: id - int
        output: true - boolean (if it exists)
                false - boolean (if it does not exist)
     */
    public boolean findDoctorByIdNumber(int id)
    {
        for(Doctor d:this.doctors.getAll())
            if(d.getIdentificationNumber()==id)
                return true;
        return false;
    }
    /*
        Finds a doctor by Identification Number
        input: id - int
        output: d - Doctor (if it exists)
                an empty object - Doctor (if it does not exist)
     */
    public Doctor getDoctorByIdNumber(int id)
    {
        for(Doctor d:this.doctors.getAll())
            if(d.getIdentificationNumber()==id)
                return d;
        return new Doctor();
    }
    /*
        Finds a patient by First Name and Last Name
        input: firstName - String, lastName - String
        output: p - Patient (if it exists)
                an empty object - Patient (if it does not exist)
     */
    public Patient getPatientByName(String firstName,String lastName)
    {
        for(Patient p:this.patients.getAll())
            if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                return p;
        return new Patient();
    }
    /*
        Finds if a consultation includes the same Doctor and Patient, but another Room
        input: patient - Patient, doctor - Doctor, room - Room
        output: true - boolean (if it exists)
                false - boolean (if it does not exist)
     */
    public boolean findConsultionByPatientAndDoctor(Patient patient,Doctor doctor,Room room){
        for(Consultation consultation:this.consultations.getAll())
            if(consultation.getDoctor().equals(doctor) && consultation.getPatient().equals(patient) && consultation.getRoom().equals(room)==false) //if there is a the same consultaion but in another room
                return true;
            return false;
    }

        /*
            Generates a random String of size length
            input: length - int
            output: the generated string - String
        */
    static String randomName(int length) {
        // chose a Character random from this String
        String lowString = "abcdefghijklmnopqrstuvxyz";
        String capitalString="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create StringBuffer size of AlphaString
        StringBuilder sb = new StringBuilder(length);

        //generate the first letter as a capital letter
        int index = (int) (capitalString.length() * Math.random());
        sb.append(capitalString.charAt(index));

        //generate the rest of the letters
        for (int i = 0; i < length-1; i++) {
            // generate a random number between 0 to length
            index = (int) (lowString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(lowString.charAt(index));
        }
        return sb.toString();
    }
        /*
            Generates a random number between minAge and maxAge (minAge and maxAge included)
            input: minAge - int, maxAge - int
            output: the generated number - int
        */
    static double randomNumber(double start, double end) {
        double result = start + (Math.random() * (end - start)); //generates a double
        return Math.round(result * 10) / 10.0; //rounds the double number to a double with 1 decimal
    }
        /*
            Generates a random reason
            input: -
            output: a random string from the list
         */
    static String randomReason()
    {
        String[] arr={"consultation", "treatment", "prescription"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        return arr[randomNumber];
    }
    /*
    Generate a list of doctors, respecting the following requirements:
-	The list must contain at least 8 doctors.
-	Each doctor will be generated following the rules:
o	First Name:  3 characters;
o	Last Name: 2 characters.
o	Age: between 30 and 65.
o	Identification number: 4 numbers, must be unique in the list

     */
    public void generateDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String firstName = randomName(3); //first name has 3 characters
            String lastName = randomName(2); //last name has 2 characters
            double age = randomNumber(31, 64); //generates a random number between 30 and 65
            int identificationNumber = (int) randomNumber(1000, 9999); //generates a number of 4 digits
            boolean ok = true;
            while (ok == true) {
                if (this.findDoctorByIdNumber(identificationNumber) == false) //if the id does not exists
                    ok = false; //to exit the loop
                else //the id exists, another one should be generated
                    identificationNumber = (int) randomNumber(1000, 9999); //generates a number of 4 digits
            }
            Doctor doctor = new Doctor(firstName, lastName, age, identificationNumber);
            if (this.getDoctors().findItem(doctor) == -1)//if the doctor does not exist
                this.addDoctor(doctor);
            else
                i--; //we generate again for this position
        }
    }

    /*
    Generate a list of patients, respecting the following requirements:
-	The list must contain 100 patients.
-	Each patient will be generated following the rules:
o	First Name: 5 characters;
o	Last Name: 4 characters.
o	Age: between 0 and 85.
o	Reason: consultation, treatment or prescriptions.
-	The list must contain at least one patient from each age category and at least one patient for each consultation reason.
     */
    public void generatePatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        //first we generated the mandatory patients:
        //at least one pacient from each age category:
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(0, 1), randomReason()));//add 1 child
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(1, 7), randomReason()));//add 1 pupil
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(7, 18), randomReason()));//add 1 student
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(18, 85), randomReason()));//add 1 adult

        //at least one pacient from each consultation reason:
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(0, 85), "consultation"));
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(0, 85), "prescription"));
        this.addPatient(new Patient(randomName(5), randomName(4), randomNumber(0, 85), "treatment"));

        for (int i = 8; i <= 100; i++) //we generated 7, we continue until there are 100 patients
        {
            String firstName = randomName(5); //first name has 5 characters
            String lastName = randomName(4); //last name has 4 characters
            double age = randomNumber(0, 85); //generates a random number from 0 to 85
            String reason = randomReason(); //generates a reason

            Patient patient = new Patient(firstName, lastName, age, reason);
            if (this.getPatients().findItem(patient) != -1) //if the patient already exists, we generate another one so there are no duplicates
                i--; //we generate again for this position
            else
                this.addPatient(patient);
        }
    }
    /*
        Gets  the summary of all patients based on their age group
        input: -
        output: strings - ArrayList<String>
    */
    public ArrayList<String> printSummaryByAge(){
        ArrayList<Patient> children=new ArrayList<>();
        ArrayList<Patient> pupil=new ArrayList<>();
        ArrayList<Patient> student=new ArrayList<>();
        ArrayList<Patient> adult=new ArrayList<>();
        for(Patient patient:this.getPatients().getAll())
        {
            if(patient.getAge()>=0 && patient.getAge()<1)
                children.add(patient);
            else
            if(patient.getAge()>=1 && patient.getAge()<7)
                pupil.add(patient);
            else
            if(patient.getAge()>=7 && patient.getAge()<18)
                student.add(patient);
            else
                adult.add(patient);
        }
        ArrayList<String> strings=new ArrayList<>();
        strings.add("Children (0-1): "+children.size()+" patients");
        strings.add("Pupil (1-7): "+pupil.size()+" patients");
        strings.add("Student (7-18): "+student.size()+" patients");
        strings.add("Adults (>18): "+adult.size()+" patients");
        return strings;
    }
    /*
        Returns the total number of pacients consulted by a given doctor
        input: doctor - Doctor
        output: numberOfPacients - int
     */
    public int getPacientsPerDoctor(Doctor doctor){
        int numberOfPacients=0;
        for(Consultation c:this.consultations.getAll())
            if(c.getDoctor()==doctor)
                numberOfPacients=+1;
        return numberOfPacients;
    }
    /*
        Returns the total price of consultation of a given doctor
        input: doctor - Doctor
        output: price - int
     */
    public int getPricePerDoctor(Doctor doctor){
        int price=0;
        for(Consultation c:this.consultations.getAll())
            if(c.getDoctor()==doctor)
                price=price+c.getPatient().getConsultationFee();
        return price;
    }
    /*
        Returns the total minutes of consultation of a given doctor
        input: doctor - Doctor
        output: minutes - int
     */
    public int getTotalMinutesPerDoctor(Doctor doctor){
        int minutes=0;
        for(Consultation c:this.consultations.getAll())
            if(c.getDoctor()==doctor)
                minutes=minutes+c.getPatient().getMinutesOfConsultation();
        return minutes;
    }
    /*
        Gets  the summary of the doctors, the number of patients consulted and the total amount billed.
        input: -
        output: strings - ArrayList<String>
     */
    public ArrayList<String> printSummaryForDoctors(){
        ArrayList<String> strings=new ArrayList<>();
        for(Doctor doctor:this.getDoctors().getAll()) {
            int noOfPacients=this.getPacientsPerDoctor(doctor);
            int price=this.getPricePerDoctor(doctor);
            int minutes=this.getTotalMinutesPerDoctor(doctor);
            strings.add(doctor.getFirstName() + ", " + doctor.getLastName() + "-" + doctor.getIdentificationNumber() + ": " +noOfPacients+" pacients, "+minutes+" minutes, "+price+" RON");
        }
        return strings;
    }
    /*
        Finds a pacient in the consultation List
        input: pacient - Pacient
        output: true - boolean (if it exists)
                false - boolean (if it does not exist)
     */
    boolean findPacientInConsultations(Patient pacient){
        for(Consultation c:this.consultations.getAll())
            if(c.getPatient()==pacient)
                return true;
        return false;
    }
    /*
        Gets all the pacients that are not consulted
        input: -
        output: strings - ArrayList<String>
     */
    public ArrayList<String> printNotConsultedPacients(){
        ArrayList<String> strings=new ArrayList<>();
        for(Patient patient:this.patients.getAll())
            if(findPacientInConsultations(patient)==false) //if the pacient is not found in any consultation
                strings.add(patient.simpleToString());
        return strings;
    }
/*
    Finds the room with the given name in the List of Rooms
    input: name - String
    output: r - Room (if it exists)
            empty object - Room (if it does not exist)
 */
    public Room findRoomByName(String name){
        for(Room r:this.rooms.getAll())
            if(r.getName().equals(name))
                return r;
        return new Room();
    }
}
