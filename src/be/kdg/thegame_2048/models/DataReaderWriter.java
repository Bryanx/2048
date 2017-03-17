package be.kdg.thegame_2048.models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 24/02/2017 10:08
 */
public final class DataReaderWriter {
    /**
     * Decryption was used while loading the playerdata.
     * The decode-number is subtracted from the individuals chars
     * to go back to the right ASCII-value.
     **/
    public static List<Player> loadPlayerData() {
        Properties prop = System.getProperties();
        String data = prop.getProperty("user.home") + File.separator + "2048_data" + File.separator + "playerdata.bin";
        List<Player> playerList = new ArrayList<>();

        try (DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(data)))) {
            while (true) {
                Player player = new Player(inputStream.readUTF(), inputStream.readInt());
                playerList.add(player);
            }
        } catch (EOFException eof) {
            //Nothing, everything is fine.
        } catch (IOException e) {
            writeToLog(e.getMessage());
        }
        return playerList;
    }

    /**
     * Encryption was used while writing the playerdata.
     **/
    public static void savePlayerData(List<Player> playerList) {
        Properties prop = System.getProperties();
        Path playerdata = Paths.get(prop.getProperty("user.home") + File.separator + "2048_data");
        Path data = playerdata.resolve("playerdata.bin");

        try (DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(data.toFile())))) {
            if (!Files.exists(playerdata)) Files.createDirectory(playerdata);
            if (!Files.exists(data)) Files.createFile(data);

            for (Player player : playerList) {
                outputStream.writeUTF(player.getName());
                outputStream.writeInt(player.getBestScore());
            }
        } catch (IOException e) {
            writeToLog(e.getMessage());
        }
    }

    /**
     * Writes all error messages to a text file
     **/
    public static void writeToLog(String message) {
        Properties prop = System.getProperties();
        Path playerData = Paths.get(prop.getProperty("user.home") + File.separator + "2048_data");
        Path errorMessage = playerData.resolve("errorLog.txt");

        try {
            if (!Files.exists(playerData)) Files.createDirectory(playerData);
            if (!Files.exists(errorMessage)) Files.createFile(errorMessage);

            BufferedWriter writer = new BufferedWriter(new FileWriter(errorMessage.toFile()));
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            System.out.println("Fundamental problem with the log-IO. Contact support!");
            System.exit(1);
        }
    }
}
