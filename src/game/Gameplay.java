package game;
import java.awt.event.KeyEvent;

import difficulties.Easy;
import difficulties.Medium;
import difficulties.Hard;
import difficulties.Tutorial;
import displayObjects.Word;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

//This class controls everything that happens during a game
public abstract class Gameplay{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	protected static void start(int difficulty){
		
		//First of all, the LED Board is reset so that all text still displayed is removed
		controller.resetColors();
		
		//This boolean determines if the game has been won
		boolean won = false;

		//Create the SpaceShooter with three lifes, three ammo and a spawning location on the bottom mid of the board
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
		//These if statements start a game based on what difficulty has been chosen in the main method
		if(difficulty == 0){
			ss.setColorAt(0, 0, 107, 0, 127);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 107, 0, 127);
			ss.setColorAt(0, 1, 107, 0, 127);
			ss.setColorAt(1, 1, 5, 107, 17);
			ss.setColorAt(2, 1, 107, 0, 127);
			Tutorial.start(ss);
		}
		else{
			if(difficulty == 1){
				ss.setColorAt(0, 0, 0, 127, 107);
				ss.setColorAt(1, 0, 0, 0, 0);
				ss.setColorAt(2, 0, 0, 127, 107);
				ss.setColorAt(0, 1, 0, 127, 107);
				ss.setColorAt(1, 1, 5, 107, 17);
				ss.setColorAt(2, 1, 0, 127, 107);
				won = Easy.start(ss);
			}
			else{
				if(difficulty == 2){
					for(int x=0; x<3; x++){
						for(int y=0; y<2; y++){
							if(x==1 && y==1)ss.setColorAt(1, 1, 5, 107, 17);
							else{
								if(y==0 && x==1)ss.setColorAt(x, y, 0, 0, 0);
								else{
									ss.setColorAt(x, y, 99, 28, 29);
								}
							}
						}
					}
					won = Medium.start(ss);
				}
				else{
					if(difficulty == 3){
						ss.setColorAt(0, 0, 28, 8, 99);
						ss.setColorAt(1, 0, 0, 0, 0);
						ss.setColorAt(2, 0, 28, 8, 99);
						ss.setColorAt(0, 1, 28, 8, 99);
						ss.setColorAt(1, 1, 5, 107, 17);
						ss.setColorAt(2, 1, 28, 8, 99);
						won = Hard.start(ss);
					}
				}
			}
			Endscreen.outro(won, controller.getColorAt(ss.getTopLeftCorner()[0], ss.getTopLeftCorner()[1]));
		}
	}
	
	//This method displays the pause screen
	public static void pause(SpaceShooter ss){
		
		int[][][] board = new int[20][20][3];
		for(int x=0; x<20; x++){
			for(int y=0; y<20; y++){
				board[x][y]=controller.getColorAt(x, y);
			}
		}

		controller.resetColors();
		controller.updateLedStripe();
		
		KeyBuffer buffer = controller.getKeyBuffer();
		Word pause = new Word("Pause");
		pause.displayWordAt(0, 5, 60, 30, 0);
		controller.updateLedStripe();
		while(true){
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null&&event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
				if(event.getKeyCode()==java.awt.event.KeyEvent.VK_ESCAPE){
					break;
				}
				if(event.getKeyCode()==java.awt.event.KeyEvent.VK_CONTROL){
					cheat(ss);
					controller.resetColors();
					pause.displayWordAt(0, 5, 60, 30, 0);
					controller.updateLedStripe();
				}
			}
		}
		controller.setColors(board);
		controller.updateLedStripe();
	}
	
	//This method displays the cheat screen :O
	public static void cheat(SpaceShooter ss){
		
		controller.resetColors();
		controller.updateLedStripe();
		
		KeyBuffer buffer = controller.getKeyBuffer();
		Word pause = new Word("Cheat");
		pause.displayWordAt(0, 0, 127, 107, 0);
		controller.updateLedStripe();
		
		String code = "";
		Word cheatCode = new Word(code);
		while(true){
			
			if(code != ""){
				cheatCode = new Word(code);
				cheatCode.displayWordAt(0, 6, 100, 100, 9);
			}
			controller.updateLedStripe();
			
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null&&event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
				
				switch(event.getKeyCode()){
				
				case java.awt.event.KeyEvent.VK_ESCAPE:
					return;
					
				case java.awt.event.KeyEvent.VK_0:
					
					if(code.length()<4){
						code += "0";
					}
					break;
					
				case java.awt.event.KeyEvent.VK_1:
					
					if(code.length()<4){
						code += "1";
					}
					break;

				case java.awt.event.KeyEvent.VK_2:
					
					if(code.length()<4){
						code += "2";
					}
					break;

				case java.awt.event.KeyEvent.VK_3:
					
					if(code.length()<4){
						code += "3";
					}
					break;

				case java.awt.event.KeyEvent.VK_4:
					
					if(code.length()<4){
						code += "4";
					}
					break;

				case java.awt.event.KeyEvent.VK_5:
					
					if(code.length()<4){
						code += "5";
					}
					break;

				case java.awt.event.KeyEvent.VK_6:
					
					if(code.length()<4){
						code += "6";
					}
					break;

				case java.awt.event.KeyEvent.VK_7:
					
					if(code.length()<4){
						code += "7";
					}
					break;

				case java.awt.event.KeyEvent.VK_8:
					
					if(code.length()<4){
						code += "8";
					}
					break;

				case java.awt.event.KeyEvent.VK_9:
					
					if(code.length()<4){
						code += "9";
					}
					break;
				
				case java.awt.event.KeyEvent.VK_ENTER:
					
					switch(code){
					
					default:
						
						int x, y;
						controller.resetColors();
						for(x=0, y=0; x<20 && y<20; x++, y++){
							controller.setColor(x, y, 69, 4, 4);
							controller.updateLedStripe();
						}
						for(x=20, y=0; x>0 && y<20; x--, y++){
							controller.setColor(x, y, 69, 4, 4);
							controller.updateLedStripe();
						}
						Endscreen.letItGo(controller.getColors(), new int[]{69,4,4}, 10);
						System.exit(666);
						
					case "5500":
						
						ss.setLifes(0);
						ss.setColorAt(1, 1, 31, 31, 31);
						break;
						
					case "5501":
						
						ss.setLifes(1);
						ss.setColorAt(1, 1, 69, 4, 4);
						break;
					
					case "5502":
						
						ss.setLifes(2);
						ss.setColorAt(1, 1, 127, 127, 0);
						break;
					
					case "5503":
						
						ss.setLifes(3);
						ss.setColorAt(1, 1, 5, 107, 17);
						break;
					}
					return;
				}
			}
		}
	}

}