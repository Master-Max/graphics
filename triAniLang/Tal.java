import java.awt.*;
import java.util.*;

public class Tal extends JFrame{
    TALPanel tp;
    String dataFile;
    FileInputStream fis = null;
    BufferedReader reader = null;
    ArrayList<triCmd> list;
    int[] x;
    int[] y;
    public static void main(String[] args){

      JFrame tframe = new Tal();
      this.setDataFile(args[0]);
      tframe.setLocation(50,50);
      tframe.setSize(100,100);
      tframe.setVisible(true);
      tframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Tal(){
      tp = new TALPanel();
      tp.setSize(100,100);
      this.add(tp);
    }

    public void setData(){
      x = new int[3];
      y = new int[3];

      try{
        fis = new FileInputStream("./" + dataIn);
  			reader = new BufferedReader(new InputStreamReader(fis));

  			String line = reader.readLine();
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
            System.out.printf("(%d,",y[yCnt]);
            yCnt++;
          }
        }
        list = new ArrayList<triCmd>();//Arraylist of triCmd (Triangle Commands)
        while((line = reader.readLine()) != null){//Read lines of file
          triCmd tmpCmd;
          String[] tmpLn = line.split("\\s+");
          if(tmpLn[0].equals("T")){
            int[] tmpT = new int[2];
            tmpT[0] = Integer.parseInt(tmpLn[1]);
            tmpT[1] = Integer.parseInt(tmpLn[2]);
            tmpCmd = new triCmd(tmpT);
          }else if(tmpLn[0].equals("S")){
            doube[] tmpS = new double[2];
            tmpS[0] = Double.parseDouble(tmpLn[1]);
            tmpS[1] = Double.parseDouble(tmpLn[2]);
            tmpCmd = new triCmd(tmpS);
          }else if(tmpLn[0].equals("R")){
            double tmpR;
            tmpR = Double.parseDouble(tmpLn[1]);
            tmpCmd = new triCmd(tmpR);
          }else{
            System.out.println("Messed up entering a data point");
          }
          list.add(tmpCmd);
        }
      }catch(Exception e){e.printStackTrace();}
    }

    public void setDataFile(String s){
      dataFile = s;
    }

}
