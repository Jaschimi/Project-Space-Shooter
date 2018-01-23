package gameObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

//The superclass of all types of EnemyShips
public abstract class EnemyShip extends Spaceship {

	protected static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	private EnemyShip next;
	
	//Getter and Setter for the successor of an EnemyShip
	public EnemyShip getNext() {return next;}
	public void setNext(EnemyShip next) {this.next = next;}
	
	//In addition to a top left corner, a length, a height, lives and ammunition, EnemyShips also have a successor called next,
	//which is always null when a new EnemyShip is created.
	//This way, the structure for a list of EnemyShips is made, so that the next EnemyShip can be spawned in case the current
	//one is destroyed.
	public EnemyShip(int[] topLeftCorner, int length, int height, int maxLives, int ammo) {
		
		super(topLeftCorner, length, height, maxLives, ammo);
		
		this.next = null;
		
		this.topLeftCorner = topLeftCorner;
		this.length = length;
		this.height = height;
		this.maxLives = maxLives;
		this.lives = maxLives;
		this.shots = new Projectile[ammo];
		
	}
	
	@Override
	public void spawn(){
		
		//Two helping variables
		int x1 = this.topLeftCorner[0];
		int y1 = this.topLeftCorner[1];
		
		//Starting from the top left corner, this loop draws every entry of the EnemyShips positions array that isn't black
		//onto the board in its corresponding color.
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
	public boolean hit(){
		this.lives -=1;
		return true;
	}
	
	protected void hitAnimation(int[] hitColor){
		
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
		
		for(int x=this.topLeftCorner[0]; x<this.topLeftCorner[0]+this.length; x++){
			for(int y=this.topLeftCorner[1]+0; y<this.topLeftCorner[1]+this.height; y++){
				if(x>=0&&x<20&&y>=0&&y<20){
					if((controller.getColorAt(x, y)[0]==30||controller.getColorAt(x, y)[0]==18)
					 &&(controller.getColorAt(x, y)[1]==30||controller.getColorAt(x, y)[1]==18)
					 &&(controller.getColorAt(x, y)[2]==90||controller.getColorAt(x, y)[2]==87)){
						controller.setColor(x, y, hitColor);
					}
				}
			}
		}
		
	}
}
