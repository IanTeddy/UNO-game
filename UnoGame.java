/* Purpose: Create Uno game class that has method to play game
 * Author: Renata Santos, Megumi Kabasawa, Mei Hirata
 * Date: August 7
 * Time: 14:30
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UnoGame {
    /* Array lists are dynamic and later the size can be increased and decreased if any data is added/removed. */
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;
    private String[] colors = {"Red", "Blue", "Yellow"};
    private String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private Card topCard;
    private int currentPlayerIndex;

    Scanner in = new Scanner(System.in);  

    //Constructor 
    public UnoGame() {
        //Instantiate of the ArrayList class to store players with all the values each of them have.
        players = new ArrayList<>();  
            String[] playerNames = new String[3]; //Create an array to store player names
            //Ask a player name.
            for (int i = 0; i < 3; i++) {
                System.out.print("Please enter the name of Player " + (i + 1) + ": ");
                playerNames[i] = in.nextLine();
                players.add(new Player(playerNames[i]));
                System.out.println("--------------------------");
                System.out.println("Player's Info");
                System.out.println("Name: " + playerNames[i]);
                System.out.println("School: "+ Player.school);  //Call static method
                System.out.println("Area: "+ Player.location);  //Call static method
                System.out.println("--------------------------");
            }
        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
        currentPlayerIndex = 0;

        initializeDeck();
        shuffleDeck();
        setInitialCards();
        placeTopCard();
    }

    //A method for creating cards with all possible combination of color and numbers and add on the deck.
    private void initializeDeck() {
        for (String color : colors) {
            for (String number : numbers) {
                deck.add(new Card(color, number));
            }
        }
    }

     //Use Java Collections class method .shuffle() to shuffle deck arraylist.
    private void shuffleDeck() {
        Collections.shuffle(deck); 
    }

    //A method for setting up initial hand for players
    private void setInitialCards() {
        int initialCard = 4; //The number of initial hand is 4.
        for (Player player : players) {
            for (int i = 0; i < initialCard; i++) {
                Card card = deck.remove(deck.size() - 1);   //Use .size() and .remove() method for ArrayList "deck"
                player.drawCard(card);   //Each player draw five cards as initial set up 
            }
        }
    }

    //A method for placing a top card
    private void placeTopCard() {
        Card card = deck.remove(deck.size() - 1); 
        topCard = card;     //Set "card" as a top card.
        discardPile.add(card);  //Add this card to a discard pile.
    }

    //A method for play game
    public void playGame() {
        boolean gameEnded = false;
        boolean isValidIndex = false;

        while (!gameEnded) { //gameEnded == true

            Player currentPlayer = players.get(currentPlayerIndex); //.get method of ArrayList. Assign a player in which currentPlayerIndex to "currentPlayer"
            
            System.out.println("Current player: " + currentPlayer.getName());
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: ");

            // Loop through the player's hand and print each card
            ArrayList<Card> playerHand = currentPlayer.getHand();
            for (int i = 0; i < playerHand.size(); i++) {
                System.out.println(i + ". " + playerHand.get(i) + "    "); 
            } 

            if(!playerHand.isEmpty()){
                    //Try & catch : Catch user's invalid input not integer.
                    try {
                    
                        System.out.println("==== Inside try ====");
                        while(!isValidIndex){
                            //Ask a player their desire move
                            System.out.print("Enter the index of the card to play or <-1 > to draw a new card.");
                            int selectedIndex = in.nextInt();
                            
                            ArrayList<Card> currentPlayerHands = currentPlayer.getHand();

                            //Switch selectedIndex value
                            switch(selectedIndex){  
                                case -1:
                                    Card drawnCard = deck.remove(deck.size() - 1);
                                    currentPlayer.drawCard(drawnCard);
                                    System.out.println("You drew: " + drawnCard);
                                    System.out.println("--------------------------------------------");
                                    isValidIndex = true;
                                    break;
                                // when user's choice was not -1    
                                default:
                                    if (selectedIndex >= 0 && selectedIndex < currentPlayerHands.size()) {  

                                        Card selectedCard = currentPlayer.getCardIndex(selectedIndex);  //Get information of selected card to check if you can play or not.                       
                                        if(selectedCard.getColor().equals(topCard.getColor()) || selectedCard.getNumber().equals(topCard.getNumber())){
                                            currentPlayer.playCard(selectedIndex);
                                            discardPile.add(selectedCard);
                                            topCard = selectedCard;            

                                            System.out.println("You played: " + selectedCard);
                                            System.out.println("--------------------------------------------");
                                            isValidIndex = true;
                                        } else {
                                            System.out.println("Neither the color nor the number match. Please select again.");
                                        }
                                        
                                        //Check if player has still cards in their hand or not.
                                        if (playerHand.isEmpty()) {    // .isEmpty() retrun boolean value;
                                            gameEnded = true;
                                            System.out.println("Congratulations! " + currentPlayer.getName() + " wins!");
                                        }
                                    } else {
                                        System.out.println("Invalid card index. Try again.");
                                        System.out.println();
                                    }     
                            }   
                        }
                        isValidIndex = false;
                       
                    //Catch exception
                    } catch (Exception e) {
                        System.out.println("--------------------------------------------");            
                        System.out.println("Exception is catched");
                        System.out.println("Invalid input. Please enter a valid integer.");
                        System.out.println("--------------------------------------------");
                                    
                        in.next(); // Consume the invalid input
                        continue;  // Skip the rest of the loop iteration and start over
                    }
                
                // Change player once their turn in over.(1, 2, 0)
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); 
                System.out.println();

            } else {   //If player's hand is empty, game ends and the player will be winner.
                gameEnded = true;
                System.out.println("Congratulations! " + currentPlayer.getName() + " wins!");
            } 
        }
        in.close();
    }
     
}

