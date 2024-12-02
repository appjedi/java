package application;

import com.sun.javafx.scene.shape.TriangleMeshHelper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dao.Database;
import models.User;

public class TriageFill extends Application {
    Stage primaryStage;
    TriageSelect ts;
    private String title;
    private boolean[] flags;
    VBox root;

    public TriageFill(String title, boolean[] flags) {
        this.title = title;
        this.flags = flags;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private final Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
        VBox triage = Triage.createForm(title, flags);
System.out.println ("TriageFill");
        Button cancel = createStyledButton("Cancel", Color.MAROON);
        Button submit = createStyledButton("Submit", Color.NAVY);

        Button downloadBtn = createStyledButton("Download", Color.CYAN);
        HBox buttBox = new HBox(cancel, submit, downloadBtn);
        buttBox.setSpacing(20);
        // triage.getChildren().add(buttBox);

        triage.setAlignment(Pos.CENTER);
        triage.setSpacing(20);

        submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				uploadData(triage);
			}
        });
    
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Portal portal = new Portal(new User());
                portal.start(primaryStage);
            }
        });
        
        downloadBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Printing something"); 
				PrinterJob job = PrinterJob.createPrinterJob();
				 
				 if (job != null) {
					job.showPageSetupDialog(primaryStage);
					job.showPrintDialog(primaryStage);
				    boolean success = job.printPage(root);
				    System.out.println("Actually in here");
				    if (success) {
				        job.endJob();
				    }
				 }
				 System.out.println("Done");
				 
			}
        	
        });

        ScrollPane scroll = new ScrollPane(triage);
        root = new VBox(scroll, buttBox);

        Scene scene = new Scene(root, 950, 1050);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void uploadData(VBox triage){
    	Database db = new Database();
    	db.uploadData(triage);
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