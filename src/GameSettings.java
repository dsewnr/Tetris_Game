/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 2011/1/6
 * Time: 下午 01:45:34
 * To change this template use File | Settings | File Templates.
 */
public class GameSettings {
	private int brickSize;
	private int gameSpeed;
	private int gameWidth;
	private int gameHeight;
        private boolean gameIsRunning;
        private int score;

	public GameSettings() {
		brickSize = 20;
		gameSpeed = 500;
		gameWidth = 14;
		gameHeight = 20;
                gameIsRunning = true;
                score = 0;
        }

	public int getBrickSize() {
		return brickSize;
	}

	public void setBrickSize(int brickSize) {
		this.brickSize = brickSize;
	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}

        public boolean isGameIsRunning() {
                return gameIsRunning;
        }

        public void setGameIsRunning(boolean gameIsRunning) {
                this.gameIsRunning = gameIsRunning;
        }

        public int getScore() {
                return score;
        }

        public void setScore(int score) {
                this.score = score;
        }

        public void addScore(int delta) {
                this.score += delta;
        }
}
