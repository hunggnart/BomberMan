package Bomb;

import Explode.Break;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoomContronler {
    GamePanel gp;
    public List<Bomb> bombs = new ArrayList<Bomb>();
    public List<Flame> flames = new ArrayList<Flame>();
    public int maxBombs = 1;
    public int flameLong = 1;

    public int bombsToltal = 0;
    public int flamesToltal = 0;

    public BoomContronler(GamePanel gp) {
        this.gp = gp;
        bombs = new ArrayList<Bomb>();
        flames = new ArrayList<Flame>();
    }

    public void bombInit(int x, int y) {
        if (bombsToltal == maxBombs) {
            return;
        }
        Bomb bomb = new Bomb(gp);
        bomb.x = ((x + gp.tileSize / 2) / gp.tileSize) * gp.tileSize;
        bomb.y = ((y + gp.tileSize / 2) / gp.tileSize) * gp.tileSize;
        bombs.add(bomb);
        bombsToltal++;
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
        flamesToltal++;
    }

    public void drawBombs(Graphics2D g2) {
        for (int i = 0; i < bombsToltal; i++) {
            bombs.get(i).draw(g2);
        }
    }

    public void drawFlames(Graphics2D g2) {
        for (int i = 0; i < flamesToltal; i++) {
            flames.get(i).draw(g2);
        }
    }

    public void updateBombs() {
        for (int i = 0; i < bombsToltal; i++) {
            if (bombs.get(i).exploded) {
                flameInit(bombs.get(i).x, bombs.get(i).y);
                bombs.remove(i);
                bombsToltal--;
                if (bombsToltal == 0) {
                    return;
                }
            }
            bombs.get(i).update();
        }
    }

    public void updateFlames() {
        for (int i = 0; i < flamesToltal; i++) {
            if (flames.get(i).flameStop) {
                flames.remove(i);
                flamesToltal--;
                if (flamesToltal == 0) {
                    return;
                }
            }
            flames.get(i).update();
        }
    }
}
