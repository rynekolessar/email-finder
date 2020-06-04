package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmailFinderTest {

  final String testPage = "src/test/resources/testPage.html";
  final String testPage2 = "src/test/resources/testPage2.html";
  final String EMAILS = "email.txt";
  final String BAD_LINKS = "badlinks.txt";
  final String GOOD_LINKS = "good-links.txt";
  final String badUrl = "http://www.invaildurl.com";

  @Test
  @DisplayName("tests the constructor")
  void testConstructor() {
    EmailFinder emailFinder = new EmailFinder();
    assertNotNull(emailFinder);
  }

  @Test
  @DisplayName("Tests that the EMAIL output file is created successfully")
  void testEmailOutputFileCreated() {
    String[] args = {testPage};
    EmailFinder.main(args);

    File emailFile = new File(EMAILS);
    assertTrue(emailFile.exists());
  }

  @Test
  @DisplayName("Tests that the GOODLINKS output file is created successfully")
  void testGoodLinksOutputFileCreated() {
    String[] args = {testPage};
    EmailFinder.main(args);

    File goodLinksFile = new File(GOOD_LINKS);
    assertTrue(goodLinksFile.exists());
  }

  @Test
  @DisplayName("Tests that the BADLINKS output file is created successfully")
  void testBadLinksOutputFileCreated() {
    String[] args = {testPage};
    EmailFinder.main(args);

    File badLinksFile = new File(BAD_LINKS);
    assertTrue(badLinksFile.exists());
  }


  @Test
  @DisplayName("Tests that the contents of the EMAIL file are as expected")
  void testEmailContent() throws IOException {
    String[] args = {testPage};
    EmailFinder.main(args);

    Collection<String> expected = new ArrayList<>();
    expected.add("emailaddress@gmail.com");
    expected.add("emailaddress2@gmail.com");

    List<String> emailFile = Files.readAllLines(Paths.get(EMAILS), StandardCharsets.US_ASCII);
    assertTrue(expected.containsAll(emailFile));
  }

  @Test
  @DisplayName("Tests that the contents of the BADLINKS output file are as expected")
  void testBadLinksContent() throws IOException {
    String[] args = {testPage};
    EmailFinder.main(args);

    Collection<String> expected = new ArrayList<>();
    expected.add(badUrl);

    List<String> badLinksFile = Files.readAllLines(Paths.get(BAD_LINKS), StandardCharsets.US_ASCII);
    assertEquals(expected, badLinksFile);
  }

  @Test
  @DisplayName("Tests that the contents of the GOODLINKS output file are as expected")
  void testGoodLinksContent() throws IOException {
    String[] args = {testPage};
    EmailFinder.main(args);

    Collection<String> expected = new ArrayList<>();
    expected.add(testPage2);
    expected.add(testPage);

    List<String> goodLinksFile = Files.readAllLines(Paths.get(GOOD_LINKS), StandardCharsets.US_ASCII);
    assertTrue(expected.containsAll(goodLinksFile));
  }
}
