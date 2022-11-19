package org.example;

import java.util.ArrayList;

public class Ticket {
    private static int accountStartID =1;
    private Event event;
    private ArrayList<Integer> seatNO;
    private Student student;

    public Ticket(Event event, ArrayList<Integer> seatNO, Student student) {
        this.event = event;
        this.seatNO = seatNO;
        this.student = student;
    }

    public static Ticket generateTicket(Event e, ArrayList<Integer> seatNO, Student student){
       return new Ticket(e,seatNO,student);

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Event :" + event.getTitle() +
                "Event Time:" + event.getdatetime() +
                ", seatNO=" + seatNO +
                ", student=" + student +
                '}';
    }

    public String printTicket() {
        return  toString();
    }
}
