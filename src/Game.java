import difficulties.Easy;
import difficulties.Medium;
import difficulties.Hard;
import difficulties.Tutorial;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;

//This class controls everything that happens during a game
public abstract class Game{

	public static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	protected static boolean start(int difficulty){
		//small chaange
		
		//First of all, the LED Board is reset so that all text still displayed is removed
		controller.resetColors();
		
		//This boolean determines if the game has been won
		boolean won = false;

		//Create the SpaceShooter with three lifes, three ammo and a spawning location on the bottom mid of the board
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
		//Design every entry in the SpaceShooters positions array
		double random =  Math.random()*3;
		if(random<=1){
			for(int x=0; x<3; x++){
				for(int y=0; y<2; y++){
					if(x==1 && y==1)ss.setColorAt(1, 1, 5, 97, 17);
					else{
						if(y==0 && x==1)ss.setColorAt(x, y, 0, 0, 0);
						else{
							ss.setColorAt(x, y, 87, 18, 18);
						}
					}
				}
			}
		}
		if(random>1 && random<=2){
			ss.setColorAt(0, 0, 127, 107, 0);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 127, 107, 0);
			ss.setColorAt(0, 1, 127, 107, 0);
			ss.setColorAt(1, 1, 5, 117, 17);
			ss.setColorAt(2, 1, 127, 107, 0);
		}
		if(random>2){
			ss.setColorAt(0, 0, 107, 0, 127);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 107, 0, 127);
			ss.setColorAt(0, 1, 107, 0, 127);
			ss.setColorAt(1, 1, 5, 107, 17);
			ss.setColorAt(2, 1, 107, 0, 127);
		}
		
		//These if statements start a game based on what difficulty has been chosen in the main method
		if(difficulty == 0){
			won = Tutorial.start(ss);
		}
		else{
			if(difficulty == 1){
				won = Easy.start(ss);
			}
			else{
				if(difficulty == 2){
					won = Medium.start(ss);
				}
				else{
					if(difficulty == 3){
						won = Hard.start(ss);
					}
				}
			}
		}
		return won;
				
	}
}