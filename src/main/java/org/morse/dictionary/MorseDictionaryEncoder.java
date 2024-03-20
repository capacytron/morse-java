package org.morse.dictionary;

import org.morse.reader.MorseDictionaryReadException;
import org.morse.reader.MorseDictionaryReader;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MorseDictionaryEncoder {
    private final Map<String, String> englishToMorse;
    private final Map<String, String> morseToEnglish;

    public MorseDictionaryEncoder(MorseDictionaryReader morseDictionaryReader) throws MorseDictionaryReadException {
        englishToMorse = morseDictionaryReader.readEnglishToMorseDictionary();
        morseToEnglish = englishToMorse.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public String encodeEnglishLetterToMorse(String englishLetter) throws MorseDictionaryException {
        return Optional.ofNullable(englishToMorse.get(englishLetter))
                .orElseThrow(() -> new MorseDictionaryException("Can't encode englishLetter to morse, englishLetter: " + englishLetter));
    }

    public String encodeMorseSymbolToEnglish(String morseCode) throws MorseDictionaryException {
        return Optional.ofNullable(morseToEnglish.get(morseCode))
                .orElseThrow(() -> new MorseDictionaryException("Can't encode morseCode to english, morseCode: " + morseCode));
    }

}
