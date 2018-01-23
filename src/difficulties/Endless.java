package difficulties;

import java.awt.event.KeyEvent;

import game.Gameplay;
import gameObjects.EnemyShip;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.BigBoulder;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.UFO;

public class Endless{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer = controller.getKeyBuffer();

	public static boolean broken = false;
	
	public static int start(SpaceShooter ss){
		
		int shipCount=0;
		
		EnemyShip currentShip = new DefaultShip(new int[]{(int) (Math.random()*18), 0}, 1, 1);
		ss.spawn();
		currentShip.spawn();

		//This variable counts the amount of times the endless loop has been started
		int loopCount = 0;
		//These variables count the amount of times the colors of a ship have faded
		int enemyFadeCount = 0, ssFadeCount = 0;
		//And these variables notice if a ship has been hit
		boolean enemyHit = false, ssHit = false;
		//This boolean determines if the current ship will move left or right
		boolean right = true;

		controller.updateLedStripe();
		
		while(true){
			//In every instance of the endless loop, ten things may happen:
			//0.: The loop count increases by one
			//1.: Hit ships regain color
			//2.: It is checked if the current EnemyShip has no lives left
			//3.: It is checked if the SpaceShooter has no lives left
			//4.: All shots the SpaceShooter fired move upwards by one
			//5.: All shots the current ship fired move downwards by one
			//6.: The current ship moves in a direction
			//7.: The current EnemyShip shoots a projectile
			//8.: The last keyboard input is detected and one of six actions is performed
			//9.: Finally, the LED stripe is updated

			//0.
			loopCount+=1;
			
			//1.
			if(ssHit){
				ss.spawn();
				ssHit = false;
			}
			if(enemyHit){
				currentShip.spawn();
				enemyHit = false;
			}
			
			//2.
			if(currentShip.getLives() <= 0){//here the current ship has no lives left
				//Letting the colors of the destroyed ship fade away
				currentShip.fade();
				currentShip.fade();
				enemyFadeCount++;

				//Here the enemy ship is completely faded away
				if(enemyFadeCount==63){
					enemyFadeCount=0;
					
					//A new enemyShip is generated based on a few parameters,
					EnemyShip successor;
					double random;
					
					if(shipCount%25==0){
						random = Math.random()*10;
						if(random<1){
							successor = new GalaxyDestroyer(new int[]{0, 0}, 250-shipCount>0 ? shipCount : 250, 7*(int)(Math.random()*4)+7);
						}
						else{
							if(random<4){
								successor = new BossaNova(new int[]{(int) (Math.random()*12), 0}, 150-shipCount>0 ? shipCount : 150, 4*(int)(Math.random()*4)+4);
							}
							else{
								successor = new UFO(new int[]{(int) (Math.random()*14), 0}, 100-shipCount>0 ? shipCount : 100, 2*(int)(Math.random()*5)+2);
							}
						}
					}
					else{
						if(shipCount%5==1){
							if(shipCount<75){
								successor = new DefaultShip(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*3)+1, (int)(Math.random()*3)+1);
							}
							else{
								successor = new DefaultShip(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*7)+1, (int)(Math.random()*5)+1);
							}
						}
						else{
							if(shipCount%25==24){
								if(shipCount<75){
									successor = new DefaultShip(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*5)+5, (int)(Math.random()*3)+1);
								}
								else{
									successor = Math.random()<0.25 ? 
											new DefaultShip.GoldenVersion(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*50)+50, (int)(Math.random()*5)+5) :
											new DefaultShip(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*10)+10, (int)(Math.random()*5)+1);
								}
							}
							else{
								random = Math.random()*10;
								if(random<6){
									if(shipCount<50){
										successor = new DefaultShip(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*10)+1, (int)(Math.random()*5)+1);
									}
									else{
										successor = new DefaultShip(new int[]{(int)(Math.random()*18), 0}, (int)(Math.random()*10)+3, (int)(Math.random()*5)+2);
									}
								}
								else{
									if(random<8){
										if(shipCount<75){
											successor = new BigBoulder(new int[]{(int)(Math.random()*16), 0}, 4*(int)(Math.random()*6)+4, (int)(Math.random()*4)+2);
										}
										else{
											if(random<6.1){
												successor = new BossaNova(new int[]{(int)(Math.random()*12), 0}, 150-shipCount>0 ? shipCount/2 : 75, 2*(int)(Math.random()*4)+2);
											}
											else{
												successor = new BigBoulder(new int[]{(int)(Math.random()*16), 0}, 4*(int)(Math.random()*12)+4, (int)(Math.random()*5)+2);
											}
										}
									}
									else{
										if(shipCount<50){
											successor = new LangerLulatsch(new int[]{(int)(Math.random()*18), 0}, 3*(int)(Math.random()*5)+3, 2*(int)(Math.random()*3)+2);
										}
										else{
											if(random<8.2){
												successor = new UFO(new int[]{(int)(Math.random()*14), 0}, 100-shipCount>0 ? shipCount/2 : 50, (int)(Math.random()*5)+2);
											}
											else{
												successor = new LangerLulatsch(new int[]{(int)(Math.random()*18), 0}, 3*(int)(Math.random()*10)+3, 2*(int)(Math.random()*5)+2);
											}
										}
									}
								}
							}
						}
					}
					//set as the successor of the currentShip
					currentShip.setNext(successor);
					
					//and is spawned.
					if(currentShip.getNext() != null){
						currentShip = currentShip.getNext();
						currentShip.spawn();
					}
					else{//this else branch is never activated, the game never ends °-°
						break;
					}
				}
			}
			
			//3.
			if(ss.getLives()==0){
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
									//the currentUFO is hit (if it still haves lives) and
									if(currentShip.getLives()>0){
										enemyHit = currentShip.hit();
										if(currentShip.getLives()==0)shipCount++;
									}
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
			if(shipCount>=25||loopCount%2==0){
				for(int i=0; i<currentShip.getShots().length; i++){
					if(currentShip.getShots()[i] != null){
						if(currentShip.getShots()[i].getY()<=19){
							for(int x=0; x<3; x++){
								for(int y=0; y<2; y++){
									if(currentShip.getShots()[i].getY()+1==ss.getTopLeftCorner()[1]+y
									   && currentShip.getShots()[i].getX()==ss.getTopLeftCorner()[0]+x && (x!=1 || y!=0)){
										controller.setColor(currentShip.getShots()[i].getX(), currentShip.getShots()[i].getY(), 0, 0, 0);
										currentShip.getShots()[i] = null;
										if(ss.getLives()>0)ssHit = ss.hit();
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
			//EnemyShips only move every 30th instance of the endless loop and if they have any lives left when less than 25
			//ships have been defeated
			if(shipCount<25){
				if(loopCount%30==0&&currentShip.getLives()>0){
				
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
													enemyHit = currentShip.hit();
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
													enemyHit = currentShip.hit();
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
			}
			else{
				if(shipCount<50){
					//EnemyShips only move every 25th instance of the endless loop and if they have any lives left when less than 50
					//and at least 25 ships have been defeated
					if(loopCount%25==0&&currentShip.getLives()>0){
						
						//This generates a random double between 0 and 100
						double random = Math.random()*100;
						
						//With a chance of 4%, the current ship changes direction
						if(random<4){
							right=!right;
						}
						
						//If they don't change direction during it, 
						//they move completely to the right of the board, before moving completely left and back again.
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
														enemyHit = currentShip.hit();
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
														enemyHit = currentShip.hit();
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
				}
				else{
					//EnemyShips only move every 20th instance of the endless loop and if they have any lives left when at least 50
					//ships have been defeated
					boolean move = true;
					if(loopCount%20==0&&currentShip.getLives()>0){

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
				}
			}
			
			//7.
			//Enemy ships only shoot with a chance of 1/45, 1/35 or 1/25 in every loop and if they and the SpaceShooter
			//have any lives left
			int random = shipCount<25 ?(int) (Math.random()*45) : shipCount<50 ? (int) (Math.random()*35) : (int) (Math.random()*25);
			if(random == 2&&currentShip.getLives()>0&&ss.getLives()>0){
				
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
					if(shipCount<50){//If less than 50 ships have been defeated, a random cannon is chosen with which to shoot
						random = (int) (Math.random()*currentShip.getCannons().length);
						currentShip.shoot(currentShip.getCannons()[random]);
					}
					else{//If at least 50 ships have been defeated, all cannons of a ship are firing (if enough ammunition exists)
						for(int i=0; i<currentShip.getCannons().length; i++){
							currentShip.shoot(currentShip.getCannons()[i]);
						}
					}
				}
			}
			
			//8.
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null&&ss.getLives()>0){
				if(event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
					
					switch(event.getKeyCode()){

					case java.awt.event.KeyEvent.VK_ESCAPE:
						//Escape makes the game pause
						broken = Gameplay.pause(ss, currentShip);
						if(broken)return shipCount;
						break;
						
					case java.awt.event.KeyEvent.VK_SPACE:
						//space makes the SS shoot
						for(int i=0; i<ss.getCannons().length; i++){
							ss.shoot(ss.getCannons()[i]);
						}
						break;
					
					case java.awt.event.KeyEvent.VK_W:
						//W makes the SS move up
						if(ss.getTopLeftCorner()[1]>10)ss.move('W');
						break;
					
					case java.awt.event.KeyEvent.VK_S:
						//S makes the SS move down
						if(ss.getTopLeftCorner()[1]+2<20)ss.move('S');
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
											ssHit = ss.hit();
											//and the projectile is set to null.
											currentShip.getShots()[i] = null;
										}
									}
								}
							}
						}
						if(ss.getTopLeftCorner()[0]+3/2>0)ss.move('A');
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
											ssHit = ss.hit();
											//and the projectile is set to null.
											currentShip.getShots()[i] = null;
										}
									}
								}
							}
						}
						if(ss.getTopLeftCorner()[0]+3/2+1<20)ss.move('D');
						break;
						
					}
				}
			}
			
			//9.
			controller.updateLedStripe();

		}
		
		return shipCount;
		
	}
}
