package objects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class EnemyShip extends Spaceship {
	
	static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	private int[] topLeftCorner = new int[2];
	private int length;
	private int height;
	
	public int[] getTopLeftCorner() {
		return topLeftCorner;
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public EnemyShip(int[] topLeftCorner, int length, int height, int lifes, boolean operating) {
		
		super(topLeftCorner, length, height, lifes, true);
		
		this.topLeftCorner = topLeftCorner;
		this.length = length;
		this.height = height;
		
	}
	
	public static void spawnShip(EnemyShip ufo){
		
		for(int x=ufo.topLeftCorner[0]; x<ufo.topLeftCorner[0] + ufo.length; x++){
			for(int y=ufo.topLeftCorner[1]; y<ufo.topLeftCorner[1] + ufo.height; y++){
				controller.setColor(x, y, ufo.positions[x][y][0], ufo.positions[x][y][1], ufo.positions[x][y][2]);
			}
		}
		
	}

	public static Projectile shoot(EnemyShip ufo) {
		
		Projectile projectile = new Projectile(ufo.topLeftCorner[0] + ufo.length/2, ufo.height, 127, 127, 127);
		Projectile.spawnProjectile(projectile);
		
		return projectile;
		
	}

	public static void move() {
		// TODO Auto-generated method stub
		
	}

	
}
