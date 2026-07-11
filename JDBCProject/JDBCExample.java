import java.sql.*;

public class JDBCExample {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "debian-sys-maint";
    static final String PASSWORD = "SytoCzq6cBRYrmul";

    public static void main(String args[]) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            st.executeUpdate("INSERT INTO student VALUES(10,'Ravi',85)");
            st.executeUpdate("INSERT INTO student VALUES(30,'Rani',75)");
            st.executeUpdate("INSERT INTO student VALUES(20,'Ram',95)");
           
            st.executeUpdate("UPDATE student SET marks=95 WHERE id=10");

            ResultSet rs = st.executeQuery("SELECT * FROM student");

            while(rs.next()) {

                System.out.println(
                    rs.getInt("id")+" "+
                    rs.getString("name")+" "+
                    rs.getInt("marks"));
            }

            st.executeUpdate("DELETE FROM student WHERE id=10");

            con.close();

            System.out.println("Database operations completed successfully.");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
