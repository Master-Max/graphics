public class triCmd{
  public enum Type{
    TRANSFORM, SCALE, ROTATE;
  }
  public int[] tVar;
  public double[] sVar;
  public double rVar;

  public Type animateType;

  public triCmd(){

  }

  public triCmd(int[] t){
    tVar = t;
    animateType = Type.TRANSFORM;
  }

  public triCmd(double[] s){
    sVar = s;
    animateType = Type.SCALE;
  }

  public triCmd(double r){
    rVar = r;
    animateType = Type.ROTATE;
  }
}
