package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.Objects;

public class Triage {
    public static VBox createForm(String title, boolean[] flags){
        VBox triage = new VBox();
        triage.getChildren().add(createTitle(title));

        for(int i = 0; i < 13; i++){
            if(flags[i]) {
                switch (i) {
                    case 0:
                        triage.getChildren().add(createDateTime(flags[i]));
                        break;
                    case 1:
                        triage.getChildren().add(createCI(flags[i]));
                        break;
                    case 2:
                        triage.getChildren().add(createInsurance(flags[i]));
                        break;
                    case 3:
                        triage.getChildren().add(createVitals(flags[i]));
                        break;
                    case 4:
                        triage.getChildren().add(createHistory(flags[i]));
                        break;
                    case 5:
                        triage.getChildren().add(createCurrMedications(flags[i]));
                        break;
                    case 6:
                        triage.getChildren().add(createAllergies(flags[i]));
                        break;
                    case 7:
                        triage.getChildren().add(createMainComplaints(flags[i]));
                        break;
                    case 8:
                        triage.getChildren().add(createPain(flags[i]));  // Ouch
                        break;
                    case 9:
                        triage.getChildren().add(createMedRefill(flags[i]));
                        break;
                    case 10:
                        triage.getChildren().add(createDiagnosis(flags[i]));
                        break;
                    case 11:
                        triage.getChildren().add(createMentalHealthRefer(flags[i]));
                        break;
                    case 12:
                        triage.getChildren().add(createAssignToPhysician(flags[i]));
                        break;                        
                }
            }
        }

        return triage;
    }

    private static HBox createTitle(String title){
        HBox titleBox = new HBox();
        Label txt1 = new Label();

        if (Objects.equals(title, "")){
            txt1.setText("Mission-EMR Triage Form");
        }
        else {
            txt1.setText(title + " Triage Form");
        }
        
        txt1.setStyle(
                "-fx-background-color: #8ec9bd; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 25px; -fx-font-size: 36"            // Border radius
        );
        titleBox.getChildren().add(txt1);

        formatHBox(titleBox);
        titleBox.setId("title");
        return titleBox;
    }

    private static HBox createDateTime(boolean flag){
        VBox dateTimeBox = new VBox();

        Label lbl1 = new Label("Date:");
        TextField txt1 = new TextField();
        txt1.setPromptText("mm/dd/yyyy");
        Label lbl2 = new Label("Time:");
        TextField txt2 = new TextField();
        txt2.setPromptText("enter time");
        Label lbl3 = new Label("City/Country:");
        TextField txt3 = new TextField();
        txt3.setPromptText("enter location");
        Label lbl4 = new Label("Physician:");
        TextField txt4 = new TextField();
        txt4.setPromptText("enter physician name");

        formatText(txt1, 16, false);
        formatText(txt2, 16, false);
        formatText(txt3, 16, false);
        formatText(txt4, 16, false);
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
        dateTimeBox.setId("datetime");
        dateTimeBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(dateTimeBox);
        wrapperBox.setId("wdatetime");
        return wrapperBox;
    }

    private static HBox createCI(boolean flag){
        VBox ciBox = new VBox();
        Label lbl1 = new Label("First Name:");
        TextField txt1 = new TextField();
        txt1.setPromptText("enter first name");
        Label lbl2 = new Label("Middle Name:");
        TextField txt2 = new TextField();
        txt2.setPromptText("enter middle name");
        Label lbl3 = new Label("Last Name:");
        TextField txt3 = new TextField();
        txt3.setPromptText("enter last name");

        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatText(txt1, 16, false);
        formatText(txt2, 16, false);
        formatText(txt3, 16, false);

        HBox nameBox = new HBox(lbl1, txt1, lbl2, txt2, lbl3, txt3);
        formatHBox(nameBox);

        Label lbl4 = new Label("Passport Number/ID:");
        TextField txt4 = new TextField();
        txt4.setPromptText("enter ID");
        Label lbl5 = new Label("Phone Number:");
        TextField txt5 = new TextField();
        txt5.setPromptText("enter phone number");
        Label lbl6 = new Label("Email:");
        TextField txt6 = new TextField();
        txt6.setPromptText("enter email");

        formatLabel(lbl4, 16);
        formatLabel(lbl5, 16);
        formatLabel(lbl6, 16);
        formatText(txt4,16, false);
        formatText(txt5, 16, false);
        formatText(txt6, 16, false);

        HBox contactBox = new HBox(lbl4, txt4, lbl5, txt5, lbl6, txt6);
        formatHBox(contactBox);

        Label lbl7 = new Label("Patient Address:");
        TextArea txt7 = new TextArea();
        txt7.setPromptText("enter address");

        formatLabel(lbl7, 16);
        formatTextArea(txt7, 16);

        HBox addressBox = new HBox(lbl7, txt7);
        formatHBox(addressBox);

        ciBox.getChildren().addAll(nameBox, contactBox, addressBox);
        formatVBox(ciBox);
        ciBox.setId("ci");
        ciBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(ciBox);
        wrapperBox.setId("wci");
        return wrapperBox;
    }

