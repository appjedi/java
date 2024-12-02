<<<<<<< HEAD
<<<<<<< HEAD
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Profile extends Application {
    dao.Database db;
    models.User user;
    models.Doctor doctor;
    MainMenu mm;

    public static void main(String[] args) {
        launch(args);
    }

    public Profile() {
    }

    public Profile(MainMenu mm, models.User user) {
        this.mm = mm;
        this.user = user;
    }
    
    public Profile(MainMenu mm, models.Doctor doc) {
        this.mm = mm;
        this.doctor = doc;
    }

    public void setProfile(models.User user) {
        this.user = user;
    }

    private Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
    	System.out.println ("Profile start");
        db = new dao.Database();
        primaryStage.setTitle("Steri Homework 2023.");

        Button btn1 = createButton("Update Patient");
        Button btn2 = createButton("Delete Patient");
        Button btn3 = createButton("Logout");

        // Two text fields (use the JavaFX class TextField).
        TextField email = new TextField();
        TextField password = new TextField();
        TextField lastName = new TextField();
        TextField firstName = new TextField();
        TextField emergencyPhone = new TextField();
        TextField emergencyContact = new TextField();
        TextField doctorsNotes = new TextField();
        /*
        email.setText(patient.getEmail());
        lastName.setText(patient.getLastName());
        firstName.setText(patient.getFirstName());
        emergencyPhone.setText(patient.getEmergencyPhone());
        emergencyContact.setText(patient.getEmergencyContact());
        doctorsNotes.setText(patient.getDoctorsNotes());
*/
        email.setPromptText("Email");
        password.setPromptText("password");
        lastName.setPromptText("Last Name"); // Enter prompt for text one
        firstName.setPromptText("First Name"); // Enter prompt for text one
        emergencyPhone.setPromptText("emergencyPhone"); // Enter prompt for text one
        emergencyContact.setPromptText("emergencyContact"); // Enter prompt for text one
        doctorsNotes.setPromptText("doctorsNotes"); // Enter prompt for text one

        BorderPane root = new BorderPane(); // Utilizing a BorderPane
        VBox boxInput = new VBox();
        boxInput.getChildren().add(email);
        boxInput.getChildren().add(password);
        boxInput.getChildren().add(lastName);
        boxInput.getChildren().add(firstName);
        boxInput.getChildren().add(emergencyPhone);
        boxInput.getChildren().add(emergencyContact);
        boxInput.getChildren().add(doctorsNotes);
        boxInput.getChildren().add(btn1);
        boxInput.getChildren().add(btn2);
        boxInput.getChildren().add(btn3);
        root.setCenter(boxInput); // Set one text field to center.

        // Create and attach an EventHandler to "button 1" utilizing an anonymous class.
        // When clicked
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ln = lastName.getText(); // Get text from the text field in the center
                String fn = firstName.getText(); // Get text from the text field in the center

                if (db.updateUser(user.getId(), email.getText(), password.getText())>0) {
                    System.out.println("patient added");
                    mm.childCallback();
                } else {
                    System.out.println("error adding patient");
                }
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (db.deleteUser(user.getId())) {
                    System.out.println("patient deleted");
                    mm.childCallback();
                } else {
                    System.out.println("error deleting patient");
                }
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mm.logout();
            }
        });
        Scene scene = new Scene(root);

        primaryStage.setMinWidth(550); // Set min width for window.
        primaryStage.setMinHeight(400); // Set max width for window.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);

        button.setMinWidth(150);
        BorderPane.setMargin(button, INSETS);
        BorderPane.setAlignment(button, Pos.CENTER);
        return button;
    }
}
=======
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Profile extends Application {
    dao.Database db;
    models.User user;
    models.Doctor doctor;
    MainMenu mm;

    public static void main(String[] args) {
        launch(args);
    }

    public Profile() {
    }

    public Profile(MainMenu mm, models.User user) {
        this.mm = mm;
        this.user = user;
    }
    
    public Profile(MainMenu mm, models.Doctor doc) {
        this.mm = mm;
        this.doctor = doc;
    }

    public void setProfile(models.User user) {
        this.user = user;
    }

    private Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
    	System.out.println ("Profile start");
        db = new dao.Database();
        primaryStage.setTitle("Steri Homework 2023.");

        Button btn1 = createButton("Update Patient");
        Button btn2 = createButton("Delete Patient");
        Button btn3 = createButton("Logout");

        // Two text fields (use the JavaFX class TextField).
        TextField email = new TextField();
        TextField password = new TextField();
        TextField lastName = new TextField();
        TextField firstName = new TextField();
        TextField emergencyPhone = new TextField();
        TextField emergencyContact = new TextField();
        TextField doctorsNotes = new TextField();
        /*
        email.setText(patient.getEmail());
        lastName.setText(patient.getLastName());
        firstName.setText(patient.getFirstName());
        emergencyPhone.setText(patient.getEmergencyPhone());
        emergencyContact.setText(patient.getEmergencyContact());
        doctorsNotes.setText(patient.getDoctorsNotes());
*/
        email.setPromptText("Email");
        password.setPromptText("password");
        lastName.setPromptText("Last Name"); // Enter prompt for text one
        firstName.setPromptText("First Name"); // Enter prompt for text one
        emergencyPhone.setPromptText("emergencyPhone"); // Enter prompt for text one
        emergencyContact.setPromptText("emergencyContact"); // Enter prompt for text one
        doctorsNotes.setPromptText("doctorsNotes"); // Enter prompt for text one

        BorderPane root = new BorderPane(); // Utilizing a BorderPane
        VBox boxInput = new VBox();
        boxInput.getChildren().add(email);
        boxInput.getChildren().add(password);
        boxInput.getChildren().add(lastName);
        boxInput.getChildren().add(firstName);
        boxInput.getChildren().add(emergencyPhone);
        boxInput.getChildren().add(emergencyContact);
        boxInput.getChildren().add(doctorsNotes);
        boxInput.getChildren().add(btn1);
        boxInput.getChildren().add(btn2);
        boxInput.getChildren().add(btn3);
        root.setCenter(boxInput); // Set one text field to center.

        // Create and attach an EventHandler to "button 1" utilizing an anonymous class.
        // When clicked
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ln = lastName.getText(); // Get text from the text field in the center
                String fn = firstName.getText(); // Get text from the text field in the center

                if (db.updateUser(user.getId(), email.getText(), password.getText())>0) {
                    System.out.println("patient added");
                    mm.childCallback();
                } else {
                    System.out.println("error adding patient");
                }
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (db.deleteUser(user.getId())) {
                    System.out.println("patient deleted");
                    mm.childCallback();
                } else {
                    System.out.println("error deleting patient");
                }
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mm.logout();
            }
        });
        Scene scene = new Scene(root);

        primaryStage.setMinWidth(550); // Set min width for window.
        primaryStage.setMinHeight(400); // Set max width for window.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);

        button.setMinWidth(150);
        BorderPane.setMargin(button, INSETS);
        BorderPane.setAlignment(button, Pos.CENTER);
        return button;
    }
}
<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
