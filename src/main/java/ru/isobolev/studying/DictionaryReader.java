package ru.isobolev.studying;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class DictionaryReader {

    private static DictionaryReader dictionary;

    private DictionaryReader() {
    }

    public static synchronized DictionaryReader getInstance() {
        if (dictionary == null) {
            dictionary = new DictionaryReader();
        }
        return dictionary;
    }


    public String getRandomWord() {

        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource("dictionary/russian_nouns.txt").toURI());

            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return lines.get(new Random().nextInt(lines.size()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("При чтении файла произошла ошибка, ", e);
        }

    }

}
