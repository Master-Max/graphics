import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.imageio.*;
import java.io.IOException;
import java.awt.Color;
import java.util.Scanner;

public class lineDraw extends JFrame{
  BufferedImage img = null;
  BufferedImage bmg = null;
  MXJPanel p2;
  Color myC1, myC2;
  int bkg, rgb;
  public static void main(String[] args){

    JFrame bframe = new lineDraw("bg.png");
    bframe.setLocation(32,32);
		bframe.setSize(700,700);
		bframe.setVisible(true);
		bframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void setColors(){
    myC1 = new Color(0,0,0);
    bkg = myC1.getRGB();
    myC2 = new Color(0,200,255);
    rgb = myC2.getRGB();
  }

  public lineDraw(){

  }

  public lineDraw(String s){
    p2 = new MXJPanel();
    this.setColors();
    this.mkBg(bkg);

    try{
      p2.img = ImageIO.read(new File("./" + s));
    } catch (IOException e){
      System.out.println("img open failed");
      e.printStackTrace();
      System.exit(1);
    }

    p2.setSize(p2.img.getWidth(), p2.img.getHeight());
		this.add(p2);

    this.mkLn();
  }

  public void mkLn(){
    Scanner key = new Scanner(System.in);
    System.out.println("Enter: x1 y1 x2 y2");
    int[] cords = new int[4];
    for(int i = 0; i<cords.length; i++){
      cords[i] = key.nextInt();
      System.out.printf("C[%d]: %d\n", i, cords[i]);
    }
    double m = calcM(cords);
    System.out.println("M: " + m);
    int deltaX, deltaY, x1, y1, x2, y2, x, y;
    x1 = cords[0]; y1 = cords[1];x2 = cords[2]; y2 = cords[3];
    deltaX = x2 - x1;
    deltaY = y2 - y1;

    if(deltaY > deltaX){
      System.out.println("Big Y");
      for(int i = y1; i<=y2; i++){
        x = (int) Math.rint((m * i) + x1);
        if(deltaX == 0){
          x = x1;
        }else{
          x = (int) Math.rint((i - y1) / m);
        }
        y = i;
        System.out.printf("x: %d    y:%d\n",x,y);
        p2.drawPixel(x,y,rgb);
      }
    }else{
      System.out.println("Big X");
      for(int i = x1; i<=x2; i++){
				y = (int) Math.rint((m * i) + y1);
				x = i;
        System.out.printf("x: %d    y:%d\n",x,y);
				p2.drawPixel(x,y,rgb);
      }
    }

  }

  public double calcM(int[] c){
    double M, x1, y1, x2, y2;
		x1 = c[0];
		y1 = c[1];
		x2 = c[2];
		y2 = c[3];
		M = (y2 - y1) / (x2 - x1);
		return M;
  }

  public void mkBg(int bkg){
    try{
      bmg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    }catch(Exception e){e.printStackTrace();}

    for(int i = 0; i<500; i++){
      for(int j = 0; j<500; j++){
        bmg.setRGB(i,j,bkg);
      }
    }
    try{
      File out = new File("bg.png");
      ImageIO.write(bmg, "png", out);
    }catch(IOException e){e.printStackTrace();}
  }
}
