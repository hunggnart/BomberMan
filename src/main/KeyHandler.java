package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPress, downPress, leftPress, rightPress, spacePress;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            if(gp.ui.optionNum>=0) {
                gp.ui.optionNum--;
            }if(gp.ui.optionNum==-1) {
                gp.ui.optionNum=2;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if(gp.ui.optionNum<=2) {
                gp.ui.optionNum++;
            }
            if(gp.ui.optionNum==3) {
            gp.ui.optionNum=0;
        }
        }
        if (code == KeyEvent.VK_ENTER){
            switch (gp.ui.optionNum) {
                case 0:
                    gp.changeGameState(gp.playState);
                    gp.playMusic(0);
                case 1:
                    //
                case 2:
                    //
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
