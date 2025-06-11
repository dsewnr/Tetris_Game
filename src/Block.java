import java.awt.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/18
 * Time: 上午 11:47:33
 * To change this template use File | Settings | File Templates.
 */
public class Block {
	int index;
	Bricks currentBricks;
        Map<Integer, Bricks> shapes;
	Shape_Square oSquare;
	Shape_Line oLine;
	Shape_RevT oRevT;
	Shape_Z oZ;
	Shape_RevZ oRevZ;
	Shape_L oL;
	Shape_RevL oRevL;

	private GameSettings oGameSettings;

	public Block(GameSettings gameSettings) {
		oGameSettings = gameSettings;
                shapes = new HashMap<Integer,Bricks>();
		initShapes();
		addShapes();
		reset();
	}

	public void reset() {
		initIndex();
		initCurrentBricks();
	}

	private void initIndex() {
		index = (int)(Math.random()*7);
		/* index = 1; */
	}

	private void initCurrentBricks() {
                currentBricks = shapes.get(index);
		currentBricks.init();
	}

	private void initShapes() {
		oSquare = new Shape_Square();
		oLine = new Shape_Line();
		oRevT = new Shape_RevT();
		oZ = new Shape_Z();
		oRevZ = new Shape_RevZ();
		oL = new Shape_L();
		oRevL = new Shape_RevL();
	}

	private void addShapes() {
                shapes.put(0,oSquare);
                shapes.put(1,oLine);
                shapes.put(2,oRevT);
                shapes.put(3,oZ);
                shapes.put(4,oRevZ);
                shapes.put(5,oL);
                shapes.put(6,oRevL);
	}

	public void move(int offSetX, int offSetY) {
		currentBricks.move(offSetX, offSetY);
	}

	public void rotate() {
		currentBricks.rotate();	
	}


	public void draw(Graphics offScreen) {
		for( int k = 0;k < 4; k++ ) {
			drawBrick(offScreen, k, Color.WHITE);
		}
	}

	private void drawBrick(Graphics g, int k, Color c) {
		g.setColor(c);
		g.drawRect(currentBricks.getoBrick()[k].getX()*oGameSettings.getBrickSize(), currentBricks.getoBrick()[k].getY()*oGameSettings.getBrickSize(), oGameSettings.getBrickSize(), oGameSettings.getBrickSize());
		g.fillRect(currentBricks.getoBrick()[k].getX()*oGameSettings.getBrickSize()+2, currentBricks.getoBrick()[k].getY()*oGameSettings.getBrickSize()+2, oGameSettings.getBrickSize()-3, oGameSettings.getBrickSize()-3);
	}

	public Bricks getCurrentBricks() {
		return currentBricks;
	}	
}
