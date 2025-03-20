package com.springboot.project.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

  public ByteArrayInputStream convertHtmlToPdf(String htmlContent) {
    Document document = Jsoup.parse(htmlContent, "UTF-8");
    document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
    String documentHtml = document.html();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(documentHtml);
    renderer.layout();
    renderer.createPDF(outputStream, false);
    renderer.finishPDF();
    return new ByteArrayInputStream(outputStream.toByteArray());
  }
}
