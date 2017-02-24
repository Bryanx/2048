package be.kdg.thegame_2048.models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Jarne Van Aerde
 * @version 1.0 24/02/2017 10:08
 */
public class DataReaderWriter {
    //ATTRIBUTES

    //CONSTRUCTORS

    //METHODS
    /**Decription was used**/
    public static List<Player> loadPlayerData() {
        Path data = Paths.get("playerdata" + File.separator + "data.txt");
        Path decoderData = Paths.get("playerdata" + File.separator + "encripted.txt");
        List<Player> playerList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(data.toFile()))){
            int decodeNumber = Integer.parseInt(new Scanner(decoderData).nextLine());
            String info = reader.readLine();
            if (info == null) return playerList;
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
                Player player = new Player(decodedName, Integer.parseInt(decodedScore));
                playerList.add(player);

                if (splittedData.length == 3) {
                    player.setLastPlayed(splittedData[2]);
                }

                info = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong, contact support!");
        }
        return playerList;
    }

    /**Incription was used**/
    public static void savePlayerData(List<Player> playerList) {
        Path playerdata = Paths.get("playerdata");
        Path data = playerdata.resolve("data.txt");
        Path encription = playerdata.resolve("encripted.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data.toFile()))) {
            if (!Files.exists(playerdata)) Files.createDirectory(playerdata);
            if (!Files.exists(data)) Files.createFile(data);
            if (!Files.exists(encription)) Files.createFile(encription);

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
                if (player.getLastPlayed() == null) {
                    playerInfo += encodedName + ":" + encodedScore +  ": " + "\n";
                } else {
                    playerInfo += encodedName + ":" + encodedScore +  ":" + player.getLastPlayed() + "\n";
                }

            }
            writer.write(playerInfo);
            Formatter formatter = new Formatter(encription.toFile());
            formatter.format(String.valueOf(randomEncriptionCode));
            formatter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
