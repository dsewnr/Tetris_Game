import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: dsewnr
 * Date: 2011/1/5
 * Time: 下午 09:04:25
 * To change this template use File | ActionManager | File Templates.
 */
public class Player extends ActionManager {
	private int id;
	private InputManager oInputManager;

	private GameSettings oGameSettings;
	private Panel oPanel;
	private Block oBlock;
	
	private Graphics offScreen;

	public Player(int playerID, Tetris t) {
		super(t);
		id = playerID;
		oGameSettings = new GameSettings();
		super.setoGameSettings(oGameSettings);
		oPanel = new Panel(oGameSettings);
		super.setoPanel(oPanel);
		oBlock = new Block(oGameSettings);
		super.setoBlock(oBlock);		
		oInputManager = new InputManager(this);		
	}

	private void cleanBuffer() {
		offScreen.setColor(Color.BLACK);
		offScreen.fillRect(0, 0, getoPanel().getWidth(), getoPanel().getHeight());
	}

	public void draw() {
		cleanBuffer();
		oPanel.draw(offScreen);
		oBlock.draw(offScreen);
	}

	public InputManager getKeyHandler(){
		return oInputManager;
	}

	public int getId() {
		return id;
	}

	public Block getoBlock() {
		return oBlock;
	}

	public Panel getoPanel() {
		return oPanel;
	}

	public GameSettings getoGameSettings() {
		return oGameSettings;
	}	

	public void setOffScreen(Graphics offScreen) {
		this.offScreen = offScreen;		
	}

	public boolean isGameRunning() {
		return oGameSettings.isGameIsRunning();
	}
}
