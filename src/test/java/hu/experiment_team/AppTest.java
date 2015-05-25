package hu.experiment_team;

import static org.junit.Assert.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Test;
import org.junit.Rule;
public class AppTest {

    @Rule
    public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

    @Test
    public void testCard(){
        Card test = new Card("c", 1);
        assertEquals("1c.gif", test.toString());
        assertTrue(test.getRank()>0);
        assertTrue(test.getImage()!=null);
    }

    @Test
    public void testCardPicker(){
        for(int i = 0; i < 5; i++){
            Dealer.INSTANCE.getRandomCard();
        }
        assertEquals(Dealer.INSTANCE.cardList.size(), 47);
    }

    @Test
    public void testResetDeck(){
        int deckSizeBeforeReset = Dealer.INSTANCE.cardList.size();
        for(int i = 0; i < 52; i++){
            Dealer.INSTANCE.getRandomCard();
        }
        Dealer.INSTANCE.resetDeck();
        int deckSizeAfterReset = Dealer.INSTANCE.cardList.size();
        assertEquals(deckSizeBeforeReset, deckSizeAfterReset);
    }

    @Test
    public void testPlayer(){
        Player test = new Player("Test", null, null, null);
        assertEquals(test.getName(), "Test");

        ImageView queen = new ImageView();
        queen.setImage(new Image("/cards/12d.gif"));
        test.setHaveQueen(queen);

        ImageView ace = new ImageView();
        ace.setImage(new Image("/cards/1d.gif"));
        test.setHaveAce(ace);

        ImageView beer = new ImageView();
        beer.setImage(new Image("beer-icon.png"));
        test.setMyTurn(beer);

        assertTrue(test.getHaveQueen() != null);
        assertTrue(test.getHaveAce() != null);
        assertTrue(test.isMyTurn() != null);
    }

    @Test
    public void testRule(){
        hu.experiment_team.Rule test = new hu.experiment_team.Rule("Test rule");
        assertTrue(test.getRule().length() > 0);
    }

}
