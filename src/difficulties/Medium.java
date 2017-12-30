package difficulties;

import java.awt.event.KeyEvent;

import gameObjects.EnemyShip;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.BigBoulder;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.LangerLulatsch;
import ufos.MidClasher;

//This class describes medium mode
public class Medium {

	public static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	public static KeyBuffer buffer = controller.getKeyBuffer();

	public static boolean start(SpaceShooter ss){
		
		//This variable counts the amount of times the endless loop in all four difficulties has been started
		int loopCount = 0;
		//This variable counts the amount of times the colors of a ship have faded
		int fadeCount = 0;

		//this boolean will be returned to the class Game
		boolean won = false;
		
		//Making of the EnemyShupList for medium mode in three steps:
		//Step 1: Create an EnemyShip array with 50 entries
		//Step 2: Convert the array to a list of EnemyShips and set the first entry as the currentShip
		
		//Step 1
		EnemyShip[] ufoArray = new EnemyShip[50];
		
		ufoArray [0] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray [1] = new DefaultShip(new int[]{8, 0}, 1, 1);
		ufoArray [2] = new DefaultShip(new int[]{5, 3}, 1, 1);
		ufoArray [3] = new DefaultShip(new int[]{0, 0}, 2, 1);
		ufoArray [4] = new LangerLulatsch(new int[]{0, 6}, 2, 1);
		ufoArray [5] = new DefaultShip(new int[]{8, 0}, 3, 2);
		ufoArray [6] = new DefaultShip(new int[]{8, 0}, 3, 2);
		ufoArray [7] = new LangerLulatsch(new int[]{8, 0}, 4, 2);
		ufoArray [8] = new LangerLulatsch(new int[]{8, 0}, 4, 2);
		ufoArray [9] = new MidClasher(new int[]{8, 0}, 5, 2);
		ufoArray[10] = new LangerLulatsch(new int[]{8, 0}, 5, 3);
		ufoArray[11] = new MidClasher(new int[]{8, 0}, 6, 3);
		ufoArray[12] = new MidClasher(new int[]{8, 0}, 6, 3);
		ufoArray[13] = new LangerLulatsch(new int[]{8, 0}, 7, 3);
		ufoArray[14] = new DefaultShip(new int[]{8, 0}, 7, 3);
		ufoArray[15] = new MidClasher(new int[]{8, 0}, 8, 4);
		ufoArray[16] = new MidClasher(new int[]{8, 0}, 8, 4);
		ufoArray[17] = new LangerLulatsch(new int[]{8, 0}, 9, 4);
		ufoArray[18] = new DefaultShip(new int[]{8, 0}, 9, 4);
		ufoArray[19] = new BigBoulder(new int[]{8, 0}, 10, 4);
		ufoArray[20] = new MidClasher(new int[]{8, 0}, 15, 5);
		ufoArray[21] = new BigBoulder(new int[]{8, 0}, 20, 5);
		ufoArray[22] = new MidClasher(new int[]{8, 0}, 25, 5);
		ufoArray[23] = new DefaultShip(new int[]{8, 0}, 40, 5);
		ufoArray[24] = new BossaNova(new int[]{0, 0}, 70, 10);
		ufoArray[25] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray[26] = new DefaultShip(new int[]{8, 0}, 2, 2);
		ufoArray[27] = new DefaultShip(new int[]{5, 3}, 3, 3);
		ufoArray[28] = new MidClasher(new int[]{0, 0}, 4, 4);
		ufoArray[29] = new MidClasher(new int[]{0, 6}, 5, 5);
		ufoArray[30] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray[31] = new DefaultShip(new int[]{8, 0}, 2, 2);
		ufoArray[32] = new DefaultShip(new int[]{5, 3}, 3, 3);
		ufoArray[33] = new MidClasher(new int[]{0, 0}, 4, 4);
		ufoArray[34] = new MidClasher(new int[]{0, 6}, 5, 5);
		ufoArray[35] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray[36] = new DefaultShip(new int[]{8, 0}, 2, 2);
		ufoArray[37] = new DefaultShip(new int[]{5, 3}, 3, 3);
		ufoArray[38] = new MidClasher(new int[]{0, 0}, 4, 4);
		ufoArray[39] = new MidClasher(new int[]{0, 6}, 5, 5);
		ufoArray[40] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray[41] = new DefaultShip(new int[]{8, 0}, 2, 2);
		ufoArray[42] = new DefaultShip(new int[]{5, 3}, 3, 3);
		ufoArray[43] = new MidClasher(new int[]{0, 0}, 4, 4);
		ufoArray[44] = new MidClasher(new int[]{0, 6}, 5, 5);
		ufoArray[45] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray[46] = new DefaultShip(new int[]{8, 0}, 2, 2);
		ufoArray[47] = new DefaultShip(new int[]{5, 3}, 3, 3);
		ufoArray[48] = new MidClasher(new int[]{0, 0}, 4, 4);
		ufoArray[49] = new BossaNova(new int[]{0, 0}, 0, 0);
		
		//Step 2
		EnemyShip currentUFO = ufoArray[0];
		for(int i=1; i<ufoArray.length; i++){
			ufoArray[i-1].setNext(ufoArray[i]);
		}
		
		ss.spawnShip();
		currentUFO.spawnShip();
		
		while(true){
			//In every instance of the endless loop, nine things are happening:
			//1.: The loop count increases by one.
			//2.: It is checked if the current EnemyShip has no lifes left
			//3.: It is checked if the SpaceShooter has no lifes left
			//4.: Every fifties instance of the loop, the current ship moves in a random direction
			//5.: The current EnemyShip shoots a projectile
			//6.: All shots the SpaceShooter fired are moving upwards by one
			//7.: All shots the currentUFO fired are moving downwards by one
			//8.: The last keyboard input is detected and one of five actions are performed
			//9.: Finally, the LED stripe is updated
			
			//1.
			loopCount+=1;
			
			//2.
			if(currentUFO.getLifes() <= 0){//here the current ship has no lifes left
				//Letting the colors of the destroyed ship fade away
				currentUFO.fade();
				currentUFO.fade();
				fadeCount++;

				//Here the enemy ship is completely faded away
				if(fadeCount==63){
					fadeCount=0;
					//If there's another UFO in the ufoList, it will become the new current UFO and be spawned now
					if(currentUFO.getNext() != null){
						currentUFO = currentUFO.getNext();
						currentUFO.spawnShip();
					}
					else{//here all enemies have been defeated, so the game has been won and the endless loop can be exited
						won = true;
						break;
					}
				}
			}
			
			//3.
			if(ss.getLifes()==0){
				break;
			}
			
			//4.
			if(loopCount%50==0&&currentUFO.getLifes()>0){
				
				//This generates a random integer between 0 and 4
				int random = (int) (Math.random()*4);
				
				//Based on what integer was generated, the char direction becomes one of four values
				char direction = 0;
				switch(random){
				
				case 0:
					direction = 'W';
					break;
				case 1:
					direction = 'A';
					break;
				case 2:
					direction = 'S';
					break;
				case 3:
					direction = 'D';
					break;
				}
				
				//And lastly, the current UFO moves in the generated direction
				currentUFO.move(direction);
			}
			
			//5.
			//Enemy ships only shoot with a chance of 1/35 in every loop
			int random = (int) (Math.random()*35);
			if(random == 2&&currentUFO.getLifes()>0){
				currentUFO.shoot();
			}
			
			//6.
			//The first loop goes through the whole shots array
			for(int i=0; i<ss.getShots().length; i++){
				//Only when an entry is not null(i.e. a projectile has been shot), the rest of the code is accessed
				if(ss.getShots()[i] != null){
					//This if statement checks if the projectile is still on the screen
					if(ss.getShots()[i].getY()>=0){
						//If that's the case, the program checks if any part of the current ship is directly above the projectile
						for(int x=0; x<currentUFO.getLength(); x++){
							for(int y=0; y<currentUFO.getHeight(); y++){
								if(ss.getShots()[i].getY()-1==currentUFO.getTopLeftCorner()[1]+y
								   && ss.getShots()[i].getX()==currentUFO.getTopLeftCorner()[0]+x){
									//If that is also the case, the projectiles color is changed to black,
									controller.setColor(ss.getShots()[i].getX(), ss.getShots()[i].getY(), 0, 0, 0);
									//the currentUFO is hit (if it still haves lifes) and
									if(currentUFO.getLifes()>0)currentUFO.hit();
									//the projectile is set to null.
									ss.getShots()[i] = null;
									break;
								}
							}
							//This statement is only triggered if the projectile hit the current ship
							if(ss.getShots()[i]==null)break;
						}
						//If the current UFO is not directly above the projectile and the projectile is not null,
						//it will move up by one spot
						if(ss.getShots()[i]!=null)ss.getShots()[i].moveProjectile("up");
					}
					else{//here the shot is offscreen, so its corresponding array entry can be set to null
						ss.getShots()[i] = null;
					}
				}
			}
			
			//7.
			for(int i=0; i<currentUFO.getShots().length; i++){
				if(currentUFO.getShots()[i] != null){
					if(currentUFO.getShots()[i].getY()<=19){
						for(int x=0; x<3; x++){
							for(int y=0; y<2; y++){
								if(currentUFO.getShots()[i].getY()-1==ss.getTopLeftCorner()[1]+y && currentUFO.getShots()[i].getX()==ss.getTopLeftCorner()[0]+x){
									controller.setColor(currentUFO.getShots()[i].getX(), currentUFO.getShots()[i].getY(), 0, 0, 0);
									currentUFO.getShots()[i] = null;
									ss.hit();
									break;
								}
							}
							if(currentUFO.getShots()[i]==null)break;
						}
						if(currentUFO.getShots()[i]!=null)currentUFO.getShots()[i].moveProjectile("down");
					}
					else{//here the shot is offscreen, so its corresponding array entry can be set to null
						currentUFO.getShots()[i] = null;
					}
				}
			}
			
			//8.
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null){
				if (event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
					
					switch (event.getKeyCode()){
					
					case java.awt.event.KeyEvent.VK_SPACE:
						//space makes the SS shoot
						ss.shoot();
						break;
					
					case java.awt.event.KeyEvent.VK_W:
						//W makes the SS move up
						ss.move('W');
						break;
					
					case java.awt.event.KeyEvent.VK_S:
						//S makes the SS move down
						ss.move('S');
						break;
						
					case java.awt.event.KeyEvent.VK_A:
						//A makes the SS move left
						ss.move('A');
						break;
						
					case java.awt.event.KeyEvent.VK_D:
						//D makes the SS move right
						ss.move('D');
						break;
						
					default:
					}
				}
			}
			
			//9.
			controller.updateLedStripe();
			
		}
		//These lines only activate once all enemy ships are defeated or the SS has no lifes left
		controller.updateLedStripe();
		return won;
	}

}
