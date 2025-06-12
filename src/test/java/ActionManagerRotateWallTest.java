import org.junit.Test;
import static org.junit.Assert.*;

public class ActionManagerRotateWallTest {

    @Test
    public void testRotateNearWallRestoresPosition() {
        GameSettings settings = new GameSettings();
        Panel panel = new Panel(settings);
        Block block = new Block(settings);

        // Use a known shape so we can predict rotation
        block.currentBricks = new Shape_Line();
        block.currentBricks.init();

        // Move block to the left wall so rotation would go out of bounds
        int shift = block.getCurrentBricks().getoBrick()[0].getX() - 1;
        block.move(-shift, 0);

        ActionManager am = new ActionManager(null);
        am.setoGameSettings(settings);
        am.setoPanel(panel);
        am.setoBlock(block);

        int[] oldX = new int[4];
        int[] oldY = new int[4];
        for (int i = 0; i < 4; i++) {
            oldX[i] = block.getCurrentBricks().getoBrick()[i].getX();
            oldY[i] = block.getCurrentBricks().getoBrick()[i].getY();
        }

        am.doRotate();

        for (int i = 0; i < 4; i++) {
            assertEquals(oldX[i], block.getCurrentBricks().getoBrick()[i].getX());
            assertEquals(oldY[i], block.getCurrentBricks().getoBrick()[i].getY());
        }
    }
}

