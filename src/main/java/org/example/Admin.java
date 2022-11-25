package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
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
        System.out.println("Enter 5. to update admin username password");
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
                editEvent();     
                break;
            case 4:
                Audi.getAudiObj().getEvents().get(0).printSeats();
                
                break;
            case 5:
                System.out.println("Enter the New Username");
                String usr= sc.nextLine();
                System.out.println("Enter the New password");
                String pass= sc.nextLine();
                this.setUsername(usr);
                this.setPassword(pass);
                break;
            case 6:
                this.getEventRevenue();
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
    
    
 
    public void addEvent() {
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
    
    public void editEvent(){
        Event event= eventchoiceHelper();
        event.editEvent();
    }
   public void getEventRevenue(){     
        Event event= eventchoiceHelper();
        System.out.printf("\n\nTotal revenue till now for %s is:  %,.3f ",event.getTitle(),event.getNetRevenue());
        System.out.print(" Rs");
    }

    private Event eventchoiceHelper(){
        System.out.println("");
        System.out.println("Following are the Events in the Auditorium:");
        this.printEvents();
        System.out.print("\n\nEnter the event number you want to get the revenue for:  ");
        Scanner sc = new Scanner(System.in);
        int eno=Integer.parseInt(sc.next());
        System.out.print(eno);
        return Audi.getAudiObj().getEvents().get(eno-1);
    
    }
 

}