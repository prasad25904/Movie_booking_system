import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookingDetails {
    private JLabel movies;
    private JLabel timings;
    private JLabel nameInput;
    private JLabel ageInput;
    private JLabel tickets;
    private JLabel languages;
    private JLabel type;

    private String selectedMovieName;
    private String selectedMovieTiming;
    private String selectedCustomerName;

    public BookingDetails() {
        this.selectedMovieName = selectedMovieName;
        this.selectedMovieTiming = selectedMovieTiming;
        this.selectedCustomerName = selectedCustomerName;

        JFrame jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(250, 30, 200, 40);
        companyName.setFont(new Font("Comic Sans", Font.BOLD, 36));

        JLabel heading = new JLabel("Booking Details");
        heading.setBounds(250, 120, 200, 30);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));

        JLabel movieName = new JLabel("Movie Name");
        movieName.setBounds(180, 200, 200, 30);
        movieName.setFont(new Font("Times New Roman", Font.BOLD, 25));

        movies = new JLabel(selectedMovieName);
        movies.setBounds(370, 200, 150, 30);

        JLabel movieTimings = new JLabel("Movie Timings");
        movieTimings.setBounds(180, 250, 200, 30);
        movieTimings.setFont(new Font("Times New Roman", Font.BOLD, 25));

        timings = new JLabel(selectedMovieTiming);
        timings.setBounds(370, 250, 150, 30);

        JLabel name = new JLabel("Customer Name");
        name.setBounds(180, 300, 200, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 25));

        nameInput = new JLabel(selectedCustomerName);
        nameInput.setBounds(370, 300, 150, 30);


        // Add labels and components to display customer information

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(180, 350, 100, 30);
        ageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        ageInput = new JLabel();
        ageInput.setBounds(300, 350, 150, 30);

        JLabel ticketLabel = new JLabel("Ticket Count:");
        ticketLabel.setBounds(180, 400, 150, 30);
        ticketLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tickets = new JLabel();
        tickets.setBounds(340, 400, 150, 30);

        JLabel languageLabel = new JLabel("Movie Language:");
        languageLabel.setBounds(180, 450, 200, 30);
        languageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        languages = new JLabel();
        languages.setBounds(370, 450, 150, 30);

        JLabel typeLabel = new JLabel("Movie Type:");
        typeLabel.setBounds(180, 500, 150, 30);
        typeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        type = new JLabel();
        type.setBounds(320, 500, 150, 30);

// Add labels and components for confirmation buttons
        JButton confirm = new JButton("Confirm Booking");
        confirm.setBounds(120, 750, 200, 30);

        JButton previous = new JButton("Previous");
        previous.setBounds(350, 750, 120, 30);

        jFrame.add(previous);

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new Home();
            }
        });

        jFrame.add(companyName);
        jFrame.add(heading);
        jFrame.add(movieName);
        jFrame.add(movies);
        jFrame.add(movieTimings);
        jFrame.add(timings);
        jFrame.add(name);
        jFrame.add(nameInput);

        jFrame.add(ageLabel);
        jFrame.add(ageInput);
        jFrame.add(ticketLabel);
        jFrame.add(tickets);
        jFrame.add(languageLabel);
        jFrame.add(languages);
        jFrame.add(typeLabel);
        jFrame.add(type);
        jFrame.add(confirm);


        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the booking confirmation here (e.g., save to the database)
                jFrame.setVisible(false);
                // You can add code to navigate to a confirmation page or perform other actions.
            }
        });



        jFrame.setLayout(null);
        jFrame.setSize(700, 850);
        // Adjusted frame size to accommodate the new components
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fetch and set data from the database
        fetchBookingDetails(selectedMovieName, selectedMovieTiming, selectedCustomerName);
    }

    private void fetchBookingDetails(String movieName, String movieTiming, String customerName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "SELECT Age, Ticket_Count, Movie_Language, Movie_Type FROM movie_details WHERE Movie_Name=? AND Movie_Timing=? AND Customer_Name=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, movieName);
            statement.setString(2, movieTiming);
            statement.setString(3, customerName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ageInput.setText(resultSet.getString("Age"));
                tickets.setText(resultSet.getString("Ticket_Count"));
                languages.setText(resultSet.getString("Movie_Language"));
                type.setText(resultSet.getString("Movie_Type"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
