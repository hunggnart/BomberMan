package Enemy;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Minvo extends Enemy {
    public Minvo(GamePanel gp) {
        super(gp);
        setValue();
    }

    public void setValue() {
        speed = 1;
    }
    @Override
    public void getEnemeImage() {
        try {
            left = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_left1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_left2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_left3.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_right1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_right2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_right3.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_left1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_left1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_left1.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_right1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_right1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Minvo/minvo_right1.png"));
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
        //If collisionOn is true
        if (collisionOn) {
            directionsNum = generator.nextInt(4) + 0;
            direction = directions[directionsNum];
        }
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
}
