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

public class ModUser extends Application{

	Stage primaryStage;
	Portal triagePortal;
	dao.Database db;
	boolean userAuth = false;
	models.User user=null;
	private Insets INSETS = new Insets(10, 10, 10, 10);
	
	public static void main(String[] args) {
		launch(args);
	}

	public ModUser() {}

	public ModUser(models.User user)
	{
		this.user=user;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		db = new dao.Database();
		primaryStage.setTitle("Update User Menu");

		Text title = new Text("Update User Credentials");

		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		title.setFill(Color.DARKBLUE);

		Button btn1 = createStyledButton("Update User", Color.LIGHTSKYBLUE);
		Button btn2 = createStyledButton("Delete User", Color.LIGHTGREY);
		Button continueBtn = createStyledButton("Back", Color.LIGHTGREEN);

		TextField username = createStyledTextField("enter username");
		TextField email = createStyledTextField("enter email");
		TextField password = createStyledTextField("enter password");
		TextField fullName = createStyledTextField("enter fullanme");
		TextField phone = createStyledTextField("enter phone");
		TextField specialty = createStyledTextField("enter specialty");
		TextField roleId = createStyledTextField("enter roleID");

		username.setText(user.getUsername());
		email.setText(user.getEmail());
		fullName.setText(user.getFullName());
		phone.setText(user.getPhone());
		specialty.setText(user.getSpecialty());
		roleId.setText(user.getRoleId()+"");

		BorderPane root = new BorderPane();
		root.setBackground(new Background(new BackgroundFill(Color.MEDIUMSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		VBox boxInput = new VBox();
		boxInput.getChildren().addAll(title,
				createLabeledField("Username:", username),
				createLabeledField("Email:", email),
				createLabeledField("Password:", password),
				createLabeledField("Full Name:", fullName),
				createLabeledField("Phone:", phone),
				createLabeledField("Specialty:", specialty),
				createLabeledField("RoleID:", roleId),
				btn1, btn2, continueBtn);
		boxInput.setAlignment(Pos.CENTER);
		boxInput.setSpacing(15);
        root.setCenter(boxInput);

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
