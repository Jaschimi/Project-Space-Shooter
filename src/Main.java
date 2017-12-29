import java.awt.event.KeyEvent;

import displayObjects.Letter;
import displayObjects.Word;
import gameObjects.EnemyShip;
import gameObjects.Projectile;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.DefaultShip;
import ufos.GalaxyDestroyer;
import ufos.MidClasher;


//This is the main class of Project Space Shooter. I WANT TO PLAY TETRIS!!!!
//It contains the main method of the program, which starts the intro, let's you begin a game, and boots up the end
//screen once a game ended.
public abstract class Main {
	
	public static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	public static KeyBuffer buffer;

	public static void main(String[] args) {

		gameStart();
//		showGalaxyDestroyer();
//		DisplayAlphabet();
//		DisplayCiphers();
//		DisplayLogo(6);

	}
	
	private static void showGalaxyDestroyer(){
		GalaxyDestroyer kunibert = new GalaxyDestroyer(new int[]{0, 0}, 250, 0);
		MidClasher airwing = new MidClasher(new int[]{1, 12}, 2, 0);
		DefaultShip noob = new DefaultShip(new int[]{12, 10}, 1, 0);
		SpaceShooter ss = new SpaceShooter(new int[]{3, 18}, 3, 3);
		double random =  Math.random()*3;
		if(random<=2){
			ss.setColorAt(0, 0, 127, 107, 0);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 127, 107, 0);
			ss.setColorAt(0, 1, 127, 107, 0);
			ss.setColorAt(1, 1, 5, 117, 17);
			ss.setColorAt(2, 1, 127, 107, 0);
		}
		else{
			if(random>2){
			ss.setColorAt(0, 0, 107, 0, 127);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 107, 0, 127);
			ss.setColorAt(0, 1, 107, 0, 127);
			ss.setColorAt(1, 1, 5, 107, 17);
			ss.setColorAt(2, 1, 107, 0, 127);
		}
		}
		ss.spawnShip();
		noob.spawnShip();
		airwing.spawnShip();
		kunibert.spawnShip();
		controller.updateLedStripe();
	}
	
	private static void gameStart(){
		
		buffer = controller.getKeyBuffer();
		
		//Making a sentence to inform the player of the different difficulties
		Word difficultyExplanation = new Word("Choose difficulty. 1 is easy, 2 is medium and 3 is hard. 0 is the Tutorial.");
		
		Word you = new Word("you");
		Word win = new Word("win");
		Word lose = new Word ("lose");
		
		//Displaying the four difficulty settings
		Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
		Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
		Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
		Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
		
		//This boolean determines which endscreen will be shown after a game has been played
		boolean won;
		
		while(true){
			//This loop repeats every time the difficulty settings sentence reaches the left end of the board
			for(int x=20; x>-difficultyExplanation.getLength() ;x--){
				
				KeyEvent event = buffer.pop();
				if(event != null){
					if (event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
						
						switch (event.getKeyCode()){
						
						//space makes the SS shoot
						case java.awt.event.KeyEvent.VK_0:
							
							//Pressing 0 starts the tutorial
							won = Game.start(0);
							//After the game is over, all remaining dots on the board are reset
							controller.resetColors();
							
							//These lines make the difficulty settings appear again after the endscreen has been seen
							Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
							Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
							Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
							Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
							
							break;
							
						case java.awt.event.KeyEvent.VK_1:
							
							//Pressing 1 starts easy mode
							won = Game.start(1);
							//After the game is over, all remaining dots on the board are reset
							controller.resetColors();
							if(won){
								you.displayWord(3, 2, 127, 107, 0);
								win.displayWord(2, 10, 127, 107, 0);
								controller.updateLedStripe();
								controller.sleep(2147483647);
							}
							else{
								you.displayWord(2, 2, 107, 0, 127);
								lose.displayWord(0, 9, 107, 0, 127);
								controller.updateLedStripe();
								controller.sleep(3000);
							}
							controller.resetColors();
							
							//These lines make the difficulty settings appear again after the endscreen has been seen
							Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
							Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
							Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
							Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
							
							break;
							
						case java.awt.event.KeyEvent.VK_2:
							
							//Pressing 2 starts medium mode
							won = Game.start(2);
							//After the game is over, all remaining dots on the board are reset
							controller.resetColors();
							
							//These lines make the difficulty settings appear again after the endscreen has been seen
							Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
							Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
							Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
							Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
							
							break;
							
						case java.awt.event.KeyEvent.VK_3:
							
							//Pressing 3 starts hard mode
							won = Game.start(3);
							//After the game is over, all remaining dots on the board are reset
							controller.resetColors();
							
							//These lines make the difficulty settings appear again after the endscreen has been seen
							Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
							Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
							Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
							Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
							
							break;
						}
					}
				}
				//The following two lines make the sentence move one spot to the left
				difficultyExplanation.displayWord(x+1, 0, 0, 0, 0);
				difficultyExplanation.displayWord(x, 0, 97, 17, 2);
			
				//And these two control how fast it is moving. A lower in the sleep method means a faster speed.
				controller.updateLedStripe();
				controller.sleep(125);
			}
		}
	}

	private static void DisplayAlphabet(){
		
		char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0; i<alphabet.length; i++){
			int red = (int) (127*Math.random()+1);
			int green = (int) (127*Math.random()+1);
			int blue = (int) (127*Math.random()+1);
			Letter.DrawLetterAt(alphabet[i], 0, 0, red, green, blue);
			controller.updateLedStripe();
			controller.sleep(1000);
			Letter.DrawLetterAt(alphabet[i], 0, 0, 0, 0, 0);
			controller.updateLedStripe();
		}
	
	}
	
	private static void DisplayCiphers(){
		
		char[] ciphers = {'0','1','2','3','4','5','6','7','8','9'};
		for(int i=0; i<ciphers.length; i++){
			int red = (int) (127*Math.random()+1);
			int green = (int) (127*Math.random()+1);
			int blue = (int) (127*Math.random()+1);
			Letter.DrawLetterAt(ciphers[i], 0, 0, red, green, blue);
			controller.updateLedStripe();
			controller.sleep(1000);
			Letter.DrawLetterAt(ciphers[i], 0, 0, 0, 0, 0);
			controller.updateLedStripe();
		}
	
	}
	
	private static void DisplayLogo(int line){
		
		Word logo = new Word("Andrea   ist   cool");
		
		while(true){
			for(int x=20; x>-logo.getLength() ;x--){
				logo.displayWord(x+1, line, 0, 0, 0);
				
				logo.displayWord(x, line, 97, 17, 2);
				controller.updateLedStripe();
				controller.sleep(125);
			}
		}
	}
	
}
