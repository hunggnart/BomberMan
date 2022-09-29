package main;

import Entity.Entity;

public class CollisionChecker {

    public GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow  = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1 ;
        int tileNum2 ;

        switch (entity.direction){
            case "up":
                entityTopRow  = (entityTopWorldY - entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];

                if(gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow  = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];

                if(gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol  = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];

                if(gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol  = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];

                if(gp.tileM.tile[tileNum1].conclusion || gp.tileM.tile[tileNum2].conclusion){
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
