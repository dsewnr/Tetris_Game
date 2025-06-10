import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by IntelliJ IDEA.
 * User: dsewnr
 * Date: 2011/1/5
 * Time: 下午 08:23:49
 * To change this template use File | Settings | File Templates.
 */
public class InputManager implements KeyListener, Runnable {
	private int downKeyCode;
	private int leftKeyCode;
	private int rightKeyCode;
	private int rotateKeyCode;
	private ActionManager oActionManager;


	public InputManager(ActionManager actionmanager) {
		oActionManager = actionmanager;
		downKeyCode = oActionManager.getDownKey();
		leftKeyCode = oActionManager.getLeftKey();
		rightKeyCode = oActionManager.getRightKey();
		rotateKeyCode = oActionManager.getRotateKey();
		new Thread(this).start();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if( keyCode == downKeyCode){
			oActionManager.setDownFlag(true);
		} else if( keyCode == leftKeyCode ) {
			oActionManager.setLeftFlag(true);
		} else if( keyCode == rightKeyCode ) {
			oActionManager.setRightFlag(true);
		} else if( keyCode == rotateKeyCode ) {
			oActionManager.setRotateFlag(true);
		} else {
			
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if( keyCode == downKeyCode){
			oActionManager.setDownFlag(false);
		} else if( keyCode == leftKeyCode ) {
			oActionManager.setLeftFlag(false);
		} else if( keyCode == rightKeyCode ) {
			oActionManager.setRightFlag(false);
		} else if( keyCode == rotateKeyCode ) {
			oActionManager.setRotateFlag(false);
		} else {

		}
	}

	public void run(){
		boolean flag = false;
		while(oActionManager.getoGameSettings().isGameIsRunning()) {
			try {
				oActionManager.processDirectionInput();				
				Thread.sleep(50);
				if(flag) {
					oActionManager.processRotateInput();
					flag = false;
				} else {
					flag = true;
				}
			} catch(InterruptedException e) {
			}
		}
		stop();
	}

	public void stop() {
		Thread.interrupted();
	}
}
