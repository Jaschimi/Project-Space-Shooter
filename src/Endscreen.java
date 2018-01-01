import displayObjects.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;

public abstract class Endscreen{

	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR); 
	
	public static void outro(boolean won, int[] color){
		
		if(won){
			
			win(color);
			
		}
		else{
			
			loss(color);
			
		}
		
	}
	
	private static void win(int[] color){        
		
        int[][][] point = new int[20][20][3];
		
		int count = 0;
		int left = 9;
		int right = 10;
		int linksHoch = 19;
		int rechtsHoch = 19;

		for(int i=0; i<3; i++){
			
			point[left][linksHoch][i] = color[i];
			point[right][rechtsHoch][i] = color[i];
			
			controller.setColors(point);
			controller.updateLedStripe();
			
		}
		
		linksHoch--;
		left--;
		
		rechtsHoch--;
		right++;
				
		for(count=0; count<4; count++){
			
			for(int i=0; i<3; i++){
				
				point[left][linksHoch][i] = color[i];
				point[right][rechtsHoch][i] = color[i];
				
				point[left + 1][linksHoch + 1][i] = 0;
				point[right - 1][rechtsHoch + 1][i] = 0;
				
				controller.setColors(point);
			    controller.updateLedStripe();
				
			}
			
			linksHoch--;
			left--;
			
			rechtsHoch--;
			right++;
		}
		
		for(int i=0; i<3; i++){
			
			point[left][linksHoch][i] = color[i];
			point[right][rechtsHoch][i] = color[i];
			
			point[left + 1][linksHoch + 1][i] = 0;
			point[right - 1][rechtsHoch + 1][i] = 0;
			
			controller.setColors(point);
		    controller.updateLedStripe();
			
		}
		
		int speicher = 0;
			
			for(int j=0; j<5; j++){
				
				if(j < 4){
				
				for(int i=0; i<3; i++){
			
				point[left - (j - 1)][linksHoch][i] = 0;
			    point[right - (j - 1)][rechtsHoch][i] = 0;	
			
			    point[left + (j - 1)][linksHoch][i] = 0;
			    point[right + (j - 1)][rechtsHoch][i] = 0;
			
			    point[left][linksHoch - (j - 1)][i] = 0;
			    point[right][rechtsHoch - (j - 1)][i] = 0;
			
			    point[left][linksHoch + (j - 1)][i] = 0;
			    point[right][rechtsHoch + (j - 1)][i] = 0;
			    
//------------------------------------------------------------------------------------
				
		        point[left - j][linksHoch][i] = color[i];
			    point[right - j][rechtsHoch][i] = color[i];	
			
			    point[left + j][linksHoch][i] = color[i];
			    point[right + j][rechtsHoch][i] = color[i];
			
			    point[left][linksHoch - j][i] = color[i];
			    point[right][rechtsHoch - j][i] = color[i];
			
			    point[left][linksHoch + j][i] = color[i];
			    point[right][rechtsHoch + j][i] = color[i];
			    

				
				controller.setColors(point);
			    controller.updateLedStripe();
			    
				}if(j < 3){
			    
			    speicher += j; 
			    
				}else{
					
					
					
				}
				
				}else{
					
					for(int i=0; i<3; i++){
						
						point[left - (speicher )][linksHoch][i] = 0;
					    point[right - (speicher )][rechtsHoch][i] = 0;	
					
					    point[left + (speicher )][linksHoch][i] = 0;
					    point[right + (speicher )][rechtsHoch][i] = 0;
					
					    point[left][linksHoch - (speicher )][i] = 0;
					    point[right][rechtsHoch - (speicher )][i] = 0;
					
					    point[left][linksHoch + (speicher )][i] = 0;
					    point[right][rechtsHoch + (speicher )][i] = 0;
					    

						
						controller.setColors(point);
					    controller.updateLedStripe();
					    
						}
					
				}
			
			}
			
			//Fireworks on both sides are done, so now the middle one starts
			
			int hoch = 19;
			count = 0;
			int mitte = 9;
			
			while(count < 14){
				
				if(count == 0){
					
					for(int i=0; i<3; i++){
						
						point[mitte][hoch][i] = color[i];
						
						controller.setColors(point);
					    controller.updateLedStripe();
						
					}
				    
				    hoch-=2;
				    count++;
				    
				}
				else{
			
					for(int i=0; i<3; i++){
				
					point[mitte][hoch][i] = color[i];
					point[mitte][hoch + 2][i] = 0;
				
					controller.setColors(point);
					controller.updateLedStripe();
				
					}
		    
				hoch-=2;
				count+=2;
			
				}
				
			}
			
            for(int i=0; i<3; i++){
				
				point[mitte][hoch][i] = color[i];
				point[mitte][hoch + 2][i] = 0;
				
				controller.setColors(point);
			    controller.updateLedStripe();
				
			}
			
			for(int j=0; j<5 ; j++){
				
				if(j<4){
				
				for(int i=0; i<3; i++){
					
					point[left - (j - 1)][linksHoch][i] = 0;
				    point[right - (j - 1)][rechtsHoch][i] = 0;	
				
				    point[left + (j - 1)][linksHoch][i] = 0;
				    point[right + (j - 1)][rechtsHoch][i] = 0;
				
				    point[left][linksHoch - (j - 1)][i] = 0;
				    point[right][rechtsHoch - (j - 1)][i] = 0;
				
				    point[left][linksHoch + (j - 1)][i] = 0;
				    point[right][rechtsHoch + (j - 1)][i] = 0;
			
				point[mitte - (j - 1)][hoch][i] = 0;
			    
			    point[mitte + (j - 1)][hoch][i] = 0;
			    
			    point[mitte][hoch - (j - 1)][i] = 0;
			    
			    point[mitte][hoch + (j - 1)][i] = 0;
			    
//------------------------------------------------------------------------------------
				
			    point[left - j][linksHoch][i] = color[i];
			    point[right - j][rechtsHoch][i] = color[i];	
			
			    point[left + j][linksHoch][i] = color[i];
			    point[right + j][rechtsHoch][i] = color[i];
			
			    point[left][linksHoch - j][i] = color[i];
			    point[right][rechtsHoch - j][i] = color[i];
			
			    point[left][linksHoch + j][i] = color[i];
			    point[right][rechtsHoch + j][i] = color[i];
			    
		        point[mitte - j][hoch][i] = color[i];
			    
			    point[mitte + j][hoch][i] = color[i];
			    
			    point[mitte][hoch - j][i] = color[i];
			    
			    point[mitte][hoch + j][i] = color[i];
			    
			    controller.setColors(point);
			    controller.updateLedStripe();
			    
				}
			    
				}else{
					
					for(int i=0; i<3; i++){
						
						point[mitte - (j - 1)][hoch][i] = 0;
					    
					    point[mitte + (j - 1)][hoch][i] = 0;
					    
					    point[mitte][hoch - (j - 1)][i] = 0;
					    
					    point[mitte][hoch + (j - 1)][i] = 0;
					    
					    point[left - (speicher - 1)][linksHoch][i] = 0;
					    point[right - (speicher - 1)][rechtsHoch][i] = 0;	
					
					    point[left + (speicher - 1)][linksHoch][i] = 0;
					    point[right + (speicher - 1)][rechtsHoch][i] = 0;
					
					    point[left][linksHoch - (speicher - 1)][i] = 0;
					    point[right][rechtsHoch - (speicher - 1)][i] = 0;
					
					    point[left][linksHoch + (speicher - 1)][i] = 0;
					    point[right][rechtsHoch + (speicher - 1)][i] = 0;
					    
					    controller.setColors(point);
					    controller.updateLedStripe();
					
				}
			
			}
			//Now that all fireworks are done, the phrase "You won" will be displayed on the board
			}
			
			controller.resetColors();
			
			Word you = new Word("You");
			Word won = new Word("won");
			
			you.displayWord(5, 4, color[0], color[1], color[2]);
			won.displayWord(3, 10, color[0], color[1], color[2]);
			
			controller.updateLedStripe();
			controller.sleep(4000);
//			blinkWinner(color, point);
		
	}
	
