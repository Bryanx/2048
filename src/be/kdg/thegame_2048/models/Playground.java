package be.kdg.thegame_2048.models;

import java.util.Random;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:44
 */
final class Playground {
    //EIGENSCAPPEN
    private static final Random blockGen = new Random();
    private static final int NUMBER_OF_H_SECTIONS = 4;
    private static final int NUMBER_OF_V_SECTIONS = 4;
    private Section[][] sections;
    private Score score;

    //CONSTRUCTORS
    Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_H_SECTIONS][NUMBER_OF_V_SECTIONS];
        initialiseSections();
        addRandomBlocks();
    }

    //METHODEN
    Section[][] getSections() {
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

    void moveBlocksTop() {
        //ROW 1 AND 2
        boolean[] haveBlocksRow1 = haveBlocksRow1();
        boolean[] alreadyMergedRow1AndRow2 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[1][i].hasBlock() && haveBlocksRow1[i]) {
                if (this.sections[1][i].getBlock().getValue() == this.sections[0][i].getBlock().getValue()) {
                    merge(this.sections[0][i], this.sections[1][i]);
                    alreadyMergedRow1AndRow2[i] = true;
                }
            } else if (this.sections[1][i].hasBlock() && !haveBlocksRow1[i]) {
                this.sections[0][i].putBlock(this.sections[1][i].getBlock());
                this.sections[1][i].removeBlock();
            }
        }

        //ROW 2 AND 3
        haveBlocksRow1 = haveBlocksRow1();
        boolean[] haveBlocksRow2 = haveBlocksRow2();
        boolean[] alreadyMergedRow2AndRow3 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[2][i].hasBlock() && haveBlocksRow2[i]) {
                if (this.sections[2][i].getBlock().getValue() == this.sections[1][i].getBlock().getValue()) {
                    merge(this.sections[1][i], this.sections[2][i]);
                    alreadyMergedRow2AndRow3[i] = true;
                }
            } else if (this.sections[2][i].hasBlock() && haveBlocksRow1[i]) {
                if (this.sections[2][i].getBlock().getValue() == this.sections[0][i].getBlock().getValue()
                        && !alreadyMergedRow1AndRow2[i]) {
                    merge(this.sections[0][i], this.sections[2][i]);
                } else {
                    this.sections[1][i].putBlock(this.sections[2][i].getBlock());
                    this.sections[2][i].removeBlock();
                }
            } else if (this.sections[2][i].hasBlock() && !haveBlocksRow1[i]) {
                this.sections[0][i].putBlock(this.sections[2][i].getBlock());
                this.sections[2][i].removeBlock();
            }
        }

        //ROW 3 AND 4
        haveBlocksRow1 = haveBlocksRow1();
        haveBlocksRow2 = haveBlocksRow2();
        boolean[] haveBlocksRow3 = haveBlocksRow3();
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[3][i].hasBlock() && haveBlocksRow3[i]) {
                if (this.sections[3][i].getBlock().getValue() == this.sections[2][i].getBlock().getValue()) {
                    merge(this.sections[2][i], this.sections[3][i]);
                }
            } else if (this.sections[3][i].hasBlock() && haveBlocksRow2[i]) {
                if (this.sections[3][i].getBlock().getValue() == this.sections[1][i].getBlock().getValue()
                        && !alreadyMergedRow2AndRow3[i]) {
                    merge(this.sections[1][i], this.sections[3][i]);
                } else {
                    this.sections[2][i].putBlock(this.sections[3][i].getBlock());
                    this.sections[3][i].removeBlock();
                }
            } else if (this.sections[3][i].hasBlock() && haveBlocksRow1[i]) {
                if (this.sections[3][i].getBlock().getValue() == this.sections[0][i].getBlock().getValue()
                        && !alreadyMergedRow1AndRow2[i] && !alreadyMergedRow2AndRow3[i]) {
                    merge(this.sections[0][i], this.sections[3][i]);
                } else {
                    this.sections[1][i].putBlock(this.sections[3][i].getBlock());
                    this.sections[3][i].removeBlock();
                }
            } else if (this.sections[3][i].hasBlock() && !haveBlocksRow1[i]) {
                this.sections[0][i].putBlock(this.sections[3][i].getBlock());
                this.sections[3][i].removeBlock();
            }
        }

        addRandomBlocks();
    }

    void moveBlocksBottom() {

    }

    void moveBlocksLeft() {

    }

    void moveBlocksRight() {

    }

    private boolean[] haveBlocksRow1() {
        boolean[] haveBlocksRow1 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[0][i].hasBlock()) haveBlocksRow1[i] = true;
        }
        return haveBlocksRow1;
    }

    private boolean[] haveBlocksRow2() {
        boolean[] haveBlocksRow2 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[1][i].hasBlock()) haveBlocksRow2[i] = true;
        }
        return haveBlocksRow2;
    }

    private boolean[] haveBlocksRow3() {
        boolean[] haveBlocksRow3 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[2][i].hasBlock()) haveBlocksRow3[i] = true;
        }
        return haveBlocksRow3;
    }

    private boolean[] haveBlocksRow4() {
        boolean[] haveBlocksRow4 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[3][i].hasBlock()) haveBlocksRow4[i] = true;
        }
        return haveBlocksRow4;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            for (int j = 0; j < NUMBER_OF_V_SECTIONS; j++) {
                s.append(this.sections[i][j].toString() + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
