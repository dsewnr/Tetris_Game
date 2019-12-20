/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/19
 * Time: 下午 01:17:59
 * To change this template use File | Settings | File Templates.
 */
public class Shape_RevZ extends Bricks {
	public Shape_RevZ() {
		setColor(5);
	}

	protected void init() {
		oBrick[0].setX(6);
		oBrick[0].setY(0);
		oBrick[1].setX(6);
		oBrick[1].setY(1);
		oBrick[2].setX(7);
		oBrick[2].setY(1);
		oBrick[3].setX(7);
		oBrick[3].setY(2);
	}

	protected void rotate() {

	}
}
