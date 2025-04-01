import Util.GameValue;
import View.Mypanel;

import javax.swing.*;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {

        JFrame star = new JFrame();
        Mypanel mypanel = new Mypanel();
        new Thread(mypanel).start();

        star.addKeyListener(mypanel);
        star.setSize(GameValue.Panel_weight, GameValue.Panel_height);
        star.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        star.add(mypanel);

        GameValue.Panel_weight = mypanel.getWidth();
        GameValue.Panel_height = mypanel.getHeight();
        star.setVisible(true);

    }
}