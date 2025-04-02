package code;

import Util.GameValue;

import java.awt.event.KeyEvent;

public class Tanke {

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }
    public void setY0(int y0) {
        this.y0 = y0;
    }
    Tanke(int x, int y){
        this.y0 = y;
        this.x0 = x;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    //
    private boolean isLive =true;
    //方向
    private int direction = KeyEvent.VK_W;
    private int y0= 0;
    private int x0= 0;
    public  int speed = GameValue.HERO_SPEED;
}
