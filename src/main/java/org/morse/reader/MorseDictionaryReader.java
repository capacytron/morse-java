package org.morse.reader;

import org.apache.commons.io.FileUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Read source file with English to Morse dictionary
 * https://github.com/jvcleave/example-ofFile-MorseCode/blob/master/bin/data/morse.csv?plain=1
 * Google for it :)
 */
public class MorseDictionaryReader {

    private final String morseDictionaryFile;



    public MorseDictionaryReader() {
        this.morseDictionaryFile = "/morse_dictionary.csv";
    }

    public MorseDictionaryReader(String pathToFile) {
        this.morseDictionaryFile = pathToFile;
    }


    public Map<String, String> readEnglishToMorseDictionary() throws MorseDictionaryReadException {
        var lines = readLinesFromFile();
        // Java stream API is horrible comparing to other languages like scala/groovy for example,
        // but you have to learn it.
        return lines.stream()
                .collect(Collectors.toMap(line -> splitLine(line, 0), line -> splitLine(line, 1)));

    }

    private String splitLine(String line, int index) {
        var splittedLine = line.split(",");
        if (splittedLine.length != 2) {
            throw new MorseDictionaryReadRuntimeException("Failed to split line by ',' expected two elements, " +
                    "but got " + splittedLine.length + ", line: " + line);
        }
        return splittedLine[index];
    }

    private List<String> readLinesFromFile() throws MorseDictionaryReadException {
        try {
            var file = FileUtils.toFile(this.getClass().getResource(morseDictionaryFile));
            return FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new MorseDictionaryReadException("Can't read morseDictionaryFile: " + morseDictionaryFile, e);
        }
    }
}
