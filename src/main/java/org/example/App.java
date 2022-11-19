package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


import com.itextpdf.text.Document;
import com.itextpdf.*;


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
        GUI gui   = new GUI();
        String dest = "random";
        PdfWriter writer = new PdfWriter(dest);


// read from file intialize audi class events & registerations
//        String myTime = "10:30:54";
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        Date date = null;

        try {
            // date = sdf.parse(myTime);
            // Java program to read character using Scanner

            String time = "17-Jul-2022 18:35";
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
            Date date = format.parse(time);
            System.out.println(date);
            System.out.println("Enter Password:\t");
            Scanner sc = new Scanner(System.in);
           String path ="/Users/dhruvsingh/Downloads";

          //  PdfWriter writer = new PdfWriter(path);


        } catch (Exception e) { //ParseException for date
            e.printStackTrace();
        }
        //  String formattedTime = sdf.format(date);

        // System.out.println(formattedTime);

    }
}