import org.junit.Test;
import static org.junit.Assert.*;

public class ActionManagerScoreTest {
    @Test
    public void testScoreIncreaseOnEliminate() {
        GameSettings settings = new GameSettings();
        Panel panel = new Panel(settings);
        Block block = new Block(settings);
        ActionManager am = new ActionManager(null);
        am.setoGameSettings(settings);
        am.setoPanel(panel);
        am.setoBlock(block);

        int width = settings.getGameWidth();
        int height = settings.getGameHeight();
        int[][] board = panel.getArrBoard();

        // Fill two complete rows
        for (int j = 1; j < width - 1; j++) {
            board[height - 2][j] = 1;
            board[height - 3][j] = 1;
        }

        am.doEliminate();

        assertEquals(200, settings.getScore());
    }
}
