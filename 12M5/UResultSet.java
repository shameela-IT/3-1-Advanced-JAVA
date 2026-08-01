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

            // Display records before update
            System.out.println("STUDENT TABLE BEFORE UPDATE");
            System.out.println("-------------------------------------------");
            System.out.printf("%-10s %-15s %-15s\n", "RollNo", "Name", "Address");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-15s %-15s\n",
                        rs.getInt("RollNo"),
                        rs.getString("Name"),
                        rs.getString("Address"));
            }

            // Delete last row
            rs.last();
            rs.deleteRow();
            System.out.println("\nLast student record deleted successfully.");

            // Insert new row
            rs.moveToInsertRow();
            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");
            rs.insertRow();
            System.out.println("New student record inserted successfully.");

            // Display records after update
            rs = st.executeQuery("SELECT * FROM Student");

            System.out.println("\nSTUDENT TABLE AFTER UPDATE");
            System.out.println("-------------------------------------------");
            System.out.printf("%-10s %-15s %-15s\n", "RollNo", "Name", "Address");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-15s %-15s\n",
                        rs.getInt("RollNo"),
                        rs.getString("Name"),
                        rs.getString("Address"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
