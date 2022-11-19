package org.example;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Event implements Comparable<Event> {

    private String organizer;
    private String title;
    private String eventDetails;
    private double ticketPrice;
    private Date datetime; // calendar class
    private Date endtime;
    private boolean [][][] bookedSeats;

    private Audi audi = Audi.getAudiObj();
    private  double netRevenue;
    private int seatno []=new int[audi.getTotalSeats()];
     ArrayList<Student> registeredForEvent;

    // is Booked on date / time ?
    // book method
    private GregorianCalendar c = new GregorianCalendar();
    void get_time(){
        c.getTime();
    }
    Event(){
        this.eventDetails=null;
        this.organizer=null;
        this.registeredForEvent=new ArrayList<>();
        this.title=null;
        this.ticketPrice=0.00;
        this.netRevenue=0.00;
        this.bookedSeats = Audi.getAudiObj().getseatsMatrix();
        for (int i=0;i< seatno.length;i++){
            seatno[i]=i+1;
        }
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public  Date getdatetime() {
        return datetime;
    }

    public  void setdatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean[][][] getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(boolean[][][] bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Audi getAudi() {
        return audi;
    }

    public void setAudi(Audi audi) {
        this.audi = audi;
    }

    public int[] getSeatno() {
        return seatno;
    }

    public void setSeatno(int[] seatno) {
        this.seatno = seatno;
    }

    public ArrayList<Student> getRegisteredForEvent() {
        return registeredForEvent;
    }

    public void setRegisteredForEvent(ArrayList<Student> registeredForEvent) {
        this.registeredForEvent = registeredForEvent;
    }

    public GregorianCalendar getC() {
        return c;
    }

    public void setC(GregorianCalendar c) {
        this.c = c;
    }

    Event(String organizer, String title, String eventDetails, float ticketPrice, Date datetime){
        this.eventDetails=eventDetails;
        this.organizer=organizer;
        this.registeredForEvent=new ArrayList<>();
        this.title=title;
        this.ticketPrice=ticketPrice;
        //or create the object of Date here
        this.datetime=datetime;
        this.bookedSeats = Audi.getAudiObj().getseatsMatrix();
        this.netRevenue=0.00;
    }


    public double getNetRevenue() {
        return netRevenue;
    }

    @Override
    public int compareTo(@NotNull Event event){
        return this.getdatetime().compareTo(event.getdatetime());
    }

    public boolean addEvent(){
        System.out.println("Enter the Title");
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Event date and time in this format dd-MMM-yyyy hh:mm   Eg: 17-Jul-2022 18:35");
            String start;
            String time = "17-Jul-2022 18:35";
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
            Date date = format.parse(time);
            System.out.println(date);
            System.out.println("Enter the ticket price:");
            double price = Double.parseDouble(sc.next());
            System.out.println("Enter the event details: ");
            String details= sc.next();




        } catch (Exception e) { //ParseException for date
            e.printStackTrace();
        }

        return false;
    }


    class RegisteredStudents extends Event {


    //    private static final long serialVersionUID = 1L;
        public void addStudent(Student usr,int seatcount) {

            registeredForEvent.add(usr);
            netRevenue+=ticketPrice* seatcount;


        }

        public void addStudents(ArrayList<Student> usr) {
            registeredForEvent.addAll(usr);
        }



    }





}
