package ufos;

import gameObjects.EnemyShip;

//A long and short enemy ship
public class LangerLulatsch extends EnemyShip {

	//It is a 3 by 5 ColoringField
	public LangerLulatsch(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 3, 5, maxLifes, ammo);

		//The top
		this.setColorAt(1, 0, 18, 18, 87);
		
		//The sides
		for(int x=0; x<3; x+=2){
			for(int y=1; y<5; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The energy dot
		this.setColorAt(1, 1, 5, 107, 17);
	}

	@Override
	public void hit() {
		//It loses a life
		super.hit();
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>=2*this.getLifes()/3){
			this.setColorAt(1, 1,  5, 107, 17);
		}
		else{
			if(this.getLifes()>=this.getLifes()/3){
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

}
