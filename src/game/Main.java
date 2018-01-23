package game;
import java.awt.event.KeyEvent;
import java.io.Console;

import difficulties.Endless;
import displayObjects.Letter;
import displayObjects.Word;
import gameObjects.EnemyShip;
import gameObjects.Projectile;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.UFO;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.BigBoulder;

//This is the main class of Project Space Shooter.
//It contains the main method of the program, which starts the launch method (a way to restart the main method when needed)
//and let's you begin a game.
//Additionally, many testing methods exist in this class.
public abstract class Main{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer = controller.getKeyBuffer();
	final static Word word = new Word("Horizon");

	public static void main(String[] args) {
		
		boolean restart;
		//When the Main.launch() method returns true, it is started again
        do{
            restart = launch();
        }
        while(restart);

	}
	
	public static boolean launch() {

		boolean restart = false;

//		restart = gameStart();
//		randomColor();
//		gameName();
//		introStart();
//		sunrise();
//		story();
//		nestTest();
		endscreenTest(true, 2, 28, 38, 99);
//		rainbow();
//		Goldenization();
//		shipDiashow();
//		DisplayAlphabet();
//		DisplayCiphers();
//		DisplayLogo(6, word);

		return restart;
	}
	
	private static boolean gameStart(){
		
		buffer.clear();
		KeyEvent event = null;
		
		//For presentations and such, the following lines can be used to start the game with a keystroke
		while(event==null){
			event = buffer.pop();
		}
		event = null;
		
		//At the beginning, there was an intro...
//		introStart();
		
		//Making a sentence to inform the player of the different difficulties
		final Word difficultyExplanation = new Word("Choose difficulty. 1 is easy, 2 is medium and 3 is hard. 0 is the Tutorial.");
		
		//Displaying the four difficulty settings
		
		if(Gameplay.endless){
			Letter.DrawLetterAt('E', 2, 7, 107, 127, 0);
		}
		Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
		Letter.DrawLetterAt('C', 16, 7, 0, 127, 107);
		Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
		Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
		Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
		
		while(true){
			
			buffer.clear();
			//This loop repeats every time the difficulty settings sentence reaches the left end of the board
			for(int x=20; x>-difficultyExplanation.getLength() ;x--){
				
				//The following two lines make the sentence move one spot to the left
				difficultyExplanation.displayWordAt(x+1, 0, 0, 0, 0);
				difficultyExplanation.displayWordAt(x, 0, 126, 30, 9);
			
				//And these two control how fast it is moving. A lower integer in the sleep method means a faster speed.
				controller.updateLedStripe();
				controller.sleep(100);
				
				event = buffer.pop();
				buffer.clear();
				if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
					
					int blinkWait=250;
					switch(event.getKeyCode()){
					
					default:
						
						continue;
					
					//space makes the SS shoot
					case java.awt.event.KeyEvent.VK_0:
						
						//The explanation disappears
						difficultyExplanation.displayWordAt(x, 0, 0, 0, 0);
						
						//The pressed option blinks a few times
						Letter.BlinkLetter('0', 9, 7, 127, 0, 127, blinkWait);
						Letter.BlinkLetter('0', 9, 7, 127, 0, 127, blinkWait);
						
						//Pressing 0 starts the tutorial
						Gameplay.start(0);
						
						break;
						
					case java.awt.event.KeyEvent.VK_1:

						//The explanation disappears
						difficultyExplanation.displayWordAt(x, 0, 0, 0, 0);
						
						//The pressed option blinks a few times
						Letter.BlinkLetter('1', 2, 14, 0, 127, 0, blinkWait);
						Letter.BlinkLetter('1', 2, 14, 0, 127, 0, blinkWait);
						
						//Pressing 1 starts easy mode
						Gameplay.start(1);
						
						break;
						
					case java.awt.event.KeyEvent.VK_2:

						//The explanation disappears
						difficultyExplanation.displayWordAt(x, 0, 0, 0, 0);
						
						//The pressed option blinks a few times
						Letter.BlinkLetter('2', 9, 14, 0, 0, 127, blinkWait);
						Letter.BlinkLetter('2', 9, 14, 0, 0, 127, blinkWait);
						
						//Pressing 2 starts medium mode
						Gameplay.start(2);
						
						break;
						
					case java.awt.event.KeyEvent.VK_3:

						//The explanation disappears
						difficultyExplanation.displayWordAt(x, 0, 0, 0, 0);
						controller.updateLedStripe();
						
						//The pressed option blinks a few times
						Letter.BlinkLetter('3', 16, 14, 127, 0, 0, blinkWait);
						Letter.BlinkLetter('3', 16, 14, 127, 0, 0, blinkWait);
						
						//Pressing 3 starts hard mode
						Gameplay.start(3);
						
						break;
						
					case java.awt.event.KeyEvent.VK_C:

						//The explanation disappears
						difficultyExplanation.displayWordAt(x, 0, 0, 0, 0);
						controller.updateLedStripe();
						
						//The pressed option blinks a few times
						Letter.BlinkLetter('C', 16, 7, 0, 127, 107, blinkWait);
						Letter.BlinkLetter('C', 16, 7, 0, 127, 107, blinkWait);
						
						//Pressing C shows the credits
						Endscreen.credits();
						
						break;
						
					case java.awt.event.KeyEvent.VK_E:

						if(!Gameplay.endless)continue;
						
						//The explanation disappears
						difficultyExplanation.displayWordAt(x, 0, 0, 0, 0);
						controller.updateLedStripe();
						
						//The pressed option blinks a few times
						Letter.BlinkLetter('E', 2, 7, 107, 127, 0, blinkWait);
						Letter.BlinkLetter('E', 2, 7, 107, 127, 0, blinkWait);
						
						//Pressing C shows the credits
						Gameplay.start(Integer.MAX_VALUE);
						
						break;
						
					//Pressing back space returns to the title screen
					case java.awt.event.KeyEvent.VK_BACK_SPACE:
						Intro.titleScreen();
						break;
						
					//Pressing enter restarts the game
					case java.awt.event.KeyEvent.VK_ENTER:
						controller.resetColors();
						controller.updateLedStripe();
						return true;
					}
					//After the chosen option is over, all remaining dots on the board are reset
					controller.resetColors();
					
					//These lines make the options appear again after the endscreen has been seen
					if(Gameplay.endless){
						Letter.DrawLetterAt('E', 2, 7, 107, 127, 0);
					}
					Letter.DrawLetterAt('0', 9, 7, 127, 0, 127);
					Letter.DrawLetterAt('C', 16, 7, 0, 127, 107);
					Letter.DrawLetterAt('1',  2, 14, 0, 127, 0);
					Letter.DrawLetterAt('2',  9, 14, 0, 0, 127);
					Letter.DrawLetterAt('3', 16, 14, 127, 0, 0);
					
					//This break restarts the difficulty explanation
					break;
				}
			}
		}
	}

	//With the following methods, a variety of different things can be tested

	//This method starts the whole intro sequence
	private static void introStart(){
		sunrise();
		gameName();
		Intro.titleScreen();
	}

	//Show the rising sun on the horizon, our logo
	private static void sunrise(){
		Intro.logoScreen();
	}

	//This shows the name of the game
	private static void gameName(){
		Intro.gameName();
	}
	
	//This displays the story
	static void story(){
		Intro.story();
	}
	
	//See the winning/losing animation
	private static void endscreenTest(boolean won, int difficulty, int red, int green, int blue){
		Endscreen.outro(won, new int[]{red, green, blue}, difficulty);
		controller.resetColors();
		controller.updateLedStripe();
	}
	
	//Display a random color on the whole board alongside its color code every time a key is pressed
	static void randomColor(){
		buffer = controller.getKeyBuffer();
		while(true){
			int red = (int) (Math.random()*128), green = (int) (Math.random()*128), blue = (int) (Math.random()*128);
			System.out.println("\n" + "Red: " + red + "\n" + "Green: " + green + "\n" + "Blue: " + blue);
			controller.setBackgroundColor(red, green, blue);
			controller.resetColors();
			controller.updateLedStripe();
			KeyEvent event = null;
			while(true){
				event = buffer.pop();
				if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
					if(event.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
						controller.setBackgroundColor(0, 0, 0);
						controller.resetColors();
						controller.updateLedStripe();
						return;
					}
					break;
				}
			}
			buffer.clear();
		}
		
	}
	
	//Show the golden version of the DefaultShip
	static void nestTest(){
		
		controller.resetColors();
		
		EnemyShip goldie = new DefaultShip.GoldenVersion(new int[]{0, 0}, 4, 4);
		
		goldie.spawn();
		controller.updateLedStripe();
		
		KeyEvent event = null;
		while(event==null){
			event = buffer.pop();
		}
		buffer.clear();
	}

	//Display a rainbow on the whole board
	static void rainbow(){
		
		KeyEvent event = null;
		while(true){
			for(int green=0;green<128;green++){
				event = buffer.pop();
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 127, green, 0);
					}
				}
				if(event!=null)return;
				buffer.clear();
				controller.updateLedStripe();
			}
			for(int red=127;red>0;red--){
				event = buffer.pop();
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, red, 127, 0);
					}
				}
				if(event!=null)return;
				buffer.clear();
				controller.updateLedStripe();
			}
			for(int blue=0;blue<128;blue++){
				event = buffer.pop();
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 0, 127, blue);
					}
				}
				if(event!=null)return;
				buffer.clear();
				controller.updateLedStripe();
			}
			for(int green=127;green>0;green--){
				event = buffer.pop();
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 0, green, 127);
					}
				}
				if(event!=null)return;
				buffer.clear();
				controller.updateLedStripe();
			}
			for(int red=0;red<128;red++){
				event = buffer.pop();
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, red, 0, 127);
					}
				}
				if(event!=null)return;
				buffer.clear();
				controller.updateLedStripe();
			}
			for(int blue=127;blue>0;blue--){
				event = buffer.pop();
				for(int x=0; x<20; x++){
					for(int y=0; y<20; y++){
						controller.setColor(x, y, 127, 0, blue);
					}
				}
				if(event!=null)return;
				buffer.clear();
				controller.updateLedStripe();
			}
		}
	}
	
	//With this method, all of the ships in the game can be displayed on the board at the same time
	static void shipDiashow(){

		controller.resetColors();
		
		GalaxyDestroyer gade = new GalaxyDestroyer(new int[]{0, 0}, 250, 0);
		BossaNova bono = new BossaNova(new int[]{10, 9}, 3, 1);
		BigBoulder bibo = new BigBoulder(new int[]{0, 9}, 2, 0);
		LangerLulatsch lalu = new LangerLulatsch(new int[]{5, 11}, 1, 1);
		UFO ufo = new UFO(new int[]{13, 15}, 1, 1);
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
		
		KeyEvent event = null;
		while(event==null){
			event = buffer.pop();
		}
		buffer.clear();
	}
	
	//You can see the Space Shooter change color from teal to gold with this method
	static void Goldenization(){

		controller.resetColors();
		
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
	static void DisplayAlphabet(){

		controller.resetColors();
		
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
	static void DisplayCiphers(){

		controller.resetColors();
		
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
	static void DisplayLogo(int line, Word logo){

		controller.resetColors();
		
		KeyEvent event = null;
		while(true){
			for(int x=20; x>-logo.getLength() ;x--){

				event = buffer.pop();
				logo.displayWordAt(x+1, line, 0, 0, 0);
				logo.displayWordAt(x, line, 97, 17, 2);
				
				controller.updateLedStripe();
				controller.sleep(125);
				if(event!=null){
					buffer.clear();
					return;
				}
			}
		}
	}
	
}
