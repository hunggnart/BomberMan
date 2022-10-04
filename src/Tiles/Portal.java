package Tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Portal extends Tile {
    BufferedImage image;
    boolean opened = false;
    GamePanel gp;

    public Portal(GamePanel gp) {
        this.gp = gp;
        this.conclusion = false;
        loadImage();
    }

    public void loadImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tile/portal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        if (opened) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            return;
        }
    }

    public void open() {
        this.opened = true;
    }
}
