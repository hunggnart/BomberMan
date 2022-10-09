package Bomb;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BombManager {
    GamePanel gp;
    public List<Bomb> bombs ;
    public List<Flame> flames;
    public int maxBombs = 5;
    public int flameLong = 1;

    public int TotalBomb = 0;
    public int TotalFlame = 0;

    public BombManager(GamePanel gp) {
        this.gp = gp;
        bombs = new ArrayList<>();
        flames = new ArrayList<>();
    }

    public void bombInit(int x, int y) {
        if (TotalBomb == maxBombs) {
            return;
        }
        Bomb bomb = new Bomb(gp);
        bomb.x = ((x + gp.tileSize / 2) / gp.tileSize) * gp.tileSize;
        bomb.y = ((y + gp.tileSize / 2) / gp.tileSize) * gp.tileSize;
        bombs.add(bomb);
        TotalBomb++;
    }

    public void flameInit(int x, int y) {
        Flame f = new Flame(gp, flameLong);
        f.flameX = x;
        f.flameY = y;
        f.flame_right_x = x + gp.tileSize * f.flameLong;
        f.flame_right_y = y;
        f.flame_up_x = x;
        f.flame_up_y = y - gp.tileSize * f.flameLong;
        f.flame_left_x = x - gp.tileSize * f.flameLong;
        f.flame_left_y = y;
        f.flame_down_x = x;
        f.flame_down_y = y + gp.tileSize * f.flameLong;
        f.check = gp.cChecker.checkFlameVsTile(f);
        flames.add(f);
        TotalFlame++;
    }

    public void drawBombs(Graphics2D g2) {
        for (int i = 0; i < TotalBomb; i++) {
            bombs.get(i).draw(g2);
        }
    }

    public void drawFlames(Graphics2D g2) {
        for (int i = 0; i < TotalFlame; i++) {
            flames.get(i).draw(g2);
        }
    }

    public void updateBombs() {
        for (int i = 0; i < TotalBomb; i++) {
            if (bombs.get(i).exploded) {
                gp.playSe(5);
                flameInit(bombs.get(i).x, bombs.get(i).y);
                bombs.remove(i);
                TotalBomb--;
                if (TotalBomb == 0) {
                    return;
                }
                continue;
            }
            bombs.get(i).update();
        }
    }

    public void updateFlames() {
        for (int i = 0; i < TotalFlame; i++) {
            if (flames.get(i).flameStop) {
                flames.remove(i);
                TotalFlame--;
                if (TotalFlame == 0) {
                    return;
                }
            }
            flames.get(i).update();
        }
    }
}
