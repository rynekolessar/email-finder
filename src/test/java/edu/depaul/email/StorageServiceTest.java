package edu.depaul.email;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * To test, you need to call addLocation() to set up where the test will write to, then call
 * storeList() and check that the file specified in addLocation() was actually written to.
 *
 * You can also create error situations by passing bad paths to addLocation. storeList() should
 * then produce the expected exception.
 */
public class StorageServiceTest {
  // TODO: Write StorageService Tests

  @Test
  @DisplayName("Tests the constructor")
  void testConstructor() {
    StorageService storage = new StorageService();
    assertNotNull(storage);
  }


}
