import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: dsewnr
 * Date: 2011/1/5
 * Time: 下午 08:16:49
 * To change this template use File | Settings | File Templates.
 */
public class Tetris extends JFrame {
	private Player oPlayer;	

	private Container oContainer;
	
	private Graphics offScreen;
	private Image bufferScreen;

	public Tetris() {
		super("Tetris");
		setProgram();
		setGUI();
		setGame();
		drawWorld();
	}
	
	public void run() {	
		while(oPlayer.isGameRunning()) {
			try {
				oPlayer.doMove("DOWN");
				drawWorld();
				Thread.sleep(350);
			} catch(InterruptedException e) {
			}
		}
		stop();		
	}

	public static void main(String[] args) {		
		new Tetris().run();
	}

	private void setProgram() {
		oPlayer = new Player(1, this);
	}

	private void setGUI() {
		oContainer = getContentPane();

		oContainer.add(oPlayer.getoPanel());
		setSize(oPlayer.getoGameSettings().getGameWidth()*oPlayer.getoGameSettings().getBrickSize()+8, oPlayer.getoGameSettings().getGameHeight()*oPlayer.getoGameSettings().getBrickSize()+29);
		setLocation(getToolkit().getScreenSize().width/2 - getSize().width/2, getToolkit().getScreenSize().height/2 - getSize().height/2);
		//pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);		
		setFocusable(true);		
	}

	private void setGame() {
		setBuffer();
		requestFocus();
		addKeyListener(oPlayer.getKeyHandler());
	}

	private void setBuffer() {
		bufferScreen = createImage(oPlayer.getoPanel().getWidth()+1, oPlayer.getoPanel().getHeight()+1);
		offScreen = bufferScreen.getGraphics();
		oPlayer.setOffScreen(offScreen);		
	}

	public void drawWorld() {
		oPlayer.draw();
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawImage(bufferScreen, 3, 25, this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	public void stop() {
		Thread.interrupted();		
	}
}
