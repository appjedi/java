<<<<<<< HEAD
<<<<<<< HEAD
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Triage;

public class TriageView extends Application{
	private Triage triage;
	
	public TriageView(Triage triage) {
		this.triage = triage;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.getChildren().add(createTitle(triage.getTitle()));	
		root.getChildren().add(createDateTime(triage.getTriageDate(), triage.getTriageTime(), triage.getLocation(), triage.getPhysician()));
		root.getChildren().add(createCI(triage.getFirstname(), triage.getMiddlename(), triage.getLastname(), triage.getId(), triage.getPhone(), triage.getEmail(), triage.getAddresss()));
		
		if((triage.getInsuranceCompany() != null && !triage.getInsuranceCompany().equals("")) ||
			(triage.getInsuredName() != null && !triage.getInsuredName().equals("")) || 
			(triage.getMemberid() != null && !triage.getMemberid().equals("")) ||
			(triage.getGroupid() != null && !triage.getGroupid().equals(""))){
			root.getChildren().add(createInsurance(triage.getInsuranceCompany(), triage.getInsuredName(), triage.getMemberid(), triage.getGroupid()));
		}
		
		root.getChildren().add(createVitals(triage.getBp(), triage.getHr(), triage.getO2(), triage.getTemp(), triage.getOthervitals()));
		root.getChildren().add(createHistory(triage.getPatienthistory()));
		root.getChildren().add(createCurrMedications(triage.getMedications()));
		
		if((triage.getAllergies() != null && !triage.getAllergies().equals(""))) {
			root.getChildren().add(createAllergies(triage.getAllergies()));
		}
		
		if((triage.getComplaints() != null && !triage.getComplaints().equals(""))) {
			root.getChildren().add(createMainComplaints(triage.getComplaints()));
		}
		
		if(triage.getPain() != 0) {
			root.getChildren().add(createPain(triage.getPain()));
		}
		
		root.getChildren().add(createMedRefill(triage.getRefill()));
		root.getChildren().add(createDiagnosis(triage.getDiagnosis()));
	
		if(triage.getReferral() != null) {
			root.getChildren().add(createMentalHealthRefer(triage.getReferral()));
		}
		
		ScrollPane scroll = new ScrollPane(root);
		
		Scene scene = new Scene(root, 950, 850);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private HBox createTitle(String title) {
		HBox titleBox = new HBox();
		Label txt1 = new Label((title == null || title.equals("")? "Mission-EMR Triage Form": title + " Triage Form"));
		
		txt1.setStyle(
                "-fx-background-color: #8ec9bd; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 25px; -fx-font-size: 36"            // Border radius
        );
        titleBox.getChildren().add(txt1);
        
        formatHBox(titleBox);
	
		return titleBox;
	}
	
	private HBox createDateTime(String triageDate, String triageTime, String location, String physician) {
		VBox dateTimeBox = new VBox();
		
		Label lbl1 = new Label("Date:");
        TextField txt1 = new TextField(triageDate);
        Label lbl2 = new Label("Time:");
        TextField txt2 = new TextField(triageTime);
        Label lbl3 = new Label("City/Country:");
        TextField txt3 = new TextField(location);
        Label lbl4 = new Label("Physician:");
        TextField txt4 = new TextField(physician);
        
        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        formatText(txt4, 16);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);
		
        HBox dtBox = new HBox(lbl1, txt1, lbl2, txt2);
        HBox lpBox = new HBox(lbl3, txt3, lbl4, txt4);
        formatHBox(dtBox);
        formatHBox(lpBox);

        dateTimeBox.getChildren().addAll(dtBox,lpBox);
        
        formatVBox(dateTimeBox);
        dateTimeBox.setAlignment(Pos.CENTER_LEFT);
        dateTimeBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(dateTimeBox);
        return wrapperBox;
	}
	
