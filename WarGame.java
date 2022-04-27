public class WarGame {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Deck firstDeck;
    private Deck secondDeck;

    //constructor - define the players' order by their names
    public WarGame(String firstPlayerName, String secondPlayerName) {
        if (firstPlayerName.compareTo(secondPlayerName) <= 0) {
            this.firstPlayer = new Player(firstPlayerName);
            this.secondPlayer = new Player(secondPlayerName);
        } else {
            this.firstPlayer = new Player(secondPlayerName);
            this.secondPlayer = new Player(firstPlayerName);
        }
        this.firstDeck = new Deck(false);
        this.secondDeck = new Deck(false);
    }

    //get functions to the 2 decks
    public Deck getFirstDeck() {
        return firstDeck;
    }

    public Deck getSecondDeck() {
        return secondDeck;
    }

    //Creating a new Deck of cards and shuffling it
    //Divide the deck one-by-one to the players
    //By giving the first card to the player with the "bigger" name
    public void initializeGame() {
        System.out.println("Initializing the game...");
        Deck warGameDeck = new Deck(true);
        warGameDeck.shuffle();
        while (!warGameDeck.isEmpty()) {
            firstPlayer.addCardPlaying(warGameDeck.removeTopCard());
            secondPlayer.addCardPlaying(warGameDeck.removeTopCard());
        }

    }

    //playing the game
    public String start() {
        this.initializeGame();
        int counter = 1;
        while (true) {
            Card firstCard = drawCardAndAddToDeck(firstPlayer, firstDeck);
            Card secondCard = drawCardAndAddToDeck(secondPlayer, secondDeck);
            if (firstCard == null) {
                return secondPlayer.getName();
            }
            if (secondCard == null) {
                return firstPlayer.getName();
            }
            System.out.println("------------------------- Round number " +
                    counter + " -------------------------");
            System.out.println(firstPlayer.getName() + " drew " + firstCard);
            System.out.println(secondPlayer.getName() + " drew " + secondCard);

            int result = firstCard.compare(secondCard);
            if (result > 0) {
                System.out.println(firstPlayer.getName() + " won");
                addCardsToWinner(firstPlayer);
            } else if (result < 0) {
                System.out.println(secondPlayer.getName() + " won");
                addCardsToWinner(secondPlayer);
            } else {
                String winner = tieWar();
                if (winner != null) {
                    return winner;
                }
            }
            counter++;
        }
    }

    //Assisting function to 'start'
    //Commit the war situation until the tie is broken
    public String tieWar() {
        System.out.println("Starting a war...");
        while (true) {
            Card firstCard = new Card(5, Shape.values()[0]);
            Card secondCard = null;
            for (int i = 0; i < 6; ++i) {
                if (i == 4) {
                    firstCard = drawCardAndAddToDeck(firstPlayer, firstDeck);
                    if (firstCard == null) {
                        return secondPlayer.getName();
                    }
                    System.out.println(firstPlayer.getName() + " drew " + firstCard);
                } else if (i == 5) {
                    secondCard = drawCardAndAddToDeck(secondPlayer, secondDeck);
                    if (secondCard == null) {
                        return firstPlayer.getName();
                    }
                    System.out.println(secondPlayer.getName() + " drew " + secondCard);
                } else if (i % 2 == 0) {
                    if (drawCardAndAddToDeck(firstPlayer, firstDeck) == null) {
                        return secondPlayer.getName();
                    }
                    System.out.println(firstPlayer.getName() + " drew a war card");
                } else {
                    if (drawCardAndAddToDeck(secondPlayer, secondDeck) == null) {
                        return firstPlayer.getName();
                    } else {
                        System.out.println(secondPlayer.getName() + " drew a war card");
                    }
                }
            }
            int result = firstCard.compare(secondCard);
            if (result > 0) {
                System.out.println(firstPlayer.getName() + " won the war");
                addCardsToWinner(firstPlayer);
                break;
            } else if (result < 0) {
                System.out.println(secondPlayer.getName() + " won the war");
                addCardsToWinner(secondPlayer);
                break;
            }
        }
        return null;
    }

    public Card drawCardAndAddToDeck(Player player, Deck deck) {
        Card card = drawCardIfExists(player);
        if (card != null) {
            deck.addCard(card);
        }
        return card;
    }

    private Card drawCardIfExists(Player player) {
        if (!player.getPlayingDeck().isEmpty()) {
            return player.drawCard();
        }
        if (player.getWinDeck().isEmpty()) {
            return null;
        }
        player.copyToPlayingDeckAndShuffle();
        return player.drawCard();
    }

    public void addCardsToWinner(Player winner) {
        while (!firstDeck.isEmpty() || !secondDeck.isEmpty()) {
            if (!secondDeck.isEmpty()) {
                winner.addCardWinning(secondDeck.removeTopCard());
            }
            if (!firstDeck.isEmpty()) {
                winner.addCardWinning(firstDeck.removeTopCard());
            }
        }
    }
}

