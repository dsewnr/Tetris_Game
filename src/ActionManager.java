import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: dsewnr
 * Date: 2011/1/5
 * Time: 下午 08:22:05
 * To change this template use File | Settings | File Templates.
 */
public class ActionManager {
	private int downKey;
	private int leftKey;
	private int rightKey;
	private int rotateKey;

	private boolean downFlag;
	private boolean leftFlag;
	private boolean rightFlag;
	private boolean rotateFlag;

	private GameSettings oGameSettings;
	private Block oBlock;
	private Panel oPanel;
	private Tetris topFrame;

	public ActionManager(Tetris t){
		topFrame = t;
		downKey = KeyEvent.VK_DOWN;
		leftKey = KeyEvent.VK_LEFT;
		rightKey = KeyEvent.VK_RIGHT;
		rotateKey = KeyEvent.VK_SPACE;
		
		downFlag = leftFlag = rightFlag = rotateFlag = false;
	}

	public int getDownKey() {
		return downKey;
	}

	public void setDownKey(int downKey) {
		this.downKey = downKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getRotateKey() {
		return rotateKey;
	}

	public void setRotateKey(int rotateKey) {
		this.rotateKey = rotateKey;
	}

	public boolean isDownFlag() {
		return downFlag;
	}

	public void setDownFlag(boolean downFlag) {
		this.downFlag = downFlag;
	}

	public boolean isLeftFlag() {
		return leftFlag;
	}

	public void setLeftFlag(boolean leftFlag) {
		this.leftFlag = leftFlag;
	}

	public boolean isRightFlag() {
		return rightFlag;
	}

	public void setRightFlag(boolean rightFlag) {
		this.rightFlag = rightFlag;
	}

	public boolean isRotateFlag() {
		return rotateFlag;
	}

	public void setRotateFlag(boolean rotateFlag) {
		this.rotateFlag = rotateFlag;
	}

	public void processDirectionInput() {
		if( isDownFlag() ) {
			doMove("DOWN");
			topFrame.drawWorld();
		}
		if( isLeftFlag() ) {
			doMove("LEFT");
			topFrame.drawWorld();
		}
		if( isRightFlag() ) {
			doMove("RIGHT");
			topFrame.drawWorld();
		}
	}

	public void processRotateInput() {
		if( isRotateFlag() ) {
			doRotate();
			topFrame.drawWorld();
		}	
	}

	synchronized public void doMove(String direction) {
		int offSetX = 0;
		int offSetY = 0;

                if( "DOWN".equals(direction) ) {
                        offSetY++;
                } else if( "LEFT".equals(direction) ) {
                        offSetX--;
                } else if( "RIGHT".equals(direction) ) {
                        offSetX++;
                }

		oBlock.move(offSetX, offSetY);

                if(isCollision()) {
                        oBlock.move(-offSetX, -offSetY);
                        if( "DOWN".equals(direction) ) {
                                doFreeze();
                                doEliminate();
                                topFrame.repaint();
                                oBlock.reset();
                                topFrame.repaint();
				if(isCollision()) {
					oGameSettings.setGameIsRunning(false);
					System.out.println("Game Over");
				}
			}
		}
	}

	synchronized public void doRotate() {
		oBlock.rotate();
	}

	synchronized public void doFreeze() {
		for( int i = 0; i < 4; i++ ) {			
			oPanel.getArrBoard()[oBlock.getCurrentBricks().getoBrick()[i].getY()][oBlock.getCurrentBricks().getoBrick()[i].getX()] = oBlock.getCurrentBricks().getoBrick()[i].getColor();
		}
	}

        synchronized public void doEliminate() {
                int bWidth = oGameSettings.getGameWidth();
                int bHeight = oGameSettings.getGameHeight();
                int [][] tmpArrBoard = new int [bHeight][bWidth];
                boolean [] fillFlag = new boolean [bHeight-1];
                int linesRemoved = 0;

		for( int i = 0; i < bHeight-1; i++ ) {
			boolean isFill = true;
			for( int j = 1; j < bWidth-1; j++ ) {
				if(oPanel.getArrBoard()[i][j] == 0) {
					isFill = false;					
				}
				tmpArrBoard[i][j] = oPanel.getArrBoard()[i][j];
			}
                        if(isFill) {
                                fillFlag[i] = true;
                                linesRemoved++;
                        }
                }

		int counter = 0;
                for( int i = bHeight-2; i >=0 ; i-- ) {
                        if(fillFlag[i] == false) {
                                for( int j = 1; j < bWidth-1; j++ ) {
                                        oPanel.getArrBoard()[bHeight-2-counter][j] = tmpArrBoard[i][j];
                                }
                                counter++;
                        }
                }

                if(linesRemoved > 0) {
                        oGameSettings.addScore(linesRemoved * 100);
                        if(topFrame != null) {
                                topFrame.updateScore(oGameSettings.getScore());
                        }
                }
        }
		
	public boolean isCollision() {
		for( int i = 0; i < 4; i++ ) {
			if( oPanel.getArrBoard()[oBlock.getCurrentBricks().getoBrick()[i].getY()][oBlock.getCurrentBricks().getoBrick()[i].getX()] != 0 ) {
				return true;
			}
		}
		return false;
	}

	public void setoGameSettings(GameSettings oGameSettings) {
		this.oGameSettings = oGameSettings;
	}

	public GameSettings getoGameSettings() {
		return oGameSettings;
	}

	public void setoBlock(Block oBlock) {
		this.oBlock = oBlock;
	}

	public void setoPanel(Panel oPanel) {
		this.oPanel = oPanel;
	}
}
