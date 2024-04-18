package lms_package;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Mainpage {
     private static code obj1=new code();
     private static Adminpage obj4;
     private static  Userpage obj3;
     public Mainpage(Adminpage adminpage, Userpage userpage,code cod) {
        obj1 = cod;
        obj4 = adminpage;
        obj3 = userpage;
    }

     public void gui() {
        obj1.main_frame1 = new JFrame("Library Management System");
        obj1.main_frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.95);
        obj1.main_frame1.setSize(width, height);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JTextArea textarea = new JTextArea();
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);

        JPanel btnpanel = new JPanel();
        JPanel userJPanel = new JPanel();
        userJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon img = obj1.user();
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
        login.setBackground(obj1.lightBlue);
        login.setForeground(Color.WHITE);
        login.setBorder(obj1.border);
        signup.setBackground(obj1.lightBlue);
        signup.setForeground(Color.WHITE);
        signup.setBorder(obj1.border);
        admin_login.setBackground(obj1.lightBlue);
        admin_login.setForeground(Color.WHITE);
        admin_login.setBorder(obj1.border);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame == null) {
                    obj1.main_frame = log();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = log();
                }
            }
        });
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame == null) {
                    obj1.main_frame = SignUp();
                    ;
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = SignUp();
                    ;
                }
            }
        });
        admin_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame == null) {
                    obj1.main_frame = log_admin();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = log_admin();
                }
            }
        });
        if (obj1.UN == null) {
            user.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (obj1.main_frame == null) {
                        obj1.main_frame = log();
                    } else {
                        obj1.main_frame.dispose();
                        obj1.main_frame = null;
                        obj1.main_frame = log();
                    }
                }
            });
        } else {

            user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profile(obj1.UN);
                }
            });

        }
        ImageIcon background = obj1.background();
        JLabel label1 = new JLabel(background);
        btnpanel.add(login);
        btnpanel.add(signup);
        btnpanel.add(admin_login);
        userJPanel.add(user);
        btnpanel.setBackground(obj1.light);
        btnpanel.setBorder(obj1.border);
        userJPanel.setBackground(obj1.light);
        userJPanel.setBorder(obj1.border);
        main.add(userJPanel, BorderLayout.NORTH);
        main.add(label1, BorderLayout.CENTER);
        main.add(btnpanel, BorderLayout.SOUTH);

        obj1.main_frame1.add(main);
        obj1.main_frame1.setVisible(true);
    }

     JFrame SignUp() {
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

        usernameField.setBorder(obj1.border1);
        usernameField.setFont(obj1.fontsize);
        usernameField.setText("Enter the Username");
        usernameField.setForeground(Color.lightGray);
        fullNameField.setBorder(obj1.border1);
        fullNameField.setFont(obj1.fontsize);
        fullNameField.setText("Enter the Full Name");
        fullNameField.setForeground(Color.lightGray);
        passwordField.setBorder(obj1.border1);
        passwordField.setFont(obj1.fontsize);
        phoneNumberField.setBorder(obj1.border1);
        phoneNumberField.setFont(obj1.fontsize);
        phoneNumberField.setText("Enter the Phone Number");
        phoneNumberField.setForeground(Color.lightGray);
        emailField.setBorder(obj1.border1);
        emailField.setFont(obj1.fontsize);
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
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Full Name:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(fullNameField);
        formPanel.add(new JLabel("Password:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Phone:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Email:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(emailField);
        formPanel.add(signup);
        formPanel.add(signUpButton);
        signUpButton.setBorder(obj1.border);
        signUpButton.setBackground(obj1.lightBlue);
        signUpButton.setForeground(Color.white);
        signUpButton.setFont(obj1.fontsize);
        signup.setBorder(obj1.border);
        signup.setBackground(obj1.lightBlue);
        signup.setForeground(Color.white);
        signup.setFont(obj1.fontsize);
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

     JFrame log_admin() {
        if (obj1.admin_frame == null) {
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

            usernameField.setBorder(obj1.border1);
            usernameField.setFont(obj1.fontsize);
            usernameField.setText("Enter the Admin Username");
            JPasswordField passwordField = new JPasswordField();
            passwordField.setBorder(obj1.border1);
            passwordField.setFont(obj1.fontsize);
            JButton button = new JButton("Login");
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorder(obj1.border);
            button.setBackground(obj1.lightBlue);
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
                    setBorder(obj1.border);
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
                    obj1.AUN = username;
                    String password = new String(passwordField.getPassword());
                    if (admin_data(username, password)) {
                        // ImageIcon icon = obj1.icon();
                        // JOptionPane.showMessageDialog(frame, "Login successful as Admin!...",
                        // "Success",
                        // JOptionPane.INFORMATION_MESSAGE, icon);
                        obj4.adminpage(username);
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
            obj4.adminpage(obj1.AUN);
            obj1.main_frame1.dispose();
        }
        return null;
    }

     JFrame log() {
        if (obj1.user_frame == null) {
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

            usernameField.setBorder(obj1.border1);
            usernameField.setFont(obj1.fontsize);
            passwordField.setBorder(obj1.border1);
            passwordField.setFont(obj1.fontsize);
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
            button.setBorder(obj1.border);
            button.setBackground(obj1.lightBlue);
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
            signup.setBorder(obj1.border);
            signup.setBackground(obj1.lightBlue);
            signup.setForeground(Color.white);
            signup.setFocusable(false);
            signup.setPreferredSize(new Dimension(150, 45));
            signup.setFont(font.deriveFont(newSize));

            formPanel.add(new JLabel("Username:") {
                {
                    setFont(obj1.fontsize1);
                }
            });
            formPanel.add(usernameField);
            formPanel.add(new JLabel("Password:") {
                {
                    setFont(obj1.fontsize1);
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
                    obj1.UN = username;
                    String password = new String(passwordField.getPassword());
                    if (user_data(username, password)) {

                        frame.dispose();
                        obj3.userpage(username);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password or Username", "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            click.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (obj1.main_frame == null) {
                        obj1.main_frame = forgot_password();
                    } else {
                        obj1.main_frame.dispose();
                        obj1.main_frame = null;
                        obj1.main_frame = forgot_password();
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
            obj3.userpage(obj1.UN);
            obj1.main_frame1.dispose();
        }
        return null;
    }

     void check(JFrame frame, JTextField text1, JTextField text2, JPasswordField text3, JPasswordField text4,
            JLabel label1, JLabel label2, JPanel formPanel, JButton reset, JButton cancel) {
        try {
            String q = "select * from users where username=? and email=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
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

     JFrame forgot_password() {
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
        text1.setBorder(obj1.border1);
        text2.setBorder(obj1.border1);
        text3.setBorder(obj1.border1);
        text4.setBorder(obj1.border1);
        text1.setFont(obj1.fontsize1);
        text2.setFont(obj1.fontsize1);
        text3.setFont(obj1.fontsize1);
        text4.setFont(obj1.fontsize1);
        JButton cancel = new JButton("Cancel");
        JButton reset = new JButton("Reset Password");
        JLabel label1 = new JLabel("New Password");
        JLabel label2 = new JLabel("Conform Password");
        label1.setFont(obj1.fontsize);
        label2.setFont(obj1.fontsize);
        cancel.setFont(obj1.fontsize1);
        reset.setFont(obj1.fontsize1);
        cancel.setBorder(obj1.border);
        reset.setBorder(obj1.border);
        cancel.setBackground(new Color(255, 70, 100));
        reset.setBackground(obj1.lightBlue);
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
                setFont(obj1.fontsize);
            }
        });
        formPanel.add(text1);
        formPanel.add(new JLabel("Email") {
            {
                setFont(obj1.fontsize);
            }
        });
        formPanel.add(text2);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
        return frame;
    }

     void reset_password(JTextField text1, JTextField text2, JPasswordField text3, JPasswordField text4,
            JFrame frame) {
        String newp = new String(text3.getPassword());
        String confirm = new String(text4.getPassword());
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            if (newp.equals(confirm)) {
                String q = "select password from users where username=? and email=? ";
                PreparedStatement pstm = obj1.con.prepareStatement(q);
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
                            PreparedStatement pstm1 = obj1.con.prepareStatement(q);
                            pstm1.setString(1, newp);
                            pstm1.setString(2, text1.getText());
                            pstm1.setString(3, text2.getText());
                            pstm1.executeUpdate();
                            ImageIcon icon = obj1.icon();
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

     boolean admin_data(String name, String loc) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            String q = "select username,password from admins where username=? and password=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
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

     boolean user_data(String name, String loc) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            String q = "select username,password from users where username=? and password=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
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

     void signup_data(String username, String fullName, String phoneNumber, String email, String password,
            JFrame frame) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            boolean valid = passwordValidator(password);
            boolean valid1 = phoneNoValidator(phoneNumber);
            boolean valid2 = emailValidator(email);
            if (!username.isBlank() && !fullName.isBlank() && !password.isBlank() && !phoneNumber.isBlank()
                    && !email.isBlank() && valid && valid1 && valid2) {
                String q = "insert into users(username,full_name,password,phone,email) values (?,?,?,?,?)";
                PreparedStatement pstm = obj1.con.prepareStatement(q);
                pstm.setString(1, username);
                pstm.setString(2, fullName);
                pstm.setString(3, password);
                pstm.setString(4, phoneNumber);
                pstm.setString(5, email);
                pstm.executeUpdate();

                ImageIcon icon = obj1.icon();
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

     boolean passwordValidator(String password) {
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

     boolean phoneNoValidator(String phoneNumber) {
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

     boolean userValidation(String user) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            String q = "select username from users where username=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
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

     boolean adminValidation(String user) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            String q = "select username from admins where username=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
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

     boolean emailValidationdb(String email) {
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            String q = "select email from users where email=?";
            PreparedStatement pstm = obj1.con.prepareStatement(q);
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

     boolean emailValidator(String email) {
        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

     void profile(String user_id) {
        JFrame frame = new JFrame("User Details");

        if (obj1.main_frame == null) {
            obj1.main_frame = frame;
        } else {
            obj1.main_frame.dispose();
            obj1.main_frame = null;
            obj1.main_frame = frame;
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
        f1.setBorder(obj1.border1);
        f2.setBorder(obj1.border1);
        f3.setBorder(obj1.border1);
        f4.setBorder(obj1.border1);
        f5.setBorder(obj1.border1);
        f6.setBorder(obj1.border1);
        f1.setFont(obj1.fontsize);
        f2.setFont(obj1.fontsize1);
        f3.setFont(obj1.fontsize1);
        f4.setFont(obj1.fontsize1);
        f5.setFont(obj1.fontsize1);
        f6.setFont(obj1.fontsize1);
        try {
            if (obj1.con == null || obj1.con.isClosed()) {
                obj1.con =obj1.connect();
            }
            String q1 = "select * from users where username=?";
            PreparedStatement pstm1 = obj1.con.prepareStatement(q1);
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
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f1);
        formPanel.add(new JLabel("User name:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f2);
        formPanel.add(new JLabel("Full Name:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f3);
        formPanel.add(new JLabel("Password:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f4);
        formPanel.add(new JLabel("Phone number:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f5);
        formPanel.add(new JLabel("Email:") {
            {
                setFont(obj1.fontsize1);
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
                        obj1.user_frame.dispose();
                        frame.dispose();
                        obj1.UN = null;
                        obj1.user_frame = null;
                        obj1.main_frame = null;
                        gui();
                    }
                });
            }
        });
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
