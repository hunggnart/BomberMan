package Explode;

import Entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Break extends Entity {
    GamePanel gp;
    BufferedImage breakImage, breakImage1, breakImage2;
    public boolean stop = false;
    int countToStop, frameBreak = 0;

    public Break(GamePanel gp){
        this.gp = gp;
        getBreakImage();
    }

    public void getBreakImage(){
        try {
            breakImage = ImageIO.read(getClass().getResourceAsStream("/Break/brick_exploded.png"));
            breakImage1 = ImageIO.read(getClass().getResourceAsStream("/Break/brick_exploded1.png"));
            breakImage2 = ImageIO.read(getClass().getResourceAsStream("/Break/brick_exploded2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        frameBreak++;
        if (frameBreak > 14) {
            countToStop++;
            frameBreak = 0;
            if (countToStop == 3) {
                stop = true;
                countToStop = 0;
            }
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void drawBreak(Graphics2D g2) {
        BufferedImage image = null;
        switch (spriteNum) {
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
