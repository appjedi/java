<<<<<<< HEAD
<<<<<<< HEAD
package application;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import dao.*;
import models.*;

import java.net.URL;

public class MainMenu extends Application {
    Database db;
    Stage primaryStage;
    User user = null;
    Doctor doctor = null;
    LoginForm loginForm;
    AddUser addUser;
    Profile profile;
    private static App APP;
    private App app;

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void SetApp(App app)
    {
    	APP=app;
    }


    private final Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
    	System.out.println ("MainMenu");

        this.primaryStage = primaryStage;
        this.app=APP;
        db = new Database();
        primaryStage.setTitle("Main Menu Login");

//        Image backgroundImage = null;
//        URL imageUrl = getClass().getResource("/main/java/image.jpeg");
//        if (imageUrl != null) {
//            backgroundImage = new Image(imageUrl.toExternalForm());
//        } else {
//            System.err.println("Image resource not found!");
//            // Handle the situation when the resource is not found
//        }

        // Load the background image
        // Image backgroundImage = new Image(getClass().getResource("/main/java/image.jpeg").toExternalForm());

        // Create an ImageView to display the background image
        // ImageView backgroundImageView = new ImageView(backgroundImage);

        // Set the preserve ratio property to maintain the aspect ratio
        // backgroundImageView.setPreserveRatio(true);

        // Set the dimensions of the background image within the scene
        // backgroundImageView.setFitWidth(1100);
        // backgroundImageView.setFitHeight(650);

        Text title = new Text("Welcome to Mission-EMR");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        loginForm = new LoginForm(this);
        addUser = new AddUser();

        Button btn1 = createStyledButton("Login", Color.GREEN);
        Button btn2 = createStyledButton("Add User", Color.ORANGE);
        Button btn3 = createStyledButton("Forgot Password", Color.PURPLE);

        BorderPane root = new BorderPane(); // Create a BorderPane layout
        VBox boxInput = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        boxInput.getChildren().addAll(title, btn1, btn2, btn3);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(15);

        root.setCenter(boxInput);

        // Create event handlers for buttons
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginForm.start(primaryStage);
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddUser au = new AddUser();
                try {
					au.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ForgotPassword fp = new ForgotPassword();
                fp.start(primaryStage);
            }
        });

//        Rectangle background = new Rectangle(1000, 600);
//        background.setOpacity(0.0);
//        StackPane sp = new StackPane(background, root);
//        Scene scene = new Scene(sp);

        StackPane sp = new StackPane(root); // Use backgroundImageView as the background
        Scene scene = new Scene(sp);

        primaryStage.setMinWidth(1000); // Set minimum width for the window
        primaryStage.setMinHeight(600); // Set minimum height for the window
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void logout()
    {
    	System.out.println("logout");
    	this.profile=null;
    	this.start(primaryStage);
    	this.user=null;
    	this.doctor=null;
    }
    public void authResponse(User u) {
        if (u == null) {
            return;
        }
        user = u;
        //profile = new Profile(this, pat);
        //profile.start(this.primaryStage);
        System.out.printf("authResponse: %s", u);
    }

    public void childCallback() {
        this.start(primaryStage);
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

//        Date/Time
//         Contact Information
//    Insurance
//        Vitals
//            History
//        Current medications
//    Allergy
//    Main Complaints
//    Current Pain Level (0-10)
//        Medications Refill
//        Diagnosis

=======
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
package application;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import dao.*;
import models.*;

import java.net.URL;

public class MainMenu extends Application {
    Database db;
    Stage primaryStage;
    User user = null;
    Doctor doctor = null;
    LoginForm loginForm;
    AddUser addUser;
    Profile profile;
    private static App APP;
    private App app;

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void SetApp(App app)
    {
    	APP=app;
    }


    private final Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
    	System.out.println ("MainMenu");

        this.primaryStage = primaryStage;
        this.app=APP;
        db = new Database();
        primaryStage.setTitle("Main Menu Login");

//        Image backgroundImage = null;
//        URL imageUrl = getClass().getResource("/main/java/image.jpeg");
//        if (imageUrl != null) {
//            backgroundImage = new Image(imageUrl.toExternalForm());
//        } else {
//            System.err.println("Image resource not found!");
//            // Handle the situation when the resource is not found
//        }

        // Load the background image
        // Image backgroundImage = new Image(getClass().getResource("/main/java/image.jpeg").toExternalForm());

        // Create an ImageView to display the background image
        // ImageView backgroundImageView = new ImageView(backgroundImage);

        // Set the preserve ratio property to maintain the aspect ratio
        // backgroundImageView.setPreserveRatio(true);

        // Set the dimensions of the background image within the scene
        // backgroundImageView.setFitWidth(1100);
        // backgroundImageView.setFitHeight(650);

        Text title = new Text("Welcome to Mission-EMR");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        loginForm = new LoginForm(this);
        addUser = new AddUser();

        Button btn1 = createStyledButton("Login", Color.GREEN);
        Button btn2 = createStyledButton("Add User", Color.ORANGE);
        Button btn3 = createStyledButton("Forgot Password", Color.PURPLE);

        BorderPane root = new BorderPane(); // Create a BorderPane layout
        VBox boxInput = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        boxInput.getChildren().addAll(title, btn1, btn2, btn3);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(15);

        root.setCenter(boxInput);

        // Create event handlers for buttons
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginForm.start(primaryStage);
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddUser au = new AddUser();
                try {
					au.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ForgotPassword fp = new ForgotPassword();
                fp.start(primaryStage);
            }
        });

//        Rectangle background = new Rectangle(1000, 600);
//        background.setOpacity(0.0);
//        StackPane sp = new StackPane(background, root);
//        Scene scene = new Scene(sp);

        StackPane sp = new StackPane(root); // Use backgroundImageView as the background
        Scene scene = new Scene(sp);

        primaryStage.setMinWidth(1000); // Set minimum width for the window
        primaryStage.setMinHeight(600); // Set minimum height for the window
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void logout()
    {
    	System.out.println("logout");
    	this.profile=null;
    	this.start(primaryStage);
    	this.user=null;
    	this.doctor=null;
    }
    public void authResponse(User u) {
        if (u == null) {
            return;
        }
        user = u;
        //profile = new Profile(this, pat);
        //profile.start(this.primaryStage);
        System.out.printf("authResponse: %s", u);
    }

    public void childCallback() {
        this.start(primaryStage);
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

//        Date/Time
//         Contact Information
//    Insurance
//        Vitals
//            History
//        Current medications
//    Allergy
//    Main Complaints
//    Current Pain Level (0-10)
//        Medications Refill
//        Diagnosis

<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
//   Mental Health Referal