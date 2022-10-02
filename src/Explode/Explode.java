package Explode;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explode {

    GamePanel gp;
    List<Break> breaks = new ArrayList<Break>();
    List<EnemyDead> enemyDeads = new ArrayList<EnemyDead>();

    public Explode(GamePanel gp) {
        this.gp = gp;
    }

    public void breakInit(int x, int y) {
        Break br = new Break(gp);
        br.x = x;
        br.y = y;
        breaks.add(br);
    }

    public void endInit(int x, int y) {
        EnemyDead bld = new EnemyDead(gp);
        bld.x = x;
        bld.y = y;
        enemyDeads.add(bld);
    }

    public void updateBreaks() {
        for (int i = 0; i < breaks.size(); i++) {
            if (breaks.get(i).stop) {
                breaks.remove(i);
                if (breaks.size() == 0 || breaks.size() == 1) {
                    return;
                }
            }
            breaks.get(i).update();
        }
    }

    public void drawBreaks(Graphics2D g2) {
        for (int i = 0; i < breaks.size(); i++) {
            breaks.get(i).drawBreak(g2);
        }
    }

    public void updateEnd() {
        for (int i = 0; i < enemyDeads.size(); i++) {
            if (enemyDeads.get(i).stop) {
                enemyDeads.remove(i);
                if (enemyDeads.size() == 0 /* || balloomDeads.size() == 1 */) {
                    return;
                }
                continue;
            }
            enemyDeads.get(i).updateBLD();
        }
    }

    public void drawEnd(Graphics2D g2) {
        for (int i = 0; i < enemyDeads.size(); i++) {
            enemyDeads.get(i).drawBLD(g2);
        }
    }
}
