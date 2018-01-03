package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//A long and short enemy ship. It can fire two shots at once above each other.
public class LangerLulatsch extends EnemyShip {

	//It is a 3 by 5 ColoringField
	public LangerLulatsch(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 3, 5, maxLifes, ammo);

		//The top
		this.setColorAt(1, 0, 18, 18, 87);
		
		//The sides
		for(int x=0; x<3; x+=2){
			for(int y=1; y<5; y++){
				if(y<3)this.setColorAt(x, y, 18, 18, 87);
				else this.setColorAt(x, y, 30, 30, 90);
			}
		}
		
		//The energy dot
		this.setColorAt(1, 1, 5, 107, 17);
		
		//The dot below the energy display
		this.setColorAt(1, 2, 30, 30, 90);
	}

	@Override
	public void spawnShip(){
		
		super.spawnShip();
		
		//This line makes sure the cannon is at its desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 1, this.getTopLeftCorner()[1] + 3}};
	}
	
	@Override
	public void hit() {
		//It loses a life
		super.hit();
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>2*this.getMaxLifes()/3){
			this.setColorAt(1, 1,  5, 107, 17);
		}
		else{
			if(this.getLifes()>this.getMaxLifes()/3){
				this.setColorAt(1, 1, 127, 127, 0);
			}
			else{
				if(this.getLifes()>=1){
					this.setColorAt(1, 1, 69, 4, 4);
				}
				else{
					this.setColorAt(1, 1, 31, 31, 31);
				}
			}
		}
		this.spawnShip();
	}

	@Override
	public void shoot(int[] cannon) {

		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length-1; i++){
			if(shots[i]==null&&shots[i+1]==null){
				
				//These are the projectiles that will be shot
				Projectile projectileTop = new Projectile(70, 127, 127);
				Projectile projectileBot = new Projectile(70, 127, 127);
				
				shots[i+1]=projectileTop;
				shots[i]=projectileBot;
				projectileTop.spawnProjectile(cannon[0], cannon[1]);
				projectileBot.spawnProjectile(cannon[0], cannon[1]+1);
				return;
			}
		}
		
	}

}
