package Classes;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Game game = new Game(player,"Letters");
        //Game game = new Classes.Game(player);
        while (true){
            game.PlayGame();
        }
    }
}
