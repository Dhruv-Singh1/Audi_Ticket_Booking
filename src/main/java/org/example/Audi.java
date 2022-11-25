package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Audi implements Serializable{
    private static Audi single_instance=null ;
    private int seatrows;
    private int seatcolumns;
    private static boolean [][][] seatsMatrix;
    private static int totalSeats;
    private ArrayList<Event> events;
    private Audi(){
        seatrows=10;
        seatcolumns=10;
        seatsMatrix= new boolean[2][seatrows][seatcolumns];
        for (int i = 0; i < 2; i++) {
             this.seatsMatrix[i] = new boolean[seatrows][seatcolumns];
             for (int j = 0; j < seatrows; j++) {
                      this.seatsMatrix[i][j] = new boolean[seatcolumns];
            }
        }
        totalSeats=2*seatcolumns*seatrows;
    }
       
    {
        String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/events.ser";
        try {
            events= new ArrayList<Event>();
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            setEvents((ArrayList<Event>)is.readObject());
            is.close();
        }
        catch(FileNotFoundException e){
             System.out.println(e);            
        }
        catch(IOException e){
            System.out.println(e);            
                   
        }
        catch(ClassNotFoundException e){
             System.out.println(e);            
        }
        catch(Exception e){
              System.out.println(e);  
        }  
    };
    
    
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
    
    private void  setEvents(ArrayList<Event> events) {
        this.events=events;
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
