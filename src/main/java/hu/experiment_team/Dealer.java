package hu.experiment_team;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class is a singleton, this will gives the beer, queen, ace etc..
 * @author Jakab Ádám
 * */
public enum Dealer {
    /**
     * Instance of the singleton class.
     * */
    INSTANCE;

    /**
     * Amount of beer given.
     * */
    private int givenBeer = 0;
    /**
     * Object of the logger.
     * */
    protected static Logger logger = Logger.getLogger(Dealer.class.getName());

    /**
     * Deck as the full list of cards.
     * */
    List<Card> cardList = new ArrayList<Card>(){{
        add( new Card("c", 1));add( new Card("c", 2));add( new Card("c", 3));add( new Card("c", 4));add( new Card("c", 5));add( new Card("c", 6));add( new Card("c", 7));add( new Card("c", 8));add( new Card("c", 9));add( new Card("c", 10));add( new Card("c", 11));add( new Card("c", 12));add( new Card("c", 13));
        add( new Card("d", 1));add( new Card("d", 2));add( new Card("d", 3));add( new Card("d", 4));add( new Card("d", 5));add( new Card("d", 6));add( new Card("d", 7));add( new Card("d", 8));add( new Card("d", 9));add( new Card("d", 10));add( new Card("d", 11));add( new Card("d", 12));add( new Card("d", 13));
        add( new Card("h", 1));add( new Card("h", 2));add( new Card("h", 3));add( new Card("h", 4));add( new Card("h", 5));add( new Card("h", 6));add( new Card("h", 7));add( new Card("h", 8));add( new Card("h", 9));add( new Card("h", 10));add( new Card("h", 11));add( new Card("h", 12));add( new Card("h", 13));
        add( new Card("s", 1));add( new Card("s", 2));add( new Card("s", 3));add( new Card("s", 4));add( new Card("s", 5));add( new Card("s", 6));add( new Card("s", 7));add( new Card("s", 8));add( new Card("s", 9));add( new Card("s", 10));add( new Card("s", 11));add( new Card("s", 12));add( new Card("s", 13));
    }};

    /**
     * Shuffles the deck, and pull a card.
     * @return The pulled card, or null if the deck is empty
     * */
    public Card getRandomCard(){
        logger.entering("Dealer", "getRandomCard");
        Collections.shuffle(cardList);
        if(!cardList.isEmpty()){
            Card card = cardList.remove(0);
            logger.info("cards left: "+cardList.size());
            logger.exiting("Dealer", "getRandomCard", card);
            return card;
        } else {
            logger.exiting("Dealer", "getRandomCard", null);
            return null;
        }
    }

    /**
     * Give the beer to the next player.
     * @param players List of players
     * */
    public void giveBeer(ObservableList<Player> players){
        logger.entering("Dealer", "giveBeer", players);
        players.stream().forEach(s -> s.setMyTurn(null));
        givenBeer %= players.size();
        ImageView beer = new ImageView();
        beer.setImage(new Image("beer-icon.png"));
        players.get(givenBeer).setMyTurn(beer);
        this.givenBeer++;
        logger.exiting("Dealer", "giveBeer");
    }

    /**
     * Get the queen from it's previous holder and give it to the new one.
     * @param players List of players
     * @param card The queen card
     * */
    public void giveQueen(ObservableList<Player> players, Card card){
        logger.entering("Dealer", "giveQueen", new Object[]{players, card});
        players.stream().forEach(s -> s.setHaveQueen(null));
        ImageView queen = new ImageView();
        queen.setImage(new Image("/cards/" + card.toString()));
        players.stream().filter(s -> s.isMyTurn() != null).forEach(s -> s.setHaveQueen(queen));
        logger.exiting("Dealer", "giveQueen");
    }

    /**
     * Get the ace from it's previous holder and give it to the new one.
     * @param players List of players
     * @param card The ace card
     * */
    public void giveAce(ObservableList<Player> players, Card card){
        logger.entering("Dealer", "giveAce", new Object[]{players, card});
        players.stream().forEach(s -> s.setHaveAce(null));
        ImageView ace = new ImageView();
        ace.setImage(new Image("/cards/"+card.toString()));
        players.stream().filter(s -> s.isMyTurn() != null).forEach(s -> s.setHaveAce(ace));
        logger.exiting("Dealer", "giveAce");
    }

    /**
     * Resets the deck, after the cards runs out.
     * */
    public void resetDeck(){
        logger.entering("Dealer", "resetDeck");
        cardList.add( new Card("c", 1));cardList.add( new Card("c", 2));cardList.add( new Card("c", 3));cardList.add( new Card("c", 4));cardList.add( new Card("c", 5));cardList.add( new Card("c", 6));cardList.add( new Card("c", 7));cardList.add( new Card("c", 8));cardList.add( new Card("c", 9));cardList.add( new Card("c", 10));cardList.add( new Card("c", 11));cardList.add( new Card("c", 12));cardList.add( new Card("c", 13));
        cardList.add( new Card("d", 1));cardList.add( new Card("d", 2));cardList.add( new Card("d", 3));cardList.add( new Card("d", 4));cardList.add( new Card("d", 5));cardList.add( new Card("d", 6));cardList.add( new Card("d", 7));cardList.add( new Card("d", 8));cardList.add( new Card("d", 9));cardList.add( new Card("d", 10));cardList.add( new Card("d", 11));cardList.add( new Card("d", 12));cardList.add( new Card("d", 13));
        cardList.add( new Card("h", 1));cardList.add( new Card("h", 2));cardList.add( new Card("h", 3));cardList.add( new Card("h", 4));cardList.add( new Card("h", 5));cardList.add( new Card("h", 6));cardList.add( new Card("h", 7));cardList.add( new Card("h", 8));cardList.add( new Card("h", 9));cardList.add( new Card("h", 10));cardList.add( new Card("h", 11));cardList.add( new Card("h", 12));cardList.add( new Card("h", 13));
        cardList.add( new Card("s", 1));cardList.add( new Card("s", 2));cardList.add( new Card("s", 3));cardList.add( new Card("s", 4));cardList.add( new Card("s", 5));cardList.add( new Card("s", 6));cardList.add( new Card("s", 7));cardList.add( new Card("s", 8));cardList.add( new Card("s", 9));cardList.add( new Card("s", 10));cardList.add(new Card("s", 11));
        cardList.add(new Card("s", 12));
        cardList.add(new Card("s", 13));
        logger.exiting("Dealer", "resetDeck");
    }

    /**
     * Resets the table after the game is over. This will reset the players states, the rules list and the table overall.
     * @param table Object of the players table
     * @param players List of players
     * @param rules List of the rules
     * */
    public void resetTable(javafx.scene.control.TableView<Player> table, ObservableList<Player> players, ObservableList<Rule> rules) {
        logger.entering("Dealer", "resetTable", new Object[]{table, players, rules});
        players.stream().forEach(player -> {
            player.setMyTurn(null);
            player.setHaveQueen(null);
            player.setHaveAce(null);
        });
        rules.removeAll(rules.stream().collect(Collectors.toList()));
        resetDeck();
        table.getColumns().stream().forEach(s -> {
            s.setVisible(false);
            s.setVisible(true);
        });
        givenBeer = 0;
        logger.exiting("Dealer", "resetTable");
    }

}
