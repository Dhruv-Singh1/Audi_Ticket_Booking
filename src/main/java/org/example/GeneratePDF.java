package org.example;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class GeneratePDF {
    private Ticket ticket;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public GeneratePDF(Ticket ticket) {
        this.ticket=ticket;
    }

    public boolean generatePdf(){
        try {
            Document document = new Document();
            String home = System.getProperty("user.home");
            File path = new File(home+"/Downloads/"  +ticket.event.getTitle()+"_ticket.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font boldsp = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font bold2 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font base = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.ITALIC);

            Paragraph para0 = new Paragraph("   BIRLA INSTITUE OF TECHNOLOGY AND SCIENCE,PILANI  (Pilani Campus)",boldsp);
            para0.setAlignment(Element.ALIGN_CENTER);
            para0.setSpacingBefore(20);

            Paragraph para1 = new Paragraph(ticket.event.getTitle(),bold2);
            para1.setSpacingBefore(50);
            para1.setSpacingAfter(50);
            para1.setAlignment(Element.ALIGN_CENTER);

            Paragraph para2 = new Paragraph("Date & Time:",bold);
            para2.add(new Chunk("         "+ticket.event.getstartTime(),base));
            para2.setSpacingAfter(25);

            Paragraph para3 = new Paragraph("Venue Details:",bold);
            para3.add(new Chunk("         Main Audi",base));
            para3.setSpacingAfter(25);

            Paragraph para4 = new Paragraph("Student Name:",bold);
            para4.add(new Chunk("         "+ticket.student.getStuName(),base));
            para4.setSpacingAfter(25);

            Paragraph para5 = new Paragraph("Seat Number:",bold);
            para5.add(new Chunk("         "+ticket.seatArrayToString(ticket.premiumseatNO)+"  "+ticket.seatArrayToString(ticket.normalseatNO) ,base));
            para5.setSpacingAfter(25);

            Paragraph para6 = new Paragraph("Total Amount:",bold);
            para6.add(new Chunk("         "+  df.format(ticket.event.getTicketPriceNormal()* ticket.normalseatNO.size()+ ticket.event.getTicketPricePremium()*ticket.premiumseatNO.size())   ,base));
            Image img = Image.getInstance("/Users/dhruvsingh/IdeaProjects/Audi_Ticket_Booking/resourcs/bits-logo.png");
            img.setSpacingBefore(20);
            img.setAlignment(Image.TEXTWRAP);
            img.setSpacingAfter(0);
            img.scaleToFit(80f, 80f);
            document.add(img);
            document.add(para0);
            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(para4);
            document.add(para5);
            document.add(para6);
            document.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (DocumentException e) {
            e .printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
