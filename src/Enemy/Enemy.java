package Enemy;

import Entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends Entity {

    GamePanel gp;
    String[] directions;
    int directionCounter = 0;
    Random generator = new Random();
    int directionsNum = 0;
    public boolean isDead = false;
    boolean findNewWay;
    int leftX;
    int upY;
    int rightX;
    int downY;
    public BufferedImage up, up1, up2, left, left1, left2, right, right1, right2, down, down1, down2;

    public Enemy(GamePanel gp) {
        this.gp = gp;
        setDefaulValue();
        getEnemeImage();
    }

    public void setDefaulValue() {
        solidArea = new Rectangle(2 * gp.scale, 2 * gp.scale, 12 * gp.scale, 12 * gp.scale);
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaulY = solidArea.y;
        directionsNum = 0;
        directions = new String[4];
        directions[0] = "up";
        directions[1] = "down";
        directions[2] = "left";
        directions[3] = "right";
        direction = directions[directionsNum];
        findNewWay = false;
    }

    public void getEnemeImage() {
        return;
    }

    public void update() {
        return;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up;
                }
                if (spriteNum == 2) {
                    image = up1;
                }
                if (spriteNum == 3) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = down1;
                }
                if (spriteNum == 3) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left;
                }
                if (spriteNum == 2) {
                    image = left1;
                }
                if (spriteNum == 3) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right;
                }
                if (spriteNum == 2) {
                    image = right1;
                }
                if (spriteNum == 3) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
