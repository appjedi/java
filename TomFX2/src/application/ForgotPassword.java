package application;

import com.mysql.cj.log.Log;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import models.Doctor;
import models.Patient;

public class ForgotPassword extends Application {
	dao.Database db;
	models.User user = null;
	MainMenu mm;
	LoginForm lf;

	public static void main(String[] args) {
		launch(args);
	}

	public ForgotPassword() {
	}

	public ForgotPassword(MainMenu mm) {
		this.mm = mm;
	}

	private Insets INSETS = new Insets(10, 10, 10, 10);

	@Override
	public void start(Stage primaryStage) {
		db = new dao.Database();
		primaryStage.setTitle("Password Recovery.");

		Text title = new Text("Password Recovery");

		title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		title.setFill(Color.DARKBLUE);

		Button btn1 = createStyledButton("Retrieve Account", Color.GREEN);
		Button btn2 = createStyledButton("Reset Password", Color.PURPLE);
		Button btn3 = createStyledButton("Back", Color.GRAY);

		TextField email = createStyledTextField("Enter email");
		TextField phone = createStyledTextField("Enter phone");
		TextField password = createStyledTextField("Enter password");
		TextField username = createStyledTextField("Enter username");

		BorderPane root = new BorderPane();
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));

		username.setDisable(true);
		password.setDisable(true);

		VBox boxInput = new VBox();

		boxInput.getChildren().addAll(title,
				createLabeledField("Email:", email),
				createLabeledField("Phone:", phone),
				btn1,
				createLabeledField("Password:", password),
				createLabeledField("Username:", username),
				btn2, btn3);
		boxInput.setAlignment(Pos.CENTER);
		boxInput.setSpacing(15);
		btn2.setDisable(true);
		root.setCenter(boxInput); // Set input fields and button in the center of the BorderPane.

		// Create an event handler for "Login" button
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String em = email.getText(); // Get Username from input
				String pn = phone.getText(); // Get Password from input

				models.User u = db.forgotPassword(em, pn); // To make this look better, use some interface, but that
															// will be annoying

				if (u != null) {
					user = u;
					System.out.println("User Found");
					btn2.setDisable(false);

					password.setDisable(false);

				} else {
					System.out.println("User NOT found");
				}

			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String pw = password.getText(); // Get Username from input
				// Get Password from input

				if (db.resetPassword(user.getId(), pw, 1)) {

					System.out.println("password reset");

				} else {
					System.out.println(" User NOT found");
				}

			}
		});

		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				LoginForm lf = new LoginForm();
				lf.start(primaryStage);
			}
		});

		Scene scene = new Scene(root);

		primaryStage.setMinWidth(1000); // Set minimum width for the window
		primaryStage.setMinHeight(600); // Set minimum height for the window
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
}
