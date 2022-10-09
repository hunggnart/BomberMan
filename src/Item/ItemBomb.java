package Item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemBomb extends Item {
    public ItemBomb(GamePanel gp) {
        super(gp);
    }

    public void loadImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/Item/powerup_bombs.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
