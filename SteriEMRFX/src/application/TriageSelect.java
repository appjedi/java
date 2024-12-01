package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dao.*;
import models.*;

import java.util.Optional;

public class TriageSelect extends Application {
    Stage primaryStage;
    BorderPane root;
    GridPane ao;
    Database db;
    Stage ps;
    Triage triage=null;
    User user=null;
    
    public void setTriage(Triage t)
    {
    	this.triage=t;
    }
    public void setUser(User user)
    {
    	this.user=user;
    }
    public static void main(String[] args){
        launch(args);
    }

    private final Insets INSETS = new Insets(10, 10, 10, 10);

    @Override
    public void start(Stage primaryStage){
    	if(triage != null) { }

        root = new BorderPane();
        db = new Database();
        ps = primaryStage;

        Text title = new Text("Create/Use Triage Form");

        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setFill(Color.DARKBLUE);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Button existingForm = createStyledButton("Use Existing Template", Color.ORANGE);
        Button newFormBtn = createStyledButton("Create New Template", Color.PURPLE);
        Button backBtn = createStyledButton("Back", Color.GRAY);

        existingForm.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event){
               viewExistingTemplates();
           }
        });

        newFormBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                templateOptions();
            }
        });

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Portal portal = new Portal(new User());
                portal.start(primaryStage);
            }
        });

        VBox optionMenu;
        if(user.getRoleId()==2)
        	optionMenu= new VBox(title, existingForm, backBtn);
        else
        	optionMenu= new VBox(title, existingForm,newFormBtn, backBtn);
        optionMenu.setSpacing(20);
        optionMenu.setAlignment(Pos.CENTER);

        root.setCenter(optionMenu);
        Scene scene = new Scene(root, 1000, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void viewExistingTemplates(){
    	TriagePreview tp = new TriagePreview();
    	Stage ps = new Stage();
    	try {
			tp.start(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    private void templateOptions(){
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setDialogPane(new DialogPane() {
            @Override
            protected Node createDetailsButton(){
                GridPane allOptions = new GridPane();
                TextField title = new TextField();
                title.setPromptText("Enter Title");
                CheckBox contactInformation = new CheckBox();
                CheckBox insurance = new CheckBox("Insurance");
                CheckBox history = new CheckBox("History");
                CheckBox painLevel = new CheckBox("Pain Level");
                CheckBox mainComplaints = new CheckBox("Main Complaints");
                CheckBox allergies = new CheckBox("Allergies");
                CheckBox currentMedications = new CheckBox("Current Medications");
                CheckBox medicationRefill = new CheckBox("Medication Refill");
                CheckBox dateTime = new CheckBox("Date/Time");
                CheckBox diagnosis = new CheckBox("Diagnosis");
                CheckBox mentalHealthRefer = new CheckBox("Mental Health Referal");
                CheckBox vitals = new CheckBox("Vitals");

                contactInformation.setText("Contact Information");
                contactInformation.setSelected(true);
                contactInformation.setDisable(true);

                history.setSelected(true);
                history.setDisable(true);

                currentMedications.setSelected(true);
                currentMedications.setDisable(true);

                medicationRefill.setSelected(true);
                medicationRefill.setDisable(true);

                dateTime.setSelected(true);
                dateTime.setDisable(true);

                diagnosis.setSelected(true);
                diagnosis.setDisable(true);

                vitals.setDisable(true);
                vitals.setSelected(true);

                allOptions.add(title, 1, 0);
                allOptions.add(dateTime, 0, 1);
                allOptions.add(contactInformation, 1, 1);
                allOptions.add(insurance, 2, 1);
                allOptions.add(vitals, 0, 2);
                allOptions.add(history, 1,2);
                allOptions.add(currentMedications, 2, 2);
                allOptions.add(allergies, 0, 3);
                allOptions.add(mainComplaints, 1, 3);
                allOptions.add(painLevel, 2,3);
                allOptions.add(medicationRefill, 0, 4);
                allOptions.add(diagnosis, 1, 4);
                allOptions.add(mentalHealthRefer, 2, 4);

                allOptions.setAlignment(Pos.CENTER);
                allOptions.setVgap(5);
                allOptions.setHgap(10);
                allOptions.setPadding(new Insets(5,5,5,5));

                ao = allOptions;

                return allOptions;
            }
        });
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Button yes = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
        yes.setText("Confirm Template");
        Button no = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
        no.setText("Cancel");


        alert.getDialogPane().setExpandableContent(new Group());
        alert.getDialogPane().setExpanded(true);
        alert.setTitle("Select all that apply");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.YES){
            // Now enter into database
            ObservableList<Node> c = ao.getChildren();
            String title = ((TextField) c.get(0)).getText();
            if (title == "") {
            	title = null;
            }
            c.remove(0);
            boolean[] flags = new boolean[12];
            for(int i = 0; i < 12; i++){
                flags[i] = ((CheckBox) c.get(i)).isSelected();
            }
            db.addTemplate(title, flags[0], flags[1], flags[2], flags[3], flags[4], flags[5], flags[6], flags[7], flags[8], flags[9], flags[10], flags[11]);
            TriageFill tf = new TriageFill(title, flags);
            tf.start(ps);

            root.setBottom(ao);
        }
    }

    // A relic of the past
    private void startFilling(){
    	
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