//	private static void blinkWinner(int[] color, int[][][] point){
//		
//		BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
//		
////		schreibe das W
//		
//		point = new int[20][20][3];
//		
//		controller.setColors(generiereEinW(3,9,point,color));
//        
////        Nun zum O
//        
//       controller.setColors(generiereEinO(9,9,point,color));
//       
////       Nun fehlt nur noch das N
//       
//       controller.setColors(generiereEinN(13,9,point,color));
//       
//       controller.setColors(generiereEinO(9,3,point,color));
//       
//       controller.setColors(generiereEinY(3,3,point,color));
//       
//       controller.setColors(generiereEinU(13,3,point,color));
//       
//       controller.setColors(point);
//       
//       controller.sleep(40);
//       
//       controller.updateLedStripe();
//       
//       for(int i=0; i<point.length; i++){
//    	   
//    	   for(int j=0; j<point.length; j++){
//    		   
//    		   for(int coloring=0; coloring<3; coloring++){
//    			   
//    			   point[j][i][coloring] = 0;
//    			   
//    		   }
//    		   
//    	   }
//    	   
//       }
//       
//       controller.setColors(point);
//       
//       controller.sleep(1000);
//       
//       controller.updateLedStripe();
//		
//	}
//	
//	private static int[][][] generiereEinW(int x, int y , int[][][] point, int[] color) {
//		
//         for(int i = x; i < x + 5; i++){
//			
//			for(int j = y; j < y + 5; j++){
//				
//				for(int coloring = 0; coloring < 3; coloring++){
//					
//					point[i][j][coloring] = color[coloring];
//					
//				}
//				
//			}
//			
//		}
//         
//        int zaehlt = x + 1;
//        
//        while(zaehlt < x + 4){
//        	
//        	if(zaehlt == x + 1 || zaehlt == x + 3){
//        		
//            for(int j = y; j < y + 5; j++){
//            	
//            	if(j == y + 4){
//            		
//            		
//            		
//            	}else{
//     
//                for(int coloring = 0; coloring < 3; coloring++){
//        				
//        				point[zaehlt][j][coloring] = 0;
//        				
//        			}
//        			
//            	}
//        			
//        		}
//        		
//        	}else{
//        		
//        		for(int j = y; j < y + 5; j++){
//        			
//        			if(j == y + 3){
//        				
//        				
//        				
//        			}else{
//        				
//                    for(int coloring = 0; coloring < 3; coloring++){
//            				
//            				point[zaehlt][j][coloring] = 0;
//            				
//            			}
//        				
//        			}
//        			
//        		}
//        		
//        	}
//        	
//        	zaehlt++;
//        	
//        }
//		
//		return point;
//		
//	}
//
//	private static int[][][] generiereEinN(int x, int y , int[][][] point, int[] color){
//		
//		for(int i = x; i < x + 5; i++){
//			
//			for(int j = y; j < y + 5; j++){
//				
//				for(int coloring = 0; coloring < 3; coloring++){
//					
//					point[i][j][coloring] = color[coloring];
//					
//				}
//				
//			}
//			
//		}
//		
//		int festRechts = x + 1;
//		
//		int festRunter = y + 1;
//		
//		while(festRunter < y + 4 && festRechts < x + 4){
//			
//			
//				for(int j = y; j < y + 5; j++){
//					
//					
//					
//					if(j == festRunter){
//						
//						
//						
//					}else{
//						
//						
//					
//					for(int i = 0; i < 3; i++){
//						
//						point[festRechts][j][i] = 0;
//						
//					}
//					
//					
//					}
//					
//					
//				}
//				
//				festRunter++;
//				festRechts++;
//				
//			}
//		
//		
//		return point;
//		
//	}
//
//	private static int[][][] generiereEinO(int x, int y, int[][][] point, int[] color){
//		
////		Vollende diesen Prototyp von Methode zur fertigen Methode die ein O ausgibt, das hier ist der Code von Oben
//		
//		for(int i = x; i < x + 3; i++){
//			
//			for(int j = y; j < y + 5; j++){
//				
//				for(int coloring = 0; coloring < 3; coloring++){
//					
//					point[i][j][coloring] = color[coloring];
//					
//				}
//				
//			}
//			
//		}
//			
//				for(int i = x; i < x + 3; i++){
//					
//					for(int j = y; j < y + 5; j++){
//						
//						if(i == x + 1){
//							
//							if(j == y || j == y + 4){
//								
//								
//						        
//							}else{
//						        	
//								for(int coloring = 0; coloring < 3; coloring++){
//									
//								    point[i][j][coloring] = 0;
//								
//							        }
//						        
//							}
//						
//						}else{
//							
//							if(j == y + 1 || j == y + 2 || j == y + 3){
//								
//								
//							
//							}else{
//								
//								for(int coloring = 0; coloring < 3; coloring++){
//									
//									point[i][j][coloring] = 0;
//									
//								}
//								
//							}
//							
//						}
//						
//					}
//					
//				}
//		
//		return point;
//		
//	}
//	
//	private static int[][][] generiereEinY(int x, int y, int[][][] point, int[] color){
//		
//        for(int i = x; i < x + 3; i++){
//			
//			for(int j = y; j < y + 5; j++){
//				
//				for(int coloring = 0; coloring < 3; coloring++){
//					
//					point[i][j][coloring] = color[coloring];
//					
//				}
//				
//			}
//			
//		}
//        
//        int zaehltRueckwaerts = y + 4;
//        
//        int zaehltVorwaerts = y;
//        
//        int zaehlt = x;
//        
//        while(zaehlt < x + 2){
//        	
//        	for(int j = y; j < y + 5; j++){
//        		
//        		if(j == zaehltRueckwaerts || j == zaehltVorwaerts){
//        			
//        			
//        			
//        		}else{
//        			
//        			for(int coloring = 0; coloring < 3; coloring++){
//        				
//        				point[zaehlt][j][coloring] = 0;
//        				
//        			}
//        			
//        		}
//        		
//        	}
//        	
//        	zaehltRueckwaerts--;
//        	zaehltVorwaerts++;
//        	zaehlt++;
//        	
//        }
//        
//        while(zaehlt < x + 5){
//        	
//        	for(int j = y; j < y + 5; j++){
//        		
//        		if(j == zaehltRueckwaerts){
//        			
//                for(int i = 0; i < 3; i++){
//        				
//        				point[zaehlt][j][i] = color[i];
//        				
//        			}
//        			
//        		}else{
//        			
//        			for(int i = 0; i < 3; i++){
//        				
//        				point[zaehlt][j][i] = 0;
//        				
//        			}
//        			
//        		}
//        		
//        	}
//        	
//        	zaehltRueckwaerts--;
//        	zaehlt++;
//        	
//        }
//		
//		return point;
//		
//	}
//	
//	private static int[][][] generiereEinU(int x, int y, int[][][] point, int[] color){
//		
//       for(int i = x; i < x + 4; i++){
//			
//			for(int j = y; j < y + 5; j++){
//				
//				for(int coloring = 0; coloring < 3; coloring++){
//					
//					point[i][j][coloring] = color[coloring];
//					
//				}
//				
//			}
//			
//		}
//       
//       int zaehlt = 0;
//       
//       int nachRechts = x + 1;
//       
//       while(zaehlt < 2){
//    	   
//    	   for(int j = y; j < y + 4; j++){
//    		   
//    		   for(int coloring = 0; coloring < 3; coloring++){
//    		   
//    		   point[nachRechts][j][coloring] = 0;
//    				   
//    		   }
//    		   
//    	   }
//    	   
//    	   nachRechts++;
//    	   zaehlt++;
//    	   
//       }
//       
//       zaehlt = 0;
//       
//       nachRechts = x;
//       
//       while(zaehlt < 2){
//    	   
//    	   for(int j = y; j < y + 5; j++){
//    		   
//    		   if(j == y + 4){
//    			   
//    			   for(int i = 0; i < 3; i++){
//    				   
//    				   point[nachRechts][j][i] = 0;
//    				   
//    			   }
//    			   
//    		   }
//    		   
//    	   }
//    	   
//    	   nachRechts+=3;
//    	   zaehlt++;
//    	   
//       }
//		
//		return point;
//		
//	}
	
	private static void loss(int[] color){
		
//		Es wäre gut falls du das ersetzen könntest mit den Methoden der Letter Klasse
//		Jascha :)
		
//        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
//		
//		int[][][] point = new int[20][20][3];
//		
//		controller.setColors(generiereEinL(1, 7,point,color));
//		
//		controller.updateLedStripe();
//		
//		controller.setColors(generiereEinY(2, 0,point,color));
//		
//		controller.setColors(generiereEinO(8, 0,point,color));
//		
//		controller.setColors(generiereEinU(12, 0,point,color));
//		
//		controller.setColors(generiereEinO(5, 7,point,color));
//		
//		controller.setColors(generiereEinS(9, 7,point,color));
//		
//		controller.setColors(generiereEinT(14, 7,point,color));
//		
//		controller.updateLedStripe();
//		
//		fallenLassen(point,color);
	
	}
	
	public static void fallenLassen(int[][][] point, int[] color){
    	
	    int[][][] copie = new int[20][20][3];
	    
	    for(int i = 0; i < point.length; i++){
	    	
	    	for(int j = 0; j < point[i].length; j++){
	    		
	    		for(int coloring = 0; coloring < color.length; coloring++){
	    			
	    			copie[i][j][coloring] = point[i][j][coloring];
	    			
	    		}
	    		
	    	}
	    	
	    }
	
	boolean isWahr = false;
	
	BoardController controller = BoardController.getBoardController();
	
	controller.sleep(700);
	controller.setColors(point);
	controller.sleep(50);
	
	for(int vonUnten = 19; vonUnten >= 0; vonUnten--){
		
		if(vonUnten == 0){
			
			int zaehler = 0;
			
			while(zaehler < 3){
			
			
			for(int geheRechts = 0; geheRechts < point.length; geheRechts++){
				
				if((point[geheRechts][vonUnten][0] == color[0]) && (point[geheRechts][vonUnten][1] == color[1]) && (point[geheRechts][vonUnten][2] == color[2])){       
					
					isWahr = true;
					
					for(int i = 0; i < color.length; i++){
								
								point[geheRechts][vonUnten][i] = 0;
								
								point[geheRechts][vonUnten + 1][i] = color[i];
							
					}
					
				}else{
					
					continue;
					
				}
				
			}
			
			controller.setColors(point);
			controller.updateLedStripe();
			
			zaehler++;
			vonUnten++;
			
			}
			
			if(isWahr == true){
				
				for(int nachUnten = vonUnten; nachUnten < point.length; nachUnten++){
					
					for(int nachRechts = 0; nachRechts < point.length; nachRechts++){
						
						if((point[nachRechts][nachUnten][0] == color[0]) && (point[nachRechts][nachUnten][1] == color[1]) && (point[nachRechts][nachUnten][2] == color[2])){       
							
							for(int i = 0; i < color.length; i++){
								
								if(nachUnten == point.length - 1){
									
									point[nachRechts][nachUnten - 3][i] = 0;
									point[nachRechts][nachUnten - 2][i] = 0;
									point[nachRechts][nachUnten - 1][i] = 0;
									point[nachRechts][nachUnten][i] = 0;
									
								}else{
									
									if(nachUnten - 1 > vonUnten){
										
										if(color[i] - 35 < 0){
											
											point[nachRechts][nachUnten - 3][i] = 0;
													
											point[nachRechts][nachUnten - 2][i] = 0;
											
											point[nachRechts][nachUnten - 1][i] = 0;
											
											point[nachRechts][nachUnten][i] = 0;
											
											point[nachRechts][nachUnten + 1][i] = color[i];
											
											}else{
												
												point[nachRechts][nachUnten - 3][i] = 0;
												
												point[nachRechts][nachUnten - 2][i] = color[i] - 35;
												
												point[nachRechts][nachUnten - 1][i] = color[i] - 35;
												
												point[nachRechts][nachUnten][i] = color[i] - 35;
												
												point[nachRechts][nachUnten + 1][i] = color[i];
												
											}
										
									}else{
									
									if(color[i] - 35 < 0){
										
										point[nachRechts][nachUnten][i] = 0;
										
										point[nachRechts][nachUnten + 1][i] = color[i];
										
										}else{
											
											point[nachRechts][nachUnten][i] = color[i] - 35;
											
											point[nachRechts][nachUnten + 1][i] = color[i];
											
										}
							    
								}
									
								}
									
							}
							
						}else{
							
							continue;
							
						}
						
					}
					
					controller.setColors(point);
					controller.sleep(50);
					controller.updateLedStripe();
					
				}
				
			}else{
				
			}
			
			isWahr = false;
			
			vonUnten = 0;
			
		}else{
		
		for(int geheRechts = 0; geheRechts < point.length; geheRechts++){
			
			if((point[geheRechts][vonUnten][0] == color[0]) && (point[geheRechts][vonUnten][1] == color[1]) && (point[geheRechts][vonUnten][2] == color[2])){       
				
				isWahr = true;
				
				for(int i = 0; i < color.length; i++){
							
							point[geheRechts][vonUnten][i] = 0;
							
							point[geheRechts][vonUnten + 1][i] = color[i];
						
				}
				
			}else{
				
				continue;
				
			}
			
		}
		
		controller.setColors(point);
		controller.updateLedStripe();
		
		if(isWahr == true){
			
			for(int nachUnten = vonUnten; nachUnten < point.length; nachUnten++){
				
				for(int nachRechts = 0; nachRechts < point.length; nachRechts++){
					
					if((point[nachRechts][nachUnten][0] == color[0]) && (point[nachRechts][nachUnten][1] == color[1]) && (point[nachRechts][nachUnten][2] == color[2])){       
						
						for(int i = 0; i < color.length; i++){
							
							if(nachUnten == point.length - 1){
								
								point[nachRechts][nachUnten - 3][i] = 0;
								point[nachRechts][nachUnten - 2][i] = 0;
								point[nachRechts][nachUnten - 1][i] = 0;
								point[nachRechts][nachUnten][i] = 0;
								
							}else{
								
								if(nachUnten - 1 > vonUnten){
									
									if(color[i] - 35 < 0){
										
										point[nachRechts][nachUnten - 3][i] = 0;
												
										point[nachRechts][nachUnten - 2][i] = 0;
										
										point[nachRechts][nachUnten - 1][i] = 0;
										
										point[nachRechts][nachUnten][i] = 0;
										
										point[nachRechts][nachUnten + 1][i] = color[i];
										
										}else{
											
											point[nachRechts][nachUnten - 3][i] = 0;
											
											point[nachRechts][nachUnten - 2][i] = color[i] - 35;
											
											point[nachRechts][nachUnten - 1][i] = color[i] - 35;
											
											point[nachRechts][nachUnten][i] = color[i] - 35;
											
											point[nachRechts][nachUnten + 1][i] = color[i];
											
										}
									
								}else{
								
								if(color[i] - 35 < 0){
									
									point[nachRechts][nachUnten][i] = 0;
									
									point[nachRechts][nachUnten + 1][i] = color[i];
									
									}else{
										
										point[nachRechts][nachUnten][i] = color[i] - 35;
										
										point[nachRechts][nachUnten + 1][i] = color[i];
										
									}
						    
							}
								
							}
								
						}
						
					}else{
						
						continue;
						
					}
					
				}
				
				controller.setColors(point);
				controller.sleep(50);
				controller.updateLedStripe();
				
			}
			
		}else{
			
			
			
		}
		
		isWahr = false;
		
	}
		
	}
	
	fallenLassen(copie,color);
	
}

}
