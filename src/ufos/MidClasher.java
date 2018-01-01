package ufos;

import gameObjects.EnemyShip;

//A bigger ship than the DefaultShips
public class MidClasher extends EnemyShip {

	//It is a 5 by 4 ColoringField
	public MidClasher(int[] topLeftCorner, int maxLifes, int ammo) {
		
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

	//This method is triggered once a ship is hit by a projectile
	public void hit(){
		//It loses a life
		super.hit();
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>=this.getMaxLifes()/2){
			this.setColorAt(2, 1,  5, 107, 17);
		}
		else{
			if(this.getLifes()>=this.getMaxLifes()/4){
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
	
}
