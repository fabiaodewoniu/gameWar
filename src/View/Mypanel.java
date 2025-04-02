package View;

import Util.GameValue;
import code.Hero;
import code.Shot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Mypanel extends JPanel  implements KeyListener , Runnable{

    private Hero hero = null;
    public Mypanel(){
        hero = new Hero(100,100);
        setSize(GameValue.Panel_weight, GameValue.Panel_height);
        setBackground(Color.black);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        //画坦克
        tangPaint(g,hero, hero.direction,true);

        //画子弹
        drawBall(g, hero.bullets, true);
    }

    /***
     * 绘画坦克
     */
    public void tangPaint(Graphics g, Hero hero, int direction, boolean flag){
        Color tanke = Color.cyan;
        if(flag){
            tanke = Color.yellow;
        }
        switch (direction){
            case KeyEvent.VK_W://向上
                g.setColor(tanke);
                g.fillRect(hero.getX0(), hero.getY0(),  GameValue.hero_weight, GameValue.hero_height); //第一调履带
                g.fillRect(hero.getX0() + 30,hero.getY0(),  GameValue.hero_weight, GameValue.hero_height);//第二调履带
                g.fillRect(hero.getX0() + 10,hero.getY0() + 10,  20, 40); //中间的方块
                g.setColor(Color.black);
                g.drawOval(hero.getX0() + 10,hero.getY0() + 20, 20 , 20); //中间的炮塔
                g.setColor(Color.cyan);
                g.drawLine(hero.getX0() + 20,hero.getY0() + 30,hero.getX0() + 20, hero.getY0());//炮管
                break;
            case KeyEvent.VK_S: //向下
                g.setColor(tanke);
                g.fillRect(hero.getX0(),hero.getY0(),  GameValue.hero_weight, GameValue.hero_height); //第一调履带
                g.fillRect(hero.getX0() + 30,hero.getY0(),  GameValue.hero_weight, GameValue.hero_height);//第二调履带
                g.fillRect(hero.getX0() + 10,hero.getY0() + 10,  20, 40); //中间的方块
                g.setColor(Color.black);
                g.drawOval(hero.getX0() + 10,hero.getY0() + 20, 20 , 20); //中间的炮塔
                g.setColor(Color.cyan);
                g.drawLine(hero.getX0() + 20,hero.getY0() + 30,hero.getX0() + 20, hero.getY0() + GameValue.hero_height);//炮管
                break;
            case KeyEvent.VK_D: //向右
                g.setColor(tanke);
                g.fillRect(   hero.getX0(),      hero.getY0(), GameValue.hero_height, GameValue.hero_weight); //第一调履带
                g.fillRect(hero.getX0() , hero.getY0()+ 30,  GameValue.hero_height, GameValue.hero_weight);//第二调履带
                g.fillRect(hero.getX0() + 10,hero.getY0() + 10,  40, 20); //中间的方块
                g.setColor(Color.black);
                g.drawOval(hero.getX0() + 20,hero.getY0() + 10, 20 , 20); //中间的炮塔
                g.setColor(Color.cyan);
                g.drawLine(hero.getX0() + 30,hero.getY0() + 20,hero.getX0() + GameValue.hero_height, hero.getY0() + 20);//炮管
                break;
            case KeyEvent.VK_A: //向左
                g.setColor(tanke);
                g.fillRect(   hero.getX0(),     hero.getY0(), GameValue.hero_height, GameValue.hero_weight); //第一调履带
                g.fillRect(   hero.getX0() , hero.getY0()+ 30,  GameValue.hero_height, GameValue.hero_weight);//第二调履带
                g.fillRect(hero.getX0() + 10,hero.getY0() + 10,  40, 20); //中间的方块
                g.setColor(Color.black);
                g.drawOval(hero.getX0() + 20,hero.getY0() + 10, 20 , 20); //中间的炮塔
                g.setColor(Color.cyan);
                g.drawLine(hero.getX0() + 30,hero.getY0() + 20,hero.getX0() , hero.getY0() + 20);//炮管
                break;
        }
    }



    /**
     *
     * @param g 画笔
     * @param bullets 获取子弹列
     * @param flag 敌 true 还是我
     */
    public void drawBall(Graphics g, Vector<Shot> bullets, boolean flag){
        for(int i = 0; i< bullets.size(); i++){
            Shot shot = bullets.get(i);
            if(shot.isLive){
                g.setColor(GameValue.HERO_BALL_COLOR);
                g.fillOval(shot.getX(), shot.getY(), GameValue.HERO_BALL_XY, GameValue.HERO_BALL_XY);
            }else{
                bullets.remove(i);
            }
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //判断键盘是否按下
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下键盘" + e.getKeyCode());

        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                hero.setY0(hero.getY0() - 1);
                hero.direction = KeyEvent.VK_W;
                break;
            case KeyEvent.VK_S:
                hero.setY0(hero.getY0() + 1);
                hero.direction = KeyEvent.VK_S;
                break;
            case KeyEvent.VK_D:
                hero.setX0(hero.getX0() + 1);
                hero.direction = KeyEvent.VK_D;
                break;
            case KeyEvent.VK_A:
                hero.setX0(hero.getX0() - 1);
                hero.direction = KeyEvent.VK_A;
                break;
            case KeyEvent.VK_J:
                System.out.println("按下开炮");
                hero.shotTanke();
                break;
        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
