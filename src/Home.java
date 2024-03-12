
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

    public class Home{
        private final String movies_name = new String();
        private final String movie_timing=new String();
        private final String customer_name=new String();
        private final int age=0;
        //Age.getText();
        private final String movie_language=new String();
        private final int ticket_count=0;
        //Ticket_count.getText();
        private final int movie_type=0;
        //Movie_type.getText();

    public Home(){
        JFrame jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(250,30,200,40);
        companyName.setFont(new Font("Comic Sans",Font.BOLD,36));

        ImageIcon i = new ImageIcon("F:\\SY sem1\\OOPs-JAVA\\CP\\ticket.jpeg");
        JLabel jLabel1 = new JLabel(i);
        jLabel1.setBounds(90,0,500,400);

        JButton movies = new JButton("Movies");
        movies.setBounds(70, 350, 150,30);

        JButton bookTickets = new JButton("Book Tickets");
        bookTickets.setBounds(270, 350, 150,30);

        JButton cancelTickets = new JButton("Cancel Tickets");
        cancelTickets.setBounds(470, 350, 150,30);

        JLabel bottom1 = new JLabel("Purchase Movie Tickets from the");
        bottom1.setBounds(100,430,500,40);
        bottom1.setFont(new Font("Sans serif",Font.BOLD,32));

        JLabel bottom2 = new JLabel("comfort of your homes");
        bottom2.setBounds(150,480,500,40);
        bottom2.setFont(new Font("Sans serif",Font.BOLD,32));

        JLabel bottom3 = new JLabel("with ease...");
        bottom3.setBounds(250,530,500,40);
        bottom3.setFont(new Font("Sans serif",Font.BOLD,32));



        jFrame.add(companyName);
        jFrame.add(jLabel1);
        jFrame.add(movies);
        jFrame.add(bookTickets);
        jFrame.add(cancelTickets);
        jFrame.add(bottom1);
        jFrame.add(bottom2);
        jFrame.add(bottom3);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "ppp25904");

            String query = "INSERT INTO account values('" + movies_name + "','" + movie_timing + "','" + customer_name + "','" +
                    age + "','" + ticket_count + "','" + movie_language + "','"+ movie_type+"')";

            Statement sta = connection.createStatement();


            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }



        movies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new Movies();
            }
        });
        bookTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new BookTickets();
            }
        });
        cancelTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new CancelTickets();
            }
        });

        jFrame.setLayout(null);
        jFrame.setSize(700,700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Home();
    }
}