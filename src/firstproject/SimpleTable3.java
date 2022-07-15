package firstproject;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class SimpleTable3 {
	static PDDocument document = new PDDocument();
	static PDPage firstPage = new PDPage(PDRectangle.A4);
	
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
		
		int initX = 30;
		int initY = pageHeight - 120;
		
		int cellHeight = 12;
		int cellWidth = 100;

		int colCount = 5;
		int rowCount = 100;
	
		
		
		for (int i = 1; i < rowCount; i++) {
			
			
			if(i % 55 == 0) {
				contentStream.stroke();
				contentStream.close();
				contentStream = new PDPageContentStream(document, firstPage = new PDPage(PDRectangle.A4));
				document.addPage(firstPage);
				
				initX = 30;
				initY = pageHeight - 150;
				
			}
			for (int j = 1; j <= colCount; j++) {
				
				if(j==1) {
				contentStream.addRect(initX, initY, cellWidth-50, -cellHeight);
				contentStream.beginText();
				contentStream.newLineAtOffset(initX+10, initY-cellHeight+5);
				contentStream.setFont(PDType1Font.HELVETICA, 8);				
				contentStream.showText("Data"+i+"  "+ j);
				contentStream.endText();
				initX += (cellWidth-50); //Coloca o ponteiro na próxima célula da direita
				}else if(j==2) {
					contentStream.addRect(initX, initY, cellWidth, -cellHeight);
					contentStream.beginText();
					contentStream.newLineAtOffset(initX+10, initY-cellHeight+5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.showText("SALDO INICIAL"+i+"   "+ j);
					contentStream.endText();
					initX += cellWidth; //Coloca o ponteiro na próxima célula da direita
				}else if(j==3) {
					contentStream.addRect(initX, initY, cellWidth-70, -cellHeight);
					contentStream.beginText();
					contentStream.newLineAtOffset(initX+5, initY-cellHeight+5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.showText("9999");
					contentStream.endText();
					initX += (cellWidth-70); //Coloca o ponteiro na próxima célula da direita
				}else {
					contentStream.addRect(initX, initY, cellWidth, -cellHeight);
					contentStream.beginText();
					contentStream.newLineAtOffset(initX+10, initY-cellHeight+5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.showText("Hello"+i+"   "+ j);
					contentStream.endText();
					initX += cellWidth; //Coloca o ponteiro na próxima célula da direita
				}
				
				
				//initX += cellWidth; //Coloca o ponteiro na próxima célula da direita
			}

			initX = 30;			//Volta para a margem da esquerda
			initY -= cellHeight; //Pula para a linha debaixo
		}
		

		contentStream.stroke();
		contentStream.close();
		
		document.save(new File("PDF\\myPDF.pdf"));
		document.close();

		System.out.println("Done!");
	}
}