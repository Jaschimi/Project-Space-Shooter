package gameObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class SpaceShooter extends Spaceship{
	
	static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	private int[] topLeftCorner = new int[2];
	private Projectile[] shots;
	private int lifes;
	private int[][] cannons;
	
	//Getters and Setters for various things
	public int[] getTopLeftCorner() {return topLeftCorner;}
	
	public Projectile[] getShots() {return shots;}
	public void setShots(Projectile[] shots) {this.shots = shots;}

	public int[][] getCannons() {return cannons;}
	public void setCannons(int[][] cannons) {this.cannons = cannons;}

	public int getLifes() {return lifes;}
	public void setLifes(int lifes) {this.lifes = lifes;}
	
	public SpaceShooter(int[] topLeftCorner, int lifes, int ammo) {
		
		super(topLeftCorner, 3, 2, lifes, ammo);
		
		this.topLeftCorner = topLeftCorner;
		this.shots = new Projectile[ammo];
		this.lifes = lifes;
		this.cannons  = new int[][]{{this.topLeftCorner[0] + 1, this.topLeftCorner[1]}};
		
	}

	public void spawn(){
		
		//Two helping variables
		int x1 = this.topLeftCorner[0];
		int y1 = this.topLeftCorner[1];
		
		//Starting from the top left corner, this loop draws every entry of the SpaceShooters positions array onto the board
		//in its corresponding color
		for(int x=0; x<3; x++){
			for(int y=0; y<2; y++){
				if(x!=1||y!=0){
					controller.setColor(x+x1, y+y1, this.positions[x][y][0], this.positions[x][y][1], this.positions[x][y][2]);
				}
			}
		}
		//This line makes sure the cannon is at its desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 1, this.getTopLeftCorner()[1]}};
	}

	@Override
	public void shoot(int[] cannon) {
		
		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length; i++){
			if(shots[i]==null){
				//This is the projectile that will be shot
				Projectile projectile = new Projectile(127, 127, 127);
				
				shots[i]=projectile;
				projectile.spawnProjectile(cannon[0], cannon[1]);
				return;
			}
		}
	}

	@Override
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
				this.spawn();
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
				this.spawn();
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
				this.spawn();
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
				this.spawn();
			}
			break;
		}
	}

	@Override
	public void hit(){
		//It loses a life
		this.setLifes(this.getLifes()-1);
		
		//And the dot indicating its energy may change color
		if(this.getLifes()==3){
			this.setColorAt(1, 1, 5, 107, 17);
		}
		else{
			if(this.getLifes()==2){
				this.setColorAt(1, 1, 122, 100, 7);
			}
			else{
				if(this.getLifes()==1){
					this.setColorAt(1, 1, 69, 4, 4);
				}
				else{
					if(this.getLifes()==0){
						this.setColorAt(1, 1, 31, 31, 31);
					}
					else{
						this.setColorAt(1, 1, 127, 0, 127);
					}
				}
			}
		}
		this.spawn();
	}
	
	@Override
	public void fade(){
		//Every color
		for(int i=0;i<3;i++){
			//in every entry of the positions array
			for(int x=0;x<3;x++){
				for(int y=0;y<2;y++){
					//is reduced by one if it isn't zero.
					if(this.positions[x][y][i]!=0)this.positions[x][y][i]-=1;
					this.spawn();
				}
			}
		}
	}
}
