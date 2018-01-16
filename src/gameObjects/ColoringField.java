package gameObjects;

//This class represents the superclass of all game objects
public abstract class ColoringField {
	
	int[][][] positions;
	
	//Every ColoringField consists of a length and a height which define the first two dimensions of the positions array.
	//The last dimension always has three entries.
	public ColoringField(int length, int height){
		
		this.positions = new int[length][height][3];
		
	}
	
	//With this method, every entry of the positions array can be filled with an RGB color
	public void setColorAt(int x, int y, int red, int green, int blue){
		this.positions[x][y] = new int[]{red, green, blue};
	}
}