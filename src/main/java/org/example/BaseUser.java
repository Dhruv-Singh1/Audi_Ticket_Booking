package org.example;

public interface BaseUser {

    //createAccount
     public  boolean login();
    // public boolean11

     default public void printEvents(){
       if(Audi.getAudiObj().getEvents()==null){
           System.out.println("Sorry!! No upcoming events yet...");
       }
         
         int i=1;
        for(Event e: Audi.getAudiObj().getEvents()){
            System.out.println(i++ +". "+e);
        }
    }
}
