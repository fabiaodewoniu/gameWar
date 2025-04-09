package View;

import Util.GameValue;
import code.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Mypanel extends JPanel  implements KeyListener, Runnable {

    private Hero hero = null;
    private Vector<EnemyTanke> enemys = new Vector<>();
    public Mypanel(){
        hero = new Hero(100,200);

        for(int i= 0; i < 3; i++){
            EnemyTanke enemy = new EnemyTanke(100 * (i+1), 100);
            enemy.setDirection(KeyEvent.VK_S);
            enemy.shotBullts();// 创建一颗子弹
            enemys.add(enemy);
        }
        setBackground(Color.black);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        //画坦克
        tangPaint(g, hero,true);

        //画子弹
        drawBullet(g, hero.shots);

        //画敌方坦克和子弹
        for(int i = 0;i < enemys.size(); i++){
            EnemyTanke enemy= enemys.get(i);
            if(enemy.isLive()){
                tangPaint(g, enemy, false);
                //画子弹
                drawBullet(g, enemy.bullts);
            }else{
                enemys.remove(enemy);// 如果死亡则移除
            }
        }
    }

    /***
     * 绘画坦克
     */
    public void tangPaint(Graphics g, Tanke hero, boolean flag){
        Color tanke = Color.cyan;
        if(flag){
            tanke = Color.yellow;
        }
        switch (hero.getDirection()){
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
     * @param bullets 获取子弹s位置
     */
    public void drawBullet(Graphics g, Vector<Shot> bullets){


        g.setColor(GameValue.HERO_BALL_COLOR);
       for(int i = 0; i < bullets.size(); i++){

           Shot shot = bullets.get(i);
           if(shot.isLive()) {
               g.fillOval(shot.getX(), shot.getY(), GameValue.HERO_BALL_XY, GameValue.HERO_BALL_XY);
           }else  bullets.remove(shot);//移除死亡子弹

       }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    //判断键盘是否按下
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                hero.moveUp();
                break;
            case KeyEvent.VK_S:
                hero.moveDown();
                break;
            case KeyEvent.VK_D:
                hero.moveRight();
                break;
            case KeyEvent.VK_A:
                hero.moveLeft();
                break;
            case KeyEvent.VK_Q:
                System.out.println("按下开炮");
                hero.shotTanke();
                break;
        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //判断坦克是否被击中
    public  void isHit(Hero hero, Vector<EnemyTanke> enemys){
        for(int i = 0; i < hero.shots.size(); i++){
            Shot shot = hero.shots.get(i);
            if(!shot.isLive()) continue;
            for(int j= 0 ; j < enemys.size(); j++){
                EnemyTanke enemy = enemys.get(j);
                if(!enemy.isLive()) continue;
                Tanke size = enemy.getXYSize();
                if((shot.getX() > enemy.getX0() && shot.getX() < size.getX0())
                 && (shot.getY() > enemy.getY0() && shot.getY() < size.getY0())){
                    enemy.setLive(false);//
                    shot.setLive(false);//子弹被消耗
                    System.out.println("坦克被击中");
                }
            }
        }
    }

    @Override
    public void run() {
        while(true){
            try{
                isHit(hero, enemys);
                this.repaint();
                Thread.sleep(200);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
