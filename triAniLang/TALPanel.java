import java.awt.*;
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

  public TALPanel(){
    super();
  }

  public synchronized void paint(Graphics g){
    g2d = (Graphics2D) g;
    //Clear The Board

    //Draw The Triangle
    //g2d.drawLine();//v1
    //g2d.drawLine();//v2
    //g2d.drawLine();//v3
  }

  public void update(){
    timer = new Timer(0, new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        repaint();
      }
    });
    timer.setRepeats(true);
    timer.setDelay(100);
    timer.start();
  }

  class triCommands{
    public triCommands(){

    }

  }

}
