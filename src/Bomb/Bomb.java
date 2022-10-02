package Bomb;

import Entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity {

    public boolean exploded = false;
    public boolean newBomb;
    int countToExplode, frameBomb = 0;
    BufferedImage bombImage, bombImage1, bombImage2;
    GamePanel gp;

    public Bomb(GamePanel gp) {
        this.gp = gp;
        getBombImage();
        setDefaulValueBomb();
        newBomb = true;
    }

    public void setDefaulValueBomb() {
        solidArea = new Rectangle(1, 1, 15 * gp.scale, 15 * gp.scale);
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaulY = solidArea.y;
    }

    public void getBombImage() {
        try {
            bombImage = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb.png"));
            bombImage1 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_1.png"));
            bombImage2 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (spriteNum) {
            case 1:
                image = bombImage;
                break;
            case 2:
                image = bombImage1;
                break;
            case 3:
                image = bombImage2;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        frameBomb++;
        if (frameBomb > 50) {
            countToExplode++;
            frameBomb = 0;
            if (countToExplode == 3) {
                exploded = true;
                countToExplode = 0;
            }
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}


