package Item;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager {
    Random generator = new Random();
    GamePanel gp;
    public List<Item> items;

    public ItemManager(GamePanel gp) {
        this.gp = gp;
        items = new ArrayList<Item>();
    }

    public void updateItems() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).wasPass) {
                items.remove(i);
                if (items.size() == 0) {
                    return;
                }
            }
        }
    }

    public void drawItem(Graphics2D g2) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(g2);
        }
    }

    public void initItems(int x, int y) {
        int luckyItem = generator.nextInt(100);
        if (luckyItem <= 10) {
            Item item = new ItemSpeed(gp);
            item.x = x;
            item.y = y;
            items.add(item);
            return;
        }
        if (luckyItem <= 20) {
            Item item = new ItemFlame(gp);
            item.x = x;
            item.y = y;
            items.add(item);
            return;
        }
        if (luckyItem <= 30) {
            Item item = new ItemBomb(gp);
            item.x = x;
            item.y = y;
            items.add(item);
        }
    }
}
