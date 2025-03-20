package com.springboot.project.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.LayerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.util.Matrix;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.awt.geom.AffineTransform;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfGeneratorService {

  public ByteArrayInputStream convertHtmlToPdf(String htmlContent) {
    List<byte[]> pdfByteArrays = this.generatePDFBytesList(htmlContent, 6);
    return this.mergePDFs(pdfByteArrays, 2, 3);
  }

  public ByteArrayInputStream mergePDFs(List<byte[]> pdfBytesList, int columns, int rows) {
    try (PDDocument mainDocument = new PDDocument()) {
      for (byte[] pdfBytes : pdfBytesList) {
        try (PDDocument doc = Loader.loadPDF(pdfBytes)) {
          for (PDPage page : doc.getPages()) {
            PDPage importedPage = mainDocument.importPage(page);
            mainDocument.addPage(importedPage);
          }
        }
      }

      int totalPageCount = mainDocument.getNumberOfPages();

      int pageWidth = (int) (PDRectangle.A4.getWidth() / columns);
      int pageHeight = (int) (PDRectangle.A4.getHeight() / rows);

      try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
        PDDocument resultDocument = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        resultDocument.addPage(firstPage);
        PDPageContentStream contentStream =
            new PDPageContentStream(
                resultDocument, firstPage, PDPageContentStream.AppendMode.APPEND, true, false);
        for (int i = 0; i < totalPageCount; i++) {
          PDPage page = mainDocument.getPage(i);
          int x = i % columns * pageWidth;
          int y = (rows - 1 - i / columns) * pageHeight;
          Matrix matrix = Matrix.getTranslateInstance(x, y);
          matrix.concatenate(
              Matrix.getScaleInstance(
                  (float) pageWidth / page.getCropBox().getWidth(),
                  (float) pageHeight / page.getCropBox().getHeight()));

          LayerUtility layerUtility = new LayerUtility(mainDocument);
          PDFormXObject pdFormXObject = layerUtility.importPageAsForm(mainDocument, i);

          contentStream.saveGraphicsState();
          contentStream.transform(matrix);
          contentStream.drawForm(pdFormXObject);
          contentStream.restoreGraphicsState();
        }
        contentStream.close();
        resultDocument.save(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<byte[]> generatePDFBytesList(String htmlContent, int numberOfPDFs) {
    List<byte[]> pdfBytesList = new ArrayList<>();
    for (int i = 0; i < numberOfPDFs; i++) {
      Document document = Jsoup.parse(htmlContent, "UTF-8");
      document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
      String documentHtml = document.html();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocumentFromString(documentHtml);
      renderer.layout();
      renderer.createPDF(outputStream, false);
      renderer.finishPDF();
      pdfBytesList.add(outputStream.toByteArray());
    }
    return pdfBytesList;
  }
}
