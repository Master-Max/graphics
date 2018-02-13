import java.awt.*;
import java.awt.Color;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TALPanel extends JPanel{
  Graphics2D g2d;
  Timer timer;
  ArrayList<TriCmd> commands;
  TriCmd[] aniList;
  int[] x;
  int[] y;
  boolean virgin;
  int ai;

  public TALPanel(TriCmd[] t, int[] a, int[]b){
    super();
    virgin = true;
    ai = 0;
    x = a;
    y = b;
    aniList = t;
    System.out.println("\nTrying to print data");
    this.printCommands();
    this.update();
  }

  public synchronized void paintComponent(Graphics g){
    g2d = (Graphics2D) g;
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0,0,500,500);
    if(!virgin){
      this.nextAni();
    }
    //Draw The Triangle
    g2d.setColor(Color.BLACK);
    g2d.drawLine(x[0],y[0],x[1],y[1]);//v1
    g2d.drawLine(x[1],y[1],x[2],y[2]);//v2
    g2d.drawLine(x[2],y[2],x[0],y[0]);//v3
  }

  public void update(){
    timer = new Timer(0, new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        //System.out.printf("#");
        //this.translate(4,4);
        virgin = false;
        repaint();
      }
    });
    timer.setRepeats(true);
    timer.setDelay(100);
    timer.start();
  }

  public void translate(int dx, int dy){
    System.out.println("Translate");
    for(int i=0;i<3;i++){
      x[i] += dx;
      y[i] += dy;
    }
  }

  public void scale(double da, double db){
    System.out.println("Scale");
    for(int i=0;i<3;i++){
      x[i] *= da;
      y[i] *= db;
    }
  }

  public void rotate(double dr){
    System.out.println("Rotate");
    int[] tmpX = x;
    int[] tmpY = y;

    int[] o = this.getCenter();
    this.translate((-1*o[0]),(-1*o[1]));

    for(int i=0;i<3;i++){
      x[i] = getRotX(x[i],y[i],dr);
      y[i] = getRotY(x[i],y[i],dr);
      //x[i] = (int) x[i] * Math.cos(dr) - y[i] * Math.sin(dr);
      //y[i] = (int) x[i] * Math.sin(dr) + y[i] * Math.cos(dr);
    }

    this.translate(o[0],o[1]);

    //int xP = (x * Math.cos(dr)) - (y * Math.sin(dr));
    //int yP = (x * Math.sin(dr)) + (y * Math.cos(dr));
  }

  public int getRotX(int roX, int roY, double rad){
    double tmpA = roX * Math.cos(rad);
    double tmpB = roY * Math.sin(rad);
    double tmpC = tmpA - tmpB;
    return (int) Math.round(tmpC);
  }

  public int getRotY(int roX, int roY, double rad){
    double tmpA = roX * Math.sin(rad);
    double tmpB = roY * Math.cos(rad);
    double tmpC = tmpA + tmpB;
    return (int) Math.round(tmpC);
  }

  public int[] getCenter(){
    int[] cen = new int[2];

    double tmpX = (x[0]+x[1]+x[2]) / 3.0;
    double tmpY = (y[0]+y[1]+y[2]) / 3.0;

    cen[0] = (int) Math.round(tmpX);
    cen[1] = (int) Math.round(tmpY);
    //System.out.printf("(%d,%d)\n",cen[0],cen[1]);
    return cen;
  }

  public void nextAni(){
    System.out.printf("Ani Count: %d\n",ai);
    if(ai < aniList.length){
      switch(aniList[ai].animateType){
        case TRANSLATE:
          System.out.printf("TRA: %d %d\n",aniList[ai].tVar[0],aniList[ai].tVar[1]);
          this.translate(aniList[ai].tVar[0],aniList[ai].tVar[1]);
          break;
        case SCALE:
          System.out.printf("SCA: %f %f\n", aniList[ai].sVar[0],aniList[ai].sVar[1]);
          this.scale(aniList[ai].sVar[0],aniList[ai].sVar[1]);
          break;
        case ROTATE:
          System.out.printf("ROT: %f\n", aniList[ai].rVar);
          this.rotate(aniList[ai].rVar);
          break;
        default:
          System.out.println("Errr");
      }
      ai++;
    }
  }

  public void printCommands(){
    for(int i=0; i<aniList.length;i++){
      switch(aniList[i].animateType){
        case TRANSLATE:
          System.out.printf("TRA: %d %d\n", aniList[i].tVar[0],aniList[i].tVar[1]);
          break;
        case SCALE:
          System.out.printf("SCA: %f %f\n", aniList[i].sVar[0],aniList[i].sVar[1]);
          break;
        case ROTATE:
          System.out.printf("ROT: %f\n", aniList[i].rVar);
          break;
        default:
          System.out.println("Errr");
      }
    }
  }
}
