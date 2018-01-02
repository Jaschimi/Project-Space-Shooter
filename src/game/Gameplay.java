package game;
import difficulties.Easy;
import difficulties.Medium;
import difficulties.Hard;
import difficulties.Tutorial;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;

//This class controls everything that happens during a game
public abstract class Gameplay{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	protected static void start(int difficulty){
		
		//First of all, the LED Board is reset so that all text still displayed is removed
		controller.resetColors();
		
		//This boolean determines if the game has been won
		boolean won = false;

		//Create the SpaceShooter with three lifes, three ammo and a spawning location on the bottom mid of the board
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
		//These if statements start a game based on what difficulty has been chosen in the main method
		if(difficulty == 0){
			ss.setColorAt(0, 0, 107, 0, 127);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 107, 0, 127);
			ss.setColorAt(0, 1, 107, 0, 127);
			ss.setColorAt(1, 1, 5, 107, 17);
			ss.setColorAt(2, 1, 107, 0, 127);
			Tutorial.start(ss);
		}
		else{
			if(difficulty == 1){
				ss.setColorAt(0, 0, 0, 127, 107);
				ss.setColorAt(1, 0, 0, 0, 0);
				ss.setColorAt(2, 0, 0, 127, 107);
				ss.setColorAt(0, 1, 0, 127, 107);
				ss.setColorAt(1, 1, 5, 107, 17);
				ss.setColorAt(2, 1, 0, 127, 107);
				won = Easy.start(ss);
			}
			else{
				if(difficulty == 2){
					for(int x=0; x<3; x++){
						for(int y=0; y<2; y++){
							if(x==1 && y==1)ss.setColorAt(1, 1, 5, 107, 17);
							else{
								if(y==0 && x==1)ss.setColorAt(x, y, 0, 0, 0);
								else{
									ss.setColorAt(x, y, 99, 28, 29);
								}
							}
						}
					}
					won = Medium.start(ss);
				}
				else{
					if(difficulty == 3){
						ss.setColorAt(0, 0, 28, 8, 99);
						ss.setColorAt(1, 0, 0, 0, 0);
						ss.setColorAt(2, 0, 28, 8, 99);
						ss.setColorAt(0, 1, 28, 8, 99);
						ss.setColorAt(1, 1, 5, 107, 17);
						ss.setColorAt(2, 1, 28, 8, 99);
						won = Hard.start(ss);
					}
				}
			}
			Endscreen.outro(won, controller.getColorAt(ss.getTopLeftCorner()[0], ss.getTopLeftCorner()[1]));
		}
	}
}