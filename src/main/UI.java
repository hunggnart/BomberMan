package main;

import javax.xml.soap.Text;
import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial;
    public int optionNum =0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.BOLD, 40);
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
    }

    public void drawMenu(Graphics2D g2) {
        //Title
        String text = "BomberMan";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        int x = getCenterX(text,g2);
        int y = gp.screenHeight / 4;
        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text,x+4,y+4);
        //Color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        //Menu Option
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        x=gp.screenWidth/2;
        y=gp.screenHeight/2;
        //NewGame
        text="New Game";
        x= getCenterX(text,g2);
        y+=2*gp.tileSize;
        if(optionNum==0) {
            g2.drawString(">",x-gp.tileSize,y);
        }
        g2.drawString(text,x,y);
        //Option
        text = "Option";
        x= getCenterX(text,g2);
        y+=2*gp.tileSize;
        if(optionNum==1) {
            g2.drawString(">",x-gp.tileSize,y);
        }
        g2.drawString(text,x,y);
        //Exit
        text = "Exit";
        x= getCenterX(text,g2);
        y+=2*gp.tileSize;
        if(optionNum==2) {
            g2.drawString(">",x-gp.tileSize,y);
        }
        g2.drawString(text,x,y);

    }

    public void drawPlay(Graphics2D g2) {

        gp.tileM.draw(g2);
        gp.bombM.drawBombs(g2);
        gp.bombM.drawFlames(g2);
        gp.player.draw(g2);
        gp.enemyM.enemysRender(g2);
        gp.explode.drawBreaks(g2);
        gp.explode.drawEnd(g2);
    }

    public void drawPause(Graphics2D g2) {

    }

    public void drawEnd(Graphics2D g2) {

    }

    public int getCenterX(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2-length/2;
    }

}
