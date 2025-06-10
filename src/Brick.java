/**
 * Created by IntelliJ IDEA.
 * User: dsewnr
 * Date: 2011/1/5
 * Time: 下午 09:04:54
 * To change this template use File | Settings | File Templates.
 */
public class Brick {
	private int color;
	private int x;
	private int y;

	public Brick() {
		color = 0;
		x = 0;
		y = 0;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void copy(Brick b) {
		this.setX(b.getX());
		this.setY(b.getY());
		this.setColor(b.getColor());
	}
}
