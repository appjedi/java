<<<<<<< HEAD
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dao.*;
import models.*;
import javafx.scene.Node;

public class AddUser extends Application{
	Database db;
	boolean userAuth = false;
	
	private Insets INSETS = new Insets(10, 10, 10, 10);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		db = new Database();
		primaryStage.setTitle("Add User Menu");
        Button btn2 = createButton();
        Button backBtn = createStyledButton("Back", Color.GRAY);

        Text title = new Text("Add User's Credentials");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        TextField userName = createStyledTextField("Enter Username");
        TextField email = createStyledTextField("Enter Email");
        TextField password = createStyledTextField("Enter Password");
        TextField fullName = createStyledTextField("Enter Full name");
        TextField phone = createStyledTextField("Enter Phone");
        TextField specialty = createStyledTextField("Enter Specialty");
        TextField roleId = createStyledTextField("Enter RoleID");
		
		GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        root.add(title, 0, 0, 2, 1);

        int rowIndex = 1;
        root.add(createLabeledField("Username:"), 0, rowIndex);
        root.add(userName, 1, rowIndex++);

        root.add(createLabeledField("Email:"), 0, rowIndex);
        root.add(email, 1, rowIndex++);

        root.add(createLabeledField("Password:"), 0, rowIndex);
        root.add(password, 1, rowIndex++);

        root.add(createLabeledField("Full Name:"), 0, rowIndex);
        root.add(fullName, 1, rowIndex++);

        root.add(createLabeledField("Phone:"), 0, rowIndex);
        root.add(phone, 1, rowIndex++);

        root.add(createLabeledField("Specialty:"), 0, rowIndex);
        root.add(specialty, 1, rowIndex++);

        root.add(createLabeledField("RoleID:"), 0, rowIndex);
        root.add(roleId, 1, rowIndex++);

        for (Node node : root.getChildren()) {
            if (node instanceof Label || node instanceof TextField) {
                GridPane.setValignment(node, VPos.CENTER);
            }
        }

