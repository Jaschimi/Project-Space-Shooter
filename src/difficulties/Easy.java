package difficulties;

import java.awt.event.KeyEvent;

import game.Gameplay;
import gameObjects.EnemyShip;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.UnnervingFloatingOctopus;
import ufos.DefaultShip;
import ufos.LangerLulatsch;
import ufos.BigBoulder;

//This class describes easy mode
public abstract class Easy {

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer = controller.getKeyBuffer();

	public static boolean broken = false;
	
	public static boolean start(SpaceShooter ss){
		
		//This boolean will be returned to the class Game
		boolean won = false;
		
		//Making of the EnemyShipList for easy mode in two steps:
		//Step 1: Create an EnemyShip array with 25 entries
		//Step 2: Convert the array to a list of EnemyShips and set the first entry as the currentShip
		
		//Step 1
		EnemyShip[] enemyShipArray = new EnemyShip[25];
		
		//The spawning locations of the ships are randomized
		int dsSpawn = (int) (Math.random()*18);
		int bbSpawn = (int) (Math.random()*16);
		int llSpawn = (int) (Math.random()*18);
		int ufoSpawn = (int) (Math.random()*14);
		
		enemyShipArray [0] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 1);
		enemyShipArray [1] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 1);
		enemyShipArray [2] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 1);
		enemyShipArray [3] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 2);
		enemyShipArray [4] = new BigBoulder(new int[]{bbSpawn, 0}, 5, 2);
		enemyShipArray [5] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 2);
		enemyShipArray [6] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 2);
		enemyShipArray [7] = new BigBoulder(new int[]{bbSpawn, 0}, 5, 2);
		enemyShipArray [8] = new BigBoulder(new int[]{bbSpawn, 0}, 5, 3);
		enemyShipArray [9] = new DefaultShip(new int[]{dsSpawn, 0}, 4, 3);
		enemyShipArray[10] = new BigBoulder(new int[]{bbSpawn, 0}, 7, 3);
		enemyShipArray[11] = new DefaultShip(new int[]{dsSpawn, 0}, 4, 3);
		enemyShipArray[12] = new LangerLulatsch(new int[]{llSpawn, 0}, 6, 2);
		enemyShipArray[13] = new BigBoulder(new int[]{bbSpawn, 0}, 10, 4);
		enemyShipArray[14] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 3);
		enemyShipArray[15] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 4);
		enemyShipArray[16] = new LangerLulatsch(new int[]{llSpawn, 0}, 9, 4);
		enemyShipArray[17] = new BigBoulder(new int[]{bbSpawn, 0}, 10, 4);
		enemyShipArray[18] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 4);
		enemyShipArray[19] = new BigBoulder(new int[]{bbSpawn, 0}, 15, 5);
		enemyShipArray[20] = new LangerLulatsch(new int[]{llSpawn, 0}, 15, 4);
		enemyShipArray[21] = new BigBoulder(new int[]{bbSpawn, 0}, 20, 5);
		enemyShipArray[22] = new LangerLulatsch(new int[]{llSpawn, 0}, 18, 6);
		enemyShipArray[23] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 4);
		enemyShipArray[24] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 35, 5);

		//Step 2
		EnemyShip currentShip = enemyShipArray[0];
		for(int i=1; i<enemyShipArray.length; i++){
			enemyShipArray[i-1].setNext(enemyShipArray[i]);
		}
		
		ss.spawn();
		currentShip.spawn();

		//This variable counts the amount of times the endless loop has been started
		int loopCount = 0;
		//These variables count the amount of times the colors of a ship have faded
		int enemyFadeCount = 0;
		int ssFadeCount = 0;
		//This boolean determines if the current ship will move left or right
		boolean right = true;
		
		controller.updateLedStripe();
		
		while(true){
			//In every instance of the endless loop, nine things may happen:
			//1.: The loop count increases by one
			//2.: It is checked if the current EnemyShip has no lifes left
			//3.: It is checked if the SpaceShooter has no lifes left
			//4.: All shots the SpaceShooter fired move upwards by one
			//5.: All shots the current ship fired move downwards by one
			//6.: The current ship moves in a direction
			//7.: The current EnemyShip shoots a projectile
			//8.: The last keyboard input is detected and one of six actions is performed
			//9.: Finally, the LED stripe is updated
			
			//1.
			loopCount+=1;
			
			//2.
			if(currentShip.getLifes() <= 0){//here the current ship has no lifes left
				//Letting the colors of the destroyed ship fade away
				currentShip.fade();
				currentShip.fade();
				enemyFadeCount++;

				//Here the enemy ship is completely faded away
				if(enemyFadeCount==63){
					enemyFadeCount=0;
					//If there's another UFO in the ufoList, it will become the new current UFO and be spawned now
					if(currentShip.getNext() != null){
						currentShip = currentShip.getNext();
						currentShip.spawn();
						right = true;
					}
					else{//here all enemies have been defeated, so the game has been won and the endless loop can be exited
						won = true;
						break;
					}
				}
			}
			
			//3.
			if(ss.getLifes()==0){
				//removing the colors of the destroyed ship
				ss.fade();
				ss.fade();
				ssFadeCount++;
				
				//Here the Space Shooter is faded out almost completely
				if(ssFadeCount==63){
					ssFadeCount=0;
					ss.fade();
					
					//All shots the current ship fired are moving off the screen
					for(int i=0; i<currentShip.getShots().length; i++){
						if(currentShip.getShots()[i] != null){
							if(currentShip.getShots()[i].getY()<=19){
								currentShip.getShots()[i].moveProjectile("down");
							}
							else{//here the shot is offscreen, so its corresponding array entry can be set to null
								currentShip.getShots()[i] = null;
							}
						}
					}
					
					//Now that the Space Shooter is completely faded out, the current ship moves off of the board
					while(currentShip.getTopLeftCorner()[1]<20){
						currentShip.move('S');
						
						controller.updateLedStripe();
						controller.sleep(150);
					}
					break;
				}
			}
			
			//4.
			//The first loop goes through the whole shots array
			for(int i=0; i<ss.getShots().length; i++){
				//Only when an entry is not null(i.e. a projectile has been shot), the rest of the code is accessed
				if(ss.getShots()[i] != null){
					//This if statement checks if the projectile is still on the screen
					if(ss.getShots()[i].getY()>=0){
						//If that's the case, the program checks if any part of the current ufo (that isn't black)
						//is directly above the projectile
						for(int x=0; x<currentShip.getLength(); x++){
							for(int y=0; y<currentShip.getHeight(); y++){
								if(ss.getShots()[i].getY()-1==currentShip.getTopLeftCorner()[1]+y
								   && ss.getShots()[i].getX()==currentShip.getTopLeftCorner()[0]+x
								   &&(controller.getColorAt(ss.getShots()[i].getX(), ss.getShots()[i].getY()-1)[0]!=0
								   ||controller.getColorAt(ss.getShots()[i].getX(), ss.getShots()[i].getY()-1)[1]!=0
								   ||controller.getColorAt(ss.getShots()[i].getX(), ss.getShots()[i].getY()-1)[2]!=0)){
									//If that is also the case, the projectiles color is changed to black,
									controller.setColor(ss.getShots()[i].getX(), ss.getShots()[i].getY(), 0, 0, 0);
									//the currentUFO is hit (if it still haves lifes) and
									if(currentShip.getLifes()>0)currentShip.hit();
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
			
			//5.
			if(loopCount%2==0){//Enemy projectiles only move every second instance of the endless loop
				for(int i=0; i<currentShip.getShots().length; i++){
					if(currentShip.getShots()[i] != null){
						if(currentShip.getShots()[i].getY()<=19){
							for(int x=0; x<3; x++){
								for(int y=0; y<2; y++){
									if(currentShip.getShots()[i].getY()+1==ss.getTopLeftCorner()[1]+y
									   && currentShip.getShots()[i].getX()==ss.getTopLeftCorner()[0]+x && (x!=1 || y!=0)){
										controller.setColor(currentShip.getShots()[i].getX(), currentShip.getShots()[i].getY(), 0, 0, 0);
										currentShip.getShots()[i] = null;
										if(ss.getLifes()>0)ss.hit();
										break;
									}
								}
								if(currentShip.getShots()[i]==null)break;
							}
							if(currentShip.getShots()[i]!=null)currentShip.getShots()[i].moveProjectile("down");
						}
						else{//here the shot is offscreen, so its corresponding array entry can be set to null
							currentShip.getShots()[i] = null;
						}
					}
				}
			}
			
			//6.
			//EnemyShips only move every 30th instance of the endless loop and if they have any lifes left
			if(loopCount%30==0&&currentShip.getLifes()>0){
				
				//They move completely to the right of the board, before moving completely left and back again.
				if(right){
					if(currentShip.getCannons()[currentShip.getCannons().length-1][0]<19){
						//The following lines check if a projectile is on the position the current ship moved to
						for(int i=0; i<ss.getShots().length; i++){
							for(int x=currentShip.getTopLeftCorner()[0]; x<currentShip.getTopLeftCorner()[0]+currentShip.getLength(); x++){
								for(int y=currentShip.getTopLeftCorner()[1]; y<currentShip.getTopLeftCorner()[1]+currentShip.getHeight(); y++){
									if(ss.getShots()[i]!=null){
										if(ss.getShots()[i].getY()==y&&ss.getShots()[i].getX()==x+1){
											if(controller.getColorAt(ss.getShots()[i].getX()-1, ss.getShots()[i].getY())[0]!=0
											 ||controller.getColorAt(ss.getShots()[i].getX()-1, ss.getShots()[i].getY())[1]!=0
											 ||controller.getColorAt(ss.getShots()[i].getX()-1, ss.getShots()[i].getY())[2]!=0){
												//The current ship is hit
												currentShip.hit();
												//and the projectile is set to null.
												ss.getShots()[i] = null;
											}
										}
									}
								}
							}
						}
						currentShip.move('D');
					}
					else right=false;
				}
				else{
					if(currentShip.getCannons()[0][0]>0){
						//The following lines check if a projectile is on the position the current ship moved to
						for(int i=0; i<ss.getShots().length; i++){
							for(int x=currentShip.getTopLeftCorner()[0]; x<currentShip.getTopLeftCorner()[0]+currentShip.getLength(); x++){
								for(int y=currentShip.getTopLeftCorner()[1]; y<currentShip.getTopLeftCorner()[1]+currentShip.getHeight(); y++){
									if(ss.getShots()[i]!=null){
										if(ss.getShots()[i].getY()==y&&ss.getShots()[i].getX()==x-1){
											if(controller.getColorAt(ss.getShots()[i].getX()+1, ss.getShots()[i].getY())[0]!=0
											 ||controller.getColorAt(ss.getShots()[i].getX()+1, ss.getShots()[i].getY())[1]!=0
											 ||controller.getColorAt(ss.getShots()[i].getX()+1, ss.getShots()[i].getY())[2]!=0){
												//The current ship is hit
												currentShip.hit();
												//and the projectile is set to null.
												ss.getShots()[i] = null;
											}
										}
									}
								}
							}
						}
						currentShip.move('A');
					}
					else right=true;
				}
				
			}
			
			//7.
			//Enemy ships only shoot with a chance of 1/45 in every loop and if they have any lifes left
			int random = (int) (Math.random()*45);
			if(random == 2&&currentShip.getLifes()>0){
				
				//A random cannon is chosen with which to shoot
				random = (int) (Math.random()*currentShip.getCannons().length);
				currentShip.shoot(currentShip.getCannons()[random]);
			}
			
			//8.
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null&&ss.getLifes()>0){
				if(event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
					
					switch(event.getKeyCode()){
					
					case java.awt.event.KeyEvent.VK_ESCAPE:
						//Escape makes the game pause
						broken = Gameplay.pause(ss, currentShip);
						if(broken)return false;
						break;

					case java.awt.event.KeyEvent.VK_SPACE:
						//space makes the SS shoot
						for(int i=0; i<ss.getCannons().length; i++){
							ss.shoot(ss.getCannons()[i]);
						}
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
						//The following lines check if a projectile is on the position the Space Shooter is moving to
						for(int i=0; i<currentShip.getShots().length; i++){
							for(int x=ss.getTopLeftCorner()[0]; x<ss.getTopLeftCorner()[0]+3; x++){
								for(int y=ss.getTopLeftCorner()[1]; y<ss.getTopLeftCorner()[1]+2; y++){
									if(currentShip.getShots()[i]!=null){
										if(currentShip.getShots()[i].getY()==y&&currentShip.getShots()[i].getX()==x-1&&(x!=1 || y!=0)){
											//The Space Shooter is hit
											ss.hit();
											//and the projectile is set to null.
											currentShip.getShots()[i] = null;
										}
									}
								}
							}
						}
						ss.move('A');
						break;
					
					case java.awt.event.KeyEvent.VK_D:
						//D makes the SS move right
						//The following lines check if a projectile is on the position the Space Shooter is moving to
						for(int i=0; i<currentShip.getShots().length; i++){
							for(int x=ss.getTopLeftCorner()[0]; x<ss.getTopLeftCorner()[0]+3; x++){
								for(int y=ss.getTopLeftCorner()[1]; y<ss.getTopLeftCorner()[1]+2; y++){
									if(currentShip.getShots()[i]!=null){
										if(currentShip.getShots()[i].getY()==y&&currentShip.getShots()[i].getX()==x+1&&(x!=1 || y!=0)){
											//The Space Shooter is hit
											ss.hit();
											//and the projectile is set to null.
											currentShip.getShots()[i] = null;
										}
									}
								}
							}
						}
						ss.move('D');
						break;
						
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
