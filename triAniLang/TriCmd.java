public class TriCmd{
  public enum Type{
    TRANSLATE, SCALE, ROTATE;
  }
  public int[] tVar;
  public double[] sVar;
  public double rVar;

  public Type animateType;

  public TriCmd(){

  }

  public TriCmd(int[] t){
    tVar = t;
    animateType = Type.TRANSLATE;
  }

  public TriCmd(double[] s){
    sVar = s;
    animateType = Type.SCALE;
  }

  public TriCmd(double r){
    rVar = r;
    animateType = Type.ROTATE;
  }
}
