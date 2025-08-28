package ru.isobolev.studying;


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
        return "Сова";
    }

}
