package code;

import java.util.Vector;

public class EnemyTanke extends  Tanke{

    // 创建子弹
    public Vector<Shot> bullets = new Vector<>();
    public EnemyTanke(int x, int y) {
        super(x, y);
    }

    //开炮
    public void shotTanke(){
        Shot shot =new Shot(this);
        bullets.add(shot);
        new Thread(shot).start();
    }
}
