package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
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
import models.*;
import dao.*;

public class ModUser extends Application{

	Stage primaryStage;
	Portal triagePortal;
	Database db;
	boolean userAuth = false;
	User user=null;
	private Insets INSETS = new Insets(10, 10, 10, 10);
	
	public static void main(String[] args) {
		launch(args);
	}

	public ModUser() {}

	public ModUser(User user)
	{
		this.user=user;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		db = new Database();
		primaryStage.setTitle("Update User Menu");

		Text title = new Text("Update User Credentials");

		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		title.setFill(Color.DARKBLUE);

		Button btn1 = createStyledButton("Update User", Color.LIGHTGREEN);
		Button btn2 = createStyledButton("Delete User", Color.INDIANRED);
		Button continueBtn = createStyledButton("Back", Color.LIGHTGREY);

		TextField username = createStyledTextField("enter username");
		TextField email = createStyledTextField("enter email");
		TextField password = createStyledTextField("enter password");
		TextField fullName = createStyledTextField("enter fullanme");
		TextField phone = createStyledTextField("enter phone");
		TextField specialty = createStyledTextField("enter specialty");
		TextField roleId = createStyledTextField("enter roleID");

		GridPane root = new GridPane();
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		root.add(title, 0, 0, 2, 1);

		int rowIndex = 1;
		root.add(createLabeledField("Username:"), 0, rowIndex);
		root.add(username, 1, rowIndex++);

		root.add(createLabeledField("Email:"), 0, rowIndex);
		root.add(email, 1, rowIndex++);

		root.add(createLabeledField("Password:"), 0, rowIndex);
		root.add(password, 1, rowIndex++);

		root.add(createLabeledField("Fulll Name:"), 0, rowIndex);
		root.add(fullName, 1, rowIndex++);

		root.add(createLabeledField("Phone:"), 0, rowIndex);
		root.add(phone, 1, rowIndex++);

		root.add(createLabeledField("Specialty:"), 0, rowIndex);
		root.add(specialty, 1, rowIndex++);

		root.add(createLabeledField("Role-ID:"), 0, rowIndex);
		root.add(roleId, 1, rowIndex++);

		for (Node node : root.getChildren()) {
			if (node instanceof Label || node instanceof TextField) {
				GridPane.setValignment(node, VPos.CENTER);
			}
		}

		root.add(btn1, 1, rowIndex++);
		root.add(btn2, 1, rowIndex++);
		root.add(continueBtn, 1, rowIndex++);

		root.setAlignment(Pos.CENTER);
		root.setHgap(20);
		root.setVgap(10);

		continueBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// It should take us to the Portal class.
				triagePortal = new Portal(user);
				triagePortal.start(primaryStage);
			}
		});
        
        // Create an event handler for "Add Doctor" button
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String ph = phone.getText(); // Get first name from input
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
                
                
                // Attempt to add a patient to the database
                if (db.updateUser(user.getId(), userEmail,ph)>0) {
                    userAuth = true;
                    System.out.println("User Successfully Updated");
                } else {
                    userAuth = false;
                    System.out.println("Error Updating User");
                }
            }
            //********************************************************************
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                // Attempt to add a patient to the database
                if (db.deleteUser(user.getId())) {
                    userAuth = true;
                    System.out.println("User Successfully Deleted");
                    primaryStage.hide();
                } else {
                    userAuth = false;
                    System.out.println("Error deleting User");
                }
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
		textField.setStyle("-fx-font-size: 20px;");

		return textField;
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

}