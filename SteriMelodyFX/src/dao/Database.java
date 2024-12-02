package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import models.Profile;
public class Database {
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/steri_melody"; //userdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Jedi2023";
    String memoryURL = "jdbc:sqlite::memory:";
//    private String jdbcUsername = "root";
 //   private String jdbcPassword = "password123";
    private Connection conn;

    public static void main(String[] args) {
        Database db = new Database();
        try {
        	//Profile p=db.forgotPassword("bob@test.com", "555-1212");
    		//System.out.println(p);
        	db.resetPassword(1,"Test2024");

        	//Profile p=db.auth("testerb", "Test1234");
        	/*
        	//System.out.println(p);
        	Profile user=new Profile();
            user.setEmail("test2@test.com");
            user.setName("DB Test 2");
            user.setPassword("Test1234");
            user.setPhone("321-2024");
            user.setRoleId(2);
            user.setGoals("DB Testing...");
            user.setStatus(1);
            user.setUsername("test2");
           // int rv=db.createProfile(user);
        	//int rv=db.createProfile("Olivia", "I want to learn as much as I can","/Users/roberttimlin/Documents/Bob2024.jpg");
        	//Profile p = db.getProfile(1);
        	/*
        	List<Profile>list=db.getAllProfile();
        	for(Profile p:list)
        		System.out.println(p);
        		*/
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
    public Profile auth(String username, String password) {
        System.out.printf("Add Patient %s. %s\n", username, password);
        Database db = new Database();
        try {
            String sql = "SELECT * FROM profiles WHERE username=? AND password=?";

            PreparedStatement ps = db.prepare(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs == null || !rs.next())
                return null;
            Profile user = new Profile();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setImage(rs.getString("image"));
            user.setGoals(rs.getString("goals"));
            user.setHighestRank(rs.getInt("highestRank"));
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
    public Profile forgotPassword(String email, String phone)
    {
    	Database db = new Database();
        try {
            String sql = "SELECT * FROM profiles WHERE email=? AND phone=?";
            

            PreparedStatement ps = db.prepare(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();
            if (rs == null || !rs.next())
                return null;
            Profile user = new Profile();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setImage(rs.getString("image"));
            user.setGoals(rs.getString("goals"));
            user.setHighestRank(rs.getInt("highestRank"));
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
    public boolean resetPassword (int id, String password)
    {
    	Database db = new Database();
        try {
        	String sql ="UPDATE profiles SET password=? WHERE id=?";
        	
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

    public List<Profile> getAllProfile()
    {
    	try {
    		List<Profile> list=new ArrayList<Profile>();
    		ResultSet rs = getResultSet("SELECT * FROM profiles");
    		
    		if(rs==null||!rs.next())
    		{
    			return null;
    		}
    		do {
    			Profile profile=new Profile();
	    		profile.setId(rs.getInt("id"));
	    		profile.setName(rs.getString("name"));
	    		profile.setGoals(rs.getString("goals"));
	    		profile.setGamesWon(rs.getInt("gamesWon"));
	    		profile.setHighestRank(rs.getInt("highestRank"));
	    		profile.setImage(rs.getString("image"));
	    		list.add(profile);
    		}while(rs.next());
    		return list;		
    	} catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Profile> getProfileTopRank(int rank)
    {
    	try {
    		List<Profile> list=new ArrayList<Profile>();
    		ResultSet rs = getResultSet("SELECT * FROM profiles WHERE highestRank<="+rank+" ORDER BY highestRank ASC");
    		
    		if(rs==null||!rs.next())
    		{
    			return null;
    		}
    		do {
    			Profile profile=new Profile();
	    		profile.setId(rs.getInt("id"));
	    		profile.setName(rs.getString("name"));
	    		profile.setGoals(rs.getString("goals"));
	    		profile.setGamesWon(rs.getInt("gamesWon"));
	    		profile.setHighestRank(rs.getInt("highestRank"));
	    		profile.setImage(rs.getString("image"));
	    		list.add(profile);
    		}while(rs.next());
    		return list;		
    	} catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Profile getProfile(int id)
    {
    	try {
    		Profile profile=new Profile();
    		ResultSet rs = getResultSet("SELECT * FROM profiles WHERE id = "+id);
    		
    		if(rs==null||!rs.next())
    		{
    			return null;
    		}
    		profile.setId(rs.getInt("id"));
    		profile.setName(rs.getString("name"));
    		profile.setGoals(rs.getString("goals"));
    		profile.setGamesWon(rs.getInt("gamesWon"));
    		profile.setHighestRank(rs.getInt("highestRank"));
    		profile.setImage(rs.getString("image"));

    		return profile;		
    	} catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int createProfile (String name, String goals, String image)
    {
    	try {
    		String insert="INSERT INTO profiles (`name`,`goals`,image, `gamesWon`,`highestRank`)VALUES(?,?,?,0,2)";
    		
    		int retval=execute(insert, name,goals,image);
    		
    		return retval;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int createProfile (Profile profile)
    {
    	try {
    		String insert="INSERT INTO profiles (`name`,username, password, email,phone,`goals`,image,role_id, status, `gamesWon`,`highestRank`)VALUES(?,?,?,?,?,?,?,?,?,0,2)";
    		
    		int retval=execute(insert, profile.getName(),profile.getUsername(), profile.getPassword(),
    				profile.getEmail(),profile.getPhone(),profile.getGoals(), profile.getImage(),profile.getRoleId(), profile.getStatus());
    		
    		return retval;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public boolean isEmailInUse(String userEmail) {
        try {
            String query = "SELECT COUNT(*) FROM profiles WHERE email = ?";
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

            PreparedStatement statement = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            return statement.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            close();
        }

        return -1;
    }

    public int execute(String sql, Object... values) {
        try {
            if (conn == null)
                getConnection();

            PreparedStatement statement = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
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
        	e.printStackTrace();
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
