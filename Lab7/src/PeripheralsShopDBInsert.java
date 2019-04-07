
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TimoshenkoStanislav
 */
public class PeripheralsShopDBInsert {
    
    	static String driver = "org.sqlite.JDBC";
	static String connect = "jdbc:sqlite:PeripheralShopDB";
	
	public static void inputPeripherals(String s1, String s2) {
            
		try {
			Class.forName( driver );
			Connection conn = DriverManager.getConnection(connect);
                        
			Statement st = conn.createStatement();
                        st.execute("INSERT INTO Peripherals VALUES('"+s1+"', '"+s2+"')");
			st.close();
			
		} catch (Exception e) {
			System.err.println("Run-time error: " + e );
		}
	}
        
	public static void inputProducer(String s1, String s2) {
            
		try {
			Class.forName( driver );
			Connection conn = DriverManager.getConnection(connect);
                        
			Statement st = conn.createStatement();
                        st.execute("INSERT INTO Producer VALUES('"+s1+"', '"+s2+"')");                        
			st.close();
			
		} catch (Exception e) {
			System.err.println("Run-time error: " + e );
		}
	}     

	public static void inputCase(String s1, String s2, String s3, String s4, String s5) {
            
		try {
			Class.forName( driver );
			Connection conn = DriverManager.getConnection(connect);
                        
			Statement st = conn.createStatement();
                        st.execute("INSERT INTO Producer VALUES('"+s1+"', '"+s2+"')");
                        st.execute("INSERT INTO CaseModel VALUES('"+s2+"', '"+s3+"', '"+s4+"')");
                        st.execute("INSERT INTO Peripherals VALUES('"+s1+"', '"+s5+"')");
			st.close();
			
		} catch (Exception e) {
			System.err.println("Run-time error: " + e );
		}
	}          
}
