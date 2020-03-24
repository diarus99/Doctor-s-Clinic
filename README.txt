This is a JavaFX 11 project implemented on Java SDK 11 in IntelliJ IDEA, and the GUI was made using Scene Builder

in the packaje Domain there are the classes used in order to solve the given problem.
-The class Doctor
-The class Patient
-The class Room : each room has the currentNumberOfMinutes that represents the minutes spents in that room (from the total of 720 in a day)
-The class Consultation : this class contains an object of each class presented above and it is used to make an association between a doctor, pacient and a room

in the packaje Infrastructure is the class Repository, a generic class that contains a list of objects on which the CRUD operations are implemented. 
The class Controller from the packaje Controll contains multiple entities of the class Repository, but particulized on the classes from the Domain. So there is a Repository<Room>, Repository<Doctor>, Repository<Patient>, Repository<Consultation>

By executing the program a window shows up(Presented in the start.PNG file).
On the left side of the window there are 4 Lists that contain all the consultations made until now (0 at the beginning), each list reffering to a different room. At the bottom of each list there are 2 labels. One for the total amount 
of minutes from a day, and another one that contains the currentNumberOFMinutes from that Room

On the right side there are 5 lists. The 2 from top contain all the patients(left) and all the doctors(right) from the clinic. The list from the middle has the summary of pacients based on age groups, and the 2 remaining lists also 
include a summary of doctors(left) and all the pacients that are not consulted(right)

This GUI Application offers a simple way to add new consultations.
On he screen there is a Text Line and a Button that reffer to this implementation. On the text Line a Room name is expected ("room1","room2","room3","room4" are valid).Any other name is not considered, so an error will occur
(a pop-up window)
If the added consultation already exists(The same doctor,pacient and room) or a similar one exists(Same doctor and pacient, different room) an error occurs with a message.

All the doctors and the patients are generated automatically by the programm and are automatically saved in the files ("patients.txt","doctors.txt").If any error occurs during this implementation, please change the path of the files in the files: Main.java, line 24,25,51,52 and MyController.Java line 26,27
At every added consultation, all the other lists are automatically change (The 2 summary's of doctors and unconsulted pacients).
If more that 720 minutes of a room are used, an error pops up with a message.

A pacient can be consulted by multiple doctors!

After a few consultations, the window can look like : end.PNG