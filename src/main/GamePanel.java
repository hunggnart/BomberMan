package main;

import Bomb.BombManager;
import Entity.Player;
import Enemy.EnemyManager;
import Explode.Explode;
import Item.ItemManager;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    public final int scale = 3;
    public final int menuState = 0;
    public final int playState = 1;
    public final int endState = 2;
    public final int pauseState = 3;
    public  int gameState = menuState;
    public final int winPortal = 9;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 31;
    public final int maxScreenRow = 13;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int FPS = 60;
    KeyHandler keyH = new KeyHandler(this);
    Player player = new Player(this, keyH);
    public BombManager bombM = new BombManager(this);
    public EnemyManager enemyM = new EnemyManager(this);
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    Sound se = new Sound();
    Sound music = new Sound();
    UI ui=new UI(this);
    ItemManager itemC = new ItemManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Explode explode = new Explode(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void changeGameState(int newGameState) {
        gameState=newGameState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);

        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if(gameState==playState) {
            enemyM.enemiesUpdate();
            player.update();
            bombM.updateBombs();
            bombM.updateFlames();
            explode.updateBreaks();
            explode.updateEnd();
            itemC.updateItems();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState==playState) {
            ui.drawPlay(g2);
        }
        ui.draw(gameState,g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSe(int i) {
        se.setFile(i);
        se.play();
    }
}
