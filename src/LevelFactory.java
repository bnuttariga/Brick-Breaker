import java.awt.Color;

public class LevelFactory {

    private final static int NUM_LEVELS = 3;
    private static int levelOn = 1;

    /**
     * Constructs a new LevelFactory. All values are defaults.
     */
    public LevelFactory() {}
    public LevelFactory(boolean a) {
        levelOn = 1;
    }

    public int getNumLevels() {
        return NUM_LEVELS;
    }

    public int levelOn() {
        return levelOn;
    }

    public boolean hasMoreLevels() {
        return levelOn < NUM_LEVELS;
    }

    public void nextLevel() {
        levelOn++;
    }
    
    public static Brick[][] getLevel() {

        switch (levelOn) {
            case 1:
            default:
                return new Level1().getLevelArray();
            case 2:
                return new Level2().getLevelArray();
            case 3:
                return new Level3().getLevelArray();
            }

}
}
