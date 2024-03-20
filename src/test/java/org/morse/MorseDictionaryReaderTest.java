package org.morse;

import org.junit.jupiter.api.Test;
import org.morse.reader.MorseDictionaryReadException;
import org.morse.reader.MorseDictionaryReadRuntimeException;
import org.morse.reader.MorseDictionaryReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MorseDictionaryReaderTest {

    @Test
    public void testReadMorseDictionary() throws MorseDictionaryReadException {
        // software under test
        var sut = new MorseDictionaryReader();

        var englishToMorse = sut.readEnglishToMorseDictionary();
        assertThat(englishToMorse, hasEntry("A", ".-"));
        assertThat(englishToMorse, hasEntry("9", "----."));
        assertThat(englishToMorse.keySet(), hasSize(36));
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
