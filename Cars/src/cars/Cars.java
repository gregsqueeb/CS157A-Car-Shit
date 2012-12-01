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
import java.util.Timer;
import javax.swing.*;

/**
 *
 * @author Greg Mathews
 */
public class Cars {
    static String dbConnectString = "jdbc:oracle:thin:@localhost:1521:orcl2";
    static String dbUserName = "scott";
    static String dbPassword = "tiger";
    static String oPNUM;
    boolean fieldcheck = true;
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
                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                     (dbConnectString, dbUserName, dbPassword);
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
        final JComboBox boxMake = test.getComboBoxMake();
        final JTextArea text = test.getTextArea();
        final JTextArea text2 = test.getTextArea2();
        box1.removeAllItems();
        boxMake.removeAllItems();
        final JComboBox box2 = test.getComboBox2();
        final JComboBox box3 = test.getComboBox3();
        final JComboBox box4 = test.getComboBox4();
        final JTextField textField1 = test.getJText1();
        final JTextField textField2 = test.getJText2();
        final JTextField textField3 = test.getJText3();
        final JTextField textField4 = test.getJText4();
        final JTextField textField5 = test.getJText5();
        final JTextField textField6 = test.getJText6();
        final JTextField textField7 = test.getJText7();
        final JTextField textField8 = test.getJText8();
        final JTextField textField9 = test.getJText9();
        final JTextField textField10 = test.getJText10();
        final JTextField textField11 = test.getJText11();
        final JTextField textField12 = test.getJText12();
        final JTextField textField13 = test.getJText13();
        final JTextField textField14 = test.getJText14();
        final JTextField textField15 = test.getJText15();
        final JTextField textField16 = test.getJText16();
        final JTextField textField17 = test.getJText17();
        // Tim's Additional field
        final JTextField textField18 = test.getJText18();
        final JTextField textField19 = test.getJText19();
        final JTextField textField20 = test.getJText20();
        final JTextField textField21 = test.getJText21();
        final JTextField textField22 = test.getJText22();
        final JTextField textField23 = test.getJText23();
        final JTextField textField24 = test.getJText24();
        
