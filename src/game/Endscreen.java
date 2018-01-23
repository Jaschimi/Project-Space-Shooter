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

public abstract class Endscreen{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR); 
	
	public static void outro(boolean won, int[] shipColor, int difficulty){
		
		if(won) win(shipColor, difficulty);
		else loss(shipColor, difficulty);
		
	}
	
	private static void win(int[] fireworkColor, int difficulty){
		
        int[][][] point = new int[20][20][3];
		
		int count = 0;
		int left = 8;
		int right = 10;
		int up = 19;

		for(int i=0; i<3; i++){
//			Special case: generates two points on the ground of the board around the middle point (9).
			point[left][up][i] = fireworkColor[i];
			point[right][up][i] = fireworkColor[i];
			
			controller.setColors(point);
			controller.updateLedStripe();
		}
		
		up--;
		left--;
		right++;
				
		for(count=0; count<4; count++){
//			generates points on the diagonal and deletes the points I generated before (on the same diagonal).
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
			
		for(int j=0; j<4; j++){
			for(int i=0; i<3; i++){
//	Here I generate the two explosions. Points on the diagonals around the middle one will be generated and 
//	the points that are already generated will become black.
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

		//Since we are working with a 3-dim. array to set the colors, it has to be set to null, otherwise everything saved
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
		
		while(count < 14){
			
			if(difficulty == 3) {
				
				Misc.makeAll(point, fireworkColor);
				
				controller.resetColors();
				
				Word you = new Word("You");
				Word won = new Word("won");
				
				you.displayWordAt(5, 4, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
				won.displayWordAt(3, 10, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
				
				controller.updateLedStripe();
				controller.sleep(4000);

				controller.resetColors();
				
				return;
				
			}
			
			if(count == 0){
//				special case. Same as before in the other loops.
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
// This while loop generates the diagonals for the two smaller explosions (like before)
// and the middle line for the big explosion.
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
		
        for(int i=0; i<3; i++){
			
			point[mid][top][i] = fireworkColor[i];
			point[mid][top + 2][i] = 0;
			
			controller.setColors(point);
		    controller.updateLedStripe();
		}
        

        if(difficulty < 3) {
        	
			Misc.makeBoom(point,fireworkColor,mid,top, 5, difficulty);
			
        }
        if(difficulty!=1){
        	if(difficulty==3){
        		Misc.makeMegaBoom(point, fireworkColor);
        	}
//        	this huge loop generates the middle and the smaller fireworks at the same time as you can watch in 
//        	endscreenTest();
        	int a = 0;
			
			for(int j=0; j<5 ; j++){
			
			if(j<4){
				
				if(j%2 ==1) {
//					this parameter is for the middle explosion (the big one) that I can generate the diagonal 
//					points
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
		
		//Now that all fireworks are done, the phrase "You won" will be displayed on the board
		controller.resetColors();
		
		final Word you = new Word("You");
		final Word won = new Word("won");
		
		you.displayWordAt(5, 3, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
		won.displayWordAt(3, 9, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
		
		if(difficulty==1){
			final Word shift = new Word("shift");
			shift.displayWordAt(0, 15, 5, 0, 4);
		}
		
		controller.updateLedStripe();
		controller.sleep(4000);
		
	}
	
	private static void loss(int[] color, int difficulty){
		
		EnemyShip[] endOfWorld = new EnemyShip[4];

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
		
		for(int i=0; i<endOfWorld.length; i++){
			endOfWorld[i].spawn();
		}
		
		//This variable counts the amount of times the following loop has been started
		int loopCount=0;
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
		
		Word you = new Word("You");
		Word lost = new Word("lost");

		//Here the color gets inverted
		color = new int[]{127-color[0], 127-color[1], 127-color[2]};
		
		you.displayWordAt(3, 1, color[0], color[1], color[2]);
		lost.displayWordAt(2, 7, color[0], color[1], color[2]);
		
		controller.updateLedStripe();
		controller.sleep(2500);
		
		int[][][] point = new int[20][20][3];
		
		for(int x=0; x<20; x++){
			for(int y=0; y<20; y++){
				point[x][y]=controller.getColorAt(x, y);
			}
		}
		
		controller.updateLedStripe();
		
		letItGo(point,color,15);
	
	}
	
	static void letItGo(int[][][] point, int[] color, int pause){
    	
	    int[][][] copy = new int[20][20][3];
	    
	    for(int x=0; x<point.length; x++){
	    	for(int y=0; y<point[x].length; y++){
	    		
	    		for(int i=0; i<color.length; i++){
	    			
	    			copy[x][y][i] = point[x][y][i];
	    			
	    		}
	    		
	    	}
	    	
	    }
	
		boolean isTrue = false;
		
//		controller.sleep(pause);
		controller.setColors(point);
	
		for(int fromBelow = 19; fromBelow >= 0; fromBelow--){
			
			if(fromBelow == 0){
				
				int count = 0;
				
				while(count < 3){
				
					for(int fromRight = 0; fromRight < point.length; fromRight++){
						
						if((point[fromRight][fromBelow][0] == color[0]) && (point[fromRight][fromBelow][1] == color[1]) && (point[fromRight][fromBelow][2] == color[2])){       
//							if the line isn´t completly black isTrue is true.
							isTrue = true;
							
							for(int i = 0; i < color.length; i++){
										
								point[fromRight][fromBelow][i] = 0;
								point[fromRight][fromBelow + 1][i] = color[i];
//								and at the point where the line isnt black i create a new point under the actual one
//								and I delete the actual one.
									
							}
							
						}
						
					}
				
					controller.setColors(point);
					controller.updateLedStripe();
					
					count++;
					fromBelow++;
				
				}
//				if the line is completely black this is unnessesarry but if not I can let a word fall down.
				if(isTrue == true){
					
					for(int goDown = fromBelow; goDown < point.length; goDown++){
//						goDown start at the point I am now and all points in this line (horizontal) will
//						fall down.
						for(int goRight = 0; goRight < point.length; goRight++){
//							in this line I go from left to right and 
							if((point[goRight][goDown][0] == color[0]) && (point[goRight][goDown][1] == color[1]) && (point[goRight][goDown][2] == color[2])){       
								
								for(int i = 0; i < color.length; i++){
									
									if(goDown == point.length - 1){
//										special case! delete all possible points, so the beard gets black when the points reached the ground of the board.
										point[goRight][goDown - 3][i] = 0;
										point[goRight][goDown - 2][i] = 0;
										point[goRight][goDown - 1][i] = 0;
										point[goRight][goDown][i] = 0;
										
									}
									else{
										
										if(goDown - 1 > fromBelow){
//											special case!
//											if this is true I am just under the point I started
//											so I can´t create a trail for the points because the points are at the 
//											top of the board so the trail would be cost an array out of bounce
//											excaption.
											if(color[i] - 35 < 0){
												
												point[goRight][goDown - 3][i] = 0;
														
												point[goRight][goDown - 2][i] = 0;
												
												point[goRight][goDown - 1][i] = 0;
												
												point[goRight][goDown][i] = 0;
												
												point[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												point[goRight][goDown - 3][i] = 0;
													
												point[goRight][goDown - 2][i] = color[i] - 35;
													
												point[goRight][goDown - 1][i] = color[i] - 35;
													
												point[goRight][goDown][i] = color[i] - 35;
													
												point[goRight][goDown + 1][i] = color[i];
													
											}
											
										}
										else{
										
											if(color[i] - 35 < 0){
												
												point[goRight][goDown][i] = 0;
												
												point[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												point[goRight][goDown][i] = color[i] - 35;
													
												point[goRight][goDown + 1][i] = color[i];
													
											}
										}
									}
								}
							}
						}
						
						controller.setColors(point);
						controller.sleep(pause);
						controller.updateLedStripe();
						
					}
				}
				
				isTrue = false;
				fromBelow = 0;
				
			}
			else{
//			this is for all the other case where fromBelow isn´t at the top of the board.
				for(int goRight=0; goRight<point.length; goRight++){
					
					if((point[goRight][fromBelow][0] == color[0]) && (point[goRight][fromBelow][1] == color[1]) && (point[goRight][fromBelow][2] == color[2])){       
//						same as before!
						isTrue = true;
						
						for(int i = 0; i < color.length; i++){
									
							point[goRight][fromBelow][i] = 0;
							if(fromBelow<19){
								point[goRight][fromBelow + 1][i] = color[i];
							}
								
						}
					}
				}
			
				controller.setColors(point);
				controller.updateLedStripe();
				
				if(isTrue == true){
//					same as before!
					for(int goDown = fromBelow; goDown < point.length; goDown++){
						
						for(int goRight = 0; goRight < point.length; goRight++){
							
							if((point[goRight][goDown][0] == color[0]) && (point[goRight][goDown][1] == color[1]) && (point[goRight][goDown][2] == color[2])){       
								
								for(int i = 0; i < color.length; i++){
									
									if(goDown == point.length - 1){
										
										point[goRight][goDown - 3][i] = 0;
										point[goRight][goDown - 2][i] = 0;
										point[goRight][goDown - 1][i] = 0;
										point[goRight][goDown][i] = 0;
										
									}
									else{
										
										if(goDown - 1 > fromBelow){
											
											if(color[i] - 35 < 0){
												
												point[goRight][goDown - 3][i] = 0;
														
												point[goRight][goDown - 2][i] = 0;
												
												point[goRight][goDown - 1][i] = 0;
												
												point[goRight][goDown][i] = 0;
												
												point[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												point[goRight][goDown - 3][i] = 0;
													
												point[goRight][goDown - 2][i] = color[i] - 35;
													
												point[goRight][goDown - 1][i] = color[i] - 35;
													
												point[goRight][goDown][i] = color[i] - 35;
													
												point[goRight][goDown + 1][i] = color[i];
													
											}
										}
										else{
										
											if(color[i] - 35 < 0){
												
												point[goRight][goDown][i] = 0;
												
												point[goRight][goDown + 1][i] = color[i];
												
											}
											else{
													
												point[goRight][goDown][i] = color[i] - 35;
													
												point[goRight][goDown + 1][i] = color[i];
													
											}
										}
									}
								}
							}
						}
						
						controller.setColors(point);
						controller.sleep(pause);
						controller.updateLedStripe();
						
					}	
				}
				
				isTrue = false;
				
			}
		}
	}

	static void credits(){

		controller.resetColors();
		
		Word cre = new Word("Cre-");
		Word dits = new Word("dits");
		
		Word ja = new Word("Ja-");
		Word scha = new Word("scha");
		Word oli = new Word("Oli-");
		Word ver = new Word("ver");
		Word oleh = new Word("Oleh");
		Word mar = new Word("Mar-");
		Word vin = new Word("vin");
		
		Word idea = new Word("Idea");
		
		Word head = new Word("head");
		Word of = new Word("of");
		Word deve = new Word("deve-");
		Word lop = new Word("lop-");
		Word ment = new Word("ment");

		Word de = new Word("de-");
		Word sign = new Word("sign");
		
		Word crea = new Word("crea-");
		Word tive = new Word("tive");

		Word supper = new Word("super");
		Word visor = new Word("visor");
		
		Word spe = new Word("spe-");
		Word cial = new Word("cial");
		Word thanks = new Word("thanks");
		
		Word big = new Word("big");
		Word help = new Word("Help");
//		Word axel = new Word("Axel");
		Word re = new Word("re");
		Word dac = new Word("dac");
		Word ted = new Word("ted");
		
		Word eme = new Word("Eme-");
		Word rald = new Word("rald");
		
		Word bug = new Word("Bug");
		Word hun = new Word("Hun-");
		Word ter = new Word("ter");
		Word tom = new Word("Tom");
		
		Word nice = new Word("nice");
		Word dudes = new Word("dudes");
		Word pal = new Word("pal");
		Word uno = new Word("uno");
		
		Word space = new Word("");
		Word[] credits = new Word[]{cre, dits, space, space, idea, space, oleh, space, crea, tive, de, sign, space, oli, ver, space,
				head, of, deve, lop, ment, space, ja, scha, space, supper, visor, space, mar, vin, space};
		
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
		
		credits = new Word[]{spe, cial, thanks, space, space, de, sign, help, space, eme, rald, space, bug, hun, ter, space,
							 tom, space, nice, dudes, space, pal, uno, space, big, help, space, re, dac, ted, space};
		
		title = new int[]{125, 125, 118};
		int[] special = new int[]{110, 45, 59};
		int[] emerald = new int[]{32, 122, 4};
		
		colors = new int[][]{special,special,special,black,black,title,title,title,black,emerald,emerald,black,title,title,title,black,
							 person,black,title,title,black,person,person,black,title,title,black,person,person,person,black};
			
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
