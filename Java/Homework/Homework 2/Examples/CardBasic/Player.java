public class Player {//玩家抽象
    private String name;
    private String position;
    private Card cards[];
    public Player(String n,String p,int k){
        cards=new Card[12];
        name=n;
        position=p;
    }
    public String getName(){return name;}
    public String getPosition(){return position;}
    public Card[] getCards(){return cards;}
    public Card getCard(int i){return cards[i];}
    public void setCards(Card[] c){cards=c;}
    public void setCard(int i,Card c){cards[i]=c;}
    public void sort() {   // 理牌
	for (int index = 0; index < cards.length - 1; index++) {
	    for (int ptr = cards.length - 1; ptr > index; ptr--) {
		if (cards[ptr].getIndex() <cards[ptr - 1].getIndex()) {
		    Card temp = cards[ptr];
		    cards[ptr] = cards[ptr - 1];
		    cards[ptr - 1] = temp;
		}
	    }
	}
    }
    public  void showHand(){
        System.out.println(name+"("+position+"):");
	for (int index = 0; index < cards.length; index++) {
	    System.out.print(cards[index].display() + " ");
	}
	System.out.println();
    }
}