/* -------------------------------------------
 * Assignment 3: Question 1
 * Written by: Ian Lopez 27296126
 * For Comp 248 Section FF - Fall 2018
 * One and Two Dimensional Arrays
 * The first array will hold 16 team names, and 
 * In this assignment I will ask the user to input string on any country he/she would like to see how they
 * did in the FIFA WORLD CUP. If the user's input (country) is not in the first array then the program
 * will not initiate. 
 * If the user's country is in the last 16 or tournament than the program will generate 4 two-dimensional arrays;
 * The 1st 2d array will hold twenty tournaments, in each index of that array there will be a tournament simulated
 * 2nd, 3rd, and 4th will be 2d string arrays to store the winners of the progressing teams.
 * At the end of each tournament I will attempt to display: 
 * 		- Each matches result, and who won a specific match and overall tournament
 * 		- if a match ends in a tie then there will be a random generator that will choose who proceeds
 * 		- average goals scored in each tournament
 * 		- Display how many games each tournament had when the goals scored were higher than the average per game.
 * System.out.println(Arrays.toString(teams16));
 */
import java.util.Scanner;
import java.util.Random; //random number generator class java has
public class WolrdCupSimulator {

	public static void main(String[] args) {
			Scanner keyboard = new Scanner(System.in);
			Random RandNumber = new Random();
			String[] teams16 = {"Uruguay", "Portugal", "France", "Argentina", "Brazil", "Mexico",
			"Belgium", "Japan", "Spain", "Russia", "Croatia", "Denmark", "Sweden", "Switzerland",
			"Colombia", "England"};
			String UserTeam;
			boolean found = false,theywon=false;
			double score1, score2, TourSum =0.0, TourAvg;
			
				System.out.print("Enter your favorite team: ");
				UserTeam = keyboard.nextLine();
				
			for (String element:teams16){
				if (element.equalsIgnoreCase(UserTeam)){ //equalsIgnoreCase() takes whatever string user inputs and matches it with what its being compared to regardless
					found = true;									// of case sensitivity
				}
			}
			
			if (found == true){
				System.out.println(UserTeam.toUpperCase() +"? , they qualified , let's see how they did in 20 Tournaments");
				
				double[][] Tour20 = new double [20][30]; //2d array of match outcomes
				String [][] quarter20 = new String[20][8];// quarter final 2d string name holder
				String [][] semi20 = new String[20][4]; // same as above but for semis
				String [][] final20 = new String[20][2]; // same as above but for finals
				String[][] winners20 = new String[20][1]; // a 2d array that holds 20 winner team names in a 1d array of string
				
				
				for (int tour = 0; tour < 20; tour++){ //updates tournament	simulated																		
					for(int team=0; team <14 ; team++){	//updates team index (Team who needs a score, "outcome"
						score1 = RandNumber.nextInt(5);
						score2 = RandNumber.nextInt(5);
						while (score1==score2){			//This while loop will be the sudden death simulator, although it
						score1 = RandNumber.nextInt(5);	// it may seem that it favors the second team more, it would kind of reset the game from the beginning							
						}
					Tour20[tour][team*2] = score1;
					Tour20[tour][(team*2) + 1] = score2;	
							}
				}
				for (int tour = 0; tour < 20; tour++){ //updates tournament																			
					for(int team=28; team <29 ; team++){	//updates score, "outcome" in final match up
						score1 = RandNumber.nextInt(5);
						score2 = RandNumber.nextInt(5);
						while (score1==score2){			//This while loop will be the sudden death simulator, although it
						score1 = RandNumber.nextInt(5);	//							
						}
					Tour20[tour][team] = score1;
					Tour20[tour][(team + 1)] = score2;}
				}

				for (int tour = 0; tour < 20; tour++){	//this piece of code examines the result of the numbers generated in
					for(int score = 0; score < 8; score++){		// the 2d double array Tour20[][] and eliminates string of teams to proceed to the next phase of 
						if ( Tour20[tour][2*score] > Tour20[tour][2*score + 1]){ // the tournament (quarter finals)
							quarter20[tour][(2*score)/2] = teams16[2*score];}
						else if(Tour20[tour][2*score + 1] > Tour20[tour][2*score]){
							quarter20[tour][(2*score)/2] = teams16[2*score+1];
						}
					}
				}
				for (int tour = 0; tour < 20; tour++){		//this piece of code examines the result of the numbers generated in
					for(int score = 8; score < 12; score++){	// the 2d double array Tour20[][] and eliminates string of teams to proceed to the next phase of
						if ( Tour20[tour][2*score] > Tour20[tour][2*score + 1]){	// the tournament (semi finals)
							semi20[tour][score-8] = quarter20[tour][(score-8)*2];}
						else if(Tour20[tour][2*score + 1] > Tour20[tour][2*score]){
							semi20[tour][score-8] = quarter20[tour][((score-8)*2)+1];
						}
					}
				}
				for (int tour = 0; tour < 20; tour++){ // same as above, but for the final two teams
					for(int score = 12; score < 14; score++){
						if ( Tour20[tour][2*score] > Tour20[tour][2*score + 1]){
							final20[tour][score-12] = semi20[tour][(score-12)*2];}
						else if(Tour20[tour][2*score + 1] > Tour20[tour][2*score]){
							final20[tour][score-12] = semi20[tour][((score-12)*2)+1];
						}
					}
				}
				for (int tour = 0; tour <20; tour++){ // this fills an array of 20 "winner" strings after all of the previous codes have been executed
					for (int score = 28; score <29; score++){
						if (Tour20[tour][score] > Tour20[tour][score+1]){
							winners20[tour][score-28] = final20[tour][(score-28)*2];
						}
						else if (Tour20[tour][score+1] > Tour20[tour][score]){
							winners20[tour][score-28] = final20[tour][(score-28)*2+1];
						}
					}
				}
				
				
				
				for (int tour = 0; tour <20 && theywon==false; tour++){ //displays the outcomes of each match in a tournament, average, and sees if your
					for(int game =0; game < 30; game++){																		//favorite team won
						TourSum += Tour20[tour][game]; // goal counter per tournament loop
					}
					TourAvg = TourSum / 15;
					System.out.println("");
					System.out.println("Tournament " + (tour+1));
					System.out.println("Round of 16 results:");
					System.out.print("["+ teams16[0] +" " + (int)Tour20[tour][0]+":"+(int)Tour20[tour][1]+ " " +teams16[1] + "]");
					System.out.println("[" + teams16[2] +" " +  (int)Tour20[tour][2]+":"+(int)Tour20[tour][3]+ " " +teams16[3] + "]");
					System.out.print("[" + teams16[4] +" " +  (int)Tour20[tour][4]+":"+(int)Tour20[tour][5]+ " " +teams16[5] + "]");
					System.out.println("[" + teams16[6] +" " +  (int)Tour20[tour][6]+":"+(int)Tour20[tour][7]+ " " +teams16[7] + "]");
					System.out.print("[" + teams16[8] +" " +  (int)Tour20[tour][8]+":"+(int)Tour20[tour][9]+ " " +teams16[9] + "]");
					System.out.println("[" + teams16[10] +" " +  (int)Tour20[tour][10]+":"+(int)Tour20[tour][11]+ " " +teams16[11] + "]");
					System.out.print("[" + teams16[12] +" " +  (int)Tour20[tour][12]+":"+(int)Tour20[tour][13]+ " " +teams16[13] + "]");
					System.out.println("[" + teams16[14] +" " +  (int)Tour20[tour][14]+":"+(int)Tour20[tour][15]+ " " +teams16[15] + "]");
					System.out.println("");
					System.out.println("Quarter-Finals results:");
					System.out.print("[" + quarter20[tour][0]+ " " +(int)Tour20[tour][16]+":"+(int)Tour20[tour][17]+ " "+quarter20[tour][1]+ "]");
					System.out.println("[" + quarter20[tour][2]+ " " +(int)Tour20[tour][18]+":"+(int)Tour20[tour][19]+ " "+quarter20[tour][3]+ "]");
					System.out.print("[" + quarter20[tour][4]+ " " +(int)Tour20[tour][20]+":"+(int)Tour20[tour][21]+ " "+quarter20[tour][5]+ "]");
					System.out.println("[" + quarter20[tour][6]+ " " +(int)Tour20[tour][22]+":"+(int)Tour20[tour][23]+ " "+quarter20[tour][7]+ "]");
					System.out.println("");
					System.out.println("Semi-Finals results:");
					System.out.print("[" + semi20[tour][0] + " "+ (int)Tour20[tour][24]+":"+(int)Tour20[tour][25]+" "+ semi20[tour][1] + "]");
					System.out.println("[" + semi20[tour][2] + " "+ (int)Tour20[tour][26]+":"+(int)Tour20[tour][27]+" "+ semi20[tour][3] + "]");
					System.out.println("");
					System.out.println("Final result:");
					System.out.println("[" +final20[tour][0] + " "+ (int)Tour20[tour][28]+":"+(int)Tour20[tour][29]+" "+ final20[tour][1] +"]");
					System.out.println("");
					System.out.println("Tournament " + (tour+1) + " Winner: " + winners20[tour][0] + "!");
					int game = 0, GamesHigher=0;
					while (game <15){ //counter for how many times a game had higher goals scored than the average of goals/match in tournament
						if ((Tour20[tour][2*game] +Tour20[tour][2*game+1]) > TourAvg){
							GamesHigher++;}
						game++;
					}
				if (winners20[tour][0].equalsIgnoreCase(UserTeam)) //if user team has won this tournament display this and leave program
					{
					System.out.println("It took " + UserTeam +" " + (tour+1) + " tournaments to win");
					
					for (int t20= 0; t20 < (tour+1); t20++){
						System.out.print("[Tournament " + t20+"] [");
						for(int sr=0; sr <14; sr++){
							System.out.print((((int)Tour20[t20][2*sr])+((int)Tour20[t20][2*sr+1]))+ ", ");
						}
						for(int last= 28; last < 29; last++){
							System.out.print((((int)Tour20[t20][last])+((int)Tour20[t20][last+1]))+ "]");
						}
						System.out.println("");
					}
					theywon=true;
					}
				else{ 
					System.out.println("");
					System.out.println("Sorry, but " + UserTeam.toUpperCase() + " didn't win in this/or any tournaments."); //display message that states user team did not win this or 20 simulated tournaments
					for (int t20= 0; t20 < (tour+1); t20++){
						System.out.print("[Tournament " + t20+"] [");
						for(int sr=0; sr <14; sr++){
							System.out.print((((int)Tour20[t20][2*sr])+((int)Tour20[t20][2*sr+1]))+ ", ");
						}
						for(int last= 28; last < 29; last++){
							System.out.print((((int)Tour20[t20][last])+((int)Tour20[t20][last+1]))+ "]");
						}
						System.out.println("");
					}
				}
					System.out.println("Total goals scored in this tournament: "+ (int)TourSum + " goals");
					System.out.printf("Average goals scored per game in this tournament: %.1f", TourAvg , " goals");
					
					System.out.println("");
					System.out.print("Matches when the score was higher than the average: " + (int)GamesHigher);
					System.out.println("");
					TourSum = 0;

				}
								
			}
			else if (found == false){// if user team is not in round of 16, exit program
				System.out.println("Your team is not in the round of 16");
			}
		keyboard.close();		
			}
		}

