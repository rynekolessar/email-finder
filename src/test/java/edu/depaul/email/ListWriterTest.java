package edu.depaul.email;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class ListWriterTest {

    @Test
    @DisplayName("Tests that the writeList() method returns the correct results")
    void testWriteList() throws IOException {
        OutputStream stream = new ByteArrayOutputStream();
        ListWriter writer = new ListWriter(stream);
        String expected = "test\ntest\ntest\n";

        Collection<String> collection = new ArrayList<>();
        collection.add("test");
        collection.add("test");
        collection.add("test");
        writer.writeList(collection);

        assertEquals(expected,stream.toString());
    }
}
