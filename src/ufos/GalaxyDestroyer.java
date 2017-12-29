package ufos;

import gameObjects.EnemyShip;

public class GalaxyDestroyer extends EnemyShip {

	public GalaxyDestroyer(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 17, 10, maxLifes, ammo);
		
		for(int x=0; x<17; x++){
			for(int y=2; y<7; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The drive
		this.setColorAt(1, 0, 103, 32, 20);
		this.setColorAt(1, 1, 103, 32, 20);
		for(int x=0; x<3; x+=2){
			for(int y=0; y<2; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		this.setColorAt(15, 0, 103, 32, 20);
		this.setColorAt(15, 1, 103, 32, 20);
		for(int x=14; x<17; x+=2){
			for(int y=0; y<2; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		
		//The second row
		for(int x=6; x<11; x++){
			this.setColorAt(x, 1, 18, 18, 87);
		}

		//Holes in third row
		this.setColorAt(3, 2, 0, 0, 0);
		this.setColorAt(13, 2, 0, 0, 0);
		
		//The middle cannon
		this.setColorAt(5, 6, 18, 18, 87);
		this.setColorAt(6, 7, 18, 18, 87);
		for(int x=7; x<10; x+=2){
			for(int y=7; y<10; y++){
				this.setColorAt(x, y, 18, 18, 87);
			}
		}
		this.setColorAt(11, 6, 18, 18, 87);
		this.setColorAt(10, 7, 18, 18, 87);
		
		//The left and right cannons
		this.setColorAt(2, 6, 0, 0, 0);
		for(int x=1; x<4; x+=2){
			this.setColorAt(x, 7, 18, 18, 87);
		}
		this.setColorAt(14, 6, 0, 0, 0);
		for(int x=13; x<16; x+=2){
			this.setColorAt(x, 7, 18, 18, 87);
		}
		
		//Holes between cannons
		this.setColorAt(0, 6, 0, 0, 0);
		this.setColorAt(4, 6, 0, 0, 0);
		this.setColorAt(12, 6, 0, 0, 0);
		this.setColorAt(16, 6, 0, 0, 0);
		
		//The cross indicating the GalaxyDestroyers Energy
		if(this.getLifes()>=this.getMaxLifes()/2){
			for(int y=2; y<5; y++){
				this.setColorAt(8, y, 5, 107, 17);
			}
			for(int x=7; x<10; x++){
				this.setColorAt(x, 3, 5, 107, 17);
			}
		}
		else{
			if(this.getLifes()>=this.getMaxLifes()/4){
				for(int y=2; y<5; y++){
					this.setColorAt(8, y, 5, 107, 17);
				}
				for(int x=7; x<10; x++){
					this.setColorAt(x, 3, 5, 107, 17);
				}
			}
		}
		
		
	}

	@Override
	public void hit() {
		
	}

}
