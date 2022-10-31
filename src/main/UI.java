package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Font arial;
    public int menuNum = 0;
    public int pauseNum = 0;
    public int overNum = 0;
    public int countFrame = 0;
    public int countNum = 0;
    BufferedImage image1, image2, image3;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.BOLD, 40);
        loadImage();
    }

    public void loadImage() {
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/ui/player_right.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/ui/player_right_1.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/ui/player_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void reset() {
        menuNum = 0;
        pauseNum = 0;
        overNum = 0;
    }

    public void update() {
        countFrame++;
        if (countFrame == 20) {
            countFrame = 0;
            countNum++;
            {
                if (countNum > 2) {
                    countNum = 0;
                }
            }
        }
    }

    public void draw(int gameState, Graphics2D g2) {
        switch (gameState) {

            case 0:
                drawMenu(g2);
                break;
            case 1:
                drawPlay(g2);
                break;
            case 2:
                drawEnd(g2);
                break;
            case 3:
                drawPause(g2);
                break;
        }
        if (gp.gameState == gp.winState) {
            drawWinStage(g2);
        }

    }

    public void drawMenu(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage icon = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ui/back2.png"));
            icon = ImageIO.read(getClass().getResourceAsStream("/ui/tri1.png"));
        } catch (IOException i) {
            i.printStackTrace();
        }
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);

        String text;
        int x, y;
        g2.setColor(Color.black);
        //Menu Option
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileSize));
        x = 4 * gp.tileSize;
        y = gp.screenHeight / 2 + 20;
        //NewGame
        text = "New Game";
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize / 2 - 3, gp.tileSize / 2, gp.tileSize / 2, null);
        }
        //Option
        text = "Option";
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize / 2 - 3, gp.tileSize / 2, gp.tileSize / 2, null);
        }

        //Exit
        text = "Exit";
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize / 2 - 3, gp.tileSize / 2, gp.tileSize / 2, null);
        }

    }


    public void drawPlay(Graphics2D g2) {
        gp.tileM.draw(g2);
        gp.bombM.drawBombs(g2);
        gp.bombM.drawFlames(g2);
        gp.itemC.drawItem(g2);
        gp.player.draw(g2);
        gp.enemyM.enemysRender(g2);
        gp.explode.drawBreaks(g2);
        gp.explode.drawEnd(g2);
    }

    public void drawPause(Graphics2D g2) {
        //Resume
        String text;
        int x, y;
        BufferedImage image = null;
        BufferedImage icon = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ui/pause2.png"));
            icon = ImageIO.read(getClass().getResourceAsStream("/ui/tri1.png"));
        } catch (IOException i) {
            i.printStackTrace();
        }

        x = gp.screenWidth / 2;
        y = gp.screenHeight / 2;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

        g2.drawImage(image, -20, 0, gp.screenWidth, gp.screenHeight, null);
        text = "Pause";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setColor(Color.black);
        g2.drawString(text, getXCenter(text, g2), 90);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        //Resume
        text = "Resume";
        x = getXCenter(text, g2);
        y += gp.tileSize;
        if (pauseNum == 0) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        }
        g2.drawString(text, x, y);

        //Exit
        text = "Menu";
        x = getXCenter(text, g2);
        y += 2 * gp.tileSize;
        if (pauseNum == 1) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        }
        g2.drawString(text, x, y);

    }

    public void drawEnd(Graphics2D g2) {
        String text = null;
        int x, y;
        BufferedImage image = null;
        BufferedImage icon = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ui/over.png"));
            icon = ImageIO.read(getClass().getResourceAsStream("/ui/tri4.png"));
        } catch (IOException i) {
            i.printStackTrace();
        }

        x = gp.screenWidth / 2;
        y = gp.screenHeight / 2;

        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);

        y += 20;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        text = "Your score:";
        g2.setColor(Color.red);
        g2.drawString(text + Integer.toString(gp.info.score), getXCenter(text, g2), y);

        g2.setColor(Color.yellow);
        //Replay
        text = "Replay";
        x = getXCenter(text, g2);
        y += gp.tileSize;
        if (overNum == 0) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize + 12, gp.tileSize / 2, gp.tileSize / 2, null);
        }
        g2.drawString(text, x, y);
        //return meu
        text = "Menu";
        x = getXCenter(text, g2);
        y += gp.tileSize;
        if (overNum == 1) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize + 32, gp.tileSize / 2, gp.tileSize / 2, null);
        }
        g2.drawString(text, x, y + 15);

        //Exit
        text = "Exit";
        x = getXCenter(text, g2);
        y += 2 * gp.tileSize;
        if (overNum == 2) {
            g2.drawImage(icon, x - gp.tileSize, y - gp.tileSize + 12, gp.tileSize / 2, gp.tileSize / 2, null);
        }
        g2.drawString(text, x, y);

    }

    public void drawWinStage(Graphics2D g2) {
        BufferedImage image = null;
        switch (countNum) {
            case 0:
                image = image1;
                break;
            case 1:
                image = image2;
                break;
            case 2:
                image = image3;
                break;

        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.drawImage(image, gp.screenWidth / 2, gp.screenHeight / 2, gp.tileSize, gp.tileSize, null);
        String text;
        text = "Congratulation! Press enter to continue...";
        g2.setColor(Color.YELLOW);
        g2.drawString(text, getXCenter(text, g2), gp.screenHeight / 4);
        text="=========================================>";
        g2.drawString(text,getXCenter(text,g2),gp.screenHeight/2+60);
    }


    public int getXCenter(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

}
