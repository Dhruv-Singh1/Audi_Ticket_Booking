package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Student  extends Thread implements  BaseUser ,Bookable,Serializable{
    private String name;
    private String bitsID;
    private String email;
    private String password;
    private ArrayList<Ticket> bookedTickets ;
    private static ArrayList<Student> students;
    double SWDCharges;
    static final long serialVersionUID = 38L;
    public String getStuName() {
        return name;
    }

    public void seStutName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getBITSID(){
        return this.bitsID;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSWDCharges() {
        return SWDCharges;
    }

    public void setSWDCharges(double SWDCharges) {
        this.SWDCharges = SWDCharges;
    }

    public ArrayList<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", BITS ID=" + bitsID +
                ", Total Charges=" + SWDCharges +
                '}';
    }
//constructor block to read students from file
    {
    
        students = new ArrayList<>();
        
        String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/student.ser";
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            students= (ArrayList<Student>)is.readObject();          
           // ArrayList<Student> s = (ArrayList<Student>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e){
             System.out.println(e.toString());            
        }
        catch(IOException e){
           
                   
        }
        catch(ClassNotFoundException e){
          //  e.printStackTrace();
            System.out.println(e);
        }
    };

    Student(){
        this.name=null;
        this.email=null;
        this.password=null;
        this.bookedTickets= new ArrayList<>();
    }
    Student(String name, String bitsID, String email, String password){
        if(name!=null){
            this.name  =name;
        }
        if(email!=null){
            //add regex to check correct format
            this.email=email;
        }
        if(password!=null){
            //add regex to check correct format
            this.password=password;
        }
        this.bitsID= bitsID;
        this.bookedTickets= new ArrayList<>();


    }

    public boolean viewRegisteredEvents(){
        if(bookedTickets.isEmpty()){
              System.out.println("\nYou are not registered for any events currently..");
              return false;
        }
        System.out.println("\nYou have registered for the following events: ");
        int i=1;
        
        for(Ticket ticket : bookedTickets){
            System.out.println(i++ +". ");
            System.out.println(ticket.toString());
            System.out.println();
        }
        return true;
    }
    
    public static Student getLoginObj(){
        return new Student();
    }
    
 
    public   boolean login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email");
        String email= sc.next();
       
        int f=0;
        for (Student student:
             students) {
            if (student.getEmail().equalsIgnoreCase(email) ) {
                System.out.println("Student found in database");
                System.out.println("Enter your Password");
                String password=sc.next();
                System.out.println("\nChecking credentials...");
                if (student.getPassword().equals(password)) {
                    System.out.println("Login Successful ");
                    
                    student.start();
                    try{
                    student.join();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    return true;
                    //call user home page
                } else {
                    System.out.println("Invalid Password Try Again!!");
                    f = 1;
                }
            }
        }
            if(f==0){
                System.out.println("Email ID not found in database please login again...");
            }
        return false;
    }

    
    public boolean signup(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Name:  ");
        String name= sc.nextLine();
        System.out.println();
        System.out.print("Enter your Email:  ");
        String email= sc.nextLine();
        System.out.println();
        System.out.println("Enter your Password");
        String password="";
        
        Console cnsl= System.console();
  
        if (cnsl == null) {
           // System.out.println( "\t\t\tNo console available");
            password = sc.nextLine();
            System.out.println();
        }
        else{
              // Read password
        // into character array
        char[] ch = cnsl.readPassword( "Enter password : ");
         password= new String(ch);
         System.out.println();
        
        }
//        System.out.print("Enter your BITS ID:   ");
//        String bitsID= sc.nextLine();
        System.out.println("\n");
        Student student = new Student(name,"",email,password);
        students.add(student);
        System.out.print("Account Created Successfully"+student);
        System.out.println("\n");
        StudentHomePagePrinter homePagePrinter= new StudentHomePagePrinter(student);
        homePagePrinter.studentHomePage();
        return true;
    }
    
    

    public boolean logout(){
       StudentPersistance studentPersistance= new StudentPersistance(students);
       studentPersistance.saveToFile();
        return true;

    }
    
