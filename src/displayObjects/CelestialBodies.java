package displayObjects;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class CelestialBodies {

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);

	public static void displayPlanet(int intensity){

		int[] blue = new int[] { 0, 0, intensity };
		int[] green = new int[] { 0, intensity, 0 };

		for (int x=0; x<20; x++) {
			for (int y=17; y<20; y++) {
				controller.setColor(x, y, blue);
			}
		}
		
		for (int x=1; x<19; x++) {
			controller.setColor(x, 16, blue);
		}
		
		for (int x=3; x<17; x++) {
			controller.setColor(x, 15, blue);
		}
		
		for (int x=6; x<14; x++) {
			controller.setColor(x, 14, blue);
		}
		
		controller.updateLedStripe();

	}
	
	public static void displaySun(int xpos, int ypos, int[] color){
		
		for (int i=-2; i<3; i++) {
			for (int a=-2; a<3; a++) {
				controller.setColor(xpos + i, ypos + a, color);
			}
		}
		for (int x=-1; x<2; x++) {
			controller.setColor(xpos + x, ypos + 3, color);
			controller.setColor(xpos + x, ypos - 3, color);
		}
		for (int y=-1; y<2; y++) {
			controller.setColor(xpos + 3, ypos + y, color);
			controller.setColor(xpos - 3, ypos + y, color);
		}

//		controller.setColor(xpos, ypos - 4, color);
//		controller.setColor(xpos, ypos + 4, color);
//		controller.setColor(xpos - 4, ypos, color);
//		controller.setColor(xpos + 4, ypos, color);
		
	}
}
