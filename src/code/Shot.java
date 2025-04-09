package code;

import Util.GameValue;

import java.awt.event.KeyEvent;

public class Shot implements  Runnable{
    public int getX() {
        return x;
    }


    int x = 0;

    public int getY() {
        return y;
    }


    int y = 0;
    int direction = 0;
    public Shot(Tanke tanke) {
        this.direction = tanke.getDirection();
        switch (direction){//判断子弹方向
            case KeyEvent.VK_W:
                this.y = tanke.getY0();
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

    //速度
    int speed = 2;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    boolean isLive = true;
    @Override
    public void run() {
            while(isLive){
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
                    System.out.println("子弹结束！");
                    return;
                }
            }
        System.out.println("子弹结束！");
    }

    //判断子弹是否结束
    public boolean isOver(){
        if( x <= 0 || x >= GameValue.Panel_weight || y<= 0 || y >= GameValue.Panel_height ){
            return true;
        }
        return false;
    }
}
