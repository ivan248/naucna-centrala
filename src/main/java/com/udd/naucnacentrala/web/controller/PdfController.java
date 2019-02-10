package com.udd.naucnacentrala.web.controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Controller
@RequestMapping(value="/api/downloadPdf")
public class PdfController {

	@RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
	public HttpEntity<byte[]> createPdf(
	                 @PathVariable("fileName") String fileName) throws IOException {

		Path pdfPath = Paths.get("src/main/java/com/udd/naucnacentrala/elasticsearch/" + fileName.replace("_", " ") +".pdf");
		
	    byte[] documentBody = Files.readAllBytes(pdfPath);

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileName.replace(" ", "_") + ".pdf");
	    header.setContentLength(documentBody.length);

	    return new HttpEntity<byte[]>(documentBody, header);
	}
}
