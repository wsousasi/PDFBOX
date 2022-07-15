package secondproject;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Main {

	public static void main(String[] args) throws IOException {
	
		PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        
        
        
        PDFont font = PDType1Font.TIMES_ROMAN;
        PDPageContentStream contentStream =  new PDPageContentStream(document, page);
        float scale = 1f;
        
        String[][] vetor = {{"Agua","Banana"}};
        
        drawTable(page, contentStream, 500, 70, vetor, 500);
        
        contentStream.close();
        document.save("PDF//Test_pdf.pdf");
        document.close();
        

	}

	public static void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin,
			String[][] content, int a) throws IOException {
		PDRectangle rect = page.getMediaBox();
        float positionY = rect.getWidth();
        
		
		final int rows = a;
		// content.length;
		final int cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = page.getMediaBox().getWidth() - margin - margin;
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth / (float) cols;
		final float cellMargin = 5f;

		// draw the rows
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.moveTo(margin, nexty);
			contentStream.lineTo(margin + tableWidth, nexty);
			contentStream.stroke();
			nexty -= rowHeight;
		}

		// draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.moveTo(nextx, y);
			contentStream.lineTo(nextx, y - tableHeight);
			contentStream.stroke();
			nextx += colWidth;
		}

		// now add the text
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		float textx = margin + cellMargin;
		float texty = y - 15;
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				String text = content[i][j];
				contentStream.beginText();
				contentStream.newLineAtOffset(textx, texty);
				contentStream.showText(text);
				contentStream.endText();
				
				float newPagepositionY = page.getMediaBox().getHeight() - margin;
				positionY = newPagepositionY;
				PDPage currentPage = new PDPage();
				
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + cellMargin;
		}
	}
	public boolean isEndOfPage(Row row) 
	{
	    float currentY = this.positionY ;
	    boolean isEndOfPage = currentY  <= (PAGE_MARGIN + 10);

	    return isEndOfPage;
	}
}
