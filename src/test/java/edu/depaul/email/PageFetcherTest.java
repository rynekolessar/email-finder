package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PageFetcherTest {
  final String GOOD_URL = "https://cdm.depaul.edu";
  final String BAD_URL = "https://badurl.com";

  @Test
  @DisplayName("Tests that the get() method returns a document when the url is valid")
  void testGet() {
    PageFetcher fetcher = new PageFetcher();
    Document out = fetcher.get(GOOD_URL);
    assertNotNull(out);
  }

  @Test
  @DisplayName("Tests that the getString() method returns a string when the url is valid")
  void testGetString() {
    PageFetcher fetcher = new PageFetcher();
    String out = fetcher.getString(GOOD_URL);
    assertNotNull(out);
  }

  @Test
  @DisplayName("Tests that get() throws an exception when the url is invalid")
  void testGetException() {
    PageFetcher fetcher = new PageFetcher();
    assertThrows(EmailFinderException.class, () -> fetcher.get(BAD_URL));
  }

  @Test
  @DisplayName("Tests that getString() throws an exception when the url is invalid")
  void testGetStringException() {
    PageFetcher fetcher = new PageFetcher();
    assertThrows(EmailFinderException.class, () -> fetcher.getString(BAD_URL));
  }
}
