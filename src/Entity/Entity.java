package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public String direction;

    public int spriteCounter = 0;

    public int spriteNum = 1;

    public Rectangle solidArea;

    public int solidAreaDefaulX, solidAreaDefaulY;

    public boolean collisionOn = false;
}
