import java.awt.event.KeyEvent;

import displayObjects.Letter;
import displayObjects.Word;
import gameObjects.EnemyShip;
import gameObjects.Projectile;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.DoubleBoulder;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.MidClasher;


//This is the main class of Project Space Shooter.
//It contains the main method of the program, which starts the intro, let's you begin a game, and boots up the end
//screen once a game ended.
public abstract class Main {
	
	public static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	public static KeyBuffer buffer;

	public static void main(String[] args) {

//		gameStart();
		endscreenTest(false);
//		rainbow();
//		displaytest();
//		Goldenization();
//		shipDiashow();
//		DisplayAlphabet();
//		DisplayCiphers();
//		DisplayLogo(6);

	}

	private static void endscreenTest(boolean won){
		Endscreen.outro(won, new int[]{6, 90, 90});
		controller.resetColors();
		controller.updateLedStripe();
	}
	
	private static void displaytest(){
		
		EnemyShip lalu = new LangerLulatsch(new int[]{7,0}, 1, 1);
		lalu.spawnShip();
		EnemyShip bibo = new DoubleBoulder(new int[]{7,7}, 1, 1);
		bibo.spawnShip();
		
		controller.updateLedStripe();
		
	}

	private static void gameStart(){
		
		buffer = controller.getKeyBuffer();
		
		//Making a sentence to inform the player of the different difficulties
		Word difficultyExplanation = new Word("Choose difficulty. 1 is easy, 2 is medium and 3 is hard. 0 is the Tutorial.");
		
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
				buffer.clear();
				if(event != null){
					if (event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
						
						switch (event.getKeyCode()){
						
						//space makes the SS shoot
						case java.awt.event.KeyEvent.VK_0:
							
							//Pressing 0 starts the tutorial
							Game.start(0);
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
							Game.start(1);
							//After the game is over, all remaining dots on the board are reset
							controller.resetColors();
							
							//These lines make the difficulty settings appear again after the endscreen has been seen
							Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
							Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
							Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
							Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
							
							break;
							
						case java.awt.event.KeyEvent.VK_2:
							
							//Pressing 2 starts medium mode
							Game.start(2);
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
							Game.start(3);
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
				difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
				difficultyExplanation.displayWordAt(x, 0, 97, 17, 2);
			
				//And these two control how fast it is moving. A lower in the sleep method means a faster speed.
				controller.updateLedStripe();
				controller.sleep(100);
			}
		}
	}
	
	private static void rainbow(){
		
		while(true){
			for(int green=0;green<128;green++){
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 127, green, 0);
					}
				}
				controller.updateLedStripe();
			}
			for(int red=127;red>0;red--){
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, red, 127, 0);
					}
				}
				controller.updateLedStripe();
			}
			for(int blue=0;blue<128;blue++){
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 0, 127, blue);
					}
				}
				controller.updateLedStripe();
			}
			for(int green=127;green>0;green--){
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 0, green, 127);
					}
				}
				controller.updateLedStripe();
			}
			for(int red=0;red<128;red++){
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, red, 0, 127);
					}
				}
				controller.updateLedStripe();
			}
			for(int blue=127;blue>0;blue--){
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 127, 0, blue);
					}
				}
				controller.updateLedStripe();
			}
		}
	}
	
	private static void shipDiashow(){
		
		GalaxyDestroyer kunibert = new GalaxyDestroyer(new int[]{0, 0}, 250, 0);
		BossaNova bono = new BossaNova(new int[]{11, 9}, 3, 1);
		MidClasher airwing = new MidClasher(new int[]{0, 9}, 2, 0);
		DefaultShip noob = new DefaultShip(new int[]{5, 11}, 1, 0);
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		EnemyShip lalu = new LangerLulatsch(new int[]{3, 14}, 1, 1);
		EnemyShip bibo = new DoubleBoulder(new int[]{14, 15}, 1, 1);
		
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
		kunibert.spawnShip();
		bono.spawnShip();
		noob.spawnShip();
		airwing.spawnShip();
		lalu.spawnShip();
		bibo.spawnShip();
		controller.updateLedStripe();
	}
	
	private static void Goldenization(){
		
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
		ss.setColorAt(0, 0, 28, 8, 99);
		ss.setColorAt(1, 0, 0, 0, 0);
		ss.setColorAt(2, 0, 28, 8, 99);
		ss.setColorAt(0, 1, 28, 8, 99);
		ss.setColorAt(1, 1, 5, 107, 17);
		ss.setColorAt(2, 1, 28, 8, 99);
		
		ss.spawnShip();
		
		for(int i=1; i<100;i++){
			int[] shipColor = controller.getColorAt(ss.getTopLeftCorner()[0], ss.getTopLeftCorner()[1]);
			ss.setColorAt(0, 0, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.setColorAt(2, 0, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.setColorAt(0, 1, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.setColorAt(2, 1, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.spawnShip();
			
			controller.updateLedStripe();
			controller.sleep(100);
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
				logo.displayWordAt(x+1, line, 0, 0, 0);
				
				logo.displayWordAt(x, line, 97, 17, 2);
				controller.updateLedStripe();
				controller.sleep(125);
			}
		}
	}
	
}
