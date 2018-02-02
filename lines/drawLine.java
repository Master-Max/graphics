import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.imageio.*;
import java.util.Random;
import java.io.*;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.imageio.*;
import java.util.Random;
import java.io.*;
import java.awt.geom.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;

public class drawLine extends JFrame{
	Graphics2D g2d;
	BufferedImage img = null;

	public static void main(String[] args){
		Color myBlue = new Color(0,200,255);
		int rgb = myBlue.getRGB();

		drawLine dl = new drawLine();
		dl.drawThing(args[0], rgb);
		/*int[] cords = new int[4];
		for(int i=0; i<args.length; i++){
			cords[i-1] = Integer.parseInt(args[i]);
		}

		JFrame bframe = new lineDraw(args[0], cords);

		bframe.setLocation(32,32);
		bframe.setSize(500,500);
		bframe.setVisible(true);
		bframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
	}

	public drawLine(){}

	public void drawThing(String s, int rgb){
		try{
			img = ImageIO.read(new File(s));
		}catch(IOException e){e.printStackTrace();}

		for(int i=0; i<100; i++){
			for(int j=0; j<100; j++){
				img.setRGB(i,j,rgb);
			}
		}

		try{
			File outFile = new File("sv.png");
			ImageIO.write(img, "png", outFile);
		}catch(IOException e){e.printStackTrace();}

	}



}
