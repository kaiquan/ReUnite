package View.SOM;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.GregorianCalendar;

import Controller.MyCalendar;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class pdf {
	public static void main (String a[]) throws DocumentException, MalformedURLException, IOException{

		
		//writting the pdf
		 Document pdf = new Document (PageSize.A4);
		 PdfWriter.getInstance(pdf, new FileOutputStream("test.pdf"));
		 pdf.open ();
		 //SETTING THE HEADER
		 pdf.addCreator("Lee Kai Quan(114173S)");
		 Image image = Image.getInstance("src\\images\\SOM\\Reunite_Header.png");
		 image.scaleAbsolute(550, 100);
		 pdf.add(image);
		 pdf.add(new Paragraph("  "));
		 pdf.add(new Paragraph("  "));
		 //WRITTING THE CONTENT HERE
		 GregorianCalendar g= new GregorianCalendar();
		 pdf.add(new Paragraph("Ballroom Record"));
		 pdf.add(new Paragraph("Recorded on :"+MyCalendar.formatDate(g)));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("ID                      : "));
		 pdf.add(new Paragraph("Facility Name    : "));
		 pdf.add(new Paragraph("Contact              : "));
		 pdf.add(new Paragraph("Address              : "));
		 pdf.add(new Paragraph("Title                   : "));
		 pdf.add(new Paragraph("Size                    : "));
		 pdf.add(new Paragraph("Price                   : $"));
		 pdf.add(new Paragraph("Description : "));
		 //pdf.add(new Paragraph(getJTextArea_ballroomDescription().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("Entitled Discount : "));
		 pdf.add(new Paragraph("Final Price        : $"));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph("                                                                         End                                               "));
		 pdf.add(new Paragraph());
		 pdf.add(new Paragraph());
		 
		 pdf.close();
	}
}
