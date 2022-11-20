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
    double SWDCharges;
    ArrayList<Student> students;
    ArrayList<Event> registeredEvents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
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
        try {
            String filename=  "../../storagefiles/student.bin";
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            readObject(is);

           // ArrayList<Student> s = (ArrayList<Student>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e){
            // System.out.println("");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    };

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
        }
        System.out.println("session deserialized");
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

    public  boolean login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email");
        String email= sc.next();
        System.out.println("Enter your Password");
        String password=sc.next();
        int f=0;
        for (Student student:
             students) {
            if (student.getEmail() == email) {
                System.out.println("Student found in database\nChecking credentials...");
                if (student.getPassword() == password) {
                    System.out.println("Login Successful ");
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
        System.out.println("Enter your Email");
        String email= sc.next();
        System.out.println("Enter your Password");
        String password=sc.next();
       // int section = Integer.parseInt(S_section);
        Student student = new Student(name,bitsID,email,password);
        students.add(student);
    return true;
    }

    public boolean logout(){
        String filename=  "../../storagefiles/student.bin";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));

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

    public void bookTicketMenu(){
        System.out.println("Following are the upcoming events in the Auditorium:");

        int i=1;
        for (Event e:Audi.getAudiObj().getEvents()
        ) {
            System.out.println(i+". "+e.toString()); i++;
        }
        System.out.println("\n\nEnter the event number you want to register for ");
        Scanner sc = new Scanner(System.in);
        int eno = Integer.parseInt(sc.next());
        bookTicket(Audi.getAudiObj().getEvents().get(eno-1));

    }

    synchronized  public  boolean bookTicket(Event event){
        //display audi seats
        for (int s = 0; s < event.getBookedSeats().length; s++) {
            for (int r = 0; r < event.getBookedSeats()[0].length; r++) {
                for (int c = 0; c < event.getBookedSeats()[0][0].length; c++) {
                    System.out.println(" ");
                }
            }
        }
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
