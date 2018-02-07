import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class MXJPanel extends JPanel{
  Graphics2D g2d;
  BufferedImage img = null;

  public MXJPanel(){
    super();
  }

  public synchronized void paintComponent(Graphics g){
    g2d = (Graphics2D) g;
		g2d.drawImage(img, null, 0, 0);
  }

  public void drawPixel(int x, int y, int c){
    img.setRGB(x,y,c);
  }
}
