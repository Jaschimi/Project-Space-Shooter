package gameObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

//This is the class that contains all methods for the controllable ship, the Space Shooter, to function properly ingame
public class SpaceShooter extends Spaceship{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	//The length of the Space Shooter is always three, the height always two.
	public SpaceShooter(int[] topLeftCorner, int lives, int ammo) {
		
		super(topLeftCorner, 3, 2, lives, ammo);
		
		this.topLeftCorner = topLeftCorner;
		this.length = 3;
		this.height = 2;
		this.shots = new Projectile[ammo];
		this.lives = lives;
		
	}

	@Override
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
//			if(this.topLeftCorner[1]>0){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[1] -= 1;
				this.spawn();
//			}
			break;
			
		case 'S':
//			if(this.topLeftCorner[1]+2<20){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[1] += 1;
				this.spawn();
//			}
			break;
		
		case 'A':
//			if(this.topLeftCorner[0]+3/2>0){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
						controller.setColor(x, y, 0, 0, 0);
					}
				}
				
				this.topLeftCorner[0] -= 1;
				this.spawn();
//			}
			break;
			
		case 'D':
//			if(this.topLeftCorner[0]+3/2+1<20){
				for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0] + 3; x++){
					for(int y=this.topLeftCorner[1]; y<this.topLeftCorner[1] + 2; y++){
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
	public boolean hit(){
		//It loses a life,
		this.setLives(this.getLives()-1);
		
		//the dot indicating its energy may change color
		if(this.getLives()==3){
			this.setColorAt(1, 1, 5, 107, 17);
		}
		else{
			if(this.getLives()==2){
				this.setColorAt(1, 1, 122, 100, 7);
			}
			else{
				if(this.getLives()==1){
					this.setColorAt(1, 1, 69, 4, 4);
				}
				else{
					if(this.getLives()==0){
						this.setColorAt(1, 1, 31, 31, 31);
					}
					else{
						this.setColorAt(1, 1, 127, 0, 127);
					}
				}
			}
		}
		this.spawn();
		
		//and it lights up. The intensity of the white is determined by the highest color component of the ship's topLeftCorner
		int[] hitColor = controller.getColorAt(this.topLeftCorner[0], this.topLeftCorner[1]);
		if(hitColor[0]<hitColor[1]){
			hitColor[0]=hitColor[1];
		}
		if(hitColor[0]>hitColor[1]){
			hitColor[1]=hitColor[0];
		}
		if(hitColor[2]<hitColor[1]){
			hitColor[2]=hitColor[1];
		}
		if(hitColor[2]>hitColor[1]){
			hitColor[1]=hitColor[2];
			hitColor[0]=hitColor[2];
		}
		
		for(int x=0; x<3; x+=2){
			for(int y=0; y<2; y++){
				controller.setColor(this.topLeftCorner[0]+x, this.topLeftCorner[1]+y, hitColor);
			}
		}
		
		return true;
	}
	
}
