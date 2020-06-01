package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * It could be I should update this one to improve its testability. The fact PageFetcher and
 * PageParser are created internally makes it harder to test (no mocks can be used there).
 * <p>
 * StorageService is passed in so a mock could be used here. The main things to be tested here are:
 * 1. The crawl function is recursive. Does it avoid endless loops?
 * 2. Does it quit when the target size is met (max addresses should also be configurable
 * 3. Does the report function produce expected results
 */
public class PageCrawlerTest {
  // TODO: Write page crawler tests

  @Test
  @DisplayName("Tests the PageCrawler constructor")
  void testConstructor() {

  }
}