	private HBox createCI(String firstname, String middlename, String lastname, int id, String phone, String email, String address) {
		VBox ciBox = new VBox();
        Label lbl1 = new Label("First Name:");
        TextField txt1 = new TextField(firstname);
        Label lbl2 = new Label("Middle Name:");
        TextField txt2 = new TextField(middlename);
        Label lbl3 = new Label("Last Name:");
        TextField txt3 = new TextField(lastname);
        
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        
        HBox nameBox = new HBox(lbl1, txt1, lbl2, txt2, lbl3, txt3);
        formatHBox(nameBox);

        Label lbl4 = new Label("Passport Number/ID:");
        TextField txt4 = new TextField("" + id);
        Label lbl5 = new Label("Phone Number:");
        TextField txt5 = new TextField(phone);
        Label lbl6 = new Label("Email:");
        TextField txt6 = new TextField(email);

        formatLabel(lbl4, 16);
        formatLabel(lbl5, 16);
        formatLabel(lbl6, 16);
        formatText(txt4, 16);
        formatText(txt5, 16);
        formatText(txt6, 16);

        HBox contactBox = new HBox(lbl4, txt4, lbl5, txt5, lbl6, txt6);
        formatHBox(contactBox);

        Label lbl7 = new Label("Patient Address:");
        TextArea txt7 = new TextArea(address);

        formatLabel(lbl7, 16);
        formatTextArea(txt7, 16);

        HBox addressBox = new HBox(lbl7, txt7);
        formatHBox(addressBox);

        ciBox.getChildren().addAll(nameBox, contactBox, addressBox);
        formatVBox(ciBox);
        ciBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(ciBox);
        return wrapperBox;
	}
	
	private HBox createInsurance(String insuranceCompany, String insuredName, String memberid, String groupid) {
		VBox insuranceBox = new VBox();
        Label lbl1 = new Label("Insurance Company:");
        TextField txt1 = new TextField(insuranceCompany);
        Label lbl2 = new Label("Insured Name:");
        TextField txt2 = new TextField(insuredName);
        Label lbl3 = new Label("Member ID:");
        TextField txt3 = new TextField(memberid);
        Label lbl4 = new Label("Group Number:");
        TextField txt4 = new TextField(groupid);

        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        formatText(txt4, 16);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);

        HBox companyBox = new HBox(lbl1, txt1);
        HBox customerBox = new HBox(lbl2, txt2, lbl3, txt3, lbl4, txt4);
        formatHBox(companyBox);
        formatHBox(customerBox);

        insuranceBox.getChildren().addAll(companyBox,customerBox);

