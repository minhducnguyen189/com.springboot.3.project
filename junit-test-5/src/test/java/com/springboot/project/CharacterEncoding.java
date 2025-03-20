package com.springboot.project;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class CharacterEncoding {

  @Test
  void testLength() {
    String text = "“Message”";
    byte[] bytes = text.getBytes();
    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
    System.out.println("Text of UTF_8: " + utf8EncodedString);
    System.out.println("Text length of UTF-8: " + utf8EncodedString.length());
    System.out.println(
        "Bytes of “ in UTF-8: " + Arrays.toString("“".getBytes(StandardCharsets.UTF_8)));
    System.out.println(
        "Bytes of ” in UTF-8: " + Arrays.toString("”".getBytes(StandardCharsets.UTF_8)));
    System.out.println("----------------------------------------------------------");
    String asciiEncodedString = new String(bytes, StandardCharsets.US_ASCII);
    System.out.println("Text of US_ASCII: " + asciiEncodedString);
    System.out.println("Text length of US_ASCII: " + asciiEncodedString.length());
    System.out.println(
        "Bytes of “ in US_ASCII: " + Arrays.toString("“".getBytes(StandardCharsets.US_ASCII)));
    System.out.println(
        "Bytes of ” in US_ASCII: " + Arrays.toString("”".getBytes(StandardCharsets.US_ASCII)));
    System.out.println("----------------------------------------------------------");
    String iSO_8859_1EncodedString = new String(bytes, StandardCharsets.ISO_8859_1);
    System.out.println("Text of ISO_8859_1: " + iSO_8859_1EncodedString);
    System.out.println("Text length of ISO_8859_1: " + iSO_8859_1EncodedString.length());
    System.out.println(
        "Bytes of “ in ISO_8859_1: " + Arrays.toString("“".getBytes(StandardCharsets.ISO_8859_1)));
    System.out.println(
        "Bytes of ” in ISO_8859_1: " + Arrays.toString("”".getBytes(StandardCharsets.ISO_8859_1)));
    System.out.println("----------------------------------------------------------");
    String uTF_16BEEncodedString = new String(bytes, StandardCharsets.UTF_16BE);
    System.out.println("Text of UTF_16BE: " + uTF_16BEEncodedString);
    System.out.println("Text length of UTF_16BE: " + uTF_16BEEncodedString.length());
    System.out.println(
        "Bytes of “ in UTF_16BE: " + Arrays.toString("“".getBytes(StandardCharsets.UTF_16BE)));
    System.out.println(
        "Bytes of ” in UTF_16BE: " + Arrays.toString("”".getBytes(StandardCharsets.UTF_16BE)));
    System.out.println("----------------------------------------------------------");
    String uTF_16LEEncodedString = new String(bytes, StandardCharsets.UTF_16LE);
    System.out.println("Text of UTF_16LE: " + uTF_16LEEncodedString);
    System.out.println("Text length of UTF_16LE: " + uTF_16LEEncodedString.length());
    System.out.println(
        "Bytes of “ in UTF_16LE: " + Arrays.toString("“".getBytes(StandardCharsets.UTF_16LE)));
    System.out.println(
        "Bytes of ” in UTF_16LE: " + Arrays.toString("”".getBytes(StandardCharsets.UTF_16LE)));
    System.out.println("----------------------------------------------------------");
    String uTF_16EncodedString = new String(bytes, StandardCharsets.UTF_16);
    System.out.println("Text of UTF_16: " + uTF_16EncodedString);
    System.out.println("Text length of UTF_16: " + uTF_16EncodedString.length());
    System.out.println(
        "Bytes of “ in UTF_16: " + Arrays.toString("“".getBytes(StandardCharsets.UTF_16)));
    System.out.println(
        "Bytes of ” in UTF_16: " + Arrays.toString("”".getBytes(StandardCharsets.UTF_16)));
  }
}
