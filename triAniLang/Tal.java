import java.awt.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.*;
import java.io.File;
import javax.swing.*;

public class Tal extends JFrame{
    TALPanel tp;
    String dataFile;
    FileInputStream fis = null;
    BufferedReader reader = null;
    ArrayList<TriCmd> list;
    TriCmd[] cmdList;
    int[] x;
    int[] y;
    TriCmd tmpCmd;
    public static void main(String[] args){

      JFrame tframe = new Tal(args[0]);
      tframe.setLocation(50,50);
      tframe.setSize(500,500);
      tframe.setVisible(true);
      tframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Tal(String s){
      this.setDataFile(s);
      this.setData();
      cmdList = list.toArray(new TriCmd[list.size()]);
      tp = new TALPanel(cmdList,x,y);
      tp.setSize(500,500);
      this.add(tp);
    }

    public void setData(){
      x = new int[3];
      y = new int[3];
      String line;
      try{
        fis = new FileInputStream("./" + dataFile);
  			reader = new BufferedReader(new InputStreamReader(fis));

  			line = reader.readLine();
        System.out.println(line);
  			String[] tmpC = line.split("\\s+");
        int xCnt, yCnt;
        xCnt = 0;
        yCnt = 0;
        for(int i = 0; i<tmpC.length; i++){
          if(i%2 == 0){
            x[xCnt] = Integer.parseInt(tmpC[i]);
            System.out.printf("(%d,",x[xCnt]);
            xCnt++;
          }else{
            y[yCnt] = Integer.parseInt(tmpC[i]);
            System.out.printf("%d)\n",y[yCnt]);
            yCnt++;
          }
        }
        list = new ArrayList<TriCmd>();//Arraylist of TriCmd (Triangle Commands)
        tmpCmd = new TriCmd();
        while((line = reader.readLine()) != null){//Read lines of file
          System.out.println(line);
          String[] tmpLn = line.split("\\s+");
          if(tmpLn[0].equals("T")){
            int[] tmpT = new int[2];
            tmpT[0] = Integer.parseInt(tmpLn[1]);
            tmpT[1] = Integer.parseInt(tmpLn[2]);
            tmpCmd = new TriCmd(tmpT);
          }else if(tmpLn[0].equals("S")){
            double[] tmpS = new double[2];
            tmpS[0] = Double.parseDouble(tmpLn[1]);
            tmpS[1] = Double.parseDouble(tmpLn[2]);
            tmpCmd = new TriCmd(tmpS);
          }else if(tmpLn[0].equals("R")){
            double tmpR;
            tmpR = Double.parseDouble(tmpLn[1]);
            tmpCmd = new TriCmd(tmpR);
          }else{
            System.out.println("Messed up entering a data point");
            System.exit(0);
          }
          list.add(tmpCmd);

        }
      }catch(Exception e){e.printStackTrace();}
    }

    public void setDataFile(String s){
      dataFile = s;
    }

}
