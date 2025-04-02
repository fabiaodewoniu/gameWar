package code;

import View.Mypanel;

import java.awt.event.KeyEvent;
import java.util.*;

public class Hero extends Tanke{

    public Vector<Shot> bullets = new Vector<>();
    public Shot shot =null;
    public Hero(int x, int y){
        super(x,y);
    }

    //开炮

    public void shotTanke(){
        Shot shot =new Shot(this);
        bullets.add(shot);
        new Thread(shot).start();
    }
}
