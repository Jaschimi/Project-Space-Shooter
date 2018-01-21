package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//The ultimate EnemyShip. This bad boy is ginormous; it has three cannons, all of which can shoot two projectiles at once
//(the middle one can even shoot three!), normally a couple hundred lifes and can shoot multiple decades of shots.
public class GalaxyDestroyer extends EnemyShip {

	//It is a 17 by 10 ColoringField
	public GalaxyDestroyer(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 17, 10, maxLifes, ammo);
		
		for(int x=0; x<17; x++){
			for(int y=2; y<6; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The drive
		this.setColorAt(1, 0, 103, 32, 20);
		this.setColorAt(1, 1, 103, 32, 20);
		for(int x=0; x<3; x+=2){
			for(int y=0; y<2; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		this.setColorAt(15, 0, 103, 32, 20);
		this.setColorAt(15, 1, 103, 32, 20);
		for(int x=14; x<17; x+=2){
			for(int y=0; y<2; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The second row
		for(int x=6; x<11; x++){
			this.setColorAt(x, 1, 18, 18, 87);
		}

		//Holes in third row
		this.setColorAt(3, 2, 0, 0, 0);
		this.setColorAt(13, 2, 0, 0, 0);
		
		//The spots left and right of the middle cannon
		this.setColorAt(5, 6, 18, 18, 87);
		this.setColorAt(6, 6, 18, 18, 87);
		this.setColorAt(6, 7, 18, 18, 87);
		this.setColorAt(7, 6, 18, 18, 87);
		this.setColorAt(9, 6, 18, 18, 87);
		this.setColorAt(11, 6, 18, 18, 87);
		this.setColorAt(10, 6, 18, 18, 87);
		this.setColorAt(10, 7, 18, 18, 87);
		
		//The middle cannon
		this.setColorAt(8, 6, 30, 30, 90);
		for(int x=7; x<10; x+=2){
			for(int y=7; y<10; y++){
				this.setColorAt(x, y, 30, 30, 90);
			}
		}
		
		//The left and right cannons
		this.setColorAt(2, 5, 30, 30, 90);
		for(int x=1; x<4; x+=2){
			for(int y=6; y<8; y++){
				this.setColorAt(x, y, 30, 30, 90);
			}
		}
		for(int x=13; x<16; x+=2){
			for(int y=6; y<8; y++){
				this.setColorAt(x, y, 30, 30, 90);
			}
		}
		this.setColorAt(14, 5, 30, 30, 90);
		
		//Holes between cannons
		this.setColorAt(0, 6, 0, 0, 0);
		this.setColorAt(4, 6, 0, 0, 0);
		this.setColorAt(12, 6, 0, 0, 0);
		this.setColorAt(16, 6, 0, 0, 0);
		
		//The cross indicating the GalaxyDestroyers Energy
		if(this.getLives()>=this.getMaxLives()/2){
			for(int y=2; y<5; y++){
				this.setColorAt(8, y, 5, 107, 17);
			}
			for(int x=7; x<10; x++){
				this.setColorAt(x, 3, 5, 107, 17);
			}
		}
		else{
			if(this.getLives()>=this.getMaxLives()/4){
				for(int y=2; y<5; y++){
					this.setColorAt(8, y, 5, 107, 17);
				}
				for(int x=7; x<10; x++){
					this.setColorAt(x, 3, 5, 107, 17);
				}
			}
		}
	}

	@Override
	public void spawn(){
		
		super.spawn();
		
		//This line makes sure the cannons are at their desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 2, this.getTopLeftCorner()[1] + 6},
								   {this.getTopLeftCorner()[0] + 8, this.getTopLeftCorner()[1] + 7},
								   {this.getTopLeftCorner()[0] + 14, this.getTopLeftCorner()[1] + 6}};
	}
	
	@Override
	public boolean hit() {
		//It loses a life,
		super.hit();
		
		//the cross indicating its energy may change color
		if(this.getLives()>=4*this.getMaxLives()/5){
			for(int y=2; y<5; y++){
				this.setColorAt(8, y, 5, 107, 17);
			}
			for(int x=7; x<10; x++){
				this.setColorAt(x, 3, 5, 107, 17);
			}
		}
		else{
			if(this.getLives()>=3*this.getMaxLives()/5){
				for(int y=2; y<5; y++){
					this.setColorAt(8, y, 86, 102, 9);
				}
				for(int x=7; x<10; x++){
					this.setColorAt(x, 3, 86, 102, 9);
				}
			}
			else{
				if(this.getLives()>=2*this.getMaxLives()/5){
					for(int y=2; y<5; y++){
						this.setColorAt(8, y, 122, 100, 7);
					}
					for(int x=7; x<10; x++){
						this.setColorAt(x, 3, 122, 100, 7);
					}
				}
				else{
					if(this.getLives()>=this.getMaxLives()/5){
						for(int y=2; y<5; y++){
							this.setColorAt(8, y, 104, 34, 24);
						}
						for(int x=7; x<10; x++){
							this.setColorAt(x, 3, 104, 34, 24);
						}
					}
					else{
						if(this.getLives()>=1){
							for(int y=2; y<5; y++){
								this.setColorAt(8, y, 69, 4, 4);
							}
							for(int x=7; x<10; x++){
								this.setColorAt(x, 3, 69, 4, 4);
							}
						}
						else{
							for(int y=2; y<5; y++){
								this.setColorAt(8, y, 31, 31, 31);
							}
							for(int x=7; x<10; x++){
								this.setColorAt(x, 3, 31, 31, 31);
							}
						}
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

		//This if statement checks if the specified cannon's y position is where the left or right cannon is located
		if(cannon[1]==this.getCannons()[0][1]){
			//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
			for(int i=0; i<this.shots.length-1; i++){
				if(shots[i]==null&&shots[i+1]==null){
					
					//These are the projectiles that will be shot
					Projectile projectileTop = new Projectile(127, 70, 70);
					Projectile projectileBot = new Projectile(127, 70, 70);
					
					shots[i+1]=projectileTop;
					shots[i]=projectileBot;
					projectileTop.spawnProjectile(cannon[0], cannon[1]);
					projectileBot.spawnProjectile(cannon[0], cannon[1]+1);
					return;
				}
			}
		}
		else{
			//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
			for(int i=0; i<this.shots.length-2; i++){
				if(shots[i]==null&&shots[i+1]==null&&shots[i+2]==null){
					
					//These are the projectiles that will be shot
					Projectile projectileTop = new Projectile(127, 70, 70);
					Projectile projectileMid = new Projectile(127, 70, 70);
					Projectile projectileBot = new Projectile(127, 70, 70);
					
					shots[i+2]=projectileTop;
					shots[i+1]=projectileMid;
					shots[i]=projectileBot;
					projectileTop.spawnProjectile(cannon[0], cannon[1]);
					projectileMid.spawnProjectile(cannon[0], cannon[1]+1);
					projectileBot.spawnProjectile(cannon[0], cannon[1]+2);
					return;
				}
			}
		}
	}

}
