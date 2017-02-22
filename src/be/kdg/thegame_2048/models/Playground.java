package be.kdg.thegame_2048.models;

import java.util.Random;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 17:44
 */
final class Playground {
    //EIGENSCAPPEN
    private final Random blockGen = new Random();
    private static final int NUMBER_OF_H_SECTIONS = 4;
    private static final int NUMBER_OF_V_SECTIONS = 4;

    private Section[][] sections;
    private Score score;
    private boolean hasMerged;

    //CONSTRUCTORS
    Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_H_SECTIONS][NUMBER_OF_V_SECTIONS];
    }

    //METHODEN
    Section[][] getSections() {
        return sections;
    }

    void initialiseSections() {
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            for (int j = 0; j < NUMBER_OF_V_SECTIONS; j++) {
                this.sections[i][j] = new Section();
            }
        }
    }

    void addRandomBlock() {
        if (playGroundFull()) return;
        boolean blockFound = false;
        while (!blockFound) {
            int x = blockGen.nextInt(NUMBER_OF_H_SECTIONS);
            int y = blockGen.nextInt(NUMBER_OF_V_SECTIONS);
            if (!this.sections[x][y].hasBlock()) {
                this.sections[x][y].putBlock(new Block(2, true));
                blockFound = true;
            }
        }
    }

    boolean playGroundFull() {
        int blockCounter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.sections[i][j].hasBlock()) blockCounter++;
            }
        }
        return blockCounter >= 16;
    }

    private void merge(Section sectionBlock, Section sectionOtherBlock) {
        score.calculateScore(sectionBlock.getBlock(), sectionOtherBlock.getBlock());
        sectionBlock.getBlock().setValue(sectionBlock.getBlock().getValue() + sectionOtherBlock.getBlock().getValue());
        sectionOtherBlock.removeBlock();
        this.hasMerged = true;
//        if (sectionBlock.getBlock() != null) sectionBlock.getBlock().setRandom(false);
//        if (sectionOtherBlock.getBlock() != null) sectionOtherBlock.getBlock().setRandom(false);
    }

    void moveBlocksTop() {
        //ROW 1 AND 2
        boolean[] haveBlocksRow1 = haveBlocksRow(1);
        boolean[] haveBlocksRow2 = haveBlocksRow(2);
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
        haveBlocksRow1 = haveBlocksRow(1);
        haveBlocksRow2 = haveBlocksRow(2);
        boolean[] haveBlocksRow3 = haveBlocksRow(3);
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
        haveBlocksRow1 = haveBlocksRow(1);
        haveBlocksRow2 = haveBlocksRow(2);
        haveBlocksRow3 = haveBlocksRow(3);
        boolean[] haveBlocksRow4 = haveBlocksRow(4);
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
                if (isMergealbe(this.sections[3][i], this.sections[0][i]) && !alreadyMergedRow1AndRow2[i] && !alreadyMergedRow2AndRow3[i]) {
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
        boolean[] haveBlocksRow4 = haveBlocksRow(4);
        boolean[] haveBlocksRow3 = haveBlocksRow(3);
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
        haveBlocksRow4 = haveBlocksRow(4);
        haveBlocksRow3 = haveBlocksRow(3);
        boolean[] haveBlocksRow2 = haveBlocksRow(2);
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
        haveBlocksRow4 = haveBlocksRow(4);
        haveBlocksRow3 = haveBlocksRow(3);
        haveBlocksRow2 = haveBlocksRow(2);
        boolean[] haveBlocksRow1 = haveBlocksRow(1);
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
                if (isMergealbe(this.sections[0][i], this.sections[3][i]) && !alreadyMergedRow3AndRow4[i] && !alreadyMergedRow2AndRow3[i]) {
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
        //COLUMN 1 AND 2
        boolean[] haveBlocksColumn1 = haveBlocksColumn(1);
        boolean[] haveBlocksColumn2 = haveBlocksColumn(2);
        boolean[] alreadyMergedColumn1AndColumn2 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (haveBlocksColumn2[i] && haveBlocksColumn1[i]) {
                if (isMergealbe(this.sections[i][1], this.sections[i][0])) {
                    merge(this.sections[i][0], this.sections[i][1]);
                    alreadyMergedColumn1AndColumn2[i] = true;
                }
            } else if (haveBlocksColumn2[i] && !haveBlocksColumn1[i]) {
                moveBlock(this.sections[i][0], this.sections[i][1]);
            }
        }

        //COLUMN 2 AND 3
        haveBlocksColumn1 = haveBlocksColumn(1);
        haveBlocksColumn2 = haveBlocksColumn(2);
        boolean[] haveBlocksColumn3 = haveBlocksColumn(3);
        boolean[] alreadyMergedColumn2AndColumn3 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (haveBlocksColumn3[i] && haveBlocksColumn2[i]) {
                if (isMergealbe(this.sections[i][2], this.sections[i][1])) {
                    merge(this.sections[i][1], this.sections[i][2]);
                    alreadyMergedColumn2AndColumn3[i] = true;
                }
            } else if (haveBlocksColumn3[i] && haveBlocksColumn1[i]) {
                if (isMergealbe(this.sections[i][2], this.sections[i][0]) && !alreadyMergedColumn1AndColumn2[i]) {
                    merge(this.sections[i][0], this.sections[i][2]);
                } else {
                    moveBlock(this.sections[i][1], this.sections[i][2]);
                }
            } else if (haveBlocksColumn3[i] && !haveBlocksColumn1[i]) {
                moveBlock(this.sections[i][0], this.sections[i][2]);

            }
        }

        //COLUMN 3 AND 4
        haveBlocksColumn1 = haveBlocksColumn(1);
        haveBlocksColumn2 = haveBlocksColumn(2);
        haveBlocksColumn3 = haveBlocksColumn(3);
        boolean[] haveBlocksColumn4 = haveBlocksColumn(4);
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (haveBlocksColumn4[i] && haveBlocksColumn3[i]) {
                if (isMergealbe(this.sections[i][3], this.sections[i][2])) {
                    merge(this.sections[i][2], this.sections[i][3]);
                }
            } else if (haveBlocksColumn4[i] && haveBlocksColumn2[i]) {
                if (isMergealbe(this.sections[i][3], this.sections[i][1]) && !alreadyMergedColumn2AndColumn3[i]) {
                    merge(this.sections[i][1], this.sections[i][3]);
                } else {
                    moveBlock(this.sections[i][2], this.sections[i][3]);
                }
            } else if (haveBlocksColumn4[i] && haveBlocksColumn1[i]) {
                if (isMergealbe(this.sections[i][3], this.sections[i][0]) && !alreadyMergedColumn1AndColumn2[i] && !alreadyMergedColumn2AndColumn3[i]) {
                    merge(this.sections[i][0], this.sections[i][3]);
                } else {
                    moveBlock(this.sections[i][1], this.sections[i][3]);
                }
            } else if (haveBlocksColumn4[i] && !haveBlocksColumn1[i]) {
                moveBlock(this.sections[i][0], this.sections[i][3]);
            }
        }
    }

    void moveBlocksRight() {
        //COLUMN 3 AND 4
        boolean[] haveBlocksColumn4 = haveBlocksColumn(4);
        boolean[] haveBlocksColumn3 = haveBlocksColumn(3);
        boolean[] alreadyMergedRow3AndRow4 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (haveBlocksColumn3[i] && haveBlocksColumn4[i]) {
                if (isMergealbe(this.sections[i][2], this.sections[i][3])) {
                    merge(this.sections[i][3], this.sections[i][2]);
                    alreadyMergedRow3AndRow4[i] = true;
                }
            } else if (haveBlocksColumn3[i] && !haveBlocksColumn4[i]) {
                moveBlock(this.sections[i][3], this.sections[i][2]);
            }
        }

        //COLUMN 2 AND 3
        haveBlocksColumn4 = haveBlocksColumn(4);
        haveBlocksColumn3 = haveBlocksColumn(3);
        boolean[] haveBlocksColumn2 = haveBlocksColumn(2);
        boolean[] alreadyMergedRow2AndRow3 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (haveBlocksColumn2[i] && haveBlocksColumn3[i]) {
                if (isMergealbe(this.sections[i][1], this.sections[i][2])) {
                    merge(this.sections[i][2], this.sections[i][1]);
                    alreadyMergedRow2AndRow3[i] = true;
                }
            } else if (haveBlocksColumn2[i] && haveBlocksColumn4[i]) {
                if (isMergealbe(this.sections[i][1], this.sections[i][3]) && !alreadyMergedRow3AndRow4[i]) {
                    merge(this.sections[i][3], this.sections[i][1]);
                } else {
                    moveBlock(this.sections[i][2], this.sections[i][1]);
                }
            } else if (haveBlocksColumn2[i] && !haveBlocksColumn4[i]) {
                moveBlock(this.sections[i][3], this.sections[i][1]);
            }
        }

        //COLUMN 1 AND 2
        haveBlocksColumn4 = haveBlocksColumn(4);
        haveBlocksColumn3 = haveBlocksColumn(3);
        haveBlocksColumn2 = haveBlocksColumn(2);
        boolean[] haveBlocksColumn1 = haveBlocksColumn(1);
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (haveBlocksColumn1[i] && haveBlocksColumn2[i]) {
                if (isMergealbe(this.sections[i][0], this.sections[i][1])) {
                    merge(this.sections[i][1], this.sections[i][0]);
                }
            } else if (haveBlocksColumn1[i] && haveBlocksColumn3[i]) {
                if (isMergealbe(this.sections[i][0], this.sections[i][2]) && !alreadyMergedRow2AndRow3[i]) {
                    merge(this.sections[i][2], this.sections[i][0]);
                } else {
                    moveBlock(this.sections[i][1], this.sections[i][0]);
                }
            } else if (haveBlocksColumn1[i] && haveBlocksColumn4[i]) {
                if (isMergealbe(this.sections[i][0], this.sections[i][3]) && !alreadyMergedRow3AndRow4[i] && !alreadyMergedRow2AndRow3[i]) {
                    merge(this.sections[i][3], this.sections[i][0]);
                } else {
                    moveBlock(this.sections[i][2], this.sections[i][0]);
                }
            } else if (haveBlocksColumn1[i] && !haveBlocksColumn4[i]) {
                moveBlock(this.sections[i][3], this.sections[i][0]);
            }
        }
    }

    private boolean[] haveBlocksRow(int numberRow) {
        boolean[] haveBlocksRow = new boolean[NUMBER_OF_H_SECTIONS];
        for (int i = 0; i < NUMBER_OF_H_SECTIONS; i++) {
            if (this.sections[numberRow-1][i].hasBlock()) haveBlocksRow[i] = true;
        }
        return haveBlocksRow;
    }

    private boolean[] haveBlocksColumn(int numberColumn) {
        boolean[] haveBlocksColumn1 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[i][numberColumn-1].hasBlock()) haveBlocksColumn1[i] = true;
        }
        return haveBlocksColumn1;
    }

    private boolean isMergealbe(Section section, Section otherSection) {
        return section.getBlock().getValue() == otherSection.getBlock().getValue();
    }

    private void moveBlock(Section section, Section otherSection) {
        section.putBlock(otherSection.getBlock());
        otherSection.removeBlock();
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
