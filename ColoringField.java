package objects;

public abstract class ColoringField {
	
	int[][][] positions;

	public ColoringField(int length, int height){
		
		this.positions = new int[length][height][3];
		
	}
	
	public void setColorAt(ColoringField cField, int x, int y, int red, int green, int blue){
		cField.positions[x][y] = new int[]{red, green, blue};
	}
}
