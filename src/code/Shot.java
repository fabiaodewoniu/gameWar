package code;

import Util.GameValue;
import View.Mypanel;

import java.awt.event.KeyEvent;

public class Shot implements  Runnable{
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    int x = 0;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    int y = 0;
    int direction = 0;
    int speed = 2;//速度
    public boolean isLive = true;
    public Shot(Tanke tanke) {
        this.direction = tanke.getDirection();
        switch (direction){//判断子弹方向
            case KeyEvent.VK_W:
                this.y = tanke.getY0() ;
                this.x = tanke.getX0() + 20;
                break;
            case KeyEvent.VK_S:
                this.y = tanke.getY0() + 60;
                this.x = tanke.getX0() + 20;
                break;
            case KeyEvent.VK_D:
                this.y = tanke.getY0() + 20;
                this.x = tanke.getX0() + 60;
                break;
            case KeyEvent.VK_A:
                this.y = tanke.getY0() + 20;
                this.x = tanke.getX0();
                break;
        }
    }


    @Override
    public void run() {
            while(true){
                switch (direction){//判断子弹方向
                    case KeyEvent.VK_W:
                        y -= speed;
                        break;
                    case KeyEvent.VK_S:
                        y += speed;
                        break;
                    case KeyEvent.VK_D:
                        x += speed;
                        break;
                    case KeyEvent.VK_A:
                        x -= speed;
                        break;
                }
                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                System.out.println(" 子弹坐标x:"+x +" y:"+ y);
                if(isOver()){//判断是否结束
                    isLive = false;
                    System.out.println(" 子弹结束！");
                    return;
                }
            }
    }

    //判断子弹是否结束
    private boolean isOver(){
        if( x < 0 || x > GameValue.Panel_weight || y<0 || y > GameValue.Panel_height ){
            return true;
        }
        return false;
    }
}
