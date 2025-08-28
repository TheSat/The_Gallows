package ru.isobolev.studying;


public class GameLoop {

    private Render render;
    private DictionaryReader dictionary;
    private GameLogic logic;

    private static GameLoop instance;

    private GameLoop() {
        this.render = Render.getInstance();
        this.dictionary = DictionaryReader.getInstance();
        this.logic = GameLogic.getInstance();
    }

    public static synchronized GameLoop getInstance() {
        if (instance == null) {
            instance = new GameLoop();
        }
        return instance;
    }

    public void start() {
        logic.showGameMenu(this, "start");

        while (true) {
            render.draw(this);
            logic.play(this);
        }
    }


    public Render getRender() {
        return render;
    }

    public DictionaryReader getDictionary() {
        return dictionary;
    }

    public GameLogic getLogic() {
        return logic;
    }
}

