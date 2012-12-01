/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
/**
 *
 * @author Alben Cheung
 */
public class Parts {
    static String dbConnectString = "jdbc:oracle:thin:@localhost:1521:orcl2";
    static String dbUserName = "scott";
    static String dbPassword = "tiger"; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String parts[] = {};
        
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        // Load the Oracle JDBC driver
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());   
        // Connect through driver
        //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");     
        Connection conn = DriverManager.getConnection(dbConnectString, dbUserName, dbPassword);
        // Create Oracle DatabaseMetaData object
        DatabaseMetaData meta = conn.getMetaData();      
        // gets driver info:
        System.out.println("JDBC driver version is " + meta.getDriverVersion());
        // Create a statement
        Statement stmt = conn.createStatement(); 
        // Do the SQL "Hello World" thing
        final ResultSet rset = stmt.executeQuery("SELECT TABLE_NAME FROM USER_TABLES");
    }
    
    public void actionPerformed(ActionEvent e){

        }
    
    
}
