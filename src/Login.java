//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class Login {
//    public Login(){
//        JFrame jFrame = new JFrame("Movie Ticket Booking");
//
//        JLabel companyName = new JLabel("MovieBook");
//        companyName.setBounds(80,140,300,40);
//        companyName.setFont(new Font("Comic Sans",Font.BOLD,48));
//
//        ImageIcon i = new ImageIcon("F:\\SY sem1\\OOPs-JAVA\\CP\\Ticket3(1).png");
//        JLabel jLabel = new JLabel(i);
//        jLabel.setBounds(10,200,380,300);
//
//        JLabel heading = new JLabel("Log In");
//        heading.setBounds(460,250,200,40);
//        heading.setFont(new Font("Times New Roman",Font.BOLD,32));
//
//        JLabel user = new JLabel("Username");
//        user.setBounds(390,300,100,30);
//        user.setFont(new Font("Times New Roman",Font.BOLD,20));
//
//        JTextField userName = new JTextField();
//        userName.setBounds(510,300,150,30);
//
//        JLabel password = new JLabel("Password");
//        password.setBounds(390,340,100,30);
//        password.setFont(new Font("Times New Roman",Font.BOLD,20));
//
//        JPasswordField pswd = new JPasswordField();
//        pswd.setBounds(510,340,150,30);
//
//        JButton login = new JButton("Log In");
//        login.setBounds(470,390,80,30);
//
//        JScrollPane sp = new JScrollPane();
//        sp.setBounds(380,240,290,210);
//
//        jFrame.add(companyName);
//        jFrame.add(jLabel);
//        jFrame.add(heading);
//        jFrame.add(user);
//        jFrame.add(userName);
//        jFrame.add(password);
//        jFrame.add(pswd);
//        jFrame.add(login);
//        jFrame.add(sp);
//
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
//
//            String query = "INSERT INTO logins values('" + userName + "','" + pswd + "')";
//
//            Statement sta = connection.createStatement();
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            tableModel.setRowCount(0);
//
//            while (resultSet.next()) {
//                Object[] rowData = {
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//
//                };
//                tableModel.addRow(rowData);
//            }
//
//            resultSet.close();
//
//            connection.close();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//
//        login.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = userName.getText();
//                String pass = pswd.getText().toString();
//                System.out.println(pass);
//
//                jFrame.setVisible(false);
//                if (name.equals(userName) && pass.equals(pswd)){
//                    new Home();
//                }
//                else if (name.equals(null)) {
//                    JOptionPane.showMessageDialog(null,"Please enter Username!" );
//                    new Login();
//                }
//                else if (pass.equals(null)) {
//                    JOptionPane.showMessageDialog(null,"Please enter Password!");
//
//                    new Login();
//                }
//                else if (pass.equals(null) && name.equals(null)) {
//                    JOptionPane.showMessageDialog(null,"Please enter Your Username and Password!");
//
//                    new Login();
//                }
//                else{
//                    JOptionPane.showMessageDialog(null,"Error in your Username or Password !" );
//
//                    new Login();
//                }
//            }
//        });
//
//        jFrame.setLayout(null);
//        jFrame.setSize(700,700);
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//    public static void main(String[] args) {
//        new Login();
//    }
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private JFrame jFrame;
    private JTextField userName;
    private JPasswordField pswd;

    public Login() {
        jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(80,140,300,40);
        companyName.setFont(new Font("Comic Sans",Font.BOLD,48));

        ImageIcon i = new ImageIcon("F:\\SY sem1\\OOPs-JAVA\\CP\\Ticket3(1).png");
        JLabel jLabel = new JLabel(i);
        jLabel.setBounds(10,200,380,300);

        JLabel heading = new JLabel("Log In");
        heading.setBounds(460,250,200,40);
        heading.setFont(new Font("Times New Roman",Font.BOLD,32));

        JLabel user = new JLabel("Username");
        user.setBounds(390,300,100,30);
        user.setFont(new Font("Times New Roman",Font.BOLD,20));

        JTextField userName = new JTextField();
        userName.setBounds(510,300,150,30);

        JLabel password = new JLabel("Password");
        password.setBounds(390,340,100,30);
        password.setFont(new Font("Times New Roman",Font.BOLD,20));

        JPasswordField pswd = new JPasswordField();
        pswd.setBounds(510,340,150,30);

        JButton login = new JButton("Log In");
        login.setBounds(470, 390, 80, 30);

        jFrame.add(login);
        JScrollPane sp = new JScrollPane();
        sp.setBounds(380,240,290,210);

        jFrame.add(companyName);
        jFrame.add(jLabel);
        jFrame.add(heading);
        jFrame.add(user);
        jFrame.add(userName);
        jFrame.add(password);
        jFrame.add(pswd);

        jFrame.add(sp);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userName.getText();
                String pass = new String(pswd.getPassword());

                if (name.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Username and Password.");
                } else {
                    // Validate the login with the database
                    if (isValidLogin(name, pass)) {
                        jFrame.setVisible(false);
                        new Home();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Username or Password.");
                    }
                }
            }
        });

        jFrame.setLayout(null);
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean isValidLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");

            // You should use PreparedStatement to avoid SQL injection
            String query = "SELECT * FROM logins WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            boolean isValid = resultSet.next();

            resultSet.close();
            connection.close();

            return isValid;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