        formatVBox(insuranceBox);
        insuranceBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(insuranceBox);
        return wrapperBox;
	}
	
	private HBox createVitals(String bp, int hr, float o2, float temp, String othervitals) {
		VBox vitalBox = new VBox();
        Label lbl1 = new Label("Blood Pressure:");
        TextField txt1 = new TextField(bp);
        Label lbl2 = new Label("Heart Rate:");
        TextField txt2 = new TextField("" + hr);
        Label lbl3 = new Label("Oxygen Saturation:");
        TextField txt3 = new TextField("" + o2);
        Label lbl4 = new Label("Temperature:");
        TextField txt4 = new TextField("" + temp);

        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        formatText(txt4, 16);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);

        HBox specifiedBox = new HBox(lbl1, txt1, lbl2, txt2, lbl3, txt3);
        formatHBox(specifiedBox);

        Label lbl5 = new Label("Other Vitals:");
        TextField txt5 = new TextField(othervitals);

        formatText(txt5, 16);
        formatLabel(lbl5, 16);

        HBox otherBox = new HBox(lbl4, txt4, lbl5, txt5);
        formatHBox(otherBox);

        vitalBox.getChildren().addAll(specifiedBox, otherBox);
        formatVBox(vitalBox);
        vitalBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(vitalBox);
        return wrapperBox;
	}
	
	private HBox createHistory(String patienthistory) {
		HBox historyBox = new HBox();

        Label lbl1 = new Label("Patient History:");
        TextArea txt1 = new TextArea(patienthistory);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        historyBox.getChildren().addAll(lbl1, txt1);

        formatHBox(historyBox);
        historyBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return historyBox;
	}
	
	private HBox createCurrMedications(String medications) {
		HBox currMedicationsBox = new HBox();

        Label lbl1 = new Label("Current Mediactions:");
        TextArea txt1 = new TextArea(medications);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        currMedicationsBox.getChildren().addAll(lbl1, txt1);

        formatHBox(currMedicationsBox);
        currMedicationsBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return currMedicationsBox;
	}
	
	private HBox createAllergies(String allergies) {
		HBox allergyBox = new HBox();

        Label lbl1 = new Label("Patient Allergies:");
        TextField txt1 = new TextField(allergies);


        formatLabel(lbl1, 16);
        formatText(txt1, 16);
        allergyBox.getChildren().addAll(lbl1, txt1);

        formatHBox(allergyBox);
        allergyBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return allergyBox;
	}
	
	private  HBox createMainComplaints(String complaints){
        HBox mcBox = new HBox();

        Label lbl1 = new Label("Main Complaints:");
        TextArea txt1 = new TextArea(complaints);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        mcBox.getChildren().addAll(lbl1, txt1);

        formatHBox(mcBox);
        mcBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return mcBox;
    }
	
	private HBox createPain(int pain){
        HBox painBox = new HBox();

        // Create components
        Label titleLabel = new Label("Pain level (0-10)");
        Label resultLabel = new Label();

        Slider painLevelSlider = new Slider(0, 10, 0);
        painLevelSlider.setShowTickMarks(true);
        painLevelSlider.setShowTickLabels(true);
        painLevelSlider.setDisable(true);
        painLevelSlider.setValue(pain);
        painLevelSlider.setOpacity(1);

        // Bind the slider value to the result label
        resultLabel.textProperty().bind(painLevelSlider.valueProperty().asString("%.0f"));

        formatLabel(titleLabel, 16);
        formatLabel(resultLabel, 16);

        painBox.getChildren().addAll(titleLabel, painLevelSlider, resultLabel);
        formatHBox(painBox);
        painBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return painBox;
    }
	
	 private HBox createMedRefill(String refill){
        HBox medRefillBox = new HBox();

        Label lbl1 = new Label("Medication Refills:");
        TextArea txt1 = new TextArea(refill);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        medRefillBox.getChildren().addAll(lbl1, txt1);

        formatHBox(medRefillBox);
        medRefillBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return medRefillBox;
    }
	 
	 private HBox createDiagnosis(String diagnosis){
        HBox diagnosisBox = new HBox();

        Label lbl1 = new Label("Describe Diagnosis:");
        TextArea txt1 = new TextArea(diagnosis);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        diagnosisBox.getChildren().addAll(lbl1, txt1);

        formatHBox(diagnosisBox);
        diagnosisBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return diagnosisBox;
    }
	 
	 private HBox createMentalHealthRefer(boolean referral){
        HBox mentalHealthReferBox = new HBox();

        Label lbl1 = new Label("Mental Health Referral:");
//	        TextArea txt1 = new TextArea();
//	        txt1.setPromptText("enter mental health referral");
        CheckBox cb = new CheckBox("Refer to Mental Health Specialist?");
        cb.setDisable(true);
        cb.setOpacity(1);
        cb.setSelected(referral);

        formatLabel(lbl1, 16);
//	        formatTextArea(txt1, 16);
        mentalHealthReferBox.getChildren().addAll(lbl1, cb);

        formatHBox(mentalHealthReferBox);
        mentalHealthReferBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return mentalHealthReferBox;
    }
	
    private void formatLabel(Label label, int fontSize){
        label.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        label.setOpacity(1.0);
        label.setFont(new Font(fontSize));
        label.setStyle("-fx-font-weight: bold;");
        label.setPadding(new Insets(5,5,5,5));
        label.setDisable(true);
        label.setMinWidth(50);
        label.setMaxWidth(200);
    }

    private void formatText(TextField text, int fontSize){
        text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        text.setDisable(true);
        text.setOpacity(1.0);
        text.setFont(new Font(fontSize));
        text.setPadding(new Insets(5,5,5,5));
        text.setMinWidth(50);
        text.setMaxWidth(400);
    }

    private void formatTextArea(TextArea text, int fontSize){
        text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        text.setFont(new Font(fontSize));
        text.setPadding(new Insets(5,5,5,5));
        text.setDisable(true);
        text.setOpacity(1.0);
        text.setWrapText(true);
        text.setVisible(true);
        text.setPrefRowCount(2);
    }

    private void formatHBox(HBox box){
        box.setAlignment(Pos.CENTER_LEFT);
        box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        box.setSpacing(5);
        box.setPrefWidth(1000);
    }

    private void formatVBox(VBox box){
        box.setAlignment(Pos.CENTER_LEFT);
        box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        box.setSpacing(5);
        box.setPrefWidth(1000);
    }
}
=======
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Triage;

