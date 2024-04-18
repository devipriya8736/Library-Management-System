import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class lmscreation {
    public static void main(String[] args) {
            try {
                System.out.println("Connecting...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Dharan@123");
                System.out.println("Connected to database");
                String q="CREATE TABLE users (user_id INT AUTO_INCREMENT PRIMARY KEY,username VARCHAR(100) NOT NULL UNIQUE,full_name VARCHAR(100) NOT NULL,password VARCHAR(100) NOT NULL,phone VARCHAR(20) NOT NULL,email VARCHAR(100) NOT NULL);";
                PreparedStatement pstm=con.prepareStatement(q);
                pstm.executeUpdate();
                q="CREATE TABLE books (book_id INT PRIMARY KEY,title VARCHAR(100) NOT NULL,author VARCHAR(100) NOT NULL,publisher VARCHAR(100),publisher_date DATE,genre VARCHAR(100),language VARCHAR(50),edition INT,copies INT NOT NULL);";
                pstm=con.prepareStatement(q);
                pstm.executeUpdate();
                q="CREATE TABLE admins (admin_id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(100) NOT NULL, full_name VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, phone VARCHAR(20), email VARCHAR(100) NOT NULL);";
                pstm=con.prepareStatement(q);
                pstm.executeUpdate();
                q="CREATE TABLE bookings (user_id INT NOT NULL, book_id INT NOT NULL, booked_date DATE NOT NULL);";
                pstm=con.prepareStatement(q);
                pstm.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
    }
}
