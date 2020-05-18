package edu.depaul.email;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is just a wrapper around the 3rd party library being used to access web servers. There
 * is not much of a reason to mock here since its whole reason for being is to interact with the
 * outside world just verify:
 *
 * 1. get returns a document when the url is good
 * 2. same for getstring
 * 3. try to force some exceptions to verify you get the expected types
 */
public class PageFetcherTest {
  // TODO: Write PageFetcher Tests
}
