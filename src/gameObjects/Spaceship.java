package gameObjects;

//This class is the superclass of both the EnemyShips and the SpaceShooter.
public abstract class Spaceship extends ColoringField {
	
	//Aside from a length and a height, Spaceships also have ammunition, lifes and a top left corner.
	//Ammunition is the amount of projectiles a Spaceship can shoot at once, the top left corner determines where
	//the ship will spawn on the board and lifes is the amount of times a ship can be hit by a projectile.
	public Spaceship(int[] topLeftCorner, int length, int height, int maxLifes, int ammo){
		
		super(length, height);
		
	}
	//This method makes a Spaceship appear on the board
	public abstract void spawnShip();
	
	//This method makes a Spaceship shoot a projectile
	public abstract void shoot();
	
	//This method makes a Spaceship move in one of four direction
	public abstract void move(char direction);
}
