import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TimoshenkoStanislav
 */
public class PeripheralsShopDBShow {
    
    static String driver = "org.sqlite.JDBC";
    static String connect = "jdbc:sqlite:PeripheralShopDB";
	
	public static void showTable(JTable table, JTable table2, JTable table3, JTable table4) {

            DefaultTableModel model =(DefaultTableModel) table.getModel();
            model.setRowCount(0);
		try {
                    
			Class.forName( driver );
			Connection conn = DriverManager.getConnection(connect);
			Statement st = conn.createStatement(); 
			ResultSet rec = st.executeQuery("SELECT * FROM Peripherals INNER JOIN Producer "
                                + "ON Peripherals.Type=Producer.Type INNER JOIN CaseModel "
                                + "ON Producer.Producer=CaseModel.Producer");
                        
			while (rec.next()) {
				Object g1 = rec.getString("Type");
                                Object g2 = rec.getString("Descripton");
                                Object g3 = rec.getString("Producer");
                                Object g4 = rec.getString("Model");
                                Object g5 = rec.getString("Price");
				model.addRow(new Object[]{g1, g2, g3, g4, g5});
			}
			rec.close();
			st.close();			
		} catch (Exception e) {
			System.err.println("Run-time error: " + e );
		}
                
                showTables(1, table2);
                showTables(2, table3);
                showTables(3, table4);

	}
        
        public static void showTables(int index, JTable table){
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            model.setRowCount(0);
            
            switch(index){
                case 1:
                    try {
                            Class.forName( driver );
                            Connection conn = DriverManager.getConnection(connect);
                            Statement st = conn.createStatement(); 
                            ResultSet rec = st.executeQuery("SELECT * FROM Peripherals");
                            
                            while (rec.next()) {
                                    Object g1 = rec.getString("Type");
                                    Object g2 = rec.getString("Descripton");
                                    model.addRow(new Object[]{g1, g2});
                            }
                            rec.close();
                            st.close();			
                    } catch (Exception e) {
                            System.err.println("Run-time error: " + e );
                    }
                    break;
                
                case 2:
                   try {
                            Class.forName( driver );
                            Connection conn = DriverManager.getConnection(connect);
                            Statement st = conn.createStatement(); 
                            ResultSet rec = st.executeQuery("SELECT * FROM Producer");
                            
                            while (rec.next()) {
                                    Object g1 = rec.getString("Type");
                                    Object g2 = rec.getString("Producer");
                                    model.addRow(new Object[]{g1, g2});
                            }
                            rec.close();
                            st.close();			
                    } catch (Exception e) {
                            System.err.println("Run-time error: " + e );
                    }
                    break;      
                    
                case 3:
                   try {
                            Class.forName( driver );
                            Connection conn = DriverManager.getConnection(connect);
                            Statement st = conn.createStatement(); 
                            ResultSet rec = st.executeQuery("SELECT * FROM CaseModel");
                            
                            while (rec.next()) {
                                    Object g1 = rec.getString("Model");
                                    Object g2 = rec.getString("Producer");
                                    Object g3 = rec.getString("Price");
                                    model.addRow(new Object[]{g1, g2, g3});
                            }
                            rec.close();
                            st.close();			
                    } catch (Exception e) {
                            System.err.println("Run-time error: " + e );
                    }
                    break;
            }
        }
    
}
