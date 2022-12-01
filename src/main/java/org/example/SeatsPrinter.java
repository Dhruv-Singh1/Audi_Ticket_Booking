package org.example;

public class SeatsPrinter {
    public static void printSeats(boolean [][][] bookedSeats){
        int i=1; char c='N';
        for (boolean[][] flr : bookedSeats) {
            for (boolean[] row: flr) {
                for(boolean seat: row) {

                    String txt= (seat)?"BookD":"Avail";
                    if(i%Audi.getAudiObj().getSeatrows()-1 >=Audi.getAudiObj().getSeatcolumns()/3&&
                            i%Audi.getAudiObj().getSeatrows()-1 <= 2*Audi.getAudiObj().getSeatcolumns()/3){
                        c='P';
                        System.out.printf(" \033[0;1m"+c + "%03d %-6s ",i++,txt);
                    }
                    else{
                        c='N';
                        System.out.printf(" "+c + "%03d %-6s ",i++,txt);
                    }
                }System.out.println();
            } System.out.println("\n\n\n");
        }   System.out.println("Seat Number starting 'P' are Premium seats and 'N' are Normal seats...");
    }
}
