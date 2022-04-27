public class Card {
    private int number;
    private Shape shape;

    //the constructor
    public Card (int number,Shape shape){
        this.number = number;
        this.shape = shape;
    }

    //define get methods to the 2 private instances
    public Shape getShape () {
        return this.shape;
    }

    public int getNumber() {
        return this.number;
    }

    /*
    compares between the numeric values of the current card and another card.
    if they're equals - return values is 0.
    if the current is bigger - return value is 1.
    if the current is smaller - return value is -1.
     */
    public int compare (Card other) {
        int otherNumber = other.getNumber();
        if (this.number > otherNumber)
            return 1;
        else if (this.number == otherNumber)
            return 0;
        else
            return -1;

    }

    //redefine the method 'toString' according to the class
    @Override
    public String toString() {

        String printString;
        if (this.number == 1) {
            printString = "Ace of " + this.shape.value;
        }
        else if (this.number == 11) {
            printString = "Jack of " + this.shape.value;
        }
        else if (this.number == 12) {
            printString = "Queen of " + this.shape.value;
        }
        else if (this.number == 13) {
            printString = "King of " + this.shape.value;
        }
        else {
            printString = this.number + " of " + this.shape.value;
        }
        return printString;
    }

}
