import java.sql.*;

public class SResultSet {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@12345";

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Database
            Connection con = DriverManager.getConnection(url, user, password);

            // Create Scrollable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Execute Query
            ResultSet rs = st.executeQuery("SELECT * FROM Student");

            System.out.println("Records in Forward Direction:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Address"));
            }

            System.out.println("\nRecords in Backward Direction:");
            while (rs.previous()) {
                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Address"));
            }

            rs.first();
            System.out.println("\nFirst Record:");
            System.out.println(
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));

            rs.last();
            System.out.println("\nLast Record:");
            System.out.println(
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));

            rs.relative(-1);
            System.out.println("\nSecond Record From Last:");
            System.out.println(
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));

            rs.absolute(2);
            System.out.println("\nSecond Record From Beginning:");
            System.out.println(
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Address"));

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
