package gameObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class SpaceShooter extends Spaceship{
	
	static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	private int[] topLeftCorner = new int[2];
	private Projectile[] shots;
	private int lifes;
	
	//Getters and Setters for various things
	public int[] getTopLeftCorner() {return topLeftCorner;}
	public void setTopLeftCorner(int[] topLeftCorner) {this.topLeftCorner = topLeftCorner;}
	
	public Projectile[] getShots() {return shots;}
	
	public int getLifes() {return lifes;}
	public void setLifes(int lifes) {this.lifes = lifes;}
	
	public SpaceShooter(int[] topLeftCorner, int lifes, int ammo) {
		
		super(topLeftCorner, 3, 2, 3, 3);
		
		this.topLeftCorner = topLeftCorner;
		this.shots = new Projectile[ammo];
		this.lifes = lifes;
		
	}

	public void spawnShip(){
		
		//Two helping variables
		int x1 = this.topLeftCorner[0];
		int y1 = this.topLeftCorner[1];
		
		//Starting from the top left corner, this loop draws every entry of the SpaceShooters positions array onto the board
		//in its corresponding color
		for(int x=x1; x<x1 + 3; x++){
			for(int y=y1; y<y1 + 2; y++){
				controller.setColor(x, y, this.positions[x-x1][y-y1][0], this.positions[x-x1][y-y1][1], this.positions[x-x1][y-y1][2]);
			}
		}
		
	}

	public void shoot() {
		
		//This is the projectile that will be shot
		Projectile projectile = new Projectile(127, 127, 127);
		
		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length; i++){
			if(shots[i]==null){
				shots[i]=projectile;
				projectile.spawnProjectile(this.topLeftCorner[0] + 1, this.topLeftCorner[1]);
				break;
			}
		}
		
	}

	public void move(char direction) {
		
		switch(direction){
		
		case 'W':
			if(this.topLeftCorner[1]>0){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[1] -= 1;
				this.spawnShip();
			}
			break;
			
		case 'S':
			if(this.topLeftCorner[1]+2<20){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[1] += 1;
				this.spawnShip();
			}
			break;
		
		case 'A':
			if(this.topLeftCorner[0]+3/2>0){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[0] -= 1;
				this.spawnShip();
			}
			break;
			
		case 'D':
			if(this.topLeftCorner[0]+3/2+1<20){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
			
				this.topLeftCorner[0] += 1;
				this.spawnShip();
			}
			break;
		}
	}

	public void hit(){
		//It loses a life
		this.setLifes(this.getLifes()-1);
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>=3){
			this.setColorAt(1, 1,  5, 107, 17);
		}
		else{
			if(this.getLifes()==2){
				this.setColorAt(1, 1, 127, 127, 0);
			}
			else{
				if(this.getLifes()==1){
					this.setColorAt(1, 1, 69, 4, 4);
				}
				else{
					this.setColorAt(1, 1, 31, 31, 31);
				}
			}
		}
		this.spawnShip();
	}
	
}
