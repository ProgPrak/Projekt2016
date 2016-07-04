package probe;

public class Message {
	int type;
	int playerID;
	int xKoo;
	int yKoo;
	boolean aufgenommen;
	Level leveldaten;
	public Message(int t, int p,int x, int y){
		this.type=t;
		this.playerID=p;
		this.xKoo=x;
		this.yKoo=y;
	}
	
	public Message(int t,int a, int b){
		this.type=t;
		this.xKoo=a;
		this.yKoo=b;
	}
	
	public Message (int t, Level x){
		this.type = t;
		this.leveldaten = x;
	}

	public Message (int t){
		this.type=t;
	}
	
	public int getType(){
		return this.type;
	}
	public int getID(){
		return this.playerID;
	}
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
}