public class TriageView extends Application{
	private Triage triage;
	
	public TriageView(Triage triage) {
		this.triage = triage;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.getChildren().add(createTitle(triage.getTitle()));	
		root.getChildren().add(createDateTime(triage.getTriageDate(), triage.getTriageTime(), triage.getLocation(), triage.getPhysician()));
		root.getChildren().add(createCI(triage.getFirstname(), triage.getMiddlename(), triage.getLastname(), triage.getId(), triage.getPhone(), triage.getEmail(), triage.getAddresss()));
		
		if((triage.getInsuranceCompany() != null && !triage.getInsuranceCompany().equals("")) ||
			(triage.getInsuredName() != null && !triage.getInsuredName().equals("")) || 
			(triage.getMemberid() != null && !triage.getMemberid().equals("")) ||
			(triage.getGroupid() != null && !triage.getGroupid().equals(""))){
			root.getChildren().add(createInsurance(triage.getInsuranceCompany(), triage.getInsuredName(), triage.getMemberid(), triage.getGroupid()));
		}
		
		root.getChildren().add(createVitals(triage.getBp(), triage.getHr(), triage.getO2(), triage.getTemp(), triage.getOthervitals()));
		root.getChildren().add(createHistory(triage.getPatienthistory()));
		root.getChildren().add(createCurrMedications(triage.getMedications()));
		
		if((triage.getAllergies() != null && !triage.getAllergies().equals(""))) {
			root.getChildren().add(createAllergies(triage.getAllergies()));
		}
		
		if((triage.getComplaints() != null && !triage.getComplaints().equals(""))) {
			root.getChildren().add(createMainComplaints(triage.getComplaints()));
		}
		
		if(triage.getPain() != 0) {
			root.getChildren().add(createPain(triage.getPain()));
		}
		
		root.getChildren().add(createMedRefill(triage.getRefill()));
		root.getChildren().add(createDiagnosis(triage.getDiagnosis()));
	
		if(triage.getReferral() != null) {
			root.getChildren().add(createMentalHealthRefer(triage.getReferral()));
		}
		
		ScrollPane scroll = new ScrollPane(root);
		
		Scene scene = new Scene(root, 950, 850);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private HBox createTitle(String title) {
		HBox titleBox = new HBox();
		Label txt1 = new Label((title == null || title.equals("")? "Mission-EMR Triage Form": title + " Triage Form"));
		
		txt1.setStyle(
                "-fx-background-color: #8ec9bd; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 25px; -fx-font-size: 36"            // Border radius
        );
        titleBox.getChildren().add(txt1);
        
        formatHBox(titleBox);
	
		return titleBox;
	}
	
	private HBox createDateTime(String triageDate, String triageTime, String location, String physician) {
		VBox dateTimeBox = new VBox();
		
		Label lbl1 = new Label("Date:");
        TextField txt1 = new TextField(triageDate);
        Label lbl2 = new Label("Time:");
        TextField txt2 = new TextField(triageTime);
        Label lbl3 = new Label("City/Country:");
        TextField txt3 = new TextField(location);
        Label lbl4 = new Label("Physician:");
        TextField txt4 = new TextField(physician);
        
        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        formatText(txt4, 16);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);
		
        HBox dtBox = new HBox(lbl1, txt1, lbl2, txt2);
        HBox lpBox = new HBox(lbl3, txt3, lbl4, txt4);
        formatHBox(dtBox);
        formatHBox(lpBox);

        dateTimeBox.getChildren().addAll(dtBox,lpBox);
        
        formatVBox(dateTimeBox);
        dateTimeBox.setAlignment(Pos.CENTER_LEFT);
        dateTimeBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(dateTimeBox);
        return wrapperBox;
	}
	
