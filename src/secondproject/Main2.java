package secondproject;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Main2 {

	public static PDPage blankPage = new PDPage();
	public static float PAGE_MARGIN = 5;
	public static float newPagepositionY = blankPage.getMediaBox().getHeight() - PAGE_MARGIN;
	public static float positionY = newPagepositionY;

	public static void main(String[] args) throws IOException {

		PDDocument document = new PDDocument();

		// adding a new page to the document - the first page
		document.addPage(blankPage);

		// creating the content that will appear on the previously added
		// page
		PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);

		// the begin of the text for the content
		contentStream.beginText();

		// formating the text
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.setLeading(14.5f);

		// setting the coordinates from where the text will be displayed
		// on the page (first one is the column, second is for row)
		contentStream.newLineAtOffset(15, 750);

		// depending on the text formats you can get more or less rows on a
		// page, for my formats I gor 49 rows + the document margins and
		// the spaces between the rows; this goes for the first for
		for (int i = 0; i < 48; i++) {

			// will display a text at the begining of every row in my case
			// it will display the row number
			contentStream.showText(Integer.toString(i));

			// this for will display some text to each column of the page
			// in my case it will display the letter "a"+ one space 64
			// times; depending on the text formats you can get more or
			// less characters
			for (int j = 0; j < 63; j++) {
				contentStream.showText("a" + " ");
			}

			// I wanted to display a "b" at each end of a line so I coud
			// see that the line fits to the page
			contentStream.showText("b");

			// adds another line
			contentStream.newLine();
		}

		// marks the end of the text that will be displayed on the first
		// page
		contentStream.endText();

		// will close the content for the first page
		contentStream.close();

		// end of the first page

		// adding other 3 pages with the same text
		int j = 0; // the counter for the pages that will be added

		while (j < 3) {

			// adding another blank page to the document with different
			// text from the previous one
			document.addPage(blankPage = new PDPage());

			// taking a content for the new page
			contentStream = new PDPageContentStream(document, blankPage);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(15, 720);

			// display a number for every row on the page
			for (int i = 50; i < 98; i++) {
				contentStream.showText(Integer.toString(i));
				contentStream.newLine();
			}

			// closing the text and the content
			contentStream.endText();
			contentStream.close();

			// increasing the counter for the page
			j++;
		}
		System.out.println("Content added");

		// Saving the document
		document.save("PDF/docc1.pdf");
		System.out.println("PDF created");
		document.close();
	}
}
