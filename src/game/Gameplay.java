package game;
import java.awt.event.KeyEvent;

import difficulties.Easy;
import difficulties.Medium;
import difficulties.Hard;
import difficulties.Tutorial;
import displayObjects.Word;
import gameObjects.EnemyShip;
import gameObjects.Projectile;
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

		//Create the SpaceShooter with three lives, three ammo and a spawning location on the bottom mid of the board
		SpaceShooter ss = new SpaceShooter(new int[]{9, 18}, 3, 3);
		
		//These if statements start a game based on what difficulty has been chosen in the main method
		if(difficulty == 0){
			ss.setColorAt(0, 0, 104, 23, 47);
			ss.setColorAt(1, 0, 0, 0, 0);
			ss.setColorAt(2, 0, 104, 23, 47);
			ss.setColorAt(0, 1, 104, 23, 47);
			ss.setColorAt(1, 1, 5, 107, 17);
			ss.setColorAt(2, 1, 104, 23, 47);
			Tutorial.start(ss);
		}
		else{
			int[] shipColor;
			if(difficulty == 1){
				ss.setColorAt(0, 0, 0, 127, 107);
				ss.setColorAt(1, 0, 0, 0, 0);
				ss.setColorAt(2, 0, 0, 127, 107);
				ss.setColorAt(0, 1, 0, 127, 107);
				ss.setColorAt(1, 1, 5, 107, 17);
				ss.setColorAt(2, 1, 0, 127, 107);
				won = Easy.start(ss);
				shipColor = new int[]{0, 127, 107};
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
					shipColor = new int[]{99, 28, 29};
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
						shipColor = new int[]{28+Hard.colorCount, 8+Hard.colorCount, 99-Hard.colorCount};
						Hard.colorCount=0;
					}
					else{
						shipColor = new int[]{127, 127, 127};
					}
				}
			}
			//After a game in easy, medium or hard mode is has been won or lost, the outro boots up
			if(!Easy.broken&&!Medium.broken&&!Hard.broken){
				Endscreen.outro(won, shipColor, difficulty);
			}
			else{
				Easy.broken = false;
				Medium.broken = false;
				Hard.broken = false;
				return;
			}
		}
	}
	
	//This method displays the pause screen
	public static boolean pause(SpaceShooter ss, EnemyShip currentShip){
		
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
					controller.setColors(board);
					controller.updateLedStripe();
					return false;
				}
				if(event.isAltDown()&&event.isShiftDown()&&event.getKeyCode()==java.awt.event.KeyEvent.VK_C){
					cheat(board, ss, currentShip);
					controller.resetColors();
					pause.displayWordAt(0, 5, 60, 30, 0);
					controller.updateLedStripe();
				}
				if(event.getKeyCode()==java.awt.event.KeyEvent.VK_BACK_SPACE){
					controller.setColors(board);
					return true;
				}
			}
		}
	}
	
	//This method displays the cheat screen :O
	public static void cheat(int[][][] board, SpaceShooter ss, EnemyShip currentShip){
		
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
					
				case java.awt.event.KeyEvent.VK_BACK_SPACE:
					if(code.length()>0){
						if(code.length()==1){
							cheatCode = new Word(code);
							cheatCode.displayWordAt(0, 6, 0, 0, 0);
							code = "";
						}
						else{
							cheatCode = new Word(code);
							cheatCode.displayWordAt(0, 6, 0, 0, 0);
							char[] codeArray = code.toCharArray();
							code = "";
							for(int i=0; i<codeArray.length-1; i++){
								code +=codeArray[i];
							}
						}
					}
					break;
					
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
						for(x=19, y=0; x>0 && y<20; x--, y++){
							controller.setColor(x, y, 69, 4, 4);
							controller.updateLedStripe();
						}
						Endscreen.letItGo(controller.getColors(), new int[]{69,4,4}, 10);
						System.exit(666);
						
					case "5500":
						for(x=0; x<3; x++){
							for(y=0; y<2; y++){
								ss.setColorAt(x, y, 0, 0, 0);
							}
						}
						break;

					case "5501":
						ss.setColorAt(0, 0, 0, 127, 107);
						ss.setColorAt(2, 0, 0, 127, 107);
						ss.setColorAt(0, 1, 0, 127, 107);
						ss.setColorAt(2, 1, 0, 127, 107);
						break;

					case "5502":
						for(x=0; x<3; x++){
							for(y=0; y<2; y++){
								if(x==1 && y==1)ss.setColorAt(1, 1, 5, 107, 17);
								else{
									if(y==0 && x==1)ss.setColorAt(x, y, 0, 0, 0);
									else{
										ss.setColorAt(x, y, 99, 28, 29);
									}
								}
							}
						}
						break;

					case "5503":
						ss.setColorAt(0, 0, 28, 8, 99);
						ss.setColorAt(2, 0, 28, 8, 99);
						ss.setColorAt(0, 1, 28, 8, 99);
						ss.setColorAt(2, 1, 28, 8, 99);
						break;

					case "5509":
						for(x=0; x<3; x++){
							for(y=0; y<2; y++){
								ss.setColorAt(x, y, 127, 127, 127);
							}
						}
						break;

					case "5510":
						y = ss.getTopLeftCorner()[1]-1;
						ss.setCannons(new int[][]{{0,y},{2,y},{4,y},{6,y},{8,y},{10,y},{12,y},{14,y},{16,y},{18,y}});
						ss.setShots(new Projectile[20]);
						break;
						
					case "5519":
						y = ss.getTopLeftCorner()[1]-1;
						ss.setCannons(new int[][]{{0,y},{1,y},{2,y},{3,y},{4,y},{5,y},{6,y},{7,y},{8,y},{9,y},{10,y},{11,y},{12,y},{13,y},{14,y},{15,y},{16,y},{17,y},{18,y},{19,y}});
						ss.setShots(new Projectile[100]);
						break;
						
					case "5550":
						ss.setShots(new Projectile[0]);
						break;

					case "5551":
						ss.setShots(new Projectile[1]);
						break;

					case "5552":
						ss.setShots(new Projectile[2]);
						break;

					case "5553":
						ss.setShots(new Projectile[3]);
						break;

					case "5554":
						ss.setShots(new Projectile[4]);
						break;

					case "5555":
						ss.setShots(new Projectile[5]);
						break;

					case "5556":
						ss.setShots(new Projectile[6]);
						break;

					case "5557":
						ss.setShots(new Projectile[7]);
						break;

					case "5559":
						ss.setShots(new Projectile[99]);
						break;
						
					case "5570":
						
						ss.setLives(0);
						ss.setColorAt(1, 1, 31, 31, 31);
						break;
						
					case "5571":
						
						ss.setLives(1);
						ss.setColorAt(1, 1, 69, 4, 4);
						break;
					
					case "5572":
						
						ss.setLives(2);
						ss.setColorAt(1, 1, 127, 127, 0);
						break;
					
					case "5573":
						
						ss.setLives(3);
						ss.setColorAt(1, 1, 5, 107, 17);
						break;

					case "5579":
						
						ss.setLives(99);
						ss.setColorAt(1, 1, 127, 0, 127);
						break;
						
					case "5590":
						
						for(int i=0; i<3; i++){
							board[ss.getShots()[0].getX()][ss.getShots()[0].getY()][i]=0;
						}
						ss.getShots()[0]=null;
						break;
						
					case "5591":
						
						for(int i=0; i<3; i++){
							board[ss.getShots()[1].getX()][ss.getShots()[1].getY()][i]=0;
						}
						ss.getShots()[1]=null;
						break;
						
					case "5592":
						
						for(int i=0; i<3; i++){
							board[ss.getShots()[2].getX()][ss.getShots()[2].getY()][i]=0;
						}
						ss.getShots()[2]=null;
						break;
						
					case "6551":
						
						if(currentShip.getNext()!=null&&currentShip.getNext().getNext()!=null){
							currentShip.setNext(currentShip.getNext().getNext());
						}
						break;

					case "6559":
						
						while(currentShip.getNext()!=null&&currentShip.getNext().getNext()!=null){
							currentShip.setNext(currentShip.getNext().getNext());
						}
						break;
						
					case "6570":
						
						currentShip.setLives(0);
						currentShip.spawn();
						break;
						
					case "6571":
						
						currentShip.setLives(1);
						currentShip.spawn();
						break;
						
					case "6572":
						
						currentShip.setLives(currentShip.getMaxLives()/2);
						currentShip.spawn();
						break;
						
					case "6573":
						
						currentShip.setLives(currentShip.getMaxLives()/3);
						currentShip.spawn();
						break;
						
					case "6574":
						
						currentShip.setLives(currentShip.getMaxLives()/4);
						currentShip.spawn();
						break;
						
					case "6575":
						
						currentShip.setLives(currentShip.getMaxLives()/5);
						currentShip.spawn();
						break;
						
					case "6579":
						
						currentShip.setLives(currentShip.getMaxLives());
						currentShip.spawn();
						break;
						
					case "6590":
						
						for(int j=0; j<currentShip.getShots().length; j++){
							for(int i=0; i<3; i++){
								board[currentShip.getShots()[j].getX()][currentShip.getShots()[j].getY()][i]=0;
							}
						}
						currentShip.getShots()[1]=null;
						break;
						
					case "6591":
						
						for(int i=0; i<3; i++){
							board[currentShip.getShots()[0].getX()][currentShip.getShots()[0].getY()][i]=0;
						}
						currentShip.getShots()[0]=null;
						break;
					
					case "6599":
						
						for(int i=0; i<3; i++){
							board[currentShip.getShots()[currentShip.getShots().length-1].getX()][currentShip.getShots()[currentShip.getShots().length-1].getY()][i]=0;
						}
						currentShip.getShots()[2]=null;
						break;
						
					case "7000":
						
						Main.randomColor();
						break;

					case "7001":
						
						Main.nestTest();
						break;

					case "7002":
						
						Main.rainbow();
						break;

					case "7003":
						
						Main.Goldenization();
						break;

					case "7004":
						
						Main.shipDiashow();
						break;

					case "7005":
						
						Main.DisplayLogo(6, Main.word);
						break;

					case "7006":
						
						Main.DisplayAlphabet();
						break;

					case "7007":
						
						Main.DisplayCiphers();
						break;

					case "7008":
						
						Main.story();
						break;
						
					}
					return;
				}
			}
		}
	}

}