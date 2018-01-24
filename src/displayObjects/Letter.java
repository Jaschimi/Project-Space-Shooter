package displayObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

//This class represents the letters, numbers and symbols that can be displayed on the LED board. To make it simple, they are
//all called Letters.
public class Letter{
	
	private int length, height;
	private boolean[][] positions;
	
	//Getters for length and height
	int getLength() {return length;}
	int getHeight() {return height;}

	//A Letter consists of a length and a height, which define the dimensions of the positions array.
	private Letter(int length, int height){
		
		this.length = length;
		this.height = height;
		this.positions = new boolean[length][height];
		
		//When a new Letter is created, every entry in the positions array is set to true.
		for(int x=0; x<length; x++){
			for(int y=0; y<height; y++){
				positions[x][y] = true;
			}
		}
	}

	//This method designs every possible Letter that can be displayed on the LED board and converts the given char into one of them
	static Letter convertToLetter(char character){
		
		Letter letter = new Letter(0,0);
		
		//This line converts lower case letters into upper case letters, so that both can be converted into the same Letter
		character = Character.toUpperCase(character);
		
		//This switch statement designs all Letters
		switch(character){
		
		//First the symbols,
		case ' ':
			letter = new Letter(2, 1);
			for(int x=0; x<2; x++){
				letter.positions[x][0] = false;
			}
			break;
			
		case '.':
			letter = new Letter(1, 5);
			for(int y=0; y<4; y++){
				letter.positions[0][y] = false;
			}
			break;
			
		case ':':
			letter = new Letter(1, 5);
			for(int y=0; y<5; y+=2){
				letter.positions[0][y] = false;
			}
			break;
			
		case ',':
			letter = new Letter(2, 6);
			for(int y=0; y<6; y++){
				if(y!=5)letter.positions[0][y] = false;
				if(y!=4)letter.positions[1][y] = false;
			}
			break;

		case '-':
			letter = new Letter(3, 5);
			for(int x=0; x<3; x++){
				for(int y=0; y<5; y++){
					if(y!=2)letter.positions[x][y] = false;
				}
			}
			break;
			
		case '!':
			letter = new Letter(1, 5);
			letter.positions[0][3] = false;
			break;
			
		case '∞':
			letter = new Letter(7,5);
			for(int x=0; x<7; x+=3){
				for(int y=0; y<5; y+=4){
					letter.positions[x][y] = false;
				}
			}
			for(int x=1; x<6; x++){
				for(int y=1; y<4; y++){
					if(x!=3)letter.positions[x][y] = false;
				}
			}
			break;
			
		//then all ten Arabian ciphers
		case '0':
			letter = new Letter(3, 5);
			for(int y=1; y<4; y++){
				letter.positions[1][y] = false;
			}
			break;

		case '1':
			letter = new Letter(3, 5);
			for(int y=0; y<5; y++){
				if(y!=2)letter.positions[0][y] = false;
				if(y!=1)letter.positions[1][y] = false;
			}
			break;

		case '2':
			letter = new Letter(3, 5);
			for(int x=0; x<3; x++){
				if(x!=2)letter.positions[x][1] = false;
				if(x!=0)letter.positions[x][3] = false;
			}
			break;

		case'3':
			letter = new Letter(3, 5);
			for(int x=0; x<3; x++){
				if(x!=2)letter.positions[x][1] = false;
				if(x!=2)letter.positions[x][3] = false;
			}
			break;

		case'4':
			letter = new Letter(3, 5);
			for(int y=0; y<5; y++){
				if(y!= 0 && y!=1 && y!=2)letter.positions[0][y] = false;
				if(y!=2)letter.positions[1][y] = false;
			}
			break;

		case'5':
			letter = new Letter(3, 5);
			for(int x=0; x<3; x++){
				if(x!=0)letter.positions[x][1] = false;
				if(x!=2)letter.positions[x][3] = false;
			}
			break;

		case'6':
			letter = new Letter(3, 5);
			for(int x=0; x<3; x++){
				if(x!=0)letter.positions[x][1] = false;
				if(x!=0 && x!=2)letter.positions[x][3] = false;
			}
			break;

		case'7':
			letter = new Letter(3, 5);
			for(int y=1; y<5; y++){
				letter.positions[0][y] = false;
				letter.positions[1][y] = false;
			}
			break;
		
		case'8':
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			break;

		case'9':
			letter = new Letter(3, 5);
			for(int x=0; x<3; x++){
				if(x!=0 && x!=2)letter.positions[x][1] = false;
				if(x!=2)letter.positions[x][3] = false;
			}
			break;
		
		//and lastly all letters in the Latin alphabet
		case'A':
			letter = new Letter(3, 5);
			letter.positions[0][0] = false;
			letter.positions[2][0] = false;
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[1][4] = false;
			break;
		
			
		case'B':
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[2][0] = false;
			letter.positions[2][2] = false;
			letter.positions[2][4] = false;
			break;
		
			
		case'C':
			letter = new Letter(3, 5);
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			for(int y=1; y<4; y++){
				letter.positions[1][y] = false;
				letter.positions[2][y] = false;
			}
			break;
		
			
		case'D':
			letter = new Letter(3, 5);
			letter.positions[2][0] = false;
			letter.positions[2][4] = false;
			for(int y=1; y<4; y++){
				letter.positions[1][y] = false;
			}
			break;
		
			
		case'E':
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[2][1] = false;
			letter.positions[2][3] = false;
			break;
		
		
		case'F':
			letter = new Letter(3, 5);
			letter.positions[1][1] = false;
			letter.positions[1][3] = false;
			letter.positions[1][4] = false;
			letter.positions[2][1] = false;
			letter.positions[2][3] = false;
			letter.positions[2][4] = false;
			break;
		
		
		case'G':
			letter = new Letter(4, 5);
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			for(int x=1; x<3; x++){
				letter.positions[x][1] = false;
				letter.positions[x][3] = false;
			}
			letter.positions[1][2] = false;
			letter.positions[3][1] = false;
			break;
		
		
		case'H':
			letter = new Letter(3, 5);
			for(int y=0; y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
			}
			break;
		
		//This is the hardest Letter to design :O
		case'I':
			letter = new Letter(1, 5);
			break;
		
		
		case'J':
			letter = new Letter(3, 5);
			for(int y=0; y<4; y++){
				if(y!=3)letter.positions[0][y] = false;
				letter.positions[1][y] = false;
			}
			break;
		
		
		case'K':
			letter = new Letter(4, 5);
			for(int y=1; y<4; y++){
				letter.positions[3][y] = false;
			}
			for(int y=0; y<5; y+=2){
				letter.positions[2][y] = false;
			}
			for(int y=0; y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
			}
			break;
		
		
		case'L':
			letter = new Letter(3, 5);
			for(int y=0; y<4; y++){
				letter.positions[1][y] = false;
				letter.positions[2][y] = false;
			}
			break;
		
		
		case'M':
			letter = new Letter(5, 5);
			for(int y=0; y<5; y++){
				if(y!=1)letter.positions[1][y] = false;
				if(y!=2)letter.positions[2][y] = false;
				if(y!=1)letter.positions[3][y] = false;
			}
			break;
		
			
		case'N':
			letter = new Letter(5, 5);
			for(int y=0; y<5; y++){
				if(y!=1)letter.positions[1][y] = false;
				if(y!=2)letter.positions[2][y] = false;
				if(y!=3)letter.positions[3][y] = false;
			}
			break;

			
		case'O':
			letter = new Letter(4, 5);
			for(int x=1; x<3; x++){
			for(int y=1; y<4; y++){
				letter.positions[x][y] = false;
			}
			}
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			letter.positions[3][0] = false;
			letter.positions[3][4] = false;
			break;

			
		case'P':
			letter = new Letter(3, 5);
			for(int y=1; y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
				if(y!=1 && y!=2)letter.positions[2][y] = false;
			}
			break;

			
		case'Q':
			letter = new Letter(4, 5);
			for(int y=0; y<4; y++){
				if(y!=0 && y!=4)letter.positions[1][y] = false;
				letter.positions[3][y] = false;
			}
			letter.positions[0][0] = false;
			letter.positions[0][4] = false;
			letter.positions[2][0] = false;
			letter.positions[2][4] = false;
			break;

			
		case'R':
			letter = new Letter(3, 5);
			for(int y=1; y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
			}
			letter.positions[2][2] = false;	
			break;

			
		case'S':
			letter = new Letter(3, 5);
			for(int y=0; y<5; y++){
				if(y!=1 && y!=4)letter.positions[0][y] = false;
				if(y%2 !=0)letter.positions[1][y] = false;
				if(y!=0 && y!=3)letter.positions[2][y] = false;
			}
			break;

			
		case'T':
			letter = new Letter(3, 5);
			for(int y=1; y<5; y++){
				letter.positions[0][y] = false;
				letter.positions[2][y] = false;
			}
			break;

			
		case'U':
			letter = new Letter(3, 5);
			for(int y=0; y<4; y++){
				letter.positions[1][y] = false;
			}
			break;

			
		case'V':
			letter = new Letter(3, 5);
			for(int y=0; y<4; y++){
				letter.positions[1][y] = false;
			}
			letter.positions[0][4] = false;
			letter.positions[2][4] = false;
			break;

			
		case'W':
			letter = new Letter(5, 5);
			for(int y=0; y<5; y++){
				if(y!=3)letter.positions[1][y] = false;
				if(y!=2)letter.positions[2][y] = false;
				if(y!=3)letter.positions[3][y] = false;
			}
			break;

			
		case'X':
			letter = new Letter(3, 5);
			for(int y=0; y<5; y++){
				if(y!=2)letter.positions[1][y] = false;
			}
			letter.positions[0][2] = false;
			letter.positions[2][2] = false;
			break;

			
		case'Y':
			letter = new Letter(3, 5);
			for(int y=0; y<2; y++){
				letter.positions[1][y] = false;
			}
			for(int y=2;y<5;y++){
				letter.positions[0][y] = false;
				letter.positions[2][y] = false;
			}
			break;

			
		case'Z':
			letter = new Letter(3, 5);
			for(int y=1; y<4; y++){
				if(y!=3)letter.positions[0][y] = false;
				if(y%2 !=0)letter.positions[1][y] = false;
				if(y!=1)letter.positions[2][y] = false;
			}
			break;
		}

		return letter;
	}
	
	//This is the public method that can display a Letter at a given position and color on (and off) the LED board
	public static void DrawLetterAt(char character, int x, int y, int red, int green, int blue){
		
		//First, the given char gets converted into a Letter
		Letter letter = convertToLetter(character);
		BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
		
		//After that, if the char matches one of the displayable Letters, a spot on the board is colored in the given
		//color for every entry in the positions array that is set to true.
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
	
	//This method colors a Letter completely white for a moment, before changing it back to its original color
	public static void BlinkLetter(char character, int x, int y, int red, int green, int blue, int wait){

		BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
		
		Letter.DrawLetterAt(character, x, y, 127, 127, 127);
		controller.updateLedStripe();
		controller.sleep(wait);
		Letter.DrawLetterAt(character, x, y, red, green, blue);
		controller.updateLedStripe();
		controller.sleep(wait);
		
	}
}
