package sample;

import Controll.MyController;
import Domain.Consultation;
import Domain.Doctor;
import Domain.Patient;
import Domain.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ControllerGUI {
    MyController ctrl;

    public ControllerGUI(MyController ctrl) {
        this.ctrl = ctrl;
    }

    ControllerGUI(){}
    public void setController(MyController ctrl){this.ctrl=ctrl;}

    @FXML
    private ListView<String> room1List;
    @FXML
    private ListView<String> room2List;
    @FXML
    private ListView<String> room3List;
    @FXML
    private ListView<String> room4List;
    @FXML
    private Label current1;
    @FXML
    private Label current2;
    @FXML
    private Label current3;
    @FXML
    private Label current4;
    @FXML
    private ListView<String> consultationList;
    @FXML
    private ListView<String> patientList;
    @FXML
    private ListView<String> doctorList;
    @FXML
    private TextField roomField;
    @FXML
    private ListView<String> summaryOfPatientsList;
    @FXML
    private ListView<String> sumarryOfDoctorsList;
    @FXML
    private Button addConsultationButton;
    @FXML
    private ListView<String> notConsultedList;


    @FXML
    public void populate(){

        ///////populate the doctor list
        ArrayList<String> doctors=new ArrayList<>();
        for(Doctor d : this.ctrl.getDoctors().getAll())
            doctors.add(d.simpleToString());
        ObservableList<String> observableList = FXCollections.observableArrayList(doctors);
        this.doctorList.setItems(observableList);

        ///////populate the patient list
        ArrayList<String> patients=new ArrayList<>();
        for(Patient p : this.ctrl.getPatients().getAll())
            patients.add(p.simpleToString());
        ObservableList<String> observableList2 = FXCollections.observableArrayList(patients);
        this.patientList.setItems(observableList2);

        ///////populate the summary of patients by age list
        ArrayList<String> strings=this.ctrl.printSummaryByAge();
        ObservableList<String> observableList3 = FXCollections.observableArrayList(strings);
        this.summaryOfPatientsList.setItems(observableList3);

        ///////populate the summary for Doctors
        ArrayList<String> doctorsString=this.ctrl.printSummaryForDoctors();
        ObservableList<String> observableList4 = FXCollections.observableArrayList(doctorsString);
        this.sumarryOfDoctorsList.setItems(observableList4);

        ///////populate the summary for not consulted pacients
        ArrayList<String> patientsString=this.ctrl.printNotConsultedPacients();
        ObservableList<String> observableList5 = FXCollections.observableArrayList(patientsString);
        this.notConsultedList.setItems(observableList5);

        populateConsultation();
    }
    @FXML
    //populate de consultations list for each room
    public void populateConsultation(){
        //populate the list for room1
        ArrayList<String> room1=new ArrayList<>();
        for(Consultation c : this.ctrl.getConsultations().getAll())
            if(c.getRoom().getName().equals("room1"))
                room1.add(c.getDoctor().simpleToString()+" | "+c.getPatient().simpleToString());
        ObservableList<String> observableList1 = FXCollections.observableArrayList(room1);
        this.room1List.setItems(observableList1);

        //populate the list for room2
        ArrayList<String> room2=new ArrayList<>();
        for(Consultation c : this.ctrl.getConsultations().getAll())
            if(c.getRoom().getName().equals("room2"))
                room2.add(c.getDoctor().simpleToString()+" | "+c.getPatient().simpleToString());
        ObservableList<String> observableList2 = FXCollections.observableArrayList(room2);
        this.room2List.setItems(observableList2);

        //populate the list for room3
        ArrayList<String> room3=new ArrayList<>();
        for(Consultation c : this.ctrl.getConsultations().getAll())
            if(c.getRoom().getName().equals("room3"))
                room3.add(c.getDoctor().simpleToString()+" | "+c.getPatient().simpleToString());
        ObservableList<String> observableList3 = FXCollections.observableArrayList(room3);
        this.room3List.setItems(observableList3);

        //populate the list for room4
        ArrayList<String> room4=new ArrayList<>();
        for(Consultation c : this.ctrl.getConsultations().getAll())
            if(c.getRoom().getName().equals("room4"))
                room4.add(c.getDoctor().simpleToString()+" | "+c.getPatient().simpleToString());
        ObservableList<String> observableList4 = FXCollections.observableArrayList(room4);
        this.room4List.setItems(observableList4);

    }
    @FXML
    public void addConsultationFunction(ActionEvent event){
        String doctor= this.doctorList.getSelectionModel().getSelectedItem(); //gets the selected doctor from the list into a string
        String pacient=this.patientList.getSelectionModel().getSelectedItem();//gets the selected patient from the list into a string

        String[] tokenizedDoctor=doctor.split("-"); //splits the string intro a list of strings
        String[] tokenizedPacient=pacient.split("-");

        int identificationNumber=Integer.parseInt(tokenizedDoctor[3]);//the 4th element from the list of strings is the identification number
        Doctor d=this.ctrl.getDoctorByIdNumber(identificationNumber); //finds the doctor with that id

        String firstname=tokenizedPacient[0];
        String lastname=tokenizedPacient[1];

        Patient p=this.ctrl.getPatientByName(firstname,lastname);//finds the pacient with this firat & last name

        String roomname=this.roomField.getText();
        Room r=this.ctrl.findRoomByName(roomname);//finds the room with the input name

        if(this.ctrl.findRoomByName(roomname).equals(new Room())) //if the room does not exist
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid Room");
            errorAlert.setContentText("That room does not exist");
            errorAlert.showAndWait();
        }
        else{
                try{
                    this.ctrl.addConsultation(new Consultation(d,p,r));
                    this.populate(); //refresh all the lists
                    this.populateConsultation();
                    if(r.getName()=="room1")
                        current1.setText(String.valueOf(r.getNumberOfMinutes()+p.getMinutesOfConsultation())); //sets the current number of minutes used in this room
                    if(r.getName()=="room2")
                        current2.setText(String.valueOf(r.getNumberOfMinutes()+p.getMinutesOfConsultation()));
                    if(r.getName()=="room3")
                        current3.setText(String.valueOf(r.getNumberOfMinutes()+p.getMinutesOfConsultation()));
                    if(r.getName()=="room4")
                        current4.setText(String.valueOf(r.getNumberOfMinutes()+p.getMinutesOfConsultation()));
                }
                catch (RuntimeException e)
                {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Invalid Consultation");
                    errorAlert.setContentText(e.toString());
                    errorAlert.showAndWait();
                }
        }
    }
}
