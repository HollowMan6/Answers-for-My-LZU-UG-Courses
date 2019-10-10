import java.io.Serializable;
public class Student implements Serializable
{
  private String id;
  private String name;
  private float english;
  private float chinese;
  private float math;

  public Student(){}
  public Student(String i,String n,float e,float c,float m)
  {
     id=i;name=n;english=e;chinese=c;math=m;
  }
  public String getId(){return id;}
  public void setId(String d){id=d;}
  public String getName(){return name;}
  public void setName(String n){name=n;}
  public float getEnglish(){return english;}
  public void setEnglish(float e){english=e;}
  public float getChinese(){return chinese;}
  public void setChinese(float c){chinese=c;}
  public float getMath(){return math;}
  public void setMath(float m){math=m;}
}