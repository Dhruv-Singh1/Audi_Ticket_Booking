package org.example;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Ticket implements Serializable {
    private static int accountStartID =1;
    protected Event event;
    protected ArrayList<Integer> premiumseatNO;
    protected ArrayList<Integer> normalseatNO;
    protected Student student;
    protected double ticketPrice;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public Ticket(Event event, ArrayList<Integer> normalseatNO,ArrayList<Integer> premiumseatNO, Student student) {
        this.event = event;
        this.normalseatNO=normalseatNO;
        this.premiumseatNO=premiumseatNO;
        this.student = student;
        this.ticketPrice=event.getTicketPriceNormal()* this.normalseatNO.size()+ event.getTicketPricePremium()*this.premiumseatNO.size();
    }
    
    public double getTicketPrice(){
        return this.ticketPrice;
    }
    
     public Event getEvent(){
        return this.event;
    }
    public ArrayList<Integer> getPremiumseatNO() {
        return premiumseatNO;
    }

    public ArrayList<Integer> getNormalseatNO() {
        return normalseatNO;
    }

    public static Ticket generateTicket(Event e, ArrayList<Integer> normalseatNO,ArrayList<Integer> premiumseatNO ,Student student){
       return new Ticket(e,normalseatNO,premiumseatNO,student);
    }

   @Override
    public String toString() {
        
        return "\n\n"+ event.getTitle() +" Ticket " +
                "\nStudent: " + student.getStuName() +
                "\nBITS ID: " + student.getBITSID() +
                "\nEvent Time:" + event.getstartTime() +
                "\nEvent Duration: " +event.geteventDuration()   +
                "\nTotal Price:  "+ df.format(this.getTicketPrice()) +
                "\nSeat Numbers: " + seatArrayToString(premiumseatNO)+"  "+seatArrayToString(normalseatNO); 
              }
    
    public String seatArrayToString(ArrayList<Integer> seatNo){
        StringBuilder seats= new StringBuilder("");
        
        for(int seat : seatNo){
             if(this.premiumseatNO==seatNo ){
                     seats=seats.append("P"+Integer.toString(seat)+"  ");                      
             }
             else{
                  seats=seats.append("N"+Integer.toString(seat)+"  ");
                 
             }      
        }
        
         return seats.toString();
    }
        
    public String printTicket() {
        return  this.toString();
    }
    
    public boolean generatePdf(){
        try {
            Document document = new Document();
            String home = System.getProperty("user.home");
            File path = new File(home+"/Downloads/"  +this.event.getTitle()+"_ticket.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font boldsp = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font bold2 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font base = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.ITALIC);

            Paragraph para0 = new Paragraph("   BIRLA INSTITUE OF TECHNOLOGY AND SCIENCE,PILANI  (Pilani Campus)",boldsp);
            para0.setAlignment(Element.ALIGN_CENTER);
            para0.setSpacingBefore(20);

            Paragraph para1 = new Paragraph(this.event.getTitle(),bold2);
            para1.setSpacingBefore(50);
            para1.setSpacingAfter(50);
            para1.setAlignment(Element.ALIGN_CENTER);

            Paragraph para2 = new Paragraph("Date & Time:",bold);
            para2.add(new Chunk("         "+this.event.getstartTime(),base));
            para2.setSpacingAfter(25);

            Paragraph para3 = new Paragraph("Venue Details:",bold);
            para3.add(new Chunk("         Main Audi",base));
            para3.setSpacingAfter(25);

            Paragraph para4 = new Paragraph("Student Name:",bold);
            para4.add(new Chunk("         "+this.student.getStuName(),base));
            para4.setSpacingAfter(25);

            Paragraph para5 = new Paragraph("Seat Number:",bold);
            para5.add(new Chunk("         "+seatArrayToString(premiumseatNO)+"  "+seatArrayToString(normalseatNO) ,base));
            para5.setSpacingAfter(25);

            Paragraph para6 = new Paragraph("Total Amount:",bold);
            para6.add(new Chunk("         "+  df.format(event.getTicketPriceNormal()* this.normalseatNO.size()+ event.getTicketPricePremium()*this.premiumseatNO.size())   ,base));
            Image img = Image.getInstance("/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/bits-logo.png");
            img.setSpacingBefore(20);
            img.setAlignment(Image.TEXTWRAP);
            img.setSpacingAfter(0);
            img.scaleToFit(80f, 80f);
            document.add(img);
            document.add(para0);
            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(para4);
            document.add(para5);
            document.add(para6);
            document.close();
            return true;
       }
        catch (IOException e) {
              e.printStackTrace();
        }
        catch (DocumentException e) {
             e .printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
