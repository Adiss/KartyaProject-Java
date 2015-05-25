package hu.experiment_team;

import javafx.scene.image.ImageView;

/**
 * This is the model of a Card.
 * @author Jakab Ádám
 * */
public class Card {

    /**
     * Suit of the card.
     * */
    private String suit;
    /**
     * Rank of the card.
     * */
    private int rank;
    /**
     * Image of the card.
     * */
    private ImageView image;

    /**
     * This method constructs the actual card.
     * @param suit Suit of the card (Spades, Hearts, Diamonds, Clubs)
     * @param rank Rank of the card
     * */
    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        image = new ImageView("/cards/"+this.rank+this.suit+".gif");
    }

    /**
     * This method returns the rank of the card.
     * @return Returns the rank of the card
     * */
    public int getRank() {
        return rank;
    }

    /**
     * This method returns the image of the card as ImageView.
     * @return Returns the image of the card
     * */
    public ImageView getImage() {
        return image;
    }

    @Override
    public String toString() {
        return rank+suit+".gif";
    }

}
