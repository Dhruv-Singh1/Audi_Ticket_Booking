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
                    AdminHomePagePrinter  adminHomePagePrinter = new AdminHomePagePrinter(acc);
                    adminHomePagePrinter.adminHomePage();
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

         System.out.println("Signing you off..");
        return true;
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
        System.out.println("");
        System.out.println("Following are the Events in the Auditorium:");
        this.printEvents();
        System.out.print("\n\nEnter the event number you want to get revenue for :  ");
        Scanner sc = new Scanner(System.in);
        int eno=Integer.parseInt(sc.nextLine());
        System.out.print(eno);
        Event event= Audi.getAudiObj().getEvents().get(eno-1);
        System.out.printf("\n\nTotal revenue till now for %s is:  %,.3f ",event.getTitle(),event.getNetRevenue());
        System.out.print(" Rs");
    }

    private Event eventchoiceHelper(){
        System.out.println("");
        System.out.println("Following are the Events in the Auditorium:");
        this.printEvents();
        System.out.print("\n\nEnter the event number you want to edit the details :  ");
        Scanner sc = new Scanner(System.in);
        int eno=Integer.parseInt(sc.nextLine());
        System.out.print(eno);
        return Audi.getAudiObj().getEvents().get(eno-1);
    }

}