import java.util.ArrayList;

public class Player {
    final private String name;
    private Deck winDeck;
    private Deck playingDeck;

    //constructor
    public Player (String name){
        this.name = name;
        this.winDeck = new Deck(false);
        this.playingDeck = new Deck(false);
    }

    //'get' functions to the private attributes
    public String getName () { return this.name;}
    public Deck getWinDeck() { return this.winDeck;}
    public Deck getPlayingDeck() { return this.playingDeck;}

    public void setPlayingDeck(Deck deck){
        this.playingDeck = deck;
    }

    //add card to each deck
    public void addCardWinning (Card card) {
        this.winDeck.addCard(card);
    }
    public void addCardPlaying (Card card) {
        playingDeck.addCard(card);
    }

    //
    public Card drawCard () {
        return this.playingDeck.removeTopCard();
    }

    //check if the player is out of cards
    public boolean outOfCards (){
        if (this.winDeck.isEmpty() && this.playingDeck.isEmpty()) {
            return true;
        }
        else { return false; }
    }

    //overriding the 'toString' function
    @Override
    public String toString () {
        return this.name;
    }

    public void clearDeck(Deck deck){
        deck.getDeck().clear();
    }

    public void copyToPlayingDeckAndShuffle(){
        this.winDeck.shuffle();
        playingDeck.getDeck().addAll(winDeck.getDeck());
        clearDeck(this.winDeck);
    }
}
