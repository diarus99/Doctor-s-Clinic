package sample;

import Controll.MyController;
import Domain.Consultation;
import Domain.Doctor;
import Domain.Patient;
import Domain.Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MyController ctrl=new MyController();
        ctrl.generatePatients();
        ctrl.generateDoctors();
        ctrl.getDoctors().writeToFile("C:\\Users\\Diana\\IdeaProjects\\Doctor'sClinicJavaFX\\src\\doctors.txt");
        ctrl.getPatients().writeToFile("C:\\Users\\Diana\\IdeaProjects\\Doctor'sClinicJavaFX\\src\\patients.txt");

        Room room1=new Room("room1",0);
        Room room2=new Room("room2",0);
        Room room3=new Room("room3",0);
        Room room4=new Room("room4",0);
        ctrl.addRoom(room1);
        ctrl.addRoom(room2);
        ctrl.addRoom(room3);
        ctrl.addRoom(room4);


        ControllerGUI guiCtrl = new ControllerGUI(ctrl);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        System.out.println(loader.getLocation());

        loader.setController(guiCtrl);
        Parent root = loader.load();

        guiCtrl.populate();

        primaryStage.setTitle("Doctor's Clinic");
        primaryStage.setScene(new Scene(root, 1004, 888));
        primaryStage.show();

        //after all the changes, save again in the files
        ctrl.getDoctors().writeToFile("C:\\Users\\Diana\\IdeaProjects\\Doctor'sClinicJavaFX\\src\\doctors.txt");
        ctrl.getPatients().writeToFile("C:\\Users\\Diana\\IdeaProjects\\Doctor'sClinicJavaFX\\src\\patients.txt");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