        final JTextField textField25 = test.getJText25();
        final JTextField textField26 = test.getJText26(); 
        final JTextField textField27 = test.getJText27();
        final JTextField textField28 = test.getJText28();
        final JTextField textField29 = test.getJText29();
        final JTextField textField30 = test.getJText30();
        final JTextField textField31 = test.getJText31();
        final JTextField textField32 = test.getJText32();
        final JTextField textField33 = test.getJText33();
        final JTextField textField34 = test.getJText34();        
        //
        final JButton newCar = test.getJButton1();
        final JButton newPart = test.getJButton2();
        final JButton deleteCar = test.getJButton3();
        final JButton deletePart = test.getJButton4();
        final JButton updateCar = test.getJButton5();
        final JButton updatePart = test.getJButton6();        
        // delete the selected car
        deleteCar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String makeName = (String)box1.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                
                try{
                    Connection conn = DriverManager.getConnection
                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                    (dbConnectString, dbUserName, dbPassword);
                    Statement stmt = conn.createStatement();
                    System.out.println("Delete from APL"+makeAbbr+" where model ='"+box2.getSelectedItem()+"' and year="+box3.getSelectedItem()+" and rlink = "+box4.getSelectedItem());
                    ResultSet testSet = stmt.executeQuery("Delete from APL"+makeAbbr+" where model = '"+box2.getSelectedItem()+"' and year = "+box3.getSelectedItem()+" and rlink = "+box4.getSelectedItem());
                    
                    testSet.close();
                    stmt.close();
                    conn.close();
                    box1.setSelectedItem("Select a Make");
                    box2.removeAllItems();
                    box3.removeAllItems();
                    box4.removeAllItems();
                }
                catch(SQLException exep){
                   exep.printStackTrace();
                }
            }
        });
        // delete the selected part
        deletePart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String makeName = (String)box1.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                
                try{
                    Connection conn = DriverManager.getConnection
                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                    (dbConnectString, dbUserName, dbPassword);
                    Statement stmt = conn.createStatement();
                    String rlink = (String)box4.getSelectedItem();
                    System.out.println("delete from rdimmod where P_Number in (select MOD4 from radcrx where rlink=" + rlink+ ")");
                    ResultSet testSet = stmt.executeQuery("delete from rdimmod where P_Number in (select MOD4 from radcrx where rlink=" + rlink+ ")");
                    
                    testSet.close();
                    stmt.close();
                    conn.close();
                    text2.setText("");
                }
                catch(SQLException exep){
                   exep.printStackTrace();
                }
            }
        });
        
        // code to create a new part
        newPart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String rlink = (String)box4.getSelectedItem();
                String pnum, core, inhead, outhead, incon, oucon, tmount, oilcool, price, amount;
                if(textField8.getText().equals("")){
                    pnum = "NA";
                }
                else{
                    pnum = textField8.getText();
                }
                if(textField9.getText().equals("")){
                    core = "NA";
                }
                else{
                    core = textField9.getText();
                }
                if(textField10.getText().equals("")){
                    inhead = "NA";
                }
                else{
                    inhead = textField10.getText();
                }
                if(textField11.getText().equals("")){
                    outhead = "NA";
                }
                else{
                    outhead = textField11.getText();
                }
                if(textField12.getText().equals("")){
                   incon = "NA";
                }
                else{
                    incon = textField12.getText();
                }
                if(textField13.getText().equals("")){
                    oucon = "NA";
                }
                else{
                    oucon = textField13.getText();
                }
                if(textField14.getText().equals("")){
                    tmount = "NA";
                }
                else{
                    tmount = textField14.getText();
                }
                if(textField15.getText().equals("")){
                    oilcool = "NA";
                }
                else{
                    oilcool = textField15.getText();
                }
                if(textField16.getText().equals("")){
                    price = "0";
                }
                else{
                    price = textField16.getText();
                }
                if(textField17.getText().equals("")){
                    amount = "0";
                }
                else{
                    amount = textField17.getText();
                }
                try{
                    Connection conn = DriverManager.getConnection
                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                    (dbConnectString, dbUserName, dbPassword);
                    Statement stmt = conn.createStatement();
                    System.out.println("INSERT INTO rdimmod VALUES ('"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"',"+price+", "+amount+")");
                    ResultSet testSet = stmt.executeQuery("INSERT INTO rdimmod VALUES ('"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"',"+price+", "+amount+")");
                    testSet.close();
                    stmt.close();
                    conn.close();
                    newPart.setText("Inserted");
                    textField8.setText("");
                    textField9.setText("");
                    textField10.setText("");
                    textField11.setText("");
                    textField12.setText("");
                    textField13.setText("");
                    textField14.setText("");
                    textField15.setText("");
                    textField16.setText("");
                    textField17.setText("");
                    String all_Data = "PNum: " + pnum + 
                                    " \nCore: " + core+
                                    " \nINHEAD: "+inhead+
                                    " \nOUTHEAD: "+outhead+
                                    " \nINCON: "+incon+
                                    " \nOUCON: "+oucon+
                                    " \nTMOUNT: "+tmount+
                                    " \nOILCOOL: "+oilcool+
                                    " \nPRICE: "+price+
                                    " \nAMOUNT: "+amount+"\n";
                    text2.setText(all_Data);
                    Timer time = new Timer();
                    (new Thread(new Runnable(){
                        public void run(){
                            newPart.setEnabled(false);
                            try{
                                Thread.sleep(2000);
                            }
                            catch(InterruptedException exep){
                                exep.printStackTrace();
                            }
                            newPart.setText("Add Part");
                            newPart.setEnabled(true);
                        }
                    })).start();
                }
                catch(SQLException exep){
                   exep.printStackTrace();
                }
                
                
            }
        });

// code to update car
     updateCar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) { 
                String make, makeAbbr, model, description, year, liter, rlink, engine, cubic;
                model = textField18.getText();
                description = textField19.getText();
                year = textField20.getText();
                liter = textField21.getText();
                rlink = textField22.getText();
                engine = textField23.getText();
                cubic = textField24.getText();
                make = (String)box1.getSelectedItem();
                makeAbbr = test.selectedMake(make);
                
                System.out.println("model: " + model + " description: " + description + " year: " + year + " liter: " + liter + " rlink: " + rlink + " engine: " + engine + " cubic: " + cubic + " make: " + make + " abbr: " + makeAbbr);
                try{
                    Connection conn = DriverManager.getConnection
                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                    (dbConnectString, dbUserName, dbPassword);
                    
                    Statement stmt = conn.createStatement();

                    ResultSet testSet = stmt.executeQuery("delete from rdimmod where P_Number in (select MOD4 from radcrx where rlink=" + rlink+ ")");
                    
                    testSet.close();
                    stmt.close();
                    conn.close();
                    
                 }catch(SQLException exep){
                            exep.printStackTrace();
                      }
            
        }
     }); 
