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

        try {
        PDDocument templatePDF = Loader.loadPDF(outputStream.toByteArray());
        PDDocument mainDocument = new PDDocument();

            PDPage samplePage = new PDPage(PDRectangle.A4);
            mainDocument.addPage(samplePage);

            PDPageContentStream contentStream = new PDPageContentStream(mainDocument,
                    samplePage, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            contentStream.endText();
            contentStream.close();

            PDPageTree destinationPages = mainDocument.getDocumentCatalog().getPages();

            LayerUtility layerUtility = new LayerUtility(mainDocument);

            PDFormXObject firstForm = layerUtility.importPageAsForm(templatePDF, 0);

            AffineTransform affineTransform = new AffineTransform();

            PDPage destPage = destinationPages.get(0);
            destPage.getMediaBox().transform(Matrix.getScaleInstance(0.5f, 0.5f));



            layerUtility.wrapInSaveRestore(destPage);
            layerUtility.appendFormAsLayer(destPage, firstForm, affineTransform, "external page1");
            layerUtility.appendFormAsLayer(destPage, firstForm, affineTransform, "external page2");
            layerUtility.appendFormAsLayer(destPage, firstForm, affineTransform, "external page3");
            layerUtility.appendFormAsLayer(destPage, firstForm, affineTransform, "external page4");


            mainDocument.save("asdasdsad.pdf");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
