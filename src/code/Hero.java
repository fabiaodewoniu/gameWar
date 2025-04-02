package code;

import Util.GameValue;
import View.Mypanel;

import java.awt.event.KeyEvent;
import java.util.*;

public class Hero extends Tanke{

    public Vector<Shot> bullets = new Vector<>();
    public Hero(int x, int y){
        super(x,y);
    }

    //开炮
    public void shot(){
        Shot shot =new Shot(this);
        shot.setSpeed(GameValue.HERO_BULLET_SPEED);
        bullets.add(shot);
        new Thread(shot).start();
    }
}
