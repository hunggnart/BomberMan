package Enemy;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ovape extends Enemy {
    public Ovape(GamePanel gp) {
        super(gp);
        setValue();
    }

    public void setValue() {
        speed = 1;
    }

    @Override
    public void getEnemeImage() {
        try {
            left = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_left1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_left2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_left3.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_right1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_right2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_right3.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_left1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_left1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_left3.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_right1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_right2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Ovape/ovape_right3.png"));
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
        leftX = (this.x + 2) / gp.tileSize;
        upY = (this.y + 2) / gp.tileSize;
        rightX = ((this.x - 2) + gp.tileSize) / gp.tileSize;
        downY = ((this.y - 2) + gp.tileSize) / gp.tileSize;
        move();
        //If collisionOn is false
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

    private void move() {
        String way = gp.findWayEnemy.FindWayForEnemy3((this.y + gp.tileSize / 2) / gp.tileSize,
                (this.x + gp.tileSize / 2) / gp.tileSize);
        switch (direction) {
            case "up":
                if (upY != downY) {
                    direction = "up";
                    findNewWay = true;
                } else if (findNewWay) {
                    direction = way;
                    findNewWay = false;
                }
                break;
            case "down":
                if (upY != downY) {
                    direction = "down";
                    findNewWay = true;
                } else if (findNewWay) {
                    direction = way;
                    findNewWay = false;
                }
                break;
            case "left":
                if (leftX != rightX) {
                    direction = "left";
                    findNewWay = true;
                } else if (findNewWay) {
                    direction = way;
                    findNewWay = false;
                }
                break;
            case "right":
                if (leftX != rightX) {
                    direction = "right";
                    findNewWay = true;
                } else if (findNewWay) {
                    direction = way;
                    findNewWay = false;
                }
                break;
        }
    }
}
