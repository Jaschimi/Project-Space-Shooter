package ufos;

import gameObjects.EnemyShip;

public class GalaxyDestroyer extends EnemyShip {

	public GalaxyDestroyer(int[] topLeftCorner, int maxLifes, int ammo) {
		
		super(topLeftCorner, 17, 10, maxLifes, ammo);
		
		for(int x=0; x<17; x++){
			for(int y=2; y<6; y++){
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
		
		//The spots left and right of the middle cannon
		this.setColorAt(5, 6, 18, 18, 87);
		this.setColorAt(6, 6, 18, 18, 87);
		this.setColorAt(6, 7, 18, 18, 87);
		this.setColorAt(7, 6, 18, 18, 87);
		this.setColorAt(9, 6, 18, 18, 87);
		this.setColorAt(11, 6, 18, 18, 87);
		this.setColorAt(10, 6, 18, 18, 87);
		this.setColorAt(10, 7, 18, 18, 87);
		
		//The middle cannon
		this.setColorAt(8, 6, 30, 30, 90);
		for(int x=7; x<10; x+=2){
			for(int y=7; y<10; y++){
				this.setColorAt(x, y, 30, 30, 90);
			}
		}
		
		//The left and right cannons
		this.setColorAt(2, 5, 30, 30, 90);
		for(int x=1; x<4; x+=2){
			for(int y=6; y<8; y++){
				this.setColorAt(x, y, 30, 30, 90);
			}
		}
		for(int x=13; x<16; x+=2){
			for(int y=6; y<8; y++){
				this.setColorAt(x, y, 30, 30, 90);
			}
		}
		this.setColorAt(14, 5, 30, 30, 90);
		
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
		//It loses a life
		super.hit();
		
		//And the cross indicating its energy may change color
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
					this.setColorAt(8, y, 127, 127, 0);
				}
				for(int x=7; x<10; x++){
					this.setColorAt(x, 3, 127, 127, 0);
				}
			}
			else{
				if(this.getLifes()>=1){
					for(int y=2; y<5; y++){
						this.setColorAt(8, y, 69, 4, 4);
					}
					for(int x=7; x<10; x++){
						this.setColorAt(x, 3, 69, 4, 4);
					}
				}
				else{
					for(int y=2; y<5; y++){
						this.setColorAt(8, y, 31, 31, 31);
					}
					for(int x=7; x<10; x++){
						this.setColorAt(x, 3, 31, 31, 31);
					}
				}
			}
		}
		this.spawnShip();
	}

}
