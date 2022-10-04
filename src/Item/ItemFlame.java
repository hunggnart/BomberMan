package Item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemFlame extends Item {
    public ItemFlame(GamePanel gp) {
        super(gp);
    }

    public void loadImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/Item/powerup_flames.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
