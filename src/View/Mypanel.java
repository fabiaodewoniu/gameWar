package View;

import Util.GameValue;
import code.EnemyTanke;
import code.Hero;
import code.Shot;
import code.Tanke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Mypanel extends JPanel  implements KeyListener , Runnable{

    private Hero hero = null;
    private Vector<EnemyTanke> enemyTankes = new Vector<>();
    public Mypanel(){
        hero = new Hero(100,100);

        //创建多个敌方坦克
        for(int i = 0 ; i < GameValue.ENEMY_SIZE; i++){
            EnemyTanke enemyTanke = new EnemyTanke((100 *(i+1)), 1);
            enemyTanke.setDirection(KeyEvent.VK_S);
            Shot shot = new Shot(enemyTanke);
            new Thread(shot).start();
            enemyTanke.bullets.add(shot);
            enemyTankes.add(enemyTanke);
        }
        setBackground(Color.black);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        //画坦克
        tangPaint(g,hero,true);

        //画子弹
        drawBall(g, hero.bullets, true);

        //画敌方坦克和子弹
        for(int j=0; j <enemyTankes.size(); j++){
            EnemyTanke enemyTanke = enemyTankes.get(j);
            if(!enemyTanke.getLive()){
                System.out.println("该坦克已死亡"); // 画一个爆炸的图形
                draw(g, enemyTanke );
                enemyTankes.remove(enemyTanke);//移除改坦克
                continue;
            }
            tangPaint(g, enemyTanke, false);
            drawBall(g, enemyTanke.bullets, false);
        }
    }

    public void draw(Graphics g , Tanke tanke){

        for(int i = 1; i< 30; i+=10){
            g.setColor(GameValue.HERO_BALL_COLOR);
            g.fillRect(tanke.getX0() + (30 - i) , tanke.getY0() + (30 - i), i*2, i*2);
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    //检查子弹师傅击中对方
    public boolean isHit(Hero hero, Vector<EnemyTanke> enemyTankes){
        boolean isHit = false;
        for(int i = 0;i <hero.bullets.size(); i++ ){
            //判断子弹是否存活
            Shot shot = hero.bullets.get(i);
            if(!shot.isLive) continue;

            for(int j=0; j <enemyTankes.size(); j++){
                EnemyTanke enemyTanke = enemyTankes.get(j);
                int x = enemyTanke.getX0();
                int y = enemyTanke.getY0();
                if((shot.getX() > x && shot.getX() < x + GameValue.hero_height)
                && (shot.getY() > y && shot.getY() < y + GameValue.hero_height)){
                    isHit = true;
                    enemyTanke.setLive(false);
                    shot.isLive = false;
                }

            }
        }
        return isHit;
    }

    /***
     * 绘画坦克
     */
    public void tangPaint(Graphics g, Tanke hero, boolean flag){
        if(!hero.getLive()){
            return;
        }
        Color tanke = GameValue.HERO_COLOR;
        if(flag){
            tanke = GameValue.ENEMY_COLOR;
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
     * @param bullets 获取子弹列
     * @param flag 敌 我
     */
    public void drawBall(Graphics g, Vector<Shot> bullets, boolean flag){
        if(flag){
            g.setColor(GameValue.HERO_BALL_COLOR);
        }else{
            g.setColor(GameValue.ENEMY_BALL_COLOR);
        }
        for(int i = 0; i< bullets.size(); i++){
            Shot shot = bullets.get(i);
            if(shot.isLive){
                g.fillOval(shot.getX(), shot.getY(), GameValue.HERO_BALL_SIZE, GameValue.HERO_BALL_SIZE);
            }else{
                bullets.remove(shot);
            }
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //判断键盘是否按下
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("按下键盘" + e.getKeyCode());
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                hero.setY0(hero.getY0() - hero.speed);
                hero.setDirection(KeyEvent.VK_W);
                break;
            case KeyEvent.VK_S:
                hero.setY0(hero.getY0() + hero.speed);
                hero.setDirection(KeyEvent.VK_S);
                break;
            case KeyEvent.VK_D:
                hero.setX0(hero.getX0() + hero.speed);
                hero.setDirection(KeyEvent.VK_D);
                break;
            case KeyEvent.VK_A:
                hero.setX0(hero.getX0() - hero.speed);
                hero.setDirection(KeyEvent.VK_A);
                break;
            case KeyEvent.VK_Q:
                System.out.println("按下开炮");
                hero.shot();
                break;
        }
//        this.repaint();

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
            //检查是否被击中
            isHit(hero, enemyTankes);
            this.repaint();
        }
    }
}
