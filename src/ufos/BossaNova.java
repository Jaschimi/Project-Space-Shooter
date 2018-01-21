package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//This ship is a true Boss. It has two cannons, which can both fire two projectiles next to each other.
//If I find the time to implement it, it will also explode upon being destroyed :-D
public class BossaNova extends EnemyShip{

	//It is a 9 by 5 ColoringField
	public BossaNova(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 9, 6, maxLifes, ammo);
		
		//The first row
		for(int x=2; x<7; x++){
			this.setColorAt(x, 0, 18, 18, 87);
		}
		
		//The second row
		for(int x=0; x<9; x++){
			if(x!=4&&x!=3&&x!=5)this.setColorAt(x, 2, 18, 18, 87);
		}
		
		//First and third row
		for(int x=1; x<8; x++){
			for(int y=1; y<4; y+=2){
				if(y==1&&x!=3&&x!=5)this.setColorAt(x, y, 18, 18, 87);
				if(y==3&&x!=4)this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The dots between the cannons
		for(int x=3; x<6; x++){
			this.setColorAt(x, 4, 18, 18, 87);
		}
		this.setColorAt(4, 5, 18, 18, 87);
		
		//The cannons
		this.setColorAt(0, 4, 30, 30, 90);
		this.setColorAt(0, 5, 30, 30, 90);
		this.setColorAt(1, 4, 30, 30, 90);
		this.setColorAt(2, 4, 30, 30, 90);
		this.setColorAt(3, 5, 30, 30, 90);
		this.setColorAt(5, 5, 30, 30, 90);
		this.setColorAt(6, 4, 30, 30, 90);
		this.setColorAt(7, 4, 30, 30, 90);
		this.setColorAt(8, 4, 30, 30, 90);
		this.setColorAt(8, 5, 30, 30, 90);
		
		//The energy display
		for(int x=3; x<6; x++){
			for(int y=1; y<4; y++){
				if(((x!=3&&x!=5)||y!=3)&&(x!=4||y!=1))this.setColorAt(x, y, 5, 107, 18);
			}
		}
	}

	@Override
	public void spawn(){
		
		super.spawn();
		
		//This line makes sure the cannons are at their desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 1, this.getTopLeftCorner()[1] + 5},
								   {this.getTopLeftCorner()[0] + 6, this.getTopLeftCorner()[1] + 5}};
	}
	
	@Override
	public boolean hit() {
		//It loses a life,
		super.hit();
		
		//the heart indicating its energy may change color
		if(this.getLives()>this.getMaxLives()/2){
			for(int x=3; x<6; x++){
				for(int y=1; y<4; y++){
					if(((x!=3&&x!=5)||y!=3)&&(x!=4||y!=1))this.setColorAt(x, y, 5, 107, 18);
				}
			}
		}
		else{
			if(this.getLives()>this.getMaxLives()/4){
				for(int x=3; x<6; x++){
					for(int y=1; y<4; y++){
						if(((x!=3&&x!=5)||y!=3)&&(x!=4||y!=1))this.setColorAt(4, 1, 122, 100, 7);
					}
				}
			}
			else{
				if(this.getLives()>=1){
					for(int x=3; x<6; x++){
						for(int y=1; y<4; y++){
							if(((x!=3&&x!=5)||y!=3)&&(x!=4||y!=1))this.setColorAt(4, 1, 69, 4, 4);
						}
					}
				}
				else{
					for(int x=3; x<6; x++){
						for(int y=1; y<4; y++){
							if(((x!=3&&x!=5)||y!=3)&&(x!=4||y!=1))this.setColorAt(4, 1, 31, 31, 31);
						}
					}
				}
			}
		}
		this.spawn();
		
		//and it lights up. The intensity of the white is determined by the highest color component of the ship's topLeftCorner
		int[] hitColor = controller.getColorAt(this.topLeftCorner[0]+1, this.topLeftCorner[1]+1);
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
		
		for(int x=this.topLeftCorner[0]+0; x<this.topLeftCorner[0]+this.length; x++){
			for(int y=this.topLeftCorner[1]+0; y<this.topLeftCorner[1]+this.height; y++){
				if((controller.getColorAt(x, y)[0]==30||controller.getColorAt(x, y)[0]==18)
				 &&(controller.getColorAt(x, y)[1]==30||controller.getColorAt(x, y)[1]==18)
				 &&(controller.getColorAt(x, y)[2]==90||controller.getColorAt(x, y)[2]==87)){
					controller.setColor(x, y, hitColor);
				}
			}
		}
		
		return true;
	}

	@Override
	public void shoot(int[] cannon) {

		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length-1; i++){
			if(shots[i]==null&&shots[i+1]==null){
				
				//These are the projectiles that will be shot
				Projectile projectileLeft = new Projectile(70, 70, 127);
				Projectile projectileRight = new Projectile(70, 70, 127);
				
				shots[i]=projectileLeft;
				shots[i+1]=projectileRight;
				projectileLeft.spawnProjectile(cannon[0], cannon[1]);
				projectileRight.spawnProjectile(cannon[0]+1, cannon[1]);
				return;
			}
		}
		
	}

}
