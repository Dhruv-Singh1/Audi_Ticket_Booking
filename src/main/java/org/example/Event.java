package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import org.jetbrains.annotations.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event implements Comparable<Event>, Serializable {

    private String organizer;
    private String title;
    private String eventDetails;
    private double ticketPriceNormal;
    private double ticketPricePremium;
    private Date startTime; // calendar class
    private Date endTime;
    private boolean [][][] bookedSeats;
    private Audi audi = Audi.getAudiObj();
    private  double netRevenue;
    RegisteredStudents registeredStudents ;
    private int seatno []=new int[Audi.getTotalSeats()];
    
    static final long serialVersionUID = 40L;

    public String geteventDuration(){
         long diff=(endTime.getTime()-startTime.getTime())/60000;
         return  diff/60+" hrs  "+ diff%60+" mins";
    }
    
    @Override
    public String toString() {
       //  Period p =Period.between(startTime,endTime);
       SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
        return
                "\n\t" + title  +
                "\t   by: " + organizer + 
                "\n Event Details: \t" + eventDetails  +
                "\n Price of Normal Seats: \t" + ticketPriceNormal +
                "\n Price of Premium Seats: \t" + ticketPricePremium +
                "\n Start Time: \t\t\t" + 
                format.format(startTime)  +
                "\n Event Duration: \t\t" +geteventDuration()                
//                +"\n Net Revenue of Event: \t\t" + netRevenue +"Rs\n\n"
//             //   "\n Booked Seats:  " + Arrays.toString(bookedSeats)
                ;
    }

    
    // is Booked on date / time ?
    // book method
    
    
        public void printSeats(){
            int i=1; char c='N';
         for (boolean[][] flr : bookedSeats) {
            for (boolean[] row: flr) {
                for(boolean seat: row) {
                    
                    String txt= (seat)?"BookD":"Avail";
                    if(i%Audi.getAudiObj().getSeatrows()-1 >=Audi.getAudiObj().getSeatcolumns()/3&& 
                            i%Audi.getAudiObj().getSeatrows()-1 <= 2*Audi.getAudiObj().getSeatcolumns()/3){
                        c='P';
                         System.out.printf(" \033[0;1m"+c + "%03d %-6s ",i++,txt);
                    }
                    else{
                        c='N';
                    System.out.printf(" "+c + "%03d %-6s ",i++,txt);
                    }
                }System.out.println();
            } System.out.println("\n\n\n"); 
        }   System.out.println("Seat Number starting 'P' are Premium seats and 'N' are Normal seats...");
    }
    
    
    Event(){
        this.eventDetails=null;
        this.organizer=null;
        //intializing the inner class
      //  this.registeredStudents = this.new RegisteredStudents();
        this.title=null;
        this.ticketPriceNormal=0.00;
        this.netRevenue=0.00;
        this.bookedSeats = Audi.getAudiObj().getseatsMatrix();
        for (int i=0;i< seatno.length;i++){
            seatno[i]=i+1;
        }
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public double getTicketPriceNormal() {
        return ticketPriceNormal;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPriceNormal = ticketPrice;
    }
      public double getTicketPricePremium() {
        return ticketPricePremium;
    }

    public void setTicketPricePremium(double ticketPrice) {
        this.ticketPricePremium = ticketPrice;
    }

    public  Date getstartTime() {
        return startTime;
    }

    public  Date getendTime() {
        return endTime;
    }

    public  void setstartTime(Date datetime) {
        this.startTime = datetime;
    }

    public boolean[][][] getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(boolean[][][] bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Audi getAudi() {
        return audi;
    }

    public void setAudi(Audi audi) {
        this.audi = audi;
    }

    public int[] getSeatno() {
        return seatno;
    }

    public void setSeatno(int[] seatno) {
        this.seatno = seatno;
    }


    

    
    Event( String title,String organizer, String eventDetails, double ticketPriceNormal,double ticketPricePremium, Date datetime,Date endtime){
        this.eventDetails=eventDetails;
        this.organizer=organizer;
        this.registeredStudents= this.new RegisteredStudents();
        this.title=title;
        this.ticketPriceNormal=ticketPriceNormal;
        this.ticketPricePremium=ticketPricePremium;
        this.startTime=datetime;
        this.endTime=endtime;
        this.bookedSeats = Audi.getAudiObj().getseatsMatrix();
        for (int i=0;i< seatno.length;i++){
            seatno[i]=i+1;
        }
        this.netRevenue=0.00;
    }


    public double getNetRevenue() {
        return netRevenue;
    }

    @Override
    public int compareTo(@NotNull Event event){
        return this.getstartTime().compareTo(event.getstartTime());
    }


    public void setNetRevenue(double netRevenue) {
        this.netRevenue = netRevenue;
    }

    public static Event addEvent(){
       
        try {
           
            File file = new File("/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/events.txt");
//             try{
//            Scanner fileSc = new Scanner(file);
//             }
//             
//             catch (FileNotFoundException ex) {
//            System.out.println("Error!! Input text file not found");;
//             }
                
             Scanner sc = new Scanner(System.in);
            System.out.print("Enter the name of Event:\t");
            String title;
//            if (fileSc.hasNextLine())
//                title= fileSc.nextLine();
            title= sc.nextLine();
            System.out.println();
             
            System.out.print("Enter the name of Organizer:\t");
            String organizer= sc.nextLine();
            System.out.println();
            
            System.out.print("Enter the Event date and time in this format dd-MMM-yyyy hh:mm   Eg: 17-Jul-2022 18:35:   ");
            String startTime= sc.nextLine();        
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
            System.out.print("\t");
            Date startDate = format.parse(startTime);
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);         
            System.out.println();
            
            System.out.print("Enter the duration in this format hh:mm   ");
            String duration= sc.nextLine();
            String[] dr= duration.split(":");
            System.out.println();
            
            Date endTime = new Date(c.getTimeInMillis()+1000*60*(Integer.parseInt(dr[0])*60 + Integer.parseInt(dr[1])) );
            
            for(Event e: Audi.getAudiObj().getEvents()){
                    if(e.getstartTime().compareTo(startDate)<0 || e.getstartTime().compareTo(startDate)==0  ){
                        //regEvent starts before this event
                        if(e.getendTime().compareTo(endTime)>0 ||e.getendTime().compareTo(startDate)>0 ){
                            //regEvent ends after current event startTime or endTIme
                            System.out.print("\nError time slot clashes with "+e.getTitle()+" it starts at "+e.getstartTime()+" and ends at "+e.getendTime());
                            System.out.print("\n Please choose another Time slot ");
                            return null;
                        }               
                    }
                    
                    if(e.getstartTime().compareTo(startDate)>0&&e.getstartTime().compareTo(endTime)<0 ){                                          
                        //regEvent starts after this event but this event ends after start of regEvent
                        System.out.print("\nError time slot clashes with "+e.getTitle()+" it starts at "+e.getstartTime()+" and ends at "+e.getendTime());
                        System.out.print("\n\nPlease choose another Time slot ");
                        return null;                        
                    }              
            }
            
             
            System.out.print("\nChecked availability of the entered timeslot... ");
                  
            System.out.print("\nEnter the price of normal seats:\t");
            double pricen = Double.parseDouble(sc.next());
            System.out.println();
             
            System.out.print("Enter the price of premium seats:\t");
            double pricep = Double.parseDouble(sc.next());
            System.out.println();
            sc.nextLine();
            System.out.print("Enter the event details:\t");
            String details= sc.nextLine();
            //System.out.println()"Event added s";
            return new Event(title,organizer,details,pricen,pricep,startDate,endTime);
            

        }   catch(NumberFormatException e ){
               System.out.println("Entered data is not a number");
           }
           catch(ParseException e ){
               System.out.println("Invalid date format unable to parse...");
           }catch (Exception e) { //ParseException for date
             System.out.println(e);
        }

        return null;
    }
    
    public boolean editEvent(){
        
        System.out.println("\nEnter the field number you want to edit :");
        System.out.println("1. Name of Event\n2.Date and Time\n3.Normal ticket Price\n4.Premium Ticket Price\n5.Organizer\n6.Event Details\n");
        Scanner sc = new Scanner(System.in);
        int ch = Integer.parseInt(sc.next());
        switch(ch){
            case 1:
                System.out.println("Enter the new Event Name");
                String name= sc.nextLine();
                System.out.println("Updated...");
                this.setTitle(name);
                this.toString();
                break;
            case 2:
                System.out.println("Enter the new Date and Time");
                String date= sc.nextLine();
                System.out.print("Enter the Event date and time in this format dd-MMM-yyyy hh:mm   Eg: 17-Jul-2022 18:35:   ");
                String startTime= sc.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
                 Date startDate ;
                try{
                 startDate = format.parse(startTime); 
                this.setstartTime(startDate);
                }
                catch(ParseException e ){
                        System.out.println("Invalid date format unable to parse...");
                    }catch (Exception e) { //ParseException for date
                     e.printStackTrace();
                 }
                System.out.println("Updated...");
                this.toString();
                break;               
            case 3:
                System.out.println("Enter the new Normal Ticket Price");
                double price= Double.parseDouble(sc.nextLine());
                this.setTicketPrice(price);
                System.out.println("Updated...");
                this.toString();
                break;
            case 4:
              System.out.println("Enter the new Premium Ticket Price");
                double premiumprice= Double.parseDouble(sc.nextLine());
                this.setTicketPricePremium(premiumprice);
                System.out.println("Updated...");
                this.toString();
                break;
            case 5:
                System.out.println("Enter the new Event Organizer");
                String org= sc.nextLine();
                this.setOrganizer(org);
                System.out.println("Updated...");
                this.toString();
                break;
            case 6:
                System.out.println("Enter the new Event details");
                String details= sc.nextLine();
                this.setEventDetails(details);
                System.out.println("Updated...");
                this.toString();
                break;
            default:
                System.out.println("Invalid choice");
                break;         
        }
        return false;
    }


    
    
    
    class RegisteredStudents extends Event {
        ArrayList<Student> registeredForEvent;
        RegisteredStudents(){
            registeredForEvent= new ArrayList<>();
        }

        public void addStudent(Student usr) {

            registeredForEvent.add(usr);
         //   netRevenue+=  //which seat typt  x* seatcount;
        }

//        public void addStudents(ArrayList<Student> usr) {
//            registeredForEvent.addAll(usr);
//        }



    }

    

}
