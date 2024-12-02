<<<<<<< HEAD
package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.Database;
import models.Template;

public class TriagePreview extends Application{
	Stage primaryStage;
	Database db;
	private int index;
	private int size;
	private VBox template;
	private List<Template> templates;
	private BorderPane root;

	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		db = new Database();
		
		Button left = createButton("Previous", "#9deded");
		Button right = createButton("Next", "#9deded");
		
		left.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("Sliiiiiiide to the left");
				goLeft();
			}
		});
		
		right.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("Sliiiiiiide to the right");
				goRight();
			}
		});
		
		template = new VBox();
		root = new BorderPane();
		root.setLeft(left);
		root.setRight(right);
		root.setCenter(template);
		
		templates = db.getTemplates();

		if(templates != null) {
			index = 0;
			size = templates.size();
			startShuffling();
		}
		
		Button backButton = createButton("Back", "gray");
		Button confirmButton = createButton("Confirm", "#4ade94");
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TriageFill tf = new TriageFill(templates.get(index).getTitle(), templates.get(index).flags());
				tf.start(primaryStage);
			}
		});
		
		HBox bottomtext = new HBox(backButton, confirmButton);
		bottomtext.setSpacing(5);
		bottomtext.setAlignment(Pos.BASELINE_CENTER);
		root.setBottom(bottomtext);
		
		Scene scene = new Scene(root, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Button createButton(String text, String color) {
		Button butt = new Button(text);
		butt.setStyle("-fx-border-width: 1px; -fx-border-style: solid; -fx-font-size: 12; -fx-text-fill: black; -fx-background-color: " + color + ";");
		butt.setMinWidth(125);
		butt.setMaxWidth(125);
		butt.setMinHeight(40);
		butt.setMaxHeight(40);
		BorderPane.setMargin(butt, new Insets(10, 10, 10, 10));
		BorderPane.setAlignment(butt,  Pos.CENTER);
		
		return butt;
	}
	
	
	private void startShuffling() {
		template = templates.get(index).createVBox();
		root.setCenter(template);
	}
	
	private void goLeft() {
		if(index == 0) {
			index = size - 1;
		}
		else {
			index--;
		}
		startShuffling();
	}
	
	private void goRight() {
		if(index == size - 1) {
			index = 0;
		}
		else {
			index++;
		}
		startShuffling();
	}
}
=======
package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.Database;
import models.Template;

public class TriagePreview extends Application{
	Stage primaryStage;
	Database db;
	private int index;
	private int size;
	private VBox template;
	private List<Template> templates;
	private BorderPane root;

	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		db = new Database();
		
		Button left = createButton("Previous", "#9deded");
		Button right = createButton("Next", "#9deded");
		
		left.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("Sliiiiiiide to the left");
				goLeft();
			}
		});
		
		right.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("Sliiiiiiide to the right");
				goRight();
			}
		});
		
		template = new VBox();
		root = new BorderPane();
		root.setLeft(left);
		root.setRight(right);
		root.setCenter(template);
		
		templates = db.getTemplates();

		if(templates != null) {
			index = 0;
			size = templates.size();
			startShuffling();
		}
		
		Button backButton = createButton("Back", "gray");
		Button confirmButton = createButton("Confirm", "#4ade94");
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TriageFill tf = new TriageFill(templates.get(index).getTitle(), templates.get(index).flags());
				tf.start(primaryStage);
			}
		});
		
		HBox bottomtext = new HBox(backButton, confirmButton);
		bottomtext.setSpacing(5);
		bottomtext.setAlignment(Pos.BASELINE_CENTER);
		root.setBottom(bottomtext);
		
		Scene scene = new Scene(root, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Button createButton(String text, String color) {
		Button butt = new Button(text);
		butt.setStyle("-fx-border-width: 1px; -fx-border-style: solid; -fx-font-size: 12; -fx-text-fill: black; -fx-background-color: " + color + ";");
		butt.setMinWidth(125);
		butt.setMaxWidth(125);
		butt.setMinHeight(40);
		butt.setMaxHeight(40);
		BorderPane.setMargin(butt, new Insets(10, 10, 10, 10));
		BorderPane.setAlignment(butt,  Pos.CENTER);
		
		return butt;
	}
	
	
	private void startShuffling() {
		template = templates.get(index).createVBox();
		root.setCenter(template);
	}
	
	private void goLeft() {
		if(index == 0) {
			index = size - 1;
		}
		else {
			index--;
		}
		startShuffling();
	}
	
	private void goRight() {
		if(index == size - 1) {
			index = 0;
		}
		else {
			index++;
		}
		startShuffling();
	}
}
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
