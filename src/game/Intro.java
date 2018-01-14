package game;

import java.awt.event.KeyEvent;

import displayObjects.Sun;
import displayObjects.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

public abstract class Intro{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer;
	
	static void gameName(){

		final Word project = new Word("Project");
		final Word space = new Word("Space");
		final Word shooter = new Word("Shooter");
		
		project.displayWordAt(0, 3, 60, 60, 60);
		space.displayWordAt(0, 9, 60, 60, 60);
		shooter.displayWordAt(0, 15, 60, 60, 60);
		
		controller.updateLedStripe();
	}
	
	static void titleScreen(){

		//These Words will be continuously displayed on the title screen
		final Word press = new Word("Press");
		final Word space = new Word("Space");

		KeyBuffer buffer = controller.getKeyBuffer();
		
		press.displayWordAt(0, 4, 127, 127, 0);
		space.displayWordAt(0, 12, 127, 127, 0);
		controller.updateLedStripe();
		
		boolean increase = false;
		int increaseCount = 95;
		int x, y;
		int loopCount = 0;
		while(true){
			
			loopCount++;
			
			//After about 45 seconds, the story screen shows up
			if(loopCount==750){
				controller.resetColors();
				story();
				increase = false;
				increaseCount = 95;
				loopCount = 0;
			}
			
			//All white/grey dots get dimmer
			for(int i=0; i<20; i++){
				for(int j=0; j<20; j++){
					int red = controller.getColorAt(i, j)[0];
					int green = controller.getColorAt(i, j)[1];
					int blue = controller.getColorAt(i, j)[2];
					if(red==green&&green==blue&&blue!=0){
						controller.setColor(i, j, red-3, green-3, blue-3);
					}
				}
			}
			
			//With a chance of 5%, a star spawns at a random location on the board
			if(Math.random()*100<=5){
				
				x = (int) (Math.random()*20);
				y = (int) (Math.random()*20);
				
				//The height of the star is adjusted so that it not coincides with the displayed text
				if(y>=4&&y<=5)y-=2;
				if(y>=6&&y<=8)y+=3;
				if(y>=12&&y<=13)y-=2;
				if(y>=14&&y<=16)y+=3;
				
				//This statement checks if the color of the position the star appears at is black at the moment
				if(controller.getColorAt(x, y)[0]==0&&controller.getColorAt(x, y)[1]==0&&controller.getColorAt(x, y)[2]==0){
					controller.setColor(x, y, 90, 90, 90);
					controller.updateLedStripe();
				}
			}
			
			//The words increase or decrease their color
			if(increase){
				increaseCount++;
				press.displayWordAt(0, 4, increaseCount, increaseCount-15, 0);
				space.displayWordAt(0, 12, increaseCount, increaseCount-15, 0);
				controller.updateLedStripe();
			}
			else{
				increaseCount--;
				press.displayWordAt(0, 4, increaseCount, increaseCount-15, 0);
				space.displayWordAt(0, 12, increaseCount, increaseCount-15, 0);
				controller.updateLedStripe();
			}
			if(increaseCount == 95){
				increase = false;
			}
			if(increaseCount == 63){
				increase = true;
			}
			
			//Pressing space really does break the endless loop
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				if(event.getKeyCode()==java.awt.event.KeyEvent.VK_SPACE){
					controller.resetColors();
					break;
				}
			}
		}
	}

	protected static void story(){

		buffer = controller.getKeyBuffer();
		
		//
		boolean skip = false;
		
		//The uber-amazing story!
		Word year = new Word("5018");
		Word conquer = new Word("The invading forces have conquered almost all defense stations.");
		Word hope = new Word("There is only one last hope...");
		Word spaceShooter = new Word("The Space Shooter!");
		
		int count = 0;
		final int maxCount = 196;
		while(count!=maxCount){

			//The count is increased by one
			count++;
			
			//If skip is activated, the loop is exited
			if(skip)break;
			
			//Pressing any key activates the skip
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				skip = true;
			}
			
			if(count<=maxCount/2){
				year.displayWordAt(2, 7, count, count/4, count/2);
				controller.updateLedStripe();
			}
			else{
				year.displayWordAt(2, 7, maxCount-count, (maxCount-count)/4, (maxCount-count)/2);
				controller.updateLedStripe();
			}
		}
		
		for(int x=20; x>-conquer.getLength(); x--){
			
			//If skip is activated, the loop is exited
			if(skip)break;
			
			//Pressing any key activates the skip
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				skip = true;
			}
			
			//The following two lines make the sentence move one spot to the left
			conquer.displayWordAt(x+1, 0, 0, 0, 0);
			conquer.displayWordAt(x, 0, 96, 87, 12);
			
			//And these two control how fast it is moving. A lower integer in the sleep method means a faster speed.
			controller.updateLedStripe();
			controller.sleep(125);
		}
		
		for(int x=20; x>-hope.getLength(); x--){
			
			//If skip is activated, the loop is exited
			if(skip)break;
			
			//Pressing any key activates the skip
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				skip = true;
			}
			
			//The following two lines make the sentence move one spot to the left
			hope.displayWordAt(x+1, 0, 0, 0, 0);
			hope.displayWordAt(x, 0, 96, 87, 12);
			
			//And these two control how fast it is moving. A lower integer in the sleep method means a faster speed.
			controller.updateLedStripe();
			controller.sleep(125);
		}
		
		for(int x=20; x>-spaceShooter.getLength(); x--){
			
			//If skip is activated, the loop is exited
			if(skip)break;
			
			//Pressing any key activates the skip
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				skip = true;
			}
			
			//The following two lines make the sentence move one spot to the left
			spaceShooter.displayWordAt(x+1, 0, 0, 0, 0);
			spaceShooter.displayWordAt(x, 0, 96, 87, 12);
			
			//And these two control how fast it is moving. A lower integer in the sleep method means a faster speed.
			controller.updateLedStripe();
			controller.sleep(125);
		}
		controller.resetColors();
	}

	public static void makeBlue() {

		int[] blue = new int[] { 0, 0, 100 };
		int[] g = new int[] { 127, 127, 0 };
		int[] black = new int[] { 0, 0, 0 };

		for (int x = 0; x < 20; x++) {
			for (int y = 15; y < 20; y++) {
				controller.setColor(x, y, blue);
			}
		}
		for (int x = 1; x < 19; x++) {
			controller.setColor(x, 14, blue);
		}
		for (int x = 2; x < 18; x++) {
			controller.setColor(x, 13, blue);
		}
		for (int x = 3; x < 17; x++) {
			controller.setColor(x, 12, blue);
		}
		controller.updateLedStripe();

	}

	public static void sun() {

		int[] blue = new int[] { 0, 0, 100 };
		int[] g = new int[] { 127, 127, 0 };
		int[] black = new int[] { 0, 0, 0 };

		makeBlue();
		controller.updateLedStripe();
		int xpos = 10;
		int loopCount = 0;
		for (int ypos = 20; ypos > -5; ypos--) {
			loopCount = 0;
			while (loopCount != 1000) {
				loopCount++;
			}
			for (int i = -2; i < 3; i++) {
				for (int a = -2; a < 3; a++) {
					if (ypos + a > 12) {
						controller.setColor(xpos + i, ypos + a, blue);
						break;
					}
					controller.setColor(xpos + i, ypos + a, g);
				}
			}
			controller.setColor(xpos, ypos - 3, g);
			controller.setColor(xpos, ypos + 3, g);
			controller.setColor(xpos - 3, ypos, g);
			controller.setColor(xpos + 3, ypos, g);
			makeBlue();
			controller.updateLedStripe();
			loopCount = 0;
			while (loopCount != 1000) {
				loopCount++;
			}
			for (int i = -2; i < 3; i++) {
				for (int a = -2; a < 3; a++) {
					if (ypos + a > 12) {
						controller.setColor(xpos + i, ypos + a, blue);
						break;
					}
					controller.setColor(xpos + i, ypos + a, black);
				}
			}
			controller.setColor(xpos, ypos - 3, black);
			controller.setColor(xpos, ypos + 3, black);
			controller.setColor(xpos - 3, ypos, black);
			controller.setColor(xpos + 3, ypos, black);
			makeBlue();
			controller.updateLedStripe();
			loopCount = 0;
		}
		controller.resetColors();
	}

}
