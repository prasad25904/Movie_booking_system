import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CancelTickets {
    private JComboBox<String> movies;
    public CancelTickets(){
        JFrame jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(250,30,200,40);
        companyName.setFont(new Font("Comic Sans",Font.BOLD,36));

        JLabel heading = new JLabel("Cancel Ticket");
        heading.setBounds(250,120,200,30);
        heading.setFont(new Font("Times New Roman",Font.BOLD,28));

        JLabel movieName = new JLabel("Movie Name");
        movieName.setBounds(180,200,200,30);
        movieName.setFont(new Font("Times New Roman",Font.BOLD,25));

        movies = new JComboBox<>();
        movies.setBounds(370, 200, 150, 30);
        loadMovieNames();


        JLabel movieTimings = new JLabel("Movie Timings");
        movieTimings.setBounds(180,250,200,30);
        movieTimings.setFont(new Font("Times New Roman",Font.BOLD,25));

        String[] time = {"09:00","10:00","11:00","13:00","15:00","18:00"};
        JComboBox timings = new JComboBox(time);
        timings.setBounds(370,250,150,30);

        JLabel name = new JLabel("Customer Name");
        name.setBounds(180,300,200,30);
        name.setFont(new Font("Times New Roman",Font.BOLD,25));

        JTextField nameInput = new JTextField();
        nameInput.setBounds(370,300,150,30);

        JLabel ticketCount = new JLabel("Ticket Count");
        ticketCount.setBounds(180,350,200,30);
        ticketCount.setFont(new Font("Times New Roman",Font.BOLD,25));

        String[] ticket = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        JComboBox tickets = new JComboBox(ticket);
        tickets.setBounds(370,350,150,30);

        JButton home = new JButton("Go To Home");
        home.setBounds(175,420,150,30);


        JButton cancel = new JButton("Cancel Tickets");
        cancel.setBounds(375, 420, 150, 30);

        jFrame.add(companyName);
        jFrame.add(heading);
        jFrame.add(movieName);
        jFrame.add(movies);
        jFrame.add(movieTimings);
        jFrame.add(timings);
        jFrame.add(name);
        jFrame.add(nameInput);
        jFrame.add(ticketCount);
        jFrame.add(tickets);
        jFrame.add(home);
        jFrame.add(cancel);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new Home();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected customer name and movie name
                String selectedCustomerName = nameInput.getText();
                String selectedMovieName = movies.getSelectedItem().toString();

                // Call the cancelMovie method with selected customer name and movie name
                cancelMovie(selectedCustomerName, selectedMovieName);

                jFrame.setVisible(false);
                new Home();
            }
        });

        jFrame.setLayout(null);
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void cancelMovie(String customerName, String movieName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "DELETE FROM user WHERE Customer_Name=? AND Movie_name=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, customerName);
            statement.setString(2, movieName);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Ticket canceled successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Ticket not found for the given customer and movie.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    private void loadMovieNames() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "SELECT Movie_name FROM movie_details";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String movieName = resultSet.getString("movie_name");
                movies.addItem(movieName);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}