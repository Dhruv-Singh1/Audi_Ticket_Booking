package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Student  implements  BaseUser ,Serializable{
    private String name;
    private String bitsID;
    private String email;
    private String password;
    private ArrayList<Event> registeredEvents;
    private static ArrayList<Student> students;
    double SWDCharges;
    static final long serialVersionUID = 38L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
            // System.out.println("");            
        }
        catch(IOException e){
           
                   
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    };

    Student(){
        this.name=null;
        this.email=null;
        this.password=null;
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


    }

    @Serial
    private void writeObject(ObjectOutputStream oos)
            throws IOException {
       
        oos.defaultWriteObject();
        // How many students we're tracking.
        oos.writeInt(students.size());
       
       for (Student student : students) {
            oos.writeObject(student);
        }
        System.out.println("session serialized");
    }

    // Control how we read in Student(s).
    @Serial
    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
              
        ois.defaultReadObject();
        // how many Students to read.
        int size = ois.readInt();
        for (int i = 0; i < size; i++) {
            Student s = (Student) ois.readObject();
            students.add(s);
        } System.out.println("\nsession deserialized");
    }

    void clearOldEvents(){
        Date d= new Date();
        for(int i=0;i<registeredEvents.size();i++){
            // registeredEvents[i].get_time()<d.;

        }
    }



    public ArrayList<Event> viewRegisteredEvents(){
        return registeredEvents;
    }
    
    public static Student getLoginObj(){
        return new Student();
    }
    
    @Override
    public  boolean login(){
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
                    
                    student.studentHomePage();
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
        System.out.print("Enter your BITS ID:   ");
        String bitsID= sc.nextLine();
        System.out.println("\n");
        Student student = new Student(name,bitsID,email,password);
        students.add(student);
        System.out.print("Account Created Successfully"+student);
        System.out.println("\n");
        student.studentHomePage();
        return true;
    }
    
    

    public boolean logout(){
        String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/student.ser";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(students);
            os.flush();
            os.close();
        }
        catch(FileNotFoundException e){
            // System.out.println("");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return true;

    }
    
    public void studentHomePage(){
        System.out.println("Hii "+this.getName());
        System.out.println("Enter 1. to see upcoming Event List");
        System.out.println("Enter 2. to book Ticket");
        System.out.println("Enter 3. to cancel booked Tickets");
        System.out.println("Enter 4. to  see your registered events");
        System.out.println("Enter 5. to Login to different Account");
        System.out.println("Enter 6. to SignUp to a new Accounts");
        System.out.println("Enter 7. to Logout");
        Scanner sc = new Scanner(System.in);
        int no = Integer.parseInt(sc.next());
        switch(no){
            case 1:
                this.printEvents();
                break;
            case 2:
                this.bookTicketMenu();
                break;
            case 3:
                this.bookTicketMenu();
                break;
            case 4:
                printEvents();
                break;
            case 5:
                this.login();
                break;
            case 6:
                this.signup();
                break;
            case 7:
                this.logout();
                break;
            default:
        }
    }
    
    
 
    
    
    public void bookTicketMenu(){
        System.out.println("Following are the upcoming events in the Auditorium:");
        this.printEvents();
        System.out.println("\n\nEnter the event number you want to register for ");
        Scanner sc = new Scanner(System.in);
        int eno = Integer.parseInt(sc.next());
        bookTicket(Audi.getAudiObj().getEvents().get(eno-1));

    }
    

    synchronized  private  boolean bookTicket(Event event){
        //display audi seats
//        for (int s = 0; s < event.getBookedSeats().length; s++) {
//            for (int r = 0; r < event.getBookedSeats()[0].length; r++) {
//                for (int c = 0; c < event.getBookedSeats()[0][0].length; c++) {
//                    System.out.println(" ");
//                }
//            }
//        }

        event.printSeats();
        System.out.println();
        System.out.println("Select Seats: ");
        //take seats input
        String seats;
        Scanner sc = new Scanner(System.in);
        seats=sc.next();
        String [] seatno = seats.split(" ");
        Integer [] intseatno = new Integer[seatno.length];

        for (int i = 0; i < seatno.length; i++) {
            intseatno[i] = Integer.valueOf(seatno[i]);


        }
        boolean errorflag=false;
        for (int i = 0; i < seatno.length; i++) {

            Audi audi = Audi.getAudiObj();
            int row = intseatno[i] / audi.getSeatrows();
            int col = intseatno[i] % audi.getSeatrows();
            int floor = 0;
            if (row>= audi.getSeatcolumns()) {
                floor = 1;
                row = (intseatno[i]- audi.getSeatrows())/ audi.getSeatcolumns();
            }
            boolean[][][] bookedSeats = event.getBookedSeats();
            if (bookedSeats[row][col][floor]) {

                System.out.println("Error the Entered Seat number" + intseatno[i] + "is already booked!!\nEnter unbooked seat No");
                errorflag=true;
                break;
            }

            else {
                bookedSeats[row][col][floor]=true;
            }
        }

        //checked for valid avail seats
        if(!errorflag){
            SWDCharges += seatno.length*event.getTicketPrice();
            Ticket.generateTicket(event,new ArrayList<>(Arrays.asList(intseatno)),this);
            //add usr to events registered user
            //add event revenue
            System.out.println("Tickets booked Successfully!!");
            return true;
        }
        else{
            System.out.println("");

        }
        //generated ticket details

        return false;

    }

}
