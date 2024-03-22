package org.morse.encoder;

import com.google.inject.Inject;
import org.morse.dictionary.MorseDictionaryEncoder;
import org.morse.dictionary.MorseDictionaryException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseEncoder {

    private final MorseDictionaryEncoder morseDictionaryEncoder;

    /**
     * for Guice Dependency injector, it tells injector to inject MorseDictionaryEncoder
     * into this constructor.
     * You should tell it to injector using special annotation  @Inject  (part of Guice library)
     * injector has no idea what to do with constructor.
     * You literally tell injector to inject MorseDictionaryEncoder here
     * */
    @Inject
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
