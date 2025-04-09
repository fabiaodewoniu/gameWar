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

    /**
     *  获取坦克本身大小 x,y最大值
     * @return 返回坦克本身
     */
    public Tanke getXYSize(){
        Tanke tanke = new Tanke(getX0(),getY0());
        switch (getDirection()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                tanke.setX0(getX0() + GameValue.TANKE_weight);
                tanke.setY0(getY0() + GameValue.TANKE_height);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_A:
                tanke.setX0(getX0() + GameValue.TANKE_height);
                tanke.setY0(getY0() + GameValue.TANKE_weight);
                break;

        }
        return tanke;
    }

    //判断坦克是否出边界
    public boolean isPass(int speed){
        boolean flag = true; // 默认出边界
        int newX = getX0(),nexY = getY0();
        int newX0 = getX0(),nexY0 = getY0();
        switch (getDirection()){
            case KeyEvent.VK_W:
                nexY -=  speed;
                nexY0 = nexY+ GameValue.TANKE_height;
                newX0 += GameValue.TANKE_weight;
                break;
            case KeyEvent.VK_S:
                nexY += speed;
                newX0 += GameValue.TANKE_weight;
                nexY0 = nexY+  GameValue.TANKE_height;
                break;
            case KeyEvent.VK_D:
                newX += speed;
                newX0 = newX +  GameValue.TANKE_height;
                nexY0 += GameValue.TANKE_weight;
                break;
            case KeyEvent.VK_A:
                newX -= speed;
                newX0 = newX +  GameValue.TANKE_height;
                nexY0 += GameValue.TANKE_weight;
                break;
        }
        if((newX >= 0 && newX0 <= GameValue.Panel_weight) && (nexY >= 0 && nexY0 <= GameValue.Panel_height)){
            flag = false;
        }
        return flag;
    }

    //方向
    public int direction = KeyEvent.VK_W;
    public int y0= 0;
    public int x0= 0;
    public int speed = GameValue.hero_speed;;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    private boolean isLive = true;
}
