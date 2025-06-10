import org.junit.Test;
import static org.junit.Assert.*;

public class ActionManagerEliminateTest {
    @Test
    public void testDoEliminateSingleRow() {
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

        // Fill second to last row completely
        for (int j = 1; j < width - 1; j++) {
            board[height - 2][j] = 1;
        }
        // Partially fill the row above so it should shift down
        for (int j = 2; j < width - 1; j++) {
            board[height - 3][j] = 2;
        }

        int[] expected = board[height - 3].clone();

        am.doEliminate();

        for (int j = 1; j < width - 1; j++) {
            assertEquals("Row shifted down", expected[j], board[height - 2][j]);
            assertEquals("Upper row cleared", 0, board[height - 3][j]);
        }
    }
}
