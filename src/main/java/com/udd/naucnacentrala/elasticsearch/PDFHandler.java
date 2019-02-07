package com.udd.naucnacentrala.elasticsearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFHandler {

	public static ArrayList<ScientificPaperIndexUnit> getIndexUnit() {
		ScientificPaperIndexUnit retVal = new ScientificPaperIndexUnit();
		ArrayList<ScientificPaperIndexUnit> resultList = new ArrayList<ScientificPaperIndexUnit>();
		try {
			
			File folder = new File("src/main/java/com/udd/naucnacentrala/elasticsearch");
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
			    if (file.isFile() && file.getName().endsWith(".pdf")) {
			        
					PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
					parser.parse();
					String text = getText(parser);
					//text =    "   29. novembar 2009. | 23:54 -> 12:12 | Izvor: B92   \"  + \r\n \"   Beograd -- Prva doza vakcina protiv novog gripa stigla je u Srbiju. Vakcinacija   \"  + \r\n \"   po\u010Dinje tek za dve nedelje, nakon \u0161to vakcina bude testirana.   \"  + \r\n \"   Prvi kontigent od 140.000 vakcina farmaceutske ku\u0107e \"Novartis\" protiv novog gripa   \"  + \r\n \"   stigao je u Srbiju. Uzorci vakcine bi\u0107e predati Agenciji za lekove, saop\u0161tila je   \"  + \r\n \"   \"Jugohemija\", koja je uvezla vakcine. \u010Clan Radne grupe za pra\u0107enje tog virusa   \"  + \r\n \"   Branislav Tidorovi\u0107 objasnio je za B92 da \u0107e vakcine biti distribuirane \u0161irom Srbije   \"  + \r\n \"   tek po\u0161to dobiju pozitivan sertifikat Agencije za lekove.   \"  + \r\n \"   \u201CAgencija za lekove to uradi po proceduri koja ina\u010De odogovara proceduri Evropske   \"  + \r\n \"   agencije za lekove i na osnovu toga taj postupak je ne\u0161to skra\u0107en u odnosu na ono \u0161to   \"  + \r\n \"   bi oni radili da je to potpuno nepoznata, nesertifikovana ili pak, neregistrovana   \"  + \r\n \"   vakcina\u201D, navodi Tiodorovi\u0107.   \"  + \r\n \"   \u201CZato smatramo da \u0107e verovatno biti mogu\u0107e da od 10. do 15. decembra po\u010Dnemo sa   \"  + \r\n \"   vakcinacijom\u201D, o\u010Dekuje on.   \"  + \r\n \"   Iz \"Jugohemijie\u201D \u010Diji radnici su preuzeli vakcine iz fabrike Sijeni, u Italiji i zapo\u010Deli   \"  + \r\n \"   transport prvih doza, ranije su saop\u0161tili da \u0107e ta kompanija u\u010Diniti sve da skrati rokove   \"  + \r\n \"   isporuke.   \"  + \r\n \"   Tako\u0111e \u0107e maksimalno pove\u0107ati koli\u010Dine isporuke doza po tran\u0161ama, kako bi vakcine   \"  + \r\n \"   bile isporu\u010Dene u \u0161to kra\u0107em vremenu.   \"  + \r\n \"   Branislav Tiodorovi\u0107 navodi da se posle prve isporuke o\u010Dekuje da u Srbiju stignu i   \"  + \r\n \"   slede\u0107e koli\u010Dine vakcina, kako bi vakcinacija zapo\u010Dela \u0161to pre.   \"  + \r\n \"   \u201CU me\u0111uvremenu o\u010Dekujemo - barem prema usmenim obe\u0107anjima, mada u ugovoru   \"  + \r\n \"   ka\u017Ee tek 15. decembar - ali da \u0107emo \u010Dak i ranije dobiti jo\u0161 500 hiljada doza i da \u0107emo   \"  + \r\n \"   sa tom koli\u010Dinom od nekih 640 hiljada doza mo\u0107i da za\u0161titimo one prve prioritete\u201C,   \"  + \r\n \"   ka\u017Ee Tiodorovi\u0107.   \"  + \r\n \"   \u201CTo zna\u010Di \u2013 malu decu, hroni\u010Dne bolesnike i trudnice. Kao i da po\u010Dnemo vakcinaciju   \"  + \r\n \"   dela zdravstvenih radnika i javnih slu\u017Ebi\u201C, navodi on.   \"  + \r\n \"   Epidemiolog Predrag Kon objasnio je da \u0107e odluka o tome da li \u0107e se gra\u0111ani   \"  + \r\n \"   vakcinisati ili ne biti, pre svega, na njima, ali je istakao da se njome \u0161tite i da je bez   \"  + \r\n \"   vakcine rizik da dobiju te\u017Ei oblik bolesti stotinu puta ve\u0107i nego ako se vakcini\u0161u.   \"  + \r\n \"   Kada je re\u010D o riziku koji nosi vakcina, on je gotovo potpuno zanemarljiv, jer, kako je   \"  + \r\n \"  rekao Kon, na milion doza mo\u017Ee se dogoditi jedna te\u0161ka ne\u017Eeljena reakcija.   \" ";  
					retVal.setPdfText(text);

					// metadata extraction
					PDDocument pdf = parser.getPDDocument();
					PDDocumentInformation info = pdf.getDocumentInformation();

					String title = "" + info.getTitle();
					retVal.setTitle(title);
					
					String keywords = "" + info.getKeywords();
					retVal.setKeywords(keywords);
					
					String author = "" + info.getAuthor();
					retVal.setAuthor(author);
					
					String d = "" + info.getSubject();
					retVal.setAbstractDescription(d);

					resultList.add(retVal);
					pdf.close();
			    }
			}
			

		} catch (Exception e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return resultList;
	}

	public static String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
	
	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser( new RandomAccessFile(file, "r"));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}

}
