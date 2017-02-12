package be.kdg.thegame_2048.models;

import java.util.Random;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:44
 */
public class Playground {
    //EIGENSCAPPEN
    private static final Random blockGen = new Random();
    private static final int NUMBER_OF_H_SECTIONS = 4;
    private static final int NUMBER_OF_V_SECTIONS = 4;
    private Section[][] sections;
    private Score score;

    //CONSTRUCTORS
    public Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_H_SECTIONS][NUMBER_OF_V_SECTIONS];
        initialiseSections();
        addRandomBlocks();
        System.out.println(toString());
    }

    //METHODEN
    public Section[][] getSections() {
        return sections;
    }

    private void initialiseSections() {
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            for (int j = 0; j < NUMBER_OF_V_SECTIONS; j++) {
                this.sections[i][j] = new Section();
            }
        }
    }

    private void addRandomBlocks() {
        //Blijft zoeken tot dat er een vrije plek gevonden is.
        // Dit heeft niets te maken met dat de speler verloren zou hebben als hij niets vindt!
        //Dat wordt bepaald in de klasse Game!
        while (true) {
            int indexH = blockGen.nextInt(NUMBER_OF_H_SECTIONS);
            int indexV = blockGen.nextInt(NUMBER_OF_V_SECTIONS);
            if (!this.sections[indexH][indexV].hasBlock()) {
                this.sections[indexH][indexV].putBlock(new Block(2));
                return;
            }
        }
    }

    private void merge(Section sectionBlock, Section sectionOtherBlock) {
        score.calculateScore(sectionBlock.getBlock(), sectionOtherBlock.getBlock());
        sectionBlock.getBlock().setValue(sectionBlock.getBlock().getValue() + sectionOtherBlock.getBlock().getValue());
        sectionOtherBlock.removeBlock();
    }

    public void moveBlocksTop() {
        //TODO
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                s.append(this.sections[i][j].toString() + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
