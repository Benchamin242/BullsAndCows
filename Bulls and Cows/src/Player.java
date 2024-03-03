public class Player {
    private final String username;
    private int numberOfBulls;
    private int numberOfCows;
    private int codesAttempted;
    private int codesDeciphered;

    public Player(String username, int numberOfBulls, int numberOfCows, int codesAttempted, int codesDeciphered) {
        this.username = username;
        this.numberOfBulls = numberOfBulls;
        this.numberOfCows = numberOfCows;
        this.codesAttempted = codesAttempted;
        this.codesDeciphered = codesDeciphered;
    }

    public Player(){
        this.username = "No username entered";
        this.numberOfBulls = 0;
        this.numberOfCows = 0;
        this.codesAttempted = 0;
        this.codesDeciphered = 0;
    }

    public void updateBulls(int numberOfBulls) {
        this.numberOfBulls = numberOfBulls;
    }

    public void updateCows(int numberOfCows) {
        this.numberOfCows = numberOfCows;
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
}
