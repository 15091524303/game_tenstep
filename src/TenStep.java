
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TenStep extends JFrame implements MouseListener {

    private int score, step = 10;
    private Circle[][] circles = new Circle[5][5];

    private TenStep(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                circles[i][j] = new Circle();
                circles[i][j].x = i * 100 + 50;
                circles[i][j].y = (j + 1) * 100 + 50;
                circles[i][j].r = 40;
                circles[i][j].angleMode = 1;
            }
        }
        this.update();
    }

    private void rotate(int i, int j) {
        circles[i][j].angleMode -= 1;
        if (circles[i][j].angleMode < 0) {
            circles[i][j].angleMode = 3;
        }
        score += 90;
    }

    private int getNextIndex(int[] index) //int[2]
    {
        int i = index[0];
        int j = index[1];
        int mode = circles[i][j].angleMode;
        if (mode == 0)
            i++;
        else if (mode == 1)
            j--;
        else if (mode == 2)
            i--;
        else
            j++;
        index[0] = i;
        index[1] = j;
        if (i >= 0 && i < 5 && j >= 0 && j < 5)
            return 1;
        else
            return 0;
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        double angle;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                graphics2D.setColor(new Color(150,128,15));
                graphics2D.drawOval(circles[i][j].x-circles[i][j].r, circles[i][j].y-circles[i][j].r, 2*circles[i][j].r, 2*circles[i][j].r); //画圆
                graphics2D.fillOval(circles[i][j].x-circles[i][j].r, circles[i][j].y-circles[i][j].r, 2*circles[i][j].r, 2*circles[i][j].r);
                graphics2D.setColor(new Color(250, 0, 0));
                double pi = 3.1415926;
                angle = circles[i][j].angleMode * pi / 2;
                graphics2D.drawLine(circles[i][j].x,
                        circles[i][j].y,
                        (int) (circles[i][j].x + circles[i][j].r * cos(angle)),
                        (int) (circles[i][j].y + circles[i][j].r * sin(-angle))
                );
            }
        }
        //要输出的字符串
        String s = String.format("%d 步  %d 度", step, score);
        g.setFont(new Font("宋体", Font.PLAIN, 20));  //设置文字字体大小
        g.drawString(s, 150, 80);// 在xy位置输出字符串文字
    }

    private void update(){
        this.setTitle("十步万度V0.1");
        this.setBounds(200, 100, 800,700);
        setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(150,128,15));
        this.setForeground(new Color(150,128,15));
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {  //鼠标单击时执行

        if (step > 0) {
            step--;
            int i = e.getX() / 100;
            int j = (e.getY() - 100) / 100;
            rotate(i, j);
            repaint();
            int[] index = new int[]{i, j};
            while (getNextIndex(index) == 1) {
                rotate(index[0], index[1]);

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

//    class UIupdate implements Runnable{
//
//        @Override
//        public void run() {
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public static void main(String[] args) {
        new TenStep();
    }
}
