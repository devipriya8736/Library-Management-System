package lms_package;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Userpage {
    private static code obj1 ;
    private static Mainpage obj2 =new Mainpage(null, null, obj1);
    public Userpage(code cod) {
        obj1=cod;
        // obj2 = mainpage;
    }

    void userpage(String user_id) {
        obj1.main_frame1.dispose();
        obj1.user_frame = new JFrame("Library Management System User");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.95);
        obj1.user_frame.setSize(width, height);
        obj1.user_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel btnpanel = new JPanel();
        btnpanel.setPreferredSize(new Dimension(width, 80));
        btnpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel btnpanel2 = new JPanel(new BorderLayout());
        btnpanel.setBackground(obj1.light);
        btnpanel2.setBackground(obj1.light);
        btnpanel2.setPreferredSize(new Dimension(width, 80));
        btnpanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ImageIcon icon = obj1.user();
        JButton user = new JButton(icon);
        user.setBorder(null);
        user.setFocusable(false);
        user.setContentAreaFilled(false);
        user.setCursor(new Cursor(Cursor.HAND_CURSOR));
        user.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 60));

        JButton bt1 = new JButton("🡨");
        bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj2.gui();
                obj1.user_frame.dispose();
                obj1.main_frame.dispose();
                obj1.main_frame = null;
            }
        });
        bt1.setFocusable(false);
        bt1.setContentAreaFilled(false);

        JButton btn1 = new JButton("Return Book");
        btn1.setPreferredSize(new Dimension(150, 45));
        Font font = btn1.getFont();
        float newSize = font.getSize() + 5;
        bt1.setFont(font.deriveFont(newSize + 4));
        btn1.setFont(font.deriveFont(newSize));
        btn1.setFocusable(false);
        JButton btn2 = new JButton("Renew Book");
        btn2.setPreferredSize(new Dimension(150, 45));
        btn2.setFont(font.deriveFont(newSize));
        btn2.setFocusable(false);
        JButton btn3 = new JButton("Borrow Book");
        btn3.setPreferredSize(new Dimension(150, 45));
        btn3.setFont(font.deriveFont(newSize));
        btn3.setFocusable(false);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame == null) {
                    obj1.main_frame = return_book();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = return_book();
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame == null) {
                    obj1.main_frame = renew_book();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = renew_book();
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame == null) {
                    obj1.main_frame = borrow_book();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = borrow_book();
                }
            }
        });
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn1.setBorder(obj1.border);
        btn2.setBorder(obj1.border);
        btn3.setBorder(obj1.border);
        btn1.setBackground(obj1.lightBlue);
        btn2.setBackground(obj1.lightBlue);
        btn3.setBackground(obj1.lightBlue);
        btn1.setForeground(Color.WHITE);
        btn2.setForeground(Color.WHITE);
        btn3.setForeground(Color.WHITE);
        btn1.setPreferredSize(new Dimension(150, 55));
        btn3.setPreferredSize(new Dimension(150, 55));
        btn2.setPreferredSize(new Dimension(150, 55));

        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj2.profile(user_id);
            }
        });
        btnpanel.add(btn3);
        btnpanel.add(btn2);
        btnpanel.add(btn1);
        ImageIcon background = obj1.background2();
        JLabel label1 = new JLabel(background);
        btnpanel2.add(bt1, BorderLayout.WEST);
        btnpanel2.add(user, BorderLayout.EAST);
        main.add(btnpanel2, BorderLayout.NORTH);
        main.add(label1, BorderLayout.CENTER);
        main.add(btnpanel, BorderLayout.SOUTH);
        obj1.user_frame.add(main);
        obj1.user_frame.setVisible(true);
    }

    JFrame renew_book() {
        JFrame frame = new JFrame("Renew Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(300, 150));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField usernameField = new JTextField();
        usernameField.setFont(obj1.fontsize);
        usernameField.setBorder(obj1.border1);
        JButton button = new JButton("Renew Book");
        button.setFont(obj1.fontsize);
        button.setBorder(obj1.border);
        button.setForeground(Color.WHITE);
        button.setBackground(obj1.lightBlue);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate(usernameField.getText()) && validatebooked_db(obj1.UN, usernameField.getText())) {
                    long fine = 0;
                    String book_id = usernameField.getText();
                    fine = fineCalc1(book_id);
                    if (fine == 0) {
                        ImageIcon icon = obj1.icon();
                        JOptionPane.showMessageDialog(frame, "Succesfully renewed", "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Succesfully renewed with a fine of " + fine, "Succes",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    try {
                        if (obj1.con == null || obj1.con.isClosed()) {
                            obj1.con = obj1.connect();
                        }
                        String q = "update booked set booked_date=? where user_id=(select user_id as user_id from users where username=?) and book_id=?";
                        PreparedStatement pstm = obj1.con.prepareStatement(q);
                        pstm.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                        pstm.setString(2, obj1.UN);
                        pstm.setString(3, book_id);
                        pstm.executeUpdate();
                    } catch (Exception l) {
                        l.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid book id", "Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(obj1.fontsize);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel());
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    long fineCalc1(String book_id) {
        LocalDate today = LocalDate.now();
        LocalDate date = null;
        long fine = 0;
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con = obj1.connect();
            }
            String q = "select booked_date from booked where user_id=(select user_id as user_id from users where username=?) and book_id=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
            pstm.setString(1, obj1.UN);
            pstm.setString(2, book_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Date sqlDate = rs.getDate("booked_date");
                date = sqlDate.toLocalDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (date != null) {
            long daysBetween = ChronoUnit.DAYS.between(date, today);
            if (daysBetween > 52 && daysBetween <= 72) {
                fine = 5 * (daysBetween - 28);
            } else if (daysBetween > 42 && daysBetween <= 52) {
                fine = 20;
            } else if (daysBetween > 28 && daysBetween <= 42) {
                fine = 10;
            } else if (daysBetween == 28) {
                fine = 0;
            }
        }
        return fine;
    }

    JFrame return_book() {
        JFrame frame = new JFrame("Return Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(300, 150));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField usernameField = new JTextField();
        usernameField.setFont(obj1.fontsize);
        usernameField.setBorder(obj1.border1);
        JButton button = new JButton("Return Book");
        button.setFont(obj1.fontsize);
        button.setBorder(obj1.border);
        button.setForeground(Color.WHITE);
        button.setBackground(obj1.lightBlue);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate(usernameField.getText()) && validatebooked_db(obj1.UN, usernameField.getText())) {
                    long fine = 0;
                    String book_id = usernameField.getText();
                    fine = fineCalc(book_id);
                    if (fine == 0) {
                        ImageIcon icon = obj1.icon();
                        JOptionPane.showMessageDialog(frame, "Succesfully return", "Succes",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Succesfully return with a fine of" + fine, "Succes",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    try {
                        if (obj1.con == null || obj1.con.isClosed()) {
                            obj1.con = obj1.connect();
                        }
                        String q = "delete from booked where book_id=? and user_id=(select user_id as user_id from users where username=?)";
                        PreparedStatement pstm = obj1.con.prepareStatement(q);
                        pstm.setString(1, book_id);
                        pstm.setString(2, obj1.UN);
                        pstm.executeUpdate();
                        int currentCopies = 0;
                        q = "Select copies from books where book_id=?";
                        PreparedStatement pstm1 = obj1.con.prepareStatement(q);
                        pstm1.setString(1, book_id);
                        ResultSet rs = pstm1.executeQuery();
                        if (rs.next()) {
                            currentCopies = rs.getInt("copies");
                        }
                        int newCopies = currentCopies + 1;
                        q = "Update books set copies=? where book_id=?";
                        PreparedStatement pstm2 = obj1.con.prepareStatement(q);
                        pstm2.setInt(1, newCopies);
                        pstm2.setString(2, book_id);
                        pstm2.executeUpdate();
                    } catch (Exception l) {
                        l.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid book id", "Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(obj1.fontsize);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel());
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    boolean validatebooked_db(String username, String book_id) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con = obj1.connect();
            }
            String q = "Select user_id,book_id from booked where book_id=? and user_id=(select user_id from users where username=?)";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
            pstm.setString(1, book_id);
            pstm.setString(2, username);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    boolean validate(String string) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con = obj1.connect();
            }
            String q = "Select book_id from books where book_id=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
            pstm.setString(1, string);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    long fineCalc(String book_id) {
        LocalDate today = LocalDate.now();
        LocalDate date = null;
        long fine = 0;
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con = obj1.connect();
            }
            String q = "select booked_date from booked where user_id=(select user_id as user_id from users where username=?) and book_id=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
            pstm.setString(1, obj1.UN);
            pstm.setString(2, book_id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Date sqlDate = rs.getDate("booked_date");
                date = sqlDate.toLocalDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (date != null) {
            long daysBetween = ChronoUnit.DAYS.between(date, today);
            if (daysBetween > 38 && daysBetween <= 58) {
                fine = 5 * (daysBetween - 28);
            } else if (daysBetween > 28 && daysBetween <= 38) {
                fine = 20;
            } else if (daysBetween == 28) {
                fine = 0;
            }
        }
        return fine;
    }

    JFrame borrow_book() {
        JFrame frame = new JFrame("Borrow Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(300, 150));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField usernameField = new JTextField();
        usernameField.setBorder(obj1.border1);
        usernameField.setFont(obj1.fontsize1);

        JButton button = new JButton("Borrow Book");
        button.setBorder(obj1.border);
        button.setFont(obj1.fontsize);
        button.setForeground(Color.WHITE);
        button.setBackground(obj1.lightBlue);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 45));
        button.setFocusable(false);

        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(obj1.fontsize);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel());
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    String bookid = usernameField.getText();
                    String q = "select count(*) as count from booked where book_id=? and user_id=(select user_id as user_id from users where username=?)";
                    PreparedStatement pstm = obj1.con.prepareStatement(q);
                    pstm.setString(1, bookid);
                    pstm.setString(2, obj1.UN);
                    ResultSet rs = pstm.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        if (count == 0) {
                            int currentCopies = 0;
                            q = "Select copies from books where book_id=?";
                            PreparedStatement pstm1 = obj1.con.prepareStatement(q);
                            pstm1.setString(1, bookid);
                            ResultSet rs1 = pstm1.executeQuery();
                            if (rs1.next()) {
                                currentCopies = rs1.getInt("copies");
                            }
                            if (currentCopies != 0) {
                                q = "insert into booked(user_id,book_id,booked_date) select user_id as user_id,?,? from users where username=?";
                                PreparedStatement pstm3 = obj1.con.prepareStatement(q);
                                pstm3.setString(1, usernameField.getText());
                                pstm3.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                                pstm3.setString(3, obj1.UN);
                                pstm3.executeUpdate();
                                int newCopies = currentCopies - 1;
                                q = "Update books set copies=? where book_id=?";
                                PreparedStatement pstm2 = obj1.con.prepareStatement(q);
                                pstm2.setInt(1, newCopies);
                                pstm2.setString(2, bookid);
                                pstm2.executeUpdate();
                                ImageIcon icon = obj1.icon();
                                JOptionPane.showMessageDialog(frame, "Succesfully borrowed", "Success",
                                        JOptionPane.INFORMATION_MESSAGE, icon);
                                frame.dispose();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Out of stock!!", "Disclaimer",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Already borrowed. So,cannot borrow it again.",
                                    "Disclaimer",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (Exception l) {
                    l.printStackTrace();
                }
            }
        });
        return frame;
    }

}
