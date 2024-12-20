
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.text.FontWeight;
import dao.*;
import models.*;

public class LoginForm extends Application {
    Database db;
    Patient patient = null;
    Doctor doctor = null;
    User user=null;
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
    	System.out.println ("LoginForm");

        db = new Database();
        primaryStage.setTitle("Login Form.");

        Text title = new Text("Login with your Credentials");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        Button btn1 = createButton("Login");
        Button backBtn = createStyledButton("Back", Color.GRAY);

        TextField userName = createStyledTextField("Enter Username");
        PasswordField passWord = createStyledPasswordField("Enter Password");

        BorderPane root = new BorderPane();
        VBox boxInput = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        boxInput.getChildren().addAll(title,
                createLabeledField("Username:", userName),
                createLabeledField("Password:", passWord), btn1, backBtn);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(20);

        root.setCenter(boxInput);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
        });

        // Create an event handler for "Login" button
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String un = userName.getText(); // Get Username from input
                String pw = passWord.getText(); // Get Password from input


	                User u = db.auth(un, pw);  // To make this look better, use some interface, but that will be annoying

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


        Rectangle background = new Rectangle(1000, 600);
        background.setOpacity(0.0);
        StackPane sp = new StackPane(background, root);
        Scene scene = new Scene(sp);

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.text.FontWeight;
import dao.*;
import models.*;

public class LoginForm extends Application {
    Database db;
    Patient patient = null;
    Doctor doctor = null;
    User user=null;
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
    	System.out.println ("LoginForm");

        db = new Database();
        primaryStage.setTitle("Login Form.");

        Text title = new Text("Login with your Credentials");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        Button btn1 = createButton("Login");
        Button backBtn = createStyledButton("Back", Color.GRAY);

        TextField userName = createStyledTextField("Enter Username");
        PasswordField passWord = createStyledPasswordField("Enter Password");

        BorderPane root = new BorderPane();
        VBox boxInput = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        boxInput.getChildren().addAll(title,
                createLabeledField("Username:", userName),
                createLabeledField("Password:", passWord), btn1, backBtn);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(20);

        root.setCenter(boxInput);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
        });

        // Create an event handler for "Login" button
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String un = userName.getText(); // Get Username from input
                String pw = passWord.getText(); // Get Password from input


	                User u = db.auth(un, pw);  // To make this look better, use some interface, but that will be annoying

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


        Rectangle background = new Rectangle(1000, 600);
        background.setOpacity(0.0);
        StackPane sp = new StackPane(background, root);
        Scene scene = new Scene(sp);

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
<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
