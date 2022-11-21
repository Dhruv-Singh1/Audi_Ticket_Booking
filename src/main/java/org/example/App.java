package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

//import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.FontFactory.*;
import com.itextpdf.text.FontProvider.*;
import com.itextpdf.text.Font.*;
//import com.itextpdf.io.font.FontConstants; 
//import com.itextpdf.text.Anchor;
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chapter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.List;
//import com.itextpdf.text.ListItem;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Section;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.pdf.*;


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

// read from file intialize audi class events & registerations
//        String myTime = "10:30:54";
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        Date date = null;

        try {
            int t=0;
            while(t!=1&&t!=2) {
                System.out.println("Enter 1 for User Mode");
                System.out.println("Enter 2 for Admin Mode\n");
                Scanner sc = new Scanner(System.in);
                t = Integer.parseInt(sc.next());
                final String os = System.getProperty("os.name");
                if (t != 1 && t != 2) {
                    System.out.println("Invalid Input...");
                }
//                if (os.contains("Windows")) {
//                    Runtime.getRuntime().exec("cls");
//                } else {
//                    Runtime.getRuntime().exec("clear");
//                }
            }
            if(t==1){
                   System.out.println("Enter 1 for Login Mode");
                   System.out.println("Enter 2 for Sign Up Mode");
                   Scanner sc = new Scanner(System.in);
                   t = Integer.parseInt(sc.next());
                   System.out.println("\n\n");
                   if(t==1){
                       Student.getLoginObj().login();
                   }
                   else if(t==2){
                       Student.getLoginObj().signup();
                   }
            }
            else{
                    Admin.getAdminObj().login();
            }


        } catch(NumberFormatException e){
            System.out.println("Invalid input please Enter a number!!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
