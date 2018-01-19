package game;

import java.awt.event.KeyEvent;

import displayObjects.CelestialBodies;
import displayObjects.Letter;
import displayObjects.Word;
import gameObjects.SpaceShooter;
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

		int[] yellow = new int[] { 127, 127, 0};
		int[] black = new int[] { 0, 0, 0};

		int sunX = 10;
		int sunY = 21;
		int intensity = 30;
		
		//The following loop makes a sun rise behind a planet
		for(int count=0; count<177; count++){

			if(count%5==0){
				sunY--;
				CelestialBodies.displaySun(sunX, sunY+1, black);
				CelestialBodies.displaySun(sunX, sunY, yellow);
			}

			CelestialBodies.displayPlanet(intensity+count/2);
			controller.updateLedStripe();

			count++;
		}
		
		controller.sleep(250);

		//This loop shows up to eight rays of light around the risen sun
		for(int i=5; i<8; i++){
			if(sunX+i<20)controller.setColor(sunX+i, sunY, yellow);
			if(sunX-i>=0)controller.setColor(sunX-i, sunY, yellow);
			if(sunY+i<20)controller.setColor(sunX, sunY+i, yellow);
			if(sunY-i>=0)controller.setColor(sunX, sunY-i, yellow);
			if(sunX+i-2<20&&sunY+i-2<20)controller.setColor(sunX+i-2, sunY+i-2, yellow);
			if(sunX-i+2>=0&&sunY+i-2<20)controller.setColor(sunX-i+2, sunY+i-2, yellow);
			if(sunX+i-2<20&&sunY-i+2>=0)controller.setColor(sunX+i-2, sunY-i+2, yellow);
			if(sunX-i+2>=0&&sunY-i+2>=0)controller.setColor(sunX-i+2, sunY-i+2, yellow);
			controller.updateLedStripe();
			controller.sleep(250);
		}
		
		controller.updateLedStripe();
		controller.sleep(1000);
		
		Word rizon = new Word("rizon");
		
		//This loop lets the word HORIZON slowly get brighter on the board
		for(intensity = 0; intensity<128; intensity+=3){
			
			//The H is shown on the top left,
			Letter.DrawLetterAt('H', 3, 1, 0, intensity, 0);
			for(int x=sunX-5; x>sunX-8; x--){
				controller.setColor(x, 3, 127-intensity, 127, 0);
			}
			
			//the first O is displayed inside the Sun,
			for(int x=sunX-1; x<sunX+2; x++){
				controller.setColor(x, sunY-2, 127-intensity, 127, 0);
				controller.setColor(x, sunY+2, 127-intensity, 127, 0);
			}
			for(int y=sunY-1; y<sunY+2; y++){
				controller.setColor(sunX-2, y, 127-intensity, 127, 0);
				controller.setColor(sunX+2, y, 127-intensity, 127, 0);
			}
			
			//a dash takes the place of the right sunray
			for(int x=sunX+5; x<sunX+8; x++){
				controller.setColor(x, 3, 127-intensity, 127, 0);
			}
			
			//and the rest of the word is shown below it.
			rizon.displayWordAt(0, 8, 0, intensity, 0);
			for(int y=sunY+6; y<sunY+8; y++){
				controller.setColor(sunX, y, 127-intensity, 127, 0);
			}
			controller.setColor(sunX+5, sunY+5, 127-intensity, 127, 0);
			
			controller.updateLedStripe();
			
			//This line makes the intensity reach the otherwise unreachable value 127
			if(intensity == 126)intensity=124;
		}
		
		controller.sleep(3500);
		
		//At the end, everything fades away
		for(int count=0; count<128; count+=2){
			for(int x=0; x<20; x++){
				for(int y=0; y<20; y++){
					for(int i=0; i<3; i++){
						if(controller.getColorAt(x, y)[i]==1){
							int[] color = controller.getColorAt(x, y);
							color[i]--;
							controller.setColor(x, y, color);
						}
						if(controller.getColorAt(x, y)[i]!=0){
							int[] color = controller.getColorAt(x, y);
							color[i]-=2;
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
		
		//The word Project lights up on the board,
		for(int color=0; color<81; color+=4){
//			if(color==64){
//				controller.sleep(1000);
//			}
//			if(color>63){
//				color+=4;
//			}
			pro.displayWordAt(4, 3, color, color, color);
			ject.displayWordAt(2, 9, color, color, color);
			controller.updateLedStripe();
			controller.sleep(15);
		}
		controller.sleep(800);
		
//		//grows a bit darker again
//		for(int color=127; color>86; color-=8){
//			pro.displayWordAt(5, 3, color, color, color);
//			ject.displayWordAt(2, 9, color, color, color);
//			controller.updateLedStripe();
//			controller.sleep(-1000000000);
//		}
		
		
		//and gains/loses some color
//		for(int color=88; color>79; color-=2){
//			pro.displayWordAt(5, 3, color, color, color);
//			ject.displayWordAt(2, 9, color, color, color);
//			controller.updateLedStripe();
//		}
		for(int color=79; color>39; color-=2){
			pro.displayWordAt(4, 3, color, color, 80);
			ject.displayWordAt(2, 9, color, color, 80);
			controller.updateLedStripe();
		}
		for(int color=39; color>29; color-=2){
			pro.displayWordAt(4, 3, 40, 40, 80);
			ject.displayWordAt(2, 9, color, 40, 80);
			controller.updateLedStripe();
		}
		
		controller.updateLedStripe();
		controller.sleep(1000);
		controller.resetColors();
		
		final Word space = new Word("Space");
		final Word sh = new Word("Sh");
		final Word ter = new Word("ter");

		for(int color=0; color<41; color+=2){
			space.displayWordAt(0, 0, color+27, color+27, 0);
			sh.displayWordAt(0, 9, color+27, color+27, 0);
			Letter.DrawLetterAt('∞', sh.getLength(), 9, color+27, color+27, 0);
			Letter.DrawLetterAt('-', sh.getLength()+8, 9, color+27, color+27, 0);
			ter.displayWordAt(0, 15, color+27, color+27, 0);
			controller.updateLedStripe();
			controller.sleep(25);
		}
		controller.sleep(750);
		
		for(int color=0; color<41; color+=2){
			space.displayWordAt(0, 0, color+67, 3*color/2+67, 0);
			controller.updateLedStripe();
			controller.sleep(25);
		}
		controller.sleep(1250);
		
		for(int color=0; color<41; color+=2){
			sh.displayWordAt(0, 9, 3*color/2+67, color+67, 0);
			Letter.DrawLetterAt('∞', sh.getLength(), 9, 3*color/2+67, 67-color/1, 0);
			Letter.DrawLetterAt('-', sh.getLength()+8, 9, 3*color/2+67, color+67, 0);
			ter.displayWordAt(0, 15, 3*color/2+67, color+67, 0);
			controller.updateLedStripe();
			controller.sleep(25);
		}
		controller.sleep(3000);
		
		for(int count=127; count>0; count-=4){
			for(int x=0; x<20; x++){
				for(int y=0; y<20; y++){
					int[] color = new int[]{0,0,0};
					for(int i=0; i<3; i++){
						if(controller.getColorAt(x, y)[i]>3){
							color[i]=controller.getColorAt(x, y)[i]-4;
						}
						else color[i]=0;
					}
					controller.setColor(x, y, color);
				}
			}
			controller.updateLedStripe();
		}
	}
	
	static void titleScreen(){

		KeyBuffer buffer = controller.getKeyBuffer();
		
		controller.resetColors();
		buffer.clear();
		
		//These Words will be continuously displayed on the title screen
		final Word press = new Word("Press");
		final Word space = new Word("Space");

		press.displayWordAt(0, 4, 95, 80, 0);
		space.displayWordAt(0, 12, 95, 80, 0);
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
			
			//Pressing space really does end the method
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				if(event.getKeyCode()==java.awt.event.KeyEvent.VK_SPACE){
					controller.resetColors();
					return;
				}
			}
		}
	}

	protected static void story(){

		buffer = controller.getKeyBuffer();
		
		//This boolean can skip the story
		boolean skip = false;
		
		//The uber-amazing story!
		final Word year = new Word("5018");
		final Word ad = new Word("A.D.");
		final Word conquer = new Word("The invading forces have conquered almost all defense stations.");
		final Word hope = new Word("There is only one last hope...");
		final Word spaceShooter = new Word("The Space Shooter!");
		
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
				year.displayWordAt(2, 4, count, count/4, count/2);
				ad.displayWordAt(4, 12, count, count/4, count/2);
				controller.updateLedStripe();
			}
			else{
				year.displayWordAt(2, 4, maxCount-count, (maxCount-count)/4, (maxCount-count)/2);
				ad.displayWordAt(4, 12, maxCount-count, (maxCount-count)/4, (maxCount-count)/2);
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
		
		SpaceShooter ss = new SpaceShooter(new int[]{0, 20}, 3, 3);
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
