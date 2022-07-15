package firstproject;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class teste {

	public static void main(String[] args) throws IOException {

		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDPageContentStream content = new PDPageContentStream(document, page);
		PDFont font = PDType1Font.HELVETICA_BOLD;

		int cursorX = 70;
		int cursorY = 500;

		int outralinha = 0;
		
		for (int i = 0; i < 5; i++) {
			// draw rectangle
			content.setNonStrokingColor(200, 200, 200); // gray background
			content.fillRect(cursorX, cursorY+outralinha, 100, 40);

			// draw text
			content.setNonStrokingColor(0, 0, 0); // black text
			content.beginText();
			content.setFont(font, 12);
			content.moveTextPositionByAmount(cursorX, cursorY+outralinha);
			content.drawString("Test Data");
			content.endText();
			outralinha = outralinha + 50;
		}
		content.close();
		document.save("C:\\Users\\weslley.guedes\\Documents\\eclipse-workspace\\PDFBox\\PDF\\testando.pdf");
		document.close();

	}

}
