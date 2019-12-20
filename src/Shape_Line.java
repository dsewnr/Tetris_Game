/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/17
 * Time: 下午 05:05:56
 * To change this template use File | Settings | File Templates.
 */
public class Shape_Line extends Bricks {
	boolean isFlip;
	public Shape_Line() {
		setColor(2);
	}

	protected void init() {
		oBrick[0].setX(5);
		oBrick[0].setY(0);
		oBrick[1].setX(6);
		oBrick[1].setY(0);
		oBrick[2].setX(7);
		oBrick[2].setY(0);
		oBrick[3].setX(8);
		oBrick[3].setY(0);
		isFlip = true;
		oBrick[0].setColor(2);
		oBrick[1].setColor(3);
		oBrick[2].setColor(4);
		oBrick[3].setColor(5);

	}

	protected void rotate() {
		if(isFlip) {
			oBrick[0].setX(oBrick[0].getX()+1);
			oBrick[0].setY(oBrick[0].getY()-1);
			oBrick[2].setX(oBrick[2].getX()-1);
			oBrick[2].setY(oBrick[2].getY()+1);
			oBrick[3].setX(oBrick[3].getX()-2);
			oBrick[3].setY(oBrick[3].getY()+2);
			isFlip = false;
		} else {
			oBrick[0].setX(oBrick[0].getX()-1);
			oBrick[0].setY(oBrick[0].getY()+1);
			oBrick[2].setX(oBrick[2].getX()+1);
			oBrick[2].setY(oBrick[2].getY()-1);
			oBrick[3].setX(oBrick[3].getX()+2);
			oBrick[3].setY(oBrick[3].getY()-2);
			isFlip = true;
		}
	}
}
