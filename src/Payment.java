import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

public class Payment {

    public Payment(String selectedMovie,String selectedTiming, String customerName, String customerAge, String ticketCount, String selectedLanguage, boolean is3D, double ticketCostPerTicket){
        JFrame jFrame = new JFrame("Movie Ticket Booking");

        JLabel companyName = new JLabel("MovieBook");
        companyName.setBounds(250,30,200,40);
        companyName.setFont(new Font("Comic Sans",Font.BOLD,36));

        JLabel heading = new JLabel("Payment Details");
        heading.setBounds(250,120,200,30);
        heading.setFont(new Font("Times New Roman",Font.BOLD,28));

        JLabel movieName = new JLabel("Movie Name");
        movieName.setBounds(180,200,200,30);
        movieName.setFont(new Font("Times New Roman",Font.BOLD,25));


        JLabel movies = new JLabel(selectedMovie);
        movies.setBounds(370,200,150,30);

        JLabel movieTimings = new JLabel("Total Cost");
        movieTimings.setBounds(180,250,200,30);
        movieTimings.setFont(new Font("Times New Roman",Font.BOLD,25));

        JLabel timings = new JLabel(String.valueOf((ticketCostPerTicket)));
        timings.setBounds(370,250,150,30);

        JRadioButton btn = new JRadioButton("Paytm");
        btn.setBounds(70,320,150,30);
        JRadioButton btn2 = new JRadioButton("Cash");
        btn2.setBounds(270,320,150,30);
        JRadioButton btn3 = new JRadioButton("Debit/Credit Card");
        btn3.setBounds(470,320,150,30);
        JRadioButton btn4 = new JRadioButton("Google Pay");
        btn4.setBounds(70,370,150,30);
        JRadioButton btn5 = new JRadioButton("Phone Pay");
        btn5.setBounds(270,370,150,30);
        JRadioButton btn6 = new JRadioButton("Amazon Pay");
        btn6.setBounds(470,370,150,30);

        JButton pay = new JButton("Pay");
        pay.setBounds(270,430,150,30);

        jFrame.add(companyName);
        jFrame.add(heading);
        jFrame.add(movieName);
        jFrame.add(movies);
        jFrame.add(movieTimings);
        jFrame.add(timings);
        jFrame.add(btn);
        jFrame.add(btn2);
        jFrame.add(btn3);
        jFrame.add(btn4);
        jFrame.add(btn5);
        jFrame.add(btn6);
        jFrame.add(pay);

        jFrame.setLayout(null);
        jFrame.setSize(700,700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    int choice = fileChooser.showSaveDialog(null);
                    if (choice == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getPath();


                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(filePath));
                        document.open();

                        Paragraph paragraph = new Paragraph("MOVIE TICKET ");

                        paragraph.setAlignment(paragraph.ALIGN_CENTER);
                        paragraph.setSpacingAfter(50f);
                        Paragraph p1=new Paragraph("Movie Name:   "+selectedMovie);
                        p1.setIndentationLeft(200f);
//                        Paragraph p2=new Paragraph(selectedMovie);
//                        p2.setIndentationLeft(100f);

                        Paragraph p3=new Paragraph("Movie Timing:   "+selectedTiming);
                        p3.setIndentationLeft(200f);
                        //Paragraph p4=new Paragraph(selectedTiming);

                        Paragraph p5=new Paragraph("Customer Name:    "+customerName);
                        p5.setIndentationLeft(200f);
                        //Paragraph p6=new Paragraph(customerName);

                        Paragraph p7=new Paragraph("Customer Age:    "+customerAge);
                        p7.setIndentationLeft(200f);
                       // Paragraph p8=new Paragraph(customerAge);

                        Paragraph p9=new Paragraph("Ticket Count:    "+ticketCount);
                        p9.setIndentationLeft(200f);
                        //Paragraph p10=new Paragraph(ticketCount);

                        Paragraph p11=new Paragraph("Language:   "+selectedLanguage);
                        p11.setIndentationLeft(200f);
                       // Paragraph p12=new Paragraph(selectedLanguage);

                        Paragraph p14;
                        if(String.valueOf(is3D).equals(false)){
                            p14=new Paragraph("2D");
                        }
                        else{
                            p14=new Paragraph("3D");
                        }
                        //p14.setIndentationLeft(280f);
                        Paragraph p13=new Paragraph("Movie Type:   "+p14);
                        p13.setIndentationLeft(200f);

                        document.add(paragraph);
                        document.add(p1);

                        document.add(p3);
                        //document.add(p4);
                        document.add(p5);
                        //document.add(p6);
                        document.add(p7);
                        //document.add(p8);
                        document.add(p9);
                        //document.add(p10);
                        document.add(p11);
                        //document.add(p12);
                        document.add(p13);
                        //document.add(p14);

                        document.close();

                        JOptionPane.showMessageDialog(null, "PDF generated and saved successfully!");

                        jFrame.setVisible(false);
                        new Home();



                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error generating PDF.");
                    JOptionPane.showMessageDialog(null, "PDF with this name exist.");
                }
            }
        });
    }


}