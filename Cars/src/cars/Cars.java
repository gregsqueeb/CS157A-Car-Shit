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
        
        String[] makes = {"Select a Make","BUICK","CADILLAC","CHEVROLET","CHRYSLER","FORD","CHEVROLET & GMC TRUCK & VAN","INTERNATIONAL TRUCK (I.H.C.)","MAZDA","OLDSMOBILE","PORSCHE","RENAULT","SAAB","SUBARU","TOYOTA","UPS","VOLKSWAGEN"};

                Class.forName("oracle.jdbc.driver.OracleDriver");
             // Load the Oracle JDBC driver
             DriverManager.registerDriver
                     (new oracle.jdbc.driver.OracleDriver());
        
             // connect through driver
             Connection conn = DriverManager.getConnection
                     ("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        
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
        final JTextArea text2 = test.getTextArea2();
        box1.removeAllItems();
        final JComboBox box2 = test.getComboBox2();
        final JComboBox box4 = test.getComboBox4();
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
                            //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
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
        box2.removeAllItems();
        box2.addItem("Select a Model".toString());
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
                                //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
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
        box3.removeAllItems();
        box3.addItem("Select a Year".toString());
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
                                //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                        Statement stmt = conn.createStatement();
                        System.out.println(makeName);
                        ResultSet testSet = stmt.executeQuery("SELECT * FROM APL"+makeAbbr+" WHERE model = '"+modelName+"' AND year = "+modelYear);
                        String all_Data="";
                        box4.removeAllItems();
                        while (testSet.next()){
                            all_Data = all_Data + "Model: " + testSet.getString(1) + 
                                    " \nYear: " + testSet.getString(2)+
                                    " \nDescription: "+testSet.getString(3)+
                                    " \nLITER: "+testSet.getString(4)+
                                    " \nENG: "+testSet.getString(5)+
                                    " \nCUBIC: "+testSet.getString(6)+
                                    " \nRLINK: "+testSet.getString(7)+"\n";
                                   box4.addItem(testSet.getString(7));
                        }
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
        
        box4.removeAllItems();
        box4.addItem("Select a RLINK".toString());
        box4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){              
               
                String rlink = (String)box4.getSelectedItem();
                  
                    try{
                        Connection conn = DriverManager.getConnection
                         ("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                         //       ("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                        Statement stmt = conn.createStatement();
                        ResultSet testSet = stmt.executeQuery("select * from rdimmod where P_Number in (select MOD4 from radcrx where rlink=" + rlink+ ")");
                        String all_Data="";
                        while (testSet.next()){
                            all_Data = all_Data + "PNum: " + testSet.getString(1) + 
                                    " \nCore: " + testSet.getString(2)+
                                    " \nINHEAD: "+testSet.getString(3)+
                                    " \nOUTHEAD: "+testSet.getString(4)+
                                    " \nINCON: "+testSet.getString(5)+
                                    " \nOUCON: "+testSet.getString(6)+
                                    " \nTMOUNT: "+testSet.getString(7)+
                                    " \nOILCOOL: "+testSet.getString(8)+
                                    " \nPRICE: "+testSet.getString(9)+
                                    " \nAMOUNT: "+testSet.getString(10)+"\n";
                                            
                        }
                        
                        text2.setText(all_Data);
                        testSet.close();
                        stmt.close();
                        conn.close();
                    }
                    catch(SQLException exep){
                       exep.printStackTrace();
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
