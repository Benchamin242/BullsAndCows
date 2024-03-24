package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Players {

  private ArrayList<Player> allPlayers;
  public Players(Player player) {
    allPlayers = new ArrayList<>();
    allPlayers.add(player);
  }
  public Players(Path filepath) {
    allPlayers = loadPlayers(filepath);
  }
  public void addPlayer(Player p) {
    allPlayers.add(p);
  }

  public void savePlayers(Path filePath) {
    try (FileWriter writer = new FileWriter(filePath.toString())){
      Collections.sort(allPlayers);
      for(int i = 0; i < allPlayers.size(); i++) {
        writer.write(allPlayers.get(i).toString() + (i == allPlayers.size() - 1 ? "" : System.lineSeparator())); }
      System.out.println("Players saved!");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static ArrayList<Player> loadPlayers(Path filepath) {
    ArrayList<Player> allReturn = new ArrayList<>();
    try (Scanner sc = new Scanner(new File(filepath.toString()))){
      while(sc.hasNext()) {
        String line = sc.nextLine();
        String[] split = line.split("\\|");
        // ------------------->  name      bulls        cows         CD           CA
        allReturn.add(new Player(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2]),Integer.parseInt(split[4]),Integer.parseInt(split[3]),Integer.parseInt(split[5])));
      }
    } catch (FileNotFoundException e) {
      System.out.println("No players loaded");
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

  public ArrayList<Player> getAllPlayers() {
    return allPlayers;
  }

  // implement displayTopPlayers, which displays the top 10 players based on the number of codes deciphered
  public void displayTop10() {
    Collections.sort(allPlayers);

    System.out.println("Top 10 players based on the number of codes deciphered:");
    for (int i = 0; i < Math.min(10, allPlayers.size()); i++) {
      System.out.println((i + 1) + ". " + allPlayers.get(i).getUsername() + " - " + allPlayers.get(i).getCodesDeciphered());
    }
  }
}