//    @Override
//    public void run(){
//       studentHomePage();
//
//   }
    

    public void bookTicketMenu(){
        System.out.println("Following are the upcoming events in the Auditorium:");
        this.printEvents();
        System.out.println("\n\nEnter the event number you want to register for ");
        Scanner sc = new Scanner(System.in);
        int eno = Integer.parseInt(sc.next());
        bookTicket(Audi.getAudiObj().getEvents().get(eno-1));

    }
    

    synchronized public boolean bookTicket(Event event){
        // Displaying available seats
        SeatsPrinter.printSeats(event.getBookedSeats());
        System.out.println("Note enter the Seat number without the prefix 'P' or 'N' ");
        System.out.println("Enter Seats: ");
        //take seats input
        String seats;
        Scanner sc = new Scanner(System.in);
        seats=sc.nextLine();
        String [] seatno = seats.split(" ");
        ArrayList<Integer> normalSeats = new ArrayList<>();
        ArrayList<Integer> premiumSeats = new ArrayList<>();

        boolean errorflag=false;
        for (int i = 0; i < seatno.length; i++) {
            int seatnumber = Integer.parseInt(seatno[i])-1;
            Audi audi = Audi.getAudiObj();
            int row = seatnumber / audi.getSeatrows();
            int col = seatnumber % audi.getSeatcolumns();
            int floor = 0;
            if (row>= audi.getSeatrows()) {
                floor = 1;
                row = (seatnumber- audi.getSeatrows()*audi.getSeatcolumns())/ audi.getSeatrows();
            }
            
            boolean[][][] bookedSeats = event.getBookedSeats();
            if (bookedSeats[floor][row][col]) {
                System.out.println("Error the Entered Seat number " + (seatnumber+1) + " is already booked!!\nEnter some unbooked Seat No...\n");
                errorflag=true;
               // bookTicket(event);
                break;
            }
            else {
               
                bookedSeats[floor][row][col]=true;
                if(col>= (audi.getSeatcolumns()/3) && col<= (2*audi.getSeatcolumns()/3) ){
                     premiumSeats.add(Integer.valueOf(seatno[i]));
                }
                else {
                      normalSeats.add(Integer.valueOf(seatno[i]));
                }
            }
        }
        
        
        //checked for valid avail seats
        if(!errorflag){     
            double toatlAmount= normalSeats.size()*event.getTicketPriceNormal()+event.getTicketPricePremium()* premiumSeats.size();
            Ticket ticket=Ticket.generateTicket(event,normalSeats,premiumSeats,this );
            if(ticket==null){
                 System.out.println("Error!! couldn't generate the Ticket");
                 return false;
            }
            else{
            bookedTickets.add(ticket);
            SWDCharges+=toatlAmount;
            //adding to net event revenue
            event.setNetRevenue(event.getNetRevenue()+toatlAmount);
           //event.
           System.out.println("Toatal Price paid: "+normalSeats.size()+"*"+event.getTicketPriceNormal()+"+"+premiumSeats.size()+"*"+event.getTicketPricePremium());
           System.out.println("Tickets booked Successfully!!");
           System.out.println("\n"+ticket.toString());
           event.registeredStudents.addStudent(this);
            return true;
        }
        //generated ticket details
        }
        return false;

    }

    synchronized  public  boolean cancelTicket(){
        // Displaying boooked tickets
        if(!this.viewRegisteredEvents()){
            return false;
        }
        System.out.println("Enter the Ticket Number which you want to cancel ");
        Scanner sc = new Scanner(System.in);        
       
        int ticketNo=Integer.parseInt(sc.nextLine());
        Ticket ticket=this.bookedTickets.get(ticketNo-1);
        Event event = ticket.getEvent();
        int j=0;
       for(Event e: Audi.getAudiObj().getEvents()){
       if(e.getTitle().equals(event.getTitle())){
           System.out.println("Found");
           event= Audi.getAudiObj().getEvents().get(j);
           break;
       }
       j++;
       }
       
         
         ArrayList<Integer> seatno = new ArrayList();
         seatno.addAll(ticket.getNormalseatNO());
         seatno.addAll(ticket.getPremiumseatNO());
        boolean[][][] bookedSeats = event.getBookedSeats();
        boolean errorflag=false;
        for (int i = 0; i < seatno.size(); i++) {
            int seatnumber = seatno.get(i);
            seatnumber-=1;
            Audi audi = Audi.getAudiObj();
            int row = seatnumber / audi.getSeatrows();
            int col = seatnumber % audi.getSeatcolumns();
            int floor = 0;
            if (row>= audi.getSeatrows()) {
                floor = 1;
                row = (seatnumber- audi.getSeatrows()*audi.getSeatcolumns())/ audi.getSeatrows();
            }
               
            
            if (bookedSeats[floor][row][col]) {
                bookedSeats[floor][row][col]=false;    
                event.getBookedSeats()[floor][row][col]=false;  
                System.out.println(" Seat number " + (seatnumber+1) + " getting unbooked...");               
                  
            }
            else {
              errorflag=true;
              System.out.println("Error seat already unbooked");  
                 bookedTickets.remove(ticket);
            }
        }
        
        
        //checked for valid avail seats
        if(!errorflag){
            event.setBookedSeats(bookedSeats);
            double totalAmount=ticket.getTicketPrice();
            bookedTickets.remove(ticket);
            SWDCharges-=totalAmount;
            //subtract to net event revenue
            event.setNetRevenue(event.getNetRevenue()-totalAmount);
            event.registeredStudents.removeStudent(this);
             System.out.println(" Ticket canceled sucessfully for "+event.getTitle());
            return true;
        
        //generated ticket details
        }
        return false;

    }

}
