package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//This ship has two cannons with which to shoot. It is the biggest ship that isn't classified as a boss.
//It has its name because it looks like a classic UFO, but some people are reminded of a certain marine animal when looking at it...
public class UFO extends EnemyShip {

	//It is a 7 by 5 ColoringField
	public UFO(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 7, 5, maxLifes, ammo);
		
		for(int x=0; x<this.getLength(); x++){
			for(int y=1; y<this.getHeight()-1; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The first row
		for(int x=2; x<this.getLength()-2; x++){
			this.setColorAt(x, 0, 18, 18, 87);
		}
		
		//The holes in the second row
		this.setColorAt(0, 1, 0, 0, 0);
		this.setColorAt(6, 1, 0, 0, 0);
		
		//The holes in the third row
		this.setColorAt(0, 2, 0, 0, 0);
		this.setColorAt(6, 2, 0, 0, 0);
		
		//The cannons
		this.setColorAt(1, 3, 30, 30, 90);
		this.setColorAt(5, 3, 30, 30, 90);
		for(int x=0; x<7; x+=2){
			for(int y=3; y<5; y++){
				this.setColorAt(x, 4, 30, 30, 90);
			}
		}
		
		//The dot in the center of the last row
		this.setColorAt(3, 4, 18, 18, 87);
		
		//The energy display
		this.setColorAt(2, 2, 5, 107, 17);
		this.setColorAt(4, 2, 5, 107, 17);
	}

	@Override
	public void spawn(){
		
		super.spawn();
		
		//This line makes sure the cannons are at their desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 1, this.getTopLeftCorner()[1] + 4}, 
								   {this.getTopLeftCorner()[0] + 5, this.getTopLeftCorner()[1] + 4}};
	}
	
	@Override
	public boolean hit() {
		//It loses a life,
		super.hit();
		
		//the dots indicating its energy may change color
		if(this.getLives()>this.getMaxLives()/2){
			this.setColorAt(2, 2, 5, 107, 17);
			this.setColorAt(4, 2, 5, 107, 17);
		}
		else{
			if(this.getLives()>this.getMaxLives()/4){
				this.setColorAt(2, 2, 122, 100, 7);
				this.setColorAt(4, 2, 122, 100, 7);
			}
			else{
				if(this.getLives()>=1){
					this.setColorAt(2, 2, 69, 4, 4);
					this.setColorAt(4, 2, 69, 4, 4);
				}
				else{
					this.setColorAt(2, 2, 31, 31, 31);
					this.setColorAt(4, 2, 31, 31, 31);
				}
			}
		}
		this.spawn();

		//and it lights up. The intensity of the white is determined by the highest color component of the ship's base color
		int[] hitColor = controller.getColorAt(this.topLeftCorner[0]+3, this.topLeftCorner[1]);
		this.hitAnimation(hitColor);
		
		return true;
	}

	@Override
	public void shoot(int[] cannon) {

		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length; i++){
			if(shots[i]==null){
				
				//This is the projectile that will be shot
				Projectile projectile = new Projectile(90, 60, 116);
				
				shots[i]=projectile;
				projectile.spawnProjectile(cannon[0], cannon[1]);
				return;
			}
		}
		
	}

}
