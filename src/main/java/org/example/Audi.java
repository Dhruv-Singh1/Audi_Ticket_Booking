package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Audi implements Serializable{
    //seats
    private static Audi single_instance=null ;
    private int seatrows;
    private int seatcolumns;
    private static boolean [][][] seatsMatrix;
    private static int totalSeats;
    private ArrayList<Event> events;
    private Audi(){
        seatrows=50;
        seatcolumns=100;
        seatsMatrix= new boolean[2][seatrows][seatcolumns];
        totalSeats=2*seatcolumns*seatrows;
    }

    public static Audi getAudiObj(){
        if(single_instance==null){
            single_instance= new Audi();

        }
        return single_instance;
    }
    public boolean addEventToAudi(Event event){
        events.add(event);
        return true;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public boolean[][][] getseatsMatrix(){
        boolean [][][] copyseatsMatrix= seatsMatrix.clone();
        return copyseatsMatrix;
    }

    public int getSeatrows() {
        return seatrows;
    }

    public void setSeatrows(int seatrows) {
        this.seatrows = seatrows;
    }

    public int getSeatcolumns() {
        return seatcolumns;
    }

    public void setSeatcolumns(int seatcolumns) {
        this.seatcolumns = seatcolumns;
    }

    public static int getTotalSeats() {
        return totalSeats;
    }

    public static void setTotalSeats(int totalSeats) {
        Audi.totalSeats = totalSeats;
    }


}
