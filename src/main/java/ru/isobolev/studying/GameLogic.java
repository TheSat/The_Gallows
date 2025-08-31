package ru.isobolev.studying;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GameLogic {

    private final Integer MAX_ITERATION = 8;

    private LinkedList<Character> hiddenCharLinkedList = new LinkedList<>();

    private LinkedList<Character> displayedCharLinkedList = new LinkedList<>();

    public LinkedList<Character> getDisplayedCharLinkedList() {
        return displayedCharLinkedList;
    }

    private LinkedList<Character> allCharFromInputLinkedList = new LinkedList<>();

    private LinkedList<Character> errors = new LinkedList<>();

    public LinkedList<Character> getErrors() {
        return errors;
    }

    private static GameLogic instance;

    private GameLogic() {
    }

    public static synchronized GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public void play(GameLoop loop) {

        if (displayedCharLinkedList.equals(hiddenCharLinkedList)) {
            showGameMenu(loop, "win");
            return;
        } else if (errors.size() > MAX_ITERATION) {
            String w = hiddenCharLinkedList.stream().map(String::valueOf).collect(Collectors.joining());
            System.out.println("Загаданное слово: " + w);
            showGameMenu(loop, "lose");
            return;
        }

        Character inputChar = askCharacter();

        if (inputChar == null) {
            System.out.println("");
            return;
        }

        if (errors.contains(inputChar) || allCharFromInputLinkedList.contains(inputChar)) {
            System.out.println("Вы вводили уже такую букву");
            System.out.println("");
            return;
        }

        if (hiddenCharLinkedList.contains(inputChar)) {
            allCharFromInputLinkedList.add(inputChar);
            System.out.println("Угадал!");
            for (int index = 0; index < hiddenCharLinkedList.size(); index++) {
                Character currentChar = hiddenCharLinkedList.get(index);
                if (currentChar.equals(inputChar)) {
                    displayedCharLinkedList.set(index, inputChar);
                }
            }
        } else {
            allCharFromInputLinkedList.add(inputChar);
            errors.add(inputChar);
            loop.getRender().incrementCurrentGallowsLevel();
        }

    }

    public void showGameMenu(GameLoop loop, String gameMenu) {
        Scanner in = new Scanner(System.in);
        System.out.println(loop.getRender().getGameMenu(gameMenu));

        System.out.print("Ответ: ");

        while (true) {
            String i = in.nextLine();
            if (i.equals("Y")) {
                break;
            } else if (i.equals("N")) {
                exit();
            } else {
                System.out.println("Введен не корректный символ!");
            }
        }
        resetGame(loop);
    }

    private void resetGame(GameLoop loop) {
        hiddenCharLinkedList.clear();
        displayedCharLinkedList.clear();
        allCharFromInputLinkedList.clear();
        errors.clear();
        loop.getRender().currentGallowsLevelReset();

        String generatedWord = loop.getDictionary().getRandomWord().toLowerCase();

        for (int i = 0; i < generatedWord.length(); i++) {
            hiddenCharLinkedList.add(generatedWord.charAt(i));
            displayedCharLinkedList.add('_');
        }
    }

    private Character askCharacter() {
        Scanner in = new Scanner(System.in);
        System.out.print("Буква: ");
        String rawCharacter = in.nextLine();
        if (rawCharacter.length() > 1) {
            System.out.println("Вы ввели более одной буквы!");
            return null;
        } else if (rawCharacter.isEmpty()) {
            System.out.println("Вы ничего не ввели!");
            return null;
        } else if (isCyrillic(rawCharacter)) {
            System.out.println("Вы ввели не русский символ!");
            return null;
        }
        return rawCharacter.toLowerCase().charAt(0);
    }

    private Boolean isCyrillic(String word) {
        return !Pattern.matches("[а-яА-Я]", word);
    }

    private void exit() {
        System.exit(0);
    }
}
