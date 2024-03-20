package org.morse;

import org.morse.dictionary.MorseDictionaryEncoder;
import org.morse.encoder.MorseEncoder;
import org.morse.reader.MorseDictionaryReadException;
import org.morse.reader.MorseDictionaryReader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MorseDictionaryReadException {
        var quitCommand = "quit!";
        Scanner scanner = new Scanner(System.in);
        var morseEncoder = new MorseEncoder(new MorseDictionaryEncoder(new MorseDictionaryReader()));
        while (true) {
            System.out.println("Enter " + quitCommand + " if you want to quit program");
            System.out.println("Enter English or Morse word to translate");
            System.out.println();
            var userInput = scanner.nextLine();
            if (userInput.equals(quitCommand)) {
                System.out.println("Bye!");
                break;
            } else if (userInput.contains(".") || userInput.contains("-")) {
                var result = morseEncoder.encodeMorseWordToEnglish(userInput);
                System.out.println("Morse to english translation: " + result);
            } else {
                var result = morseEncoder.encodeEnglishWordToMorse(userInput);
                System.out.println("English to Morse translation: " + result);
            }
        }
    }
}