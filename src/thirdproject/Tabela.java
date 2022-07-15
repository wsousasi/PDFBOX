package thirdproject;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class Tabela {
	
	int pageHeight = 0;
	int pageWidth = 0;
	int initX = 50;
	int initY = pageHeight - 50;
	int cellHeight = 20;
	int cellWidth = 100;
	int colCount = 5;
	int rowCount = 30;
	PDPageContentStream contentStream;
	
	Tabela(PDDocument document, PDPage firstPage) throws IOException{
		document.addPage(firstPage);
		this.contentStream = new PDPageContentStream(document, firstPage);
		this.pageHeight = (int) firstPage.getTrimBox().getHeight();
		this.pageWidth = (int) firstPage.getTrimBox().getWidth();
		this.initX = 50;
		this.initY = pageHeight - 50;
		this.cellHeight = 20;
		this.cellWidth = 100;
		this.colCount = 5;
		this.rowCount = 30;
	}

	public int getPageHeight() {
		return pageHeight;
	}

	public void setPageHeight(int pageHeight) {
		this.pageHeight = pageHeight;
	}

	public int getPageWidth() {
		return pageWidth;
	}

	public void setPageWidth(int pageWidth) {
		this.pageWidth = pageWidth;
	}

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitY(int initY) {
		this.initY = initY;
	}

	public int getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
	}

	public int getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public PDPageContentStream getContentStream() {
		return contentStream;
	}

	public void setContentStream(PDPageContentStream contentStream) {
		this.contentStream = contentStream;
	}
	
	
	


}
