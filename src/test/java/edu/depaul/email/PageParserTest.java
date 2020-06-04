package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PageParserTest {
  String testPageSeveral = "<!DOCTYPE html>\n" +
          "<html lang=\"en\">\n" +
          "<head>\n" +
          "    <meta charset=\"UTF-8\">\n" +
          "    <title>Test</title>\n" +
          "</head>\n" +
          "<body>\n" +
          "    <a href=\"https://www.cdm.depaul.edu/Pages/default.aspx\">CDM Website</a>\n" +
          "    <a href=\"http://invaildurl.com\">Invalid URL</a>\n" +
          "\n" +
          "    <p>emailaddress@gmail.com</p>\n" +
          "    <p>emailaddress2@gmail.com</p>\n" +
          "</body>\n" +
          "</html>";

  String testPageOne = "<!DOCTYPE html>\n" +
          "<html lang=\"en\">\n" +
          "<head>\n" +
          "    <meta charset=\"UTF-8\">\n" +
          "    <title>Title</title>\n" +
          "</head>\n" +
          "<body>\n" +
          "    <a href=\"https://www.cdm.depaul.edu/Pages/default.aspx\">CDM Website</a>\n" +
          "\n" +
          "    <p>emailaddress@gmail.com</p>\n" +
          "</body>\n" +
          "</html>";

  String emptyTestPage = "<!DOCTYPE html>\n" +
          "<html lang=\"en\">\n" +
          "<head>\n" +
          "    <meta charset=\"UTF-8\">\n" +
          "    <title>Title</title>\n" +
          "</head>\n" +
          "<body>\n" +
          "\n" +
          "</body>\n" +
          "</html>";


  @Test
  @DisplayName("Tests a Document with no emails or links")
  void testFindEmailsNone() {
    Document document = Jsoup.parse(emptyTestPage);
    PageParser parser = new PageParser();
    Set<String> emailsFound = parser.findEmails(document);
    assertEquals(0, emailsFound.size());
  }

  @Test
  @DisplayName("tests that the correct number of links are found in a document with 1 <a> tag.")
  void testFindLinksOneTag() {
    Document document = Jsoup.parse(testPageOne);
    PageParser parser = new PageParser();
    Set<String> linksFound = parser.findLinks(document);
    assertEquals(1, linksFound.size());
  }

  @Test
  @DisplayName("tests that the correct number of emails are found in a document with 1 <a> tag.")
  void testFindEmailsOneTag() {
    Document document = Jsoup.parse(testPageOne);
    PageParser parser = new PageParser();
    Set<String> emailsFound = parser.findEmails(document);
    assertEquals(1, emailsFound.size());
  }

  @Test
  @DisplayName("Tests that the correct number of links are found in a document with several <a> tags.")
  void testFindLinksSeveralTags() {
    Document document = Jsoup.parse(testPageSeveral);
    PageParser parser = new PageParser();
    Set<String> linksFound = parser.findLinks(document);
    assertEquals(2, linksFound.size());
  }

  @Test
  @DisplayName("Tests that the correct number of emails are found in a document with several <a> tags")
  void testFindEmailsSeveralTags() {
    Document document = Jsoup.parse(testPageSeveral);
    PageParser parser = new PageParser();
    Set<String> emailsFound = parser.findEmails(document);
    assertEquals(2, emailsFound.size());
  }
}
