package setch.service;

import com.itextpdf.layout.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class headerpdf extends PdfPageEventHelper {
	public void onEndPage (PdfWriter writer, Document document) {
		Rectangle rect = writer.getBoxSize("header");//Rectangle contenant le texte et à positionner
		//Police particulière comme example (facultatif)
		FontFactory.registerDirectories();
		 Font fontArial = FontFactory.getFont("Arial",BaseFont.IDENTITY_H,12);
		Font fontBIGrisSmall = new Font(fontArial);
		fontBIGrisSmall.setSize(6);
		fontBIGrisSmall.setColor(150, 150, 150);
		fontBIGrisSmall.setStyle(Font.ITALIC);
		//Ajout du texte 
		String monTexte="Texte en entête";
		Phrase monParagraphe = new Phrase(monTexte, fontBIGrisSmall);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(monParagraphe), rect.getRight(),rect.getTop(), 0);               
		 }
}
