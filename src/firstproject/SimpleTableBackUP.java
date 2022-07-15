package firstproject;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class SimpleTableBackUP {
	static PDDocument document = new PDDocument();
	static PDPage firstPage = new PDPage();
	
	public static void main(String[] args) throws IOException {
		
		document.addPage(firstPage);
		

		int pageHeight = (int) firstPage.getTrimBox().getHeight();
		int pageWidth = (int) firstPage.getTrimBox().getWidth();
		
		File imagefile = new File("..//Boxable//imagens//barra_esq.jpg");
		PDImageXObject pdimage = PDImageXObject.createFromFile("..//Boxable//imagens//barra_esq.jpg", document);

		
		PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
		
		contentStream.setStrokingColor(Color.DARK_GRAY);
		contentStream.setLineWidth(1);
		contentStream.drawImage(pdimage, 15, pageHeight - 65, 550, 36);
		
		int initX = 50;
		int initY = pageHeight - 50;
		
		int cellHeight = 20;
		int cellWidth = 100;

		int colCount = 5;
		int rowCount = 30;
	
		
		
		for (int i = 1; i < rowCount; i++) {
			if(i == 15) {
				contentStream.close();
				contentStream = new PDPageContentStream(document, firstPage = new PDPage());
				document.addPage(firstPage);
				
			}
			for (int j = 1; j <= colCount; j++) {
				contentStream.addRect(initX, initY, cellWidth, -cellHeight);
				
				contentStream.beginText();
				contentStream.newLineAtOffset(initX+10, initY-cellHeight+10);
				contentStream.setFont(PDType1Font.TIMES_ROMAN, 15);
				contentStream.showText("Hello"+i);
				contentStream.endText();
				
				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}
			
			

		

		contentStream.stroke();
		contentStream.close();
		
		document.save(new File("PDF\\myPDF.pdf"));
		document.close();

		System.out.println("Done!");
	}
}