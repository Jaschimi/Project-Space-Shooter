package game;

import java.awt.event.KeyEvent;

import displayObjects.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

public abstract class Intro{
	
	public static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	public static KeyBuffer buffer;
	
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

		final Word press = new Word("Press");
		final Word space = new Word("Space");

		KeyBuffer buffer = controller.getKeyBuffer();
		
		press.displayWordAt(0, 4, 127, 127, 0);
		space.displayWordAt(0, 12, 127, 127, 0);
		controller.updateLedStripe();
		
		boolean increase = false;
		int increaseCount = 95;
		while(true){
			
			if(increase){
				increaseCount++;
				press.displayWordAt(0, 4, increaseCount, increaseCount, 0);
				space.displayWordAt(0, 12, increaseCount, increaseCount, 0);
				controller.updateLedStripe();
			}
			else{
				increaseCount--;
				press.displayWordAt(0, 4, increaseCount, increaseCount, 0);
				space.displayWordAt(0, 12, increaseCount, increaseCount, 0);
				controller.updateLedStripe();
			}
			
			if(increaseCount == 95){
				increase = false;
			}
			if(increaseCount == 63){
				increase = true;
			}
			
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

}
