package be.kdg.thegame_2048.models;

import java.util.Random;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:44
 */
final class Playground {
    //EIGENSCAPPEN
    private final Random blockGen = new Random();
    private static final int NUMBER_OF_H_SECTIONS = 4;
    private static final int NUMBER_OF_V_SECTIONS = 4;
    private Section[][] sections;
    private Score score;

    //CONSTRUCTORS
    Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_H_SECTIONS][NUMBER_OF_V_SECTIONS];
        initialiseSections();
        for (int i = 0; i < 10; i++) {
            addRandomBlocks(1);
        }
        System.out.println(toString());
        moveBlocksBottom();
        System.out.println(toString());
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

    void addRandomBlocks(int numberOfBlocks) {
        for (int i = 0; i < numberOfBlocks; i++) {
            boolean blockFound = false;
            while (!blockFound) {
                int indexH = blockGen.nextInt(NUMBER_OF_H_SECTIONS);
                int indexV = blockGen.nextInt(NUMBER_OF_V_SECTIONS);
                if (!this.sections[indexH][indexV].hasBlock()) {
                    this.sections[indexH][indexV].putBlock(new Block(2));
                    blockFound = true;
                }
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
        boolean[] haveBlocksRow2 = haveBlocksRow2();
        boolean[] alreadyMergedRow1AndRow2 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (haveBlocksRow2[i] && haveBlocksRow1[i]) {
                if (isMergealbe(this.sections[1][i], this.sections[0][i])) {
                    merge(this.sections[0][i], this.sections[1][i]);
                    alreadyMergedRow1AndRow2[i] = true;
                }
            } else if (haveBlocksRow2[i] && !haveBlocksRow1[i]) {
                moveBlock(this.sections[0][i], this.sections[1][i]);
            }
        }

        //ROW 2 AND 3
        haveBlocksRow1 = haveBlocksRow1();
        haveBlocksRow2 = haveBlocksRow2();
        boolean[] haveBlocksRow3 = haveBlocksRow3();
        boolean[] alreadyMergedRow2AndRow3 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (haveBlocksRow3[i] && haveBlocksRow2[i]) {
                if (isMergealbe(this.sections[2][i], this.sections[1][i])) {
                    merge(this.sections[1][i], this.sections[2][i]);
                    alreadyMergedRow2AndRow3[i] = true;
                }
            } else if (haveBlocksRow3[i] && haveBlocksRow1[i]) {
                if (isMergealbe(this.sections[2][i], this.sections[0][i]) && !alreadyMergedRow1AndRow2[i]) {
                    merge(this.sections[0][i], this.sections[2][i]);
                } else {
                    moveBlock(this.sections[1][i], this.sections[2][i]);
                }
            } else if (haveBlocksRow3[i] && !haveBlocksRow1[i]) {
                moveBlock(this.sections[0][i], this.sections[2][i]);
            }
        }

        //ROW 3 AND 4
        haveBlocksRow1 = haveBlocksRow1();
        haveBlocksRow2 = haveBlocksRow2();
        haveBlocksRow3 = haveBlocksRow3();
        boolean[] haveBlocksRow4 = haveBlocksRow4();
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (haveBlocksRow4[i] && haveBlocksRow3[i]) {
                if (isMergealbe(this.sections[3][i], this.sections[2][i])) {
                    merge(this.sections[2][i], this.sections[3][i]);
                }
            } else if (haveBlocksRow4[i] && haveBlocksRow2[i]) {
                if (isMergealbe(this.sections[3][i], this.sections[1][i]) && !alreadyMergedRow2AndRow3[i]) {
                    merge(this.sections[1][i], this.sections[3][i]);
                } else {
                    moveBlock(this.sections[2][i], this.sections[3][i]);
                }
            } else if (haveBlocksRow4[i] && haveBlocksRow1[i]) {
                if (isMergealbe(this.sections[3][i], this.sections[0][i])
                        && !alreadyMergedRow1AndRow2[i]) {
                    merge(this.sections[0][i], this.sections[3][i]);
                } else {
                    moveBlock(this.sections[1][i], this.sections[3][i]);
                }
            } else if (haveBlocksRow4[i] && !haveBlocksRow1[i]) {
                moveBlock(this.sections[0][i], this.sections[3][i]);
            }
        }
    }

    void moveBlocksBottom() {
        //ROW 3 AND 4
        boolean[] haveBlocksRow4 = haveBlocksRow4();
        boolean[] haveBlocksRow3 = haveBlocksRow3();
        boolean[] alreadyMergedRow3AndRow4 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (haveBlocksRow3[i] && haveBlocksRow4[i]) {
                if (isMergealbe(this.sections[2][i], this.sections[3][i])) {
                    merge(this.sections[3][i], this.sections[2][i]);
                    alreadyMergedRow3AndRow4[i] = true;
                }
            } else if (haveBlocksRow3[i] && !haveBlocksRow4[i]) {
                moveBlock(this.sections[3][i], this.sections[2][i]);
            }
        }

        //ROW 2 AND 3
        haveBlocksRow4 = haveBlocksRow4();
        haveBlocksRow3 = haveBlocksRow3();
        boolean[] haveBlocksRow2 = haveBlocksRow2();
        boolean[] alreadyMergedRow2AndRow3 = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (haveBlocksRow2[i] && haveBlocksRow3[i]) {
                if (isMergealbe(this.sections[1][i], this.sections[2][i])) {
                    merge(this.sections[2][i], this.sections[1][i]);
                    alreadyMergedRow2AndRow3[i] = true;
                }
            } else if (haveBlocksRow2[i] && haveBlocksRow4[i]) {
                if (isMergealbe(this.sections[1][i], this.sections[3][i]) && !alreadyMergedRow3AndRow4[i]) {
                    merge(this.sections[3][i], this.sections[1][i]);
                } else {
                    moveBlock(this.sections[2][i], this.sections[1][i]);
                }
            } else if (haveBlocksRow2[i] && !haveBlocksRow4[i]) {
                moveBlock(this.sections[3][i], this.sections[1][i]);
            }
        }

        //ROW 1 AND 2
        haveBlocksRow4 = haveBlocksRow4();
        haveBlocksRow3 = haveBlocksRow3();
        haveBlocksRow2 = haveBlocksRow2();
        boolean[] haveBlocksRow1 = haveBlocksRow1();
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (haveBlocksRow1[i] && haveBlocksRow2[i]) {
                if (isMergealbe(this.sections[0][i], this.sections[1][i])) {
                    merge(this.sections[1][i], this.sections[0][i]);
                }
            } else if (haveBlocksRow1[i] && haveBlocksRow3[i]) {
                if (isMergealbe(this.sections[0][i], this.sections[2][i]) && !alreadyMergedRow2AndRow3[i]) {
                    merge(this.sections[2][i], this.sections[0][i]);
                } else {
                    moveBlock(this.sections[1][i], this.sections[0][i]);
                }
            } else if (haveBlocksRow1[i] && haveBlocksRow4[i]) {
                if (isMergealbe(this.sections[0][i], this.sections[3][i])
                        && !alreadyMergedRow3AndRow4[i]) {
                    merge(this.sections[3][i], this.sections[0][i]);
                } else {
                    moveBlock(this.sections[2][i], this.sections[0][i]);
                }
            } else if (haveBlocksRow1[i] && !haveBlocksRow4[i]) {
                moveBlock(this.sections[3][i], this.sections[0][i]);
            }
        }
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

    private boolean isMergealbe(Section section, Section otherSection) {
        return section.getBlock().getValue() == otherSection.getBlock().getValue();
    }

    private void moveBlock(Section section, Section otherSection) {
        section.putBlock(otherSection.getBlock());
        otherSection.removeBlock();
    }

    public Random getBlockGen() {
        return blockGen;
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
