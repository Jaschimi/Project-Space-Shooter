package gameObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class Projectile extends ColoringField{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private int x, y, red, green, blue;
	
	//Getters and Setters for various things
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	public int getY() {return y;}

	//A projectile is a 1 by 1 ColoringField with a customizable color
	public Projectile(int red, int green, int blue) {
		super(1, 1);
		this.positions[0][0] = new int[]{red, green, blue};
		this.red = red;
		this.green = green;
		this.blue = blue;
		
	}
	
	//This method displays a Projectile at a position on the board
	public void spawnProjectile(int x, int y){
		
		this.x = x;
		this.y = y;
		
		controller.setColor(this.x, this.y, this.red, this.green, this.blue);
		
	}
	
	//This method moves a projetile either one spot up or one spot down and changes its y-coordinate accordingly
	public void moveProjectile(String direction){
		
		if(direction=="up"){
			this.y -=1;
			controller.setColor(this.x, this.y+1, 0, 0, 0);
			controller.setColor(this.x, this.y, this.red, this.green, this.blue);
		}
		
		if(direction=="down"){
			this.y +=1;
			controller.setColor(this.x, this.y-1, 0, 0, 0);
			controller.setColor(this.x, this.y, this.red, this.green, this.blue);
		}
		
	}

}
