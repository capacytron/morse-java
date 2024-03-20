package org.morse.draft;

import org.morse.dictionary.MorseDictionaryEncoder;
import org.morse.dictionary.MorseDictionaryException;
import org.morse.encoder.MorseEncoderException;
import org.morse.reader.MorseDictionaryReadException;
import org.morse.reader.MorseDictionaryReader;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MorseDraft {

    class MorseDictionaryReader {
        public Map<String, String> readEnglishToMorseDictionary() {
            return null;
        }
    }

    class MorseDictionaryEncoder {
        public MorseDictionaryEncoder(MorseDictionaryReader morseDictionaryReader) {
        }

        public String encodeEnglishLetterToMorse(String englishLetter) throws MorseDictionaryException {
            return null;
        }

        public String encodeMorseSymbolToEnglish(String morseCode) throws MorseDictionaryException {
            return null;
        }
    }

    class MorseEncoder {



        public MorseEncoder(MorseDictionaryEncoder morseDictionaryEncoder) {
        }

        public String encodeEnglishWordToMorse(String englishWord) {
            return null;
        }

        public String encodeMorseWordToEnglish(String morseWord) {
            return null;
        }
    }
}
