import java.io.IOException;

public class Board {
	private String[][] board;
	int row, col;
	Board(int row, int col, String fill) {
		this.row = row;
		this.col = col;
		board = new String[row][col];
		fill(fill);
	}
	private void fill(String cs) {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = cs;
			}
		}
	}
	public void setPoint(int x, int y, String character) {
		if (x <= 0 || y <= 0) {
			print(x + " " + y);
			System.exit(0);
		}
		try {
			board[x-1][y-1] = character;
		} catch (ArrayIndexOutOfBoundsException e) {
			print(x + " " + y);
			System.exit(0);
		}
	}

	public void print() throws InterruptedException {
		Thread.sleep(1000);
		for (int j = 0; j < this.board[0].length; j++) {
			for (int i = 0; i < this.board.length; i++) {
				print(this.board[i][j]);
				if (i == board.length - 1) {
					println("");
				}
			}
		}
		println("____________");
	}
	private static void print(Object obj) {
		System.out.print(obj);
	}
	private static void println(Object obj) {
		System.out.println(obj);
	}
}
