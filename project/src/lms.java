import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;
import java.awt.*;

public class lms {

    static ImageIcon icon() {
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\icon.jpg");
        Image img = icon.getImage();
        img = img.getScaledInstance(26, 26, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

    static ImageIcon background() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.8);
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\gui_background.jpeg");
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

    static ImageIcon background1() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.8);
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\app.jpg");
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

    static ImageIcon background2() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.8);
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\users.jpeg");
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        return icon;
    }

    static ImageIcon user() {
        ImageIcon icon = new ImageIcon("C:\\Dharan\\Dharan\\JDBC\\project\\lib\\user.png");
        Image img = icon.getImage();
        img = img.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }

    static Connection con = null;
    static JFrame main_frame;
    static String UN = null;
    static String AUN = null;
    static JFrame main_frame1;
    static JFrame admin_frame;
    static JFrame user_frame;
    static Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
    static Border border1 = BorderFactory.createLineBorder(Color.BLACK, 2);
    static Color lightBlue = new Color(13, 110, 253);
    static Color light = new Color(68, 114, 125);
    static Font fontsize = new Font("Arial", Font.PLAIN, 20);
    static Font fontsize1 = new Font("Arial", Font.PLAIN, 18);
    static LocalDate today = LocalDate.now();
    static String userreset = " ";
    static String emailreset = " ";

    static Connection connect() {
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

    static void gui() {
        main_frame1 = new JFrame("Library Management System");
        main_frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.95);
        main_frame1.setSize(width, height);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JTextArea textarea = new JTextArea();
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);

        JPanel btnpanel = new JPanel();
        JPanel userJPanel = new JPanel();
        userJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon img = user();
        JButton user = new JButton(img);
        user.setBorder(null);
        user.setFocusable(false);
        user.setContentAreaFilled(false);
        user.setCursor(new Cursor(Cursor.HAND_CURSOR));
        user.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 60));
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(100, 60));
        Font font = login.getFont();
        float newSize = font.getSize() + 6;
        login.setFont(font.deriveFont(newSize));
        login.setFocusable(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton signup = new JButton("Signup");
        signup.setPreferredSize(new Dimension(100, 60));
        signup.setFont(font.deriveFont(newSize));
        signup.setFocusable(false);
        signup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton admin_login = new JButton("Admin Login");
        admin_login.setPreferredSize(new Dimension(140, 60));
        admin_login.setFont(font.deriveFont(newSize));
        admin_login.setFocusable(false);
        admin_login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setBackground(lightBlue);
        login.setForeground(Color.WHITE);
        login.setBorder(border);
        signup.setBackground(lightBlue);
        signup.setForeground(Color.WHITE);
        signup.setBorder(border);
        admin_login.setBackground(lightBlue);
        admin_login.setForeground(Color.WHITE);
        admin_login.setBorder(border);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (main_frame == null) {
                    main_frame = log();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = log();
                }
            }
        });
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (main_frame == null) {
                    main_frame = SignUp();
                    ;
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = SignUp();
                    ;
                }
            }
        });
        admin_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (main_frame == null) {
                    main_frame = log_admin();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = log_admin();
                }
            }
        });
        if (UN == null) {
            user.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (main_frame == null) {
                        main_frame = log();
                    } else {
                        main_frame.dispose();
                        main_frame = null;
                        main_frame = log();
                    }
                }
            });
        } else {

            user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   profile(UN);
                }
            });

        }
        ImageIcon background = background();
        JLabel label1 = new JLabel(background);
        btnpanel.add(login);
        btnpanel.add(signup);
        btnpanel.add(admin_login);
        userJPanel.add(user);
        btnpanel.setBackground(light);
        btnpanel.setBorder(border);
        userJPanel.setBackground(light);
        userJPanel.setBorder(border);
        main.add(userJPanel, BorderLayout.NORTH);
        main.add(label1, BorderLayout.CENTER);
        main.add(btnpanel, BorderLayout.SOUTH);

        main_frame1.add(main);
        main_frame1.setVisible(true);
    }

    static JFrame SignUp() {
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField usernameField = new JTextField();
        JTextField fullNameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField phoneNumberField = new JTextField();
        JTextField emailField = new JTextField();

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (passwordValidator(new String(passwordField.getPassword()))) {
                    passwordField.setForeground(new Color(4, 124, 26));
                } else {
                    passwordField.setForeground(Color.red);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (passwordValidator(new String(passwordField.getPassword()))) {
                    passwordField.setForeground(new Color(4, 124, 26));
                } else {
                    passwordField.setForeground(Color.red);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (passwordValidator(new String(passwordField.getPassword()))) {
                    passwordField.setForeground(new Color(4, 124, 26));
                } else {
                    passwordField.setForeground(Color.red);
                }
            }
        });

        phoneNumberField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (phoneNoValidator(phoneNumberField.getText())) {
                    phoneNumberField.setForeground(new Color(4, 124, 26));
                } else {
                    phoneNumberField.setForeground(Color.red);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (phoneNoValidator(phoneNumberField.getText())) {
                    phoneNumberField.setForeground(new Color(4, 124, 26));
                } else {
                    phoneNumberField.setForeground(Color.red);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (phoneNoValidator(phoneNumberField.getText())) {
                    phoneNumberField.setForeground(new Color(4, 124, 26));
                } else {
                    phoneNumberField.setForeground(Color.red);
                }
            }
        });

        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (emailValidator(emailField.getText())) {
                    emailField.setForeground(new Color(4, 124, 26));
                } else {
                    emailField.setForeground(Color.red);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (emailValidator(emailField.getText())) {
                    emailField.setForeground(new Color(4, 124, 26));
                } else {
                    emailField.setForeground(Color.red);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (emailValidator(emailField.getText())) {
                    emailField.setForeground(new Color(4, 124, 26));
                } else {
                    emailField.setForeground(Color.red);
                }
            }
        });

        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!userValidation(usernameField.getText())) {
                    usernameField.setForeground(new Color(4, 124, 26));
                } else {
                    usernameField.setForeground(Color.red);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!userValidation(usernameField.getText())) {
                    usernameField.setForeground(new Color(4, 124, 26));
                } else {
                    usernameField.setForeground(Color.red);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!userValidation(usernameField.getText())) {
                    usernameField.setForeground(new Color(4, 124, 26));
                } else {
                    usernameField.setForeground(Color.red);
                }
            }
        });

        usernameField.setBorder(border1);
        usernameField.setFont(fontsize);
        usernameField.setText("Enter the Username");
        usernameField.setForeground(Color.lightGray);
        fullNameField.setBorder(border1);
        fullNameField.setFont(fontsize);
        fullNameField.setText("Enter the Full Name");
        fullNameField.setForeground(Color.lightGray);
        passwordField.setBorder(border1);
        passwordField.setFont(fontsize);
        phoneNumberField.setBorder(border1);
        phoneNumberField.setFont(fontsize);
        phoneNumberField.setText("Enter the Phone Number");
        phoneNumberField.setForeground(Color.lightGray);
        emailField.setBorder(border1);
        emailField.setFont(fontsize);
        emailField.setText("example@example.com");
        emailField.setForeground(Color.lightGray);
        JButton signUpButton = new JButton("SignUp");
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font font = signUpButton.getFont();
        float newSize = font.getSize() + 3;
        signUpButton.setPreferredSize(new Dimension(150, 45));
        signUpButton.setFont(font.deriveFont(newSize));
        JButton signup = new JButton("Login");
        signup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signup.setPreferredSize(new Dimension(150, 45));
        signup.setFont(font.deriveFont(newSize));
        signUpButton.setFocusable(false);
        signup.setFocusable(false);
        formPanel.add(new JLabel("Username:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Full Name:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(fullNameField);
        formPanel.add(new JLabel("Password:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Phone:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Email:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(emailField);
        formPanel.add(signup);
        formPanel.add(signUpButton);
        signUpButton.setBorder(border);
        signUpButton.setBackground(lightBlue);
        signUpButton.setForeground(Color.white);
        signUpButton.setFont(fontsize);
        signup.setBorder(border);
        signup.setBackground(lightBlue);
        signup.setForeground(Color.white);
        signup.setFont(fontsize);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String fullName = fullNameField.getText();
                String password = new String(passwordField.getPassword());
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                signup_data(username, fullName, phoneNumber, email, password, frame);
            }
        });
        signup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                log();
            }

        });
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Enter the Username")) {
                    usernameField.setForeground(Color.BLACK);
                    usernameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Enter the Username");
                    usernameField.setForeground(Color.lightGray);
                }
            }
        });
        fullNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fullNameField.getText().equals("Enter the Full Name")) {
                    fullNameField.setText("");
                    fullNameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (fullNameField.getText().isEmpty()) {
                    fullNameField.setForeground(Color.lightGray);
                    fullNameField.setText("Enter the Full Name");
                }
            }
        });
        phoneNumberField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneNumberField.getText().equals("Enter the Phone Number")) {
                    phoneNumberField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneNumberField.getText().isEmpty()) {
                    phoneNumberField.setText("Enter the Phone Number");
                    phoneNumberField.setForeground(Color.lightGray);
                }
            }
        });
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("example@example.com")) {
                    emailField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("example@example.com");
                    emailField.setForeground(Color.lightGray);
                }
            }
        });

        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    static JFrame log_admin() {
        if (admin_frame == null) {
            JFrame frame = new JFrame("Admin Login");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setPreferredSize(new Dimension(550, 200));
            frame.pack();
            frame.setLocationRelativeTo(null);

            JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(formPanel, BorderLayout.CENTER);

            JTextField usernameField = new JTextField();

            usernameField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (adminValidation(usernameField.getText())) {
                        usernameField.setForeground(new Color(4, 124, 26));
                    } else {
                        usernameField.setForeground(Color.red);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (adminValidation(usernameField.getText())) {
                        usernameField.setForeground(new Color(4, 124, 26));
                    } else {
                        usernameField.setForeground(Color.red);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (adminValidation(usernameField.getText())) {
                        usernameField.setForeground(new Color(4, 124, 26));
                    } else {
                        usernameField.setForeground(Color.red);
                    }
                }
            });

            usernameField.setBorder(border1);
            usernameField.setFont(fontsize);
            usernameField.setText("Enter the Admin Username");
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBorder(border1);
            passwordField.setFont(fontsize);
            JButton button = new JButton("Login");
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorder(border);
            button.setBackground(lightBlue);
            button.setForeground(Color.white);
            Font font = button.getFont();
            float newSize = font.getSize() + 3;
            button.setPreferredSize(new Dimension(150, 45));
            button.setFont(font.deriveFont(newSize));
            button.setFocusable(false);
            button.setFocusable(false);

            formPanel.add(new JLabel("Username for Admin:") {
                {
                    setFont(font.deriveFont(newSize));
                }
            });
            formPanel.add(usernameField);
            formPanel.add(new JLabel("Password for Admin:") {
                {
                    setFont(font.deriveFont(newSize));
                }
            });
            formPanel.add(passwordField);
            formPanel.add(new JButton("Cancel") {
                {
                    setFont(font.deriveFont(newSize));
                    setFocusable(false);
                    setBorder(border);
                    setForeground(Color.white);
                    setBackground(new Color(255, 70, 100));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                        }
                    });
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            });
            ;
            formPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    AUN = username;
                    String password = new String(passwordField.getPassword());
                    if (admin_data(username, password)) {
                        // ImageIcon icon = icon();
                        // JOptionPane.showMessageDialog(frame, "Login successful as Admin!...",
                        // "Success",
                        // JOptionPane.INFORMATION_MESSAGE, icon);
                        adminpage(username);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password or Username", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            usernameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (usernameField.getText().equals("Enter the Admin Username")) {
                        usernameField.setText("");
                        usernameField.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (usernameField.getText().isEmpty()) {
                        usernameField.setForeground(Color.lightGray);
                        usernameField.setText("Enter the Admin Username");
                    }
                }
            });
            frame.setResizable(false);
            frame.setVisible(true);
            return frame;
        } else {
            adminpage(AUN);
            main_frame1.dispose();
        }
        return null;
    }

    static JFrame log() {
        if (user_frame == null) {
            JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setPreferredSize(new Dimension(500, 250));
            frame.pack();
            frame.setLocationRelativeTo(null);

            JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
            formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(formPanel, BorderLayout.CENTER);

            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();

            usernameField.setBorder(border1);
            usernameField.setFont(fontsize);
            passwordField.setBorder(border1);
            passwordField.setFont(fontsize);
            usernameField.setText("Enter the Username");
            usernameField.setForeground(Color.lightGray);

            usernameField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (userValidation(usernameField.getText())) {
                        usernameField.setForeground(new Color(4, 124, 26));
                    } else {
                        usernameField.setForeground(Color.red);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (userValidation(usernameField.getText())) {
                        usernameField.setForeground(new Color(4, 124, 26));
                    } else {
                        usernameField.setForeground(Color.red);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (userValidation(usernameField.getText())) {
                        usernameField.setForeground(new Color(4, 124, 26));
                    } else {
                        usernameField.setForeground(Color.red);
                    }
                }
            });

            JButton button = new JButton("Login");
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Font font = button.getFont();
            float newSize = font.getSize() + 3;
            button.setPreferredSize(new Dimension(150, 45));
            button.setFont(font.deriveFont(newSize));
            button.setFocusable(false);
            button.setBorder(border);
            button.setBackground(lightBlue);
            button.setForeground(Color.white);
            JButton click = new JButton("Forgot Password");
            click.setCursor(new Cursor(Cursor.HAND_CURSOR));
            click.setForeground(Color.red);
            click.setFont(font.deriveFont(newSize));
            click.setFocusable(false);
            click.setContentAreaFilled(false);
            click.setBorder(null);
            JButton signup = new JButton("SignUp");
            signup.setCursor(new Cursor(Cursor.HAND_CURSOR));
            signup.setBorder(border);
            signup.setBackground(lightBlue);
            signup.setForeground(Color.white);
            signup.setFocusable(false);
            signup.setPreferredSize(new Dimension(150, 45));
            signup.setFont(font.deriveFont(newSize));

            formPanel.add(new JLabel("Username:") {
                {
                    setFont(fontsize1);
                }
            });
            formPanel.add(usernameField);
            formPanel.add(new JLabel("Password:") {
                {
                    setFont(fontsize1);
                }
            });
            formPanel.add(passwordField);
            formPanel.add(new JLabel());
            formPanel.add(click);
            formPanel.add(signup);
            formPanel.add(button);
            usernameField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (usernameField.getText().equals("Enter the Username")) {
                        usernameField.setText("");
                        usernameField.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (usernameField.getText().isEmpty()) {
                        usernameField.setText("Enter the Username");
                        usernameField.setForeground(Color.lightGray);
                    }
                }
            });
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    UN = username;
                    String password = new String(passwordField.getPassword());
                    if (user_data(username, password)) {

                        frame.dispose();
                        userpage(username);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password or Username", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            click.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (main_frame == null) {
                        main_frame = forgot_password();
                    } else {
                        main_frame.dispose();
                        main_frame = null;
                        main_frame = forgot_password();
                    }
                }
            });
            signup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    SignUp();
                }
            });
            frame.setResizable(false);

            frame.setVisible(true);
            return frame;
        } else {
            userpage(UN);
            main_frame1.dispose();
        }
        return null;
    }

    static void check(JFrame frame, JTextField text1, JTextField text2, JPasswordField text3, JPasswordField text4,
            JLabel label1, JLabel label2, JPanel formPanel, JButton reset, JButton cancel) {
        try {
            String q = "select * from users where username=? and email=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, text1.getText());
            pstm.setString(2, text2.getText());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                frame.setPreferredSize(new Dimension(500, 300));
                frame.pack();
                formPanel.setLayout(new GridLayout(5, 2, 5, 5));
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                reset.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reset_password(text1, text2, text3, text4, frame);
                    }
                });
                formPanel.add(label1);
                formPanel.add(text3);
                formPanel.add(label2);
                formPanel.add(text4);
                formPanel.add(cancel);
                formPanel.add(reset);
                formPanel.revalidate();
                formPanel.repaint();
                text3.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        if (passwordValidator(new String(text3.getPassword()))) {
                            text3.setForeground(new Color(4, 124, 26));
                        } else {
                            text3.setForeground(Color.red);
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        if (passwordValidator(new String(text3.getPassword()))) {
                            text3.setForeground(new Color(4, 124, 26));
                        } else {
                            text3.setForeground(Color.red);
                        }

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        if (passwordValidator(new String(text3.getPassword()))) {
                            text3.setForeground(new Color(4, 124, 26));
                        } else {
                            text3.setForeground(Color.red);
                        }

                    }
                });
                text4.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        String newp = new String(text3.getPassword());
                        String cp = new String(text4.getPassword());
                        if (newp.equals(cp)) {
                            text4.setForeground(new Color(4, 124, 26));
                        } else {
                            text4.setForeground(Color.red);
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String newp = new String(text3.getPassword());
                        String cp = new String(text4.getPassword());
                        if (newp.equals(cp)) {
                            text4.setForeground(new Color(4, 124, 26));
                        } else {
                            text4.setForeground(Color.red);
                        }

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        String newp = new String(text3.getPassword());
                        String cp = new String(text4.getPassword());
                        if (newp.equals(cp)) {
                            text4.setForeground(new Color(4, 124, 26));
                        } else {
                            text4.setForeground(Color.red);
                        }

                    }
                });
            }
        } catch (Exception el) {
            // TODO: handle exception
        }
    }

    static JFrame forgot_password() {
        JFrame frame = new JFrame("Forgot Password");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500, 150));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JPasswordField text3 = new JPasswordField();
        JPasswordField text4 = new JPasswordField();
        text1.setBorder(border1);
        text2.setBorder(border1);
        text3.setBorder(border1);
        text4.setBorder(border1);
        text1.setFont(fontsize1);
        text2.setFont(fontsize1);
        text3.setFont(fontsize1);
        text4.setFont(fontsize1);
        JButton cancel = new JButton("Cancel");
        JButton reset = new JButton("Reset Password");
        JLabel label1 = new JLabel("New Password");
        JLabel label2 = new JLabel("Conform Password");
        label1.setFont(fontsize);
        label2.setFont(fontsize);
        cancel.setFont(fontsize1);
        reset.setFont(fontsize1);
        cancel.setBorder(border);
        reset.setBorder(border);
        cancel.setBackground(new Color(255, 70, 100));
        reset.setBackground(lightBlue);
        cancel.setForeground(Color.WHITE);
        reset.setForeground(Color.WHITE);
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reset.setCursor(new Cursor(Cursor.HAND_CURSOR));

        text2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (emailValidationdb(text2.getText())) {
                    text2.setForeground(new Color(4, 124, 26));
                    if (userValidation(text1.getText()) && emailValidationdb(text2.getText())) {
                        check(frame, text1, text2, text3, text4, label1, label2, formPanel, reset, cancel);
                    }
                } else {
                    text2.setForeground(Color.red);
                    frame.setPreferredSize(new Dimension(500, 150));
                    frame.pack();
                    formPanel.setLayout(new GridLayout(2, 2, 5, 5));
                    formPanel.remove(text3);
                    formPanel.remove(label1);
                    formPanel.remove(text4);
                    formPanel.remove(label2);
                    formPanel.remove(reset);
                    formPanel.remove(cancel);
                    formPanel.revalidate();
                    formPanel.repaint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (emailValidationdb(text2.getText())) {
                    text2.setForeground(new Color(4, 124, 26));
                    if (userValidation(text1.getText()) && emailValidationdb(text2.getText())) {
                        check(frame, text1, text2, text3, text4, label1, label2, formPanel, reset, cancel);
                    }
                } else {
                    text2.setForeground(Color.red);
                    frame.setPreferredSize(new Dimension(500, 150));
                    frame.pack();
                    formPanel.setLayout(new GridLayout(2, 2, 5, 5));
                    formPanel.remove(text3);
                    formPanel.remove(label1);
                    formPanel.remove(text4);
                    formPanel.remove(label2);
                    formPanel.remove(reset);
                    formPanel.remove(cancel);
                    formPanel.revalidate();
                    formPanel.repaint();
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (emailValidationdb(text2.getText())) {
                    text2.setForeground(new Color(4, 124, 26));
                    if (userValidation(text1.getText()) && emailValidationdb(text2.getText())) {
                        check(frame, text1, text2, text3, text4, label1, label2, formPanel, reset, cancel);
                    }
                } else {
                    text2.setForeground(Color.red);
                    frame.setPreferredSize(new Dimension(500, 150));
                    frame.pack();
                    formPanel.setLayout(new GridLayout(2, 2, 5, 5));
                    formPanel.remove(text3);
                    formPanel.remove(label1);
                    formPanel.remove(text4);
                    formPanel.remove(label2);
                    formPanel.remove(reset);
                    formPanel.remove(cancel);
                    formPanel.revalidate();
                    formPanel.repaint();
                }

            }
        });

        text1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (userValidation(text1.getText())) {
                    text1.setForeground(new Color(4, 124, 26));
                    if (userValidation(text1.getText()) && emailValidationdb(text2.getText())) {
                        check(frame, text1, text2, text3, text4, label1, label2, formPanel, reset, cancel);
                    }
                } else {
                    text1.setForeground(Color.red);
                    frame.setPreferredSize(new Dimension(500, 150));
                    frame.pack();
                    formPanel.setLayout(new GridLayout(2, 2, 5, 5));
                    formPanel.remove(text3);
                    formPanel.remove(label1);
                    formPanel.remove(text4);
                    formPanel.remove(label2);
                    formPanel.remove(reset);
                    formPanel.remove(cancel);
                    formPanel.revalidate();
                    formPanel.repaint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (userValidation(text1.getText())) {
                    text1.setForeground(new Color(4, 124, 26));
                    if (userValidation(text1.getText()) && emailValidationdb(text2.getText())) {
                        check(frame, text1, text2, text3, text4, label1, label2, formPanel, reset, cancel);
                    }
                } else {
                    text1.setForeground(Color.red);
                    frame.setPreferredSize(new Dimension(500, 150));
                    frame.pack();
                    formPanel.setLayout(new GridLayout(2, 2, 5, 5));
                    formPanel.remove(text3);
                    formPanel.remove(label1);
                    formPanel.remove(text4);
                    formPanel.remove(label2);
                    formPanel.remove(reset);
                    formPanel.remove(cancel);
                    formPanel.revalidate();
                    formPanel.repaint();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (userValidation(text1.getText())) {
                    text1.setForeground(new Color(4, 124, 26));
                    if (userValidation(text1.getText()) && emailValidationdb(text2.getText())) {
                        check(frame, text1, text2, text3, text4, label1, label2, formPanel, reset, cancel);
                    }
                } else {
                    text1.setForeground(Color.red);
                    frame.setPreferredSize(new Dimension(500, 150));
                    frame.pack();
                    formPanel.setLayout(new GridLayout(2, 2, 5, 5));
                    formPanel.remove(text3);
                    formPanel.remove(label1);
                    formPanel.remove(text4);
                    formPanel.remove(label2);
                    formPanel.remove(reset);
                    formPanel.remove(cancel);
                    formPanel.revalidate();
                    formPanel.repaint();
                }
            }
        });
        formPanel.add(new JLabel("Username") {
            {
                setFont(fontsize);
            }
        });
        formPanel.add(text1);
        formPanel.add(new JLabel("Email") {
            {
                setFont(fontsize);
            }
        });
        formPanel.add(text2);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
        return frame;
    }

    static void reset_password(JTextField text1, JTextField text2, JPasswordField text3, JPasswordField text4,
            JFrame frame) {
        String newp = new String(text3.getPassword());
        String confirm = new String(text4.getPassword());
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            if (newp.equals(confirm)) {
                String q = "select password from users where username=? and email=? ";
                PreparedStatement pstm = con.prepareStatement(q);
                pstm.setString(1, text1.getText());
                pstm.setString(2, text2.getText());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    boolean valid = passwordValidator(newp);
                    if (!newp.isBlank() && valid) {
                        if (password.equals(newp)) {
                            JOptionPane.showMessageDialog(frame,
                                    "The new password matches the old password.Please change it", "Disclaimer",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            q = "update users set password=? where username=? and email=?";
                            PreparedStatement pstm1 = con.prepareStatement(q);
                            pstm1.setString(1, newp);
                            pstm1.setString(2, text1.getText());
                            pstm1.setString(3, text2.getText());
                            pstm1.executeUpdate();
                            ImageIcon icon = icon();
                            JOptionPane.showMessageDialog(frame,
                                    "Successfully password changed", "Success",
                                    JOptionPane.INFORMATION_MESSAGE, icon);
                            frame.dispose();
                            log();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Password should contain \n \t\t• Minimum 8 characters. \n \t\t• Atleast one uppercase letter. \n \t\t• Atleast one special character. \n \t\t• Atleast one number.",
                                "Incorrect password format",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "The new password does not match the confirm password.",
                        "Disclaimer", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception l) {
            l.printStackTrace();
        }

    }

    static boolean admin_data(String name, String loc) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select username,password from admins where username=? and password=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, name);
            pstm.setString(2, loc);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean user_data(String name, String loc) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select username,password from users where username=? and password=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, name);
            pstm.setString(2, loc);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void signup_data(String username, String fullName, String phoneNumber, String email, String password,
            JFrame frame) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            boolean valid = passwordValidator(password);
            boolean valid1 = phoneNoValidator(phoneNumber);
            boolean valid2 = emailValidator(email);
            if (!username.isBlank() && !fullName.isBlank() && !password.isBlank() && !phoneNumber.isBlank()
                    && !email.isBlank() && valid && valid1 && valid2) {
                String q = "insert into users(username,full_name,password,phone,email) values (?,?,?,?,?)";
                PreparedStatement pstm = con.prepareStatement(q);
                pstm.setString(1, username);
                pstm.setString(2, fullName);
                pstm.setString(3, password);
                pstm.setString(4, phoneNumber);
                pstm.setString(5, email);
                pstm.executeUpdate();

                ImageIcon icon = icon();
                JOptionPane.showMessageDialog(frame, "Signup successfully!...", "Success",
                        JOptionPane.INFORMATION_MESSAGE, icon);
                frame.dispose();
                log();

            } else if (valid == false) {
                JOptionPane.showMessageDialog(frame,
                        "Password should contain \n \t\t• Minimum 8 characters. \n \t\t• Atleast one uppercase letter. \n \t\t• Atleast one special character. \n \t\t• Atleast one number.",
                        "Incorrect password format",
                        JOptionPane.ERROR_MESSAGE);
            } else if (valid1 == false) {
                JOptionPane.showMessageDialog(frame, " Phone number should consist of exactly 10 digits.",
                        "Incorrect phone number format",
                        JOptionPane.ERROR_MESSAGE);
            } else if (valid2 == false) {
                JOptionPane.showMessageDialog(frame, "Check the email provided properly.", "Incorrect email format",
                        JOptionPane.ERROR_MESSAGE);
            }

            else {
                JOptionPane.showMessageDialog(frame, "Fill all fields", "Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Duplicate Username or Password", "Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    static boolean passwordValidator(String password) {
        if (password.length() < 8) {
            return false;
        }
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        if (!uppercaseMatcher.find()) {
            return false;
        }
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher specialCharMatcher = specialCharPattern.matcher(password);
        if (!specialCharMatcher.find()) {
            return false;
        }
        Pattern numberPattern = Pattern.compile("[0-9]");
        Matcher numberMatcher = numberPattern.matcher(password);
        if (!numberMatcher.find()) {
            return false;
        }
        return true;
    }

    static boolean phoneNoValidator(String phoneNumber) {
        if (phoneNumber.length() < 10) {
            return false;
        }
        String regex = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            return true;
        } else
            return false;
    }

    static boolean userValidation(String user) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select username from users where username=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, user);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean adminValidation(String user) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select username from admins where username=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, user);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean emailValidationdb(String email) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select email from users where email=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean emailValidator(String email) {
        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    static void profile( String user_id){
        JFrame frame = new JFrame("User Details");

                if (main_frame == null) {
                    main_frame = frame;
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = frame;
                }
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setPreferredSize(new Dimension(400, 420));
                frame.pack();
                frame.setLocationRelativeTo(null);

                JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
                formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                frame.add(formPanel, BorderLayout.CENTER);

                JTextField f1 = new JTextField();
                f1.setEditable(false);
                JTextField f2 = new JTextField();
                f2.setEditable(false);
                JTextField f3 = new JTextField();
                f3.setEditable(false);
                JTextField f4 = new JTextField();
                f4.setEditable(false);
                JTextField f5 = new JTextField();
                f5.setEditable(false);
                JTextField f6 = new JTextField();
                f6.setEditable(false);
                f1.setBorder(border1);
                f2.setBorder(border1);
                f3.setBorder(border1);
                f4.setBorder(border1);
                f5.setBorder(border1);
                f6.setBorder(border1);
                f1.setFont(fontsize);
                f2.setFont(fontsize1);
                f3.setFont(fontsize1);
                f4.setFont(fontsize1);
                f5.setFont(fontsize1);
                f6.setFont(fontsize1);
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String q1 = "select * from users where username=?";
                    PreparedStatement pstm1 = con.prepareStatement(q1);
                    pstm1.setString(1, user_id);
                    ResultSet rs = pstm1.executeQuery();
                    if (rs.next()) {
                        f1.setText(rs.getString(1));
                        f2.setText(rs.getString(2));
                        f3.setText(rs.getString(3));
                        f4.setText(rs.getString(4));
                        f5.setText(rs.getString(5));
                        f6.setText(rs.getString(6));
                    } else {
                    }

                } catch (Exception l) {
                    l.printStackTrace();
                }

                formPanel.add(new JLabel("User Id:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f1);
                formPanel.add(new JLabel("User name:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f2);
                formPanel.add(new JLabel("Full Name:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f3);
                formPanel.add(new JLabel("Password:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f4);
                formPanel.add(new JLabel("Phone number:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f5);
                formPanel.add(new JLabel("Email:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f6);
                formPanel.add(new JLabel());
                formPanel.add(new JButton("Log out") {
                    {
                        setForeground(Color.red);
                        setContentAreaFilled(false);
                        setBorder(null);
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                user_frame.dispose();
                                frame.dispose();
                                UN = null;
                                user_frame = null;
                                main_frame = null;
                                gui();
                            }
                        });
                    }
                });
                frame.setResizable(false);
                frame.setVisible(true);
    }
    
    static void userpage(String user_id) {
        main_frame1.dispose();
        user_frame = new JFrame("Library Management System User");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.95);
        user_frame.setSize(width, height);
        user_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel btnpanel = new JPanel();
        btnpanel.setPreferredSize(new Dimension(width, 80));
        btnpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel btnpanel2 = new JPanel(new BorderLayout());
        btnpanel.setBackground(light);
        btnpanel2.setBackground(light);
        btnpanel2.setPreferredSize(new Dimension(width, 80));
        btnpanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ImageIcon icon = user();
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
                gui();
                user_frame.dispose();
                main_frame.dispose();
                main_frame = null;
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
                if (main_frame == null) {
                    main_frame = return_book();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = return_book();
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (main_frame == null) {
                    main_frame = renew_book();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = renew_book();
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (main_frame == null) {
                    main_frame = borrow_book();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = borrow_book();
                }
            }
        });
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn1.setBorder(border);
        btn2.setBorder(border);
        btn3.setBorder(border);
        btn1.setBackground(lightBlue);
        btn2.setBackground(lightBlue);
        btn3.setBackground(lightBlue);
        btn1.setForeground(Color.WHITE);
        btn2.setForeground(Color.WHITE);
        btn3.setForeground(Color.WHITE);
        btn1.setPreferredSize(new Dimension(150, 55));
        btn3.setPreferredSize(new Dimension(150, 55));
        btn2.setPreferredSize(new Dimension(150, 55));

        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile(user_id);
            }
        });
        btnpanel.add(btn3);
        btnpanel.add(btn2);
        btnpanel.add(btn1);
        ImageIcon background = background2();
        JLabel label1 = new JLabel(background);
        btnpanel2.add(bt1, BorderLayout.WEST);
        btnpanel2.add(user, BorderLayout.EAST);
        main.add(btnpanel2, BorderLayout.NORTH);
        main.add(label1, BorderLayout.CENTER);
        main.add(btnpanel, BorderLayout.SOUTH);
        user_frame.add(main);
        user_frame.setVisible(true);
    }

    static void adminpage(String admin_name) {
        main_frame1.dispose();
        admin_frame = new JFrame("Library Management System User");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.95);
        admin_frame.setSize(width, height);
        admin_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = background1();
                g.drawImage(background.getImage(), 0, 20, getWidth(), getHeight(), this);
            }
        };
        main.setLayout(new BorderLayout());
        JTextArea textarea = new JTextArea();
        Font fon = textarea.getFont();
        float newSiz = fon.getSize() + 5;
        textarea.setFont(fon.deriveFont(newSiz));
        textarea.setEditable(false);
        textarea.setOpaque(false);
        JPanel btnpanel = new JPanel();
        JPanel btnpanel1 = new JPanel();
        btnpanel1.setBackground(light);
        btnpanel1.setLayout(new BorderLayout());
        JButton btn1 = new JButton("Add Books");
        Font font = btn1.getFont();
        float newSize = font.getSize() + 5;
        JButton bt1 = new JButton("🡨");
        bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui();
                admin_frame.dispose();
                main_frame.dispose();
                main_frame = null;
            }
        });
        bt1.setFocusable(false);
        bt1.setContentAreaFilled(false);
        bt1.setFont(font.deriveFont(newSize + 4));
        ImageIcon icon = user();
        JButton user = new JButton(icon);
        btnpanel.setBackground(light);
        user.setBorder(null);
        user.setFocusable(false);
        user.setContentAreaFilled(false);
        user.setCursor(new Cursor(Cursor.HAND_CURSOR));
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin Details");
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();

                if (main_frame == null) {
                    main_frame = frame;
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = frame;
                }
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setPreferredSize(new Dimension(500, 420));
                frame.pack();
                frame.setLocationRelativeTo(null);

                JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
                formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                frame.add(formPanel, BorderLayout.CENTER);

                JTextField f1 = new JTextField();
                f1.setEditable(false);
                f1.setFont(fontsize);
                f1.setBorder(border);
                JTextField f2 = new JTextField();
                f2.setEditable(false);
                f2.setFont(fontsize);
                f2.setBorder(border);
                JTextField f3 = new JTextField();
                f3.setEditable(false);
                f3.setFont(fontsize);
                f3.setBorder(border);
                JTextField f4 = new JTextField();
                f4.setEditable(false);
                f4.setFont(fontsize);
                f4.setBorder(border);
                JTextField f5 = new JTextField();
                f5.setEditable(false);
                f5.setFont(fontsize);
                f5.setBorder(border);
                JTextField f6 = new JTextField();
                f6.setEditable(false);
                f6.setFont(fontsize);
                f6.setBorder(border);
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String q1 = "select * from admins where username=?";
                    PreparedStatement pstm1 = con.prepareStatement(q1);
                    pstm1.setString(1, admin_name);
                    ResultSet rs = pstm1.executeQuery();
                    if (rs.next()) {
                        f1.setText(rs.getString(1));
                        f2.setText(rs.getString(2));
                        f3.setText(rs.getString(3));
                        f4.setText(rs.getString(4));
                        f5.setText(rs.getString(5));
                        f6.setText(rs.getString(6));
                    } else {
                    }

                } catch (Exception l) {
                    l.printStackTrace();
                }

                formPanel.add(new JLabel("Admin Id:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f1);
                formPanel.add(new JLabel("Admin Name:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f2);
                formPanel.add(new JLabel("Full Name:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f3);
                formPanel.add(new JLabel("Password:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f4);
                formPanel.add(new JLabel("Phone number:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f5);
                formPanel.add(new JLabel("Email:") {
                    {
                        setFont(fontsize1);
                    }
                });
                formPanel.add(f6);
                formPanel.add(new JLabel());
                formPanel.add(new JButton("Log out") {
                    {
                        setForeground(Color.red);
                        setFocusable(false);
                        setContentAreaFilled(false);
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        setPreferredSize(new Dimension(150, 45));
                        setFont(font.deriveFont(newSize));
                        addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                admin_frame.dispose();
                                frame.dispose();
                                AUN = null;
                                admin_frame = null;
                                main_frame = null;
                                gui();
                            }
                        });
                    }
                });
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        user.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 60));
        btn1.setPreferredSize(new Dimension(150, 55));
        btn1.setFont(font.deriveFont(newSize));
        btn1.setFocusable(false);
        btn1.setBackground(lightBlue);
        btn1.setBorder(border);
        btn1.setForeground(Color.WHITE);
        JButton btn2 = new JButton("Remove Books");
        btn2.setPreferredSize(new Dimension(170, 55));
        btn2.setFont(font.deriveFont(newSize));
        btn2.setFocusable(false);
        btn2.setBackground(lightBlue);
        btn2.setBorder(border);
        btn2.setForeground(Color.WHITE);
        JButton btn3 = new JButton("Update Books");
        btn3.setPreferredSize(new Dimension(150, 55));
        btn3.setFont(font.deriveFont(newSize));
        btn3.setFocusable(false);
        btn3.setBackground(lightBlue);
        btn3.setBorder(border);
        btn3.setForeground(Color.WHITE);
        JButton btn4 = new JButton("Books data");
        btn4.setPreferredSize(new Dimension(150, 55));
        btn4.setFont(font.deriveFont(newSize));
        btn4.setFocusable(false);
        btn4.setBackground(lightBlue);
        btn4.setBorder(border);
        btn4.setForeground(Color.WHITE);
        JButton btn5 = new JButton("Users data");
        btn5.setPreferredSize(new Dimension(150, 55));
        btn5.setFont(font.deriveFont(newSize));
        btn5.setFocusable(false);
        btn5.setBackground(lightBlue);
        btn5.setBorder(border);
        btn5.setForeground(Color.WHITE);
        JButton btn6 = new JButton("Remove user");
        btn6.setPreferredSize(new Dimension(150, 55));
        btn6.setFont(font.deriveFont(newSize));
        btn6.setFocusable(false);
        btn6.setBackground(lightBlue);
        btn6.setBorder(border);
        btn6.setForeground(Color.WHITE);
        JButton btn7 = new JButton("Update user");
        btn7.setPreferredSize(new Dimension(150, 55));
        btn7.setFont(font.deriveFont(newSize));
        btn7.setFocusable(false);
        btn7.setBackground(lightBlue);
        btn7.setBorder(border);
        btn7.setForeground(Color.WHITE);
        JButton btn8 = new JButton("Booked data");
        btn8.setPreferredSize(new Dimension(150, 55));
        btn8.setFont(font.deriveFont(newSize));
        btn8.setFocusable(false);
        btn8.setBackground(lightBlue);
        btn8.setBorder(border);
        btn8.setForeground(Color.WHITE);
        btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        user.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (main_frame == null) {
                    main_frame = add_book();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = add_book();
                }

            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (main_frame == null) {
                    main_frame = remove_book();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = remove_book();
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (main_frame == null) {
                    main_frame = update_book();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = update_book();
                }
            }
        });
        btn5.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (main_frame != null)
                    main_frame.dispose();
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String q = "select * from users";
                    PreparedStatement pstm = con.prepareStatement(q);
                    ResultSet rs = pstm.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    String[] colname = { "User_id", "User_name", "Full Name", "Password", "Phone", "Email" };
                    ArrayList<Object[]> data = new ArrayList<>();
                    while (rs.next()) {
                        Object[] rowData = new Object[count];
                        for (int j = 0; j < count; j++) {
                            rowData[j] = rs.getString(j + 1);
                        }
                        data.add(rowData);
                    }
                    JTable table = new JTable(data.toArray(new Object[0][0]), colname);
                    Font headerFont = table.getTableHeader().getFont();
                    Font newHeaderFont = new Font(headerFont.getName(), Font.BOLD, 20);
                    table.getTableHeader().setFont(newHeaderFont);
                    Font cellFont = table.getFont();
                    Font newCellFont = new Font(cellFont.getName(), Font.PLAIN, 16);
                    table.setFont(newCellFont);
                    table.getTableHeader().setBackground(new Color(23, 162, 184));
                    table.setBackground(new Color(134, 192, 207));
                    table.setRowHeight(40);
                    table.disable();
                    table.setIntercellSpacing(new Dimension(10, 10));
                    JScrollPane scrollPane = new JScrollPane(table);
                    textarea.setText("");
                    textarea.removeAll();
                    textarea.setLayout(new BorderLayout());
                    textarea.add(scrollPane, BorderLayout.CENTER);
                    textarea.revalidate();
                    textarea.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn4.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (main_frame != null)
                    main_frame.dispose();
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String q = "select * from books";
                    PreparedStatement pstm = con.prepareStatement(q);
                    ResultSet rs = pstm.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    String[] colname = { "Book_id", "Title", "Author", "Publisher", "Publisher Date", "Genre",
                            "Langauage", "Edition", "Copies" };
                    ArrayList<Object[]> data = new ArrayList<>();
                    while (rs.next()) {
                        Object[] rowData = new Object[count];
                        for (int j = 0; j < count; j++) {
                            rowData[j] = rs.getString(j + 1);
                        }
                        data.add(rowData);
                    }
                    JTable table = new JTable(data.toArray(new Object[0][0]), colname);
                    Font headerFont = table.getTableHeader().getFont();
                    Font newHeaderFont = new Font(headerFont.getName(), Font.BOLD, 20);
                    table.getTableHeader().setFont(newHeaderFont);
                    Font cellFont = table.getFont();
                    Font newCellFont = new Font(cellFont.getName(), Font.PLAIN, 16);
                    table.setFont(newCellFont);
                    table.setRowHeight(40);
                    table.getTableHeader().setBackground(new Color(23, 162, 184));
                    table.setBackground(new Color(134, 192, 207));
                    table.disable();
                    table.setIntercellSpacing(new Dimension(10, 10));
                    JScrollPane scrollPane = new JScrollPane(table);
                    textarea.setText("");
                    textarea.removeAll();
                    textarea.setLayout(new BorderLayout());
                    textarea.add(scrollPane, BorderLayout.CENTER);
                    textarea.revalidate();
                    textarea.repaint();
                } catch (Exception l) {
                    l.printStackTrace();
                }
            }
        });
        btn8.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (main_frame != null)
                    main_frame.dispose();
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String q = "select * from booked";
                    PreparedStatement pstm = con.prepareStatement(q);
                    ResultSet rs = pstm.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    String[] colname = { "User_id", "Book_id", "Booked Date" };
                    ArrayList<Object[]> data = new ArrayList<>();
                    while (rs.next()) {
                        Object[] rowData = new Object[count];
                        for (int j = 0; j < count; j++) {
                            rowData[j] = rs.getString(j + 1);
                        }
                        data.add(rowData);
                    }
                    JTable table = new JTable(data.toArray(new Object[0][0]), colname);
                    Font headerFont = table.getTableHeader().getFont();
                    Font newHeaderFont = new Font(headerFont.getName(), Font.BOLD, 20);
                    table.getTableHeader().setFont(newHeaderFont);
                    Font cellFont = table.getFont();
                    Font newCellFont = new Font(cellFont.getName(), Font.PLAIN, 16);
                    table.setFont(newCellFont);
                    table.setRowHeight(40);
                    table.getTableHeader().setBackground(new Color(23, 162, 184));
                    table.setBackground(new Color(134, 192, 207));
                    table.disable();
                    table.setIntercellSpacing(new Dimension(10, 10));
                    JScrollPane scrollPane = new JScrollPane(table);
                    textarea.setText("");
                    textarea.removeAll();
                    textarea.setLayout(new BorderLayout());
                    textarea.add(scrollPane, BorderLayout.CENTER);
                    textarea.revalidate();
                    textarea.repaint();
                } catch (Exception l) {
                    l.printStackTrace();
                }
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (main_frame == null) {
                    main_frame = remove_user();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = remove_user();
                }
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (main_frame == null) {
                    main_frame = update_user();
                } else {
                    main_frame.dispose();
                    main_frame = null;
                    main_frame = update_user();
                }
            }
        });

        btnpanel.add(btn1);
        btnpanel.add(btn2);
        btnpanel.add(btn3);
        btnpanel.add(btn4);
        btnpanel.add(btn5);
        btnpanel.add(btn8);
        btnpanel.add(btn6);
        btnpanel.add(btn7);
        btnpanel.setPreferredSize(new Dimension(width, 80));
        btnpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // btnpanel.setBackground(Color.LIGHT_lightGray);
        btnpanel1.setPreferredSize(new Dimension(width, 80));
        btnpanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnpanel1.add(bt1, BorderLayout.WEST);
        btnpanel1.add(user, BorderLayout.EAST);
        main.add(btnpanel1, BorderLayout.NORTH);
        main.add(textarea, BorderLayout.CENTER);
        main.add(btnpanel, BorderLayout.SOUTH);
        admin_frame.setContentPane(main);
        admin_frame.setVisible(true);
    }

    static JFrame renew_book() {
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
        usernameField.setFont(fontsize);
        usernameField.setBorder(border1);
        JButton button = new JButton("Renew Book");
        button.setFont(fontsize);
        button.setBorder(border);
        button.setForeground(Color.WHITE);
        button.setBackground(lightBlue);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate(usernameField.getText())&& validatebooked_db(UN,usernameField.getText())) {
                    long fine = 0;
                    String book_id = usernameField.getText();
                    fine = fineCalc1(book_id);
                    if (fine == 0) {
                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame, "Succesfully renewed", "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                                frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Succesfully renewed with a fine of " + fine, "Succes",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    try {
                        if (con == null || con.isClosed()) {
                            con = connect();
                        }
                        String q = "update booked set booked_date=? where user_id=(select user_id as user_id from users where username=?) and book_id=?";
                        PreparedStatement pstm = con.prepareStatement(q);
                        pstm.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                        pstm.setString(2, UN);
                        pstm.setString(3, book_id);
                        pstm.executeUpdate();
                    } catch (Exception l) {
                        l.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Please enter a valid book id","Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(fontsize);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel());
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    static long fineCalc1(String book_id) {
        LocalDate today = LocalDate.now();
        LocalDate date = null;
        long fine = 0;
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select booked_date from booked where user_id=(select user_id as user_id from users where username=?) and book_id=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, UN);
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

    static JFrame return_book() {
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
        usernameField.setFont(fontsize);
        usernameField.setBorder(border1);
        JButton button = new JButton("Return Book");
        button.setFont(fontsize);
        button.setBorder(border);
        button.setForeground(Color.WHITE);
        button.setBackground(lightBlue);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate(usernameField.getText())&& validatebooked_db(UN, usernameField.getText())) {
                    long fine = 0;
                    String book_id = usernameField.getText();
                    fine = fineCalc(book_id);
                    if (fine == 0) {
                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame, "Succesfully return", "Succes",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                                frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Succesfully return with a fine of" + fine, "Succes",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    try {
                        if (con == null || con.isClosed()) {
                            con = connect();
                        }
                        String q = "delete from booked where book_id=? and user_id=(select user_id as user_id from users where username=?)";
                        PreparedStatement pstm = con.prepareStatement(q);
                        pstm.setString(1, book_id);
                        pstm.setString(2, UN);
                        pstm.executeUpdate();
                        int currentCopies = 0;
                        q = "Select copies from books where book_id=?";
                        PreparedStatement pstm1 = con.prepareStatement(q);
                        pstm1.setString(1, book_id);
                        ResultSet rs = pstm1.executeQuery();
                        if (rs.next()) {
                            currentCopies = rs.getInt("copies");
                        }
                        int newCopies = currentCopies + 1;
                        q = "Update books set copies=? where book_id=?";
                        PreparedStatement pstm2 = con.prepareStatement(q);
                        pstm2.setInt(1, newCopies);
                        pstm2.setString(2, book_id);
                        pstm2.executeUpdate();
                    } catch (Exception l) {
                        l.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Please enter a valid book id","Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(fontsize);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel());
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }
    static boolean validatebooked_db(String username,String book_id){
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "Select user_id,book_id from booked where book_id=? and user_id=(select user_id from users where username=?)";
            PreparedStatement pstm = con.prepareStatement(q);
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

    static boolean validate(String string) {
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "Select book_id from books where book_id=?";
            PreparedStatement pstm = con.prepareStatement(q);
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

    static long fineCalc(String book_id) {
        LocalDate today = LocalDate.now();
        LocalDate date = null;
        long fine = 0;
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }
            String q = "select booked_date from booked where user_id=(select user_id as user_id from users where username=?) and book_id=?";
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.setString(1, UN);
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

    static JFrame borrow_book() {
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
        usernameField.setBorder(border1);
        usernameField.setFont(fontsize1);

        JButton button = new JButton("Borrow Book");
        button.setBorder(border);
        button.setFont(fontsize);
        button.setForeground(Color.WHITE);
        button.setBackground(lightBlue);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 45));
        button.setFocusable(false);

        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(fontsize);
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
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String bookid = usernameField.getText();
                    String q = "select count(*) as count from booked where book_id=? and user_id=(select user_id as user_id from users where username=?)";
                    PreparedStatement pstm = con.prepareStatement(q);
                    pstm.setString(1, bookid);
                    pstm.setString(2, UN);
                    ResultSet rs = pstm.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        if (count == 0) {
                            int currentCopies = 0;
                            q = "Select copies from books where book_id=?";
                            PreparedStatement pstm1 = con.prepareStatement(q);
                            pstm1.setString(1, bookid);
                            ResultSet rs1 = pstm1.executeQuery();
                            if (rs1.next()) {
                                currentCopies = rs1.getInt("copies");
                            }
                            if (currentCopies != 0) {
                                q = "insert into booked(user_id,book_id,booked_date) select user_id as user_id,?,? from users where username=?";
                                PreparedStatement pstm3 = con.prepareStatement(q);
                                pstm3.setString(1, usernameField.getText());
                                pstm3.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                                pstm3.setString(3, UN);
                                pstm3.executeUpdate();
                                int newCopies = currentCopies - 1;
                                q = "Update books set copies=? where book_id=?";
                                PreparedStatement pstm2 = con.prepareStatement(q);
                                pstm2.setInt(1, newCopies);
                                pstm2.setString(2, bookid);
                                pstm2.executeUpdate();
                                ImageIcon icon = icon();
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

    static JFrame add_book() {
        JFrame frame = new JFrame("Add Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500, 480));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField f1 = new JTextField();
        JTextField f2 = new JTextField();
        JTextField f3 = new JTextField();
        JTextField f4 = new JTextField();
        JTextField f5 = new JTextField();
        JTextField f6 = new JTextField();
        JTextField f7 = new JTextField();
        JTextField f8 = new JTextField();
        JTextField f9 = new JTextField();
        f1.setForeground(Color.lightGray);
        f2.setForeground(Color.lightGray);
        f3.setForeground(Color.lightGray);
        f4.setForeground(Color.lightGray);
        f5.setForeground(Color.lightGray);
        f6.setForeground(Color.lightGray);
        f7.setForeground(Color.lightGray);
        f8.setForeground(Color.lightGray);
        f9.setForeground(Color.lightGray);
        f1.setText("Enter the Book id");
        f2.setText("Enter the Title");
        f3.setText("Enter the Author");
        f4.setText("Enter the Publisher");
        f5.setText("yyyy-mm-dd");
        f6.setText("Enter the Genre");
        f7.setText("Enter the Language");
        f8.setText("Enter the Edition");
        f9.setText("Enter the Copies");
        f1.setBorder(border1);
        f2.setBorder(border1);
        f3.setBorder(border1);
        f4.setBorder(border1);
        f5.setBorder(border1);
        f6.setBorder(border1);
        f7.setBorder(border1);
        f8.setBorder(border1);
        f9.setBorder(border1);
        f1.setFont(fontsize);
        f2.setFont(fontsize);
        f3.setFont(fontsize);
        f4.setFont(fontsize);
        f5.setFont(fontsize);
        f6.setFont(fontsize);
        f7.setFont(fontsize);
        f8.setFont(fontsize);
        f9.setFont(fontsize);
        JButton button = new JButton("Add Book");
        button.setBackground(lightBlue);
        button.setBorder(border);
        button.setForeground(Color.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font font = button.getFont();
        float newSize = font.getSize() + 3;
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    if (!f1.toString().isBlank() && !f2.toString().isBlank() && !f3.toString().isBlank()
                            && !f9.toString().isBlank()) {
                        String q = "insert into books(book_id,title,author,publisher,publisher_date,genre,language,edition,copies) values (?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pstm = con.prepareStatement(q);
                        pstm.setString(1, f1.getText());
                        pstm.setString(2, f2.getText());
                        pstm.setString(3, f3.getText());
                        pstm.setString(4, f4.getText());
                        pstm.setString(5, f5.getText());
                        pstm.setString(6, f6.getText());
                        pstm.setString(7, f7.getText());
                        pstm.setString(8, f8.getText());
                        pstm.setString(9, f9.getText());
                        pstm.executeUpdate();
                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame, "Sucessfully added books", "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please fill:\n→book_id\n→Title\n→Author\n→Copies",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLIntegrityConstraintViolationException l) {
                    JOptionPane.showMessageDialog(frame, "Book_id already exists ,do update", "INFO",
                            JOptionPane.WARNING_MESSAGE);
                    l.printStackTrace();
                } catch (Exception ll) {
                    JOptionPane.showMessageDialog(frame, "Please fill all the flieds:" + ll.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    ll.printStackTrace();
                }

            }
        });

        f1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f1.getText().equals("Enter the Book id")) {
                    f1.setText(null);
                    f1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f1.getText().isEmpty()) {
                    f1.setForeground(Color.lightGray);
                    f1.setText("Enter the Book id");
                }
            }
        });
        f2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f2.getText().equals("Enter the Title")) {
                    f2.setText(null);
                    f2.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f2.getText().isEmpty()) {
                    f2.setForeground(Color.lightGray);
                    f2.setText("Enter the Title");
                }
            }
        });
        f3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f3.getText().equals("Enter the Author")) {
                    f3.setText(null);
                    f3.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f3.getText().isEmpty()) {
                    f3.setForeground(Color.lightGray);
                    f3.setText("Enter the Author");
                }
            }
        });
        f4.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f4.getText().equals("Enter the Publisher")) {
                    f4.setText(null);
                    f4.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f4.getText().isEmpty()) {
                    f4.setForeground(Color.lightGray);
                    f4.setText("Enter the Publisher");
                }
            }
        });
        f5.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f5.getText().equals("yyyy-mm-dd")) {
                    f5.setText(null);
                    f5.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f5.getText().isEmpty()) {
                    f5.setForeground(Color.lightGray);
                    f5.setText("yyyy-mm-dd");
                }
            }
        });
        f6.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f6.getText().equals("Enter the Genre")) {
                    f6.setText(null);
                    f6.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f6.getText().isEmpty()) {
                    f6.setForeground(Color.lightGray);
                    f6.setText("Enter the Genre");
                }
            }
        });
        f7.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f7.getText().equals("Enter the Language")) {
                    f7.setText(null);
                    f7.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f7.getText().isEmpty()) {
                    f7.setForeground(Color.lightGray);
                    f7.setText("Enter the Language");
                }
            }
        });
        f8.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f8.getText().equals("Enter the Edition")) {
                    f8.setText(null);
                    f8.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f8.getText().isEmpty()) {
                    f8.setForeground(Color.lightGray);
                    f8.setText("Enter the Edition");
                }
            }
        });
        f9.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f9.getText().equals("Enter the Copies")) {
                    f9.setText(null);
                    f9.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f9.getText().isEmpty()) {
                    f9.setForeground(Color.lightGray);
                    f9.setText("Enter the Copies");
                }
            }
        });

        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f1);
        formPanel.add(new JLabel("Title:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f2);
        formPanel.add(new JLabel("Author:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f3);
        formPanel.add(new JLabel("Publisher:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f4);
        formPanel.add(new JLabel("Publish date:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f5);
        formPanel.add(new JLabel("Genre:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f6);
        formPanel.add(new JLabel("Language:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f7);
        formPanel.add(new JLabel("Edition:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f8);
        formPanel.add(new JLabel("Copies:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f9);
        formPanel.add(new JButton("Cancel") {
            {
                setFont(font.deriveFont(newSize));
                setFocusable(false);
                setBorder(border);
                setForeground(Color.white);
                setBackground(new Color(255, 70, 100));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    static JFrame remove_book() {
        JFrame frame = new JFrame("Remove Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(400, 180));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField f1 = new JTextField();
        f1.setFont(fontsize);
        f1.setBorder(border1);
        f1.setText("Enter the Book id");
        f1.setForeground(Color.lightGray);
        JTextField f2 = new JTextField();
        f2.setText("Enter the copies");
        f1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCopies();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCopies();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCopies();
            }

            private void updateCopies() {
                try {
                    String bookId = f1.getText();
                    String query = "SELECT copies FROM books WHERE book_id=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, bookId);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        f2.setText(rs.getString("copies"));
                    } else {
                        f2.setText("Wrong book id");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        f2.setFont(fontsize);
        f2.setBorder(border1);

        JButton button = new JButton("Remove Book");
        button.setFont(fontsize1);
        button.setBorder(border);
        button.setForeground(Color.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(lightBlue);
        Font font = button.getFont();
        float newSize = font.getSize();
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setFocusable(false);
        f1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f1.getText().equals("Enter the Book id")) {
                    f1.setText("");
                    f1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f1.getText().isEmpty()) {
                    f1.setForeground(Color.GRAY);
                    f1.setText("Enter the Book id");
                }
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    int copies = Integer.parseInt(f2.getText());
                    int curr_copies = 0;
                    String q1 = "select copies from books where book_id=?";
                    PreparedStatement pstm1 = con.prepareStatement(q1);
                    pstm1.setString(1, f1.getText());
                    ResultSet rs = pstm1.executeQuery();
                    if (rs.next()) {
                        curr_copies = rs.getInt("copies");
                    }
                    if (curr_copies == copies) {
                        String q = "delete from books where book_id=? and copies=?";
                        PreparedStatement pstm = con.prepareStatement(q);
                        pstm.setString(1, f1.getText());
                        pstm.setString(2, f2.getText());
                        pstm.executeUpdate();

                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame,
                                "Removed the book with given book id :" + f1.getText() + " and total copies", "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                        frame.dispose();
                    } else if (curr_copies > copies) {
                        String q2 = "update books set copies=? where book_id=?";
                        PreparedStatement pstm2 = con.prepareStatement(q2);
                        String current_copies = Integer.toString(curr_copies - copies);
                        pstm2.setString(1, current_copies);
                        pstm2.setString(2, f1.getText());
                        pstm2.executeUpdate();
                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame,
                                "Removed the book with given book id :" + f1.getText() + " and copies: " + f2.getText(),
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                        frame.dispose();

                    } else {
                        JOptionPane.showMessageDialog(frame, "incorrect id or copies", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                        frame.dispose();
                    }
                } catch (Exception l) {
                    l.printStackTrace();
                }
            }
        });

        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f1);
        formPanel.add(new JLabel("Copies:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f2);
        formPanel.add(new JButton("Cancel") {
            {
                setFont(font.deriveFont(newSize));
                setFocusable(false);
                setBorder(border);
                setForeground(Color.white);
                setBackground(new Color(255, 70, 100));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    @SuppressWarnings("deprecation")
    static private void update_data(JTextField f1, JTextField f2, JTextField f3, JTextField f4, JTextField f5,
            JTextField f6, JTextField f7, JTextField f8, JTextField f9, JLabel txt, JLabel txt1) {
        try {
            String bookId = f1.getText();
            String query = "SELECT * FROM books WHERE book_id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txt.setText("Correct Book id :" + f1.getText() + "________________________________________");
                txt.setForeground(new Color(4, 124, 26));
                txt1.setForeground(new Color(4, 124, 26));
                f2.setText(rs.getString("title"));
                f3.setText(rs.getString("author"));
                f4.setText(rs.getString("publisher"));
                f5.setText(rs.getString("publisher_date"));
                f6.setText(rs.getString("genre"));
                f7.setText(rs.getString("language"));
                f8.setText(rs.getString("edition"));
                f9.setText(rs.getString("copies"));
                f2.enable();
                f3.enable();
                f4.enable();
                f5.enable();
                f6.enable();
                f7.enable();
                f8.enable();
                f9.enable();
            } else if (f1.getText().isEmpty()) {
                txt.setText("Enter the Book id ____________________");
                txt.setForeground(Color.gray);
                txt1.setForeground(Color.gray);
                f2.setText("");
                f3.setText("");
                f4.setText("");
                f5.setText("");
                f6.setText("");
                f7.setText("");
                f8.setText("");
                f9.setText("");
                f2.disable();
                f3.disable();
                f4.disable();
                f5.disable();
                f6.disable();
                f7.disable();
                f8.disable();
                f9.disable();
            } else {
                txt.setText("Wrong Book id :" + f1.getText() + "_____________________________");
                txt.setForeground(Color.red);
                txt1.setForeground(Color.red);
                f2.setText("");
                f3.setText("");
                f4.setText("");
                f5.setText("");
                f6.setText("");
                f7.setText("");
                f8.setText("");
                f9.setText("");
                f2.disable();
                f3.disable();
                f4.disable();
                f5.disable();
                f6.disable();
                f7.disable();
                f8.disable();
                f9.disable();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static JFrame update_book() {
        JFrame frame = new JFrame("Update Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(400, 550));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField f1 = new JTextField();
        JLabel txt = new JLabel("Enter the book id first ________________________________________");
        txt.setForeground(Color.gray);
        JLabel txt1 = new JLabel("________________________________________________________________");
        txt1.setForeground(Color.gray);
        f1.setText("Enter the Book id");
        f1.setForeground(Color.lightGray);
        JTextField f2 = new JTextField();
        JTextField f3 = new JTextField();
        JTextField f4 = new JTextField();
        JTextField f5 = new JTextField();
        JTextField f6 = new JTextField();
        JTextField f7 = new JTextField();
        JTextField f8 = new JTextField();
        JTextField f9 = new JTextField();
        f1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (f1.getText().equals("Enter the Book id")) {
                    f1.setText("");
                    f1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (f1.getText().isEmpty()) {
                    f1.setForeground(Color.GRAY);
                    f1.setText("Enter the Book id");
                }
            }
        });
        f1.setBorder(border);
        f2.setBorder(border1);
        f3.setBorder(border1);
        f4.setBorder(border1);
        f5.setBorder(border1);
        f6.setBorder(border1);
        f7.setBorder(border1);
        f8.setBorder(border1);
        f9.setBorder(border1);
        f1.setFont(fontsize);
        f2.setFont(fontsize1);
        f3.setFont(fontsize1);
        f4.setFont(fontsize1);
        f5.setFont(fontsize1);
        f6.setFont(fontsize1);
        f7.setFont(fontsize1);
        f8.setFont(fontsize1);
        f9.setFont(fontsize1);

        f1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update_data(f1, f2, f3, f4, f5, f6, f7, f8, f9, txt, txt1);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update_data(f1, f2, f3, f4, f5, f6, f7, f8, f9, txt, txt1);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update_data(f1, f2, f3, f4, f5, f6, f7, f8, f9, txt, txt1);
            }
        });
        JButton button = new JButton("Update Book");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font font = button.getFont();
        float newSize = font.getSize() + 3;
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setBorder(border);
        button.setForeground(Color.white);
        button.setBackground(lightBlue);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    if (!f2.getText().isBlank() && !f3.getText().isBlank() && !f4.getText().isBlank()
                            && !f5.getText().isBlank() && !f6.getText().isBlank() && !f7.getText().isBlank()
                            && !f8.getText().isBlank() && !f9.getText().isBlank()) {
                        String q1 = "DELETE FROM books WHERE book_id = ?";
                        PreparedStatement pst1 = con.prepareStatement(q1);
                        pst1.setString(1, f1.getText());
                        pst1.executeUpdate();
                        String q = "insert into books(book_id,title,author,publisher,publisher_date,genre,language,edition,copies) values (?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pstm = con.prepareStatement(q);
                        pstm.setString(1, f1.getText());
                        pstm.setString(2, f2.getText());
                        pstm.setString(3, f3.getText());
                        pstm.setString(4, f4.getText());
                        pstm.setString(5, f5.getText());
                        pstm.setString(6, f6.getText());
                        pstm.setString(7, f7.getText());
                        pstm.setString(8, f8.getText());
                        pstm.setString(9, f9.getText());
                        pstm.executeUpdate();
                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame, "Book Updated", "Success", JOptionPane.INFORMATION_MESSAGE,
                                icon);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Text fields should be not empty", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                        update_data(f1, f2, f3, f4, f5, f6, f7, f8, f9, txt, txt1);
                    }
                } catch (Exception l) {
                    JOptionPane.showMessageDialog(frame, l.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(new JLabel("Book Id:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f1);
        formPanel.add(txt1);
        formPanel.add(txt);
        formPanel.add(new JLabel("Title:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f2);
        formPanel.add(new JLabel("Author:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f3);
        formPanel.add(new JLabel("Publisher:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f4);
        formPanel.add(new JLabel("Publish date:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f5);
        formPanel.add(new JLabel("Genre:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f6);
        formPanel.add(new JLabel("Language:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f7);
        formPanel.add(new JLabel("Edition:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f8);
        formPanel.add(new JLabel("Copies:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f9);
        formPanel.add(new JButton("Cancel") {
            {
                setFont(font.deriveFont(newSize));
                setFocusable(false);
                setBorder(border);
                setForeground(Color.white);
                setBackground(new Color(255, 70, 100));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    static JFrame remove_user() {
        JFrame frame = new JFrame("Remove User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(400, 150));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField f1 = new JTextField();
        f1.setBorder(border1);
        f1.setFont(fontsize);
        JButton button = new JButton("Remove User");
        Font font = button.getFont();
        float newSize = font.getSize() + 3;
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setFocusable(false);
        button.setBorder(border);
        button.setForeground(Color.white);
        button.setBackground(lightBlue);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    String q = "delete from users where user_id=?";
                    PreparedStatement pstm = con.prepareStatement(q);
                    pstm.setString(1, f1.getText());
                    pstm.executeUpdate();
                    ImageIcon icon = icon();
                    JOptionPane.showMessageDialog(frame, "User Removed", "Success", JOptionPane.INFORMATION_MESSAGE,
                            icon);

                } catch (Exception l) {
                    JOptionPane.showMessageDialog(frame, l.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        formPanel.add(new JLabel("User Id:") {
            {
                setFont(font.deriveFont(newSize));
            }
        });
        formPanel.add(f1);
        formPanel.add(new JButton("Cancel") {
            {
                setFont(font.deriveFont(newSize));
                setFocusable(false);
                setBorder(border);
                setForeground(Color.white);
                setBackground(new Color(255, 70, 100));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    @SuppressWarnings("deprecation")
    static private void update_user_data(JTextField f1, JTextField f2, JTextField f3, JTextField f4, JTextField f5,
            JTextField f6, JLabel txt, JLabel txt1) {
        try {
            String bookId = f1.getText();
            String query = "SELECT * FROM users WHERE user_id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txt.setText("Correct User id :" + f1.getText() + "________________________________________");
                txt.setForeground(new Color(4, 124, 26));
                txt1.setForeground(new Color(4, 124, 26));
                f2.setText(rs.getString("username"));
                f3.setText(rs.getString("full_name"));
                f4.setText(rs.getString("password"));
                f5.setText(rs.getString("phone"));
                f6.setText(rs.getString("email"));
                f2.enable();
                f3.enable();
                f4.enable();
                f5.enable();
                f6.enable();
            } else if (f1.getText().isEmpty()) {
                txt.setText("Enter the User id ____________________");
                txt.setForeground(Color.gray);
                txt1.setForeground(Color.gray);
                f2.setText("");
                f3.setText("");
                f4.setText("");
                f5.setText("");
                f6.setText("");
                f2.disable();
                f3.disable();
                f4.disable();
                f5.disable();
                f6.disable();
            } else {
                txt.setText("Wrong User id :" + f1.getText() + "_____________________________");
                txt.setForeground(Color.red);
                txt1.setForeground(Color.red);
                f2.setText("");
                f3.setText("");
                f4.setText("");
                f5.setText("");
                f6.setText("");
                f2.disable();
                f3.disable();
                f4.disable();
                f5.disable();
                f6.disable();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static JFrame update_user() {
        JFrame frame = new JFrame("Update User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(400, 420));
        frame.pack();
        frame.setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(formPanel, BorderLayout.CENTER);

        JTextField f1 = new JTextField();
        JLabel txt = new JLabel("Enter the user id first ________________________________________");
        txt.setForeground(Color.gray);
        JLabel txt1 = new JLabel("________________________________________________________________");
        txt1.setForeground(Color.gray);
        JTextField f2 = new JTextField();
        JTextField f3 = new JTextField();
        JTextField f4 = new JTextField();
        JTextField f5 = new JTextField();
        JTextField f6 = new JTextField();
        f1.setBorder(border);
        f2.setBorder(border1);
        f3.setBorder(border1);
        f4.setBorder(border1);
        f5.setBorder(border1);
        f6.setBorder(border1);
        f1.setFont(fontsize);
        f2.setFont(fontsize);
        f3.setFont(fontsize);
        f4.setFont(fontsize);
        f5.setFont(fontsize);
        f6.setFont(fontsize);
        JButton button = new JButton("Update User");
        Font font = button.getFont();
        float newSize = font.getSize() + 3;
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setFocusable(false);
        button.setBorder(border);
        button.setForeground(Color.white);
        button.setBackground(lightBlue);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        f1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update_user_data(f1, f2, f3, f4, f5, f6, txt, txt1);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update_user_data(f1, f2, f3, f4, f5, f6, txt, txt1);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update_user_data(f1, f2, f3, f4, f5, f6, txt, txt1);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con == null || con.isClosed()) {
                        con = connect();
                    }
                    if (!f2.getText().isBlank() && !f3.getText().isBlank() && !f4.getText().isBlank()
                            && !f5.getText().isBlank() && !f6.getText().isBlank()) {
                        String q1 = "DELETE FROM users WHERE user_id = ?";
                        PreparedStatement pst1 = con.prepareStatement(q1);
                        pst1.setString(1, f1.getText());
                        pst1.executeUpdate();
                        String q = "insert into users(user_id,username,full_name,password,phone,email) value (?,?,?,?,?,?)";
                        PreparedStatement pstm = con.prepareStatement(q);
                        pstm.setString(1, f1.getText());
                        pstm.setString(2, f2.getText());
                        pstm.setString(3, f3.getText());
                        pstm.setString(4, f4.getText());
                        pstm.setString(5, f5.getText());
                        pstm.setString(6, f6.getText());
                        pstm.executeUpdate();
                        ImageIcon icon = icon();
                        JOptionPane.showMessageDialog(frame, "Update User Succesfully...", "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Text fields should be not empty", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                        update_user_data(f1, f2, f3, f4, f5, f6, txt, txt1);
                    }
                } catch (Exception l) {
                    JOptionPane.showMessageDialog(frame, l.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formPanel.add(new JLabel("User Id:") {
            {
                setFont(fontsize);
            }
        });
        formPanel.add(f1);
        formPanel.add(txt1);
        formPanel.add(txt);
        formPanel.add(new JLabel("Username:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f2);
        formPanel.add(new JLabel("Full Name:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f3);
        formPanel.add(new JLabel("Password:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f4);
        formPanel.add(new JLabel("Phone number:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f5);
        formPanel.add(new JLabel("Email:") {
            {
                setFont(fontsize1);
            }
        });
        formPanel.add(f6);
        formPanel.add(new JButton("Cancel") {
            {
                setFont(font.deriveFont(newSize));
                setFocusable(false);
                setBorder(border);
                setForeground(Color.white);
                setBackground(new Color(255, 70, 100));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    public static void main(String[] args) throws SQLException {
        gui();
        connect();
    }
}