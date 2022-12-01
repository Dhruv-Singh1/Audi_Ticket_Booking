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
    

}
