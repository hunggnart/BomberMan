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
        font= new Font("Arial", Font.BOLD, 30);
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

       g2.drawImage(background,2*gp.tileSize,0,10*gp.tileSize,gp.tileSize,null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileSize));
        g2.setColor(Color.YELLOW);
        g2.drawString("Time:"+ max_time,2*gp.tileSize+2,27);
        g2.drawString("Score:"+ score,8* gp.tileSize,27);
    }
}
