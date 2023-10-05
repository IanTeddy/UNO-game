/* Purpose: Create Card class as one of objects for the game
 * Author: Renata Santos, Megumi Kabasawa, Mei Hirata
 * Date: July 29
 * Time: 12:40
 */

public class Card {
    private String color;   //Color of cards
    private String number;   //Number of cards

    //Constructor
    public Card(String color, String number) {
        this.color = color;
        this.number = number;
    }

    //Getters for each instance variables
    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    //Create a one card with two values(color and number) 
    public String toString() {
        return color + " " + number;
    }
}