// code to update part
        updatePart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                  String origPnum = oPNUM;
                  String rlink = (String)box4.getSelectedItem();
                  String pnum, core, inhead, outhead, incon, oucon, tmount, oilcool, price, amount;
                  System.out.println(origPnum);
//                  if(textField25.getText().equals(origPnum)){
//                      pnum = textField25.getText();
//                      core = textField32.getText();
//                      inhead = textField31.getText();
//                      outhead = textField30.getText();
//                      incon = textField34.getText();
//                      oucon = textField26.getText();
//                      tmount = textField29.getText();
//                      oilcool = textField28.getText();
//                      price = textField27.getText();
//                      amount = textField33.getText();
//                  }else{
                      pnum = textField25.getText();
                      core = textField32.getText();
                      inhead = textField31.getText();
                      outhead = textField30.getText();
                      incon = textField34.getText();
                      oucon = textField26.getText();
                      tmount = textField29.getText();
                      oilcool = textField28.getText();
                      price = textField27.getText();
                      amount = textField33.getText();
//                  }
                  
                  if(!textField25.getText().equals(origPnum)){
                      try{
//                          String [] queries = {
//                              "INSERT INTO RDIMARS (P_NUMBER, CORE, INHEAD, OUTHEAD, INCON, OUCON, TMOUNT, OILCOOL, PRICE, AMOUNT) SELECT '"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"','"+price+"', '"+amount+"' FROM RDIMARS WHERE P_NUMBER='"+origPnum+"'",
//                          };
                                  
                          Connection conn = DriverManager.getConnection(dbConnectString, dbUserName, dbPassword);   
                          Statement stmt = conn.createStatement();
                         // ResultSet testSet = stmt.executeQuery("INSERT INTO RDIMARS (P_NUMBER, CORE, INHEAD, OUTHEAD, INCON, OUCON, TMOUNT, OILCOOL, PRICE, AMOUNT) SELECT '"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"','"+price+"', '"+amount+"' FROM RDIMARS WHERE P_NUMBER='"+origPnum+"'");
                          
                            String ars = "INSERT INTO RDIMARS (P_NUMBER, CORE, INHEAD, OUTHEAD, INCON, OUCON, TMOUNT, OILCOOL, PRICE, AMOUNT) SELECT '"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"','"+price+"', '"+amount+"' FROM RDIMARS WHERE P_NUMBER='"+origPnum+"'";
                           // System.out.println(ars);
                            String ars1 = "UPDATE RADCRX SET ARS1='"+pnum+"' WHERE ARS1='"+origPnum+"'";
                            String ars2 = "UPDATE RADCRX SET ARS2='"+pnum+"' WHERE ARS2='"+origPnum+"'";
                            String ars3 = "UPDATE RADCRX SET ARS3='"+pnum+"' WHERE ARS3='"+origPnum+"'";
                            String ars4 = "UPDATE RADCRX SET ARS4='"+pnum+"' WHERE ARS4='"+origPnum+"'";
                            String dars = "DELETE FROM RDIMARS WHERE P_NUMBER='"+origPnum+"'";
                            String beh = "INSERT INTO RDIMBEH (P_NUMBER, CORE, INHEAD, OUTHEAD, INCON, OUCON, TMOUNT, OILCOOL, PRICE, AMOUNT) SELECT '"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"','"+price+"', '"+amount+"' FROM RDIMBEH WHERE P_NUMBER='"+origPnum+"'";
                            String beh1 = "UPDATE RADCRX SET BEH1='"+pnum+"' WHERE BEH1='"+origPnum+"'";
                            String beh2 = "UPDATE RADCRX SET BEH2='"+pnum+"' WHERE BEH2='"+origPnum+"'";
                            String beh3 = "UPDATE RADCRX SET BEH3='"+pnum+"' WHERE BEH3='"+origPnum+"'";
                            String beh4 = "UPDATE RADCRX SET ARS4='"+pnum+"' WHERE ARS4='"+origPnum+"'";
                            String dbeh = "DELETE FROM RDIMBEH WHERE P_NUMBER='"+origPnum+"'";
                            String dan = "INSERT INTO RDIMDAN (P_NUMBER, CORE, INHEAD, OUTHEAD, INCON, OUCON, TMOUNT, OILCOOL, PRICE, AMOUNT) SELECT '"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"','"+price+"', '"+amount+"' FROM RDIMDAN WHERE P_NUMBER='"+origPnum+"'";
                            String dan1 = "UPDATE RADCRX SET DAN1='"+pnum+"' WHERE DAN1='"+origPnum+"'";
                            String dan2 = "UPDATE RADCRX SET DAN2='"+pnum+"' WHERE DAN2='"+origPnum+"'";
                            String dan3 = "UPDATE RADCRX SET DAN3='"+pnum+"' WHERE DAN3='"+origPnum+"'";
                            String dan4 = "UPDATE RADCRX SET DAN4='"+pnum+"' WHERE DAN4='"+origPnum+"'";
                            String ddan = "DELETE FROM RDIMDAN WHERE P_NUMBER='"+origPnum+"'";
                            String mod = "INSERT INTO RDIMMOD (P_NUMBER, CORE, INHEAD, OUTHEAD, INCON, OUCON, TMOUNT, OILCOOL, PRICE, AMOUNT) SELECT '"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"','"+price+"', '"+amount+"' FROM RDIMMOD WHERE P_NUMBER='"+origPnum+"'";
                            String mod1 = "UPDATE RADCRX SET MOD1='"+pnum+"' WHERE MOD1='"+origPnum+"'";
                            String mod2 = "UPDATE RADCRX SET MOD2='"+pnum+"' WHERE MOD2='"+origPnum+"'";
                            String mod3 = "UPDATE RADCRX SET MOD3='"+pnum+"' WHERE MOD3='"+origPnum+"'";
                            String mod4 = "UPDATE RADCRX SET MOD4='"+pnum+"' WHERE MOD4='"+origPnum+"'";
                            String dmod = "DELETE FROM RDIMMOD WHERE P_NUMBER='"+origPnum+"'";
                            //System.out.println("INSERT INTO RDIMARS VALUES ('"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"',"+price+", "+amount+")");
                            //ResultSet testSet = stmt.executeQuery("INSERT INTO rdimmod VALUES ('"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"',"+price+", "+amount+")");
                            //testSet.close();
                            conn.setAutoCommit(false);
                            stmt.addBatch(ars);
                            stmt.addBatch(ars1);
                            stmt.addBatch(ars2);
                            stmt.addBatch(ars3);
                            stmt.addBatch(ars4);
                            stmt.addBatch(dars);
                            stmt.addBatch(beh);
                            stmt.addBatch(beh1);
                            stmt.addBatch(beh2);
                            stmt.addBatch(beh3);
                            stmt.addBatch(beh4);
                            stmt.addBatch(dbeh);
                            stmt.addBatch(dan);
                            stmt.addBatch(dan1);
                            stmt.addBatch(dan2);
                            stmt.addBatch(dan3);
                            stmt.addBatch(dan4);
                            stmt.addBatch(ddan);
                            stmt.addBatch(mod);
                            stmt.addBatch(mod1);
                            stmt.addBatch(mod2);
                            stmt.addBatch(mod3);
                            stmt.addBatch(mod4);
                            stmt.addBatch(dmod);
                            stmt.executeBatch();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            textField25.setText("");
                            textField32.setText("");
                            textField31.setText("");
                            textField30.setText("");
                            textField34.setText("");
                            textField26.setText("");
                            textField29.setText("");
                            textField28.setText("");
                            textField27.setText("");
                            textField33.setText("");
                      }catch(SQLException exep){
                            exep.printStackTrace();
                      }
                  }else{
                      try{
                          Connection conn = DriverManager.getConnection(dbConnectString, dbUserName, dbPassword);   
                          //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                          Statement stmt = conn.createStatement();
                          System.out.println("UPDATE RDIMARS SET CORE='"+core+"', INHEAD='"+inhead+"', OUTHEAD='"+outhead+"', INCON='"+incon+"', OUCON='"+oucon+"', TMOUNT='"+tmount+"', OILCOOL='"+oilcool+"', PRICE='"+price+"', AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"';");
                            String ars = "UPDATE RDIMARS SET CORE='"+core+"', INHEAD='"+inhead+"', OUTHEAD='"+outhead+"', INCON='"+incon+"', OUCON='"+oucon+"', TMOUNT='"+tmount+"', OILCOOL='"+oilcool+"', PRICE='"+price+"', AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"'";
                            String beh = "UPDATE RDIMBEH SET CORE='"+core+"', INHEAD='"+inhead+"', OUTHEAD='"+outhead+"', INCON='"+incon+"', OUCON='"+oucon+"', TMOUNT='"+tmount+"', OILCOOL='"+oilcool+"', PRICE='"+price+"', AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"'";
                            String dan = "UPDATE RDIMDAN SET CORE='"+core+"', INHEAD='"+inhead+"', OUTHEAD='"+outhead+"', INCON='"+incon+"', OUCON='"+oucon+"', TMOUNT='"+tmount+"', OILCOOL='"+oilcool+"', PRICE='"+price+"', AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"'";
                            String mod = "UPDATE RDIMMOD SET CORE='"+core+"', INHEAD='"+inhead+"', OUTHEAD='"+outhead+"', INCON='"+incon+"', OUCON='"+oucon+"', TMOUNT='"+tmount+"', OILCOOL='"+oilcool+"', PRICE='"+price+"', AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"'";
                            conn.setAutoCommit(false);
                            stmt.addBatch(ars);
                            stmt.addBatch(beh);
                            stmt.addBatch(dan);
                            stmt.addBatch(mod);
                            stmt.executeBatch();
                            conn.commit();
                            //System.out.println("UPDATE RDIMARS SET CORE='"+core+"', SET INHEAD='"+inhead+"', SET OUTHEAD='"+outhead+"', SET INCON='"+incon+"', SET OUCON='"+oucon+"', SET TMOUNT='"+tmount+"', SET OILCOOL='"+oilcool+"', SET PRICE='"+price+"', SET AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"';");
                            //ResultSet testSet = stmt.executeQuery("UPDATE RDIMARS SET CORE='"+core+"', SET INHEAD='"+inhead+"', SET OUTHEAD='"+outhead+"', SET INCON='"+incon+"', SET OUCON='"+oucon+"', SET TMOUNT='"+tmount+"', SET OILCOOL='"+oilcool+"', SET PRICE='"+price+"', SET AMOUNT='"+amount+"' WHERE P_NUMBER='"+origPnum+"';");
                            // testSet.close();
                            stmt.close();
                            conn.close();
                            textField25.setText("");
                            textField32.setText("");
                            textField31.setText("");
                            textField30.setText("");
                            textField34.setText("");
                            textField26.setText("");
                            textField29.setText("");
                            textField28.setText("");
                            textField27.setText("");
                            textField33.setText("");
                      }catch(SQLException exep){
                            exep.printStackTrace();
                      }
                  }
                  
                  
//                String rlink = (String)box4.getSelectedItem();
//                String pnum, core, inhead, outhead, incon, oucon, tmount, oilcool, price, amount;
//                if(textField8.getText().equals("")){
//                    pnum = "NA";
//                }
//                else{
//                    pnum = textField8.getText();
//                }
//                if(textField9.getText().equals("")){
//                    core = "NA";
//                }
//                else{
//                    core = textField9.getText();
//                }
//                if(textField10.getText().equals("")){
//                    inhead = "NA";
//                }
//                else{
//                    inhead = textField10.getText();
//                }
//                if(textField11.getText().equals("")){
//                    outhead = "NA";
//                }
//                else{
//                    outhead = textField11.getText();
//                }
//                if(textField12.getText().equals("")){
//                   incon = "NA";
//                }
//                else{
//                    incon = textField12.getText();
//                }
//                if(textField13.getText().equals("")){
//                    oucon = "NA";
//                }
//                else{
//                    oucon = textField13.getText();
//                }
//                if(textField14.getText().equals("")){
//                    tmount = "NA";
//                }
//                else{
//                    tmount = textField14.getText();
//                }
//                if(textField15.getText().equals("")){
//                    oilcool = "NA";
//                }
//                else{
//                    oilcool = textField15.getText();
//                }
//                if(textField16.getText().equals("")){
//                    price = "0";
//                }
//                else{
//                    price = textField16.getText();
//                }
//                if(textField17.getText().equals("")){
//                    amount = "0";
//                }
//                else{
//                    amount = textField17.getText();
//                }
//                try{
//                    Connection conn = DriverManager.getConnection
//                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
//                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
//                    (dbConnectString, dbUserName, dbPassword);
//                    Statement stmt = conn.createStatement();
//                    System.out.println("INSERT INTO rdimmod VALUES ('"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"',"+price+", "+amount+")");
//                    ResultSet testSet = stmt.executeQuery("INSERT INTO rdimmod VALUES ('"+pnum+"','"+core+"','"+inhead+"','"+outhead+"','"+incon+"','"+oucon+"','"+tmount+"','"+oilcool+"',"+price+", "+amount+")");
//                    testSet.close();
//                    stmt.close();
//                    conn.close();
//                    newPart.setText("Inserted");
//                    textField8.setText("");
//                    textField9.setText("");
//                    textField10.setText("");
//                    textField11.setText("");
//                    textField12.setText("");
//                    textField13.setText("");
//                    textField14.setText("");
//                    textField15.setText("");
//                    textField16.setText("");
//                    textField17.setText("");
//                    String all_Data = "PNum: " + pnum + 
//                                    " \nCore: " + core+
//                                    " \nINHEAD: "+inhead+
//                                    " \nOUTHEAD: "+outhead+
//                                    " \nINCON: "+incon+
//                                    " \nOUCON: "+oucon+
//                                    " \nTMOUNT: "+tmount+
//                                    " \nOILCOOL: "+oilcool+
//                                    " \nPRICE: "+price+
//                                    " \nAMOUNT: "+amount+"\n";
//                    text2.setText(all_Data);
//                    Timer time = new Timer();
//                    (new Thread(new Runnable(){
//                        public void run(){
//                            newPart.setEnabled(false);
//                            try{
//                                Thread.sleep(2000);
//                            }
//                            catch(InterruptedException exep){
//                                exep.printStackTrace();
//                            }
//                            newPart.setText("Add Part");
//                            newPart.setEnabled(true);
//                        }
//                    })).start();
//                }
//                catch(SQLException exep){
//                   exep.printStackTrace();
//                }
                
                
            }
        });        
        
        // code to create a new car
        newCar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String model;
                String description;
                String year;
                String liter;
                String rlink;
                String engine;
                String cubic;
                String makeName = (String)boxMake.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                if(textField1.getText().equals("")){
                    model = "NA";
                }
                else{
                    model = textField2.getText();
                }
                if(textField1.getText().equals("")){
                    description = "NA";
                }
                else{
                    description = textField1.getText();
                }
                if(textField3.getText().equals("")){
                    year = "12";
                }
                else{
                    year = textField3.getText();
                }
                if(textField5.getText().equals("")){
                    liter = "NA";
                }
                else{
                    liter = textField5.getText();
                }
                if(textField4.getText().equals("")){
                   rlink = "NA";
                }
                else{
                    rlink = textField4.getText();
                }
                if(textField6.getText().equals("")){
                    engine = "NA";
                }
                else{
                    engine = textField6.getText();
                }
                if(textField7.getText().equals("")){
                    cubic = "0";
                }
                else{
                    cubic = textField7.getText();
                }

                try{
                    Connection conn = DriverManager.getConnection
                    //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                    //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                    (dbConnectString, dbUserName, dbPassword);
                    Statement stmt = conn.createStatement();
                    System.out.println("INSERT INTO APL"+makeAbbr+" VALUES ('"+model+"', "+year+", '"+description+"', '"+liter+"', '"+engine+"', "+cubic+", "+rlink+")");
                    ResultSet testSet = stmt.executeQuery("INSERT INTO APL"+makeAbbr+" VALUES ('"+model+"', "+year+", '"+description+"', '"+liter+"', '"+engine+"', "+cubic+", "+rlink+")");
                    testSet.close();
                    stmt.close();
                    conn.close();
                    newCar.setText("Inserted");
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                    Timer time = new Timer();
                    (new Thread(new Runnable(){
                        public void run(){
                            newCar.setEnabled(false);
                            try{
                                Thread.sleep(2000);
                            }
                            catch(InterruptedException exep){
                                exep.printStackTrace();
                            }
                            newCar.setText("Create New Car");
                            newCar.setEnabled(true);
                        }
                    })).start();
                    
                }
                catch(SQLException exep){
                   exep.printStackTrace();
                }
            }
        });
        
        // Add all the makes to the combobox
        int i = 0;
        while(i < makes.length){
            box1.addItem(makes[i]);
            boxMake.addItem(makes[i]);
            i++;
        }
        // Listen for a change on this combo box
        box1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String makeName = (String)cb.getSelectedItem();
                String makeAbbr = test.selectedMake(makeName);
                
                try{
                    Connection conn = DriverManager.getConnection
                     //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                     //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                    (dbConnectString, dbUserName, dbPassword);
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
                         //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                         //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                        (dbConnectString, dbUserName, dbPassword);
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
                        //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                        //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                        (dbConnectString, dbUserName, dbPassword);
                        Statement stmt = conn.createStatement();
                        System.out.println(makeName);
                        ResultSet testSet = stmt.executeQuery("SELECT * FROM APL"+makeAbbr+" WHERE model = '"+modelName+"' AND year = "+modelYear);
                        String all_Data="";
                        box4.removeAllItems();
                        while (testSet.next()){
//                            all_Data = all_Data + "Model: " + testSet.getString(1) + 
//                                    " \nYear: " + testSet.getString(2)+
//                                    " \nDescription: "+testSet.getString(3)+
//                                    " \nLITER: "+testSet.getString(4)+
//                                    " \nENG: "+testSet.getString(5)+
//                                    " \nCUBIC: "+testSet.getString(6)+
//                                    " \nRLINK: "+testSet.getString(7)+"\n";
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
                         //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                         //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                        (dbConnectString, dbUserName, dbPassword);
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
                                            
                        // tim's field retrieval
                            textField25.setText(testSet.getString(1));
                            oPNUM = testSet.getString(1);
                            textField26.setText(testSet.getString(6));
                            textField32.setText(testSet.getString(2));
                            textField29.setText(testSet.getString(7));
                            textField31.setText(testSet.getString(3));
                            textField28.setText(testSet.getString(8));
                            textField30.setText(testSet.getString(4));
                            textField27.setText(testSet.getString(9));
                            textField34.setText(testSet.getString(5));
                            textField33.setText(testSet.getString(10));
                        
                        //                            
                        }
                        text2.setText(all_Data);
         
                        
                        
                        
                        testSet.close();
                        stmt.close();
                        conn.close();
                    }
                    catch(SQLException exep){
                       exep.printStackTrace();
                    }
                    
                
                
