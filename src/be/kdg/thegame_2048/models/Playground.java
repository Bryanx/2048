package be.kdg.thegame_2048.models;

import java.util.Random;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:44
 */
public class Playground {
    //EIGENSCAPPEN
    private static final Random blockGen = new Random();
    private static final int NUMBER_OF_SECTIONS = 16;
    private Section[] sections;
    private Score score;

    //CONSTRUCTORS
    public Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_SECTIONS];
        initialiseSections();
        addRandomBlocks();
    }

    //METHODEN
    public Section[] getSections() {
        return sections;
    }

    private void initialiseSections() {
        //Ik hoop dat je dit snapt want ik zou niet weten hoe ik dit moet uitleggen haha!
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.sections[index++] = new Section(String.valueOf(i) + String.valueOf(j)); //Bv. coord = 01 (eerste rij, tweede kolom.
            }
        }
    }

    private void addRandomBlocks() {
        //Blijft zoeken tot dat er een vrije plek gevonden is.
        // Dit heeft niets te maken met dat de speler verloren zou hebben als hij niets vindt!
        //Dat wordt bepaald in de klasse Game!
        while (true) {
            int index = blockGen.nextInt(16);
            if (!this.sections[index].hasBlock()) {
                this.sections[index].putBlock(new Block(2));
                return;
            }
        }
    }

    private void merge(Section sectionBlock, Section sectionOtherBlock) {
        score.calculateScore(sectionBlock.getBlock(), sectionOtherBlock.getBlock());
        sectionBlock.getBlock().setValue(sectionBlock.getBlock().getValue() + sectionOtherBlock.getBlock().getValue());
        sectionOtherBlock.removeBlock();
    }

    public void moveBlocksBottom() {
        for (Section section : sections) {

        }
    }
}
