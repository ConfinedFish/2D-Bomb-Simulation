import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
enum CharacterType {B,R,G}
public class Main {
	public static Character bomb, robot, gold1, gold2;
	private static Random rand = new Random();
	private static boolean cont = true;
	private static ArrayList<Integer> usedX = new ArrayList<>();
	private static ArrayList<Integer> usedY = new ArrayList<>();
	private static Board board = new Board(4,4,"[ ]");
	public static void main(String [] args) throws InterruptedException, IOException{
		setRandomSpawns(board);
		board.print();
		while (cont) {
			if(!gold1.enabled && !gold2.enabled) {
				cont = false;
				println("The robot has collected all the gold");
				break;
			}
			for(int i = 0; i < 2; i++) {
				robot.Move(rand, board);
				board.print();
			}
			if(!gold1.enabled && !gold2.enabled) {
				cont = false;
				println("The robot has collected all the gold");
				break;
			}
			bomb.Move(rand, board);
			board.print();
			
		}
	}
	private static void setRandomSpawns(Board board) {
		for (int i = 1; i <= 4; i++) {
			int randomX = rand.nextInt(4)+1;
			int randomY = rand.nextInt(4)+1;
			while(usedX.contains(randomX)) {
				randomX = rand.nextInt(4) + 1;
			}
			usedX.add(randomX);
			while(usedY.contains(randomY)) {
				randomY = rand.nextInt(4)+1;
			}
			usedY.add(randomY);
			switch(i) {
				case(1):
					robot = new Character(randomX, randomY, CharacterType.R);
					robot.setX(randomX);
					robot.setY(randomY);
					board.setPoint(randomX, randomY, "[R]");
					break;
				case(2):
					bomb = new Character(randomX, randomY, CharacterType.B);
					bomb.setX(randomX);
					bomb.setY(randomY);
					board.setPoint(randomX, randomY, "[B]");
					break;
				case(3):
					gold1 = new Character(randomX, randomY, CharacterType.G);
					gold1.setX(randomX);
					gold1.setY(randomY);
					board.setPoint(randomX, randomY, "[G]");
					break;
				case(4):
					gold2 = new Character(randomX, randomY, CharacterType.G);
					gold2.setX(randomX);
					gold2.setY(randomY);
					board.setPoint(randomX, randomY, "[G]");
					break;
			}
				
		}
	}
	public static int ensureRange(int value, int min, int max) {
		if(value < min) {
			return min;
		}else if(value > max) {
			return max;
		}else {
			return value;
		}
	}

	public static void println(Object obj) {
		System.out.println(obj);
	}
}

