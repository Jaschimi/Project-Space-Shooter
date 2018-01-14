package game;
import java.awt.event.KeyEvent;

import displayObjects.Letter;
import displayObjects.Word;
import gameObjects.EnemyShip;
import gameObjects.Projectile;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.UnnervingFloatingOctopus;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.BigBoulder;


//This is the main class of Project Space Shooter.
//It contains the main method of the program, which starts the intro and let's you begin a game.
//Additionally, many testing methods exist in this class.
public abstract class Main{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer;
	private static Word word = new Word("Horizon");

	public static void main(String[] args) {
//		gameStart();
//		introStart();
		sunStart();
//		story();
//		nestTest();
//		endscreenTest(true);
//		rainbow();
//		Goldenization();
//		shipDiashow();
//		DisplayAlphabet();
//		DisplayCiphers();
//		DisplayLogo(6, word);

	}
	
	private static void gameStart(){

		introStart();
		
		buffer = controller.getKeyBuffer();
		
		//Making a sentence to inform the player of the different difficulties
		final Word difficultyExplanation = new Word("Choose difficulty. 1 is easy, 2 is medium and 3 is hard. 0 is the Tutorial.");
		
		//Displaying the four difficulty settings
		Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
		Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
		Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
		Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
		
		while(true){
			//This loop repeats every time the difficulty settings sentence reaches the left end of the board
			for(int x=20; x>-difficultyExplanation.getLength() ;x--){
				
				KeyEvent event = buffer.pop();
				buffer.clear();
				if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
					
					int wait=250;
					switch (event.getKeyCode()){
					
					//space makes the SS shoot
					case java.awt.event.KeyEvent.VK_0:
						
						//The explanation disappears,
						difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
						
						//The pressed option blinks a few times
						Letter.DrawLetterAt('0', 9, 7, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('0', 9, 7, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						
						//Pressing 0 starts the tutorial
						Gameplay.start(0);
						
						break;
						
					case java.awt.event.KeyEvent.VK_1:

						//The explanation disappears,
						difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
						
						//The pressed option blinks a few times
						Letter.DrawLetterAt('1', 2, 14, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('1', 2, 14, 0, 127, 0);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('1', 2, 14, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('1', 2, 14, 0, 127, 0);
						controller.updateLedStripe();
						controller.sleep(wait);
						
						//Pressing 1 starts easy mode
						Gameplay.start(1);
						
						break;
						
					case java.awt.event.KeyEvent.VK_2:

						//The explanation disappears,
						difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
						
						//The pressed option blinks a few times
						Letter.DrawLetterAt('2',  9, 14, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('2',  9, 14, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						
						//Pressing 2 starts medium mode
						Gameplay.start(2);
						
						break;
						
					case java.awt.event.KeyEvent.VK_3:

						//The explanation disappears,
						difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
						controller.updateLedStripe();
						
						//The pressed option blinks a few times
						Letter.DrawLetterAt('3', 16, 14, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('3', 16, 14, 127, 127, 127);
						controller.updateLedStripe();
						controller.sleep(wait);
						Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
						controller.updateLedStripe();
						controller.sleep(wait);
						
						//Pressing 3 starts hard mode
						Gameplay.start(3);
						
						break;
					}
					//After the game is over, all remaining dots on the board are reset
					controller.resetColors();
					
					//These lines make the difficulty settings appear again after the endscreen has been seen
					Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
					Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
					Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
					Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
					
					//This break restarts the difficulty explanation
					break;
				}
				//The following two lines make the sentence move one spot to the left
				difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
				difficultyExplanation.displayWordAt(x, 0, 97, 17, 2);
			
				//And these two control how fast it is moving. A lower integer in the sleep method means a faster speed.
				controller.updateLedStripe();
				controller.sleep(100);
			}
		}
	}

	private static void story(){
		Intro.story();
	}
	
	private static void introStart(){
		Intro.sun();
		Intro.titleScreen();
	}
	private static void sunStart(){
		Intro.sun();
	}
	
	//With the following methods, a variety of different things can be tested
	
	private static void nestTest(){
		
		EnemyShip goldie = new DefaultShip.GoldenVersion(new int[]{0, 0}, 4, 4);
		
		goldie.spawn();
		controller.updateLedStripe();
	}

	//See the winning/losing animation
	private static void endscreenTest(boolean won){
		Endscreen.outro(won, new int[]{6, 90, 90});
		controller.resetColors();
		controller.updateLedStripe();
	}
	
	//Display a rainbow on the whole board
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
	
	//With this method, all of the ships in the game can be displayed on the board at the same time
	private static void shipDiashow(){
		
		GalaxyDestroyer gade = new GalaxyDestroyer(new int[]{0, 0}, 250, 0);
		BossaNova bono = new BossaNova(new int[]{10, 9}, 3, 1);
		BigBoulder bibo = new BigBoulder(new int[]{0, 9}, 2, 0);
		LangerLulatsch lalu = new LangerLulatsch(new int[]{5, 11}, 1, 1);
		UnnervingFloatingOctopus ufo = new UnnervingFloatingOctopus(new int[]{13, 15}, 1, 1);
		DefaultShip noob = new DefaultShip(new int[]{3, 16}, 1, 0);
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
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
		ss.spawn();
		gade.spawn();
		bono.spawn();
		noob.spawn();
		bibo.spawn();
		lalu.spawn();
		ufo.spawn();
		controller.updateLedStripe();
	}
	
	//You can see the Space Shooter change color from teal to gold with this method
	private static void Goldenization(){
		
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
		ss.setColorAt(0, 0, 28, 8, 99);
		ss.setColorAt(1, 0, 0, 0, 0);
		ss.setColorAt(2, 0, 28, 8, 99);
		ss.setColorAt(0, 1, 28, 8, 99);
		ss.setColorAt(1, 1, 5, 107, 17);
		ss.setColorAt(2, 1, 28, 8, 99);
		
		ss.spawn();
		
		for(int i=1; i<100;i++){
			int[] shipColor = controller.getColorAt(ss.getTopLeftCorner()[0], ss.getTopLeftCorner()[1]);
			ss.setColorAt(0, 0, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.setColorAt(2, 0, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.setColorAt(0, 1, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.setColorAt(2, 1, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
			ss.spawn();
			
			controller.updateLedStripe();
			controller.sleep(100);
		}
	}
	
	//This method displays all 26 letters of the Latin alphabet on the board
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
	
	//This letter displays all 10 Arabian ciphers on the board
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
	
	//This method lets a string move from right to left on the board
	private static void DisplayLogo(int line, Word logo){
		
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
