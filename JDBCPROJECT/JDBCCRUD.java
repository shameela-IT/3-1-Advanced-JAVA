import java.sql.*;

public class JDBCCRUD {

public static void main(String args[]) {  

    String url = "jdbc:mysql://localhost:3306/studentdb";  
    String user = "debian-sys-maint";  
    String password = "NJG5cTrYVyVd5QCJ";  

    try {  

        Class.forName("com.mysql.cj.jdbc.Driver");  

        Connection con = DriverManager.getConnection(url, user, password);  

        Statement st = con.createStatement();  

        st.executeUpdate("CREATE TABLE IF NOT EXISTS student(id INT PRIMARY KEY,name VARCHAR(50),marks INT)");  

        st.executeUpdate("INSERT INTO student VALUES(101,'Rahul',90)");  

        st.executeUpdate("UPDATE student SET marks=95 WHERE id=101");  

        ResultSet rs = st.executeQuery("SELECT * FROM student");  

        while(rs.next())  
        {  
            System.out.println(  
                rs.getInt("id")+" "+  
                rs.getString("name")+" "+  
                rs.getInt("marks"));  
        }  

        st.executeUpdate("DELETE FROM student WHERE id=101");  

        System.out.println("CRUD Operations Completed Successfully");  

        con.close();  

    } catch(Exception e) {  
        e.printStackTrace();  
    }  
}

}