// Experiment
                try{
                    String modelName = (String)box2.getSelectedItem();
                    String makeName = (String)box1.getSelectedItem();
                    String makeAbbr = test.selectedMake(makeName);
                    String modelYear = (String)box3.getSelectedItem();
                    System.out.println(modelName);
                        Connection conn = DriverManager.getConnection
                         //("jdbc:oracle:thin:@localhost:1521:ORCL","system","admin");
                         //("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
                        (dbConnectString, dbUserName, dbPassword);
                        Statement stmt = conn.createStatement();
                        ResultSet testSet = stmt.executeQuery("SELECT * FROM APL"+makeAbbr+" WHERE model = '"+modelName+"' AND year = '"+modelYear+"' AND rlink = "+rlink);
                        String all_Data="";
                        while (testSet.next()){
                            all_Data = all_Data + "Model: " + testSet.getString(1) + 
                                    " \nYear: " + testSet.getString(2)+
                                    " \nDescription: "+testSet.getString(3)+
                                    " \nLITER: "+testSet.getString(4)+
                                    " \nENG: "+testSet.getString(5)+
                                    " \nCUBIC: "+testSet.getString(6)+
                                    " \nRLINK: "+rlink+"\n";
                            textField18.setText(testSet.getString(1));
                            textField19.setText(testSet.getString(3));
                            textField20.setText(testSet.getString(2));
                            textField21.setText(testSet.getString(4));
                            textField22.setText(rlink);
                            textField23.setText(testSet.getString(5));
                            textField24.setText(testSet.getString(6));
                                            
                                                   
                        }
                        text.setText(all_Data);
         
                        
                        
                        
                        testSet.close();
                        stmt.close();
                        conn.close();
                        }
                    catch(SQLException exep){
                       exep.printStackTrace();
                    }
// End Experiment
            
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
