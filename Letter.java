package objects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class Letter {
	
	private int length, height;
	boolean[][] positions;

	private Letter(int length, int height){
		
		this.length = length;
		this.height = height;
		this.positions = new boolean[length][height];
		
		for(int x=0; x<length; x++){
			for(int y=0; y<height; y++){
				positions[x][y] = true;
			}
		}
	}
	
	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public static Letter convertToLetter(char character){
		Letter letter = new Letter(0,0);
		character = Character.toUpperCase(character);
		if(character=='A'){
			letter = new Letter(3, 5);
			letter.positions[0][0] = false;
			letter.positions[2][0] = false;
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[1][4] = false;
		}
		
		if(character=='B'){
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[2][0] = false;
			letter.positions[2][2] = false;
			letter.positions[2][4] = false;
		}
		
		if(character=='C'){
			letter = new Letter(3, 5);
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			for(int i=1; i<4; i++){
				letter.positions[1][i] = false;
				letter.positions[2][i] = false;
			}
		}
		
		if(character=='D'){
			letter = new Letter(3, 5);
			letter.positions[2][0] = false;
			letter.positions[2][4] = false;
			for(int i=1; i<4; i++){
				letter.positions[1][i] = false;
			}
		}
		
		if(character=='E'){
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[2][1] = false;
			letter.positions[2][3] = false;
		}
		
		
		if(character=='F'){
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[1][4] = false;
			letter.positions[2][1] = false;
			letter.positions[2][3] = false;
			letter.positions[2][4] = false;
		}
		
		
		if(character=='G'){
			letter = new Letter(4, 5);
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			for(int x=1; x<3; x++){
				letter.positions[x][1] = false;
				letter.positions[x][3] = false;
			}
			letter.positions[1][2] = false;
			letter.positions[3][1] = false;
		}
		
		
		if(character=='H'){
			letter = new Letter(3, 5);
			for(int y=0;y<5;y++){
				if(y!=2)letter.positions[1][y] = false;
			}
		}
		
		
		if(character=='I'){
			letter = new Letter(1, 5);
		}
		
		
		if(character=='J'){
			letter = new Letter(3, 5);
			for(int y=1;y<4;y++){
				if(y!=2)letter.positions[1][y] = false;
				if(y!=3)letter.positions[0][y] = false;
			}
			
		}
		
		
		if(character=='K'){
			letter = new Letter(3, 5);
			for(int y=1;y<4; y++){
				letter.positions[2][y] = false;
			}
			for(int y=0;y<5; y+=2){
				letter.positions[1][y] = false;
			}
		}
		
		
		if(character=='L'){
			letter = new Letter(3, 5);
			for(int y=0;y<4; y++){
				letter.positions[1][y] = false;
				letter.positions[2][y] = false;
			}
		}
		
		
		if(character=='M'){
			letter = new Letter(5, 5);
			for(int y=0;y<5; y++){
				if(y!=1)letter.positions[1][y] = false;
				if(y!=2)letter.positions[2][y] = false;
				if(y!=1)letter.positions[3][y] = false;
			}
		}
		
		if(character=='N'){
			letter = new Letter(5, 5);
			for(int y=0;y<5; y++){
				if(y!=1)letter.positions[1][y] = false;
				if(y!=2)letter.positions[2][y] = false;
				if(y!=3)letter.positions[3][y] = false;
			}
		}

		if(character=='O'){
			letter = new Letter(3, 5);
			for(int y=1;y<4; y++){
				letter.positions[1][y] = false;
			}
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			letter.positions[2][0] = false;
			letter.positions[2][4] = false;
		}

		if(character=='P'){
			letter = new Letter(3, 5);
			for(int y=1;y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
				if(y!=1 && y!=2)letter.positions[2][y] = false;
			}
		}

		if(character=='Q'){
			letter = new Letter(4, 5);
			for(int y=0;y<4; y++){
				if(y!=0 && y!=4)letter.positions[1][y] = false;
				letter.positions[3][y] = false;
			}
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			letter.positions[2][0] = false;
			letter.positions[2][4] = false;
		}

		if(character=='R'){
			letter = new Letter(3, 5);
			for(int y=1;y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
			}
			letter.positions[2][2] = false;
			
		}

		if(character=='S'){
			letter = new Letter(3, 5);
			for(int y=0;y<5;y++){
				if(y!=1 && y!=4)letter.positions[0][y] = false;
				if(y%2 !=0)letter.positions[1][y] = false;
				if(y!=0 && y!=3)letter.positions[2][y] = false;
			}
		}

		if(character=='T'){
			letter = new Letter(3, 5);
			for(int y=1;y<5;y++){
				letter.positions[0][y] = false;
				letter.positions[2][y] = false;
			}
		}

		if(character=='U'){
			letter = new Letter(3, 5);
			for(int y=0;y<4;y++){
				letter.positions[1][y] = false;
			}
		}

		if(character=='V'){
			letter = new Letter(3, 5);
			for(int y=0;y<4;y++){
				letter.positions[1][y] = false;
			}
			letter.positions[0][4] = false;
			letter.positions[2][4] = false;
		}

		if(character=='W'){
			letter = new Letter(5, 5);
			for(int y=0;y<5; y++){
				if(y!=3)letter.positions[1][y] = false;
				if(y!=2)letter.positions[2][y] = false;
				if(y!=3)letter.positions[3][y] = false;
			}
		}

		if(character=='X'){
			letter = new Letter(3, 5);
			for(int y=0;y<5;y++){
				if(y!=2)letter.positions[1][y] = false;
			}
			letter.positions[0][2] = false;
			letter.positions[2][2] = false;
		}

		if(character=='Y'){
			letter = new Letter(3, 5);
			for(int y=0;y<2;y++){
				letter.positions[1][y] = false;
			}
			for(int y=2;y<5;y++){
				letter.positions[0][y] = false;
				letter.positions[2][y] = false;
			}
		}

		if(character=='Z'){
			letter = new Letter(3, 5);
			for(int y=1;y<4;y++){
				if(y!=3)letter.positions[0][y] = false;
				if(y%2 !=0)letter.positions[1][y] = false;
				if(y!=1)letter.positions[2][y] = false;
			}
		}

		return letter;
	}
	
	public static void DrawLetterAt(char character, int x, int y, int red, int green, int blue){
		Letter letter = convertToLetter(character);
		BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
		
		if(letter!=null){
			for(int i=0; i<letter.length; i++){
				for(int j=0; j<letter.height; j++){
					if(letter.positions[i][j]){
					controller.setColor(x+i, y+j, red, green, blue);
					}
				}
			}
		}
		
	}
	
}
