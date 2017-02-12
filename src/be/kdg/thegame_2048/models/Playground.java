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
        this.sections[0][1].putBlock(new Block(4));
        this.sections[0][2].putBlock(new Block(2));
        this.sections[1][1].putBlock(new Block(4));
        this.sections[1][2].putBlock(new Block(2));
        this.sections[1][3].putBlock(new Block(2));
        this.sections[2][1].putBlock(new Block(8));
        this.sections[2][3].putBlock(new Block(2));
        System.out.println(toString());
        moveBlocksTop();
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
        //ROW 1 AND 2
        boolean[] haveBlocksRow1 = haveBlocksRow1();
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[1][i].hasBlock() && haveBlocksRow1[i]) {
                if (this.sections[1][i].getBlock().getValue() == this.sections[0][i].getBlock().getValue()) {
                    merge(this.sections[0][i], this.sections[1][i]);
                }
            } else if (this.sections[1][i].hasBlock() && !haveBlocksRow1[i]) {
                this.sections[0][i].putBlock(this.sections[1][i].getBlock());
                this.sections[1][i].removeBlock();
            }
        }

        //ROW 2 AND 3
        boolean[] haveBlocksRow2 = haveBlocksRow2();
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[2][i].hasBlock() && haveBlocksRow2[i]) {
                if (this.sections[2][i].getBlock().getValue() == this.sections[1][i].getBlock().getValue()) {
                    merge(this.sections[1][i], this.sections[2][i]);
                }
            } else if (this.sections[2][i].hasBlock() && haveBlocksRow1[i]) {
                if (this.sections[2][i].getBlock().getValue() == this.sections[0][i].getBlock().getValue()) {
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

        /*boolean[] haveBlocksRow3 = haveBlocksRow3();
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[3][i].hasBlock() && haveBlocksRow3[i]) {
                //TODO
            } else if (this.sections[3][i].hasBlock())
        }*/

        boolean[] haveBlocksRow4 = haveBlocksRow4();
    }

    private boolean[] haveBlocksRow1() {
        boolean[] haveBlocksRow1 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[0][i].hasBlock()) haveBlocksRow1[i] = true;
        }
        return haveBlocksRow1;
    }

    private boolean[] haveBlocksRow2() {
        boolean[] haveBlocksRow2 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[1][i].hasBlock()) haveBlocksRow2[i] = true;
        }
        return haveBlocksRow2;
    }

    private boolean[] haveBlocksRow3() {
        boolean[] haveBlocksRow3 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[2][i].hasBlock()) haveBlocksRow3[i] = true;
        }
        return haveBlocksRow3;
    }

    private boolean[] haveBlocksRow4() {
        boolean[] haveBlocksRow4 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
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
