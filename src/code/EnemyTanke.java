package code;

import java.util.Vector;

public class EnemyTanke  extends  Tanke{

    public Vector<Shot> bullts = new Vector<>();
    public EnemyTanke(int x, int y) {
        super(x, y);
    }

    public void shotBullts(){
        Shot shot = new Shot(this);
        new Thread(shot).start();
        bullts.add(shot);
    }
}
