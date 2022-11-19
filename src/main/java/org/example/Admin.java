package org.example;

import java.util.Scanner;

public class Admin implements BaseUser {
    private String username;
    private String password;
    private Audi audi = Audi.getAudiObj();

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

     Admin(String email, String password) {

    }


    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username");
        String email = sc.next();
        System.out.println("Enter your Password");
        String password = sc.next();

            if (this.getUsername() == username) {
                System.out.println("Student found in database");

                if (this.getPassword() == password) {
                    System.out.println("Login Successful ");
                    return true;
                }
                else {
                    System.out.println("Invalid Password Try Again!!");
                }
            }

        return false;
    }


    void getEvents(){


    }
    void addEvent() {
        // take details of event input
        Event event = new Event();

    }
    void editEvent(){

    }
    void getEventRevenue(){

    }


}
