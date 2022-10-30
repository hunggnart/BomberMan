package Enemy;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Pass extends Enemy {
    public Pass(GamePanel gp) {
        super(gp);
        setValue();
    }

    public void setValue() {
        speed = 2;
    }

    public void getEnemeImage() {
        try {
            left = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_left1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_left2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_left3.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_right1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_right2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_right3.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_left1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_left2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_left3.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_right1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_right2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Enemy/Pass/pass_right3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        int isPursue = generator.nextInt(100);
        isDead = gp.cChecker.checkEntityVsFlame(this);
        //Check Flame
        leftX = (this.x + 2) / gp.tileSize;
        upY = (this.y + 2) / gp.tileSize;
        rightX = ((this.x - 2) + gp.tileSize) / gp.tileSize;
        downY = ((this.y - 2) + gp.tileSize) / gp.tileSize;
        if (isDead) {
            gp.explode.endInit(this.x, this.y);
        }
        //Check collision Tile
        collisionOn = false;
        gp.cChecker.checkTile(this);
        //Check bomb
        gp.cChecker.checkBombVsEnemy(this);
        //If collisionOn is false
        if (isPursue <= 80) {
            moveToPursue();
        } else {
            moveNotPursue();
        }
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

    private void moveNotPursue() {
        String way = gp.findWayEnemy.FindWayForEnemy1((this.y + gp.tileSize / 2) / gp.tileSize,
                (this.x + gp.tileSize / 2) / gp.tileSize);
        if (collisionOn) {
            direction = way;
        }
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

    private void moveToPursue() {
        if (collisionOn) {
            directionsNum = generator.nextInt(4);
            direction = directions[directionsNum];
            switch (direction) {
                case "up":
                    if (upY != downY) {
                        direction = "up";
                        findNewWay = true;
                    } else if (findNewWay) {
                        directionsNum = generator.nextInt(4);
                        direction = directions[directionsNum];
                        findNewWay = false;
                    }
                    break;
                case "down":
                    if (upY != downY) {
                        direction = "down";
                        findNewWay = true;
                    } else if (findNewWay) {
                        directionsNum = generator.nextInt(4);
                        direction = directions[directionsNum];
                        findNewWay = false;
                    }
                    break;
                case "left":
                    if (leftX != rightX) {
                        direction = "left";
                        findNewWay = true;
                    } else if (findNewWay) {
                        directionsNum = generator.nextInt(4);
                        direction = directions[directionsNum];
                        findNewWay = false;
                    }
                    break;
                case "right":
                    if (leftX != rightX) {
                        direction = "right";
                        findNewWay = true;
                    } else if (findNewWay) {
                        directionsNum = generator.nextInt(4);
                        direction = directions[directionsNum];
                        findNewWay = false;
                    }
                    break;
            }
        } else {
            String way = gp.findWayEnemy.FindWayForEnemy((this.y + gp.tileSize / 2) / gp.tileSize, (this.x + gp.tileSize / 2) / gp.tileSize);
            if (way.equals("none")) {
                switch (direction) {
                    case "up":
                        if (upY != downY) {
                            direction = "up";
                            findNewWay = true;
                        } else if (findNewWay) {
                            directionsNum = generator.nextInt(4);
                            direction = directions[directionsNum];
                            findNewWay = false;
                        }
                        break;
                    case "down":
                        if (upY != downY) {
                            direction = "down";
                            findNewWay = true;
                        } else if (findNewWay) {
                            directionsNum = generator.nextInt(4);
                            direction = directions[directionsNum];
                            findNewWay = false;
                        }
                        break;
                    case "left":
                        if (leftX != rightX) {
                            direction = "left";
                            findNewWay = true;
                        } else if (findNewWay) {
                            directionsNum = generator.nextInt(4);
                            direction = directions[directionsNum];
                            findNewWay = false;
                        }
                        break;
                    case "right":
                        if (leftX != rightX) {
                            direction = "right";
                            findNewWay = true;
                        } else if (findNewWay) {
                            directionsNum = generator.nextInt(4);
                            direction = directions[directionsNum];
                            findNewWay = false;
                        }
                        break;
                }
            } else {
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
    }
}
