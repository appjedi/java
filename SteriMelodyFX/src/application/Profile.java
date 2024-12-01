package application;

/*
--module-path "/Users/roberttimlin/Documents/javafx-sdk-22/lib" --add-modules javafx.controls,javafx.fxml 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import javafx.scene.Group; 

public class Profile extends Application {
	dao.Database db;
	Stage primaryStage;
	models.Profile profile;
	MainMenu mainMenu;
	Profile thisProfile;
	public Profile() {}
	public Profile(models.Profile p,MainMenu mm) {
		this.profile=p;
		this.mainMenu=mm;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private final Insets INSETS = new Insets(100, 10, 10, 10);
	private final Insets LEFTSIDE = new Insets(5, 5, 5, 5);
	private final Insets TOPLEFTSIDE = new Insets(5, 5, 5, 5);
	private final static String[] RANKS= {"n/a","1st", "2nd","3rd", "4th", "5th","6th","7th","8th","9th"};
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			db = new dao.Database();
			thisProfile=this;
			//profile=db.getProfile(1);
	        ProgressBar pb = new ProgressBar(); 
	        pb.setProgress(.5); 

			pb.setLayoutX(400);
			pb.setLayoutY(250);
			//pb.set			
			primaryStage.setTitle("Profile");
			String img = profile.getImage();
			if(img==null)
			{
				img="dog.jpg";
			}
			Image image = new Image(new FileInputStream("/Users/roberttimlin/eclipse-workspace/SteriMelodyFX/images/"+img));
		    ImageView imageView = new ImageView(image); 
		    imageView.setFitHeight(100); 
		    imageView.setFitWidth(100);
		    imageView.setX(800);
		    imageView.setY(15);
			Text title = new Text("Profile");
			Text goals= new Text("Goals: "+profile.getGoals());
			Text highestRank= new Text("Highest Rank: "+RANKS[profile.getHighestRank()]);
			title.setX(400);
			title.setY(100);
			
			highestRank.setX(400);
			highestRank.setY(200);	
			
			goals.setX(400);
			goals.setY(300);
			
			//title.setTextAlignment(TextAlignment.CENTER);
			//title.setY(500);
			Text rightText = new Text(profile.getName());
			Text topLeftText = new Text("Top Left");
			topLeftText.setX(15);
			topLeftText.setY(15);

			rightText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			rightText.setX(850);
			rightText.setY(250);
			title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
			title.setFill(Color.DARKBLUE);

			//loginForm = new LoginForm(this);

			Button btn1 = createStyledButton("Play", Color.TAN);
			Button btn2 = createStyledButton("Learn", Color.TAN);
			Button btn3 = createStyledButton("Chat", Color.TAN);
			Button btn4 = createStyledButton("Leader Board", Color.TAN);
			Button btn5 = createStyledButton("Profile", Color.TAN);
			Button btn6 = createStyledButton("Logout", Color.TAN);

			VBox topLeftBox = new VBox();
			
			VBox boxInput = new VBox();
			boxInput.setLayoutX(5);
			boxInput.setLayoutY(5);
			VBox boxCenter = new VBox();
			VBox rightBox = new VBox();


			boxCenter.getChildren().addAll(title);
			topLeftBox.getChildren().addAll(topLeftText);
			rightBox.getChildren().addAll(imageView,rightText);
			boxInput.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
			rightBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
			boxInput.getChildren().addAll(btn1, btn2, btn3, btn4, btn5,btn6);
			//topLeftBox.getChildren().addAll(topLeftText);
			boxInput.setMinWidth(220);
			rightBox.setMinWidth(200);
			rightBox.setMaxWidth(200);
			topLeftBox.setMinHeight(100);
			topLeftBox.setMinWidth(200);
			topLeftBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

			// boxCenter.setAlignment(Pos.CENTER);
			boxInput.setSpacing(5);
			boxCenter.setSpacing(5);
			boxInput.setPadding(LEFTSIDE);
			boxInput.setLayoutY(100);
			boxCenter.setPadding(INSETS);
			topLeftBox.setPadding(TOPLEFTSIDE);
			//root.setTop(topLeftBox);
			
			Group root = new Group(topLeftText,boxInput,title,highestRank,pb,goals,imageView,rightText); // Create a BorderPane layout

			//rightBox.setSpacing(10);
			

			// Create event handlers for buttons
			btn1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("button 1 click");
				}
			});

			btn2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("button 2 click");

				}
			});
			btn3.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("button 3 click");

				}
			});
			btn4.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					LeaderBoard lb = new LeaderBoard(profile, thisProfile);
					lb.start(primaryStage);
					System.out.println("button 4 click");

				}
			});
			btn5.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("button 5 click");

				}
			});
			btn6.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					mainMenu.logout();
				}
			});
			Scene scene = new Scene(root);
			
			primaryStage.setMinWidth(1000); // Set minimum width for the window
			primaryStage.setMinHeight(600); // Set minimum height for the window
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
