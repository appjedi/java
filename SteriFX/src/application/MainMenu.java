package application;
/*
   --module-path "/Users/roberttimlin/Documents/javafx-sdk-22/lib" --add-modules javafx.controls,javafx.fxml 
 */
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainMenu extends Application {
    dao.Database db;
    Stage primaryStage;
    models.User user = null;
    models.Doctor doctor = null;
    LoginForm loginForm;
    AddUser addUser;
    Profile profile;

    public static void main(String[] args) {
        launch(args);
    }

    private final Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        db = new dao.Database();
        primaryStage.setTitle("Main Menu Login");

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
    	this.doctor=null;
    }
    public void authResponse(models.User u) {
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

//   Mental Health Referal
