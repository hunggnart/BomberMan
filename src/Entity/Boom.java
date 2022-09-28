package Entity;

import main.GamePanel;

public class Boom extends Entity {
    GamePanel gp;

    public Boom(GamePanel gp){
        this.gp = gp;
        setValue();

    }

    public void setValue(){
        x = gp.player.x;
        y = gp.player.y;
    }

    public void getImageBoom(){

    }
}
