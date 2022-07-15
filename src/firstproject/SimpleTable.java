package firstproject;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.CIDFontMapping;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.bouncycastle.oer.its.ContributedExtensionBlock;

public class SimpleTable {
	static PDDocument document = new PDDocument();
	static PDPage firstPage = new PDPage(PDRectangle.A4);

	public static void main(String[] args) throws IOException {

		document.addPage(firstPage);

		int pageHeight = (int) firstPage.getTrimBox().getHeight();
		int pageWidth = (int) firstPage.getTrimBox().getWidth();

		File imagefile = new File("..//Boxable//imagens//barra_esq.jpg");
		PDImageXObject pdimage = PDImageXObject.createFromFile("..//Boxable//imagens//barra_esq.jpg", document);
		int cursorX = 70;
		int cursorY = 500;

		int outralinha = 0;
		PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);

		contentStream.setStrokingColor(Color.DARK_GRAY);
		contentStream.setLineWidth(1);
		contentStream.drawImage(pdimage, 15, pageHeight - 65, 550, 36);

		int initX = 30;
		int initY = pageHeight - 120;

		int cellHeight = 12;
		int cellWidth = 140;

		int colCount = 5;
		int rowCount = 150;
		PDPageContentStream contentStream1 = null;

		for (int i = 1; i < rowCount; i++) {

			
			if (i % 56 == 0) {
				contentStream.stroke();
				contentStream.close();
				contentStream = new PDPageContentStream(document, firstPage = new PDPage(PDRectangle.A4));
				document.addPage(firstPage);
				/*
				setHeader("Data",contentStream, initX, pageHeight, cellHeight);
				setHeader("Histórico de Lançamentos",contentStream, initX+cellWidth-50, pageHeight, cellHeight);
				setHeader("Orig",contentStream, initX+cellWidth-50, pageHeight, cellHeight);
				*/
				
				
				initX = 30;
				initY = pageHeight - 120;

			}
			for (int j = 1; j <= colCount; j++) {

				if (j == 1) {

					// contentStream.stroke();
					// contentStream.close();
					// contentStream = new PDPageContentStream(document, firstPage,
					// PDPageContentStream.AppendMode.APPEND,
					// false);
					// if (i % 2 == 0)
					// contentStream.setNonStrokingColor(200, 200, 200); // gray background
					// else
					// contentStream.setNonStrokingColor(255, 255, 255); // gray background
					contentStream.addRect(initX, initY, cellWidth - 80, -cellHeight);
					// contentStream.fill();

					contentStream.beginText();
					contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.setNonStrokingColor(Color.BLACK);
					contentStream.showText("Data" + i + "  " + j);
					contentStream.endText();
					initX += (cellWidth - 80); // Coloca o ponteiro na próxima célula da direita
				} else if (j == 2) {

					contentStream.stroke();
					contentStream.close();
					contentStream = new PDPageContentStream(document, firstPage, PDPageContentStream.AppendMode.APPEND,
							false);
					// if (i % 2 == 0)
					// contentStream.setNonStrokingColor(200, 200, 200); // gray background
					// else
					// contentStream.setNonStrokingColor(255, 255, 255); // gray background
					contentStream.addRect(initX, initY, cellWidth, -cellHeight);
					// contentStream.fill();
					contentStream.beginText();
					contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.setNonStrokingColor(Color.RED);
					contentStream.showText("SALDO INICIAL" + i + "   " + j);
					contentStream.endText();
					initX += cellWidth; // Coloca o ponteiro na próxima célula da direita

					// contentStream.stroke();
					// contentStream.close();
					// contentStream = new PDPageContentStream(document, firstPage,
					// PDPageContentStream.AppendMode.APPEND,false,true);

				} else if (j == 3) {
					contentStream.addRect(initX, initY, cellWidth - 105, -cellHeight);
					contentStream.beginText();
					contentStream.newLineAtOffset(initX + 5, initY - cellHeight + 5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.showText("9999");
					contentStream.endText();
					initX += (cellWidth - 105); // Coloca o ponteiro na próxima célula da direita
				} else if (j == 4) {
					String value = "Hello" + i + "   " + j;
					if(i==3) {
						value = "850,50-";
					}
					if(i==4) {
						value = "850,50";
					}
					
					float text_width = getTextWidth(PDType1Font.HELVETICA, 8, value);

					contentStream.addRect(initX, initY, cellWidth, -cellHeight);
					contentStream.beginText();
					//contentStream.newLineAtOffset(initX , initY - cellHeight + 5);
					contentStream.newLineAtOffset((initX+cellWidth)-text_width , initY - cellHeight + 5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.showText(value);
					contentStream.endText();
					initX += cellWidth; // Coloca o ponteiro na próxima célula da direita
				} else {

					contentStream.addRect(initX, initY, cellWidth, -cellHeight);
					contentStream.beginText();
					contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
					contentStream.setFont(PDType1Font.HELVETICA, 8);
					contentStream.showText("Hello" + i + "   " + j);
					contentStream.endText();
					initX += cellWidth; // Coloca o ponteiro na próxima célula da direita
				}

				// initX += cellWidth; //Coloca o ponteiro na próxima célula da direita
			}

			initX = 30; // Volta para a margem da esquerda
			initY -= cellHeight; // Pula para a linha debaixo
		}

		contentStream.stroke();
		contentStream.close();

		document.save(new File("PDF\\myPDF.pdf"));
		document.close();

		System.out.println("Done!");
	}

	private static float getTextWidth(PDType1Font font, int fontSize, String text) throws IOException {
		return (font.getStringWidth(text) / 1000.0f) * fontSize;
	}
	
	private static void setHeader(String value,PDPageContentStream contentStream, int initX, int pageHeight, int cellHeight) throws IOException {
		
		contentStream.beginText();
		contentStream.newLineAtOffset(initX + 10, pageHeight-120+cellHeight);
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.showText(value);
		contentStream.endText();
	}
	
	private static void setHeaders(String[] value) {
		
		
		
	}
}