package difficulties;

import java.awt.event.KeyEvent;

import displayObjects.Word;
import game.Gameplay;
import gameObjects.EnemyShip;
import gameObjects.SpaceShooter;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;
import ufos.DefaultShip;
import ufos.BigBoulder;

//This class describes the tutorial
public abstract class Tutorial{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	private static KeyBuffer buffer = controller.getKeyBuffer();
	
	public static void start(SpaceShooter ss){
		
		//This boolean will determine if the Tutorial has been won or not
		boolean won = false;
		
		//These Words will be used throughout the tutorial
		final Word tutorial = new Word("In this tutorial, you will learn the basics of the game.");
		
		final Word SS = new Word("This is your ship, the Space Shooter.");
		final Word leftRight = new Word("Press A or D to move left and right.");
		final Word upDown = new Word("Press W or S to move up and down.");
		final Word shoot = new Word("Press Space to shoot.");
		
		final Word ufo = new Word("This is an enemy ship.");
		final Word movement = new Word("It can also move.");
		final Word projectile = new Word("Watch out for its projectiles!");
		final Word hit = new Word("If they hit you, you lose a life.");
		
		final Word lives = new Word("Depending on how many you have, the dot in the center of your ship is green, yellow or red.");
		final Word green = new Word("Depending on how many you have, the dot in the center of your ship is ");
		final Word yellow = new Word("green, ");
		final Word red = new Word("yellow or");
		final Word alt = new Word("alt");
		final Word dead = new Word("Once you lose all, your ship is destroyed and the game is over.");

		final Word you = new Word("You");
		final Word lost = new Word("lost");

		final Word dot = new Word("As with your ship, the dot in the enemy ships center shows its lives.");
		final Word destroy = new Word("An enemy ship is destroyed when it too loses all lives.");
		final Word next = new Word("If you destroy a ship, another one may spawn.");
		final Word goal = new Word("Your goal is to destroy all of them without dying.");
		
		final Word test = new Word("Try beating the following 5 enemies.");
		
		final Word allSet = new Word("Now you are all set to play a regular game.");
		final Word ships = new Word("There are a lot more than just these two types of enemy ships, so be prepared.");
		final Word good = new Word("Good");
		final Word luck = new Word("luck!");
		
		//They will be displayed in the following color
		int c1,c2,c3;
		c1=47;
		c2=97;
		c3=2;
		
		//This is a shortcut ;-)
		boolean skip = false;
		//Displaying the first Word
		for(int x=20; x>-tutorial.getLength() ;x--){
			
			//Shortcut
			if(skip){
				controller.resetColors();
				break;
			}
			
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null){
				if(event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
					
					switch(event.getKeyCode()){
					
					//Pressing enter activates the shortcut
					case java.awt.event.KeyEvent.VK_ENTER:
						buffer.clear();
						skip = true;
						break;
					}
				}
			}
			
			//Moving the Word one space to the left every 100 milliseconds
			tutorial.displayWordAt(x+1, 0, 0, 0, 0);
			tutorial.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Spawning the SpaceShooter and explaining what it is
		ss.spawn();
		for(int x=20; x>-SS.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			//Moving the Word one space to the left every 100 milliseconds
			SS.displayWordAt(x+1, 0, 0, 0, 0);
			SS.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Starting the left/right movement test. It ends once this variable reaches 10.
		int count = 0;
		buffer.clear();
		while(count<10){
			
			//Shortcut
			if(skip)break;
			
			for(int x=20; x>-leftRight.getLength() ;x--){
				
				KeyEvent event = buffer.pop();
				buffer.clear();
				if(event != null){
					if(event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
						
						switch(event.getKeyCode()){
						
						case java.awt.event.KeyEvent.VK_A:
							//A makes the SS move left
							ss.move('A');
							//and the count increase by one.
							count +=1;
							break;
						
						case java.awt.event.KeyEvent.VK_D:
							//D makes the SS move right
							ss.move('D');
							//and the count increase by one.
							count+=1;
							break;
						}
					}
				}
					
				//Moving the Word one space to the left every 100 milliseconds
				leftRight.displayWordAt(x+1, 0, 0, 0, 0);
				//Putting the break statement here makes sure that no text is still visible after it
				if(count == 10){
					break;
				}
				leftRight.displayWordAt(x, 0, c1, c2, c3);
				controller.updateLedStripe();
				controller.sleep(100);
			}
		}
		
		//Starting the up/down movement test. It ends once this variable reaches 10.
		count = 0;
		while(count<10){
			
			//Shortcut
			if(skip)break;
			
			for(int x=20; x>-upDown.getLength() ;x--){
				
				KeyEvent event = buffer.pop();
				buffer.clear();
				if(event != null){
					if(event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
						
						switch(event.getKeyCode()){
						
						case java.awt.event.KeyEvent.VK_W:
							//W makes the SS move up
							ss.move('W');
							//and the count increase by one.
							count +=1;
							break;
						
						case java.awt.event.KeyEvent.VK_S:
							//S makes the SS move down
							ss.move('S');
							//and the count increase by one.
							count+=1;
							break;
						}
					}
				}	
				//Moving the Word one space to the left every 100 milliseconds
				upDown.displayWordAt(x+1, 0, 0, 0, 0);
				//Putting the break statement here makes sure that no text is still visible after it
				if(count == 10){
					break;
				}
				upDown.displayWordAt(x, 0, c1, c2, c3);
				controller.updateLedStripe();
				controller.sleep(100);
			}
		}
		
		//Starting the shooting test. It ends once this variable reaches 7.
		count = 0;
		while(count<7){
			
			//Shortcut
			if(skip)break;
			
			for(int x=20; x>-shoot.getLength() ;x--){
				
				//Moving the Word one space to the left every 100 milliseconds
				shoot.displayWordAt(x+1, 0, 0, 0, 0);
				//Putting the break statement here makes sure that no text is still visible after it
				if(count == 7){
					break;
				}
				shoot.displayWordAt(x, 0, c1, c2, c3);
				
				//This loop makes all projectiles shot move up by one
				for(int i=0; i<ss.getShots().length; i++){
					//Only when an entry is not null(i.e. a projectile has been shot), the rest of the code is accessed
					if(ss.getShots()[i] != null){
						//This if statement checks if the projectile is still on the screen
						if(ss.getShots()[i].getY()>=0){
							//If that's the case, it will move up by one spot
							ss.getShots()[i].moveProjectile("up");
						}
						else{//here the shot is offscreen, so its corresponding array entry can be set to null
							ss.getShots()[i] = null;
						}
					}
				}
	
				KeyEvent event = buffer.pop();
				buffer.clear();
				if(event != null){
					if(event.getID()==java.awt.event.KeyEvent.KEY_RELEASED && event.getKeyCode()==java.awt.event.KeyEvent.VK_SPACE){
						//Space makes the SS shoot
						ss.shoot(ss.getCannons()[0]);
						//and the count increase by one.
						count +=1;
						break;
					}
				}
				
				controller.updateLedStripe();
				controller.sleep(100);
			}
		}
		
		//Before the EnemyShip explanation starts, the Space Shooter moves to its starting location again
		while(ss.getTopLeftCorner()[0]!=9||ss.getTopLeftCorner()[1]!=18){
			
			//Shortcut
			if(skip)break;
			
			//Any projectiles still on the screen continue moving upwards
			for(int i=0; i<ss.getShots().length; i++){
				if(ss.getShots()[i] != null){
					if(ss.getShots()[i].getY()>=0){
						ss.getShots()[i].moveProjectile("up");
					}
					else{
						ss.getShots()[i] = null;
					}
				}
			}
			
			//If the SS is above its starting position, it will move down by one spot
			if(ss.getTopLeftCorner()[1]<18){
				ss.move('S');
				controller.updateLedStripe();
				controller.sleep(100);
			}
			else{
				//If the SS is left of its starting position, it will move right by one spot
				if(ss.getTopLeftCorner()[0]<9){
					ss.move('D');
					controller.updateLedStripe();
					controller.sleep(100);
				}
				//If the SS is right of its starting position, it will move left by one spot
				if(ss.getTopLeftCorner()[0]>9){
					ss.move('A');
					controller.updateLedStripe();
					controller.sleep(100);
				}
			}
		}
		
		//After the Space Shooter is at its starting position, all shots continue moving upwards
		while(ss.getShots()[0]!=null||ss.getShots()[1]!=null||ss.getShots()[2]!=null){
			
			//Shortcut
			if(skip)break;
			
			for(int i=0; i<ss.getShots().length; i++){
				if(ss.getShots()[i] != null){
					if(ss.getShots()[i].getY()>=0){
						ss.getShots()[i].moveProjectile("up");
					}
					else{
						ss.getShots()[i] = null;
					}
				}
			}
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Secondly, a new DefaultShip is created and spawned, while its explanation appears on the screen
		EnemyShip currentShip = new DefaultShip(new int[]{8, 6}, 1, 1);
		currentShip.spawn();
		for(int x=20; x>-ufo.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			//Moving the Word one space to the left every 100 milliseconds
			ufo.displayWordAt(x+1, 0, 0, 0, 0);
			ufo.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Starting enemy moving test. The current ship moves in a different direction every time the loop starts again.
		count = -1;
		for(int x=20; x>-movement.getLength()-2 ;x--){
			
			//Shortcut
			if(skip)break;
			
			count+=1;
			if(count%8==0){
				currentShip.move('A');
			}
			else{
				if(count%8==2){
					currentShip.move('S');
				}
				else{
					if(count%8==4){
						currentShip.move('D');
					}
					else{
						if(count%8==6){
							currentShip.move('W');
						}
					}
				}
			}
			//Moving the Word one space to the left every 100 milliseconds
			movement.displayWordAt(x+1, 0, 0, 0, 0);
			movement.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Starting enemy shooting test. The current ship shoots a projectile every time the loop starts and it has ammo left.
		count=0;
		for(int x=20; x>-projectile.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;
			
			//This loop makes all projectiles shot move down by one
			for(int i=0; i<currentShip.getShots().length; i++){
				//Only when an entry is not null(i.e. a projectile has been shot), the rest of the code is accessed
				if(currentShip.getShots()[i] != null){
					//This if statement checks if the projectile is still on the screen
					if(currentShip.getShots()[i].getY()<20){
						//If that's the case, it will move up by one spot
						currentShip.getShots()[i].moveProjectile("down");
					}
					else{//here the shot is offscreen, so its corresponding array entry can be set to null
						currentShip.getShots()[i] = null;
					}
				}
			}
			
			if(count%8==0){
				currentShip.shoot(currentShip.getCannons()[0]);
			}
			
			//Moving the Word one space to the left every 100 milliseconds
			projectile.displayWordAt(x+1, 0, 0, 0, 0);
			projectile.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Showing what getting hit by a projectile does
		count=0;
		for(int x=20; x>-hit.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;
			
			//The enemy ship moves one space to the right
			if(count==51){
				currentShip.move('D');
			}
			
			//The enemy ship shoots once
			if(count==56){
				currentShip.shoot(currentShip.getCannons()[0]);
			}
			
			//The projectile moves down one spot eleven times
			if(count>56&&count<66){
				currentShip.getShots()[0].moveProjectile("down");
			}
			
			//Here the projectile hits the Space Shooter
			if(count==66){
				controller.setColor(currentShip.getShots()[0].getX(), currentShip.getShots()[0].getY(), 0, 0, 0);
				currentShip.getShots()[0]=null;
				ss.hit();
			}
			
			if(count==67)ss.spawn();
			
			//Moving the Word one space to the left every 100 milliseconds
			hit.displayWordAt(x+1, 0, 0, 0, 0);
			hit.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Explaining life
		count=0;
		for(int x=20; x>-lives.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;
			
			//The dot turns green
			if(count==green.getLength()){
				ss.setLives(3);
				ss.setColorAt(1, 1, 5, 107, 17);
				ss.spawn();
			}
			
			//The enemy ship shoots
			if(count==green.getLength()+yellow.getLength()){
				currentShip.shoot(currentShip.getCannons()[0]);
			}
			
			//The projectile moves down one spot eleven times
			if(count>green.getLength()+yellow.getLength()&&count<green.getLength()+yellow.getLength()+10){
				currentShip.getShots()[0].moveProjectile("down");
			}
			
			//Here the projectile hits the Space Shooter
			if(count==green.getLength()+yellow.getLength()+10){
				controller.setColor(currentShip.getShots()[0].getX(), currentShip.getShots()[0].getY(), 0, 0, 0);
				currentShip.getShots()[0]=null;
				ss.hit();
			}

			if(count==green.getLength()+yellow.getLength()+11)ss.spawn();
			
			//The enemy ship shoots
			if(count==green.getLength()+yellow.getLength()+red.getLength()){
				currentShip.shoot(currentShip.getCannons()[0]);
			}
			
			//The projectile moves down one spot eleven times
			if(count>green.getLength()+yellow.getLength()+red.getLength()&&count<green.getLength()+yellow.getLength()+red.getLength()+10){
				currentShip.getShots()[0].moveProjectile("down");
			}
			
			//Here the projectile hits the Space Shooter
			if(count==green.getLength()+yellow.getLength()+red.getLength()+10){
				controller.setColor(currentShip.getShots()[0].getX(), currentShip.getShots()[0].getY(), 0, 0, 0);
				currentShip.getShots()[0]=null;
				ss.hit();
			}

			if(count==green.getLength()+yellow.getLength()+red.getLength()+11)ss.spawn();
			
			//Moving the Word one space to the left every 100 milliseconds
			lives.displayWordAt(x+1, 0, 0, 0, 0);
			lives.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}

		//Explaining death
		count=0;
		for(int x=20; x>-dead.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;

			//The enemy ship shoots once
			if(count==56){
				currentShip.shoot(currentShip.getCannons()[0]);
			}
			
			//The projectile moves down one spot eleven times
			if(count>56&&count<66){
				currentShip.getShots()[0].moveProjectile("down");
			}
			
			//Here the projectile hits the Space Shooter
			if(count==66){
				controller.setColor(currentShip.getShots()[0].getX(), currentShip.getShots()[0].getY(), 0, 0, 0);
				currentShip.getShots()[0]=null;
				ss.hit();
			}
			
			//Letting the colors of the Space Shooter fade out over time
			if(count>66 && count<194){
				ss.fade();
			}
			
			if(count==194){

				//The phrase "YOU LOST" shows up on the board
				int[] color = new int[]{127, 20, 0};
				you.displayWordAt(4, 9, color[0], color[1], color[2]);
				lost.displayWordAt(2, 15, color[0], color[1], color[2]);
				
			}
			//Moving the Word one space to the left every 100 milliseconds
			dead.displayWordAt(x+1, 0, 0, 0, 0);
			dead.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}

		//"YOU LOST" disappears
		you.displayWordAt(4, 9, 0, 0, 0);
		lost.displayWordAt(2, 15, 0, 0, 0);
		
		//Explaining the enemy dot
		for(int x=20; x>-dot.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			//Moving the Word one space to the left every 100 milliseconds
			dot.displayWordAt(x+1, 0, 0, 0, 0);
			dot.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Recoloring, reviving and respawning the Space Shooter
		ss.setColorAt(0, 0, 104, 23, 47);
		ss.setColorAt(1, 0, 0, 0, 0);
		ss.setColorAt(2, 0, 104, 23, 47);
		ss.setColorAt(0, 1, 104, 23, 47);
		ss.setColorAt(1, 1, 5, 107, 17);
		ss.setColorAt(2, 1, 104, 23, 47);
		ss.setLives(3);
		ss.spawn();
		
		//Shooting at the currentShip and destroying it
		count=0;
		for(int x=20; x>-destroy.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;

			//The Space Shooter shoots once
			if(count==56){
				ss.shoot(ss.getCannons()[0]);
			}
			
			//The projectile moves up one spot eleven times
			if(count>56&&count<66){
				ss.getShots()[0].moveProjectile("up");
			}
			
			//Here the projectile hits the currentShip
			if(count==66){
				controller.setColor(ss.getShots()[0].getX(), ss.getShots()[0].getY(), 0, 0, 0);
				ss.getShots()[0]=null;
				currentShip.hit();
			}
			
			//Letting the colors of the currentShip fade out over time
			if(count>66 && count<154){
				currentShip.fade();
			}
			
			//Moving the Word one space to the left every 100 milliseconds
			destroy.displayWordAt(x+1, 0, 0, 0, 0);
			destroy.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Spawning another ship
		currentShip = new DefaultShip(new int[]{8, 6}, 3, 1);
		count=-1;
		for(int x=20; x>-next.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;
			
			if(count==80)currentShip.spawn();
			
			//Moving the Word one space to the left every 100 milliseconds
			next.displayWordAt(x+1, 0, 0, 0, 0);
			next.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Showcasing the goal of the game with an epic battle
		count=-1;
		for(int x=20; x>-goal.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			count++;
			
			//The Space Shooter shoots once
			if(count==16){
				ss.shoot(ss.getCannons()[0]);
			}
			
			//The projectile moves up one spot nine times
			if(count>16&&count<26){
				ss.getShots()[0].moveProjectile("up");
			}
			
			//Here the projectile hits the currentShip
			if(count==26){
				controller.setColor(ss.getShots()[0].getX(), ss.getShots()[0].getY(), 0, 0, 0);
				ss.getShots()[0]=null;
				currentShip.hit();
			}
			
			if(count==27)currentShip.spawn();
			
			//The current ship shoots once
			if(count==40){
				currentShip.shoot(currentShip.getCannons()[0]);
			}

			//The projectile moves down one spot twelve times
			if(count>40&&count<53){

				if(count==45)ss.move('D');
				
				currentShip.getShots()[0].moveProjectile("down");
			}
			
			//Here the shot is offscreen, so its corresponding array entry can be set to null
			if(count==53){
				currentShip.getShots()[0] = null;
			}
			
			
			//The Space Shooter moves left again
			if(count==66)ss.move('A');
			
			
			//The Space Shooter shoots twice
			if(count==74||count==77){
				ss.shoot(ss.getCannons()[0]);
			}
			
			//The first projectile moves up one spot three times
			if(count>74&&count<78){
				ss.getShots()[0].moveProjectile("up");
			}
			//Both projectiles moves up one spot six times
			if(count>77&&count<84){
				ss.getShots()[0].moveProjectile("up");
				ss.getShots()[1].moveProjectile("up");
			}
			
			//Here the first projectile hits the currentShip
			if(count==84){
				controller.setColor(ss.getShots()[0].getX(), ss.getShots()[0].getY(), 0, 0, 0);
				ss.getShots()[0]=null;
				currentShip.hit();
				//The other projectile keeps moving upwards
				ss.getShots()[1].moveProjectile("up");
			}
			
			if(count==85)currentShip.spawn();
			
			//The second projectile moves up one spot a lot of times
			if(count>84&&count<92){
				//The current ship dodges to the left
				if(count==86)currentShip.move('A');
				ss.getShots()[1].moveProjectile("up");
			}
			
			//Here the shot is offscreen, so its corresponding array entry can be set to null
			if(count==92){
				ss.getShots()[1]=null;
			}
			
			
			//The current ship shoots once
			if(count==100){
				currentShip.shoot(currentShip.getCannons()[0]);
			}

			//The projectile moves down one spot nine times
			if(count>100&&count<110){
				//The Space Shooter moves left again
				if(count==105||count==107||count==109)ss.move('A');
				//and shoots once
				if(count==109)ss.shoot(ss.getCannons()[0]);
				currentShip.getShots()[0].moveProjectile("down");
			}
			
			//Here the shot hits the Space Shooter
			if(count==110){
				controller.setColor(currentShip.getShots()[0].getX(), currentShip.getShots()[0].getY(), 0, 0, 0);
				currentShip.getShots()[0]=null;
				ss.hit();
				//and its projectile moves up
				ss.getShots()[0].moveProjectile("up");
			}
			
			//The SS projectile keeps moving...
			if(count>110&&count<119){
				ss.getShots()[0].moveProjectile("up");
				//The Space Shooter moves left again
				if(count==111)ss.move('A');
			}
			
			//...and finally hits the current ship a third time
			if(count==119){
				controller.setColor(ss.getShots()[0].getX(), ss.getShots()[0].getY(), 0, 0, 0);
				ss.getShots()[0]=null;
				currentShip.hit();
			}
			
			//Letting the colors of the currentShip fade out over time
			if(count>119 && count<207){
				currentShip.fade();
			}
			
			//Moving the Word one space to the left every 100 milliseconds
			goal.displayWordAt(x+1, 0, 0, 0, 0);
			goal.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}

		//Telling the player to start with the actual tutorial
		for(int x=20; x>-test.getLength() ;x--){
			
			//Shortcut
			if(skip)break;
			
			//Moving the Word one space to the left every 100 milliseconds
			test.displayWordAt(x+1, 0, 0, 0, 0);
			test.displayWordAt(x, 0, c1, c2, c3);
			controller.updateLedStripe();
			controller.sleep(100);
		}
		
		//Making an EnemyShipList for the actual tutorial in two steps:
		//Step 1: Create an EnemyShip array with 5 entries
		//Step 2: Convert the array to a list of EnemyShips and set the first entry as the currentShip
		
		//Step 1
		EnemyShip[] ufoArray = new EnemyShip[5];
		
		ufoArray[0] = new DefaultShip(new int[]{5, 0}, 1, 1);
		ufoArray[1] = new DefaultShip(new int[]{8, 0}, 2, 2);
		ufoArray[2] = new DefaultShip(new int[]{5, 3}, 3, 3);
		ufoArray[3] = new BigBoulder(new int[]{0, 0}, 4, 4);
		ufoArray[4] = new BigBoulder(new int[]{0, 6}, 5, 5);

		//Step 2
		currentShip = ufoArray[0];
		for(int i=1; i<ufoArray.length; i++){
			ufoArray[i-1].setNext(ufoArray[i]);
		}
		
		count=0;
		controller.resetColors();
		//Reviving, recoloring and respawning the Space Shooter
		ss.setLives(3);
		ss.setColorAt(1, 1, 5, 107, 17);
		ss.spawn();

		//Spawning the first ship
		currentShip.spawn();
		
		//These variables counts the amount of times the colors of a ship have faded
		int enemyFadeCount = 0, ssFadeCount = 0;
		//And these variables notice if a ship has been hit
		boolean enemyHit = false, ssHit = false;
		
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

//			if(skip){
//				won = true;
//				break;
//			}
			
			//0.
			count+=1;
			
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
			if(currentShip.getLives() == 0){
				//removing the colors of the destroyed ship
				currentShip.fade();
				currentShip.fade();
				enemyFadeCount++;
				
				//Here the currentShip faded out completely
				if(enemyFadeCount==63){
					enemyFadeCount=0;
					//If there's another enemyShip in the enemyShipList, it will become the new current ship and be spawned now
					if(currentShip.getNext() != null){
						currentShip = currentShip.getNext();
						currentShip.spawn();
					}
					else{//here all enemies have been defeated, so the game has been won and the endless loop can be exited
						won = true;
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
				
				//Here the Space Shooter is faded out almost completely, so the game ends
				if(ssFadeCount==63){
					ssFadeCount=0;
					ss.fade();
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
						//If that's the case, the program checks if any part of the current ship is directly above the projectile
						for(int x=0; x<currentShip.getLength(); x++){
							for(int y=0; y<currentShip.getHeight(); y++){
								if(ss.getShots()[i].getY()-1==currentShip.getTopLeftCorner()[1]+y
								   && ss.getShots()[i].getX()==currentShip.getTopLeftCorner()[0]+x
								   &&(controller.getColorAt(ss.getShots()[i].getX(), ss.getShots()[i].getY()-1)[0]!=0
								   ||controller.getColorAt(ss.getShots()[i].getX(), ss.getShots()[i].getY()-1)[1]!=0
								   ||controller.getColorAt(ss.getShots()[i].getX(), ss.getShots()[i].getY()-1)[2]!=0)){
									//If that is also the case, the projectile's color is changed to black,
									controller.setColor(ss.getShots()[i].getX(), ss.getShots()[i].getY(), 0, 0, 0);
									//the currentShip is hit (if it still has lives)
									if(currentShip.getLives()>0)enemyHit = currentShip.hit();
									//and the projectile is set to null.
									ss.getShots()[i] = null;
									break;
								}
							}
							//This statement is only triggered if the projectile hit the current ship
							if(ss.getShots()[i]==null)break;
						}
						//If the current ship is not directly above the projectile and the projectile is not null,
						//it will move up by one spot
						if(ss.getShots()[i]!=null)ss.getShots()[i].moveProjectile("up");
					}
					else{//here the shot is offscreen, so its corresponding array entry can be set to null
						ss.getShots()[i] = null;
					}
				}
			}
			
			//5.
			if(count%2==0){//Enemy projectiles only move every second instance of the endless loop
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
			//EnemyShips only move every 50th instance of the endless loop and if they have any lives left
			if(count%50==0&&currentShip.getLives()>0){
				
				//This generates a random integer from 0 to 3
				int random = (int) (Math.random()*4);
				
				//Based on what integer was generated, the char direction becomes one of four values
				char direction = 0;
				boolean move = true;
				switch(random){
				
				case 0:
					if(currentShip.getTopLeftCorner()[1]>0)direction = 'W';
					else move = false;
					break;
				case 1:
					if(currentShip.getTopLeftCorner()[0]>0)direction = 'A';
					else move = false;
					break;
				case 2:
					if(currentShip.getTopLeftCorner()[1]+currentShip.getHeight()<9)direction = 'S';
					else move = false;
					break;
				case 3:
					if(currentShip.getTopLeftCorner()[0]+currentShip.getLength()<19)direction = 'D';
					else move = false;
					break;
				}
				
				//The following lines check if a projectile is on the position the current ship moved to
				switch(direction){
				
				case 'A':
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
					break;
					
				case 'D':
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
					break;
				}
				
				//And lastly, the current ship moves in the generated direction
				if(move)currentShip.move(direction);

			}
			
			//7.
			//Enemy ships only shoot with a chance of 1/45 in every loop
			int random = (int) (Math.random()*45);
			if(random == 2&&currentShip.getLives()>0){
				currentShip.shoot(currentShip.getCannons()[0]);
			}
			
			//8.
			KeyEvent event = buffer.pop();
			buffer.clear();
			if(event != null && ss.getLives()>0 && event.getID() == java.awt.event.KeyEvent.KEY_RELEASED){
					
				switch(event.getKeyCode()){
				
				case java.awt.event.KeyEvent.VK_ESCAPE:
					//Escape makes the game pause
					Gameplay.pause(ss, currentShip);
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
			
			//9.
			controller.updateLedStripe();
			
		}
		
		controller.resetColors();
		controller.updateLedStripe();
		
		if(won){
			//Wrapping up the tutorial
			for(int x=20; x>-allSet.getLength() ;x--){
				
//				if(skip)break;
					
				//Moving the Word one space to the left every 100 milliseconds
				allSet.displayWordAt(x+1, 0, 0, 0, 0);
				allSet.displayWordAt(x, 0, c1, c2, c3);
				controller.updateLedStripe();
				controller.sleep(100);
			}
			
			//Informing the player of the other types of ships
			EnemyShip peter = new DefaultShip(new int[]{-5, 10}, 2, 0);
			EnemyShip kopernikus = new BigBoulder(new int[]{20, 15}, 12, 0);
			peter.spawn();
			kopernikus.spawn();
			char dsDirection = 'D';
			char bbDirection = 'A';
			count=-1;
			for(int x=20; x>-ships.getLength() ;x--){
				
//				if(skip)break;
				
				count++;
				
				if(count>25&&count<ships.getLength()-20){
					if(count%215==85){
						dsDirection = 'S';
						bbDirection = 'W';
					}
					if(count%215==120){
						dsDirection = 'A';
						bbDirection = 'D';
					}
					if(count%215==180){
						dsDirection = 'W';
						bbDirection = 'S';
					}
					if(count%215==0){
						dsDirection = 'D';
						bbDirection = 'A';
					}
					if(count%5==0){
						peter.move(dsDirection);
						kopernikus.move(bbDirection);
					}
				}
				else{
					if(count%5==0){
						peter.move(dsDirection);
						kopernikus.move(bbDirection);
					}
				}
				
				//Moving the Word one space to the left every 100 milliseconds
				ships.displayWordAt(x+1, 0, 0, 0, 0);
				ships.displayWordAt(x, 0, c1, c2, c3);
				controller.updateLedStripe();
				controller.sleep(100);
			}
			
			//Wishing them good luck
			for(int x=20;x>-luck.getLength();x--){
				good.displayWordAt(x+2, 5, 0, 0, 0);
				good.displayWordAt(x+1, 5, 127, 127, 52);
				alt.displayWordAt(5, 0, 0, 6, 4);
				luck.displayWordAt(x+2, 12, 0, 0, 0);
				luck.displayWordAt(x+1, 12, 127, 127, 52);
				controller.updateLedStripe();
				controller.sleep(100);
				
				if(x==0)controller.sleep(3875);
			}
		}
		else{
			//...
			final Word tooBad = new Word("Whelp, you failed the tutorial.");
			final Word tryAgain = new Word("You may want to try it again before playing an actual game.");
			final Word proTip = new Word("Tip: You can skip straight to the interactive part with pressing ENTER at the start.");
			
			//Rip
			for(int x=20; x>-tooBad.getLength() ;x--){
				
				//Moving the Word one space to the left every 100 milliseconds
				tooBad.displayWordAt(x+1, 0, 0, 0, 0);
				tooBad.displayWordAt(x, 0, 69, 4, 4);
				controller.updateLedStripe();
				controller.sleep(100);
			}
			
			//At least they can try again
			for(int x=20; x>-tryAgain.getLength() ;x--){
				
				//Moving the Word one space to the left every 100 milliseconds
				tryAgain.displayWordAt(x+1, 0, 0, 0, 0);
				tryAgain.displayWordAt(x, 0, 69, 4, 4);
				controller.updateLedStripe();
				controller.sleep(100);
			}
			
			//And now they will get to know a secret cheat code!
			for(int x=20; x>-proTip.getLength() ;x--){
				
				//Moving the Word one space to the left every 100 milliseconds
				proTip.displayWordAt(x+1, 0, 0, 0, 0);
				proTip.displayWordAt(x, 0, 69, 4, 4);
				controller.updateLedStripe();
				controller.sleep(100);
			}
			
		}
	}
	
}