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
	Map Shapes;
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
		Shapes = new HashMap<Integer,Bricks>();	
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
		currentBricks = (Bricks)Shapes.get(index);
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
		Shapes.put(0,oSquare);
		Shapes.put(1,oLine);
		Shapes.put(2,oRevT);
		Shapes.put(3,oZ);
		Shapes.put(4,oRevZ);
		Shapes.put(5,oL);
		Shapes.put(6,oRevL);
	}

	public void move(int offSetX, int offSetY) {
		currentBricks.move(offSetX, offSetY);
	}

	public void rotate() {
		currentBricks.rotate();	
	}


        public void draw(Graphics offScreen) {
                for( int k = 0;k < 4; k++ ) {
                        int colorCode = currentBricks.getoBrick()[k].getColor();
                        drawBrick(offScreen, k, colorForCode(colorCode));
                }
        }

        private Color colorForCode(int code) {
                switch(code) {
                        case 1: return Color.YELLOW;
                        case 2: return Color.RED;
                        case 3: return Color.CYAN;
                        case 4: return Color.GREEN;
                        case 5: return Color.BLUE;
                        case 6: return Color.MAGENTA;
                        case 7: return Color.PINK;
                        default: return Color.WHITE;
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
