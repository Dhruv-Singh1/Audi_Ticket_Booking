package org.example;

import java.util.Scanner;

public class AdminHomePagePrinter {
    private Admin admin;

    public AdminHomePagePrinter(Admin admin) {
        this.admin = admin;
    }

    public void adminHomePage(){
        Audi.getAudiObj();
        Scanner sc = new Scanner(System.in);
        char ch='a';
        while(ch!='q'){
            System.out.println("Hii "+admin.getUsername());
            System.out.println("Enter 1. to see upcoming Event List");
            System.out.println("Enter 2. Add Event");
            System.out.println("Enter 3. Edit Events");
            System.out.println("Enter 4. Track Revenue of a Event");
            System.out.println("Enter 5. to update admin username password");
            System.out.println("Enter 6. to Logout");
            int no = Integer.parseInt(sc.next());
            switch(no){
                case 1:
                    admin.printEvents();
                    break;
                case 2:
                    admin.addEvent();
                    break;
                case 3:
                    admin.editEvent();
                    break;
                case 4:
                    admin.getEventRevenue();
                    break;
                case 5:
                    System.out.println("Enter the New Username");
                    String usr= sc.nextLine();
                    System.out.println("Enter the New password");
                    String pass= sc.nextLine();
                    admin.setUsername(usr);
                    admin.setPassword(pass);
                    break;

                case 6:
                    admin.logout();
                    break;
                default:
            }
            System.out.println("Enter q to quit any other key to continue");
            ch=sc.next().charAt(0);
        }

    }
}
