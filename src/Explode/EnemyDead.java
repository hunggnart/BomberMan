package Explode;

import Entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyDead extends Entity {
    GamePanel gp;
    BufferedImage dead, breakImage, breakImage1, breakImage2;
    public boolean stop = false;
    int countToStop, frameBreak = 0;

    public EnemyDead(GamePanel gp) {
        this.gp = gp;
        getBLDImage();
    }

    public void getBLDImage() {
        try {
            dead = ImageIO.read(getClass().getResourceAsStream("/Enemy/Balloon/balloom_dead.png"));
            breakImage = ImageIO.read(getClass().getResourceAsStream("/Enemy/Balloon/mob_dead1.png"));
            breakImage1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Balloon/mob_dead2.png"));
            breakImage2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Balloon/mob_dead3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBLD() {
        frameBreak++;
        if (frameBreak > 14) {
            countToStop++;
            frameBreak = 0;
            if (countToStop == 4) {
                stop = true;
                countToStop = 0;
            }
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            spriteNum++;
            if (spriteNum > 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void drawBLD(Graphics2D g2) {
        BufferedImage image = null;
        switch (spriteNum) {
//            case 0:
//                image = dead;
            case 1:
                image = breakImage;
                break;
            case 2:
                image = breakImage1;
                break;
            case 3:
                image = breakImage2;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
