package displayObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

//In this class, display methods for a sun and a planet can be found. They are mostly used in the intro, on the logo screen.
public abstract class CelestialBodies {

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);

	public static void displayPlanet(int intensity){

		int[] blue = new int[] { 0, 0, intensity};
		int[] green = new int[] { 0, intensity, intensity/3};

		//Line #1
		for(int x=0; x<20; x++) {
			controller.setColor(x, 19, blue);
		}
		for(int x=3; x<18; x++) {
			if(x!=11&&x!=12&&x!=13&&x!=14)controller.setColor(x, 19, green);
		}
		
		//Line #2
		for(int x=0; x<20; x++) {
			controller.setColor(x, 18, blue);
		}
		for(int x=1; x<18; x++) {
			if(x!=5&&x!=11&&x!=12&&x!=13&&x!=14)controller.setColor(x, 18, green);
		}
		
		//Line #3
		for(int x=0; x<20; x++) {
			controller.setColor(x, 17, blue);
		}
		for(int x=0; x<19; x++) {
			if(x!=4&&x!=5&&x!=11&&x!=12&&x!=13&&x!=14)controller.setColor(x, 17, green);
		}
				
		//Line #4
		for(int x=9; x<15; x++) {
			if(x!=12)controller.setColor(x, 16, blue);
		}
		for(int x=1; x<19; x++) {
			if(x!=9&&x!=10&&x!=11&&x!=13&&x!=14)controller.setColor(x, 16, green);
		}
		
		//Line #5
		for(int x=8; x<16; x++) {
			controller.setColor(x, 15, blue);
		}
		for(int x=3; x<17; x++) {
			if(x==8){
				x=15;
			}
			else{
				controller.setColor(x, 15, green);
			}
		}
		
		//Line #6
		for(int x=6; x<14; x++) {
			controller.setColor(x, 14, blue);
		}
		
		controller.updateLedStripe();

	}
	
	public static void displaySun(int xpos, int ypos, int[] color){
		
		for(int x=-2; x<3; x++) {
			for(int y=-2; y<3; y++) {
				controller.setColor(xpos + x, ypos + y, color);
			}
		}
		for(int x=-1; x<2; x++) {
			controller.setColor(xpos + x, ypos + 3, color);
			controller.setColor(xpos + x, ypos - 3, color);
		}
		for(int y=-1; y<2; y++) {
			controller.setColor(xpos + 3, ypos + y, color);
			controller.setColor(xpos - 3, ypos + y, color);
		}

//		controller.setColor(xpos, ypos - 4, color);
//		controller.setColor(xpos, ypos + 4, color);
//		controller.setColor(xpos - 4, ypos, color);
//		controller.setColor(xpos + 4, ypos, color);
		
	}
}
