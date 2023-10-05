/* Purpose: Create Player class as one of objects for the game.
Each player provided five cards for their hand.
 * Author: Renata Santos, Megumi Kabasawa, Mei Hirata
 * Date: July 30
 * Time: 15:00
 */

import java.util.ArrayList;

public class Player {
    private String name;    
    private ArrayList<Card> hand;    //an instance variable that is an array of Card objects.
    public static String school = "Georgian@ILAC";
    public static String location = "Canada";

    //Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();    //Distribute 4 cards to each players 
    }

    //Getters for name and hand
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    //A method for drawing a new card
    public void drawCard(Card card) {
        hand.add(card);          //Add a card to player's hand by using .add() method of ArrayList class
    }

    //A method to play a card
    public Card playCard(int index) {
        if(index >= 0 && index < hand.size()){
            return hand.remove(index);              //A selected index card will be returned
        }       
        else {
            return null;    //If selected index was invalid, return null.
        }                    
    }


    //A method to get index of card from hand
    public Card getCardIndex(int index){
        return hand.get(index);     //Return a card
    }

    // Overload method for above method
    public Card getCardIndex(String color, String number) {
        for (Card card : hand) {
            if (card.getColor().equals(color) && card.getNumber().equals(number)) {
                return card;
            }
        }
        return null; // If the card is not found in the hand, return null
    }


}
