import java.io.*;

public class triAnim extends JFrame{
	BufferedImage img = null;
	BufferedImage bmg = null;
	FileInputStream fis = null;
	BufferedReader reader = null;

	MXJPanel p1;
	Color myC1, myC2;
	int bkg, rgb;
	public static void main(String[] args){

		String dataFile = args[0];

		JFrame bframe = new triAnim("bg.png");
		bframe.setLocation(32,32);
		bframe.setSize(700,700);
		bframe.setVisible(true);
		bframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while(bframe.animateTriangle(dataFile)){

		}
	}

	public boolean animateTriangle(String dataIn){
		int[] cords = new int[6];
		Scanner str = new Scanner(System.in);

		try{
			fis = new FileInputStream("./" + dataIn);
			reader = new BufferedReader(new InputStreamReader(fis));

			String line = reader.readLine();
			String[] tmpC = line.split("\\s+");
			for(int i = 0; i<cords.length; i++){
				cords[i] = Integer.parseInt(tmpC[i]);
			}

			line = reader.readLine();
			while(line != null){
				System.out.println(line);

			}

		}
	}

	public void transform(){

	}

	public void scale(){

	}

	public void rotate(){
		
	}

	public void setColors(){
		myC1 = new Color(255,255,255);
		bkg = myC1.getRGB();
		myC2 = new Color(randWithRange(0,255),randWithRange(0,255),randWithRange(0,255));
		rgb = myC2.getRGB();
	}

	public int randWithRange(int min, int max){
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}

}
