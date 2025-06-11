/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/17
 * Time: 下午 05:05:36
 * To change this template use File | Settings | File Templates.
 */
public class Shape_Square extends Bricks  {
        public Shape_Square() {
                setColor(TetrominoColor.YELLOW);
        }

	protected void init() {
		oBrick[0].setX(6);
		oBrick[0].setY(0);
		oBrick[1].setX(7);
		oBrick[1].setY(0);
		oBrick[2].setX(6);
		oBrick[2].setY(1);
		oBrick[3].setX(7);
		oBrick[3].setY(1);
	}

	protected void rotate() {

	}
}
