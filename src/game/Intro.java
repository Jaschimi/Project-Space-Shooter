package game;

import java.awt.event.KeyEvent;

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

		//These will be continuously displayed on the title screen
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
			
			System.out.println(loopCount);
			
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
			
			if(Math.random()*100<=5){
				
				x = (int) (Math.random()*20);
				y = (int) (Math.random()*20);
				
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

	private static void story(){

		buffer = controller.getKeyBuffer();
		
		Word story = new Word("It is the year 5018.    "
				+ "The invading forces have conquered almost all defense stations.    "
				+ "There is only one last hope...    "
				+ "The Space Shooter!");
		
		for(int x=20; x>-story.getLength(); x--){
			//Pressing any key ends the story time
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && event.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
				break;
			}
			
			//The following two lines make the sentence move one spot to the left
			story.displayWordAt(x+1, 0, 0, 0, 0);
			story.displayWordAt(x, 0, 96, 87, 12);
			
			//And these two control how fast it is moving. A lower integer in the sleep method means a faster speed.
			controller.updateLedStripe();
			controller.sleep(125);
		}
		
		controller.resetColors();
	}
}
