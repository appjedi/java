package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.User;
import models.Template;
import models.Triage;
public class Database {
    
	// This is for Mohamad Amjad Alzein
	/*
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/mission_emr";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Password321";
    */
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/steri_emr";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Jedi2023";
    
    String memoryURL = "jdbc:sqlite::memory:";
    private Connection conn;

    public static void main(String[] args) {
        Database db = new Database();
        try {
        	
        	List<Triage>list = db.queryTriage("email", "steri@test.com");
        	
        	for (Triage t:list)
        	{
        		System.out.println(t);
        	}
        	
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
    public User auth(String username, String password) {
        System.out.printf("Add Patient %s. %s\n", username, password);
        Database db = new Database();
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = db.prepare(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs == null || !rs.next())
                return null;
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setEmail(rs.getString("email"));
            user.setFullName(rs.getString("full_name"));
            user.setPhone(rs.getString("phone"));
            user.setSpecialty(rs.getString("specialty"));
            user.setRoleId(rs.getInt("role_id"));
            user.setStatus(rs.getInt("status"));
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
    public User getUserById(int id) {
        System.out.printf("Add Patient By Id: %d\n", id);
        Database db = new Database();
        try {
            String sql = "SELECT * FROM users WHERE user_id=?";

            PreparedStatement ps = db.prepare(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs == null || !rs.next())
                return null;
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setEmail(rs.getString("email"));
            user.setFullName(rs.getString("full_name"));
            user.setPhone(rs.getString("phone"));
            user.setSpecialty(rs.getString("specialty"));
            user.setRoleId(rs.getInt("role_id"));
            user.setStatus(rs.getInt("status"));
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
    public List<Template> getTemplates(){
    	List<Template> list = new ArrayList<>();
    	Database db = new Database();
    	try {
    		String sql = "select distinct * from triage_template;";
    		System.out.println(sql);
    		PreparedStatement ps = db.prepare(sql);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs == null || !rs.next())
    			return null;
    		
    		do {
    			Template t = new Template();
    			t.setTitle(rs.getString("title"));
    			t.setDatetime(rs.getBoolean("datetime"));
    			t.setCi(rs.getBoolean("ci"));
    			t.setInsurance(rs.getBoolean("insurance"));
    			t.setVitals(rs.getBoolean("vitals"));
    			t.setHistory(rs.getBoolean("history"));
    			t.setCurrentmeds(rs.getBoolean("currentmeds"));
    			t.setAllergies(rs.getBoolean("allergies"));
    			t.setMaincomplaint(rs.getBoolean("maincomplaint"));
    			t.setPain(rs.getBoolean("pain"));
    			t.setMedrefill(rs.getBoolean("medrefill"));
    			t.setDiagnosis(rs.getBoolean("diagnosis"));
    			t.setMhreferal(rs.getBoolean("mhreferal"));
    			t.setAssignToDoc(true);
    			list.add(t);
    		} while(rs.next());
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
            return null;
    	}
    	finally {
    		db.close();
    	}
    	
    	return list;
    }
    
    public List<Triage>queryTriage(String column, Object value)
    {
    	System.out.printf("queryTriage %s. %s\n", column, value);
        Database db = new Database();
        try {
        	 String sql = String.format("SELECT * FROM triage_form WHERE %s=?", column);
        	 System.out.println(sql);
        	 PreparedStatement ps = db.prepare(sql);
             ps.setObject(1, value);
             ResultSet rs = ps.executeQuery();
             if (rs == null || !rs.next())
                 return null;
             List<Triage>list=new ArrayList<Triage>();
             do {
        		Triage t=new Triage();
        		t.setAddresss(rs.getString("addresss")); // spelled wrong in DB
        		t.setAllergies(rs.getString("allergies"));
        		t.setBp(rs.getString("bp"));
        		t.setDiagnosis(rs.getString("diagnosis"));
        		t.setComplaints(rs.getString("complaints"));
        		t.setEmail(rs.getString("email"));
        		t.setFirstname(rs.getString("firstname"));
        		t.setGroupid(rs.getString("groupId"));
        		t.setHr(rs.getInt("hr"));
        		t.setId(rs.getInt("id"));
        		t.setInsuranceCompany(rs.getString("insurance_company"));
        		t.setInsuredName(rs.getString("insured_name"));
        		t.setLocation(rs.getString("location"));
        		t.setMedications(rs.getString("medications"));
        		t.setMiddlename(rs.getString("middlename"));
        		t.setLastname(rs.getString("lastname"));
        		t.setO2(rs.getFloat("o2"));
        		t.setOthervitals(rs.getString("othervitals"));
        		t.setPain(rs.getInt("pain"));
        		t.setPatienthistory(rs.getString("patienthistory"));
        		t.setPhone(rs.getString("phone"));
        		t.setPhysician(rs.getString("physician"));
        		t.setReferral(rs.getBoolean("referal")); // spelled wrong in DB
        		t.setTemp(rs.getFloat("temp"));
        		t.setTid(rs.getInt("tid"));
        		t.setTitle(rs.getString("title"));
        		t.setTriageDate(rs.getString("date"));
        		t.setTriageTime(rs.getString("time"));
        		
        		list.add(t);
        		
             }while(rs.next());
             return list;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
    public List<Triage>queryTriageByAssignToID(int id)
    {
    	System.out.printf("queryTriageByAssignToID %d.\n", id);
        Database db = new Database();
        try {
        	 String sql = "SELECT * FROM triage_form WHERE assignToId=?";
        	 System.out.println(sql);
        	 PreparedStatement ps = db.prepare(sql);
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
             if (rs == null || !rs.next())
                 return null;
             List<Triage>list=new ArrayList<Triage>();
             do {
        		Triage t=new Triage();
        		t.setAddresss(rs.getString("addresss")); // spelled wrong in DB
        		t.setAllergies(rs.getString("allergies"));
        		t.setBp(rs.getString("bp"));
        		t.setDiagnosis(rs.getString("diagnosis"));
        		t.setComplaints(rs.getString("complaints"));
        		t.setEmail(rs.getString("email"));
        		t.setFirstname(rs.getString("firstname"));
        		t.setLastname(rs.getString("lastname"));
        		t.setGroupid(rs.getString("groupId"));
        		t.setHr(rs.getInt("hr"));
        		t.setId(rs.getInt("id"));
        		t.setInsuranceCompany(rs.getString("insurance_company"));
        		t.setInsuredName(rs.getString("insured_name"));
        		t.setLocation(rs.getString("location"));
        		t.setMedications(rs.getString("medications"));
        		t.setMiddlename(rs.getString("middlename"));
        		t.setO2(rs.getFloat("o2"));
        		t.setOthervitals(rs.getString("othervitals"));
        		t.setPain(rs.getInt("pain"));
        		t.setPatienthistory(rs.getString("patienthistory"));
        		t.setPhone(rs.getString("phone"));
        		t.setPhysician(rs.getString("physician"));
        		t.setReferral(rs.getBoolean("referal")); // spelled wrong in DB
        		t.setTemp(rs.getFloat("temp"));
        		t.setTid(rs.getInt("tid"));
        		t.setTitle(rs.getString("title"));
        		t.setTriageDate(rs.getString("date"));
        		t.setTriageTime(rs.getString("time"));
        		
        		list.add(t);
        		
             }while(rs.next());
             return list;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
    public User forgotPassword(String email, String phone)
    {
    	Database db = new Database();
        try {
            String sql = "SELECT * FROM users WHERE email=? AND phone=?";
            

            PreparedStatement ps = db.prepare(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();
            if (rs == null || !rs.next())
                return null;
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setEmail(rs.getString("email"));
            user.setFullName(rs.getString("full_name"));
            user.setPhone(rs.getString("phone"));
            user.setSpecialty(rs.getString("specialty"));
            user.setRoleId(rs.getInt("role_id"));
            user.setStatus(rs.getInt("status"));
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
    public boolean resetPassword (int id, String password, int userType)
    {
    	Database db = new Database();
        try {
        	String sql ="UPDATE users SET password=? WHERE user_id=?";
        	
        	PreparedStatement ps = db.prepare(sql);
        	ps.setString(1, password);
        	ps.setInt(2, id);
        	ps.execute();
        	
        	return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            
            
            return false;
        } finally {
            db.close();
        }
    }
    
    //*****************************
    public boolean isEmailInUse(String userEmail) {
        try {
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            PreparedStatement ps = prepare(query);
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // If count > 0, the email is in use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    //******************************
    
    public int insertUser(User user)
    {
    	try {
        	String sql="INSERT INTO `mission_emr`.`users`(`username`,`password`,full_name, email, phone, specialty, role_id,`status`,created)VALUES(?,?,?,?,?,?,?,1,SYSDATE());";
        	
            Database db = new Database();
            PreparedStatement ps = db.prepare(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getSpecialty());
            ps.setInt(7, user.getRoleId());
            ps.executeUpdate();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    public int updateUser(int id, String email, String phone)
    {
    	try {
        	String sql="UPDATE users SET email=?, phone=? WHERE user_id=?";
        	
            Database db = new Database();
            PreparedStatement ps = db.prepare(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
           
            ps.setInt(3, id);
            ps.executeUpdate();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    public boolean deleteUser(int id) {
        try {
            String query = "DELETE FROM users WHERE user_id=?";
            PreparedStatement ps = prepare(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int addTemplate(String title, boolean dateTime, boolean ci, boolean insurance, boolean vitals, boolean history, boolean currentmeds, boolean allergies, boolean maincomplaints, boolean pain, boolean medrefill, boolean diagnosis, boolean mhreferal) {
        String insert = "insert into triage_template (title, datetime, ci, insurance, vitals, history, currentmeds, allergies, maincomplaint, pain, medrefill, diagnosis, mhreferal) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = prepare(insert);
        try {
            ps.setString(1, title);
            ps.setBoolean(2, dateTime);
            ps.setBoolean(3, ci);
            ps.setBoolean(4, insurance);
            ps.setBoolean(5, vitals);
            ps.setBoolean(6, history);
            ps.setBoolean(7, currentmeds);
            ps.setBoolean(8, allergies);
            ps.setBoolean(9, maincomplaints);
            ps.setBoolean(10, pain);
            ps.setBoolean(11, medrefill);
            ps.setBoolean(12, diagnosis);
            ps.setBoolean(13, mhreferal);
            ps.executeUpdate();
            return 1;
        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        // return -1;
    }

    // For your own sanity, please do not try to refactor this.
    public int uploadData(VBox triage) {
        String insert = "INSERT INTO triage_form ( title, date, time, location, physician, firstname, middlename, lastname, id, phone, email, addresss, insurance_company, insured_name, memberid, groupid, bp, hr, o2, temp, othervitals, patienthistory, medications, allergies, complaints, pain, refill, diagnosis, referal,assignToId) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
        PreparedStatement ps = prepare(insert, true);
        try {
            HBox tempH;
            VBox tempV;
            ObservableList<Node> allFields = triage.getChildren();
            int count = 0;
            HBox titleBox = (HBox) allFields.get(count++);
            
            if (titleBox.getId() == "title") {
            	System.out.println("YEeeEEssssSSs :)");
            	String title = ((Label) titleBox.getChildren().get(0)).getText().replace(" Triage Form", "");
            	title = (title.equals("Mission-EMR"))? null : title;
            	System.out.println("The title is " + title);
            	ps.setString(1, title);
            }
            
            // Add a few "ps.setString(X, null) for the unchecked boxes
            ps.setString(13, null);  // Insurance Company
            ps.setString(14, null);  // Insured Name
            ps.setString(15, null);  // Member ID
            ps.setString(16, null);  // Group Number
            
            ps.setString(24, null);  // Allergies
            ps.setString(25, null);	 // Main Complaints
            ps.setString(26, null);  // Pain (Like me)
            ps.setString(29, null);  // Mental Health (Definitely checked for me)
            int assignToDocId=0;
            int size = allFields.size();
            while(count < size) {
            	HBox currentBox = (HBox) allFields.get(count++);
            	switch(currentBox.getId()) {
            		case "wdatetime":
            			VBox datetime = (VBox) currentBox.getChildren().get(0);
            			ObservableList<Node> dtFields = datetime.getChildren();
            			HBox dtBox = (HBox) dtFields.get(0);
            			HBox lpBox = (HBox) dtFields.get(1);
            			TextField txt1 = (TextField) dtBox.getChildren().get(1);
            			String date = (txt1.getText() != null && !txt1.getText().equals(""))? txt1.getText(): null;
            			TextField txt2 = (TextField) dtBox.getChildren().get(3);
            			String time = (txt2.getText() != null && !txt2.getText().equals(""))? txt2.getText(): null;
            			TextField txt3 = (TextField) lpBox.getChildren().get(1);
            			String city_country = (txt3.getText() != null && !txt3.getText().equals(""))? txt3.getText(): null;
            			TextField txt4 = (TextField) lpBox.getChildren().get(3);
            			String physician = (txt4.getText() != null && !txt4.getText().equals(""))? txt4.getText(): null;
            			
            			System.out.println("Date: " + date);
            			System.out.println("Time: " + time);
            			System.out.println("City/Country: " + city_country);
            			System.out.println("Physician: " + physician);
            			
            			ps.setString(2, date);
            			ps.setString(3, time);
            			ps.setString(4, city_country);
            			ps.setString(5, physician);
            			break;
            		case "wci":
            			VBox ci = (VBox) currentBox.getChildren().get(0);
            			ObservableList<Node> ciFields = ci.getChildren();
            			HBox nameBox = (HBox) ciFields.get(0);
            			HBox contactBox = (HBox) ciFields.get(1);
            			HBox addressBox = (HBox) ciFields.get(2);
            			TextField txt5 = (TextField) nameBox.getChildren().get(1);
            			String first = (txt5.getText() != null && !txt5.getText().equals(""))? txt5.getText(): null;
            			TextField txt6 = (TextField) nameBox.getChildren().get(3);
            			String middle = (txt6.getText() != null && !txt6.getText().equals(""))? txt6.getText(): null;
            			TextField txt7 = (TextField) nameBox.getChildren().get(5);
            			String last = (txt7.getText() != null && !txt7.getText().equals(""))? txt7.getText(): null;
            			TextField txt8 = (TextField) contactBox.getChildren().get(1);
            			String passport = (txt8.getText() != null && !txt8.getText().equals(""))? txt8.getText(): null;
            			TextField txt9 = (TextField) contactBox.getChildren().get(3);
            			String phone = (txt9.getText() != null && !txt9.getText().equals(""))? txt9.getText(): null;
            			TextField txt10 = (TextField) contactBox.getChildren().get(5);
            			String email = (txt10.getText() != null && !txt10.getText().equals(""))? txt10.getText(): null;
            			TextArea txt11 = (TextArea) addressBox.getChildren().get(1);
            			String address = (txt11.getText() != null && !txt11.getText().equals(""))? txt11.getText(): null;
            			
            			System.out.println("First: " + first);
            			System.out.println("Middle: " + middle);
            			System.out.println("Last: " + last);
            			System.out.println("ID: " + passport);
            			System.out.println("Phone: " + phone);
            			System.out.println("Email: " + email);
            			System.out.println("Address: " + address);
            			
            			ps.setString(6, first);
            			ps.setString(7, middle);
            			ps.setString(8, last);
            			ps.setString(9, passport);
            			ps.setString(10, phone);
            			ps.setString(11, email);
            			ps.setString(12, address);
            			break;
            		case "winsurance":
            			VBox insurance = (VBox) currentBox.getChildren().get(0);
            			ObservableList<Node> insuranceFields = insurance.getChildren();
            			HBox companyBox = (HBox) insuranceFields.get(0);
            			HBox customerBox = (HBox) insuranceFields.get(1);
            			TextField txt12 = (TextField) companyBox.getChildren().get(1);
            			String company = (txt12.getText() != null && !txt12.getText().equals(""))? txt12.getText(): null;
            			TextField txt13 = (TextField) customerBox.getChildren().get(1);
            			String insured = (txt13.getText() != null && !txt13.getText().equals(""))? txt13.getText(): null;
            			TextField txt14 = (TextField) customerBox.getChildren().get(3);
            			String member_id = (txt14.getText() != null && !txt14.getText().equals(""))? txt14.getText(): null;
            			TextField txt15 = (TextField) customerBox.getChildren().get(5);
            			String group_number = (txt15.getText() != null && !txt15.getText().equals(""))? txt15.getText(): null;
            			
            			System.out.println("Insurance Company: " + company);
            			System.out.println("Insured Name: " + insured);
            			System.out.println("Member ID: " + member_id);
            			System.out.println("Group Number: " + group_number);
            			
            			ps.setString(13, company);
            			ps.setString(14, insured);
            			ps.setString(15, member_id);
            			ps.setString(16, group_number);
            			break;
            		case "wvital":
            			VBox vital = (VBox) currentBox.getChildren().get(0);
            			ObservableList<Node> vitalFields = vital.getChildren();
            			HBox specifiedBox = (HBox) vitalFields.get(0);
            			HBox otherBox = (HBox) vitalFields.get(1);
            			TextField txt16 = (TextField) specifiedBox.getChildren().get(1);
            			String bp = (txt16.getText() != null && !txt16.getText().equals(""))? txt16.getText(): null;
            			TextField txt17 = (TextField) specifiedBox.getChildren().get(3);
            			String hr = (txt17.getText() != null && !txt17.getText().equals(""))? txt17.getText(): null;
            			TextField txt18 = (TextField) specifiedBox.getChildren().get(5);
            			String o2 = (txt18.getText() != null && !txt18.getText().equals(""))? txt18.getText(): null;
            			TextField txt19 = (TextField) otherBox.getChildren().get(1);
            			String temp = (txt19.getText() != null && !txt19.getText().equals(""))? txt19.getText(): null;
            			TextField txt20 = (TextField) otherBox.getChildren().get(3);
            			String other = (txt20.getText() != null && !txt20.getText().equals(""))? txt20.getText(): null;
            			
            			System.out.println("Blood Pressure: " + bp);
            			System.out.println("Heart Rate: " + hr);
            			System.out.println("Oxygen Saturation: " + o2);
            			System.out.println("Temperature: " + temp);
            			System.out.println("Other Vitals: " + other);
            			
            			ps.setString(17, bp);
            			ps.setString(18, hr);
            			ps.setString(19, o2);
            			ps.setString(20, temp);
            			ps.setString(21, other);
            			break;
            		case "history":
            			HBox history = (HBox) currentBox;
            			ObservableList<Node> historyFields = history.getChildren();
            			TextArea txt21 = (TextArea) historyFields.get(1);
            			String herstory = (txt21.getText() != null && !txt21.getText().equals(""))? txt21.getText(): null;
            			
            			System.out.println("History: " + herstory);
            			
            			ps.setString(22, herstory);
            			break;
            		case "currentmed":
            			HBox currentMed = (HBox) currentBox;
            			ObservableList<Node> currentMedFields = currentMed.getChildren();
            			TextArea txt22 = (TextArea) currentMedFields.get(1);
            			String cm = (txt22.getText() != null && !txt22.getText().equals(""))? txt22.getText(): null;
            			
            			System.out.println("Current Medications: " + cm);
            			
            			ps.setString(23, cm);
            			break;
        			case "allergy":
        				HBox allergy = (HBox) currentBox;
            			ObservableList<Node> allergyFields = allergy.getChildren();
            			TextField txt23 = (TextField) allergyFields.get(1);
            			String peanuts = (txt23.getText() != null && !txt23.getText().equals(""))? txt23.getText(): null;
            			
            			System.out.println("Allergies: " + peanuts);
            			
            			ps.setString(24, peanuts);
            			break;
    				case "mc":
    					HBox mc = (HBox) currentBox;
            			ObservableList<Node> mcFields = mc.getChildren();
            			TextArea txt24 = (TextArea) mcFields.get(1);  // Eat hotchip and lie
            			String main_complaints = (txt24.getText() != null && !txt24.getText().equals(""))? txt24.getText(): null;
            			
            			System.out.println("Main Complaints: " + main_complaints);
            			
            			ps.setString(25, main_complaints);
            			break;
    				case "pain":
    					HBox pain = (HBox) currentBox;
            			ObservableList<Node> painFields = pain.getChildren();
            			Slider toTheLeft = (Slider) painFields.get(1);
            			int pain_level = (int) toTheLeft.getValue();
            			
            			System.out.println("Pain Level: " + pain_level);
            			
            			ps.setInt(26, pain_level);
            			break;
    				case "medrefill":
    					HBox medRefill = (HBox) currentBox;
            			ObservableList<Node> medRefillFields = medRefill.getChildren();
            			TextArea txt25 = (TextArea) medRefillFields.get(1);
            			String refill = (txt25.getText() != null && !txt25.getText().equals(""))? txt25.getText(): null;
            			
            			System.out.println("Medication Refill: " + refill);
            			
            			ps.setString(27, refill);
            			break;
    				case "diagnosis":
    					HBox diagnosis = (HBox) currentBox;
            			ObservableList<Node> diagnosisFields = diagnosis.getChildren();
            			TextArea txt26 = (TextArea) diagnosisFields.get(1);
            			String diag = (txt26.getText() != null && !txt26.getText().equals(""))? txt26.getText(): null;
            			
            			System.out.println("Diagnosis: " + diag);
            			
            			ps.setString(28, diag);
    					break;
    				case "mentalhealth":  // After this function, I need to checkup on mine
    					HBox mentalHealth = (HBox) currentBox;
            			ObservableList<Node> mentalHealthFields = mentalHealth.getChildren();
            			CheckBox check = (CheckBox) mentalHealthFields.get(1);
            			boolean needstogotoatherapistrightaway = check.isSelected();
            			
            			System.out.println("Mental Health Referal: " + needstogotoatherapistrightaway);
            			
            			ps.setBoolean(29, needstogotoatherapistrightaway);
            			break;
        			case "assignToDoc":
        				HBox assignToDoc = (HBox) currentBox;
            			ObservableList<Node> assignToDocFields = assignToDoc.getChildren();
            			TextField textAssignToDoc = (TextField) assignToDocFields.get(1);
            			assignToDocId = parseInt(textAssignToDoc.getText(),0);
            			
            			System.out.println("assignToDocId: " + assignToDocId);
            			
            			
            			break;
            	}
            }

            User doc=null;
            if(assignToDocId>0)
            {
            	doc=getUserById(assignToDocId);
                if(doc==null || doc.getRoleId()!=1)
                {
                 	return -3;
                }
               
            }
            ps.setInt(30, assignToDocId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = rs.next()?rs.getInt(1):0;
            
            if(id==0)
            {
            	return -1;
            }
            
            if(rs.next())
            {
            	return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }

    }
    
    public static int parseInt (String val, int alt)
    {
    	try {
    		return Integer.parseInt(val);
    	}catch (Exception ex)
    	{
    		return alt;
    	}
    }
    public Connection getConnection() {
        try {
            if (conn != null)
                return conn;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int execute(String sql) {
        try {
            if (conn == null)
                getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            return statement.executeUpdate();
        } catch (SQLException e) {

        } finally {
            close();
        }

        return -1;
    }

    public int execute(String sql, Object... values) {
        try {
            if (conn == null)
                getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            int row = 1;
            for (Object obj : values) {
                statement.setObject(row++, obj);
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                return newId;
            }
        } catch (SQLException e) {

        } finally {
            close();
        }

        return -1;
    }

    public PreparedStatement prepare(String sql, boolean returnId) {
        try {
            if (conn == null)
                getConnection();

            if(returnId)
            {
            	return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }else {
            	return conn.prepareStatement(sql);
            }
            


        } catch (SQLException e) {

        } finally {
            // close();
        }

        return null;
    }
    public PreparedStatement prepare(String sql) {
        try {
            if (conn == null)
                getConnection();

          
            return conn.prepareStatement(sql);
                        


        } catch (SQLException e) {

        } finally {
            // close();
        }

        return null;
    }
    public ResultSet getResultSet(String query, Object... values) {
        ResultSet rs = null;
        try {
            if (conn == null)
                getConnection();

            PreparedStatement statement = conn.prepareStatement(query);
            int row = 1;
            if (values != null && values.length > 0) {
                for (Object obj : values) {
                    statement.setObject(row++, obj);
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {

        } finally {
            // close();
        }
        return rs;
    }

    public boolean close() {
        try {
            if (conn == null)
                return true;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
