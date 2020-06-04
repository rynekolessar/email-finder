package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StorageServiceTest {

  @Test
  @DisplayName("Tests the constructor")
  void testConstructor() {
    StorageService storage = new StorageService();
    assertNotNull(storage);
  }

  @Test
  @DisplayName("Tests that an exception is thrown when no email location is given.")
  void testExceptionNoEmailLocation() {
    StorageService storageService = new StorageService();
    Collection<String> collection = new ArrayList<>();
    assertThrows(EmailFinderException.class, () -> storageService.storeList(StorageService.StorageType.EMAIL, collection));
  }

  @Test
  @DisplayName("Tests that an exception is thrown when no goodlinks location is given")
  void testExceptionNoGoodLinksLocation() {
    StorageService storageService = new StorageService();
    Collection<String> collection = new ArrayList<>();
    assertThrows(EmailFinderException.class, () -> storageService.storeList(StorageService.StorageType.GOODLINKS, collection));
  }

  @Test
  @DisplayName("Tests that an exception is thrown when no badlinks location is given")
  void testExceptionNoBadLinksLocation() {
    StorageService storageService = new StorageService();
    Collection<String> collection = new ArrayList<>();
    assertThrows(EmailFinderException.class, () -> storageService.storeList(StorageService.StorageType.BADLINKS, collection));
  }

  @Test
  @DisplayName("Tests that an exception is thrown when trying to write to an invalid location")
  void testExceptionInvalidLocation() {
    StorageService storageService = new StorageService();

    Collection<String> collection = new ArrayList<>();
    collection.add("testemail@gmail.com");
    collection.add("testemail2@gmail.com");

    String location = "src/test/resources/invalid/test.txt";
    storageService.addLocation(StorageService.StorageType.EMAIL, location);

    assertThrows(EmailFinderException.class, () -> storageService.storeList(StorageService.StorageType.EMAIL, collection));
  }

  @Test
  @DisplayName("Tests the given badlinks location was written to.")
  void testBadLinksLocation () throws IOException {
    StorageService storageService = new StorageService();

    Collection<String> collection = new ArrayList<>();
    collection.add("https://badlink1.com");
    collection.add("https://badlink2.com");

    String location = "src/test/resources/badLinks.txt";
    storageService.addLocation(StorageService.StorageType.BADLINKS, location);
    storageService.storeList(StorageService.StorageType.BADLINKS, collection);

    List<String> badLinks = Files.readAllLines(Paths.get(location), StandardCharsets.US_ASCII);
    assertEquals(collection, badLinks);
  }

  @Test
  @DisplayName("Tests the given email location was written to.")
  void testEmailLocation () throws IOException {
    StorageService storageService = new StorageService();

    Collection<String> collection = new ArrayList<>();
    collection.add("testemail@gmail.com");
    collection.add("testemail2@gmail.com");

    String location = "src/test/resources/emails.txt";
    storageService.addLocation(StorageService.StorageType.EMAIL, location);
    storageService.storeList(StorageService.StorageType.EMAIL, collection);

    List<String> emails = Files.readAllLines(Paths.get(location), StandardCharsets.US_ASCII);
    assertEquals(collection, emails);
  }

  @Test
  @DisplayName("Tests the given goodLinks location was written to.")
  void testGoodLinksLocation () throws IOException {
    StorageService storageService = new StorageService();

    Collection<String> collection = new ArrayList<>();
    collection.add("https://www.google.com");
    collection.add("https://cdm.depaul.edu");

    String location = "src/test/resources/goodLinks.txt";
    storageService.addLocation(StorageService.StorageType.GOODLINKS, location);
    storageService.storeList(StorageService.StorageType.GOODLINKS, collection);

    List<String> goodLinks = Files.readAllLines(Paths.get(location), StandardCharsets.US_ASCII);
    assertEquals(collection, goodLinks);
  }
}
