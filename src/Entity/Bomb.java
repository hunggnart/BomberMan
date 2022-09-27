package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity {
    int bombX, bombY;
    public boolean exploded = false;
    public boolean isPlaced = false;
    int countToExplode, frameBomb = 0;
    BufferedImage bombImage, bombImage1, bombImage2;
    GamePanel gp;

    public Bomb(GamePanel gp) {
        this.gp = gp;
        getBombImage();
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
            case 0:
                image = bombImage;
                break;
            case 1:
                image = bombImage1;
                break;
            case 2:
                image = bombImage2;
                break;
        }
        g2.drawImage(image, bombX, bombY, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        frameBomb++;
        if (frameBomb > 60) {
            countToExplode++;
            frameBomb = 0;
            if (countToExplode == 3) {
                exploded = true;
                countToExplode = 0;
            }
            if (exploded) {
                isPlaced = false;
                exploded = false;
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


