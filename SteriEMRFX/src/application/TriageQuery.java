package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import models.*;
import dao.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.text.FontWeight;
import java.util.List;

public class TriageQuery extends Application {
    Database db;
    Portal portal;
    User user;
    //MainMenu mm;

    public static void main(String[] args) {
        launch(args);
    }

    public TriageQuery() {
    }

    public TriageQuery(Portal portal, User user) {
    	this.user=user;
        this.portal = portal;
    }

    private Insets INSETS = new Insets(10, 10, 10, 10);
    public static final String[] columnsPharmacist= {
    		"title",
			"triageDate",
			"triageTime",
			"location",
			"physician",			
			"insuranceCompany",
			"insuredName",
			"memberid",
			"groupid",
			"bp",
			"hr",
			"o2",
			"temp",
			"othervitals",
			"patienthistory",
			"medications",
			"allergies",
			"complaints",
			"pain",
			"refill",
			"diagnosis",
			"referral",
			"status"
    };
    
	public static final String[] columns= {
			"title",
			"triageDate",
			"triageTime",
			"location",
			"physician",
			"firstname",
			"middlename",
			"lastname",
			"id",
			"phone",
			"email",
			"addresss",
			"insuranceCompany",
			"insuredName",
			"memberid",
			"groupid",
			"bp",
			"hr",
			"o2",
			"temp",
			"othervitals",
			"patienthistory",
			"medications",
			"allergies",
			"complaints",
			"pain",
			"refill",
			"diagnosis",
			"referral",
			"status"};
    @Override
    public void start(Stage primaryStage) {
        db = new Database();
        primaryStage.setTitle("Login Form.");

        Button btn1 = createButton("Search");
        Button btn2 = createButton("Close");

       // TextField column = createStyledTextField("Enter Value");
        TextField value = createStyledTextField("Enter Value");
        TableView tableView = new TableView();
       
        BorderPane root = new BorderPane();
        VBox boxInput = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        String[] useColumns = user.getRoleId()==3?columnsPharmacist:columns;
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(useColumns));
        comboBox.setPromptText("Select Column");
        boxInput.getChildren().addAll(
                comboBox,
                createLabeledField("Value:", value), btn1,btn2);
        boxInput.getChildren().add(tableView);
        boxInput.setAlignment(Pos.CENTER);
        boxInput.setSpacing(20);

        root.setCenter(boxInput);
       
        tableView.setOnMouseClicked((MouseEvent event) -> { 
        	 //your code goes here
        	if(event.getClickCount()==2)
        	{
        		models.Triage t =(models.Triage) tableView.getSelectionModel().getSelectedItem();
        		System.out.println(t);
        		TriageView tv = new TriageView(t);
        		Stage ps = new Stage();
        		try {
					tv.start(ps);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });

        // Create an event handler for "Login" button
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String col = (String)comboBox.getValue(); // Get Username from input
                String val = value.getText(); // Get Password from input

                	List<models.Triage> list= db.queryTriage(col, val);  // To make this look better, use some interface, but that will be annoying

	                if (list != null) {
	                    
	                    for (String colVal:useColumns)
	                    {
		                    TableColumn<Triage, String> column1 = 
		                    	    new TableColumn<>(colVal);
		                    column1.setCellValueFactory(
		                            new PropertyValueFactory<>(colVal));
		                    tableView.getColumns().add(column1);

	                    }
	                	for (models.Triage t:list)
	                	{
	                		tableView.getItems().add(t);
	                		System.out.println(t);
	                	}
	                } else {
	                	tableView.getItems().clear();
	                    System.out.println("No matches found!");
	                }

           }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	portal.childCallback();
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
