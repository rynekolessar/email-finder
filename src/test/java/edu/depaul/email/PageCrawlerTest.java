package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PageCrawlerTest {
  final String BAD_URL = "https://www.a;sdlkfjeinguwp.com";

  @Test
  @DisplayName("Tests the PageCrawler constructor")
  void testConstructor() {
    StorageService mockStorage = mock(StorageService.class);
    PageCrawler crawler = new PageCrawler(mockStorage);
    assertNotNull(crawler);
  }

  @ParameterizedTest
  @DisplayName("Tests that the crawl function stops when the target size is met")
  @ValueSource(strings = {"https://www.cdm.depaul.edu/", "https://www.stickyminds.com/", "https://www.amazon.com/"})
  void testCrawlMax(String url) {
    StorageService mockStorage = mock(StorageService.class);
    PageCrawler crawler = new PageCrawler(mockStorage, 10);
    crawler.crawl(url);
    crawler.report();
    assertTrue(crawler.getEmails().size() <= 10);
  }

  @Test
  @DisplayName("Tests invalid urls are stored")
  void testCrawlInvaildUrl() {
    StorageService mockStorage = mock(StorageService.class);
    PageCrawler crawler = new PageCrawler(mockStorage);
    crawler.crawl(BAD_URL);
    crawler.report();
    assertTrue(crawler.getBadLinks().contains(BAD_URL));
  }
}
