package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PageCrawlerTest {
  final String BAD_URL = "http://www.invaildurl.com";
  final String TEST_PAGE = "src/test/resources/testPage3.html";

  @Test
  @DisplayName("Tests the PageCrawler constructor")
  void testConstructor() {
    StorageService mockStorage = mock(StorageService.class);
    PageCrawler crawler = new PageCrawler(mockStorage);
    assertNotNull(crawler);
  }

  @ParameterizedTest()
  @DisplayName("Tests that the crawl function stops when the target size is met")
  @ValueSource(strings = {"https://www.cdm.depaul.edu/", TEST_PAGE})
  void testCrawlMax(String url) {
    StorageService mockStorage = mock(StorageService.class);
    PageCrawler crawler = new PageCrawler(mockStorage, 5);
    crawler.crawl(url);
    crawler.report();
    assertTrue(crawler.getEmails().size() <= 5);
  }

  @Test
  @DisplayName("Tests invalid urls are stored")
  void testCrawlInvalidUrl() {
    StorageService mockStorage = mock(StorageService.class);
    PageCrawler crawler = new PageCrawler(mockStorage);
    crawler.crawl(TEST_PAGE);
    crawler.report();
    assertTrue(crawler.getBadLinks().contains(BAD_URL));
  }
}
