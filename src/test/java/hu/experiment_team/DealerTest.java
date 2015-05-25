package hu.experiment_team;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
public class DealerTest {

    @Rule
    public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

    @Test
    public void testCard(){
        Card test = new Card("c", 1);
        assertEquals("1c.gif", test.toString());
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

}
