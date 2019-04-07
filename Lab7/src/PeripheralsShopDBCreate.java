import java.sql.*;

public class PeripheralsShopDBCreate {
	static String driver = "org.sqlite.JDBC";
	static String connect = "jdbc:sqlite:PeripheralShopDB";
	
	public static void createDB() {
            
		try {
			Class.forName( driver );
			Connection conn = DriverManager.getConnection(connect);
			Statement st = conn.createStatement();
			st.execute("CREATE TABLE Peripherals " +
				"(Type TEXT PRIMARY KEY, Descripton TEXT )");
			st.execute("CREATE TABLE Producer " + 
				"(Type TEXT, Producer TEXT )");
			st.execute("CREATE TABLE CaseModel " + 
				"(Producer TEXT, " +
				"Model TEXT, " +
				"Price REAL )");

			st.close();
			
		} catch (Exception e) {
			System.err.println("Run-time error: " + e );
		}

	}
}
