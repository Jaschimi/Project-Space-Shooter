package ufos;

import gameObjects.EnemyShip;

public class BossaNova extends EnemyShip{

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
				if(x!=3&&x!=5)this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
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
	public void hit() {
		super.hit();
	}

}
