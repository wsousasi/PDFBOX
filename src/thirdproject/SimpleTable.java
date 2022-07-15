package thirdproject;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class SimpleTable {
	static PDDocument document = new PDDocument();
	static PDPage firstPage = new PDPage();
	
	public static void main(String[] args) throws IOException {
		
		Tabela table = new Tabela(document, firstPage);
		
		
		
		for (int i = 1; i < table.getRowCount(); i++) {
			if(i == 15) {
				table.getContentStream().close();
				table = new Tabela(document,firstPage = new PDPage());
				//document.addPage(firstPage = new PDPage());
				//contentStream = new PDPageContentStream(document,firstPage);
				
			}
			for (int j = 1; j <= table.getColCount(); j++) {
			//for (int j = 1; j <= colCount; j++) {
				table.getContentStream().addRect(table.getInitX(), table.getInitY(), table.getCellWidth(), -table.getCellHeight());
				//table.getContentStream().addRect(initX, initY, cellWidth, -cellHeight);
				
				table.getContentStream().beginText();
				//table.getContentStream().newLineAtOffset(initX+10, initY-cellHeight+10);
				table.getContentStream().newLineAtOffset(table.getInitX()+10, table.getInitY()-table.getCellHeight()+10);
				table.getContentStream().setFont(PDType1Font.TIMES_ROMAN, 15);
				table.getContentStream().showText("Hello"+i);
				table.getContentStream().endText();
				
				table.setInitX(table.getInitX() + table.getCellHeight());
				//initX += cellWidth;
			}
			table.setInitX(50);
			table.setInitY(table.getInitX() - table.getCellHeight());
			//initX = 50;
			//initY -= cellHeight;
		}


		table.getContentStream().stroke();
		table.getContentStream().close();
		
		document.save(new File("PDF\\myPDF.pdf"));
		document.close();

		System.out.println("Done!");
	}
}