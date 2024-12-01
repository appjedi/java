package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.text.FontWeight;

public class LoginForm extends Application {
    dao.Database db;
    models.Patient patient = null;
    models.Doctor doctor = null;
    models.User user=null;
    MainMenu mm;
    Portal portal;

    public static void main(String[] args) {
        launch(args);
    }

    public LoginForm() {
    }

    public LoginForm(MainMenu mm) {
        this.mm = mm;
    }

    private Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
        db = new dao.Database();
        primaryStage.setTitle("Login Form.");

        Text title = new Text("Login with your Credentials");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        Button btn1 = createButton("Login");

        TextField userName = createStyledTextField("Enter Username");
        PasswordField passWord = createStyledPasswordField("Enter Password");

        BorderPane root = new BorderPane();
        VBox boxInput = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        boxInput.getChildren().addAll(title,
                createLabeledField("Username:", userName),
                createLabeledField("Password:", passWord), btn1);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(20);

        root.setCenter(boxInput);

        // Create an event handler for "Login" button
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String un = userName.getText(); // Get Username from input
                String pw = passWord.getText(); // Get Password from input


	                models.User u = db.auth(un, pw);  // To make this look better, use some interface, but that will be annoying

	                if (u != null) {
	                    user = u;

	                    System.out.println("User Authenticated:"+u);
	                    mm.authResponse(u);
//	                    ModUser mu = new ModUser(u);
                        portal = new Portal(u);
	                    try {

                            portal.start(primaryStage);
							// mu.start(primaryStage);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


	                } else {
	                    System.out.println("Patient User NOT Authenticated");
	                }

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

        button.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(50), Insets.EMPTY)));

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

    // Helper method to create styled password field
    private PasswordField createStyledPasswordField(String promptText) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setStyle("-fx-font-size: 20px;");

        return passwordField;
    }
}
