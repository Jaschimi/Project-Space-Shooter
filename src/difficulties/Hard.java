package difficulties;

import java.awt.event.KeyEvent;

import game.Gameplay;
import gameObjects.EnemyShip;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.UnnervingFloatingOctopus;
import ufos.BigBoulder;

//This method describes hard mode
public class Hard {

	public static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	public static KeyBuffer buffer = controller.getKeyBuffer();

	public static boolean start(SpaceShooter ss){
		
		//this boolean will be returned to the class Game
		boolean won = false;
		
		//Making of the EnemyShipList for hard mode in two steps:
		//Step 1: Create an EnemyShip array with 100 entries
		//Former Step 2: Design all one-hundred ships by hand because who needs a life anyway?
		//Step 2: Convert the array to a list of EnemyShips and set the first entry as the currentUFO
		
		//Step 1
		EnemyShip[] ufoArray = new EnemyShip[100];

		//The spawning locations of the ships are randomized
		int dsSpawn = (int) (Math.random()*18);
		int bbSpawn = (int) (Math.random()*16);
		int llSpawn = (int) (Math.random()*18);
		int ufoSpawn = (int) (Math.random()*14);

		ufoArray [0] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 1);
		ufoArray [1] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 1);
		ufoArray [2] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 1);
		ufoArray [3] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 2);
		ufoArray [4] = new BigBoulder(new int[]{bbSpawn, 0}, 5, 2);
		ufoArray [5] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 2);
		ufoArray [6] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 2);
		ufoArray [7] = new BigBoulder(new int[]{bbSpawn, 0}, 5, 2);
		ufoArray [8] = new BigBoulder(new int[]{bbSpawn, 0}, 5, 3);
		ufoArray [9] = new DefaultShip(new int[]{dsSpawn, 0}, 4, 3);
		ufoArray[10] = new BigBoulder(new int[]{bbSpawn, 0}, 7, 3);
		ufoArray[11] = new DefaultShip(new int[]{dsSpawn, 0}, 4, 3);
		ufoArray[12] = new LangerLulatsch(new int[]{llSpawn, 0}, 6, 2);
		ufoArray[13] = new BigBoulder(new int[]{bbSpawn, 0}, 10, 4);
		ufoArray[14] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 3);
		ufoArray[15] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 4);
		ufoArray[16] = new LangerLulatsch(new int[]{llSpawn, 0}, 9, 4);
		ufoArray[17] = new BigBoulder(new int[]{bbSpawn, 0}, 10, 4);
		ufoArray[18] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 4);
		ufoArray[19] = new BigBoulder(new int[]{bbSpawn, 0}, 15, 5);
		ufoArray[20] = new LangerLulatsch(new int[]{llSpawn, 0}, 15, 4);
		ufoArray[21] = new BigBoulder(new int[]{bbSpawn, 0}, 20, 5);
		ufoArray[22] = new LangerLulatsch(new int[]{llSpawn, 0}, 18, 6);
		ufoArray[23] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 4);
		ufoArray[24] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 35, 4);
		
		//The spawning locations of the ships are randomized (again)
		dsSpawn = (int) (Math.random()*18);
		bbSpawn = (int) (Math.random()*16);
		llSpawn = (int) (Math.random()*18);
		ufoSpawn = (int) (Math.random()*14);
		int bnSpawn = (int) (Math.random()*12);
				
		ufoArray[25] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 1);
		ufoArray[26] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 1);
		ufoArray[27] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 1);
		ufoArray[28] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 1);
		ufoArray[29] = new LangerLulatsch(new int[]{llSpawn, 0}, 15, 2);
		ufoArray[30] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 2);
		ufoArray[31] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 2);
		ufoArray[32] = new LangerLulatsch(new int[]{llSpawn, 0}, 15, 4);
		ufoArray[33] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 30, 2);
		ufoArray[34] = new BigBoulder(new int[]{bbSpawn, 0}, 20, 2);
		ufoArray[35] = new LangerLulatsch(new int[]{llSpawn, 0}, 18, 6);
		ufoArray[36] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 3);
		ufoArray[37] = new BigBoulder(new int[]{bbSpawn, 0}, 24, 3);
		ufoArray[38] = new LangerLulatsch(new int[]{llSpawn, 0}, 21, 6);
		ufoArray[39] = new DefaultShip(new int[]{dsSpawn, 0}, 15, 3);
		ufoArray[40] = new DefaultShip(new int[]{dsSpawn, 0}, 15, 4);
		ufoArray[41] = new BigBoulder(new int[]{bbSpawn, 0}, 28, 4);
		ufoArray[42] = new LangerLulatsch(new int[]{llSpawn, 0}, 24, 8);
		ufoArray[43] = new DefaultShip(new int[]{dsSpawn, 0}, 15, 4);
		ufoArray[44] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 40, 4);
		ufoArray[45] = new BigBoulder(new int[]{bbSpawn, 0}, 32, 5);
		ufoArray[46] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 50, 6);
		ufoArray[47] = new BigBoulder(new int[]{bbSpawn, 0}, 36, 6);
		ufoArray[48] = new DefaultShip(new int[]{dsSpawn, 0}, 20, 5);
		ufoArray[49] = new BossaNova(new int[]{bnSpawn, 0}, 75, 4);

		//The spawning locations of the ships are randomized again
		dsSpawn = (int) (Math.random()*18);
		bbSpawn = (int) (Math.random()*16);
		llSpawn = (int) (Math.random()*18);
		ufoSpawn = (int) (Math.random()*14);
		bnSpawn = (int) (Math.random()*12);

		ufoArray[50] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 1);
		ufoArray[51] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 1);
		ufoArray[52] = new DefaultShip(new int[]{dsSpawn, 0}, 1, 2);
		ufoArray[53] = new LangerLulatsch(new int[]{llSpawn, 0}, 6, 2);
		ufoArray[54] = new BigBoulder(new int[]{bbSpawn, 0}, 4, 1);
		ufoArray[55] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 2);
		ufoArray[56] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 2);
		ufoArray[57] = new DefaultShip(new int[]{dsSpawn, 0}, 2, 3);
		ufoArray[58] = new LangerLulatsch(new int[]{llSpawn, 0}, 9, 4);
		ufoArray[59] = new BigBoulder(new int[]{bbSpawn, 0}, 8, 2);
		ufoArray[60] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 3);
		ufoArray[61] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 3);
		ufoArray[62] = new DefaultShip(new int[]{dsSpawn, 0}, 3, 4);
		ufoArray[63] = new LangerLulatsch(new int[]{llSpawn, 0}, 12, 4);
		ufoArray[64] = new BigBoulder(new int[]{bbSpawn, 0}, 12, 2);
		ufoArray[65] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 50, 4);
		ufoArray[66] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 4);
		ufoArray[67] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 4);
		ufoArray[68] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 5);
		ufoArray[69] = new LangerLulatsch(new int[]{llSpawn, 0}, 15, 6);
		ufoArray[70] = new BigBoulder(new int[]{bbSpawn, 0}, 16, 3);
		ufoArray[71] = new LangerLulatsch(new int[]{llSpawn, 0}, 18, 6);
		ufoArray[72] = new BigBoulder(new int[]{bbSpawn, 0}, 20, 3);
		ufoArray[73] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 60, 6);
		ufoArray[74] = new BossaNova(new int[]{bnSpawn, 0}, 80, 8);

		//The spawning locations of the ships are randomized again
		dsSpawn = (int) (Math.random()*18);
		bbSpawn = (int) (Math.random()*16);
		llSpawn = (int) (Math.random()*18);
		ufoSpawn = (int) (Math.random()*14);
		bnSpawn = (int) (Math.random()*12);
		
		ufoArray[75] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 1);
		ufoArray[76] = new DefaultShip(new int[]{dsSpawn, 0}, 5, 2);
		ufoArray[77] = new LangerLulatsch(new int[]{llSpawn, 0}, 21, 6);
		ufoArray[78] = new DefaultShip(new int[]{dsSpawn, 0}, 7, 2);
		ufoArray[79] = new DefaultShip(new int[]{dsSpawn, 0}, 7, 3);
		ufoArray[80] = new BigBoulder(new int[]{bbSpawn, 0}, 28, 3);
		ufoArray[81] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 70, 6);
		ufoArray[82] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 3);
		ufoArray[83] = new DefaultShip(new int[]{dsSpawn, 0}, 10, 4);
		ufoArray[84] = new BossaNova(new int[]{bnSpawn, 0}, 110, 12);
		ufoArray[85] = new DefaultShip(new int[]{dsSpawn, 0}, 12, 4);
		ufoArray[86] = new LangerLulatsch(new int[]{llSpawn, 0}, 24, 6);
		ufoArray[87] = new BigBoulder(new int[]{bbSpawn, 0}, 36, 3);
		ufoArray[88] = new UnnervingFloatingOctopus(new int[]{ufoSpawn, 0}, 80, 8);
		ufoArray[89] = new DefaultShip(new int[]{dsSpawn, 0}, 15, 5);
		ufoArray[90] = new LangerLulatsch(new int[]{llSpawn, 0}, 27, 8);
		ufoArray[91] = new DefaultShip(new int[]{dsSpawn, 0}, 20, 5);
		ufoArray[92] = new BigBoulder(new int[]{bbSpawn, 0}, 44, 4);
		ufoArray[93] = new DefaultShip(new int[]{17, 0}, 2, 10);
		ufoArray[94] = new LangerLulatsch(new int[]{12, 0}, 30, 10);
		ufoArray[95] = new BigBoulder(new int[]{14, 0}, 52, 5);
		ufoArray[96] = new UnnervingFloatingOctopus(new int[]{6, 0}, 90, 10);
		ufoArray[97] = new BossaNova(new int[]{4, 0}, 150, 16);
		ufoArray[98] = new DefaultShip.GoldenVersion(new int[]{8, 0}, 100, 5);
		ufoArray[99] = new GalaxyDestroyer(new int[]{0, 0}, 250, 28);

		//Step 2
		EnemyShip currentShip = ufoArray[0];
		for(int i=1; i<ufoArray.length; i++){
			ufoArray[i-1].setNext(ufoArray[i]);
		}

		ss.spawnShip();
		currentShip.spawnShip();

		//This variable counts the amount of times the endless loop in all four difficulties has been started
		int loopCount = 0;
		//These variables count the amount of times the colors of a ship have faded
		int fadeCount = 0;
		int ssFadeCount = 0;
		//This boolean determines if the current ship will move left or right
		boolean right = true;

		while(true){
			//In every instance of the endless loop, nine things may happen:
			//1.: The loop count increases by one
			//2.: It is checked if the current EnemyShip has no lifes left
			//3.: It is checked if the SpaceShooter has no lifes left
			//4.: All shots the SpaceShooter fired are moving upwards by one
			//5.: All shots the currentUFO fired are moving downwards by one
			//6.: The current ship moves in a direction
			//7.: The current EnemyShip shoots a projectile
			//8.: The last keyboard input is detected and one of five actions is performed
			//9.: Finally, the LED stripe is updated
			
			//1.
			loopCount+=1;
			
			//2.
			if(currentShip.getLifes() <= 0){//here the current ship has no lifes left
				//Letting the colors of the destroyed ship fade away
				currentShip.fade();
				currentShip.fade();
				fadeCount++;

				//Here the enemy ship is completely faded away
				if(fadeCount==63){
					fadeCount=0;
					//If there's another UFO in the ufoList, it will become the new current UFO and be spawned now
					if(currentShip.getNext() != null){
						currentShip = currentShip.getNext();
						currentShip.spawnShip();
						//In addition to spawning a new ship, the Space Shooter becomes more and more
						//golden with every UFO it destroys
						int[] shipColor = controller.getColorAt(ss.getTopLeftCorner()[0], ss.getTopLeftCorner()[1]);
						ss.setColorAt(0, 0, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
						ss.setColorAt(2, 0, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
						ss.setColorAt(0, 1, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
						ss.setColorAt(2, 1, shipColor[0]+1, shipColor[1]+1, shipColor[2]-1);
						ss.spawnShip();
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
					
					//Now that the Space Shooter is completely faded out, the current ship moves off of the board
					while(currentShip.getTopLeftCorner()[1]<20){
						currentShip.move('S');
						
						//TODO all projectiles still on the screen have to move downward too
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
			
			//6.
			//EnemyShips only move every 20th instance of the endless loop and if they have any lifes left
			boolean move = true;
			if(loopCount%20==0&&currentShip.getLifes()>0){

				//If they are too far left or right above the Space Shooter, they move closer to it
				if(currentShip.getTopLeftCorner()[0]+currentShip.getLength()<=ss.getTopLeftCorner()[0]){
					//The following lines check if a projectile is on the position the current ship is moving to
					for(int i=0; i<ss.getShots().length; i++){
						for(int x=currentShip.getTopLeftCorner()[0]; x<currentShip.getTopLeftCorner()[0]+currentShip.getLength(); x++){
							for(int y=currentShip.getTopLeftCorner()[1]; y<currentShip.getTopLeftCorner()[1]+currentShip.getHeight(); y++){
								if(ss.getShots()[i]!=null){
									//If there is, the ship won't move
									if(ss.getShots()[i].getY()==y&&ss.getShots()[i].getX()==x+1){
									move = false;
									}
								}
							}
						}
					}
					if(move){
						currentShip.move('D');
						right = true;
					}
				}
				else{
					if(currentShip.getTopLeftCorner()[0]>ss.getTopLeftCorner()[0]+2){
						//The following lines check if a projectile is on the position the current ship is moving to
						for(int i=0; i<ss.getShots().length; i++){
							for(int x=currentShip.getTopLeftCorner()[0]; x<currentShip.getTopLeftCorner()[0]+currentShip.getLength(); x++){
								for(int y=currentShip.getTopLeftCorner()[1]; y<currentShip.getTopLeftCorner()[1]+currentShip.getHeight(); y++){
									if(ss.getShots()[i]!=null){
										//If there is, the ship won't move
										if(ss.getShots()[i].getY()==y&&ss.getShots()[i].getX()==x-1){
											move = false;
										}
									}
								}
							}
						}
						if(move){
							currentShip.move('A');
							right = false;
						}
					}
					else{
						//This generates a random double between 0 and 100
						double random = Math.random()*100;
						
						//With a chance of 9.5%, the current ship changes direction
						if(random<9.5){
							right=!right;
						}
						
						//If they are inside the range and don't change direction during it, they move completely
						//to the right end of it, before moving completely left of it and back again.
						if(right){
							if(currentShip.getCannons()[currentShip.getCannons().length-1][0]<19){
								//The following lines check if a projectile is on the position the current ship is moving to
								for(int i=0; i<ss.getShots().length; i++){
									for(int x=currentShip.getTopLeftCorner()[0]; x<currentShip.getTopLeftCorner()[0]+currentShip.getLength(); x++){
										for(int y=currentShip.getTopLeftCorner()[1]; y<currentShip.getTopLeftCorner()[1]+currentShip.getHeight(); y++){
											if(ss.getShots()[i]!=null){
												//If there is, the ship won't move
												if(ss.getShots()[i].getY()==y&&ss.getShots()[i].getX()==x+1){
													move = false;
												}
											}
										}
									}
								}
								if(move){
									currentShip.move('D');
								}
							}
							else right=false;
						}
						else{
							if(currentShip.getCannons()[0][0]>0){
								//The following lines check if a projectile is on the position the current ship is moving to
								for(int i=0; i<ss.getShots().length; i++){
									for(int x=currentShip.getTopLeftCorner()[0]; x<currentShip.getTopLeftCorner()[0]+currentShip.getLength(); x++){
										for(int y=currentShip.getTopLeftCorner()[1]; y<currentShip.getTopLeftCorner()[1]+currentShip.getHeight(); y++){
											if(ss.getShots()[i]!=null){
												//If there is, the ship won't move
												if(ss.getShots()[i].getY()==y&&ss.getShots()[i].getX()==x-1){
												move = false;
												}
											}
										}
									}
								}
								if(move){
									currentShip.move('A');
								}
							}
							else right=true;
						}
					}
				}

			}
			
			//7.
			//Enemy ships only shoot with a chance of 1/25 in every loop and if they have any lifes left
			int random = (int) (Math.random()*25);
			if(random == 2&&currentShip.getLifes()>0){
				
				//The GalaxyDestroyer has a different shooting mechanic than the other ships
				if(currentShip instanceof GalaxyDestroyer){
					random = (int) (Math.random()*9+1);
					if(random==10){
						currentShip.shoot(currentShip.getCannons()[0]);
						currentShip.shoot(currentShip.getCannons()[1]);
						currentShip.shoot(currentShip.getCannons()[2]);
					}
					else{
						if(random>5){
							currentShip.shoot(currentShip.getCannons()[2]);
							if(random>7){
								currentShip.shoot(currentShip.getCannons()[1]);
							}
						}
						else{
							if(random==5){
								currentShip.shoot(currentShip.getCannons()[1]);
							}
							else{
								currentShip.shoot(currentShip.getCannons()[0]);
								if(random<3){
									currentShip.shoot(currentShip.getCannons()[1]);
								}
							}
						}
					}
				}
				else{
					//All cannons of a ship are firing (if enough ammunition exists)
					for(int i=0; i<currentShip.getCannons().length; i++){
						currentShip.shoot(currentShip.getCannons()[i]);
					}
				}
			}
			
			//8.
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null&&ss.getLifes()>0){
				if (event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
					
					switch (event.getKeyCode()){

					case java.awt.event.KeyEvent.VK_ESCAPE:
						//Escape makes the game pause
						Gameplay.pause();
						break;
						
					case java.awt.event.KeyEvent.VK_SPACE:
						//space makes the SS shoot
						ss.shoot(ss.getCannons()[0]);
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
							for(int y=ss.getTopLeftCorner()[1]; y<ss.getTopLeftCorner()[1]; y++){
								if(currentShip.getShots()[i]!=null){
									if(currentShip.getShots()[i].getY()==y&&currentShip.getShots()[i].getX()==ss.getTopLeftCorner()[0]){
										if(controller.getColorAt(currentShip.getShots()[i].getX()+1, currentShip.getShots()[i].getY())[0]!=0
										 ||controller.getColorAt(currentShip.getShots()[i].getX()+1, currentShip.getShots()[i].getY())[1]!=0
										 ||controller.getColorAt(currentShip.getShots()[i].getX()+1, currentShip.getShots()[i].getY())[2]!=0){
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
							for(int y=ss.getTopLeftCorner()[1]; y<ss.getTopLeftCorner()[1]+3; y++){
								if(currentShip.getShots()[i]!=null){
									if(currentShip.getShots()[i].getY()==y&&currentShip.getShots()[i].getX()==ss.getTopLeftCorner()[0]+3){
										if(controller.getColorAt(currentShip.getShots()[i].getX()-1, currentShip.getShots()[i].getY())[0]!=0
										 ||controller.getColorAt(currentShip.getShots()[i].getX()-1, currentShip.getShots()[i].getY())[1]!=0
										 ||controller.getColorAt(currentShip.getShots()[i].getX()-1, currentShip.getShots()[i].getY())[2]!=0){
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
			
//			ufo1.shoot();
//				while(projectile.getY()<20){
//					
//					controller.sleep(200);
//					projectile.moveProjectile("down");
//					controller.updateLedStripe();
//				}
		}
		//These lines only activate once all enemy ships are defeated or the SS has no lifes left
		controller.updateLedStripe();
		return won;
	}
}
