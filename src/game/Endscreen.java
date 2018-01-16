package game;
import displayObjects.Word;
import gameObjects.EnemyShip;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ufos.BossaNova;
import ufos.DefaultShip;
import ufos.UnnervingFloatingOctopus;
import ufos.GalaxyDestroyer;
import ufos.LangerLulatsch;
import ufos.BigBoulder;

public abstract class Endscreen extends TrySmth{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR); 
	
	public static void outro(boolean won, int[] shipColor, int difficulty){
		
		if(won) win(shipColor, difficulty);
		else loss(shipColor);
		
	}
	
	private static void win(int[] fireworkColor, int difficulty){
		
        int[][][] point = new int[20][20][3];
		
		int count = 0;
		int left = 8;
		int right = 10;
		int up = 19;

		for(int i=0; i<3; i++){
			
			point[left][up][i] = fireworkColor[i];
			point[right][up][i] = fireworkColor[i];
			
			controller.setColors(point);
			controller.updateLedStripe();
		}
		
		up--;
		left--;
		right++;
				
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

		//For some reason, controller.resetColors() doesn't work here :/
		point = new int[20][20][3];
		controller.setColors(point);
		
		//Fireworks on both sides are done, so now the middle one starts
		int top = 19;
		count = 0;
		int mid = 9;
		
		
		
		int leffi = 8;
		int rightti = 10;
		int leftUppi = 19;
		int rightUppi = 19;
		
		
		while(count < 14){
			
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
				
				if(difficulty == 3){
					if(count > 2 && count < 14){
						
						leftUppi-=1;
						leffi-=1;
						
						rightUppi-=1;
						rightti+=1;
						
					}
				}
		
				for(int i=0; i<3; i++){
			
					point[mid][top][i] = fireworkColor[i];
					point[mid][top + 2][i] = 0;
					
					if(difficulty == 3) {

						point[leffi][leftUppi][i] = fireworkColor[i];
						point[rightti][rightUppi][i] = fireworkColor[i];
						
						if(count > 3 && count < 14){
		
							point[leffi][leftUppi][i] = fireworkColor[i];
							point[rightti][rightUppi][i] = fireworkColor[i];
							
							point[leffi + 1][leftUppi + 1][i] = 0;
							point[rightti - 1][rightUppi + 1][i] = 0;
						
						}
				
					controller.setColors(point);
					controller.updateLedStripe();
				
					}
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
			
			makeBoom(point,fireworkColor,mid,top, 5, difficulty);
			
        }
        else{
        	
        	int a = 0;
			
			for(int j=0; j<5 ; j++){
			
			if(j<4){
				
				if(j%2 ==1) {
					
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
		
		Word you = new Word("You");
		Word won = new Word("won");
		
		you.displayWordAt(5, 4, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
		won.displayWordAt(3, 10, fireworkColor[0], fireworkColor[1], fireworkColor[2]);
		
		controller.updateLedStripe();
		controller.sleep(4000);
		
	}
	
	private static void loss(int[] color){
		
		EnemyShip[] endOfWorld = new EnemyShip[6];
		
		endOfWorld[0] = new DefaultShip(new int[]{3, -4}, 1, 1);
		endOfWorld[1] = new UnnervingFloatingOctopus(new int[]{13, -10}, 1, 3);
		endOfWorld[2] = new LangerLulatsch(new int[]{5, -14}, 1, 2);
		endOfWorld[3] = new BigBoulder(new int[]{0, -20}, 2, 3);
		endOfWorld[4] = new BossaNova(new int[]{10, -25}, 3, 4);
		endOfWorld[5] = new GalaxyDestroyer(new int[]{0, -35}, 250, 5);
		
		for(int i=0; i<endOfWorld.length; i++){
			endOfWorld[i].spawn();
		}
		
		//This variable counts the amount of times the following loop has been started
		int loopCount=0;
		while(endOfWorld[5].getTopLeftCorner()[1]<21){
		
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
			
			//Every 20th instance of the loop, all ships move down by one spot
			if(loopCount%15==1){
				for(int i=0; i<endOfWorld.length; i++){
					endOfWorld[i].move('S');
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
							
							isTrue = true;
							
							for(int i = 0; i < color.length; i++){
										
								point[fromRight][fromBelow][i] = 0;
								point[fromRight][fromBelow + 1][i] = color[i];
									
							}
							
						}
						
					}
				
					controller.setColors(point);
					controller.updateLedStripe();
					
					count++;
					fromBelow++;
				
				}
				
				if(isTrue == true){
					
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
				fromBelow = 0;
				
			}
			else{
			
				for(int goRight=0; goRight<point.length; goRight++){
					
					if((point[goRight][fromBelow][0] == color[0]) && (point[goRight][fromBelow][1] == color[1]) && (point[goRight][fromBelow][2] == color[2])){       
						
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
		
	}
}
