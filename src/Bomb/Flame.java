package Bomb;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Flame {

    BufferedImage
            explosion_horizontal, explosion_horizontal1, explosion_horizontal2,
            explosion_horizontal_left_last, explosion_horizontal_left_last1, explosion_horizontal_left_last2,
            explosion_horizontal_right_last, explosion_horizontal_right_last1, explosion_horizontal_right_last2,
            explosion_vertical, explosion_vertical1, explosion_vertical2,
            explosion_vertical_down_last, explosion_vertical_down_last1, explosion_vertical_down_last2,
            explosion_vertical_top_last, explosion_vertical_top_last1, explosion_vertical_top_last2;

    public int flameX, flameY;
    public int maxFlameSize = 4;
    public int frameCounter = 0;
    public int flameNum = 1;
    public static boolean isFlameStarted=false;
    public static boolean isFlameStop=false;
    GamePanel gp;

    public Flame(GamePanel gp) {
        this.gp = gp;
        getImage();
    }

    public void getImage() {
        try {
            explosion_horizontal = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal.png"));
            explosion_horizontal1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal1.png"));
            explosion_horizontal2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal2.png"));
            explosion_horizontal_left_last = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_left_last.png"));
            explosion_horizontal_left_last1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_left_last1.png"));
            explosion_horizontal_left_last2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_left_last2.png"));
            explosion_horizontal_right_last = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_right_last.png"));
            explosion_horizontal_right_last1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_right_last1.png"));
            explosion_horizontal_right_last2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_horizontal_right_last2.png"));
            explosion_vertical = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical.png"));
            explosion_vertical1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical1.png"));
            explosion_vertical2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical2.png"));
            explosion_vertical_down_last = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_down_last.png"));
            explosion_vertical_down_last1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_down_last1.png"));
            explosion_vertical_down_last2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_down_last2.png"));
            explosion_vertical_top_last = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_top_last.png"));
            explosion_vertical_top_last1 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_top_last1.png"));
            explosion_vertical_top_last2 = ImageIO.read(getClass().getResourceAsStream("/flame/explosion_vertical_top_last2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
            frameCounter++;
            if (frameCounter == 20) {
                flameNum++;
                frameCounter = 0;
                if (flameNum > 3) {
                    isFlameStarted=false;
                    isFlameStop=true;
            }
        }
    }

    public void draw(List<Flame> flames, Graphics2D g2) {
        BufferedImage image=null;
        for(int i=0;i<flames.size();i++) {
            Flame fl = flames.get(i);
            switch (flameNum) {
                case 1:image=fl.explosion_horizontal;break;
                case 2:image=fl.explosion_horizontal1;break;
                case 3:image=fl.explosion_horizontal2;break;
            }
            g2.drawImage(image,fl.flameX,fl.flameY,fl.gp.tileSize,fl.gp.tileSize,null);
        }
    }
}

