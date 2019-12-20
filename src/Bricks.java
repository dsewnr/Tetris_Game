import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/18
 * Time: 上午 11:35:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class Bricks {
	Brick [] oBrick;

	public Bricks() {
		oBrick = new Brick [4];
		for( int i = 0; i < 4; i++ ) {
			oBrick[i] = new Brick();
		}
	}

	protected abstract void init();
	protected abstract void rotate();	

	protected void move(int offSetX, int offSetY) {
		for( int i = 0;i < 4; i++ ) {
			oBrick[i].setX(oBrick[i].getX()+offSetX);
			oBrick[i].setY(oBrick[i].getY()+offSetY);
		}
	}

	protected void copy( Bricks b ) {
		for( int i = 0; i < 4; i ++ ) {
			oBrick[i].setX(b.oBrick[i].getX());
			oBrick[i].setY(b.oBrick[i].getY());
			oBrick[i].setColor(b.oBrick[i].getColor());
		}
	}

	public Brick[] getoBrick() {
		return oBrick;
	}

	protected void setColor(int color) {
		for( int i = 0; i < 4; i ++ ) {
			oBrick[i].setColor(color);
		}
	}
}
