package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class GUI extends JFrame implements ActionListener {

    JTextField email,status; JPasswordField pass;

    GUI(){

        JLabel title= new JLabel("Audi Ticket Booker");
        JLabel emailLabel= new JLabel("Enter Email");
        JLabel passLabel= new JLabel("Enter Password");

        email = new JTextField();
        pass = new JPasswordField();
        status= new JTextField();
        JButton login= new JButton("LOGIN");

        // JButton b=new JButton(new ImageIcon("D:\\icon.png"));
        title.setBounds(500,10,400,50);
        email.setBounds(100,100,400,50);
        emailLabel.setBounds(100,90,400,10);
        pass.setBounds(100,200,400,50);
        passLabel.setBounds(100,190,400,10);
        login.setBounds(100,400,200,50);
        status.setBounds(100,600,400,50);
        login.addActionListener(this);
        add(title);
        add(email);
        add(emailLabel);
        add(pass);
        add(passLabel);
        add(login);
        add(status);

        setLayout(null);
        setVisible(true);
        setSize(1400,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public void actionPerformed(ActionEvent e) {
        try{
            String emails= email.getText();
            String password= Arrays.toString(pass.getPassword());
            status.setText("Login Sucessfull "+ emails);
        }catch(Exception ex){System.out.println(ex);}

    }
}
public class App  {


    public static void main(String[] args) throws IOException {
     //   GUI gui   = new GUI();
        try {
            int t=0;
            while(t!=3) {
                System.out.println("Enter 1 for User Mode");
                System.out.println("Enter 2 for Admin Mode");
                System.out.println("Enter 3 to Exit\n");
                Scanner sc = new Scanner(System.in);
                t = Integer.parseInt(sc.next());                          
            switch(t){               
                case 1:
                   System.out.println("Enter 1 for Login Mode");
                   System.out.println("Enter 2 for Sign Up Mode");                  
                   t = Integer.parseInt(sc.next());
                   System.out.println("\n\n");
                   if(t==1){
                       Student.getLoginObj().login();
                   }
                   else if(t==2){
                       Student.getLoginObj().signup();
                   }
                   break;
            
                case 2:
                    Admin.getAdminObj().login();
                    break;
                    
                case 3: break;
                default:
                    System.out.println("Invalid choice!!");
            }
           }

        } catch(NumberFormatException e){
            System.out.println("Invalid input please Enter a number!!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
