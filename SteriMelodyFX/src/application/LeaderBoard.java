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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.util.List;

import javafx.scene.Group; 

public class LeaderBoard extends Application {
	dao.Database db;
	Stage primaryStage;
	models.Profile profile;
	List<models.Profile>profiles;
	Profile profilePage;

	public LeaderBoard() {
		db = new dao.Database();
		this.profile=db.getProfile(1);

	}
	public LeaderBoard(models.Profile p, Profile pp)
	{
		this.profilePage=pp;
		db = new dao.Database();
		
		this.profile=p;
	}
	public static void main(String[] args) {
		launch(args);
	}

	private final static Insets INSETS = new Insets(100, 10, 10, 10);
	private final Insets LEFTSIDE = new Insets(5, 5, 5, 5);
	private final Insets TOPLEFTSIDE = new Insets(5, 5, 5, 5);
	private final static String[] RANKS= {"n/a","1st", "2nd","3rd", "4th", "5th","6th","7th","8th","9th"};
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			profiles=db.getProfileTopRank(5);
			
	
			//pb.set			
			primaryStage.setTitle("Ranks");
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
			Text rightText = new Text(profile.getName());
			
			Text topLeftText = new Text("Top Left");
			Text topBanner = new Text("Rank");
			topBanner.setFont(Font.font("Arial", FontWeight.BOLD, 24));
			topBanner.setFill(Color.DARKBLUE);
			topBanner.setX(350);
			topBanner.setY(200);
			topLeftText.setX(15);
			topLeftText.setY(15);

			rightText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			rightText.setX(800);
			rightText.setY(250);
			title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
			title.setFill(Color.DARKBLUE);
			int ct=0;
			VBox boxLeaders = new VBox();
			boxLeaders.setLayoutX(320);
			boxLeaders.setLayoutY(100);
			boxLeaders.setSpacing(10);
			VBox boxImages=new VBox();
			boxImages.setLayoutX(250);
			boxImages.setLayoutY(100);	
			boxImages.setSpacing(10);

			Image topDog = new Image(new FileInputStream("/Users/roberttimlin/eclipse-workspace/SteriMelodyFX/images/leader1.jpeg"));
			ImageView ivTopDog = new ImageView(topDog); 
			ivTopDog.setFitHeight(50); 
			ivTopDog.setFitWidth(50);
			ivTopDog.setX(650);
			ivTopDog.setY(100);
		    for (models.Profile p:profiles)
			{
				System.out.println(p);
				int rank=p.getHighestRank();
				Button b = createStyledButton(p.getName()+": "+RANKS[rank], Color.TAN,50,300);
				//Text b = new Text(p.getName()+": "+RANKS[rank]);
				String img1 = p.getImage();
				if(img1==null)
				{
					img1="dog.jpg";
				}
				Image image1 = new Image(new FileInputStream("/Users/roberttimlin/eclipse-workspace/SteriMelodyFX/images/"+img1));
				ImageView iv = new ImageView(image1); 
			    iv.setFitHeight(50); 
			    iv.setFitWidth(50);
			    
			    //iv.setX(650);
			    iv.setLayoutY(150*ct);
				//iv.setLayoutY(50*ct);
				boxImages.getChildren().add(iv);
				//boxImages.getChildren().add(fill);

				b.setLayoutY(150*ct);
				//l.setX(400);
			//	b.setLayoutY(50*ct);
				boxLeaders.getChildren().add(b);
				//boxLeaders.getChildren().add(fill);
				
				++ct;
			}



			//loginForm = new LoginForm(this);

			Button btn1 = createStyledButton("Play", Color.TAN);
			Button btn2 = createStyledButton("Learn", Color.TAN);
			Button btn3 = createStyledButton("Chat", Color.TAN);
			Button btn4 = createStyledButton("Leader Board", Color.TAN);
			Button btn5 = createStyledButton("Profile", Color.TAN);
			Button btn6 = createStyledButton("Back", Color.TAN);
			
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
			
			Group root = new Group(topBanner,topLeftText,boxInput,title,ivTopDog,boxImages,boxLeaders,imageView,rightText); // Create a BorderPane layout
			
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
					profilePage.start(primaryStage);
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

	public void logout() {
		System.out.println("logout");
		this.profile = null;
		this.start(primaryStage);
	}

	public void authResponse(models.Profile u) {
		if (u == null) {
			return;
		}
		this.profile = u;
		// profile = new Profile(this, pat);
		// profile.start(this.primaryStage);
		System.out.printf("authResponse: %s", u);
	}

	public void childCallback() {
		this.start(primaryStage);
	}
    private static Button createStyledButton(String text, Color color, int height,int width) {
        Button button = new Button(text);

        // Set button color
        button.setBackground(new Background(new BackgroundFill(color, new CornerRadii(height), Insets.EMPTY)));

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
