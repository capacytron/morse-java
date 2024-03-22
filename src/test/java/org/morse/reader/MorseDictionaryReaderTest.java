package org.morse.reader;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MorseDictionaryReaderTest {

    @Test
    public void testReadMorseDictionary() throws MorseDictionaryReadException {
        // software under test
        var sut = new MorseDictionaryReader();

        var englishToMorse = sut.readEnglishToMorseDictionary();
        // hamcrest way to assert
        assertThat(englishToMorse, hasEntry("A", ".-"));
        assertThat(englishToMorse, hasEntry("9", "----."));
        //junit way to assert
        assertEquals(englishToMorse.get("A"), ".-");

        // hamcrest way to assert
        assertThat(englishToMorse.keySet(), hasSize(36));
        // junit way to assert
        assertEquals(englishToMorse.size(), 36);
    }

    @Test
    public void testFailureWhenFileDoesNotExist() {
        var sut = new MorseDictionaryReader("wrong_file.csv");
        var exception = assertThrows(MorseDictionaryReadException.class, sut::readEnglishToMorseDictionary);
        assertThat(exception.getCause(), instanceOf(NullPointerException.class));
    }

    @Test
    public void testFailureWhenFileHasIncorrectFormat() {
        var sut = new MorseDictionaryReader("/broken_morse_dictionary.csv");
        var exception = assertThrows(MorseDictionaryReadRuntimeException.class, sut::readEnglishToMorseDictionary);
        assertThat(exception.getMessage(), startsWith("Failed to split line by ','"));
        assertThat(exception.getMessage(), containsString("I AM TOTALLY WRONG"));
    }
}
