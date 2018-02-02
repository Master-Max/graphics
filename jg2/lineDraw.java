import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class lineDraw extends JFrame{

	//Jpanel p;
	int mx, my;
	MYJPanel p2, p3;
	Timer tm;
	int count = 0;
	Color myBlue, myPink, myBlack;
	//public String pic;
	//String pic;


	public static void main(String[] args){
		//Color myBlue, myPink, myBlack;
		int[] cords = new int[4];
		for(int i = 1; i< args.length; i++){
			cords[i-1] = Integer.parseInt(args[i]);
		}
		JFrame bframe = new lineDraw(args[0], cords);


		bframe.setLocation(32,32);
		bframe.setSize(500,500);
		bframe.setVisible(true);
		bframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	public void setPic(String s){
		//pic = s;
	}*/

	public void setColors(){
		myBlue = new Color(0,200,255);
		myPink = new Color(255,0,195);
		myBlack = new Color(0,0,0);
	}

	public lineDraw(String s, int[] cords){
		p2 = new MYJPanel();
		//p2 =
		String pic = s;
		try{
			p2.img = ImageIO.read(new File("./" + pic));
		}catch (IOException e){
			System.out.printf("img open fail\n");
			e.printStackTrace();
			System.exit(1);
		}

		p2.setSize(p2.img.getWidth(), p2.img.getHeight());
		this.add(p2);

		//Drawing The Line
		//double m = calcM(cords);
		//this.mkLn(m,cords);
		this.vertLines();
	}

	public void vertLines(){
		setColors();
		int rgb;
		for(int i = 0; i < 500; i ++){
			for(int j = 0; j < 500; j++){
				//int tmp = i%2;
				if(i%2 == 0){
					rgb = myPink.getRGB();
				}else{
					rgb = myBlue.getRGB();
				}
				p2.drawPixel(i,j,rgb);
			}
		}
	}

	public void mkLn(double m, int[] cords){
		int x,x1,x2,y,y1,y2;
		x1 = cords[0];
		y1 = cords[1];
		x2 = cords[2];
		y2 = cords[3];
		for(int i = x1; i<=x2; i++){
			y = (int) Math.rint((m * i) + y1);
			x = i;
			p2.drawPixel(x,y,4372223);
 		}
	}


	public double calcM(int[] c){//Int to Double
		double M, x1, y1, x2, y2;
		x1 = c[0];
		y1 = c[1];
		x2 = c[2];
		y2 = c[3];
		M = (y2 - y1) / (x2 - x1);
		return M;
	}


}
