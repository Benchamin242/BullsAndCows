package Classes;

public class Player implements Comparable<Player> {
    private final String username;
    private int numberOfBulls;
    private int numberOfCows;
    private int codesAttempted;
    private int codesDeciphered;
    private int numberOfGuesses;

    public Player(String username, int numberOfBulls, int numberOfCows, int codesAttempted, int codesDeciphered, int numberOfGuesses) {
        this.username = username;
        this.numberOfBulls = numberOfBulls;
        this.numberOfCows = numberOfCows;
        this.codesAttempted = codesAttempted;
        this.codesDeciphered = codesDeciphered;
        this.numberOfGuesses = numberOfGuesses;
    }

    public Player(){
        this("No username entered",0,0,0,0,0);
    }

    public void updateBulls(int numberOfBulls) {
        this.numberOfBulls += numberOfBulls;
    }

    public void updateCows(int numberOfCows) {
        this.numberOfCows += numberOfCows;
    }

    public void incrementCodesAttempted() {
        this.codesAttempted++;
    }

    public void incrementCodesDeciphered() {
        this.codesDeciphered++;
    }

    public String getUsername() {
        return username;
    }

    public int getBulls() {
        return numberOfBulls;
    }

    public int getCows() {
        return numberOfCows;
    }

    public int getCodesAttempted() {
        return codesAttempted;
    }

    public int getCodesDeciphered() {
        return codesDeciphered;
    }
    public void incrementNumberOfGuesses() {
        this.numberOfGuesses += 1;
    }
    @Override
    public String toString(){
        // ------------------->             name      bulls        cows         CD           CA                 NG
        return "%s|%d|%d|%d|%d|%d".formatted(username,numberOfBulls,numberOfCows,codesDeciphered,codesAttempted,numberOfGuesses);
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(otherPlayer.getCodesDeciphered(), getCodesDeciphered());
    }

}
