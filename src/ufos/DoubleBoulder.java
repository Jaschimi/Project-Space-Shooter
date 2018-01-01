package ufos;

import gameObjects.EnemyShip;

public class DoubleBoulder extends EnemyShip {

	public DoubleBoulder(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 6, 5, maxLifes, ammo);
		
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
		this.setColorAt(5, 1, 0, 0, 0);
		
		//The cannons
		this.setColorAt(1, 3, 30, 30, 90);
		this.setColorAt(4, 3, 30, 30, 90);
		for(int x=0; x<6; x++){
			if(x!=1&&x!=4)this.setColorAt(x, 4, 30, 30, 90);
		}
		
		//The energy display
		this.setColorAt(2, 2, 5, 107, 17);
		this.setColorAt(3, 2, 5, 107, 17);
	}

	@Override
	public void hit() {
		super.hit();
	}

}
