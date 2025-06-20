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
                        doMove(Direction.DOWN);
                        topFrame.drawWorld();
                }
                if( isLeftFlag() ) {
                        doMove(Direction.LEFT);
                        topFrame.drawWorld();
                }
                if( isRightFlag() ) {
                        doMove(Direction.RIGHT);
                        topFrame.drawWorld();
                }
        }

	public void processRotateInput() {
		if( isRotateFlag() ) {
			doRotate();
			topFrame.drawWorld();
		}	
	}

        synchronized public void doMove(Direction direction) {
                int offSetX = 0;
                int offSetY = 0;

                if( direction == Direction.DOWN ) {
                        offSetY++;
                } else if( direction == Direction.LEFT ) {
                        offSetX--;
                } else if( direction == Direction.RIGHT ) {
                        offSetX++;
                }

		oBlock.move(offSetX, offSetY);

                if(isCollision()) {
                        oBlock.move(-offSetX, -offSetY);
                        if( direction == Direction.DOWN ) {
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
                Brick[] bricks = oBlock.getCurrentBricks().getoBrick();
                int[] oldX = new int[4];
                int[] oldY = new int[4];
                for (int i = 0; i < 4; i++) {
                        oldX[i] = bricks[i].getX();
                        oldY[i] = bricks[i].getY();
                }

                oBlock.rotate();

                int width = oGameSettings.getGameWidth();
                int height = oGameSettings.getGameHeight();
                boolean outOfRange = false;
                for (int i = 0; i < 4; i++) {
                        int x = bricks[i].getX();
                        int y = bricks[i].getY();
                        if (x < 0 || x >= width || y < 0 || y >= height) {
                                outOfRange = true;
                                break;
                        }
                }

                if (outOfRange || isCollision()) {
                        for (int i = 0; i < 4; i++) {
                                bricks[i].setX(oldX[i]);
                                bricks[i].setY(oldY[i]);
                        }
                }
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
			}
		}

                int counter = 0;
                int linesCleared = 0;
                for( int i = bHeight-2; i >=0 ; i-- ) {
                        if(fillFlag[i] == false) {
                                for( int j = 1; j < bWidth-1; j++ ) {
                                        oPanel.getArrBoard()[bHeight-2-counter][j] = tmpArrBoard[i][j];
                                }
                                counter++;
                        } else {
                                linesCleared++;
                        }
                }

                if(linesCleared > 0) {
                        oGameSettings.setScore(oGameSettings.getScore() + linesCleared * 100);
                        if (topFrame != null) {
                                topFrame.updateScore();
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