        root.add(btn2, 1, rowIndex++);
        root.add(backBtn, 1, rowIndex++);

        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(10);

//		root.getChildren().addAll(title,
//                createLabeledField("Username:", userName),
//                createLabeledField("Email:", email),
//                createLabeledField("Password:", password),
//                createLabeledField("Full Name:", fullName),
//                createLabeledField("Phone:", phone),
//                createLabeledField("Specialty:", specialty),
//                createLabeledField("RoleID:", roleId), btn2, backBtn);
//        root.setAlignment(Pos.CENTER);
//        root.setSpacing(15);

//        root.setCenter(boxInput);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
        });
        
        // Create an event handler for "Add User" button
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fn = fullName.getText(); // Get last name from input
                String pw = password.getText(); // Get first name from input
                String ph = phone.getText(); // Get first name from input
                String sp = specialty.getText(); // Get first name from input
                String un = userName.getText();
                int rid = Integer.parseInt(roleId.getText());
                
                String userEmail = email.getText(); // Get email from input

                //******************************************************
                // Validate email format
                if (!isValidEmail(userEmail)) {
                    showErrorAlert("Invalid Email", "Please enter a valid email address.");
                    return;
                }
                // Check if the email is already in use
                if (db.isEmailInUse(userEmail)) {
                    showErrorAlert("Email In Use", "This email is already associated with an existing account. Please use a different email.");
                    return;
                }
                // Check if any of the required fields (except Emergency Phone and Emergency Contact) are empty
                if (fn.isEmpty() || userEmail.isEmpty() || un.isEmpty()||password.getText().isEmpty()) {
                    showErrorAlert("Missing Information", "Please fill in all required fields (Last Name, First Name, Email, Password).");
                    return;
                }
                User user = new User();
                user.setEmail(userEmail);
                user.setFullName(fn);
                user.setPassword(pw);
                user.setPhone(ph);
                user.setRoleId(rid);
                user.setSpecialty(sp);
                user.setStatus(1);
                user.setUsername(un);
                // Attempt to add a patient to the database
                if (db.insertUser(user)>0) {
                    userAuth = true;
                    System.out.println("User Successfully Added");
                } else {
                    userAuth = false;
                    System.out.println("Error Adding User");
                }

                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
            //********************************************************************
        });


        Rectangle background = new Rectangle(1000, 600);
        background.setOpacity(0.0);
        StackPane sp = new StackPane(background, root);
        Scene scene = new Scene(sp);

        primaryStage.setMinWidth(1000); // Set minimum width for the window
        primaryStage.setMinHeight(600); // Set minimum height for the window
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	private boolean isValidEmail(String email) {
		return email.contains("@");
	}
	
	private void showErrorAlert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

    // Helper method to create labeled text field
    private HBox createLabeledField(String label) {
        Label labelNode = new Label(label);
        labelNode.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        HBox labeledField = new HBox(labelNode);
        labeledField.setAlignment(Pos.CENTER);
        labeledField.setSpacing(10);

        return labeledField;
    }

    // Helper method to create styled text field
    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 20px;  -fx-pref-width: 300px;");

        return textField;
    }

    private Button createButton() {
        Button button = new Button("Add User");

        button.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(50), Insets.EMPTY)));

        // Set button text color
        button.setTextFill(Color.WHITE);

        // Add some padding and styling for a better look
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        BorderPane.setMargin(button, INSETS);
        BorderPane.setAlignment(button, Pos.CENTER);

        return button;
    }

    private Button createStyledButton(String text, Color color) {
        Button button = new Button(text);

        // Set button color
        button.setBackground(new Background(new BackgroundFill(color, new CornerRadii(50), Insets.EMPTY)));

        // Set button text color
        button.setTextFill(Color.WHITE);

        // Add some padding and styling for a better look
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Set button margins
        BorderPane.setMargin(button, INSETS);
        BorderPane.setAlignment(button, Pos.CENTER);

        return button;
    }

=======
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dao.*;
import models.*;
import javafx.scene.Node;

public class AddUser extends Application{
	Database db;
	boolean userAuth = false;
	
	private Insets INSETS = new Insets(10, 10, 10, 10);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		db = new Database();
		primaryStage.setTitle("Add User Menu");
        Button btn2 = createButton();
        Button backBtn = createStyledButton("Back", Color.GRAY);

        Text title = new Text("Add User's Credentials");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        TextField userName = createStyledTextField("Enter Username");
        TextField email = createStyledTextField("Enter Email");
        TextField password = createStyledTextField("Enter Password");
        TextField fullName = createStyledTextField("Enter Full name");
        TextField phone = createStyledTextField("Enter Phone");
        TextField specialty = createStyledTextField("Enter Specialty");
        TextField roleId = createStyledTextField("Enter RoleID");
		
		GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        root.add(title, 0, 0, 2, 1);

        int rowIndex = 1;
        root.add(createLabeledField("Username:"), 0, rowIndex);
        root.add(userName, 1, rowIndex++);

        root.add(createLabeledField("Email:"), 0, rowIndex);
        root.add(email, 1, rowIndex++);

        root.add(createLabeledField("Password:"), 0, rowIndex);
        root.add(password, 1, rowIndex++);

        root.add(createLabeledField("Full Name:"), 0, rowIndex);
        root.add(fullName, 1, rowIndex++);

        root.add(createLabeledField("Phone:"), 0, rowIndex);
        root.add(phone, 1, rowIndex++);

        root.add(createLabeledField("Specialty:"), 0, rowIndex);
        root.add(specialty, 1, rowIndex++);

        root.add(createLabeledField("RoleID:"), 0, rowIndex);
        root.add(roleId, 1, rowIndex++);

        for (Node node : root.getChildren()) {
            if (node instanceof Label || node instanceof TextField) {
                GridPane.setValignment(node, VPos.CENTER);
            }
        }

