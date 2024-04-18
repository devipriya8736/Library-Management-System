package lms_package;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class code {
     ImageIcon icon() {
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\icon.jpg");
        Image img = icon.getImage();
        img = img.getScaledInstance(26, 26, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

     ImageIcon background() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.8);
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\gui_background.jpeg");
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

     ImageIcon background1() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.8);
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\app.jpg");
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

     ImageIcon background2() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.8);
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\users.jpeg");
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

     ImageIcon user() {
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\user.png");
        Image img = icon.getImage();
        img = img.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }

     public Connection con = null;
     JFrame main_frame;
     String UN = null;
     String AUN = null;
     JFrame main_frame1;
     JFrame admin_frame;
     JFrame user_frame;
     Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
     Border border1 = BorderFactory.createLineBorder(Color.BLACK, 2);
     Color lightBlue = new Color(13, 110, 253);
     Color light = new Color(68, 114, 125);
     Font fontsize = new Font("Arial", Font.PLAIN, 20);
     Font fontsize1 = new Font("Arial", Font.PLAIN, 18);
     LocalDate today = LocalDate.now();
     String userreset = " ";
     String emailreset = " ";

     public Connection connect() {
        try {
            System.out.println("Connecting...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Dharan@123");
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
