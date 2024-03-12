import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookTickets {
    private JComboBox<String> movies;
    private JFrame jFrame;
    private DefaultComboBoxModel<Object> tickets;

    public BookTickets() {
        jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(250, 30, 200, 40);
        companyName.setFont(new Font("Comic Sans", Font.BOLD, 36));

        JLabel heading = new JLabel("Ticket Booking");
        heading.setBounds(250, 120, 200, 30);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));

        JLabel movieName = new JLabel("Movie Name");
        movieName.setBounds(180, 200, 200, 30);
        movieName.setFont(new Font("Times New Roman", Font.BOLD, 25));

        // Create a JComboBox for movie names and load data from the database
        movies = new JComboBox<>();
        movies.setBounds(370, 200, 150, 30);
        loadMovieNames();

        JLabel movieTimings = new JLabel("Movie Timings");
        movieTimings.setBounds(180, 250, 200, 30);
        movieTimings.setFont(new Font("Times New Roman", Font.BOLD, 25));

        String[] time = {"09:00", "10:00", "11:00", "13:00", "15:00", "18:00"};
        JComboBox<String> timings = new JComboBox<>(time);
        timings.setBounds(370, 250, 150, 30);



        JButton book = new JButton("Confirm");
        book.setBounds(380, 550, 150, 30);

        jFrame.add(companyName);
        jFrame.add(heading);
        jFrame.add(movieName);
        jFrame.add(movies);
        jFrame.add(movieTimings);
        jFrame.add(timings);

        // ... (other UI components)

        jFrame.add(book);

        JLabel name = new JLabel("Customer Name");
        name.setBounds(180, 300, 200, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 25));

        JTextField nameInput = new JTextField();
        nameInput.setBounds(370, 300, 150, 30);
        jFrame.add(nameInput);

        JLabel age = new JLabel("Age");
        age.setBounds(180, 350, 200, 30);
        age.setFont(new Font("Times New Roman", Font.BOLD, 25));

        JTextField ageInput = new JTextField();
        ageInput.setBounds(370, 350, 150, 30);
        jFrame.add(ageInput);

        JLabel ticketCount = new JLabel("Ticket Count");
        ticketCount.setBounds(180, 400, 200, 30);
        ticketCount.setFont(new Font("Times New Roman", Font.BOLD, 25));

        String[] ticket = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        JComboBox<String> tickets = new JComboBox<>(ticket);
        tickets.setBounds(370, 400, 150, 30);
        jFrame.add(tickets);

        JLabel movieLanguage = new JLabel("Movie Language");
        movieLanguage.setBounds(180, 450, 200, 30);
        movieLanguage.setFont(new Font("Times New Roman", Font.BOLD, 25));

        String[] language = {"English", "Hindi", "Telugu", "Marathi", "Malayalam", "Punjabi", "Urdu", "Japanese", "Korean"};
        JComboBox<String> languages = new JComboBox<>(language);
        languages.setBounds(370, 450, 150, 30);
        jFrame.add(languages);

        JRadioButton twoD = new JRadioButton("2D");
        twoD.setBounds(290, 500, 50, 30);
        JRadioButton threeD = new JRadioButton("3D");
        threeD.setBounds(360, 500, 50, 30);

        jFrame.add(name);
        jFrame.add(age);
        jFrame.add(ticketCount);
        jFrame.add(movieLanguage);

        jFrame.add(twoD);
        jFrame.add(threeD);

        JButton previous = new JButton("Previous");
        previous.setBounds(150, 550, 150, 30);
        jFrame.add(previous);

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new Home();
            }
        });

        // ActionListener for the "Confirm" button
        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected values
                String selectedMovie = (String) movies.getSelectedItem();
                String selectedTiming = (String) timings.getSelectedItem();
                String customerName = nameInput.getText();
                String customerAge = ageInput.getText();

                int customerAge2 = 0; // Initialize as 0

                try {

                    customerAge2 = Integer.parseInt(customerAge);
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    JOptionPane.showMessageDialog(jFrame, "Please enter a valid age as a number.");
                    return;
                }


                String ticketCount = (String) tickets.getSelectedItem();
                String selectedLanguage = (String) languages.getSelectedItem();
                boolean is3D = threeD.isSelected();

                // Store the booking information in the database
                storeBookingInfo(selectedMovie, selectedTiming, customerName, customerAge, ticketCount, selectedLanguage, is3D);

                nameInput.setText("");
                ageInput.setText("");
                tickets.setSelectedIndex(0);
                languages.setSelectedIndex(0);
                twoD.setSelected(false);
                threeD.setSelected(false);

                // Display booking details to the user
                displayBookingDetails(selectedMovie, selectedTiming, customerName, customerAge, ticketCount, selectedLanguage, is3D);
            }
        });

        jFrame.setLayout(null);
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to load movie names from the database
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

    String movieCost;

    private String loadMovieCost() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "SELECT Cost_INR FROM movie_details";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String movieCost = resultSet.getString("Cost_INR");

            }



            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieCost;
    }




    // Method to store booking information in the database
    private void storeBookingInfo(String selectedMovie, String selectedTiming, String customerName, String customerAge, String ticketCount, String selectedLanguage, boolean is3D) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "INSERT INTO user (Movie_name, Movie_timing, Customer_Name, Age, Ticket_count, Movie_language, Movie_type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, selectedMovie);
            statement.setString(2, selectedTiming);
            statement.setString(3, customerName);
            statement.setString(4, customerAge);
            statement.setString(5, ticketCount);
            statement.setString(6, selectedLanguage);
            statement.setBoolean(7, is3D);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking saved successfully!");
            } else {
                System.err.println("Booking failed.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookTickets();
        });
    }


    public String displayMovieNameAndCost() {
        String selectedMovie = (String) movies.getSelectedItem();
        int ticketCount = Integer.parseInt((String) tickets.getSelectedItem());

        // Retrieve the ticket cost per ticket from the database based on the selected movie
        double ticketCostPerTicket = getTicketCost(selectedMovie, String.valueOf(ticketCount));

        // Calculate the total ticket cost
        double totalTicketCost = ticketCount * ticketCostPerTicket;

        // Display the movie name and total ticket cost to the customer
        return selectedMovie;
    }


    private double getTicketCost(String selectedMovie, String ticketCount) {
        double ticketCostPerTicket = 0.0; // Initialize with a default value
        int count = Integer.parseInt(ticketCount);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "SELECT Cost_INR FROM movie_details WHERE Movie_name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedMovie);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ticketCostPerTicket = resultSet.getDouble("Cost_INR");
            }
            System.out.println(ticketCostPerTicket*count);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketCostPerTicket*count;
    }


    private void displayBookingDetails(String selectedMovie, String selectedTiming, String customerName, String customerAge, String ticketCount, String selectedLanguage, boolean is3D) {
        JFrame detailsFrame = new JFrame("Booking Details");

        JLabel n = new JLabel("Booking Details: ");
        n.setBounds(50,50,350,40);
        n.setFont(new Font("Comic Sans", Font.BOLD, 30));

        JLabel n1 = new JLabel("Movie: ");
        n1.setBounds(100,120,200,40);
        n1.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n2 = new JLabel(selectedMovie);
        n2.setBounds(200,120,200,40);
        n2.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n3 = new JLabel("Timing: ");
        n3.setBounds(100,170,200,40);
        n3.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n4 = new JLabel(selectedTiming);
        n4.setBounds(200,170,200,40);
        n4.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n5 = new JLabel("Customer Name: ");
        n5.setBounds(100,220,200,40);
        n5.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n6 = new JLabel(customerName);
        n6.setBounds(310,220,200,40);
        n6.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n7 = new JLabel("Age: ");
        n7.setBounds(100,260,200,40);
        n7.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n8 = new JLabel(customerAge);
        n8.setBounds(200,260,200,40);
        n8.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n9 = new JLabel("Ticket Count: ");
        n9.setBounds(100,300,200,40);
        n9.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n10 = new JLabel(ticketCount);
        n10.setBounds(250,300,200,40);
        n10.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n11 = new JLabel("Language: ");
        n11.setBounds(100,340,200,40);
        n11.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n12 = new JLabel(selectedLanguage);
        n12.setBounds(220,340,200,40);
        n12.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n13 = new JLabel("Movie Type: ");
        n13.setBounds(100,380,200,40);
        n13.setFont(new Font("Comic Sans", Font.BOLD, 20));

        JLabel n14 = new JLabel(is3D ? "3D" : "2D");
        n14.setBounds(250,380,200,40);
        n14.setFont(new Font("Comic Sans", Font.BOLD, 20));


        JButton confirm = new JButton("Confirm");
        confirm.setBounds(250, 550, 150, 30);
        detailsFrame.add(confirm);
        detailsFrame.add(n);
        detailsFrame.add(n1);
        detailsFrame.add(n2);

        detailsFrame.add(n3);
        detailsFrame.add(n4);

        detailsFrame.add(n5);
        detailsFrame.add(n6);

        detailsFrame.add(n7);
        detailsFrame.add(n8);

        detailsFrame.add(n9);
        detailsFrame.add(n10);

        detailsFrame.add(n11);
        detailsFrame.add(n12);

        detailsFrame.add(n13);
        detailsFrame.add(n14);


//        detailsFrame.add(scrollPane);
        detailsFrame.setLayout(null);
        detailsFrame.setSize(700, 700);

        detailsFrame.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Payment(selectedMovie,selectedTiming,customerName,customerAge,ticketCount,selectedLanguage,is3D, getTicketCost(selectedMovie,ticketCount));
            }
        });
    }


}