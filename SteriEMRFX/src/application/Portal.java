package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.User;

public class Portal extends Application {

    Stage primaryStage;
    ModUser updateUser;
    User user;
    TriageSelect ts;
    TriageQuery triageQuery;

    public static void main(String[] args){
        launch(args);
    }

    public Portal(User user){
        this.user = user;
    }

    private final Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage){
System.out.println ("Portal");
    	this.primaryStage=primaryStage;
    	primaryStage.setTitle("Portal");

        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox topBox = new HBox();
//        TextField name = new TextField("Welcome to M-EMR!");
//
//        name.setDisable(true);
//        name.setOpacity(1.0);
//        name.setBackground(new Background(new BackgroundFill(new Color(0,0,0,0), null, null)));
//        name.setFont(new Font(28));
//        name.setPrefSize(600, 72);
//        name.setPadding(new Insets(5,5,5,5));

        Text title = new Text("Welcome to Triage Form Page");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);

        Image img = new Image("file:target/OwO.jpeg");
        ImageView view = new ImageView(img);
        view.setFitHeight(100);
        view.setFitWidth(100);
        view.setPreserveRatio(true);
        Button profileBtn = new Button(null, view);
        profileBtn.setStyle("-fx-background-image: url('target/OwO.jpeg'); -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: 100;");
        triageQuery=new TriageQuery(this,user);
        
        profileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ModUser mu = new ModUser(user);
                try {
                    mu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        topBox.getChildren().addAll(title, profileBtn);
        topBox.setAlignment(Pos.CENTER);

        Button uploadButt = createStyledButton("Upload Triage Form", Color.ORANGE);
        Button retrieveButt = createStyledButton("Retrieve Patient Information", Color.PURPLE);
        Button triageQueryBtn = createStyledButton("Triage Query", Color.PURPLE);
        Button logoutBtn = createStyledButton("Back", Color.GRAY);
        Button myAssignments = createStyledButton("Assiged To Me", Color.GRAY);

        root.getChildren().addAll(topBox, uploadButt, retrieveButt,triageQueryBtn, logoutBtn);
        if(user.getRoleId()==1)
        {
        	root.getChildren().add(myAssignments);
        }
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu mm = new MainMenu();
                mm.start(primaryStage);
            }
        });

        uploadButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TriageSelect ts = new TriageSelect();
                ts.setUser(user);
                ts.start(primaryStage);
            }
        });
        triageQueryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                
            	triageQuery.start(primaryStage);
            }
        });
        myAssignments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                
            	System.out.println("myAssignments");
            	TriageAssignTo assignTo = new TriageAssignTo(user);
            	assignTo.start(primaryStage);
            }
        });
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
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
    public void childCallback() {
        this.start(primaryStage);
    }
}
