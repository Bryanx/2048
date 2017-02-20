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

    //CONSTRUCTORS
    Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_H_SECTIONS][NUMBER_OF_V_SECTIONS];
        initialiseSections();
        addRandomBlocks(1);
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
                int X = blockGen.nextInt(NUMBER_OF_H_SECTIONS);
                int Y = blockGen.nextInt(NUMBER_OF_V_SECTIONS);
                if (!this.sections[X][Y].hasBlock()) {
                    this.sections[X][Y].putBlock(new Block(2));
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
        boolean[] haveBlocksColumn1 = haveBlocksColumn1();
        boolean[] haveBlocksColumn2 = haveBlocksColumn2();
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
        haveBlocksColumn1 = haveBlocksColumn1();
        haveBlocksColumn2 = haveBlocksColumn2();
        boolean[] haveBlocksColumn3 = haveBlocksColumn3();
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
        haveBlocksColumn1 = haveBlocksColumn1();
        haveBlocksColumn2 = haveBlocksColumn2();
        haveBlocksColumn3 = haveBlocksColumn3();
        boolean[] haveBlocksColumn4 = haveBlocksColumn4();
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
        boolean[] haveBlocksColumn4 = haveBlocksColumn4();
        boolean[] haveBlocksColumn3 = haveBlocksColumn3();
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
        haveBlocksColumn4 = haveBlocksColumn4();
        haveBlocksColumn3 = haveBlocksColumn3();
        boolean[] haveBlocksColumn2 = haveBlocksColumn2();
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
        haveBlocksColumn4 = haveBlocksColumn4();
        haveBlocksColumn3 = haveBlocksColumn3();
        haveBlocksColumn2 = haveBlocksColumn2();
        boolean[] haveBlocksColumn1 = haveBlocksColumn1();
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

    private boolean[] haveBlocksColumn1() {
        boolean[] haveBlocksColumn1 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[i][0].hasBlock()) haveBlocksColumn1[i] = true;
        }
        return haveBlocksColumn1;
    }

    private boolean[] haveBlocksColumn2() {
        boolean[] haveBlocksColumn2 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[i][1].hasBlock()) haveBlocksColumn2[i] = true;
        }
        return haveBlocksColumn2;
    }

    private boolean[] haveBlocksColumn3() {
        boolean[] haveBlocksColumn3 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[i][2].hasBlock()) haveBlocksColumn3[i] = true;
        }
        return haveBlocksColumn3;
    }

    private boolean[] haveBlocksColumn4() {
        boolean[] haveBlocksColumn4 = new boolean[NUMBER_OF_V_SECTIONS];
        for (int i = 0; i < NUMBER_OF_V_SECTIONS; i++) {
            if (this.sections[i][3].hasBlock()) haveBlocksColumn4[i] = true;
        }
        return haveBlocksColumn4;
    }

    private boolean isMergealbe(Section section, Section otherSection) {
        return section.getBlock().getValue() == otherSection.getBlock().getValue();
    }

    private void moveBlock(Section section, Section otherSection) {
        section.putBlock(otherSection.getBlock());
        otherSection.removeBlock();
    }

    Random getBlockGen() {
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
