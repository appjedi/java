package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddUser extends Application{
	dao.Database db;
	boolean userAuth = false;
	
	private Insets INSETS = new Insets(10, 10, 10, 10);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		db = new dao.Database();
		primaryStage.setTitle("Add User Menu");
        Button btn2 = createButton();

        Text title = new Text("Add User's Credentials");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        TextField userName = createStyledTextField("Enter Username");
        TextField email = createStyledTextField("Enter Email");
        TextField password = createStyledTextField("Enter Password");
        TextField fullName = createStyledTextField("Enter Full name");
        TextField phone = createStyledTextField("Enter Phone");
        TextField goals = createStyledTextField("Enter Goals");
        TextField roleId = createStyledTextField("Enter RoleID");
		
		BorderPane root = new BorderPane();
		VBox boxInput = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		boxInput.getChildren().addAll(title,
                createLabeledField("Username:", userName),
                createLabeledField("Email:", email),
                createLabeledField("Password:", password),
                createLabeledField("Full Name:", fullName),
                createLabeledField("Phone:", phone),
                createLabeledField("Goals:", goals),
                createLabeledField("RoleID:", roleId), btn2);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(15);

        root.setCenter(boxInput);
        
        // Create an event handler for "Add Doctor" button
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fn = fullName.getText(); // Get last name from input
                String pw = password.getText(); // Get first name from input
                String ph = phone.getText(); // Get first name from input
                String g = goals.getText(); // Get first name from input
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
                models.Profile user = new models.Profile();
                user.setEmail(userEmail);
                user.setName(fn);
                user.setPassword(pw);
                user.setPhone(ph);
                user.setRoleId(rid);
                user.setGoals(g);
                user.setStatus(1);
                user.setUsername(un);
                // Attempt to add a patient to the database
                if (db.createProfile(user)>0) {
                    userAuth = true;
                    System.out.println("User Successfully Added");
                } else {
                    userAuth = false;
                    System.out.println("Error Adding User");
                }
            }
            //********************************************************************
        });
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
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
    private HBox createLabeledField(String label, TextField textField) {
        Label labelNode = new Label(label);
        labelNode.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        HBox labeledField = new HBox(labelNode, textField);
        labeledField.setAlignment(Pos.CENTER);
        labeledField.setSpacing(10);

        return labeledField;
    }

    // Helper method to create styled text field
    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 20px;");

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

}
