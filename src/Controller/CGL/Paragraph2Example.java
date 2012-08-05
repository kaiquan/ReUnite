package Controller.CGL;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Paragraph2Example {

    public static void main(String[] args) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("Paragraph2.pdf"));

            document.open();
            Paragraph paragraph1 = new Paragraph("Event Name: ");
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph2 = new Paragraph("Event Time: ");
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            paragraph2.add("Event status : 12:00 pm");
            paragraph2.setAlignment(Element.ALIGN_RIGHT);
            
            Paragraph comb=new Paragraph(); 
            comb.add(paragraph1); 
            comb.add(paragraph2);
            
            document.add(comb);
            

                        
//            Paragraph paragraph2 = new Paragraph();
//
//            paragraph2.setSpacingAfter(25);
//            paragraph2.setSpacingBefore(25);
//            paragraph2.setAlignment(Element.ALIGN_CENTER);
//            paragraph2.setIndentationLeft(50);
//            paragraph2.setIndentationRight(50);
//            
//
//            for(int i=0; i<10; i++){
//                Chunk chunk = new Chunk(
//                    "This is a sentence which is long " + i + ". ");
//                paragraph1.add(chunk);
//                paragraph2.add(chunk);
//            }
//
//            document.add(paragraph1);
//            document.add(paragraph2);
//            	document.add(paragraph1);
//            	document.add(paragraph2);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

