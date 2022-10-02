package Bomb;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Flame {

    BufferedImage flame1, flame2, flame3,
            flameUp1, flameUp2, flameUp3,
            flameDown1, flameDown2, flameDown3,
            flameRight1, flameRight2, flameRight3,
            flameLeft1, flameLeft2, flameLeft3,
            flameMidUp1, flameMidUp2, flameMidUp3,
            flameMidRight1, flameMidRight2, flameMidRight3;

    public int flameX, flameY, flame_up_x, flame_up_y, flame_down_y, flame_down_x,
            flame_right_x, flame_right_y, flame_left_x, flame_left_y;
    public int frameCounter = 0;
    public int flameNum = 1;
    public int flameLong = 2;
    public boolean flameStop = false;

    public int[] check;
    GamePanel gp;

    public Flame(GamePanel gp) {
        this.gp = gp;
        getImage();
    }

    public void getImage() {
        try {
            flame1 = ImageIO.read(getClass().getResourceAsStream("/flame/bomb_exploded.png"));
            flame2 = ImageIO.read(getClass().getResourceAsStream("/flame/bomb_exploded1.png"));
            flame3 = ImageIO.read(getClass().getResourceAsStream("/flame/bomb_exploded2.png"));
            flameMidRight1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal.png"));
            flameMidRight2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal1.png"));
            flameMidRight3 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal2.png"));
            flameRight1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_right_last.png"));
            flameRight2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_right_last1.png"));
            flameRight3 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_right_last2.png"));
            flameLeft1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_left_last.png"));
            flameLeft2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_left_last1.png"));
            flameLeft3 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_left_last2.png"));
            flameMidUp1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical.png"));
            flameMidUp2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical1.png"));
            flameMidUp3 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical2.png"));
            flameDown1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_down_last.png"));
            flameDown2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_down_last1.png"));
            flameDown3 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_down_last2.png"));
            flameUp1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_top_last.png"));
            flameUp2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_top_last1.png"));
            flameUp3 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_top_last2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        frameCounter++;
        if (frameCounter == 10) {
            flameNum++;
            frameCounter = 0;
            if (flameNum > 3) {
                flameStop = true;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int drawUpNum, drawDownNum, drawRightNum, drawLeftNum;
        drawUpNum = check[0];
        drawRightNum = check[1];
        drawDownNum = check[2];
        drawLeftNum = check[3];
        BufferedImage imagesMidUp = null;
        BufferedImage imagesMidRight = null;
        BufferedImage image1 = null,
                image2 = null, image3 = null,
                image4 = null, image = null;
        switch (flameNum) {
            case 1:
                image = flame1;
                image1 = flameUp1;
                image2 = flameRight1;
                image3 = flameDown1;
                image4 = flameLeft1;
                imagesMidUp = flameMidUp1;
                imagesMidRight = flameMidRight1;
                break;
            case 2:
                image = flame2;
                image1 = flameUp2;
                image2 = flameRight2;
                image3 = flameDown2;
                image4 = flameLeft2;
                imagesMidUp = flameMidUp2;
                imagesMidRight = flameMidRight2;
                break;
            case 3:
                image = flame3;
                image1 = flameUp3;
                image2 = flameRight3;
                image3 = flameDown3;
                image4 = flameLeft3;
                imagesMidUp = flameMidUp3;
                imagesMidRight = flameMidRight3;
                break;
        }
        g2.drawImage(image, flameX, flameY, gp.tileSize, gp.tileSize, null);
        drawUp(drawUpNum, image1, imagesMidUp, g2);
        drawRight(drawRightNum, image2, imagesMidRight, g2);
        drawDown(drawDownNum, image3, imagesMidUp, g2);
        drawLeft(drawLeftNum, image4, imagesMidRight, g2);
    }

    private void drawUp(int longFlame, BufferedImage image1, BufferedImage imagesMidUp, Graphics2D g2) {
        if (longFlame < flameLong) {
            for (int i = 1; i <= longFlame; i++) {
                g2.drawImage(imagesMidUp, flameX, flameY - i * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
        } else {
            for (int i = 1; i < flameLong; i++) {
                g2.drawImage(imagesMidUp, flameX, flameY - i * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(image1, flame_up_x, flame_up_y, gp.tileSize, gp.tileSize, null);
        }
    }

    private void drawRight(int longFlame, BufferedImage image2, BufferedImage imagesMidUp, Graphics2D g2) {
        if (longFlame < flameLong) {
            for (int i = 1; i <= longFlame; i++) {
                g2.drawImage(imagesMidUp, flameX + i * gp.tileSize, flameY, gp.tileSize, gp.tileSize, null);
            }
        } else {
            for (int i = 1; i < flameLong; i++) {
                g2.drawImage(imagesMidUp, flameX + i * gp.tileSize, flameY, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(image2, flame_right_x, flame_right_y, gp.tileSize, gp.tileSize, null);
        }
    }

    private void drawDown(int longFlame, BufferedImage image3, BufferedImage imagesMidUp, Graphics2D g2) {
        if (flameLong > longFlame) {
            for (int i = 1; i <= longFlame; i++) {
                g2.drawImage(imagesMidUp, flameX, flameY + i * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
        } else {
            for (int i = 1; i < flameLong; i++) {
                g2.drawImage(imagesMidUp, flameX, flameY + i * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(image3, flame_down_x, flame_down_y, gp.tileSize, gp.tileSize, null);
        }
    }

    private void drawLeft(int longFlame, BufferedImage image4, BufferedImage imagesMidUp, Graphics2D g2) {
        if (longFlame < flameLong) {
            for (int i = 1; i <= longFlame; i++) {
                g2.drawImage(imagesMidUp, flameX - i * gp.tileSize, flameY, gp.tileSize, gp.tileSize, null);
            }
        } else {
            for (int i = 1; i < flameLong; i++) {
                g2.drawImage(imagesMidUp, flameX - i * gp.tileSize, flameY, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(image4, flame_left_x, flame_left_y, gp.tileSize, gp.tileSize, null);
        }
    }
}

