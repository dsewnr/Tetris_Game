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
                                        drawBrick(offScreen, i, j, colorForCode(arrBoard[i][j]));
                                }

				/*switch( arrBoard[i][j] ) {
					case -1:
						drawBrick(offScreen, i, j, Color.GRAY);
						break;
					case 0:
						break;
					case 1:
						drawBrick(offScreen, i, j, Color.YELLOW);
						break;
					case 2:
						drawBrick(offScreen, i, j, Color.RED);
						break;
					case 3:
						drawBrick(offScreen, i, j, Color.CYAN);
						break;
					case 4:
						drawBrick(offScreen, i, j, Color.GREEN);
						break;
					case 5:
						drawBrick(offScreen, i, j, Color.BLUE);
						break;
					case 6:
						drawBrick(offScreen, i, j, Color.MAGENTA);
						break;
					case 7:
						drawBrick(offScreen, i, j, Color.PINK);
						break;
					default:
						drawBrick(offScreen, i, j, Color.LIGHT_GRAY);
						break;
				}*/
			}
		}		
	}

        private void drawBrick(Graphics g, int i, int j, Color c) {
                g.setColor(c);
                g.drawRect(j*oGameSettings.getBrickSize(), i*oGameSettings.getBrickSize(), oGameSettings.getBrickSize(), oGameSettings.getBrickSize());
                g.fillRect(j*oGameSettings.getBrickSize()+2, i*oGameSettings.getBrickSize()+2, oGameSettings.getBrickSize()-3, oGameSettings.getBrickSize()-3);
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

	public int[][] getArrBoard() {
		return arrBoard;
	}

	public void setArrBoard(int[][] arrBoard) {
		this.arrBoard = arrBoard;
	}
}