    private static HBox createInsurance(boolean flag){
        VBox insuranceBox = new VBox();
        Label lbl1 = new Label("Insurance Company:");
        TextField txt1 = new TextField();
        txt1.setPromptText("enter company name");
        Label lbl2 = new Label("Insured Name:");
        TextField txt2 = new TextField();
        txt2.setPromptText("enter insured name");
        Label lbl3 = new Label("Member ID:");
        TextField txt3 = new TextField();
        txt3.setPromptText("enter member ID");
        Label lbl4 = new Label("Group Number:");
        TextField txt4 = new TextField();
        txt4.setPromptText("enter group number");

        formatText(txt1, 16, false);
        formatText(txt2, 16, false);
        formatText(txt3, 16, false);
        formatText(txt4, 16, false);
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
        insuranceBox.setId("insurance");
        insuranceBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(insuranceBox);
        wrapperBox.setId("winsurance");
        return wrapperBox;
    }

    private static HBox createVitals(boolean flag){
        VBox vitalBox = new VBox();
        Label lbl1 = new Label("Blood Pressure:");
        TextField txt1 = new TextField();
        txt1.setPromptText("enter BP");
        Label lbl2 = new Label("Heart Rate:");
        TextField txt2 = new TextField();
        txt2.setPromptText("enter HR");
        Label lbl3 = new Label("Oxygen Saturation:");
        TextField txt3 = new TextField();
        txt3.setPromptText("enter O2");
        Label lbl4 = new Label("Temperature:");
        TextField txt4 = new TextField();
        txt4.setPromptText("enter temperature");

        formatText(txt1, 16, false);
        formatText(txt2, 16, false);
        formatText(txt3, 16, false);
        formatText(txt4, 16, false);
        formatLabel(lbl1, 16);
        formatLabel(lbl2, 16);
        formatLabel(lbl3, 16);
        formatLabel(lbl4, 16);

        HBox specifiedBox = new HBox(lbl1, txt1, lbl2, txt2, lbl3, txt3);
        formatHBox(specifiedBox);

        Label lbl5 = new Label("Other Vitals:");
        TextField txt5 = new TextField();

        formatText(txt5, 16, false);
        formatLabel(lbl5, 16);

        HBox otherBox = new HBox(lbl4, txt4, lbl5, txt5);
        formatHBox(otherBox);

        vitalBox.getChildren().addAll(specifiedBox, otherBox);
        formatVBox(vitalBox);
        vitalBox.setId("vital");
        vitalBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox wrapperBox = new HBox(vitalBox);
        wrapperBox.setId("wvital");
        return wrapperBox;
    }

    private static HBox createHistory(boolean flag){
        HBox historyBox = new HBox();

        Label lbl1 = new Label("Patient History:");
        TextArea txt1 = new TextArea();
        txt1.setPromptText("enter history");

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        historyBox.getChildren().addAll(lbl1, txt1);

        formatHBox(historyBox);
        historyBox.setId("history");
        historyBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return historyBox;
    }

    private static HBox createCurrMedications(boolean flag){
        HBox currMedicationsBox = new HBox();

        Label lbl1 = new Label("Current Mediactions:");
        TextArea txt1 = new TextArea();
        txt1.setPromptText("enter current medications");

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        currMedicationsBox.getChildren().addAll(lbl1, txt1);

        formatHBox(currMedicationsBox);
        currMedicationsBox.setId("currentmed");
        currMedicationsBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return currMedicationsBox;
    }

    private static HBox createAllergies(boolean flag){
        HBox allergyBox = new HBox();

        Label lbl1 = new Label("Patient Allergies:");
        TextField txt1 = new TextField();
        txt1.setPromptText("enter allergies");


        formatLabel(lbl1, 16);
        formatText(txt1, 16, false);
        allergyBox.getChildren().addAll(lbl1, txt1);

        formatHBox(allergyBox);
        allergyBox.setId("allergy");
        allergyBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return allergyBox;
    }

