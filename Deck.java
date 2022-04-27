import java.util.ArrayList;
import java.util.List;

public class Deck {
    public List<Card> deck = new ArrayList<Card>();

    static final int RANGE = 13;
    //constructor - if the param is true, initialize the deck.
    public Deck(boolean fillDeck){
        if (fillDeck){
            initDeck();
        }
    }
    //Initiates the deck by order of shapes using addAllCardsFromShape function
    private void initDeck() {
        addAllCardsFromShape(Shape.Spades);
        addAllCardsFromShape(Shape.Diamonds);
        addAllCardsFromShape(Shape.Clubs);
        addAllCardsFromShape(Shape.Hearts);
    }

    public List<Card> getDeck() {return this.deck;}

    //add card to the deck
    public void addCard(Card card){
        deck.add(card);
    }

    //remove top card from the deck
    public Card removeTopCard(){
        return deck.remove(deck.size() - 1);
    }

    //check if the deck is empty
    public boolean isEmpty(){
        return deck.isEmpty();
    }

    //shuffle the deck in random order
    public void shuffle(){
        for (int i = 0 ; i < 50 ; i++){
            int index1 = Main.rnd.nextInt(deck.size());
            int index2 = Main.rnd.nextInt(deck.size());
            swap(index1, index2);
        }
    }

    //assisting function to 'shuffle'
    //swap between cards by their index
    private void swap(int index1, int index2) {
        Card tempCard = deck.get(index1);
        deck.set(index1, deck.get(index2));
        deck.set(index2, tempCard);
    }

    /* assisting function to 'initDeck'.
       add cards, which values go from 1 to 13,
       from the given shape to the deck
    */
    private void addAllCardsFromShape(Shape shape){
        for (int i = 1 ; i <= RANGE ; i++){
            Card cardToAdd = new Card(i, shape);
            addCard(cardToAdd);
        }
    }
}
