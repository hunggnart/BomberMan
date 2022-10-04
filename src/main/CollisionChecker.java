package main;

import Bomb.Flame;
import Entity.Entity;
import Entity.Player;
import Enemy.Enemy;
import Item.ItemBomb;
import Item.ItemFlame;
import Item.ItemSpeed;

public class CollisionChecker {

    public GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];

                if (gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];

                if (gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];

                if (gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];

                if (gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkEnemy(Entity entity, boolean player) {
        for (int i = 0; i < gp.enemyM.enemies.size(); i++) {

            // get Entity solid area
            entity.solidArea.x = entity.x + entity.solidArea.x;
            entity.solidArea.y = entity.y + entity.solidArea.y;

            // get Enemy solid area

            gp.enemyM.enemies.get(i).solidArea.x = gp.enemyM.enemies.get(i).solidArea.x
                    + gp.enemyM.enemies.get(i).x;
            gp.enemyM.enemies.get(i).solidArea.y = gp.enemyM.enemies.get(i).solidArea.y
                    + gp.enemyM.enemies.get(i).y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.enemyM.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.enemyM.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.enemyM.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.enemyM.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
            }

            entity.solidArea.x = entity.solidAreaDefaulX;
            entity.solidArea.y = entity.solidAreaDefaulY;
            gp.enemyM.enemies.get(i).solidArea.x = gp.enemyM.enemies.get(i).solidAreaDefaulX;
            gp.enemyM.enemies.get(i).solidArea.y = gp.enemyM.enemies.get(i).solidAreaDefaulY;

        }
    }

    public void checkBombVsEnemy(Enemy entity) {
        for (int i = 0; i < gp.bombM.TotalBomb; i++) {

            // get Entity solid area
            entity.solidArea.x = entity.x + entity.solidArea.x;
            entity.solidArea.y = entity.y + entity.solidArea.y;

            // get Bomb solid area

            gp.bombM.bombs.get(i).solidArea.x = gp.bombM.bombs.get(i).solidArea.x
                    + gp.bombM.bombs.get(i).x;
            gp.bombM.bombs.get(i).solidArea.y = gp.bombM.bombs.get(i).solidArea.y
                    + gp.bombM.bombs.get(i).y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        entity.collisionOn = true;
                    }
                    break;
            }
            entity.solidArea.x = entity.solidAreaDefaulX;
            entity.solidArea.y = entity.solidAreaDefaulY;
            gp.bombM.bombs.get(i).solidArea.x = gp.bombM.bombs.get(i).solidAreaDefaulX;
            gp.bombM.bombs.get(i).solidArea.y = gp.bombM.bombs.get(i).solidAreaDefaulY;
        }
    }

    public void checkPlayerVsBomb(Player entity) {
        for (int i = 0; i < gp.bombM.TotalBomb; i++) {

            // get Entity solid area
            entity.solidArea.x = entity.x + entity.solidArea.x;
            entity.solidArea.y = entity.y + entity.solidArea.y;

            // get Bomb solid area

            gp.bombM.bombs.get(i).solidArea.x = gp.bombM.bombs.get(i).solidArea.x
                    + gp.bombM.bombs.get(i).x;
            gp.bombM.bombs.get(i).solidArea.y = gp.bombM.bombs.get(i).solidArea.y
                    + gp.bombM.bombs.get(i).y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        if (gp.bombM.bombs.get(i).newBomb) {
                            entity.collisionOn = false;
                        } else {
                            entity.collisionOn = true;
                        }
                    } else {
                        gp.bombM.bombs.get(i).newBomb = false;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        if (gp.bombM.bombs.get(i).newBomb) {
                            entity.collisionOn = false;
                        } else {
                            entity.collisionOn = true;
                        }
                    } else {
                        gp.bombM.bombs.get(i).newBomb = false;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        if (gp.bombM.bombs.get(i).newBomb) {
                            entity.collisionOn = false;
                        } else {
                            entity.collisionOn = true;
                        }
                    } else {
                        gp.bombM.bombs.get(i).newBomb = false;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.bombM.bombs.get(i).solidArea)) {
                        if (gp.bombM.bombs.get(i).newBomb) {
                            entity.collisionOn = false;
                        } else {
                            entity.collisionOn = true;
                        }

                    } else {
                        gp.bombM.bombs.get(i).newBomb = false;
                    }
                    break;
            }
            entity.solidArea.x = entity.solidAreaDefaulX;
            entity.solidArea.y = entity.solidAreaDefaulY;
            gp.bombM.bombs.get(i).solidArea.x = gp.bombM.bombs.get(i).solidAreaDefaulX;
            gp.bombM.bombs.get(i).solidArea.y = gp.bombM.bombs.get(i).solidAreaDefaulY;
        }
    }

    public int[] checkFlameVsTile(Flame flame) {
        int[] rs = new int[4];

        for (int i = 0; i < 4; i++) {
            rs[i] = 99;
        }

        for (int i = 0; i <= flame.flameLong; i++) {
            int rowRightFlame = flame.flameY / gp.tileSize;
            int colRightFlame = (flame.flameX + i * gp.tileSize) / gp.tileSize;
            int rowLeftFlame = flame.flameY / gp.tileSize;
            int colLeftFlame = (flame.flameX - i * gp.tileSize) / gp.tileSize;
            int rowUpFlame = (flame.flameY - i * gp.tileSize) / gp.tileSize;
            int colUpFlame = flame.flameX / gp.tileSize;
            int rowDownFlame = (flame.flameY + i * gp.tileSize) / gp.tileSize;
            int colDownFlame = flame.flameX / gp.tileSize;

            if (rowLeftFlame < 0) {
                rowLeftFlame = 0;
            }
            if (colLeftFlame < 0) {
                colLeftFlame = 0;
            }
            if (rowRightFlame > gp.maxScreenRow - 1) {
                rowRightFlame = gp.maxScreenRow - 1;
            }
            if (colRightFlame > gp.maxScreenCol - 1) {
                colRightFlame = gp.maxScreenCol - 1;
            }
            if (rowUpFlame < 0) {
                rowUpFlame = 0;
            }
            if (colUpFlame < 0) {
                colUpFlame = 0;
            }
            if (rowDownFlame > gp.maxScreenRow - 1) {
                rowDownFlame = gp.maxScreenRow - 1;
            }
            if (colDownFlame > gp.maxScreenCol - 1) {
                colDownFlame = gp.maxScreenCol - 1;
            }

            int tileNumUp = gp.tileM.mapTileNum[rowUpFlame][colUpFlame];
            int tileNumDown = gp.tileM.mapTileNum[rowDownFlame][colDownFlame];
            int tileNumRight = gp.tileM.mapTileNum[rowRightFlame][colRightFlame];
            int tileNumLeft = gp.tileM.mapTileNum[rowLeftFlame][colLeftFlame];

            if (gp.tileM.tile[tileNumUp].conclusion) {
                if (rs[0] == 99) {
                    rs[0] = i - 1;
                    if (gp.tileM.tile[tileNumUp].canBreak) {
                        if (tileNumUp == gp.winPortal) {
                            gp.tileM.portal.open();
                        }
                        gp.explode.breakInit(colUpFlame * gp.tileSize, rowUpFlame * gp.tileSize);
                        gp.itemC.initItems(colUpFlame * gp.tileSize, rowUpFlame * gp.tileSize);
                        gp.tileM.mapTileNum[rowUpFlame][colUpFlame] = 1;
                    }
                }
            }

            if (gp.tileM.tile[tileNumRight].conclusion) {
                if (rs[1] == 99) {
                    rs[1] = i - 1;
                    if (gp.tileM.tile[tileNumRight].canBreak) {
                        if (tileNumRight == gp.winPortal) {
                            gp.tileM.portal.open();
                        }
                        gp.explode.breakInit(colRightFlame * gp.tileSize, rowRightFlame * gp.tileSize);
                        gp.itemC.initItems(colRightFlame * gp.tileSize, rowRightFlame * gp.tileSize);
                        gp.tileM.mapTileNum[rowRightFlame][colRightFlame] = 1;
                    }
                }
            }

            if (gp.tileM.tile[tileNumDown].conclusion) {
                if (rs[2] == 99) {
                    rs[2] = i - 1;
                    if (gp.tileM.tile[tileNumDown].canBreak) {
                        if (tileNumDown == gp.winPortal) {
                            gp.tileM.portal.open();
                        }
                        gp.explode.breakInit(colDownFlame * gp.tileSize, rowDownFlame * gp.tileSize);
                        gp.itemC.initItems(colDownFlame * gp.tileSize, rowDownFlame * gp.tileSize);
                        gp.tileM.mapTileNum[rowDownFlame][colDownFlame] = 1;
                    }
                }
            }

            if (gp.tileM.tile[tileNumLeft].conclusion) {
                if (rs[3] == 99) {
                    rs[3] = i - 1;
                    if (gp.tileM.tile[tileNumLeft].canBreak) {
                        if (tileNumLeft == gp.winPortal) {
                            gp.tileM.portal.open();
                        }
                        gp.explode.breakInit(colLeftFlame * gp.tileSize, rowLeftFlame * gp.tileSize);
                        gp.tileM.mapTileNum[rowLeftFlame][colLeftFlame] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (rs[i] == 99) {
                rs[i] = flame.flameLong;
            }
        }

        return rs;
    }

    public Boolean checkEntityVsFlame(Entity enemy) {

        int colTopLeftPlayer = enemy.x / gp.tileSize;
        int rowTopLeftPlayer = enemy.y / gp.tileSize;
        int colTopRightPlayer = colTopLeftPlayer + 1;
        int rowTopRightPlayer = rowTopLeftPlayer;
        int colBottomLeftPlayer = colTopLeftPlayer;
        int rowBottomLeftPlayer = rowTopLeftPlayer + 1;
        int colBottomRightPlayer = colTopLeftPlayer + 1;
        int rowBottomRightPlayer = rowTopLeftPlayer + 1;

        for (int i = 0; i < gp.bombM.flames.size(); i++) {

            for (int j = 1; j <= gp.bombM.flames.get(i).check[0]; j++) {
                int rowUpFlame = (gp.bombM.flames.get(i).flameY - j * gp.tileSize) / gp.tileSize;
                int colUpFlame = gp.bombM.flames.get(i).flameX / gp.tileSize;

                if (colTopLeftPlayer == colUpFlame && rowTopLeftPlayer == rowUpFlame) {
                    return true;
                }
                if (colTopRightPlayer == colUpFlame && rowTopRightPlayer == rowUpFlame) {
                    return true;
                }
                if (colBottomLeftPlayer == colUpFlame && rowBottomLeftPlayer == rowUpFlame) {
                    return true;
                }
                if (colBottomRightPlayer == colUpFlame && rowBottomRightPlayer == rowUpFlame) {
                    return true;
                }
            }

            for (int j = 1; j <= gp.bombM.flames.get(i).check[1]; j++) {
                int rowRightFlame = gp.bombM.flames.get(i).flameY / gp.tileSize;
                int colRightFlame = (gp.bombM.flames.get(i).flameX + j * gp.tileSize) / gp.tileSize;

                if (colTopLeftPlayer == colRightFlame && rowTopLeftPlayer == rowRightFlame) {
                    return true;
                }
                if (colTopRightPlayer == colRightFlame && rowTopRightPlayer == rowRightFlame) {
                    return true;
                }
                if (colBottomLeftPlayer == colRightFlame && rowBottomLeftPlayer == rowRightFlame) {
                    return true;
                }
                if (colBottomRightPlayer == colRightFlame && rowBottomRightPlayer == rowRightFlame) {
                    return true;
                }
            }

            for (int j = 1; j <= gp.bombM.flames.get(i).check[2]; j++) {
                int rowDownFlame = (gp.bombM.flames.get(i).flameY + j * gp.tileSize) / gp.tileSize;
                int colDownFlame = gp.bombM.flames.get(i).flameX / gp.tileSize;

                if (colTopLeftPlayer == colDownFlame && rowTopLeftPlayer == rowDownFlame) {
                    return true;
                }
                if (colTopRightPlayer == colDownFlame && rowTopRightPlayer == rowDownFlame) {
                    return true;
                }
                if (colBottomLeftPlayer == colDownFlame && rowBottomLeftPlayer == rowDownFlame) {
                    return true;
                }
                if (colBottomRightPlayer == colDownFlame && rowBottomRightPlayer == rowDownFlame) {
                    return true;
                }
            }

            for (int j = 1; j <= gp.bombM.flames.get(i).check[3]; j++) {
                int rowLeftFlame = gp.bombM.flames.get(i).flameY / gp.tileSize;
                int colLeftFlame = (gp.bombM.flames.get(i).flameX - j * gp.tileSize) / gp.tileSize;

                if (colTopLeftPlayer == colLeftFlame && rowTopLeftPlayer == rowLeftFlame) {
                    return true;
                }
                if (colTopRightPlayer == colLeftFlame && rowTopRightPlayer == rowLeftFlame) {
                    return true;
                }
                if (colBottomLeftPlayer == colLeftFlame && rowBottomLeftPlayer == rowLeftFlame) {
                    return true;
                }
                if (colBottomRightPlayer == colLeftFlame && rowBottomRightPlayer == rowLeftFlame) {
                    return true;
                }
            }
        }
        return false;
    }

    public void CheckPlayerVsItem(Player entity) {
        for (int i = 0; i < gp.itemC.items.size(); i++) {

            // get Entity solid area
            entity.solidArea.x = entity.x + entity.solidArea.x;
            entity.solidArea.y = entity.y + entity.solidArea.y;

            // get Bomb solid area

            gp.itemC.items.get(i).solidArea.x = gp.itemC.items.get(i).solidArea.x
                    + gp.itemC.items.get(i).x;
            gp.itemC.items.get(i).solidArea.y = gp.itemC.items.get(i).solidArea.y
                    + gp.itemC.items.get(i).y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.itemC.items.get(i).solidArea)) {
                        if (gp.itemC.items.get(i) instanceof ItemBomb) {
                            gp.bombM.maxBombs++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemFlame) {
                            gp.bombM.flameLong++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemSpeed) {
                            entity.speed++;
                            gp.itemC.items.get(i).update();
                        }
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.itemC.items.get(i).solidArea)) {
                        if (gp.itemC.items.get(i) instanceof ItemBomb) {
                            gp.bombM.maxBombs++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemFlame) {
                            gp.bombM.flameLong++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemSpeed) {
                            entity.speed++;
                            gp.itemC.items.get(i).update();
                        }
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.itemC.items.get(i).solidArea)) {
                        if (gp.itemC.items.get(i) instanceof ItemBomb) {
                            gp.bombM.maxBombs++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemFlame) {
                            gp.bombM.flameLong++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemSpeed) {
                            entity.speed++;
                            gp.itemC.items.get(i).update();
                        }
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.itemC.items.get(i).solidArea)) {
                        if (gp.itemC.items.get(i) instanceof ItemBomb) {
                            gp.bombM.maxBombs++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemFlame) {
                            gp.bombM.flameLong++;
                            gp.itemC.items.get(i).update();
                        }
                        if (gp.itemC.items.get(i) instanceof ItemSpeed) {
                            entity.speed++;
                            gp.itemC.items.get(i).update();
                        }
                    }
                    break;
            }
            entity.solidArea.x = entity.solidAreaDefaulX;
            entity.solidArea.y = entity.solidAreaDefaulY;
            gp.itemC.items.get(i).solidArea.x = gp.itemC.items.get(i).solidAreaDefaulX;
            gp.itemC.items.get(i).solidArea.y = gp.itemC.items.get(i).solidAreaDefaulY;
        }
    }

}
