package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPress, downPress, leftPress, rightPress, spacePress;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
        }
        if (code == KeyEvent.VK_UP) {
            if (gp.gameState == gp.menuState) {
                if (gp.ui.menuNum >= 0) {
                    gp.ui.menuNum--;
                    if (gp.ui.menuNum == -1) {
                        gp.ui.menuNum = 2;
                    }
                }
            }
            if (gp.gameState == gp.pauseState) {
                if (gp.ui.pauseNum >= 0) {
                    gp.ui.pauseNum--;
                    if (gp.ui.pauseNum == -1) {
                        gp.ui.pauseNum = 1;
                    }
                }
            }
            if (gp.gameState == gp.endState) {
                if (gp.ui.overNum > -1) {
                    gp.ui.overNum--;
                    if (gp.ui.overNum == -1) {
                        gp.ui.overNum = 2;
                    }
                }
            }

        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.pauseNum < 2) {
                gp.ui.pauseNum++;
                if (gp.ui.pauseNum == 2) {
                    gp.ui.pauseNum = 0;
                }
            }
            if (gp.ui.menuNum <= 2) {
                gp.ui.menuNum++;
                if (gp.ui.menuNum == 3) {
                    gp.ui.menuNum = 0;
                }
            }
            if(gp.gameState==gp.endState) {
                if(gp.ui.overNum<3) {
                    gp.ui.overNum++;
                    if(gp.ui.overNum==3) {
                        gp.ui.overNum=0;
                    }
                }
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.gameState == gp.menuState) {
                switch (gp.ui.menuNum) {
                    case 0:
                        gp.InitGame();
                        gp.changeGameState(gp.playState);
                        gp.playMusic(0);
                        break;
                    case 1:
                        //draw option
                    case 2:
                        System.exit(0);
                        break;
                }
            }
            if (gp.gameState == gp.pauseState) {
                switch (gp.ui.pauseNum) {
                    case 0:
                        gp.changeGameState(gp.playState);
                        break;
                    case 1:
                        gp.changeGameState(gp.menuState);
                        gp.stopMusic();
                        break;
                }
            }
            if (gp.gameState == gp.endState) {
                switch (gp.ui.overNum) {
                    case 0:
                        gp.InitGame();
                        gp.changeGameState(gp.playState);
                        gp.playMusic(0);
                        break;
                    case 1:
                        gp.changeGameState(gp.menuState);
                        gp.stopMusic();
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            }
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePress = true;
        }
        if (code == KeyEvent.VK_W) {
            upPress = true;
        }

        if (code == KeyEvent.VK_S) {
            downPress = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPress = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            spacePress = false;
        }

        if (code == KeyEvent.VK_W) {
            upPress = false;
        }

        if (code == KeyEvent.VK_S) {
            downPress = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPress = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPress = false;
        }
    }
}
