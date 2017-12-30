package ufos;

import gameObjects.EnemyShip;

//A long and short enemy ship
public class LangerLulatsch extends EnemyShip {

	//It is a 3 by 5 ColoringField
	public LangerLulatsch(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 3, 5, maxLifes, ammo);

		for(int x=0; x<3; x++){
			for(int y=0; y<5; y++){
				this.setColorAt(x, y, 30, 30, 60);
			}
		}
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub

	}

}
