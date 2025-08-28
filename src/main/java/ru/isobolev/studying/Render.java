package ru.isobolev.studying;

import java.text.MessageFormat;
import java.util.Map;

public class Render {

    private Integer currentGallowsLevel = 0;

    private static Render instance;

    private final static Map<String, String> gameMenu = Map.of(
            "start", """
                    ========================================
                    =            *ИГРА ВИСЕЛИЦА*           =
                    =          начать новую игру?          =
                    =       Y - старт игры / N - выход     =
                    ========================================
                    """,
            "win", """
                    ========================================
                    =               *ПОБЕДА!*              =
                    =          начать новую игру?          =
                    =       Y - старт игры / N - выход     =
                    ========================================
                    """,
            "lose", """
                    ========================================
                    =              *ПРОИГРЫШ :(*           =
                    =          начать новую игру?          =
                    =       Y - старт игры / N - выход     =
                    ========================================
                    """
    );
    private final static Map<Integer, String> gallowsLevel = Map.of(
            0, """
                    ||=====||
                    ||     |
                    ||
                    ||
                    ||
                    ||
                    ||
                    ||
                    """,
            1, """
                    ||=====||
                    ||     |
                    ||     O
                    ||
                    ||
                    ||
                    ||
                    ||
                    """,
            2, """
                    ||=====||
                    ||     |
                    ||     O
                    ||     |
                    ||
                    ||
                    ||
                    ||
                    """,
            3, """
                    ||=====||
                    ||     |
                    ||     O
                    ||     |
                    ||     |
                    ||
                    ||
                    ||
                    """,
            4, """
                    ||=====||
                    ||     |
                    ||     O
                    ||    /|
                    ||     |
                    ||
                    ||
                    ||
                    """,
            5, """
                    ||=====||
                    ||     |
                    ||     O
                    ||    /|\\
                    ||     |
                    ||
                    ||
                    ||
                    """,
            6, """
                    ||=====||
                    ||     |
                    ||     O
                    ||    /|\\
                    ||     |
                    ||    /
                    ||
                    ||
                    """,
            7, """
                    ||=====||
                    ||     |
                    ||     O
                    ||    /|\\
                    ||     |
                    ||    /
                    ||
                    ||
                    """,
            8, """
                    ||=====||
                    ||     |
                    ||     O
                    ||    /|\\
                    ||     |
                    ||    /
                    ||
                    ||
                    """,
            9, """
                    ||=====||
                    ||     |
                    ||     O
                    ||    /|\\
                    ||     |
                    ||    / \\
                    ||
                    ||
                    """
    );


    private Render() {
    }

    public static synchronized Render getInstance() {
        if (instance == null) {
            instance = new Render();
        }
        return instance;
    }

    public String getCurrentGallowsLevel() {
        return gallowsLevel.get(currentGallowsLevel);
    }

    public String getGameMenu(String level) {
        return gameMenu.get(level);
    }

    public void draw(GameLoop loop) {
//        clearConsole();
        System.out.println(getCurrentGallowsLevel());
        String meta = MessageFormat.format(
                "Слово: {0}\n" +
                        "Ошибки {1}:\n", loop.getLogic().getHiddenCharLinkedList().toString(), loop.getLogic().getErrors());

        System.out.println(meta);

    }

    public void incrementCurrentGallowsLevel() {
        currentGallowsLevel++;
    }

    public void currentGallowsLevelReset() {
        currentGallowsLevel = 0;
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Не удалось очистить консоль");
        }

    }
}

