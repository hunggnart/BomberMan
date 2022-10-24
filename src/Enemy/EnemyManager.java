package Enemy;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    GamePanel gp;

    public List<Enemy> enemies;

    int enemiesTotal;

    public EnemyManager(GamePanel gp) {
        this.gp = gp;
        enemies = new ArrayList<>();
        enemiesTotal = 0;
    }

    public void enemiesInit(int i, int j, String z) {
        Enemy e = new Enemy(gp);
        switch (z) {
            case "1":
                e = new Balloom(gp);
                break;
            case "2":
                e = new Oneal(gp);
                break;
            case "3":
                e = new Doll(gp);
                break;
            case "4":
                e = new Kondoria(gp);
                break;
            case "5":
                e = new Minvo(gp);
                break;
        }
        e.x = j * gp.tileSize;
        e.y = i * gp.tileSize;
        enemies.add(e);
        enemiesTotal++;
    }

    public void enemysRender(Graphics2D g2) {
        for (int i = 0; i < enemiesTotal; i++) {
            enemies.get(i).draw(g2);
        }
    }

    public void enemiesUpdate() {
        for (int i = 0; i < enemiesTotal; i++) {
            if (enemies.get(i).isDead) {
                enemies.remove(i);
                enemiesTotal--;
                if (enemies.size() == 0) {
                    return;
                }
                continue;
            }
            enemies.get(i).update();
        }
    }

}
