package code;

import Util.GameValue;
import View.Mypanel;

import java.awt.event.KeyEvent;
import java.util.*;

public class Hero extends Tanke{

    public Vector<Shot> shots = new Vector<>();
    public Shot shot =null;

    public int speed = GameValue.hero_speed;// 速度
    public Hero(int x, int y){
        super(x,y);
    }

    //开炮

    public void shotTanke(){
        shot = new Shot(this);
        new Thread(shot).start();
        shots.add(shot);
    }
}
