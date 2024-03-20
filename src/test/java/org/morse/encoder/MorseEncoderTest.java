package org.morse.encoder;

import org.junit.jupiter.api.Test;
import org.morse.dictionary.MorseDictionaryEncoder;
import org.morse.reader.MorseDictionaryReadException;
import org.morse.reader.MorseDictionaryReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MorseEncoderTest {

    @Test
    public void encodeEnglishWord() throws MorseDictionaryReadException {
        var sut = new MorseEncoder(new MorseDictionaryEncoder(new MorseDictionaryReader()));
        var morseWord = sut.encodeEnglishWordToMorse("AbC");
        assertThat(morseWord, equalTo(".- -... -.-."));
    }

    @Test
    public void encodeMorseWord() throws MorseDictionaryReadException {
        var sut = new MorseEncoder(new MorseDictionaryEncoder(new MorseDictionaryReader()));
        var morseWord = sut.encodeMorseWordToEnglish(".- -... -.-.");
        assertThat(morseWord, equalTo("ABC"));
    }

    @Test
    public void encodeEnglishWordWithFailure() throws MorseDictionaryReadException {
        var sut = new MorseEncoder(new MorseDictionaryEncoder(new MorseDictionaryReader()));
        var exception = assertThrows(MorseEncoderException.class, () -> sut.encodeEnglishWordToMorse("***WRONG***"));
        assertThat(exception.getMessage(), containsString("Error while encoding englishWord"));
        assertThat(exception.getMessage(), containsString("*"));
    }

    @Test
    public void encodeMorseWordWithFailure() throws MorseDictionaryReadException {
        var sut = new MorseEncoder(new MorseDictionaryEncoder(new MorseDictionaryReader()));
        var exception = assertThrows(MorseEncoderException.class, () -> sut.encodeMorseWordToEnglish("!"));
        assertThat(exception.getMessage(), containsString("Error while encoding morseWord"));
        assertThat(exception.getMessage(), containsString("!"));
    }


}
