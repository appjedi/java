<<<<<<< HEAD
<<<<<<< HEAD
package models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Template {
	private String title;
	private boolean datetime;
	private boolean ci;
	private boolean insurance;
	private boolean vitals;
	private boolean history;
	private boolean currentmeds;
	private boolean allergies;
	private boolean maincomplaint;
	private boolean pain;
	private boolean medrefill;
	private boolean diagnosis;
	private boolean mhreferal;
	private boolean assignToDoc;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean getDatetime() {
		return datetime;
	}
	public void setDatetime(boolean datetime) {
		this.datetime = datetime;
	}
	
	public boolean getCi() {
		return ci;
	}
	public void setCi(boolean ci) {
		this.ci = ci;
	}
	
	public boolean getInsurance() {
		return insurance;
	}
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	public boolean getVitals() {
		return vitals;
	}
	public void setVitals(boolean vitals) {
		this.vitals = vitals;
	}
	
	public boolean getHistory() {
		return history;
	}
	public void setHistory(boolean history) {
		this.history = history;
	}
	
	public boolean getCurrentmeds() {
		return currentmeds;
	}
	public void setCurrentmeds(boolean currentmeds) {
		this.currentmeds = currentmeds;
	}
	
	public boolean getAllergies() {
		return allergies;
	}
	public void setAllergies(boolean allergies) {
		this.allergies = allergies;
	}
	
	public boolean getMaincomplaint() {
		return maincomplaint;
	}
	public void setMaincomplaint(boolean maincomplaint) {
		this.maincomplaint = maincomplaint;
	}
	
	public boolean getPain() {
		return pain;
	}
	public void setPain(boolean pain) {
		this.pain = pain;
	}
	
	public boolean getMedrefill() {
		return medrefill;
	}
	public void setMedrefill(boolean medrefill) {
		this.medrefill = medrefill;
	}
	
	public boolean getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(boolean diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	public boolean getMhreferal() {
		return mhreferal;
	}
	public void setMhreferal(boolean mhreferal) {
		this.mhreferal = mhreferal;
	}
	
	public boolean isAssignToDoc() {
		return assignToDoc;
	}
	public void setAssignToDoc(boolean assignToDoc) {
		this.assignToDoc = assignToDoc;
	}
	public boolean[] flags() {
		boolean[] flags = new boolean[13];
		flags[0] = datetime;
		flags[1] = ci;
		flags[2] = insurance;
		flags[3] = vitals;
		flags[4] = history;
		flags[5] = currentmeds;
		flags[6] = allergies;
		flags[7] = maincomplaint;
		flags[8] = pain;
		flags[9] = medrefill;
		flags[10] = diagnosis;
		flags[11] = mhreferal;
		flags[12] = assignToDoc;
		return flags;
	}
	
	public VBox createVBox() {
		VBox template = new VBox();
		boolean[] flags = flags();
		
		TextField titleField = new TextField((title.equals(""))? "\"Mission-EMR Triage Form\"": "\"" + title + " Triage Form" + "\"");
		formatText(titleField);
		template.getChildren().add(titleField);
		
		for(int i = 0; i < flags.length; i++) {
			TextField current = new TextField("oogly boogly");
			if(flags[i]) {
				switch(i) {
					case 0:
						current.setText("Date/Time");
						break;
					case 1:
						current.setText("Contact Information");
						break;
					case 2:
						current.setText("Insurance");
						break;
					case 3:
						current.setText("Vitals");
						break;
					case 4:
						current.setText("Patient History");
						break;
					case 5:
						current.setText("Current Medications");
						break;
					case 6:
						current.setText("Allergies");
						break;
					case 7:
						current.setText("Main Complaint");
						break;
					case 8:
						current.setText("Pain Level");
						break;
					case 9:
						current.setText("Medication Refill");
						break;
					case 10:
						current.setText("Diagnosis");
						break;
					case 11:
						current.setText("Mental Heatlh Referal");
						break;
					case 12:
						current.setText("Assign to physician");
						break;
				}
			}
			if(!current.getText().equals("oogly boogly")) {
				formatText(current);
				template.getChildren().add(current);
			}
		}
		
		formatVBox(template);
		return template;
	}
	
	private void formatText(TextField text) {
		text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
		text.setDisable(true);
		text.setOpacity(1.0);
		text.setFont(new Font(16));
		text.setPadding(new Insets(5,5,5,5));
		text.setMinWidth(50);
		text.setMaxWidth(300);
		text.setAlignment(Pos.CENTER);
		text.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, new BorderWidths(0.5))));
	}
	
	private void formatVBox(VBox box) {
		box.setAlignment(Pos.CENTER);
		box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		box.setSpacing(5);
		box.setPrefWidth(350);
	}
}
=======
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
package models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Template {
	private String title;
	private boolean datetime;
	private boolean ci;
	private boolean insurance;
	private boolean vitals;
	private boolean history;
	private boolean currentmeds;
	private boolean allergies;
	private boolean maincomplaint;
	private boolean pain;
	private boolean medrefill;
	private boolean diagnosis;
	private boolean mhreferal;
	private boolean assignToDoc;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean getDatetime() {
		return datetime;
	}
	public void setDatetime(boolean datetime) {
		this.datetime = datetime;
	}
	
	public boolean getCi() {
		return ci;
	}
	public void setCi(boolean ci) {
		this.ci = ci;
	}
	
	public boolean getInsurance() {
		return insurance;
	}
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	public boolean getVitals() {
		return vitals;
	}
	public void setVitals(boolean vitals) {
		this.vitals = vitals;
	}
	
	public boolean getHistory() {
		return history;
	}
	public void setHistory(boolean history) {
		this.history = history;
	}
	
	public boolean getCurrentmeds() {
		return currentmeds;
	}
	public void setCurrentmeds(boolean currentmeds) {
		this.currentmeds = currentmeds;
	}
	
	public boolean getAllergies() {
		return allergies;
	}
	public void setAllergies(boolean allergies) {
		this.allergies = allergies;
	}
	
	public boolean getMaincomplaint() {
		return maincomplaint;
	}
	public void setMaincomplaint(boolean maincomplaint) {
		this.maincomplaint = maincomplaint;
	}
	
	public boolean getPain() {
		return pain;
	}
	public void setPain(boolean pain) {
		this.pain = pain;
	}
	
	public boolean getMedrefill() {
		return medrefill;
	}
	public void setMedrefill(boolean medrefill) {
		this.medrefill = medrefill;
	}
	
	public boolean getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(boolean diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	public boolean getMhreferal() {
		return mhreferal;
	}
	public void setMhreferal(boolean mhreferal) {
		this.mhreferal = mhreferal;
	}
	
	public boolean isAssignToDoc() {
		return assignToDoc;
	}
	public void setAssignToDoc(boolean assignToDoc) {
		this.assignToDoc = assignToDoc;
	}
	public boolean[] flags() {
		boolean[] flags = new boolean[13];
		flags[0] = datetime;
		flags[1] = ci;
		flags[2] = insurance;
		flags[3] = vitals;
		flags[4] = history;
		flags[5] = currentmeds;
		flags[6] = allergies;
		flags[7] = maincomplaint;
		flags[8] = pain;
		flags[9] = medrefill;
		flags[10] = diagnosis;
		flags[11] = mhreferal;
		flags[12] = assignToDoc;
		return flags;
	}
	
	public VBox createVBox() {
		VBox template = new VBox();
		boolean[] flags = flags();
		
		TextField titleField = new TextField((title.equals(""))? "\"Mission-EMR Triage Form\"": "\"" + title + " Triage Form" + "\"");
		formatText(titleField);
		template.getChildren().add(titleField);
		
		for(int i = 0; i < flags.length; i++) {
			TextField current = new TextField("oogly boogly");
			if(flags[i]) {
				switch(i) {
					case 0:
						current.setText("Date/Time");
						break;
					case 1:
						current.setText("Contact Information");
						break;
					case 2:
						current.setText("Insurance");
						break;
					case 3:
						current.setText("Vitals");
						break;
					case 4:
						current.setText("Patient History");
						break;
					case 5:
						current.setText("Current Medications");
						break;
					case 6:
						current.setText("Allergies");
						break;
					case 7:
						current.setText("Main Complaint");
						break;
					case 8:
						current.setText("Pain Level");
						break;
					case 9:
						current.setText("Medication Refill");
						break;
					case 10:
						current.setText("Diagnosis");
						break;
					case 11:
						current.setText("Mental Heatlh Referal");
						break;
					case 12:
						current.setText("Assign to physician");
						break;
				}
			}
			if(!current.getText().equals("oogly boogly")) {
				formatText(current);
				template.getChildren().add(current);
			}
		}
		
		formatVBox(template);
		return template;
	}
	
	private void formatText(TextField text) {
		text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
		text.setDisable(true);
		text.setOpacity(1.0);
		text.setFont(new Font(16));
		text.setPadding(new Insets(5,5,5,5));
		text.setMinWidth(50);
		text.setMaxWidth(300);
		text.setAlignment(Pos.CENTER);
		text.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, null, new BorderWidths(0.5))));
	}
	
	private void formatVBox(VBox box) {
		box.setAlignment(Pos.CENTER);
		box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		box.setSpacing(5);
		box.setPrefWidth(350);
	}
}
<<<<<<< HEAD
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
=======
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
