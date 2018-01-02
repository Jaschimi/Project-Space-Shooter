package ufos;

import gameObjects.EnemyShip;

public class DoubleBoulder extends EnemyShip {

	public DoubleBoulder(int[] topLeftCorner, int maxLifes, int ammo) {
		
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
	public void hit() {
		//It loses a life
		super.hit();
		
		//And the dot indicating its energy may change color
		if(this.getLifes()>=this.getMaxLifes()/2){
			this.setColorAt(2, 2, 5, 107, 17);
			this.setColorAt(3, 2, 5, 107, 17);
		}
		else{
			if(this.getLifes()>=this.getMaxLifes()/4){
				this.setColorAt(2, 2, 127, 127, 0);
				this.setColorAt(3, 2, 5, 107, 17);
			}
			else{
				if(this.getLifes()>=1){
					this.setColorAt(2, 2, 69, 4, 4);
					this.setColorAt(3, 2, 5, 107, 17);
				}
				else{
					this.setColorAt(2, 2, 31, 31, 31);
					this.setColorAt(3, 2, 5, 107, 17);
				}
			}
		}
		this.spawnShip();
	}

}
