package ufos;

import gameObjects.EnemyShip;

public class BigBoulder extends EnemyShip {

	public BigBoulder(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 7, 3, maxLifes, ammo);
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

}
