import java.io.IOException;
import java.util.Random;

public class Character {
	private int x, y;
	boolean enabled = true;
	private CharacterType type;
	Character(int x, int y, CharacterType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	public CharacterType getType() {
		return type;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void Move(Random rand, Board board) throws InterruptedException, IOException {
		int oldx = x;
		int oldy = y;
		int randommove = rand.nextInt(8) + 1;
			switch(randommove) {
			case(1):
				board.setPoint(x, y, "[ ]");
				y = checkForColision(x , Main.ensureRange(y+1, 1, board.col), board) ? y : Main.ensureRange(y+1, 1, board.col);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(2):
				board.setPoint(x, y, "[ ]");
				boolean collision = checkForColision(Main.ensureRange(x+1, 1, board.row), Main.ensureRange(y+1, 1, board.col), board);
				y = collision ? y : Main.ensureRange(y+1, 1, board.col);
				x = collision ? x : Main.ensureRange(x+1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(3):
				board.setPoint(x, y, "[ ]");
				x = checkForColision(Main.ensureRange(x+1, 1, board.row), y, board) ? x : Main.ensureRange(x+1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(4):
				board.setPoint(x, y, "[ ]");
				boolean collision2 = checkForColision(Main.ensureRange(x+1, 1, board.row), Main.ensureRange(y-1, 1, board.col), board);
				y = collision2 ? y : Main.ensureRange(y-1, 1, board.col);
				x = collision2 ? x : Main.ensureRange(x+1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(5):
				board.setPoint(x, y, "[ ]");
				x = checkForColision(Main.ensureRange(x-1, 1, board.row), y, board) ? x : Main.ensureRange(x-1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(6):
				board.setPoint(x, y, "[ ]");
				boolean colliosion3 = checkForColision(Main.ensureRange(x-1, 1, board.row), Main.ensureRange(y-1, 1, board.col), board);
				y = colliosion3 ? y : Main.ensureRange(y-1, 1, board.col);
				x = colliosion3 ? x : Main.ensureRange(x-1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(7):
				board.setPoint(x, y, "[ ]");
				x = checkForColision(Main.ensureRange(x-1, 1, board.row), y, board) ? x : Main.ensureRange(x-1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
			case(8):
				board.setPoint(x, y, "[ ]");
				boolean collision4 = checkForColision(Main.ensureRange(x-1, 1, board.row) , Main.ensureRange(y+1, 1, board.col), board);
				y = collision4 ? y : Main.ensureRange(y+1, 1, board.col);
				x = collision4 ? x : Main.ensureRange(x-1, 1, board.row);
				board.setPoint(x, y, "[" + type.name() + "]");
				break;
		}
		if(oldx == x && oldy == y) {
			Move(rand, board);
        }
	}
	private boolean checkForColision(int x, int y, Board board) throws InterruptedException, IOException {
		if(Main.robot.x == x && Main.robot.y == y) {
			if(type == CharacterType.B) {
				Main.println("The bomb Reached the robot");
				board.print();
				System.exit(0);
			}
			return true;
		}else if(Main.bomb.x == x && Main.bomb.y == y) {
			if(type == CharacterType.R) {
				Main.println("The robot will reach the bomb. Finding new path");
				return true;
			}
			return true;
		}else if((Main.gold1.x == x && Main.gold1.y == y) && Main.gold1.enabled) {
			if(type == CharacterType.R) {
				Main.println("The robot reached a gold");
				board.print();
				Main.gold1.enabled = false;
				return false;
			}
			return true;
		}else if((Main.gold2.x == x && Main.gold2.y == y) && Main.gold2.enabled) {
			if(type == CharacterType.R) {
				Main.println("The robot reached a gold");
				board.print();
				Main.gold2.enabled = false;
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
}
