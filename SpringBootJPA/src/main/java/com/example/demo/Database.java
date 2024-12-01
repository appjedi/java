package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Database {
    
	// This is for Mohamad Amjad Alzein
	/*
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/mission_emr";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Password321";
    */
	private String jdbcURL = "jdbc:h2:file:~/spring-boot-h2-db102";
    private String jdbcUsername = "sa";
    private String jdbcPassword = "";
    private String dbDriver="org.h2.Driver";
    String memoryURL = "jdbc:sqlite::memory:";
    private Connection conn;

    public static void main(String[] args) {
        Database db = new Database();
        try {
        	//ResultSet rs = db.getResultSet("SELECT * FROM INFORMATION_SCHEMA.TABLES");
        	//db.showColumns("product_part");
        	//db.insertProductPart(7,1);
        	db.showData("product_part");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
    public static void showData(String tableName) {
        Database db = new Database();
        try {
        	//ResultSet rs = db.getResultSet("SELECT * FROM INFORMATION_SCHEMA.TABLES");
        	ResultSet rs = db.getResultSet("SELECT * FROM "+tableName);

        	if (rs==null||!rs.next())
        	{
        		System.err.println ("Query failed");
        		return;
        	}
        	do {
        		System.out.printf ("%s - %s\n", rs.getString(1), rs.getString(2));
        	}while(rs.next());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
    public void insertProductPart(int prodId, int partId)
    {
    	//326
    	Database db = new Database();
        try {
        	//ResultSet rs = db.getResultSet("SELECT * FROM INFORMATION_SCHEMA.TABLES");
        	String insert = String.format("INSERT INTO product_part (part_id, product_id) VALUES (%d,%d)",partId,prodId);

        	db.execute(insert);
        	
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
    public void showColumns (String tableName)
    {
    	 Database db = new Database();
         try {
         	//ResultSet rs = db.getResultSet("SELECT * FROM INFORMATION_SCHEMA.TABLES");
         	ResultSet rs = db.getResultSet("SELECT * FROM "+tableName);
         	ResultSetMetaData rsmd = rs.getMetaData();

         	int colCount = rsmd.getColumnCount();
         	for (int i=1;i<=colCount;i++)
         	{
         		String name = rsmd.getColumnName(i);
         		System.out.println(name);

         	}
         } catch (Exception ex) {
             ex.printStackTrace();
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
            Class.forName(dbDriver);
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
