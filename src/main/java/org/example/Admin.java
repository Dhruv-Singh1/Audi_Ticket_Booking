package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin implements BaseUser , Serializable {
    private String username;
    private String password;
    private static Admin acc;
    static final long serialVersionUID = 42L;
    //private Audi audi = Audi.getAudiObj();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Admin(String username, String password) {
            this.username= username;
            this.password=password;
                    
    }

 static {
    
        
        try {
            
            String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/admin.ser";
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));           
            os.writeObject(new Admin("admin","pass"));
            os.flush();
            os.close();
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            acc=(Admin)is.readObject();
          //  System.out.println(acc);
            
           // ArrayList<Student> s = (ArrayList<Student>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e){
             System.out.println(e.toString());
            
        }
        catch(IOException e){
           
                    System.out.println(e.toString());
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    };
 
    public static Admin getAdminObj(){
        return acc;
    }
    
    public  boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = sc.next();
        
            
            if (this.getUsername().equals(username)) {
                System.out.println("Username found in db");
                System.out.println("Enter your Password");
                String password = sc.next();
                if (this.getPassword().equals(password)) {
                    System.out.println("Login Successful ");
                    acc.adminHomePage();
                    return true;
                }
                else {
                    System.out.println("Invalid Password Try Again!!");
                }
            }
            else{
                System.out.println("Invalid Username");
            }

        return false;
    }

public boolean logout(){
        String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/admin.ser";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            
            //writeObject(os);
            os.close();
            filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/events.ser";
            ObjectOutputStream osEvent = new ObjectOutputStream(new FileOutputStream(filename));
            osEvent.writeObject(Audi.getAudiObj().getEvents());
            osEvent.close();
        }
        catch(FileNotFoundException e){
            // System.out.println("");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
         System.out.println("Signing you off..");
     
        return true;

    }


    public void adminHomePage(){
       Audi.getAudiObj();
      Scanner sc = new Scanner(System.in);
      char ch='a';
      while(ch!='q'){
        System.out.println("Hii "+this.getUsername());
        System.out.println("Enter 1. to see upcoming Event List");
        System.out.println("Enter 2. Add Event");
        System.out.println("Enter 3. Edit Events");
        System.out.println("Enter 4. Reserve Seats for an Event ");
        System.out.println("Enter 5. to Login to different Account");
        System.out.println("Enter 6. Track Revenue of a Event");
        System.out.println("Enter 7. to Logout");       
        int no = Integer.parseInt(sc.next());
        switch(no){
            case 1:
               this.printEvents();
                break;
            case 2:
               this.addEvent();
                break;
            case 3:
            //    this.bookTicketMenu();
                break;
            case 4:
                Audi.getAudiObj().getEvents().get(0).printSeats();
                break;
            case 5:
                this.login();
                break;
            case 6:
             
                break;
            case 7:
                this.logout();
                break;
            default:
        }
         System.out.println("Enter q to quit any other key to continue");
         ch=sc.next().charAt(0);
      }
        
    }
    
    
 
    void addEvent() {
        // take details of event input
       Event event = Event.addEvent();
       if(event!=null){
           Audi.getAudiObj().addEventToAudi(event);
            System.out.println("\nEvent Added to Audi Sucessfully");
       }
       else{
            System.out.println("\nSome Error occured while trying to create Event Try Again!! ");
       }

    }
    void editEvent(){
        
        
    }
    void getEventRevenue(){
        
    }

 

}