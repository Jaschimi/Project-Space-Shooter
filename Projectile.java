package objects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class Projectile {
	
	private int x, y, red, green, blue;

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Projectile(int x, int y, int red, int green, int blue) {
		
		this.x = x;
		this.y = y;
		this.red = red;
		this.green = green;
		this.blue = blue;
		
	}
	
	public static void spawnProjectile(Projectile projectile){
		
		BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
		controller.setColor(projectile.x, projectile.y, projectile.red, projectile.green, projectile.blue);
		
	}
	
	public static void moveProjectile(Projectile projectile, String direction){
		
		BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
		
		if(direction=="up"){
			projectile.y -=1;
			controller.setColor(projectile.x, projectile.y+1, 0, 0, 0);
			controller.setColor(projectile.x, projectile.y, projectile.red, projectile.green, projectile.blue);
		}
		
		if(direction=="down"){
			projectile.y +=1;
			controller.setColor(projectile.x, projectile.y-1, 0, 0, 0);
			controller.setColor(projectile.x, projectile.y, projectile.red, projectile.green, projectile.blue);
		}
		
	}

}
