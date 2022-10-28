package Enemy;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pontan extends Enemy {
    private BufferedImage red_up, red_up1, red_up2, red_down, red_down1, red_down2,
            red_right, red_right1, red_right2, red_left, red_left1, red_left2;
    private int timeToChange, cl;

    public Pontan(GamePanel gp) {
        super(gp);
        setValue();
    }

    public void setValue() {
        speed = 1;
        timeToChange = 0;
        cl = 0;
    }

    @Override
    public void getEnemeImage() {
        try {
            left = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or5.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or4.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or5.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or5.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or4.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_or5.png"));
            red_left = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red2.png"));
            red_left1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red1.png"));
            red_left2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red5.png"));
            red_right = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red3.png"));
            red_right1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red4.png"));
            red_right2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red5.png"));
            red_up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red2.png"));
            red_up1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red1.png"));
            red_up2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red5.png"));
            red_down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red3.png"));
            red_down1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red4.png"));
            red_down2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pontan/pontan_red5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //Check Flame
        isDead = gp.cChecker.checkEntityVsFlame(this);
        if (isDead) {
            gp.explode.endInit(this.x, this.y);
        }
        //Check collision Tile
        collisionOn = false;
        gp.cChecker.checkTile(this);
        //Check bomb
        gp.cChecker.checkBombVsEnemy(this);
        //Make move
        move();
        //If collisionOn is false
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "right":
                    x += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
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

    private void move() {
        if (collisionOn) {
            direction = gp.findWayEnemy.FindWayForEnemy1((this.y + gp.tileSize / 2) / gp.tileSize,
                    (this.x + gp.tileSize / 2) / gp.tileSize);
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        timeToChange++;
        if (timeToChange == 120) {
            if (cl == 0) {
                cl = 1;
            } else if (cl == 1) {
                cl = 0;
            }
            timeToChange = 0;
        }
        if (cl == 1) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = red_up;
                    }
                    if (spriteNum == 2) {
                        image = red_up1;
                    }
                    if (spriteNum == 3) {
                        image = red_up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = red_down;
                    }
                    if (spriteNum == 2) {
                        image = red_down1;
                    }
                    if (spriteNum == 3) {
                        image = red_down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = red_left;
                    }
                    if (spriteNum == 2) {
                        image = red_left1;
                    }
                    if (spriteNum == 3) {
                        image = red_left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = red_right;
                    }
                    if (spriteNum == 2) {
                        image = red_right1;
                    }
                    if (spriteNum == 3) {
                        image = red_right2;
                    }
                    break;
            }
        } else {
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
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
