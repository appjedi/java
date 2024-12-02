<<<<<<< HEAD
<<<<<<< HEAD
package application;
/*
--module-path "/Users/roberttimlin/Documents/javafx-sdk-22/lib" --add-modules javafx.controls,javafx.fxml 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainMenu extends Application {
	private static App APP;
	App app;
    dao.Database db;
    Stage primaryStage;
    models.Profile user = null;
    LoginForm loginForm;
    AddUser addUser;
    Profile profile;

    public static void main(String[] args) {
        launch(args);
    }
    public static void SetApp(App app)
    {
    	APP=app;
    }

    private final static Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.app=APP;

        db = new dao.Database();
        primaryStage.setTitle("Main Menu Login");

        Text title = new Text("Welcome to Melody");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        loginForm = new LoginForm(this);
        addUser = new AddUser();

        Button btn1 = createStyledButton("Login", Color.GREEN,50,200);
        Button btn2 = createStyledButton("Add User", Color.ORANGE,50,200);
        Button btn3 = createStyledButton("Forgot Password", Color.PURPLE,50,200);

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
                
                try {
                	addUser.start(primaryStage);
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
       
        Scene scene = new Scene(root);

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
    }
    public void authResponse(models.Profile u) {
        if (u == null) {
            return;
        }
        user = u;
        try {
        	profile = new Profile(u,this);
        	profile.start(primaryStage);
			// mu.start(primaryStage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //profile = new Profile(this, pat);
        //profile.start(this.primaryStage);
        System.out.printf("authResponse: %s", u);
    }
   

    public void childCallback() {
        this.start(primaryStage);
    }

    private static Button createStyledButton(String text, Color color, int height,int width) {
        Button button = new Button(text);

        // Set button color
        button.setBackground(new Background(new BackgroundFill(color, new CornerRadii(50), Insets.EMPTY)));

        // Set button text color
        button.setTextFill(Color.WHITE);

        // Add some padding and styling for a better look
        button.setMinWidth(width);
        button.setMinHeight(height);
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

//   Mental Health Referal
=======
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
package application;
/*
--module-path "/Users/roberttimlin/Documents/javafx-sdk-22/lib" --add-modules javafx.controls,javafx.fxml 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainMenu extends Application {
	private static App APP;
	App app;
    dao.Database db;
    Stage primaryStage;
    models.Profile user = null;
    LoginForm loginForm;
    AddUser addUser;
    Profile profile;

    public static void main(String[] args) {
        launch(args);
    }
    public static void SetApp(App app)
    {
    	APP=app;
    }

    private final static Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.app=APP;

        db = new dao.Database();
        primaryStage.setTitle("Main Menu Login");

        Text title = new Text("Welcome to Melody");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        loginForm = new LoginForm(this);
        addUser = new AddUser();

        Button btn1 = createStyledButton("Login", Color.GREEN,50,200);
        Button btn2 = createStyledButton("Add User", Color.ORANGE,50,200);
        Button btn3 = createStyledButton("Forgot Password", Color.PURPLE,50,200);

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
                
                try {
                	addUser.start(primaryStage);
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
       
        Scene scene = new Scene(root);

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
    }
    public void authResponse(models.Profile u) {
        if (u == null) {
            return;
        }
        user = u;
        try {
        	profile = new Profile(u,this);
        	profile.start(primaryStage);
			// mu.start(primaryStage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //profile = new Profile(this, pat);
        //profile.start(this.primaryStage);
        System.out.printf("authResponse: %s", u);
    }
   

    public void childCallback() {
        this.start(primaryStage);
    }

    private static Button createStyledButton(String text, Color color, int height,int width) {
        Button button = new Button(text);

        // Set button color
        button.setBackground(new Background(new BackgroundFill(color, new CornerRadii(50), Insets.EMPTY)));

        // Set button text color
        button.setTextFill(Color.WHITE);

        // Add some padding and styling for a better look
        button.setMinWidth(width);
        button.setMinHeight(height);
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

//   Mental Health Referal
<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
