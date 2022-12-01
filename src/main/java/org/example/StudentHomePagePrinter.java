package org.example;

import java.util.Scanner;


public class StudentHomePagePrinter  {
    private Student student;


    public StudentHomePagePrinter(Student student) {
       this.student = student;
    }

    public  void  studentHomePage(){
        Scanner sc = new Scanner(System.in);
        char ch='a';

        while(ch!='q'){
            try{
                System.out.println("Hii "+student.getStuName()+"!!");
                System.out.println("\nEnter 1. to see upcoming Event List");
                System.out.println("Enter 2. to book Ticket");
                System.out.println("Enter 3. to download Ticket PDF");
                System.out.println("Enter 4. to cancel booked Tickets");
                System.out.println("Enter 5. to  see the total amount spent on Tickets");
                System.out.println("Enter 6. to  see your registered events");
                System.out.println("Enter 7. to Login to different Account");
                System.out.println("Enter 8. to SignUp to a new Accounts");
                System.out.println("Enter 9. to Logout");
                int no = Integer.parseInt(sc.next());


                switch(no){
                    case 1:
                        student.printEvents();
                        break;
                    case 2:
                        student.bookTicketMenu();
                        break;
                    case 3:
                        if(!student.viewRegisteredEvents()){break;}
                        System.out.println("\nEnter the Event number for which you want to Download your Ticket PDF ");
                        int eno=Integer.parseInt(sc.next());
                        GeneratePDF pdf= new GeneratePDF(student.getBookedTickets().get(eno-1));
                        pdf.generatePdf();
                        System.out.println("\nTicket PDF for "+ student.getBookedTickets().get(eno-1).event.getTitle()+" saved to Downloads folder...");
                        break;
                    case 4:
                        student.cancelTicket();
                        break;
                    case 5:
                        System.out.println("\n\nYour Total amount for Tickets are: " +student.getSWDCharges());
                        break;
                    case 6:
                        student.viewRegisteredEvents();
                        break;
                    case 7:
                        student.login();
                        break;
                    case 8:
                        student.signup();
                        break;
                    case 9:
                        student.logout();
                        break;
                    default:
                        System.out.println("Error!! Invalid Input");
                }
            }catch(NumberFormatException e){
                System.out.print("Error!! Input is not a number...");
            }

            System.out.println("Enter q to quit any other key to continue");
            ch=sc.next().charAt(0);
        }
    }

}
