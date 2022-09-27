package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public int max_bombNum = 2;
    List<Bomb> bombs = new ArrayList<>();

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaulValues();
        getPlayerImage();
        solidArea = new Rectangle(5 * gp.scale, 7 * gp.scale, 6 * gp.scale, 7 * gp.scale);
        initBombs();
    }

    public void initBombs() {
        for(int i=0;i<max_bombNum;i++) {
            bombs.add(new Bomb(gp));
        }
    }

    public void setDefaulValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/player_up.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/player_left.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/player_right.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //
        if (keyH.leftPress || keyH.rightPress || keyH.upPress || keyH.downPress) {
            if (keyH.upPress) {
                direction = "up";
            } else if (keyH.downPress) {
                direction = "down";
            } else if (keyH.rightPress) {
                direction = "right";
            } else if (keyH.leftPress) {
                direction = "left";
            }

            //Check collision Tile
            collisionOn = false;
            gp.cChecker.checkTile(this);

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
            }

            spriteCounter++;
            if (spriteCounter > 2) {
                spriteNum++;
                if (spriteNum > 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        //place bomb
        if(keyH.spacePress) {
            placeBomb(bombs);
            keyH.spacePress=false;
        }
        //update bomb;
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).isPlaced) {
                bombs.get(i).update();
            }
        }
    }

    //Dat bom
    public void placeBomb(List<Bomb> bombs) {
        if (bombs != null) {
            for(int i=0;i<bombs.size();i++) {
                if(!bombs.get(i).isPlaced){
                    bombs.get(i).isPlaced = true;
                    bombs.get(i).bombX = ((this.x + gp.tileSize / 2) / gp.tileSize) * gp.tileSize;
                    bombs.get(i).bombY = ((this.y + gp.tileSize / 2) / gp.tileSize) * gp.tileSize;
                    break;
                }
            }
        }
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
        //draw player
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        //draw bombs
        for (int i = 0; i < max_bombNum; i++) {
            if (bombs != null && bombs.get(i).isPlaced) {
                bombs.get(i).draw(g2);
            }
        }
    }

}
