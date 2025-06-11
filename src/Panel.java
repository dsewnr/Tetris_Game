import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
	private int boardWidth;
	private int boardHeight;
	private int [][] arrBoard;
	private Dimension dim;
	private GameSettings oGameSettings;


	public Panel(GameSettings gSettings)
	{
		oGameSettings = gSettings;
	 	boardWidth = oGameSettings.getGameWidth();
		boardHeight = oGameSettings.getGameHeight();
		arrBoard = new int [boardHeight][boardWidth];
		dim = new Dimension(boardWidth*oGameSettings.getBrickSize(), boardHeight*oGameSettings.getBrickSize());
		setGUI();
		initBoard();	
	}

	private void setGUI() {
		setPreferredSize(dim);
		setBackground(Color.BLACK);
	}

	private void initBoard() {
		for( int i = 0; i < boardHeight; i++ ) {
			for( int j = 0; j < boardWidth; j++ ) {
				if( i == boardHeight-1 || j == 0 || j == boardWidth-1 ) {
					arrBoard[i][j] = -1;
				}
			}
		}
	}

	public void draw(Graphics offScreen) {
                for( int i = 0; i < boardHeight; i++ ) {
                        for( int j = 0; j < boardWidth; j++ ) {
                                if( arrBoard[i][j] == -1 ) {
                                        drawBrick(offScreen, i, j, Color.GRAY);
                                } else if( arrBoard[i][j] != 0 ) {
                                        drawBrick(offScreen, i, j, mapColor(arrBoard[i][j]));
                                }
                        }
                }
        }

        private void drawBrick(Graphics g, int i, int j, Color c) {
                g.setColor(c);
                g.drawRect(j*oGameSettings.getBrickSize(), i*oGameSettings.getBrickSize(), oGameSettings.getBrickSize(), oGameSettings.getBrickSize());
                g.fillRect(j*oGameSettings.getBrickSize()+2, i*oGameSettings.getBrickSize()+2, oGameSettings.getBrickSize()-3, oGameSettings.getBrickSize()-3);
        }

        private Color mapColor(int color) {
                switch (color) {
                        case TetrominoColor.YELLOW:
                                return Color.YELLOW;
                        case TetrominoColor.RED:
                                return Color.RED;
                        case TetrominoColor.CYAN:
                                return Color.CYAN;
                        case TetrominoColor.GREEN:
                                return Color.GREEN;
                        case TetrominoColor.BLUE:
                                return Color.BLUE;
                        case TetrominoColor.MAGENTA:
                                return Color.MAGENTA;
                        case TetrominoColor.PINK:
                                return Color.PINK;
                        default:
                                return Color.LIGHT_GRAY;
                }
        }

	public int[][] getArrBoard() {
		return arrBoard;
	}

	public void setArrBoard(int[][] arrBoard) {
		this.arrBoard = arrBoard;
	}
}
