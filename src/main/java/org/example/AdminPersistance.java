package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AdminPersistance implements Persistance {

    public  boolean saveToFile(){
        String filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/admin.ser";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            //writeObject(os);
            os.close();
            filename=  "/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/events.ser";
            ObjectOutputStream osEvent = new ObjectOutputStream(new FileOutputStream(filename));
            osEvent.writeObject(Audi.getAudiObj().getEvents());
            osEvent.close();
            return true;
        }
        catch(FileNotFoundException e){
            // System.out.println("");
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
