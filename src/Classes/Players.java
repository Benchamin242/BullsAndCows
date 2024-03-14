package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Players {
  private ArrayList<Player> allPlayers;

  public Players(String filepath) {
    allPlayers = loadPlayers(filepath);
  }
  public void addPlayer(Player p) {
    allPlayers.add(p);
  }

  public void savePlayers() {
    try {
      FileWriter writer = new FileWriter("src/players.txt");
      for(int i = 0; i < allPlayers.size(); i++) {
        writer.write(allPlayers.get(i).toString() + (i == allPlayers.size() - 1 ? "" : "\n")); }
      System.out.println("file wrote successfully");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static ArrayList<Player> loadPlayers(String filepath) {
    ArrayList<Player> allReturn = new ArrayList<>();
    try {
      Scanner sc = new Scanner(new File(filepath));
      while(sc.hasNext()) {
        // ------------------->  name      bulls        cows         CD           CA
        allReturn.add(new Player(sc.next(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()));
      }
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return allReturn;
  }

  public Player getPlayer(String name) {
    for(Player p : allPlayers) {
      if(p.getUsername().equals(name)) return p;
    }
    return null;
  }

  public ArrayList<Integer> getAllPlayersBulls() {
    ArrayList<Integer> rList = new ArrayList<>();
    for(Player p : allPlayers) {
      rList.add(p.getBulls());
    }
    return rList;
  }

  public ArrayList<Integer> getAllPlayersCows() {
    ArrayList<Integer> rList = new ArrayList<>();
    for(Player p : allPlayers) {
      rList.add(p.getCows());
    }
    return rList;
  }

  public ArrayList<Integer> getAllPlayersCodesAttempted() {
    ArrayList<Integer> rList = new ArrayList<>();
    for(Player p : allPlayers) {
      rList.add(p.getCodesAttempted());
    }
    return rList;
  }

  public ArrayList<Integer> getAllPlayersCodesDeciphered() {
    ArrayList<Integer> rList = new ArrayList<>();
    for(Player p : allPlayers) {
      rList.add(p.getCodesDeciphered());
    }
    return rList;
  }
}