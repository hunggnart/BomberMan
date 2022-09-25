package Tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;

    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap();
    }

    public void loadMap() {
        try {
            String mapTileMark;
            File myFile = new File("res/level/Level1.txt");
            Scanner myReader = new Scanner(myFile);
            for (int i = 0; i < gp.maxScreenRow; i++) {
                String data = myReader.nextLine();
                for (int j = 0; j < gp.maxScreenCol; j++) {
                    mapTileMark = String.valueOf(data.charAt(j));

                    switch (mapTileMark) {
                        case "#":
                            mapTileNum[i][j] = 3;
                            break;
                        case "*":
                            mapTileNum[i][j] = 0;
                            break;
                        case "x":
                            mapTileNum[i][j] = 2;
                            break;
                        case " ":
                            mapTileNum[i][j] = 1;
                            break;

                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].img = ImageIO.read(getClass().getResourceAsStream("/tile/brick.png"));
            tile[0].conclusion = true;
            tile[1] = new Tile();
            tile[1].img = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
            tile[1].conclusion = false;
            tile[2] = new Tile();
            tile[2].img = ImageIO.read(getClass().getResourceAsStream("/tile/portal.png"));
            tile[2].conclusion = false;
            tile[3] = new Tile();
            tile[3].img = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
            tile[3].conclusion = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
//        g2.drawImage(tile[0].img, 0 , 0, gp.tileSize, gp.tileSize, null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            g2.drawImage(tile[mapTileNum[row][col]].img, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