	private HBox createCI(String firstname, String middlename, String lastname, int id, String phone, String email, String address) {
		VBox ciBox = new VBox();
        Label lbl1 = new Label("First Name:");
        TextField txt1 = new TextField(firstname);
        Label lbl2 = new Label("Middle Name:");
        TextField txt2 = new TextField(middlename);
        Label lbl3 = new Label("Last Name:");
        TextField txt3 = new TextField(lastname);
        
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        
        HBox nameBox = new HBox(lbl1, txt1, lbl2, txt2, lbl3, txt3);
        formatHBox(nameBox);

        Label lbl4 = new Label("Passport Number/ID:");
        TextField txt4 = new TextField("" + id);
        Label lbl5 = new Label("Phone Number:");
        TextField txt5 = new TextField(phone);
        Label lbl6 = new Label("Email:");
        TextField txt6 = new TextField(email);

        formatLabel(lbl4, 16);
        formatLabel(lbl5, 16);
        formatLabel(lbl6, 16);
        formatText(txt4, 16);
        formatText(txt5, 16);
        formatText(txt6, 16);

        HBox contactBox = new HBox(lbl4, txt4, lbl5, txt5, lbl6, txt6);
        formatHBox(contactBox);

        Label lbl7 = new Label("Patient Address:");
        TextArea txt7 = new TextArea(address);

        formatLabel(lbl7, 16);
        formatTextArea(txt7, 16);

        HBox addressBox = new HBox(lbl7, txt7);
        formatHBox(addressBox);

        ciBox.getChildren().addAll(nameBox, contactBox, addressBox);
        formatVBox(ciBox);
        ciBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(ciBox);
        return wrapperBox;
	}
	
	private HBox createInsurance(String insuranceCompany, String insuredName, String memberid, String groupid) {
		VBox insuranceBox = new VBox();
        Label lbl1 = new Label("Insurance Company:");
        TextField txt1 = new TextField(insuranceCompany);
        Label lbl2 = new Label("Insured Name:");
        TextField txt2 = new TextField(insuredName);
        Label lbl3 = new Label("Member ID:");
        TextField txt3 = new TextField(memberid);
        Label lbl4 = new Label("Group Number:");
        TextField txt4 = new TextField(groupid);

        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        formatText(txt4, 16);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);

        HBox companyBox = new HBox(lbl1, txt1);
        HBox customerBox = new HBox(lbl2, txt2, lbl3, txt3, lbl4, txt4);
        formatHBox(companyBox);
        formatHBox(customerBox);

        insuranceBox.getChildren().addAll(companyBox,customerBox);

