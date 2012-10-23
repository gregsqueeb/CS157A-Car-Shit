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
 * @author Greg Mathews
 */
public class Cars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        String[] makes = {"BUICK","CADILLAC","CHEVROLET","CHRYSLER","FORD","CHEVROLET & GMC TRUCK & VAN","INTERNATIONAL TRUCK (I.H.C.)","MAZDA","OLDSMOBILE","PORSCHE","RENAULT","SAAB","SUBARU","TOYOTA","UPS","VOLKSWAGEN"};

                Class.forName("oracle.jdbc.driver.OracleDriver");
             // Load the Oracle JDBC driver
             DriverManager.registerDriver
                     (new oracle.jdbc.driver.OracleDriver());
        
             // connect through driver
             Connection conn = DriverManager.getConnection
                     ("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
        
             // Create Oracle DatabaseMetaData object
             DatabaseMetaData meta = conn.getMetaData();
        
             // gets driver info:
            System.out.println("JDBC driver version is " + meta.getDriverVersion());
        
             // Create a statement
             Statement stmt = conn.createStatement();
        
             // Do the SQL "Hello World" thing
             final ResultSet rset = stmt.executeQuery("SELECT TABLE_NAME FROM USER_TABLES");
        
        
        
        
        
        // TODO code application logic here
        final CarUI test = new CarUI();
        final JComboBox box1 = test.getComboBox1();
        final JTextArea text = test.getTextArea();
        box1.removeAllItems();
        final JComboBox box2 = test.getComboBox2();
        int i = 0;
        while(i < makes.length){
            box1.addItem(makes[i]);
            i++;
        }
        box1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String makeName = (String)cb.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                
                try{
                    Connection conn = DriverManager.getConnection
                     ("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                    Statement stmt = conn.createStatement();
                    ResultSet testSet = stmt.executeQuery("SELECT DISTINCT MODEL FROM APL"+makeAbbr);
                    box2.removeAllItems();
                    while (testSet.next())
                        box2.addItem(testSet.getString(1));
                    testSet.close();
                    stmt.close();
                    conn.close();
                }
                catch(SQLException exep){
                   exep.printStackTrace();
                }
                
                
            }
        });
        final JComboBox box3 = test.getComboBox3();
        box3.removeAllItems();
        box2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String modelName = (String)box2.getSelectedItem();
                String makeName = (String)box1.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                System.out.println(modelName);
                if(modelName != null){
                    System.out.println("SELECT YEAR FROM APL"+makeAbbr+" WHERE model = '"+modelName+"'");
                    try{
                        Connection conn = DriverManager.getConnection
                         ("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                        Statement stmt = conn.createStatement();
                        System.out.println(makeName);
                        ResultSet testSet = stmt.executeQuery("SELECT DISTINCT YEAR FROM APL"+makeAbbr+" WHERE model = '"+modelName+"'");
                        box3.removeAllItems();
                        while (testSet.next())
                            box3.addItem(testSet.getString(1));
                        testSet.close();
                        stmt.close();
                        conn.close();
                    }
                    catch(SQLException exep){
                       exep.printStackTrace();
                    }
                }
            }
        });
        
        box3.addItem("test".toString());
        box3.addItem("testasdf".toString());
        box3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String modelName = (String)box2.getSelectedItem();
                String makeName = (String)box1.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                String modelYear = (String)box3.getSelectedItem();
                System.out.println(modelName);
                
                if(modelYear != null){
                    System.out.println("SELECT * FROM APL"+makeAbbr+" WHERE model = '"+modelName+"' AND year = "+modelYear);
                    try{
                        Connection conn = DriverManager.getConnection
                         ("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                        Statement stmt = conn.createStatement();
                        System.out.println(makeName);
                        ResultSet testSet = stmt.executeQuery("SELECT * FROM APL"+makeAbbr+" WHERE model = '"+modelName+"' AND year = "+modelYear);
                        String all_Data="";
                        while (testSet.next())
                            all_Data = all_Data + "Model: " + testSet.getString(1) + 
                                    " Year: " + testSet.getString(2)+
                                    " Description: "+testSet.getString(3)+
                                    " LITER: "+testSet.getString(4)+
                                    " ENG: "+testSet.getString(5)+
                                    " CUBIC: "+testSet.getString(6)+
                                    " RLINK: "+testSet.getString(7)+"\n";
                        text.setText(all_Data);
                        testSet.close();
                        stmt.close();
                        conn.close();
                    }
                    catch(SQLException exep){
                       exep.printStackTrace();
                    }
                }
            }
        });
        test.setVisible(true);

        
//             while (rset.next())
//                System.out.println(rset.getString(1));
        
             // close the result set, the statement and disconnect
             rset.close();
             stmt.close();
             conn.close();
        
        
        
    }
    public void actionPerformed(ActionEvent e){
            JComboBox cb = (JComboBox)e.getSource();
            String makeName = (String)cb.getSelectedItem();
            System.out.println("pressed");
        }
}
