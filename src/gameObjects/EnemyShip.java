package gameObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public abstract class EnemyShip extends Spaceship {

	static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	private EnemyShip next;
	private int[] topLeftCorner = new int[2];
	private int length;
	private int height;
	private int maxLifes;
	private int lifes;
	protected int[][] cannons;
	protected Projectile[] shots;
	
	//Getters and Setters for various things
	public EnemyShip getNext() {return next;}
	public void setNext(EnemyShip next) {this.next = next;}
	
	public int[] getTopLeftCorner() {return topLeftCorner;}

	public int getLength() {return length;}
	public int getHeight() {return height;}

	public int getMaxLifes() {return maxLifes;}
	public int getLifes() {return lifes;}
	public void setLifes(int lifes) {this.lifes = lifes;}
	
	public int[][] getCannons() {return cannons;}
	protected void setCannons(int[][] cannons) {this.cannons = cannons;}
	
	public Projectile[] getShots() {return shots;}
	protected void setShots(Projectile[] shots) {this.shots = shots;}
	
	//In addition to a top left corner, a length, a height, lifes and ammunition, EnemyShips also have a successor called next,
	//which is always null when a new EnemyShip is created.
	//This way, the structure for a list of EnemyShips is made, so that the next EnemyShip can be spawned in case the current
	//one is destroyed.
	public EnemyShip(int[] topLeftCorner, int length, int height, int maxLifes, int ammo) {
		
		super(topLeftCorner, length, height, maxLifes, ammo);
		
		this.next = null;
		
		this.topLeftCorner = topLeftCorner;
		this.length = length;
		this.height = height;
		this.maxLifes = maxLifes;
		this.lifes = maxLifes;
		this.shots = new Projectile[ammo];
		
	}
	
	@Override
	public void spawn(){
		
		//Two helping variables
		int x1 = this.topLeftCorner[0];
		int y1 = this.topLeftCorner[1];
		
		//Starting from the top left corner, this loop draws every entry of the EnemyShips positions array that isn't black
		//onto the board in its corresponding color
		for(int x=0; x<this.length; x++){
			for(int y=0; y<this.height; y++){
				if(this.positions[x][y][0]!=0||this.positions[x][y][1]!=0||this.positions[x][y][2]!=0){
					controller.setColor(x+x1, y+y1, this.positions[x][y][0], this.positions[x][y][1], this.positions[x][y][2]);
				}
			}
		}
		
	}

	@Override
	public abstract void shoot(int[] cannon);

	@Override
	public void move(char direction) {

		switch(direction){
		
		case 'W':
//			if(this.topLeftCorner[1]>0){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + this.length; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + this.height; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[1] -= 1;
				this.spawn();
//			}
			break;
			
		case 'S':
//			if(this.topLeftCorner[1]+this.height<20){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + this.length; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + this.height; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[1] += 1;
				this.spawn();
//			}
			break;
		
		case 'A':
//			if(this.topLeftCorner[0] + this.length/2>0){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + this.length; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + this.height; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[0] -= 1;
				this.spawn();
//			}
			break;
			
		case 'D':
//			if(this.topLeftCorner[0]+this.length/2+1<20){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + this.length; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + this.height; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
			
				this.topLeftCorner[0] += 1;
				this.spawn();
//			}
			break;
		}
		
	}

	@Override
	public void hit(){
		this.lifes -=1;
	}
	
	@Override
	public void fade(){
		//Every color
		for(int i=0;i<3;i++){
			//in every entry of the positions array
			for(int x=0;x<this.length;x++){
				for(int y=0;y<this.height;y++){
					//is reduced by one if it isn't zero.
					if(this.positions[x][y][i]!=0)this.positions[x][y][i]-=1;
					this.spawn();
				}
			}
		}
	}
}
