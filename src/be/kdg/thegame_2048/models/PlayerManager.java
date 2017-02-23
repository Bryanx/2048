package be.kdg.thegame_2048.models;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.beans.Encoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 17:17
 */
public final class PlayerManager {
    //EIGENSCHAPPEN
    private List<Player> playerList;
    private Player currentPlayer;
    private int currentPlayerScore;

    //CONSTRUCTORS
    public PlayerManager() {
        this.playerList = new ArrayList<>();
        loadPlayerData();
    }

    public void saveInfoCurrentPlayer() {
        int bestScore = this.currentPlayer.getBestScore();

        if (currentPlayerScore > bestScore) {
            this.currentPlayer.setBestScore(currentPlayerScore);
        }
    }

    /**Decription was used**/
    private void loadPlayerData() {
        Path data = Paths.get("playerdata" + File.separator + "data.txt");
        Path decoderData = Paths.get("playerdata" + File.separator + "encripted.txt");
        try {
            int decodeNumber = Integer.parseInt(new Scanner(decoderData).nextLine());

            BufferedReader reader = new BufferedReader(new FileReader(data.toFile()));
            String info = reader.readLine();
            if (info == null) return;
            while (info != null) {
                String[] splittedData = info.split(":");

                //DECRYPT THE PLAYER DATA
                String decodedName = "";
                String decodedScore = "";
                for (int i = 0; i < splittedData[0].length(); i++) {
                    char decodedLetter = ((char) (splittedData[0].charAt(i) - decodeNumber));
                    decodedName = decodedName + String.valueOf(decodedLetter);
                }
                for (int i = 0; i < splittedData[1].length(); i++) {
                    char decodedNumber = ((char) (splittedData[1].charAt(i) - decodeNumber));
                    decodedScore = decodedScore + decodedNumber;
                }
                playerList.add(new Player(decodedName, Integer.parseInt(decodedScore)));
                info = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Something went wrong, contact support!");
        }
    }

    /**Incription was used**/
    public void savePlayerData() {
        Path playerdata = Paths.get("playerdata");
        Path data = playerdata.resolve("data.txt");
        Path encription = playerdata.resolve("encripted.txt");
        try {
            if (!Files.exists(playerdata)) Files.createDirectory(playerdata);
            if (!Files.exists(data)) Files.createFile(data);
            if (!Files.exists(encription)) Files.createFile(encription);

            BufferedWriter writer = new BufferedWriter(new FileWriter(data.toFile()));
            String playerInfo = "";
            Random random = new Random();
            int randomEncriptionCode = random.nextInt(100) + 10;

            //ENCRYPTING THE PLAYER DATA
            for (Player player : playerList) {
                String encodedName = "";
                String encodedScore = "";
                for (int i = 0; i < player.getName().length(); i++) {
                    char encodedLetter = ((char) (player.getName().charAt(i) + randomEncriptionCode));
                    encodedName = encodedName + String.valueOf(encodedLetter);
                }
                for (int i = 0; i < String.valueOf(player.getBestScore()).length(); i++) {
                    char encodedNumber = ((char) (String.valueOf(player.getBestScore()).charAt(i) + randomEncriptionCode));
                    encodedScore = encodedScore + encodedNumber;
                }
                playerInfo += encodedName + ":" + encodedScore + "\n";
            }
            writer.write(playerInfo);
            writer.close();

            Formatter formatter = new Formatter(encription.toFile());
            formatter.format(String.valueOf(randomEncriptionCode));
            formatter.close();
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

    public void setCurrentPlayerScore(int currentPlayerScore) {
        this.currentPlayerScore = currentPlayerScore;
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
