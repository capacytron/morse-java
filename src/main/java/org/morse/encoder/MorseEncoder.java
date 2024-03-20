package org.morse.encoder;

import org.morse.dictionary.MorseDictionaryEncoder;
import org.morse.dictionary.MorseDictionaryException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseEncoder {

    private final MorseDictionaryEncoder morseDictionaryEncoder;

    public MorseEncoder(MorseDictionaryEncoder morseDictionaryEncoder) {
        this.morseDictionaryEncoder = morseDictionaryEncoder;
    }

    public String encodeEnglishWordToMorse(String englishWord) {
        return Arrays.stream(englishWord.split("")).map(englishLetter -> {
            try {
                return morseDictionaryEncoder.encodeEnglishLetterToMorse(englishLetter.toUpperCase());
            } catch (MorseDictionaryException e) {
                throw new MorseEncoderException("Error while encoding englishWord" + englishWord + ", bad englishLetter: " + englishLetter, e);
            }
        }).collect(Collectors.joining(" "));
    }

    public String encodeMorseWordToEnglish(String morseWord) {
        return Arrays.stream(morseWord.split(" ")).map(morseSymbol -> {
            try {
                return morseDictionaryEncoder.encodeMorseSymbolToEnglish(morseSymbol);
            } catch (MorseDictionaryException e) {
                throw new MorseEncoderException("Error while encoding morseWord" + morseWord + ", bad morseSymbol: " + morseSymbol, e);
            }
        }).collect(Collectors.joining(""));
    }

}
