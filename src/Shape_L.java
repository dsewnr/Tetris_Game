/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/19
 * Time: 下午 01:18:05
 * To change this template use File | Settings | File Templates.
 */
public class Shape_L extends Bricks {
	public Shape_L() {
		setColor(6);
	}

	protected void init() {
		oBrick[0].setX(5);
		oBrick[0].setY(0);
		oBrick[1].setX(5);
		oBrick[1].setY(1);
		oBrick[2].setX(6);
		oBrick[2].setY(1);
		oBrick[3].setX(7);
		oBrick[3].setY(1);		
	}

	protected void rotate() {

	}		
}
