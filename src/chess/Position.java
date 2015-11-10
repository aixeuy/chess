package chess;

public class Position {
int x,y;
Ship sh;
public Position(int x,int y){
	this.x=85*x+60;this.y=60*y;
	sh=null;
}
}
