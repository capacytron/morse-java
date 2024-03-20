package org.morse.dictionary;

import org.junit.jupiter.api.Test;
import org.morse.reader.MorseDictionaryReadException;
import org.morse.reader.MorseDictionaryReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MorseDictionaryEncoderTest {

    @Test
    public void testEncodeEnglishToMorse() throws MorseDictionaryReadException, MorseDictionaryException {
        var sut = new MorseDictionaryEncoder(new MorseDictionaryReader());
        var morseCode = sut.encodeEnglishLetterToMorse("A");
        assertThat(morseCode, equalTo(".-"));
    }


    @Test
    public void testEncodeMorseToEnglish() throws MorseDictionaryReadException, MorseDictionaryException {
        var sut = new MorseDictionaryEncoder(new MorseDictionaryReader());
        var morseCode = sut.encodeMorseSymbolToEnglish(".-");
        assertThat(morseCode, equalTo("A"));
    }

    @Test
    public void testEncodeEnglishToMorseFailure() throws MorseDictionaryReadException {
        var sut = new MorseDictionaryEncoder(new MorseDictionaryReader());
        var exception = assertThrows(MorseDictionaryException.class, () -> {
            sut.encodeEnglishLetterToMorse("OOPS!");
        });
        assertThat(exception.getMessage(), startsWith("Can't encode englishLetter to morse, englishLetter"));
        assertThat(exception.getMessage(), containsString("OOPS!"));
    }

    @Test
    public void testEncodeMorseToEnglishFailure() throws MorseDictionaryReadException {
        var sut = new MorseDictionaryEncoder(new MorseDictionaryReader());
        var exception = assertThrows(MorseDictionaryException.class, () -> {
            sut.encodeMorseSymbolToEnglish("-- -- --");
        });
        assertThat(exception.getMessage(), startsWith("Can't encode morseCode to english, morseCode"));
        assertThat(exception.getMessage(), containsString("-- -- --"));
    }
}
