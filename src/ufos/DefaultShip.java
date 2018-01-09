package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//The default EnemyShip
public class DefaultShip extends EnemyShip {

	//This is a special version of it with a different color
	public static class GoldenVersion extends DefaultShip {

		public GoldenVersion(int[] topLeftCorner, int maxLifes, int ammo) {
			super(topLeftCorner, maxLifes, ammo);

			this.setColorAt(0, 1, 127, 107, 0);
			this.setColorAt(0, 2, 127, 107, 0);
			this.setColorAt(1, 0, 127, 107, 0);
			this.setColorAt(2, 1, 127, 107, 0);
			this.setColorAt(2, 2, 127, 107, 0);
		}
		
		@Override
		public void shoot(int[] cannon) {
			
			//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
			for(int i=0; i<this.shots.length; i++){
				if(shots[i]==null){
					
					//This is the projectile that will be shot
					Projectile projectile = new Projectile(127, 107, 55);
					
					shots[i]=projectile;
					projectile.spawnProjectile(cannon[0], cannon[1]);
					return;
				}
			}
		}
		
	}

	//It is a 3 by 3 ColoringField
	public DefaultShip(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 3, 3, maxLifes, ammo);
		
		//These lines paint it in blue
		for(int x = 0; x < this.getLength(); x++){
			for(int y = 0; y < this.getHeight(); y++){
				if(x== 1 && y == 2)this.setColorAt(x, y, 0, 0, 0);
				else{
					if(y==0 && (x==0 || x==2))this.setColorAt(x, y, 0, 0, 0);
					else{
						this.setColorAt(x, y, 18, 18, 87);
					}
				}
			}
		}
		//This is the dot displaying the ships lifes
		if(this.getLifes()<=0){
			this.setColorAt(1, 1, 31, 31, 31);
		}
		else{
			if(this.getLifes()<=1){
			this.setColorAt(1, 1, 69, 4, 4);
			}
			else{
				if(this.getLifes()<=2){
					this.setColorAt(1, 1, 127, 127, 0);
				}
				else{
					this.setColorAt(1, 1,  5, 107, 17);
				}
			}
		}
	}

	@Override
	public void spawnShip(){
		
		super.spawnShip();
		
		//This line makes sure the cannon is at its desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 1, this.getTopLeftCorner()[1] + 2}};
	}
	
	@Override
	public void shoot(int[] cannon) {
		
		//This loop saves the projectile as the first free shots array entry and only spawns it if one exists
		for(int i=0; i<this.shots.length; i++){
			if(shots[i]==null){
				
				//This is the projectile that will be shot
				Projectile projectile = new Projectile(127, 127, 100);
				
				shots[i]=projectile;
				projectile.spawnProjectile(cannon[0], cannon[1]);
				return;
			}
		}
	}
	
	//This method is triggered once a ship is hit by a projectile
	public void hit(){
		//It loses a life
		super.hit();

		//And the dot indicating its energy may change color
		if(this instanceof GoldenVersion){
			if(this.getLifes()>this.getMaxLifes()/2){
				this.setColorAt(1, 1,  5, 107, 17);
			}
			else{
				if(this.getLifes()>this.getMaxLifes()/4){
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
		}
		else{
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
		}
		this.spawnShip();
	}

	
}