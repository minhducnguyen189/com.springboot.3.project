package com.springboot.project.controller;

import com.springboot.project.service.PdfGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class PdfGeneratorController {

  private final PdfGeneratorService pdfGeneratorService;

  @Autowired
  public PdfGeneratorController(PdfGeneratorService pdfGeneratorService) {
    this.pdfGeneratorService = pdfGeneratorService;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @PostMapping("/generatePdfFile")
  public void generatePdfFile(
      HttpServletResponse response, @RequestParam("contentToGenerate") String contentToGenerate)
      throws IOException {
    ByteArrayInputStream byteArrayInputStream =
        this.pdfGeneratorService.convertHtmlToPdf(contentToGenerate);
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=file.pdf");
    IOUtils.copy(byteArrayInputStream, response.getOutputStream());
  }
}
