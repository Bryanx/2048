package be.kdg.thegame_2048.models;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.beans.Encoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 17:17
 */
public final class PlayerManager {
    //EIGENSCHAPPEN
    private List<Player> playerList;
    private Player currentPlayer;

    //CONSTRUCTORS
    public PlayerManager() {
        this.playerList = new ArrayList<>();
        loadPlayerData();
    }

    private void loadPlayerData() {
        Path data = Paths.get("playerdata" + File.separator + "data.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data.toFile()));
            String info = reader.readLine();
            while (info != null) {
                String[] splittedData = info.split(":");
                playerList.add(new Player(splittedData[0], Integer.parseInt(splittedData[1])));
                info = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Couldn't find file data.txt, contact support!");
        }
    }

    public void saveData() {
        Path playerdata = Paths.get("playerdata");
        Path data = playerdata.resolve("data.txt");
        try {
            if (!Files.exists(playerdata)) Files.createDirectory(playerdata);
            if (!Files.exists(data)) Files.createFile(data);

            BufferedWriter writer = new BufferedWriter(new FileWriter(data.toFile()));
            String playerInfo = "";
            for (Player player : playerList) {
                playerInfo += player.getName() + ":" + player.getBestScore() + "\n";
            }

            writer.write(playerInfo);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //METHODEN
    public void setCurrentPlayerToNull() {
        this.currentPlayer = null;
    }

    public void setCurrentPlayer(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
                this.currentPlayer = player;
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void addPlayer(String name) {
        playerList.add(new Player(name, 0));
        System.out.println("New player added: " + name);
        for (Player player : playerList) {
            System.out.println(player);
        }
    }

    public boolean checkIfExists(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) return true;
        }
        return false;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Player player : this.playerList) {
            s.append(player.toString() + "\n");
        }
        return s.toString();
    }
}
