package game;

import java.awt.event.KeyEvent;

import displayObjects.CelestialBodies;
import displayObjects.Letter;
import displayObjects.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

public abstract class Intro{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer;


	//This method displays our team name on a stylized screen
	public static void logoScreen(){

//		final Word sw = new Word("A long time ago in a galaxy far, far away...");
//		for(int x=20; x>-sw.getLength() ;x--){
//			sw.displayWordAt(x+1, 5, 0, 0, 0);
//			sw.displayWordAt(x, 5,0, 0, 100);
//			controller.updateLedStripe();
//			controller.sleep(100);
//		}

		int[] yellow = new int[] { 127, 127, 0 };
		int[] black = new int[] { 0, 0, 0 };

		int x = 16;
		int y = 21;
		int intensity = 30;
		
		//The following loop makes a sun rise behind a planet
		for(int count=0; count<177; count++){

			if(count%5==0){
				y--;
				CelestialBodies.displaySun(x, y+1, black);
				CelestialBodies.displaySun(x, y, yellow);
			}

			CelestialBodies.displayPlanet(intensity+count/2);
			controller.updateLedStripe();

			count++;
			
		}
		
		controller.sleep(250);

		//This loop shows up to eight rays of light around the risen sun
		for(int i=5; i<8; i++){
			if(x+i<20)controller.setColor(x+i, y, yellow);
			if(x-i>=0)controller.setColor(x-i, y, yellow);
			if(y+i<20)controller.setColor(x, y+i, yellow);
			if(y-i>=0)controller.setColor(x, y-i, yellow);
			if(x+i-2<20&&y+i-2<20)controller.setColor(x+i-2, y+i-2, yellow);
			if(x-i+2>=0&&y+i-2<20)controller.setColor(x-i+2, y+i-2, yellow);
			if(x+i-2<20&&y-i+2>=0)controller.setColor(x+i-2, y-i+2, yellow);
			if(x-i+2>=0&&y-i+2>=0)controller.setColor(x-i+2, y-i+2, yellow);
			controller.updateLedStripe();
			controller.sleep(250);
		}
		
		controller.updateLedStripe();
		controller.sleep(1000);
		
		Word rizon = new Word("rizon");
		
		//This loop lets the word HORIZON slowly get brighter on the board
		for(intensity = 0; intensity<128; intensity++){
			
			//The H is shown on the top left,
			Letter.DrawLetterAt('H', 4, 1, 0, intensity, 0);
			
			//the first O is displayed inside the Sun
			for(int i=15; i<18; i++){
				controller.setColor(i, 1, 127-intensity, 127, 0);
				controller.setColor(i, 5, 127-intensity, 127, 0);
			}
			for(int i=2; i<5; i++){
				controller.setColor(14, i, 127-intensity, 127, 0);
				controller.setColor(18, i, 127-intensity, 127, 0);
			}
			
			//and the rest of the word is shown below it.
			rizon.displayWordAt(0, 8, 0, intensity, 0);
			controller.setColor(11, 8, 127-intensity, 127, 0);
			controller.setColor(16, 9, 127-intensity, 127, 0);
			
			controller.updateLedStripe();
		}
		
		controller.sleep(3500);
		
		for(int count=0; count<128; count++){
			for(x=0; x<20; x++){
				for(y=0; y<20; y++){
					for(int i=0; i<3; i++){
						if(controller.getColorAt(x, y)[i]!=0){
							int[] color = controller.getColorAt(x, y);
							color[i]--;
							controller.setColor(x, y, color);
						}
					}
				}
			}
			controller.updateLedStripe();
		}
		
	}

	static void gameName(){

		final Word pro = new Word("Pro");
		final Word ject = new Word("ject");
		
		pro.displayWordAt(5, 3, 90, 90, 90);
		ject.displayWordAt(2, 9, 90, 90, 90);

		controller.updateLedStripe();
		controller.sleep(3000);
		controller.resetColors();
		
		final Word space = new Word("Space");
		final Word shoo = new Word("Shoo");
		final Word ter = new Word("ter");
		
		space.displayWordAt(0, 0, 127, 107, 0);
		shoo.displayWordAt(2, 9, 107, 127, 0);
		ter.displayWordAt(5, 15, 107, 127, 0);
		
		controller.updateLedStripe();
		controller.sleep(5000);
		controller.resetColors();
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

}
