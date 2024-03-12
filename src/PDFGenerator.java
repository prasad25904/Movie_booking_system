import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PDFGenerator {
    public static void main(String[] args) {
        try {
            String file_name = "F:\\SY sem1\\OOPs-JAVA\\CP\\Ticket.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream(file_name));
            document.open();

            Paragraph para=new Paragraph("                                                               Movie Booking Details");
            Paragraph para0=new Paragraph("                                                         _______________________\n\n\n\n\n");


            Paragraph para1=new Paragraph("  Movie Name : Oh My God!");
            Paragraph para2=new Paragraph("  Movie Timings : 13:00");
            Paragraph para3=new Paragraph("  Customer Name : Johs Sans");
            Paragraph para4=new Paragraph("  Age : 20");
            Paragraph para5=new Paragraph("  Ticket Count : 1");
            Paragraph para6=new Paragraph("  Movie Language : English");
            Paragraph para7=new Paragraph("  Movie Type : 2D");


            document.add(para);
            document.add(para0);

            //image
           // document.add(Image.getInstance("F:\\SY sem1\\OOPs-JAVA\\CP\\ticket.jpeg"));
            Image image = Image.getInstance("F:\\SY sem1\\OOPs-JAVA\\CP\\ticket.jpeg");

            Rectangle pageSize = document.getPageSize();
            float x = (pageSize.getWidth() - image.getScaledWidth()) / 2;
            float y = (pageSize.getHeight() - image.getScaledHeight()) / 2;

            image.setAbsolutePosition(50, 68);


            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(para4);
            document.add(para5);
            document.add(para6);
            document.add(para7);

            document.close();
            System.out.println("Finished......");

            }
        catch (Exception e){
            System.err.println(e);
        }
    }
    public static void generatePDF(String fileName, String content) {

    }
}
