package firstproject;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SimpleTableXML {
	static PDDocument document = new PDDocument();
	static PDPage firstPage = new PDPage(PDRectangle.A4);

	public static void main(String[] args) throws Exception {
		String path = "exemplo.xml";
		
		byte[] encoded = Files.readAllBytes(Paths.get(path));

		String xml = new String(encoded, StandardCharsets.ISO_8859_1);
		
		Document xmldoc = buildXMLDocument(xml.toString());

		
		
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

		NodeList clienteList = xmldoc.getElementsByTagName("Lancamento");
		
		
		
		
		//for (int i = 1; i < rowCount; i++) {
		for (int temp = 1; temp < clienteList.getLength(); temp++) {
				if (temp % 56 == 0) {
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
			
			Node nNode = clienteList.item(temp);
			NodeList filhos = nNode.getChildNodes();
			
			
			
			//for (int j = 1; j <= colCount; j++) {
			for (int j = 0; j < filhos.getLength(); j++) {
				
				Node fio = filhos.item(j);
				
				if (fio.getNodeType() == Node.ELEMENT_NODE && (fio.getNodeName().equals("Data")
						|| ((fio.getNodeName().equals("Lancamento")) && fio.hasChildNodes())
						|| fio.getNodeName().equals("Orig") || fio.getNodeName().equals("Valor")
						|| fio.getNodeName().equals("Saldo")
						|| (false && (fio.getNodeName().equals("HPLoteFI") || fio.getNodeName().equals("CDTransferencia")
								|| fio.getNodeName().equals("DtVl") || fio.getNodeName().equals("DtComp")
								|| fio.getNodeName().equals("TM") || fio.getNodeName().equals("Terminal")
								|| fio.getNodeName().equals("NumCaixa") || fio.getNodeName().equals("Transacao")
								|| fio.getNodeName().equals("Auten") || fio.getNodeName().equals("ID")
								|| fio.getNodeName().equals("Cart"))))) {
					
					String valor = fio.getTextContent().trim();
			

					switch (fio.getNodeName()) {
					case "Data":

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
						contentStream.showText(valor);
						contentStream.endText();
						initX += (cellWidth - 80); // Coloca o ponteiro na próxima célula da direita
						break;
						
					case "Lancamento":
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
						contentStream.showText(valor);
						contentStream.endText();
						initX += cellWidth; // Coloca o ponteiro na próxima célula da direita

						// contentStream.stroke();
						// contentStream.close();
						// contentStream = new PDPageContentStream(document, firstPage,
						// PDPageContentStream.AppendMode.APPEND,false,true);
						break;
					case "Orig":
						contentStream.addRect(initX, initY, cellWidth - 105, -cellHeight);
						contentStream.beginText();
						contentStream.newLineAtOffset(initX + 5, initY - cellHeight + 5);
						contentStream.setFont(PDType1Font.HELVETICA, 8);
						contentStream.showText("9999");
						contentStream.endText();
						break;
					case "Valor":
						float text_width = getTextWidth(PDType1Font.HELVETICA, 8, valor);

						contentStream.addRect(initX, initY, cellWidth, -cellHeight);
						contentStream.beginText();
						//contentStream.newLineAtOffset(initX , initY - cellHeight + 5);
						contentStream.newLineAtOffset((initX+cellWidth)-text_width , initY - cellHeight + 5);
						contentStream.setFont(PDType1Font.HELVETICA, 8);
						contentStream.showText(valor);
						contentStream.endText();
						initX += cellWidth; // Coloca o ponteiro na próxima célula da direita
						break;
					default:
						contentStream.addRect(initX, initY, cellWidth, -cellHeight);
						contentStream.beginText();
						contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
						contentStream.setFont(PDType1Font.HELVETICA, 8);
						contentStream.showText(valor);
						contentStream.endText();
						initX += cellWidth; // Coloca o ponteiro na próxima célula da direita
						initX = 30; // Volta para a margem da esquerda
						initY -= cellHeight; // Pula para a linha debaixo
						break;
					}
				}
				
			}

			
		}

		contentStream.stroke();
		contentStream.close();

		document.save(new File("PDF\\myPDF2xml.pdf"));
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
	
	private static Document buildXMLDocument(String xml) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			dbFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
		} catch (Exception e) {
			System.err.println("DocumentBuilderFactory: could not set parser feature");
		}
		DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		Document xmlDoc = docBuilder.parse(new InputSource(new StringReader(xml)));

		return xmlDoc;
	}
}