        root.add(btn2, 1, rowIndex++);
        root.add(backBtn, 1, rowIndex++);

        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(10);

//		root.getChildren().addAll(title,
//                createLabeledField("Username:", userName),
//                createLabeledField("Email:", email),
//                createLabeledField("Password:", password),
//                createLabeledField("Full Name:", fullName),
//                createLabeledField("Phone:", phone),
//                createLabeledField("Specialty:", specialty),
//                createLabeledField("RoleID:", roleId), btn2, backBtn);
//        root.setAlignment(Pos.CENTER);
//        root.setSpacing(15);

//        root.setCenter(boxInput);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
        });
        
        // Create an event handler for "Add User" button
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fn = fullName.getText(); // Get last name from input
                String pw = password.getText(); // Get first name from input
                String ph = phone.getText(); // Get first name from input
                String sp = specialty.getText(); // Get first name from input
                String un = userName.getText();
                int rid = Integer.parseInt(roleId.getText());
                
                String userEmail = email.getText(); // Get email from input

                //******************************************************
                // Validate email format
                if (!isValidEmail(userEmail)) {
                    showErrorAlert("Invalid Email", "Please enter a valid email address.");
                    return;
                }
                // Check if the email is already in use
                if (db.isEmailInUse(userEmail)) {
                    showErrorAlert("Email In Use", "This email is already associated with an existing account. Please use a different email.");
                    return;
                }
                // Check if any of the required fields (except Emergency Phone and Emergency Contact) are empty
                if (fn.isEmpty() || userEmail.isEmpty() || un.isEmpty()||password.getText().isEmpty()) {
                    showErrorAlert("Missing Information", "Please fill in all required fields (Last Name, First Name, Email, Password).");
                    return;
                }
                User user = new User();
                user.setEmail(userEmail);
                user.setFullName(fn);
                user.setPassword(pw);
                user.setPhone(ph);
                user.setRoleId(rid);
                user.setSpecialty(sp);
                user.setStatus(1);
                user.setUsername(un);
                // Attempt to add a patient to the database
                if (db.insertUser(user)>0) {
                    userAuth = true;
                    System.out.println("User Successfully Added");
                } else {
                    userAuth = false;
                    System.out.println("Error Adding User");
                }

                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
            //********************************************************************
        });


        Rectangle background = new Rectangle(1000, 600);
        background.setOpacity(0.0);
        StackPane sp = new StackPane(background, root);
        Scene scene = new Scene(sp);

        primaryStage.setMinWidth(1000); // Set minimum width for the window
        primaryStage.setMinHeight(600); // Set minimum height for the window
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	private boolean isValidEmail(String email) {
		return email.contains("@");
	}
	
	private void showErrorAlert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

    // Helper method to create labeled text field
    private HBox createLabeledField(String label) {
        Label labelNode = new Label(label);
        labelNode.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        HBox labeledField = new HBox(labelNode);
        labeledField.setAlignment(Pos.CENTER);
        labeledField.setSpacing(10);

        return labeledField;
    }

    // Helper method to create styled text field
    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 20px;  -fx-pref-width: 300px;");

        return textField;
    }

    private Button createButton() {
        Button button = new Button("Add User");

        button.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(50), Insets.EMPTY)));

        // Set button text color
        button.setTextFill(Color.WHITE);

        // Add some padding and styling for a better look
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        BorderPane.setMargin(button, INSETS);
        BorderPane.setAlignment(button, Pos.CENTER);

        return button;
    }

    private Button createStyledButton(String text, Color color) {
        Button button = new Button(text);

        // Set button color
        button.setBackground(new Background(new BackgroundFill(color, new CornerRadii(50), Insets.EMPTY)));

        // Set button text color
        button.setTextFill(Color.WHITE);

        // Add some padding and styling for a better look
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Set button margins
        BorderPane.setMargin(button, INSETS);
        BorderPane.setAlignment(button, Pos.CENTER);

        return button;
    }

>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
}