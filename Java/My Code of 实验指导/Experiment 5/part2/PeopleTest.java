class People{
    protected double weight,height;
    public void speakHello(){
        System.out.println("Who am I?");
    }
    public void averageHeight(){
        height=173;
        System.out.println("average height:"+height);
    }
    public void averageWeight(){
        weight=70;
        System.out.println("average weight:"+weight);
    }
}
class ChinaPeople extends People{
    public void speakHello(){
        System.out.println("哈喽，我是中国人！");
    }
    public void averageHeight(){
        height=168.78;
        System.out.println("我们中国人的平均身高:"+height);
    }
    public void averageWeight(){
        weight=65;
        System.out.println("我们中国人的平均体重:"+weight);
    }
    public void chinaGongfu(){
        System.out.println("坐如钟，站如松，睡如弓");
    }
}
class AmericanPeople extends People{
    public void speakHello(){
        System.out.println("Hello, I am America!");
    }
    public void averageHeight(){
        height=173;
        System.out.println("average height:"+height);
    }
    public void averageWeight(){
        weight=70;
        System.out.println("average weight:"+weight);
    }
    public void americanBoxing(){
        System.out.println("The straight, hook");
    }
}
class BeijingPeople extends ChinaPeople{
    public void speakHello(){
        System.out.println("您好，俺是北京人");
    }
    public void beijingOpera(){
        System.out.println("京剧");
    }
}
class PeopleTest{
    public static void main(String[] args) {
        ChinaPeople chinaPeople = new ChinaPeople();
        AmericanPeople americanPeople = new AmericanPeople();
        BeijingPeople beijingPeople = new BeijingPeople();
        chinaPeople.speakHello();
        americanPeople.speakHello();
        beijingPeople.speakHello();
        chinaPeople.averageHeight();;
        americanPeople.averageHeight();
        beijingPeople.averageHeight();
        chinaPeople.averageWeight();
        americanPeople.averageWeight();
        beijingPeople.averageWeight();
        chinaPeople.chinaGongfu();
        americanPeople.americanBoxing();
        beijingPeople.beijingOpera();
        beijingPeople.chinaGongfu();
    }
}