package game;
import displayObjects.Word;
import gameObjects.EnemyShip;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.UFO;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.BigBoulder;

//This class contains all endings of the game, including the credits.
public abstract class Endscreen{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR); 
	
	//This public method starts the win or loss animation respectively. The difficulty changes a few things in them and the shipColor
	//determines the color of a few things.
	public static void outro(boolean won, int[] shipColor, int difficulty){
		
		if(won) win(shipColor, difficulty);
		else loss(shipColor, difficulty);
		
	}
	
	//The winning animation
	private static void win(int[] fireworkColor, int difficulty){
		
        int[][][] point = new int[20][20][3];
		
		int count = 0;
		int left = 8;
		int right = 10;
		int up = 19;

		//Special case: generates two points in the middle of the bottom of the board
		for(int i=0; i<3; i++){
			point[left][up][i] = fireworkColor[i];
			point[right][up][i] = fireworkColor[i];
			
			controller.setColors(point);
			controller.updateLedStripe();
		}
		
		up--;
		left--;
		right++;

		//This loop generates points diagonally and deletes the ones generated in the last instance of it
		for(count=0; count<4; count++){
			for(int i=0; i<3; i++){
				
				point[left][up][i] = fireworkColor[i];
				point[right][up][i] = fireworkColor[i];
				
				point[left + 1][up + 1][i] = 0;
				point[right - 1][up + 1][i] = 0;
				
				controller.setColors(point);
			    controller.updateLedStripe();
				
			}
			
			up--;
			left--;
			right++;
		}
		
		for(int i=0; i<3; i++){
			
			point[left][up][i] = fireworkColor[i];
			point[right][up][i] = fireworkColor[i];
			
			point[left + 1][up + 1][i] = 0;
			point[right - 1][up + 1][i] = 0;
			
			controller.setColors(point);
		    controller.updateLedStripe();
			
		}
		
		int speicher = 3;

		//This loop generates two explosions. Points on the diagonals around the middle one will be generated and 
		//the points that are already generated will become black.
		for(int j=0; j<4; j++){
			for(int i=0; i<3; i++){
				point[left - (j - 1)][up][i] = 0;
			    point[right - (j - 1)][up][i] = 0;	
			
			    point[left + (j - 1)][up][i] = 0;
			    point[right + (j - 1)][up][i] = 0;
			
			    point[left][up - (j - 1)][i] = 0;
			    point[right][up - (j - 1)][i] = 0;
			
			    point[left][up + (j - 1)][i] = 0;
			    point[right][up + (j - 1)][i] = 0;
			    
//------------------------------------------------------------------------------------
		
		        point[left - j][up][i] = fireworkColor[i];
			    point[right - j][up][i] = fireworkColor[i];	
			
			    point[left + j][up][i] = fireworkColor[i];
			    point[right + j][up][i] = fireworkColor[i];
			
			    point[left][up - j][i] = fireworkColor[i];
			    point[right][up - j][i] = fireworkColor[i];
			
			    point[left][up + j][i] = fireworkColor[i];
			    point[right][up + j][i] = fireworkColor[i];
			    

				
				controller.setColors(point);
			    controller.updateLedStripe();
			}
			
		}

		//Since the endscreen works with a 3-dim. array to display the colors, it has to be set to null, otherwise everything saved
		//in it would be displayed again once it is used to do that
		point = new int[20][20][3];
		controller.setColors(point);
		
		//Fireworks on both sides are done, so now the middle one starts
		int top = 19;
		count = 0;
		int mid = 9;
		
		int leffi = 8;
		int rightti = 10;

		int uppi = 19;

		//Depending on the difficulty, this while loop generates the diagonal points for the two smaller explosions (like before)
		//and the middle line for the big explosion.
		while(count < 14&&difficulty!=3){

			//Special case. Same as before in the other loops.
			if(count == 0){
				for(int i=0; i<3; i++){
					
					point[mid][top][i] = fireworkColor[i];
					
					controller.setColors(point);
				    controller.updateLedStripe();
					
				}
			    
			    top-=2;
			    count+=2;
			    
			    
			}
			else{
				
				if(difficulty == 2){
					if(count > 3 && count < 14){

						final Word c = new Word("C");
						c.displayWordAt(0, 0, 8, 0, 5);
						point = controller.getColors();
						
						uppi--;
						leffi--;
						rightti++;
						
					}
				}
		
				for(int i=0; i<3; i++){
			
					point[mid][top][i] = fireworkColor[i];
					point[mid][top + 2][i] = 0;
					
					if(difficulty == 2) {
						point[leffi][uppi][i] = fireworkColor[i];
						point[rightti][uppi][i] = fireworkColor[i];
						
						if(count > 3 && count < 14){
		
							point[leffi][uppi][i] = fireworkColor[i];
							point[rightti][uppi][i] = fireworkColor[i];
							
							point[leffi + 1][uppi + 1][i] = 0;
							point[rightti - 1][uppi + 1][i] = 0;
						
						}
					}
				
					controller.setColors(point);
					controller.updateLedStripe();
				}
				
			top-=2;
			count+=2;
		
			}
		}

		if(difficulty == 3) {
			
			Misc.makeAll(point, fireworkColor);
			
			controller.resetColors();
			
		}

        if(difficulty < 3) {

            for(int i=0; i<3; i++){
    			
    			point[mid][top][i] = fireworkColor[i];
    			point[mid][top + 2][i] = 0;
    			
    			controller.setColors(point);
    		    controller.updateLedStripe();
    		}
			Misc.makeBoom(point,fireworkColor,mid,top, 5, difficulty);
			
        }
        if(difficulty==2){
        	
        	int a = 0;

        	//This huge loop generates the middle and the smaller fireworks at the same time
			for(int j=0; j<5 ; j++){
			
				if(j<4){
					
					if(j%2 ==1) {
						//this parameter is for the middle explosion (the big one) in order to generate the diagonal points
						a++;
						
					}
					
					for(int i=0; i<3; i++){
						
						point[left - (j - 1)][up][i] = 0;
					    point[right - (j - 1)][up][i] = 0;	
					
					    point[left + (j - 1)][up][i] = 0;
					    point[right + (j - 1)][up][i] = 0;
					
					    point[left][up - (j - 1)][i] = 0;
					    point[right][up - (j - 1)][i] = 0;
					
					    point[left][up + (j - 1)][i] = 0;
					    point[right][up + (j - 1)][i] = 0;
					    
					    
					    point[mid + (a - 1)][top + (a - 1)][i] = 0;
					    point[mid - (a - 1)][top - (a - 1)][i] = 0;
					    
					    point[mid + (a - 1)][top - (a - 1)][i] = 0;
					    
					    point[mid - (a - 1)][top + (a - 1)][i] = 0;
					    
					    
					    
				
						point[mid - (j - 1)][top][i] = 0;
					    
					    point[mid + (j - 1)][top][i] = 0;
					    
					    point[mid][top - (j - 1)][i] = 0;
					    
					    point[mid][top + (j - 1)][i] = 0;
				
	//---------------------------------------------------------------------------------------
					    
					    point[left - j][up][i] = fireworkColor[i];
					    point[right - j][up][i] = fireworkColor[i];	
					
					    point[left + j][up][i] = fireworkColor[i];
					    point[right + j][up][i] = fireworkColor[i];
					
					    point[left][up - j][i] = fireworkColor[i];
					    point[right][up - j][i] = fireworkColor[i];
					
					    point[left][up + j][i] = fireworkColor[i];
					    point[right][up + j][i] = fireworkColor[i];
					    
					   
					    point[mid - a][top - a][i] = fireworkColor[i];
					    point[mid + a][top - a][i] = fireworkColor[i];
					    
					    point[mid - a][top + a][i] = fireworkColor[i];
					    point[mid + a][top + a][i] = fireworkColor[i];
					    
					    
					    
				        point[mid - j][top][i] = fireworkColor[i];
					    
					    point[mid + j][top][i] = fireworkColor[i];
					    
					    point[mid][top - j][i] = fireworkColor[i];
					    
					    point[mid][top + j][i] = fireworkColor[i];
					    
					    controller.setColors(point);
					    controller.updateLedStripe();
					    
					}
			    
				}
				else{
					
					for(int i=0; i<3; i++){
						
						point[mid - (j - 1)][top][i] = 0;
					    
					    point[mid + (j - 1)][top][i] = 0;
					    
					    point[mid][top - (j - 1)][i] = 0;
					    
					    point[mid][top + (j - 1)][i] = 0;
					    
					    point[left - (speicher - 1)][up][i] = 0;
					    point[right - (speicher - 1)][up][i] = 0;	
					
					    point[left + (speicher - 1)][up][i] = 0;
					    point[right + (speicher - 1)][up][i] = 0;
					
					    point[left][up - (speicher - 1)][i] = 0;
					    point[right][up - (speicher - 1)][i] = 0;
					
					    point[left][up + (speicher - 1)][i] = 0;
					    point[right][up + (speicher - 1)][i] = 0;
	
					    controller.setColors(point);
					    controller.updateLedStripe();
					
					}
				}
			}
        }
		
		//Now that all fireworks are done, the phrase "YOU WON" will be displayed on the board
		controller.resetColors();
		
		final Word you = new Word("You");
		final Word won = new Word("won");
		
		you.displayWordAt(4, 3, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
		won.displayWordAt(2, 9, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
		
		if(difficulty==1){
			final Word shift = new Word("shift");
			shift.displayWordAt(0, 15, 5, 0, 4);
		}
		
		controller.updateLedStripe();
		controller.sleep(4000);
		
	}
	
	//The losing animation
	private static void loss(int[] shipColor, int difficulty){
		
		EnemyShip[] endOfWorld = new EnemyShip[4];

		//Depending on the difficulty, four, five or all types of EnemyShips will be generated
		if(difficulty==2){
			endOfWorld = new EnemyShip[5];
			endOfWorld[4] = new BossaNova(new int[]{10, -25}, 3, 4);
		}
		if(difficulty==3){
			endOfWorld = new EnemyShip[6];
			endOfWorld[4] = new BossaNova(new int[]{10, -25}, 3, 4);
			endOfWorld[5] = new GalaxyDestroyer(new int[]{0, -35}, 250, 5);
		}

		endOfWorld[0] = new DefaultShip(new int[]{3, -4}, 1, 1);
		endOfWorld[1] = new UFO(new int[]{13, -10}, 1, 4);
		endOfWorld[2] = new LangerLulatsch(new int[]{5, -14}, 1, 2);
		endOfWorld[3] = new BigBoulder(new int[]{0, -20}, 2, 2);
		
		//All ships are spawned at once, even though they are all off the screen at first
		for(int i=0; i<endOfWorld.length; i++){
			endOfWorld[i].spawn();
		}
		
		//This variable counts the amount of times the following loop has been started
		int loopCount=0;
		//The loop continues until the top left corner of the last ship of the endOfWorld array is off the screen
		while(endOfWorld[endOfWorld.length-1].getTopLeftCorner()[1]<21){
		
			loopCount++;
			
			//All projectiles on the screen move down one spot every second instance of the loop
			if(loopCount%2==0)
			for(int j=0; j<endOfWorld.length; j++){
				for(int i=0; i<endOfWorld[j].getShots().length; i++){
					if(endOfWorld[j].getShots()[i] != null){
						if(endOfWorld[j].getShots()[i].getY()<=19){
							endOfWorld[j].getShots()[i].moveProjectile("down");
						}
						else{//here the shot is offscreen, so its corresponding array entry can be set to null
							endOfWorld[j].getShots()[i] = null;
						}
					}
				}
			}
			
			//Every 15th instance of the loop, all ships move down by one spot
			if(loopCount%15==0){
				for(int i=0; i<endOfWorld.length; i++){
					endOfWorld[i].move('S');
				}
			}
		
			//Every 5th instance of the loop, all ships on the board shoot a projectile
			if(loopCount%5==0){
				for(int i=0; i<endOfWorld.length; i++){
					if(endOfWorld[i].getTopLeftCorner()[1]+endOfWorld[i].getHeight()-1>=0){
						if(i==5){
							endOfWorld[i].shoot(endOfWorld[i].getCannons()[1]);
						}
						else{
							if(i==1){
								endOfWorld[i].shoot(endOfWorld[i].getCannons()[1]);
							}
							endOfWorld[i].shoot(endOfWorld[i].getCannons()[0]);
						}
						
					}
				}
			}
		
		controller.updateLedStripe();
		
		}
		
        controller.resetColors();
        
        //Now all EnemyShips have flown across the screen and the phrase "YOU LOST" will be displayed on the board
		final Word you = new Word("You");
		final Word lost = new Word("lost");

		//Here the shipColor gets inverted
		shipColor = new int[]{127-shipColor[0], 127-shipColor[1], 127-shipColor[2]};
		
		you.displayWordAt(4, 1, shipColor[0], shipColor[1], shipColor[2]);
		lost.displayWordAt(2, 7, shipColor[0], shipColor[1], shipColor[2]);
		
		controller.updateLedStripe();
		controller.sleep(2500);
		
		letItGo(controller.getColors(),shipColor,15);
	
	}
	
	//This method lets all dots on the board which have the specified color fall down 
	static void letItGo(int[][][] board, int[] color, int pause){
    	
		boolean isTrue = false;
		
//		controller.sleep(pause);
		controller.setColors(board);
	
		for(int fromBelow = 19; fromBelow >= 0; fromBelow--){
			
			//The top of the board is a special case
			if(fromBelow == 0){
				
				int count = 0;
				
				while(count < 3){
				
					for(int fromRight = 0; fromRight < 19; fromRight++){
						//If there's at least one point in the specified color in the line, isTrue is true.
						if((board[fromRight][fromBelow][0] == color[0]) && (board[fromRight][fromBelow][1] == color[1]) && (board[fromRight][fromBelow][2] == color[2])){       

							isTrue = true;
							
							for(int i = 0; i < color.length; i++){

								//The point in the specified color is set to black and a new one is displayed below it
								board[fromRight][fromBelow][i] = 0;
								board[fromRight][fromBelow + 1][i] = color[i];
									
							}
							
						}
						
					}
				
					controller.setColors(board);
					controller.updateLedStripe();
					
					count++;
					fromBelow++;
				
				}
				//If the line contains dots in the specified color, it can fall down
				if(isTrue){

					//goDown starts at the height of fromBelow and all points in this line will fall down
					for(int goDown = fromBelow; goDown < board.length; goDown++){
						//This loop goes from left to right and 
						for(int goRight = 0; goRight < board.length; goRight++){
							if((board[goRight][goDown][0] == color[0]) && (board[goRight][goDown][1] == color[1]) && (board[goRight][goDown][2] == color[2])){       
								
								for(int i = 0; i < color.length; i++){

									//special case! all possible points are deleted, so the board becomes black
									//when the points reached the ground of the board.
									if(goDown == board.length - 1){
										board[goRight][goDown - 3][i] = 0;
										board[goRight][goDown - 2][i] = 0;
										board[goRight][goDown - 1][i] = 0;
										board[goRight][goDown][i] = 0;
										
									}
									else{

										//special case!
										//If this is true, the line is directly below the line fromBelow, so a trail for the points
										//cannot be created because the points would be outside of the board
										if(goDown - 1 > fromBelow){
											if(color[i] - 35 < 0){
												
												board[goRight][goDown - 3][i] = 0;
														
												board[goRight][goDown - 2][i] = 0;
												
												board[goRight][goDown - 1][i] = 0;
												
												board[goRight][goDown][i] = 0;
												
												board[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												board[goRight][goDown - 3][i] = 0;
													
												board[goRight][goDown - 2][i] = color[i] - 35;
													
												board[goRight][goDown - 1][i] = color[i] - 35;
													
												board[goRight][goDown][i] = color[i] - 35;
													
												board[goRight][goDown + 1][i] = color[i];
													
											}
											
										}
										else{
										
											if(color[i] - 35 < 0){
												
												board[goRight][goDown][i] = 0;
												
												board[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												board[goRight][goDown][i] = color[i] - 35;
													
												board[goRight][goDown + 1][i] = color[i];
													
											}
										}
									}
								}
							}
						}
						
						controller.setColors(board);
						controller.sleep(pause);
						controller.updateLedStripe();
						
					}
				}
				
				isTrue = false;
				fromBelow = 0;
				
			}
			//This is for all the other cases where fromBelow isn't at the top of the board
			else{
				for(int goRight=0; goRight<board.length; goRight++){
					
					if((board[goRight][fromBelow][0] == color[0]) && (board[goRight][fromBelow][1] == color[1]) && (board[goRight][fromBelow][2] == color[2])){       
//						same as before!
						isTrue = true;
						
						for(int i = 0; i < color.length; i++){
									
							board[goRight][fromBelow][i] = 0;
							if(fromBelow<19){
								board[goRight][fromBelow + 1][i] = color[i];
							}
								
						}
					}
				}
			
				controller.setColors(board);
				controller.updateLedStripe();
				
				if(isTrue){
//					same as before!
					for(int goDown = fromBelow; goDown < 20; goDown++){
						
						for(int goRight = 0; goRight < 20; goRight++){
							
							if((board[goRight][goDown][0] == color[0]) && (board[goRight][goDown][1] == color[1]) && (board[goRight][goDown][2] == color[2])){       
								
								for(int i = 0; i < 3; i++){
									
									if(goDown == 19){
										
										board[goRight][goDown - 3][i] = 0;
										board[goRight][goDown - 2][i] = 0;
										board[goRight][goDown - 1][i] = 0;
										board[goRight][goDown][i] = 0;
										
									}
									else{
										
										if(goDown - 1 > fromBelow){
											
											if(color[i] - 35 < 0){
												
												board[goRight][goDown - 3][i] = 0;
														
												board[goRight][goDown - 2][i] = 0;
												
												board[goRight][goDown - 1][i] = 0;
												
												board[goRight][goDown][i] = 0;
												
												board[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												board[goRight][goDown - 3][i] = 0;
													
												board[goRight][goDown - 2][i] = color[i] - 35;
													
												board[goRight][goDown - 1][i] = color[i] - 35;
													
												board[goRight][goDown][i] = color[i] - 35;
													
												board[goRight][goDown + 1][i] = color[i];
													
											}
										}
										else{
										
											if(color[i] - 35 < 0){
												
												board[goRight][goDown][i] = 0;
												
												board[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												board[goRight][goDown][i] = color[i] - 35;
													
												board[goRight][goDown + 1][i] = color[i];
													
											}
										}
									}
								}
							}
						}
						
						controller.setColors(board);
						controller.sleep(pause);
						controller.updateLedStripe();
						
					}	
				}
				
				isTrue = false;
				
			}
		}
	}

	//This method shows all the people who worked on this Project or contributed to it in some way <3
	static void credits(){

		controller.resetColors();
		
		//The crew
		final Word cre = new Word("Cre-");
		final Word dits = new Word("dits");
		
		final Word ja = new Word("Ja-");
		final Word scha = new Word("scha");
		final Word oli = new Word("Oli-");
		final Word ver = new Word("ver");
		final Word oleh = new Word("Oleh");
		final Word mar = new Word("Mar-");
		final Word vin = new Word("vin");
		
		final Word idea = new Word("Idea");
		
		final Word head = new Word("head");
		final Word of = new Word("of");
		final Word deve = new Word("deve-");
		final Word lop = new Word("lop-");
		final Word ment = new Word("ment");

		final Word crea = new Word("crea-");
		final Word tive = new Word("tive");

		final Word de = new Word("de-");
		final Word sign = new Word("sign");
		
		final Word supper = new Word("super");
		final Word visor = new Word("visor");
		
		//Thank you very much :-D
		final Word spe = new Word("spe-");
		final Word cial = new Word("cial");
		final Word thx = new Word("thx");

		final Word help = new Word("Help");
		final Word eme = new Word("Eme-");
		final Word rald = new Word("rald");
		
		final Word bug = new Word("Bug");
		final Word hun = new Word("Hun-");
		final Word ter = new Word("ter");
		final Word tom = new Word("Tom");
		
		final Word nice = new Word("nice");
		final Word plat = new Word("plat-");
		final Word form = new Word("form");
		final Word git = new Word("git-");
		final Word hub = new Word("hub");
		
		//A helping Word
		final Word space = new Word("");
		
		Word[] credits = new Word[]{cre, dits, space, space, idea, space, oleh, space, crea, tive, de, sign, space, oli, ver, space,
				head, of, deve, lop, ment, space, ja, scha, space, supper, visor, space, mar, vin, space};
		
		//Some colors for the people
		int[] black = new int[]{0, 0, 0};
		int[] gold = new int[]{127, 107, 0};
		int[] title = new int[]{90, 90, 90};
		int[] person = new int[]{90, 0, 90};
		int[] jascha = new int[]{121, 69, 57};
		int[] oliver = new int[]{37, 124, 89};
		int[] oleg = new int[]{92, 5, 36};
		int[] marvin = new int[]{117, 80, 2};
		
		int[][] colors = new int[][]{gold,gold,black,black,title,black,oleg,black,title,title,title,title,black,oliver,oliver,black,
						title,title,title,title,title,black,jascha,jascha,black,title,title,black,marvin,marvin,black};
		for(int y=20; y>-credits.length*6+5; y--){
			for(int i=0; i<credits.length; i++){
				credits[i].displayWordAt(0, y+i*6+1, 0, 0, 0);
				credits[i].displayWordAt(0, y+i*6, colors[i][0], colors[i][1], colors[i][2]);
			}
			controller.updateLedStripe();
			controller.sleep(175);
		}
		
		credits = new Word[]{spe, cial, thx, space, space, de, sign, help, space, eme, rald, space, bug, hun, ter, space,
							 tom, space, nice, plat, form, space, git, hub, space};
		
		//Some more colors for some other people
		title = new int[]{125, 125, 118};
		int[] specialThx = new int[]{110, 45, 59};
		int[] emerald = new int[]{32, 122, 4};
		
		colors = new int[][]{specialThx,specialThx,specialThx,black,black,title,title,title,black,emerald,emerald,black,title,title,title,black,
							 person,black,title,title,title,black,person,person,black};
			
		for(int y=20; y>-credits.length*6+5; y--){
			for(int i=0; i<credits.length; i++){
				credits[i].displayWordAt(0, y+i*6+1, 0, 0, 0);
				credits[i].displayWordAt(0, y+i*6, colors[i][0], colors[i][1], colors[i][2]);
			}
			controller.updateLedStripe();
			controller.sleep(175);
		}
	}
}
