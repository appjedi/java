<<<<<<< HEAD
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.User;
public class Database {


	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/mission_emr"; //userdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password123";
    String memoryURL = "jdbc:sqlite::memory:";
//    private String jdbcUsername = "root";
 //   private String jdbcPassword = "password123";
    private Connection conn;

    public static void main(String[] args) {
        Database db = new Database();
        try {
        	
        	User u = db.auth("testerb", "Test1234");
        	System.out.println(u);
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

    /*
    public int uploadData(VBox triage) {
        String insert = "INSERT INTO triage_form ( title, date, time, location, physician, firstname, middlename, id, phone, email, addresss, insurance_company, insured_name, memberid, groupid, bp, hr, o2, temp, othervitals, patienthistory, medications, allergies, complaints, pain, refill, diagnosis, referal) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = prepare(insert);
        try {
            HBox tempH;
            VBox tempV;
            Collection<Node> allChildren = triage.getChildren();
            ps.executeUpdate();
            return 1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }

    }
    */

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

    public PreparedStatement prepare(String sql) {
        try {
            if (conn == null)
                getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            return statement;
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
=======
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.User;
public class Database {


	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/mission_emr"; //userdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password123";
    String memoryURL = "jdbc:sqlite::memory:";
//    private String jdbcUsername = "root";
 //   private String jdbcPassword = "password123";
    private Connection conn;

    public static void main(String[] args) {
        Database db = new Database();
        try {
        	
        	User u = db.auth("testerb", "Test1234");
        	System.out.println(u);
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

    /*
    public int uploadData(VBox triage) {
        String insert = "INSERT INTO triage_form ( title, date, time, location, physician, firstname, middlename, id, phone, email, addresss, insurance_company, insured_name, memberid, groupid, bp, hr, o2, temp, othervitals, patienthistory, medications, allergies, complaints, pain, refill, diagnosis, referal) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = prepare(insert);
        try {
            HBox tempH;
            VBox tempV;
            Collection<Node> allChildren = triage.getChildren();
            ps.executeUpdate();
            return 1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }

    }
    */

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

    public PreparedStatement prepare(String sql) {
        try {
            if (conn == null)
                getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            return statement;
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
>>>>>>> 913514eccee8fc89cbd1377c0d9679bc6724ff54
