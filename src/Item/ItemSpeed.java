package Item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemSpeed extends Item{
    public ItemSpeed(GamePanel gp) {
        super(gp);
    }

    public void loadImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/Item/powerup_speed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
