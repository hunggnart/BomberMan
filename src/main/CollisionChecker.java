package main;

import Entity.Entity;

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

    public int CheckEnemy(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.enemyC.enemies.size(); i++) {

            // get Entity solid area
            entity.solidArea.x = entity.x + entity.solidArea.x;
            entity.solidArea.y = entity.y + entity.solidArea.y;

            // get Enemy solid area

            gp.enemyC.enemies.get(i).solidArea.x = gp.enemyC.enemies.get(i).solidArea.x
                    + gp.enemyC.enemies.get(i).x;
            gp.enemyC.enemies.get(i).solidArea.y = gp.enemyC.enemies.get(i).solidArea.y
                    + gp.enemyC.enemies.get(i).y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.enemyC.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.enemyC.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.enemyC.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.enemyC.enemies.get(i).solidArea)) {
                        entity.collisionOn = true;
                        System.out.println("Game over!");
                    }
                    break;
            }
            entity.solidArea.x = entity.solidAreaDefaulX;
            entity.solidArea.y = entity.solidAreaDefaulY;
            gp.enemyC.enemies.get(i).solidArea.x = gp.enemyC.enemies.get(i).solidAreaDefaulX;
            gp.enemyC.enemies.get(i).solidArea.y = gp.enemyC.enemies.get(i).solidAreaDefaulY;

        }
        return index;
    }
}
