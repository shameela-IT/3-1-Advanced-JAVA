import java.sql.*;

public class UResultSet {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@12345";

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Database
            Connection con = DriverManager.getConnection(url, user, password);

            // Create Updatable and Scrollable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Execute Query
            ResultSet rs = st.executeQuery("SELECT * FROM Student");

            // Delete last row
            rs.last();
            rs.deleteRow();
            System.out.println("Last student record deleted successfully.");

            // Insert new row
            rs.moveToInsertRow();
            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");
            rs.insertRow();

            System.out.println("New student record inserted successfully.");

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
