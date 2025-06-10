import org.junit.Test;
import static org.junit.Assert.*;

public class ActionManagerMoveTest {
    @Test
    public void testDoMoveLeftNoCollision() {
        GameSettings settings = new GameSettings();
        Panel panel = new Panel(settings);
        Block block = new Block(settings);
        // Force a known shape in a safe position
        block.currentBricks = new Shape_Square();
        block.currentBricks.init();
        ActionManager am = new ActionManager(null);
        am.setoGameSettings(settings);
        am.setoPanel(panel);
        am.setoBlock(block);

        int beforeX = block.getCurrentBricks().getoBrick()[0].getX();
        int beforeY = block.getCurrentBricks().getoBrick()[0].getY();

        am.doMove("LEFT");

        assertEquals(beforeX - 1, block.getCurrentBricks().getoBrick()[0].getX());
        assertEquals(beforeY, block.getCurrentBricks().getoBrick()[0].getY());
    }
}
