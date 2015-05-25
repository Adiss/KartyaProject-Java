/**
 * Created by Jakab on 2015.04.27..
 */
package hu.experiment_team;

import javafx.scene.image.ImageView;

/**
 * This model will contains the player.
 * @author Jakab Ádám
 * */
public class Player {

    /**
     * Name of the actual player.
     * */
    private String name;
    /**
     * Image of the beer.
     * */
    private ImageView myTurn;
    /**
     * Image of the specific queen card.
     * */
    private ImageView haveQueen;
    /**
     * Image of the specific ace card.
     * */
    private ImageView haveAce;

    /**
     * Constructs the model of a player.
     * @param name The player's name as String.
     * @param myTurn Image of the beer if this is my turn.
     * @param haveQueen Image of the specific queen card if I have it.
     * @param haveAce Image of the specific ace card if I have it.
     * */
    public Player(String name, ImageView myTurn, ImageView haveQueen, ImageView haveAce) {
        this.name = name;
        this.myTurn = myTurn;
        this.haveQueen = haveQueen;
        this.haveAce = haveAce;
    }

    /**
     * Returns the name of the player.
     * @return String players name
     * */
    public String getName() {
        return name;
    }

    /**
     * Returns the image of the holded queen.
     * @return Imageview image of the holded queen
     * */
    public ImageView getHaveQueen() {
        return haveQueen;
    }

    /**
     * Returns theimage of the holded ace.
     * @return Imageview image of the holded ace
     * */
    public ImageView getHaveAce() {
        return haveAce;
    }

    /**
     * Returns the value of the <code>mMyTurn</code>.
     * @return Imageview if this is my turn, else null
     * */
    public ImageView isMyTurn() {
        return myTurn;
    }

    /**
     * Updates the <code>myTurn</code>, when a card picked, passes you the beer.
     * @param myTurn Path to the specific cards file
     * */
    public void setMyTurn(ImageView myTurn) {
        this.myTurn = myTurn;
    }

    /**
     * Updates the <code>haveQueen</code>, when a Queen picked, passes you the queen.
     * @param haveQueen Path to the specific cards file
     * */
    public void setHaveQueen(ImageView haveQueen) {
        this.haveQueen = haveQueen;
    }

    /**
     * Updates the <code>haveAce</code>, when an Ace picked, passes you the ace.
     * @param haveAce Path to the specific cards file
     * */
    public void setHaveAce(ImageView haveAce) {
        this.haveAce = haveAce;
    }

}
