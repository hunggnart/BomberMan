package main;
import Enemy.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Info {
    BufferedImage background;
    int countFrame=0;
    public int score=0;
    public int max_time=150;
    Font font;
    GamePanel gp;


    public Info(GamePanel gp){
        this.gp=gp;
        font= new Font("Arial", Font.BOLD, 20);
        loadImage();
    }
    public void loadImage() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/info/iback.png"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        countFrame++;
        if(countFrame== gp.FPS&&max_time>0){
            max_time--;
            countFrame=0;
        }
    }
    public void draw(Graphics2D g2){

        int x=gp.screenWidth/6;
        String text;
        int length;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileSize));
        g2.setColor(Color.WHITE);
        text="Time:"+ max_time;
        g2.drawString(text,x,gp.screenHeight);
        length=getLength(text,g2);
        x=x+length+120;
        text="Score:"+ score;
        g2.drawString(text,x,gp.screenHeight);
        length=getLength(text,g2);
        x=x+length+120;
        text="Bombs:"+ gp.bombM.maxBombs;
        g2.drawString(text,x,gp.screenHeight);
    }
    public int getLength(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return length;
    }
}
