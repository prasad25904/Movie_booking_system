import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Movies {
    private JTable table;
    private DefaultTableModel tableModel;

    public Movies() {
        JFrame jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(250, 30, 200, 40);
        companyName.setFont(new Font("Comic Sans", Font.BOLD, 36));

        JLabel heading = new JLabel("Movies' Details");
        heading.setBounds(250, 120, 200, 30);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));

        String[] labels = {"Movie Name", "Show Time", "Theatre No.", "Cost (INR)"};

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(labels);
        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        table.setRowHeight(28);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(45, 160, 600, 400);

        JButton home = new JButton("Go To Home");
        home.setBounds(260, 600, 150, 30);

        jFrame.add(companyName);
        jFrame.add(heading);
        jFrame.add(sp);
        jFrame.add(home);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new Home();
            }
        });

        jFrame.setLayout(null);
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Call method to load data from the database into the JTable
        loadDataFromDatabase();
    }

    // Method to load data from the database into the JTable
    private void loadDataFromDatabase() {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");
            String query = "SELECT Movie_name, Movie_Timing, Theatre_no, Cost_INR FROM movie_details";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Clear the table
            tableModel.setRowCount(0);

            // Iterate through the result set and add data to the table
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("Movie_name"),
                        resultSet.getString("Movie_Timing"),
                        resultSet.getInt("Theatre_no"),
                        resultSet.getDouble("Cost_INR")
                };
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Movies();
        });
    }
}
