package ufos;

import gameObjects.EnemyShip;
import gameObjects.Projectile;

//This ship is a true Boss. It has two cannons, which can both fire two projectiles next to each other.
//If I find the time to implement it, it will also explode upon being destroyed :-D
public class BossaNova extends EnemyShip{

	//It is a 9 by 5 ColoringField
	public BossaNova(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 9, 5, maxLifes, ammo);
		
		//The energy dot
		this.setColorAt(4, 1, 5, 107, 18);
		
		//The second row
		for(int x=0; x<9; x++){
			if(x!=4)this.setColorAt(x, 1, 18, 18, 87);
		}
		
		//First and third row
		for(int x=1; x<8; x++){
			for(int y=0; y<3; y+=2){
				if(y==0&&x!=4)this.setColorAt(x, y, 18, 18, 87);
				if(y==2&&x!=3&&x!=5)this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The dots next to the cannons
		this.setColorAt(0, 3, 30, 30, 90);
		this.setColorAt(8, 3, 30, 30, 90);
		
		//The cannons
		this.setColorAt(0, 4, 30, 30, 90);
		this.setColorAt(1, 3, 30, 30, 90);
		this.setColorAt(2, 3, 30, 30, 90);
		this.setColorAt(3, 4, 30, 30, 90);
		this.setColorAt(5, 4, 30, 30, 90);
		this.setColorAt(6, 3, 30, 30, 90);
		this.setColorAt(7, 3, 30, 30, 90);
		this.setColorAt(8, 4, 30, 30, 90);	
	}

	@Override
	public void spawnShip(){
		
		super.spawnShip();
		
		//This line makes sure the cannons are at their desired location when spawning the ship
		this.cannons = new int[][]{{this.getTopLeftCorner()[0] + 1, this.getTopLeftCorner()[1] + 4},
								   {this.getTopLeftCorner()[0] + 6, this.getTopLeftCorner()[1] + 4}};
	}
	
	@Override
	public void hit() {
		//It loses a life
		super.hit();
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>this.getMaxLifes()/2){
			this.setColorAt(4, 1, 5, 107, 17);
		}
		else{
			if(this.getLifes()>this.getMaxLifes()/4){
				this.setColorAt(4, 1, 127, 127, 0);
			}
			else{
				if(this.getLifes()>=1){
					this.setColorAt(4, 1, 69, 4, 4);
				}
				else{
					this.setColorAt(4, 1, 31, 31, 31);
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
