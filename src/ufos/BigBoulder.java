package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//A bigger ship than the DefaultShips. It usually has more lifes and can shoot more projectiles.
public class BigBoulder extends EnemyShip {

	//It is a 5 by 4 ColoringField
	public BigBoulder(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 5, 4, maxLifes, ammo);
		
		
		for(int x = 0; x < this.getLength(); x++){
			for(int y = 0; y < this.getHeight(); y++){
				if((x==0 || x==2 || x==4) && y==3)this.setColorAt(x, y, 0, 0, 0);
				else{
					if(y==0 && (x==0 || x==4))this.setColorAt(x, y, 0, 0, 0);
					else{
						this.setColorAt(x, y, 18, 18, 87);
					}
				}
			}
		}
		
		//The cannon
		this.setColorAt(1, 3, 30, 30, 90);
		this.setColorAt(2, 2, 30, 30, 90);
		this.setColorAt(3, 3, 30, 30, 90);
		
		//This is the dot displaying the ships lifes
		this.setColorAt(2, 1,  5, 107, 17);
	}

	@Override
	public void spawnShip(){
		
		super.spawnShip();
		
		//This line makes sure the cannon is at its desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 2, this.getTopLeftCorner()[1] + 3}};
	}
	
	//This method is triggered once a ship is hit by a projectile
	public void hit(){
		//It loses a life
		super.hit();
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>this.getMaxLifes()/2){
			this.setColorAt(2, 1,  5, 107, 17);
		}
		else{
			if(this.getLifes()>this.getMaxLifes()/4){
				this.setColorAt(2, 1, 127, 127, 0);
			}
			else{
				if(this.getLifes()>=1){
					this.setColorAt(2, 1, 69, 4, 4);
				}
				else{
					this.setColorAt(2, 1, 31, 31, 31);
				}
			}
		}
		this.spawnShip();
	}

	@Override
	public void shoot(int[] cannon) {

		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length; i++){
			if(shots[i]==null){
				
				//This is the projectile that will be shot
				Projectile projectile = new Projectile(100, 127, 100);
				
				shots[i]=projectile;
				projectile.spawnProjectile(cannon[0], cannon[1]);
				return;
			}
		}
		
	}
	
}
