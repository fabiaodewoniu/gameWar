package code;

import View.Mypanel;

import java.awt.event.KeyEvent;
import java.util.*;

public class Hero extends Tanke{

    public List<Shot> shots = new ArrayList<>();
    public Shot shot =null;
    public Hero(int x, int y){
        super(x,y);
    }

    //开炮

    public void shotTanke(Mypanel mypanel){
//        Shot shot =null;
        switch (getDirection()){//判断子弹方向
            case KeyEvent.VK_W:
                shot = new Shot(KeyEvent.VK_W, getX0() + 20, getY0());
                break;
            case KeyEvent.VK_S:
                shot = new Shot(KeyEvent.VK_S, getX0() + 20, getY0() + 60);
                break;
            case KeyEvent.VK_D:
                shot = new Shot(KeyEvent.VK_D, getX0() + 60, getY0() + 20);
                break;
            case KeyEvent.VK_A:
                shot = new Shot(KeyEvent.VK_A, getX0() , getY0() + 20);
                break;
        }
        shot.setMypanel(mypanel);
        shots.add(shot);
        new Thread(shot).start();
    }
}
