import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: dsewnr
 * Date: 2011/1/5
 * Time: 下午 09:05:00
 * To change this template use File | ActionManager | File Templates.
 */
public class Block_backup {
	int index;
	int [][][] maskMap;
	Brick [][] blockMap;
	Brick [] currentBlock;
	private GameSettings oGameSettings;	

	public Block_backup(GameSettings gSettings) {
		oGameSettings = gSettings;
		initIndex();		
		initBlockMap();
		initCurrentBlock();
	}

	private void initIndex() {
		index = (int)(Math.random()*7);
	}

	public void reset() {
		initIndex();
		initCurrentBlock();
	}

	public void initCurrentBlock() {
		currentBlock = new Brick[4];
		for( int k = 0;k < 4; k++ ) {
			currentBlock[k] = new Brick();
			currentBlock[k].copy(blockMap[index][k]);
		}
	}

	private void initBlockMap() {
		blockMap = new Brick[7][4];
		for( int i = 0;i < 7; i ++ ) {
			for( int j = 0; j < 4; j++ ) {
				blockMap[i][j] = new Brick();
				blockMap[i][j].setColor(i+1);
			}
		}

		// ■■
		// ■■
		blockMap[0][0].setX(6);
		blockMap[0][0].setY(0);
		blockMap[0][1].setX(7);
		blockMap[0][1].setY(0);
		blockMap[0][2].setX(6);
		blockMap[0][2].setY(1);
		blockMap[0][3].setX(7);
		blockMap[0][3].setY(1);
		// ■■■■
		blockMap[1][0].setX(5);
		blockMap[1][0].setY(0);
		blockMap[1][1].setX(6);
		blockMap[1][1].setY(0);
		blockMap[1][2].setX(7);
		blockMap[1][2].setY(0);
		blockMap[1][3].setX(8);
		blockMap[1][3].setY(0);
		//   ■
		// ■■■
		blockMap[2][0].setX(6);
		blockMap[2][0].setY(0);
		blockMap[2][1].setX(5);
		blockMap[2][1].setY(1);
		blockMap[2][2].setX(6);
		blockMap[2][2].setY(1);
		blockMap[2][3].setX(7);
		blockMap[2][3].setY(1);
		// ■
		// ■■
		//   ■
		blockMap[3][0].setX(6);
		blockMap[3][0].setY(0);
		blockMap[3][1].setX(6);
		blockMap[3][1].setY(1);
		blockMap[3][2].setX(7);
		blockMap[3][2].setY(1);
		blockMap[3][3].setX(7);
		blockMap[3][3].setY(2);
		//   ■
		// ■■
		// ■
		blockMap[4][0].setX(7);
		blockMap[4][0].setY(0);
		blockMap[4][1].setX(6);
		blockMap[4][1].setY(1);
		blockMap[4][2].setX(7);
		blockMap[4][2].setY(1);
		blockMap[4][3].setX(6);
		blockMap[4][3].setY(2);
		// ■
		// ■■■
		blockMap[5][0].setX(5);
		blockMap[5][0].setY(0);
		blockMap[5][1].setX(5);
		blockMap[5][1].setY(1);
		blockMap[5][2].setX(6);
		blockMap[5][2].setY(1);
		blockMap[5][3].setX(7);
		blockMap[5][3].setY(1);
		//     ■
		// ■■■
		blockMap[6][0].setX(7);
		blockMap[6][0].setY(0);
		blockMap[6][1].setX(5);
		blockMap[6][1].setY(1);
		blockMap[6][2].setX(6);
		blockMap[6][2].setY(1);
		blockMap[6][3].setX(7);
		blockMap[6][3].setY(1);
	}

	private void swapXY( Brick toSwap ) {
		int tmp;
		tmp = toSwap.getX();
		toSwap.setX(toSwap.getY());
		toSwap.setY(tmp);		
	}

	public void rotate() {
		// clockwise
		// anticlockwise
		switch(index) {
			case 0:
				break;
			case 1:
				for( int i = 0; i < 4; i++ ) {
					swapXY(currentBlock[i]);
					currentBlock[i].setX(currentBlock[i].getX()-1);
				}
				break;
			default:
                                System.out.println("Unknown how to rotate.");
				break;
		}
	}

	public void draw(Graphics offScreen) {				
		for( int k = 0;k < 4; k++ ) {
			drawBrick(offScreen, k, Color.WHITE);
		}		
	}

	private void drawBrick(Graphics g, int k, Color c) {
		g.setColor(Color.WHITE);
		g.drawRect(currentBlock[k].getX()*oGameSettings.getBrickSize(), currentBlock[k].getY()*oGameSettings.getBrickSize(), oGameSettings.getBrickSize(), oGameSettings.getBrickSize());
		g.fillRect(currentBlock[k].getX()*oGameSettings.getBrickSize()+2, currentBlock[k].getY()*oGameSettings.getBrickSize()+2, oGameSettings.getBrickSize()-3, oGameSettings.getBrickSize()-3);
	}

	public void move(int offSetX, int offSetY) {
		for( int k = 0;k < 4; k++ ) {
			currentBlock[k].setX(currentBlock[k].getX()+offSetX);
			currentBlock[k].setY(currentBlock[k].getY()+offSetY);
		}
	}

	public Brick[] getCurrentBlock() {
		return currentBlock;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