    private static HBox createMainComplaints(boolean flag){
        HBox mcBox = new HBox();

        Label lbl1 = new Label("Main Complaints:");
        TextArea txt1 = new TextArea();
        txt1.setPromptText("enter main complaints");

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        mcBox.getChildren().addAll(lbl1, txt1);

        formatHBox(mcBox);
        mcBox.setId("mc");
        mcBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return mcBox;
    }

    private static HBox createPain(boolean flag){
        HBox painBox = new HBox();

        // Create components
        Label titleLabel = new Label("Pain level (0-10)");
        Label resultLabel = new Label();

        Slider painLevelSlider = new Slider(0, 10, 0);
        painLevelSlider.setShowTickMarks(true);
        painLevelSlider.setShowTickLabels(true);

        // Bind the slider value to the result label
        resultLabel.textProperty().bind(painLevelSlider.valueProperty().asString("%.0f"));

        formatLabel(titleLabel, 16);
        formatLabel(resultLabel, 16);

        painBox.getChildren().addAll(titleLabel, painLevelSlider, resultLabel);
        formatHBox(painBox);
        painBox.setId("pain");
        painBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return painBox;
    }

    private static HBox createMedRefill(boolean flag){
        HBox medRefillBox = new HBox();

        Label lbl1 = new Label("Medication Refills:");
        TextArea txt1 = new TextArea();
        txt1.setPromptText("enter medication refills");

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        medRefillBox.getChildren().addAll(lbl1, txt1);

        formatHBox(medRefillBox);
        medRefillBox.setId("medrefill");
        medRefillBox.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, Insets.EMPTY)));
        return medRefillBox;
    }

    private static HBox createDiagnosis(boolean flag){
        HBox diagnosisBox = new HBox();

        Label lbl1 = new Label("Describe Diagnosis:");
        TextArea txt1 = new TextArea();
        txt1.setPromptText("describe diagnosis");

        formatLabel(lbl1, 16);
        formatTextArea(txt1, 16);
        diagnosisBox.getChildren().addAll(lbl1, txt1);

        formatHBox(diagnosisBox);
        diagnosisBox.setId("diagnosis");
        diagnosisBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        return diagnosisBox;
    }

    private static HBox createMentalHealthRefer(boolean flag){
        HBox mentalHealthReferBox = new HBox();

        Label lbl1 = new Label("Mental Health Referral:");
//        TextArea txt1 = new TextArea();
//        txt1.setPromptText("enter mental health referral");
        CheckBox cb = new CheckBox("Refer to Mental Health Specialist?");

        formatLabel(lbl1, 16);
//        formatTextArea(txt1, 16);
        mentalHealthReferBox.getChildren().addAll(lbl1, cb);

        formatHBox(mentalHealthReferBox);
        mentalHealthReferBox.setId("mentalhealth");
        mentalHealthReferBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return mentalHealthReferBox;
    }
    private static HBox createAssignToPhysician(boolean flag){
        HBox assignBox = new HBox();

        Label lbl1 = new Label("Assign To Physician:");
        TextField txt1 = new TextField();
        txt1.setPromptText("enter physician id");


        formatLabel(lbl1, 16);
        formatText(txt1, 16, false);
        assignBox.getChildren().addAll(lbl1, txt1);

        formatHBox(assignBox);
        assignBox.setId("assignToDoc");
        assignBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        return assignBox;
    }
    private static void formatLabel(Label label, int fontSize){
        label.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        label.setOpacity(1.0);
        label.setFont(new Font(fontSize));
        label.setStyle("-fx-font-weight: bold;");
        label.setPadding(new Insets(5,5,5,5));
        label.setDisable(true);
        label.setMinWidth(50);
        label.setMaxWidth(200);
    }

    private static void formatText(TextField text, int fontSize, boolean disable){
        text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        text.setDisable(disable);
        text.setOpacity(1.0);
        text.setFont(new Font(fontSize));
        text.setPadding(new Insets(5,5,5,5));
        text.setMinWidth(50);
        text.setMaxWidth(400);
    }

    private static void formatTextArea(TextArea text, int fontSize){
        text.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0), null, null)));
        text.setFont(new Font(fontSize));
        text.setPadding(new Insets(5,5,5,5));
        text.setWrapText(true);
        text.setVisible(true);
        text.setPrefRowCount(2);
    }

    private static void formatHBox(HBox box){
        box.setAlignment(Pos.CENTER_LEFT);
        box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        box.setSpacing(5);
        box.setPrefWidth(1000);
    }

    private static void formatVBox(VBox box){
        box.setAlignment(Pos.CENTER_LEFT);
        box.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        box.setSpacing(5);
        box.setPrefWidth(1000);
    }

}
