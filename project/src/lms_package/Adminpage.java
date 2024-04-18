package lms_package;

import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Adminpage {
    private code obj1 ;
     private Mainpage obj2=new Mainpage(null, null, obj1);
    public Adminpage(code cod){
        // obj2=main;
        obj1=cod;
    }
     void adminpage(String admin_name) {
        obj1.main_frame1.dispose(); 
        obj1.admin_frame = new JFrame("Library Management System User");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width);
        int height = (int) (screenSize.height * 0.95);
        obj1.admin_frame.setSize(width, height);
        obj1.admin_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = obj1.background1();
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
        btnpanel1.setBackground(obj1.light);
        btnpanel1.setLayout(new BorderLayout());
        JButton btn1 = new JButton("Add Books");
        Font font = btn1.getFont();
        float newSize = font.getSize() + 5;
        JButton bt1 = new JButton("🡨");
        bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj2.gui();
                obj1.admin_frame.dispose();
                obj1.main_frame.dispose();
                obj1.main_frame = null;
            }
        });
        bt1.setFocusable(false);
        bt1.setContentAreaFilled(false);
        bt1.setFont(font.deriveFont(newSize + 4));
        ImageIcon icon = obj1.user();
        JButton user = new JButton(icon);
        btnpanel.setBackground(obj1.light);
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

                if (obj1.main_frame == null) {
                    obj1.main_frame = frame;
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = frame;
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
                f1.setFont(obj1.fontsize);
                f1.setBorder(obj1.border);
                JTextField f2 = new JTextField();
                f2.setEditable(false);
                f2.setFont(obj1.fontsize);
                f2.setBorder(obj1.border);
                JTextField f3 = new JTextField();
                f3.setEditable(false);
                f3.setFont(obj1.fontsize);
                f3.setBorder(obj1.border);
                JTextField f4 = new JTextField();
                f4.setEditable(false);
                f4.setFont(obj1.fontsize);
                f4.setBorder(obj1.border);
                JTextField f5 = new JTextField();
                f5.setEditable(false);
                f5.setFont(obj1.fontsize);
                f5.setBorder(obj1.border);
                JTextField f6 = new JTextField();
                f6.setEditable(false);
                f6.setFont(obj1.fontsize);
                f6.setBorder(obj1.border);
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    String q1 = "select * from admins where username=?";
                    PreparedStatement pstm1 = obj1.con.prepareStatement(q1);
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
                        setFont(obj1.fontsize1);
                    }
                });
                formPanel.add(f1);
                formPanel.add(new JLabel("Admin Name:") {
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
                        setFocusable(false);
                        setContentAreaFilled(false);
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        setPreferredSize(new Dimension(150, 45));
                        setFont(font.deriveFont(newSize));
                        addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                obj1.admin_frame.dispose();
                                frame.dispose();
                                obj1.AUN = null;
                                obj1.admin_frame = null;
                                obj1.main_frame = null;
                                obj2.gui();
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
        btn1.setBackground(obj1.lightBlue);
        btn1.setBorder(obj1.border);
        btn1.setForeground(Color.WHITE);
        JButton btn2 = new JButton("Remove Books");
        btn2.setPreferredSize(new Dimension(170, 55));
        btn2.setFont(font.deriveFont(newSize));
        btn2.setFocusable(false);
        btn2.setBackground(obj1.lightBlue);
        btn2.setBorder(obj1.border);
        btn2.setForeground(Color.WHITE);
        JButton btn3 = new JButton("Update Books");
        btn3.setPreferredSize(new Dimension(150, 55));
        btn3.setFont(font.deriveFont(newSize));
        btn3.setFocusable(false);
        btn3.setBackground(obj1.lightBlue);
        btn3.setBorder(obj1.border);
        btn3.setForeground(Color.WHITE);
        JButton btn4 = new JButton("Books data");
        btn4.setPreferredSize(new Dimension(150, 55));
        btn4.setFont(font.deriveFont(newSize));
        btn4.setFocusable(false);
        btn4.setBackground(obj1.lightBlue);
        btn4.setBorder(obj1.border);
        btn4.setForeground(Color.WHITE);
        JButton btn5 = new JButton("Users data");
        btn5.setPreferredSize(new Dimension(150, 55));
        btn5.setFont(font.deriveFont(newSize));
        btn5.setFocusable(false);
        btn5.setBackground(obj1.lightBlue);
        btn5.setBorder(obj1.border);
        btn5.setForeground(Color.WHITE);
        JButton btn6 = new JButton("Remove user");
        btn6.setPreferredSize(new Dimension(150, 55));
        btn6.setFont(font.deriveFont(newSize));
        btn6.setFocusable(false);
        btn6.setBackground(obj1.lightBlue);
        btn6.setBorder(obj1.border);
        btn6.setForeground(Color.WHITE);
        JButton btn7 = new JButton("Update user");
        btn7.setPreferredSize(new Dimension(150, 55));
        btn7.setFont(font.deriveFont(newSize));
        btn7.setFocusable(false);
        btn7.setBackground(obj1.lightBlue);
        btn7.setBorder(obj1.border);
        btn7.setForeground(Color.WHITE);
        JButton btn8 = new JButton("Booked data");
        btn8.setPreferredSize(new Dimension(150, 55));
        btn8.setFont(font.deriveFont(newSize));
        btn8.setFocusable(false);
        btn8.setBackground(obj1.lightBlue);
        btn8.setBorder(obj1.border);
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
                if (obj1.main_frame == null) {
                    obj1.main_frame = add_book();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = add_book();
                }

            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (obj1.main_frame == null) {
                    obj1.main_frame = remove_book();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = remove_book();
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (obj1.main_frame == null) {
                    obj1.main_frame = update_book();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = update_book();
                }
            }
        });
        btn5.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (obj1.main_frame != null)
                    obj1.main_frame.dispose();
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    String q = "select * from users";
                    PreparedStatement pstm = obj1.con.prepareStatement(q);
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
                if (obj1.main_frame != null)
                    obj1.main_frame.dispose();
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    String q = "select * from books";
                    PreparedStatement pstm = obj1.con.prepareStatement(q);
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
                if (obj1.main_frame != null)
                    obj1.main_frame.dispose();
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    String q = "select * from booked";
                    PreparedStatement pstm = obj1.con.prepareStatement(q);
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
                if (obj1.main_frame == null) {
                    obj1.main_frame = remove_user();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = remove_user();
                }
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.removeAll();
                textarea.revalidate();
                textarea.repaint();
                if (obj1.main_frame == null) {
                    obj1.main_frame = update_user();
                } else {
                    obj1.main_frame.dispose();
                    obj1.main_frame = null;
                    obj1.main_frame = update_user();
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
        obj1.admin_frame.setContentPane(main);
        obj1.admin_frame.setVisible(true);
    }

     JFrame add_book() {
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
        f1.setBorder(obj1.border1);
        f2.setBorder(obj1.border1);
        f3.setBorder(obj1.border1);
        f4.setBorder(obj1.border1);
        f5.setBorder(obj1.border1);
        f6.setBorder(obj1.border1);
        f7.setBorder(obj1.border1);
        f8.setBorder(obj1.border1);
        f9.setBorder(obj1.border1);
        f1.setFont(obj1.fontsize);
        f2.setFont(obj1.fontsize);
        f3.setFont(obj1.fontsize);
        f4.setFont(obj1.fontsize);
        f5.setFont(obj1.fontsize);
        f6.setFont(obj1.fontsize);
        f7.setFont(obj1.fontsize);
        f8.setFont(obj1.fontsize);
        f9.setFont(obj1.fontsize);
        JButton button = new JButton("Add Book");
        button.setBackground(obj1.lightBlue);
        button.setBorder(obj1.border);
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
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    if (!f1.toString().isBlank() && !f2.toString().isBlank() && !f3.toString().isBlank()
                            && !f9.toString().isBlank()) {
                        String q = "insert into books(book_id,title,author,publisher,publisher_date,genre,language,edition,copies) values (?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pstm = obj1.con.prepareStatement(q);
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
                        ImageIcon icon = obj1.icon();
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
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f1);
        formPanel.add(new JLabel("Title:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f2);
        formPanel.add(new JLabel("Author:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f3);
        formPanel.add(new JLabel("Publisher:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f4);
        formPanel.add(new JLabel("Publish date:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f5);
        formPanel.add(new JLabel("Genre:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f6);
        formPanel.add(new JLabel("Language:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f7);
        formPanel.add(new JLabel("Edition:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f8);
        formPanel.add(new JLabel("Copies:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f9);
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
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

     JFrame remove_book() {
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
        f1.setFont(obj1.fontsize);
        f1.setBorder(obj1.border1);
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
                    PreparedStatement pst = obj1.con.prepareStatement(query);
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
        f2.setFont(obj1.fontsize);
        f2.setBorder(obj1.border1);

        JButton button = new JButton("Remove Book");
        button.setFont(obj1.fontsize1);
        button.setBorder(obj1.border);
        button.setForeground(Color.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(obj1.lightBlue);
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
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    int copies = Integer.parseInt(f2.getText());
                    int curr_copies = 0;
                    String q1 = "select copies from books where book_id=?";
                    PreparedStatement pstm1 = obj1.con.prepareStatement(q1);
                    pstm1.setString(1, f1.getText());
                    ResultSet rs = pstm1.executeQuery();
                    if (rs.next()) {
                        curr_copies = rs.getInt("copies");
                    }
                    if (curr_copies == copies) {
                        String q = "delete from books where book_id=? and copies=?";
                        PreparedStatement pstm = obj1.con.prepareStatement(q);
                        pstm.setString(1, f1.getText());
                        pstm.setString(2, f2.getText());
                        pstm.executeUpdate();

                        ImageIcon icon = obj1.icon();
                        JOptionPane.showMessageDialog(frame,
                                "Removed the book with given book id :" + f1.getText() + " and total copies", "Success",
                                JOptionPane.INFORMATION_MESSAGE, icon);
                        frame.dispose();
                    } else if (curr_copies > copies) {
                        String q2 = "update books set copies=? where book_id=?";
                        PreparedStatement pstm2 = obj1.con.prepareStatement(q2);
                        String current_copies = Integer.toString(curr_copies - copies);
                        pstm2.setString(1, current_copies);
                        pstm2.setString(2, f1.getText());
                        pstm2.executeUpdate();
                        ImageIcon icon = obj1.icon();
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
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f1);
        formPanel.add(new JLabel("Copies:") {
            {
                setFont(obj1.fontsize1);
            }
        });
        formPanel.add(f2);
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
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    @SuppressWarnings("deprecation")
     private void update_data(JTextField f1, JTextField f2, JTextField f3, JTextField f4, JTextField f5,
            JTextField f6, JTextField f7, JTextField f8, JTextField f9, JLabel txt, JLabel txt1) {
        try {
            String bookId = f1.getText();
            String query = "SELECT * FROM books WHERE book_id=?";
            PreparedStatement pst = obj1.con.prepareStatement(query);
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

     JFrame update_book() {
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
        f1.setBorder(obj1.border);
        f2.setBorder(obj1.border1);
        f3.setBorder(obj1.border1);
        f4.setBorder(obj1.border1);
        f5.setBorder(obj1.border1);
        f6.setBorder(obj1.border1);
        f7.setBorder(obj1.border1);
        f8.setBorder(obj1.border1);
        f9.setBorder(obj1.border1);
        f1.setFont(obj1.fontsize);
        f2.setFont(obj1.fontsize1);
        f3.setFont(obj1.fontsize1);
        f4.setFont(obj1.fontsize1);
        f5.setFont(obj1.fontsize1);
        f6.setFont(obj1.fontsize1);
        f7.setFont(obj1.fontsize1);
        f8.setFont(obj1.fontsize1);
        f9.setFont(obj1.fontsize1);

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
        button.setBorder(obj1.border);
        button.setForeground(Color.white);
        button.setBackground(obj1.lightBlue);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    if (!f2.getText().isBlank() && !f3.getText().isBlank() && !f4.getText().isBlank()
                            && !f5.getText().isBlank() && !f6.getText().isBlank() && !f7.getText().isBlank()
                            && !f8.getText().isBlank() && !f9.getText().isBlank()) {
                        String q1 = "DELETE FROM books WHERE book_id = ?";
                        PreparedStatement pst1 = obj1.con.prepareStatement(q1);
                        pst1.setString(1, f1.getText());
                        pst1.executeUpdate();
                        String q = "insert into books(book_id,title,author,publisher,publisher_date,genre,language,edition,copies) values (?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pstm = obj1.con.prepareStatement(q);
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
                        ImageIcon icon = obj1.icon();
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
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

     JFrame remove_user() {
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
        f1.setBorder(obj1.border1);
        f1.setFont(obj1.fontsize);
        JButton button = new JButton("Remove User");
        Font font = button.getFont();
        float newSize = font.getSize() + 3;
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setFocusable(false);
        button.setBorder(obj1.border);
        button.setForeground(Color.white);
        button.setBackground(obj1.lightBlue);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    String q = "delete from users where user_id=?";
                    PreparedStatement pstm = obj1.con.prepareStatement(q);
                    pstm.setString(1, f1.getText());
                    pstm.executeUpdate();
                    ImageIcon icon = obj1.icon();
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
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    @SuppressWarnings("deprecation")
     private void update_user_data(JTextField f1, JTextField f2, JTextField f3, JTextField f4, JTextField f5,
            JTextField f6, JLabel txt, JLabel txt1) {
        try {
            String bookId = f1.getText();
            String query = "SELECT * FROM users WHERE user_id=?";
            PreparedStatement pst = obj1.con.prepareStatement(query);
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

     JFrame update_user() {
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
        f1.setBorder(obj1.border);
        f2.setBorder(obj1.border1);
        f3.setBorder(obj1.border1);
        f4.setBorder(obj1.border1);
        f5.setBorder(obj1.border1);
        f6.setBorder(obj1.border1);
        f1.setFont(obj1.fontsize);
        f2.setFont(obj1.fontsize);
        f3.setFont(obj1.fontsize);
        f4.setFont(obj1.fontsize);
        f5.setFont(obj1.fontsize);
        f6.setFont(obj1.fontsize);
        JButton button = new JButton("Update User");
        Font font = button.getFont();
        float newSize = font.getSize() + 3;
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(font.deriveFont(newSize));
        button.setFocusable(false);
        button.setBorder(obj1.border);
        button.setForeground(Color.white);
        button.setBackground(obj1.lightBlue);
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
                    if (obj1.con == null || obj1.con.isClosed()) {
                        obj1.con = obj1.connect();
                    }
                    if (!f2.getText().isBlank() && !f3.getText().isBlank() && !f4.getText().isBlank()
                            && !f5.getText().isBlank() && !f6.getText().isBlank()) {
                        String q1 = "DELETE FROM users WHERE user_id = ?";
                        PreparedStatement pst1 = obj1.con.prepareStatement(q1);
                        pst1.setString(1, f1.getText());
                        pst1.executeUpdate();
                        String q = "insert into users(user_id,username,full_name,password,phone,email) value (?,?,?,?,?,?)";
                        PreparedStatement pstm = obj1.con.prepareStatement(q);
                        pstm.setString(1, f1.getText());
                        pstm.setString(2, f2.getText());
                        pstm.setString(3, f3.getText());
                        pstm.setString(4, f4.getText());
                        pstm.setString(5, f5.getText());
                        pstm.setString(6, f6.getText());
                        pstm.executeUpdate();
                        ImageIcon icon = obj1.icon();
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
                setFont(obj1.fontsize);
            }
        });
        formPanel.add(f1);
        formPanel.add(txt1);
        formPanel.add(txt);
        formPanel.add(new JLabel("Username:") {
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
        formPanel.add(button);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

}
