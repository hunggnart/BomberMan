package Item;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {
    boolean wasPass = false;
    BufferedImage image;
    GamePanel gp;

    public Item(GamePanel gp) {
        this.gp = gp;
        loadImage();
        solidArea = new Rectangle(1, 1, 15 * gp.scale, 15 * gp.scale);
        solidAreaDefaulX = solidArea.x;
        solidAreaDefaulY = solidArea.y;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        wasPass = true;
    }

    public void loadImage() {
        return;
    }
}