        formatVBox(insuranceBox);
        insuranceBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(insuranceBox);
        return wrapperBox;
	}
	
	private HBox createVitals(String bp, int hr, float o2, float temp, String othervitals) {
		VBox vitalBox = new VBox();
        Label lbl1 = new Label("Blood Pressure:");
        TextField txt1 = new TextField(bp);
        Label lbl2 = new Label("Heart Rate:");
        TextField txt2 = new TextField("" + hr);
        Label lbl3 = new Label("Oxygen Saturation:");
        TextField txt3 = new TextField("" + o2);
        Label lbl4 = new Label("Temperature:");
        TextField txt4 = new TextField("" + temp);

        formatText(txt1, 16);
        formatText(txt2, 16);
        formatText(txt3, 16);
        formatText(txt4, 16);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);

        HBox specifiedBox = new HBox(lbl1, txt1, lbl2, txt2, lbl3, txt3);
        formatHBox(specifiedBox);

        Label lbl5 = new Label("Other Vitals:");
        TextField txt5 = new TextField(othervitals);

        formatText(txt5, 16);
        formatLabel(lbl5, 16);

        HBox otherBox = new HBox(lbl4, txt4, lbl5, txt5);
        formatHBox(otherBox);

        vitalBox.getChildren().addAll(specifiedBox, otherBox);
        formatVBox(vitalBox);
        vitalBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(vitalBox);
        return wrapperBox;
	}
	
	private HBox createHistory(String patienthistory) {
		HBox historyBox = new HBox();

        Label lbl1 = new Label("Patient History:");
        TextArea txt1 = new TextArea(patienthistory);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        historyBox.getChildren().addAll(lbl1, txt1);

        formatHBox(historyBox);
        historyBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return historyBox;
	}
	
	private HBox createCurrMedications(String medications) {
		HBox currMedicationsBox = new HBox();

        Label lbl1 = new Label("Current Mediactions:");
        TextArea txt1 = new TextArea(medications);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        currMedicationsBox.getChildren().addAll(lbl1, txt1);

        formatHBox(currMedicationsBox);
        currMedicationsBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return currMedicationsBox;
	}
	
	private HBox createAllergies(String allergies) {
		HBox allergyBox = new HBox();

        Label lbl1 = new Label("Patient Allergies:");
        TextField txt1 = new TextField(allergies);


        formatLabel(lbl1, 16);
        formatText(txt1, 16);
        allergyBox.getChildren().addAll(lbl1, txt1);

        formatHBox(allergyBox);
        allergyBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return allergyBox;
	}
	
	private  HBox createMainComplaints(String complaints){
        HBox mcBox = new HBox();

        Label lbl1 = new Label("Main Complaints:");
        TextArea txt1 = new TextArea(complaints);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        mcBox.getChildren().addAll(lbl1, txt1);

        formatHBox(mcBox);
        mcBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return mcBox;
    }
	
	private HBox createPain(int pain){
        HBox painBox = new HBox();

        // Create components
        Label titleLabel = new Label("Pain level (0-10)");
        Label resultLabel = new Label();

        Slider painLevelSlider = new Slider(0, 10, 0);
        painLevelSlider.setShowTickMarks(true);
        painLevelSlider.setShowTickLabels(true);
        painLevelSlider.setDisable(true);
        painLevelSlider.setValue(pain);
        painLevelSlider.setOpacity(1);

        // Bind the slider value to the result label
        resultLabel.textProperty().bind(painLevelSlider.valueProperty().asString("%.0f"));

        formatLabel(titleLabel, 16);
        formatLabel(resultLabel, 16);

        painBox.getChildren().addAll(titleLabel, painLevelSlider, resultLabel);
        formatHBox(painBox);
        painBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return painBox;
    }
	
	 private HBox createMedRefill(String refill){
        HBox medRefillBox = new HBox();

        Label lbl1 = new Label("Medication Refills:");
        TextArea txt1 = new TextArea(refill);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        medRefillBox.getChildren().addAll(lbl1, txt1);

        formatHBox(medRefillBox);
        medRefillBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return medRefillBox;
    }
	 
	 private HBox createDiagnosis(String diagnosis){
        HBox diagnosisBox = new HBox();

        Label lbl1 = new Label("Describe Diagnosis:");
        TextArea txt1 = new TextArea(diagnosis);

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        diagnosisBox.getChildren().addAll(lbl1, txt1);

        formatHBox(diagnosisBox);
        diagnosisBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return diagnosisBox;
    }
	 
	 private HBox createMentalHealthRefer(boolean referral){
        HBox mentalHealthReferBox = new HBox();

        Label lbl1 = new Label("Mental Health Referral:");
//	        TextArea txt1 = new TextArea();
//	        txt1.setPromptText("enter mental health referral");
        CheckBox cb = new CheckBox("Refer to Mental Health Specialist?");
        cb.setDisable(true);
        cb.setOpacity(1);
        cb.setSelected(referral);

        formatLabel(lbl1, 16);
//	        formatTextArea(txt1, 16);
        mentalHealthReferBox.getChildren().addAll(lbl1, cb);

        formatHBox(mentalHealthReferBox);
        mentalHealthReferBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return mentalHealthReferBox;
    }
	
    private void formatLabel(Label label, int fontSize){
        label.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        label.setOpacity(1.0);
        label.setFont(new Font(fontSize));
        label.setStyle("-fx-font-weight: bold;");
        label.setPadding(new Insets(5,5,5,5));
        label.setDisable(true);
        label.setMinWidth(50);
        label.setMaxWidth(200);
    }

    private void formatText(TextField text, int fontSize){
        text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        text.setDisable(true);
        text.setOpacity(1.0);
        text.setFont(new Font(fontSize));
        text.setPadding(new Insets(5,5,5,5));
        text.setMinWidth(50);
        text.setMaxWidth(400);
    }

    private void formatTextArea(TextArea text, int fontSize){
        text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        text.setFont(new Font(fontSize));
        text.setPadding(new Insets(5,5,5,5));
        text.setDisable(true);
        text.setOpacity(1.0);
        text.setWrapText(true);
        text.setVisible(true);
        text.setPrefRowCount(2);
    }

    private void formatHBox(HBox box){
        box.setAlignment(Pos.CENTER_LEFT);
        box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        box.setSpacing(5);
        box.setPrefWidth(1000);
    }

    private void formatVBox(VBox box){
        box.setAlignment(Pos.CENTER_LEFT);
        box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        box.setSpacing(5);
        box.setPrefWidth(1000);
    }
}
<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
