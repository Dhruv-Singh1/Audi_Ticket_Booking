package org.example;

public interface Bookable extends BaseUser{
   public boolean bookTicket(Event event);
   public boolean cancelTicket();

}
