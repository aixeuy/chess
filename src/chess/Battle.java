package chess;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JButton;

public class Battle extends Applet{
static Ship[] ships=new Ship[22];
static Position[][] pos=new Position[16][12];
int tx,ty;
double dis;
Button next=new Button();
TextField tf=new TextField();
String s1="player1";
String s2="player2";
static boolean turn;
static boolean start;
public static boolean shipselected;
int winner;
public void init(){
	winner=0;
	this.setLayout(null);
	dis=0;
	tx=0;ty=0;
	for(int i=0;i<16;i++){
		for(int j=0;j<12;j++){
			pos[i][j]=new Position(i,j);
		}
	}
	for(int i=0;i<2;i++){
		ships[i]=new CV();
	}
	for(int i=2;i<5;i++){
		ships[i]=new BB();
	}
	for(int i=5;i<9;i++){
		ships[i]=new C();
	}
	for(int i=9;i<14;i++){
		ships[i]=new DD();
	}
	for(int i=14;i<20;i++){
		ships[i]=new SS();
	}
	for(int i=20;i<22;i++){
		ships[i]=new Tp();
	}
	Button show=new Button("show instruction");
	Button next=new Button("next");
	final TextField tf=new TextField();
	next.setBounds(new Rectangle(630,700,70,40));
	tf.setBounds(new Rectangle(700,700,70,40));
	show.setBounds(new Rectangle(630,740,140,20));
	next.addActionListener(new ActionListener(){
		int a=0;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(a>=2){
				start=false;
				if(random()>0.5){
					turn=true;
				}
				else{
					turn=false;
				}
			}
			if(!turn){
				turn=true;
				tf.setText(s1);
			arrange(turn);
			}
			else{
				turn=false;
				tf.setText(s2);
			arrange(turn);
			}
			a++;
		}
		
	});
	show.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			repaint();
		}
		
	});
	add(next);
	add(tf);
	add(show);
	shipselected=false;
	
}
public void start(){
	this.setFocusable(true);
	start=true;
	int[] n=get10rn();
	for(int i=0;i<10;i++){
		ships[n[i]].side=true;
	}
	ships[21].side=true;
	ships[20].side=false;
	arrangebattle();		
}
public void paint(Graphics g){
	this.setBackground(Color.cyan);
	for(int i=0;i<16;i++){
		g.drawLine(85*i+60+10, 10, 85*i+10,730-60);
	}
	for(int j=0;j<12;j++){
		g.drawLine(60-5*j+10, 60*j+10, 1420-5*j-85+10, 60*j+10);
	}
	if(!start){
		if(shipselected){
			Ship sh=getselected();
			for(int i=sh.ox-2;i<=sh.ox+2;i++){
				for(int j=sh.oy-2;j<=sh.oy+2;j++){
					if(i<0||i>15||j<0||j>11){
					//System.out.println("case1"+i+" "+j);
					}
					else{
						if(canmove(sh,i,j)){
							toreal(i,j);
							g.setColor(Color.blue);
						g.drawOval(tx-25, ty-25, 50, 50);
						}
					}
				}
			}
			g.setColor(Color.pink);
			toreal(sh.ox,sh.oy);
			g.drawOval(tx-25, ty-25, 50, 50);
			for(int i=0;i<22;i++){
				if(ships[i].canbeattacked(sh)){
					g.setColor(Color.magenta);
					toreal(ships[i].ox,ships[i].oy);
					g.drawOval(tx-30, ty-30, 60, 60);
				}
			}
		}
	}
	Font Myfont=new Font("Old English Text MT",Font.BOLD,36);
	g.setFont(Myfont);
	if(winner==1){
		g.setColor(Color.WHITE);
		g.drawString("THE WINNNER IS PLAYER1", 400, 300);
	}
	else if(winner==2){
		g.setColor(Color.BLACK);
		g.drawString("THE WINNNER IS PLAYER2", 400, 300);
	}
}
public boolean mouseDown(Event e,int x,int y)
{
	maptopoint(x,y);
	if(shipselected){
		Ship sh=new Ship();
		for(int i=0;i<22;i++){
		if(ships[i].selected){
			sh=ships[i];
			if(canmove(sh,tx,ty)){
			move(sh,tx,ty);
			}
			break;
		}
		}
	}
	if(ships[21].x==15){
		winner=1;
		repaint();
		stop();
	}
	if(ships[20].x==0){
		winner=2;
		repaint();
		stop();
	}
  return true; 
 }
public void toreal(int x,int y){
	this.tx=85*x+60-5*y+10;
	this.ty=60*y+10;
}
public void maptopoint(int x,int y){
	this.ty=ver((y-10)/60.0);
	this.tx=ver((x-10+5*(this.ty)-60)/85.0);
}
public int ver(double d){
	int i=(int)d;
	d=d-i;
	if(d>0.5){
		return(i+1);
	}
	return i;
}
public void move(Ship sh,int x,int y){
	int x0=sh.x;int y0=sh.y;Ship sh0=pos[x][y].sh;
	
	if(sh0!=null){
		pos[sh.x][sh.y].sh=null;
		pos[x][y].sh=sh;
		sh.toposition(x, y);
		move(sh0,x0, y0);
		pos[x][y].sh=sh;
	}
	else{
		pos[sh.x][sh.y].sh=null;
		pos[x][y].sh=sh;
		sh.toposition(x, y);
	}
}
public void setside(){
	
}
public int[] get10rn(){
	int[] n=new int[10];
	int ind=0;
	int m=ver(20*random());
	boolean canfind=false;
	n[ind]=m;
	while(true){
		m=(int)(20*random());
		for(int i=0;i<ind+1;i++){
			if(n[i]==m){
				canfind=true;
				break;
			}
		}
		if(canfind){
			canfind=false;
		}
		else{
			ind++;
			n[ind]=m;
			if(ind==9){
				break;
			}
		}
	}
	return n;
}
public static double random(){
	Random r = new Random();
    double rv =r.nextDouble();
    return rv;
}
public void arrangebattle(){
	move(ships[21],0,0);
	ships[21].setside();
	add(ships[21].bt);
	move(ships[20],15,11);
	ships[20].setside();
	add(ships[20].bt);
	int h1,h2;h1=0;h2=0;
	for(int i=0;i<20;i++){
		ships[i].setside();
		if(ships[i].side==true){
			move(ships[i],0,h1+1);
			h1++;
		}
		else{
			move(ships[i],15,11-(h2+1));
			h2++;
		}
		add(ships[i].bt);
	}
	setorigin();
	turn=false;
	
}
public void arrange(boolean side){
	if(start){
	for(int i=0;i<20;i++){
		if(ships[i].side==side){
			ships[i].unlocked=true;
		}
		else{
			ships[i].unlocked=false;
		}
	}
	}
	else{
		for(int i=0;i<22;i++){
			if(ships[i].side==side){
				ships[i].unlocked=true;
			}
			else{
				ships[i].unlocked=false;
			}
		}
	}
	setorigin();
}
public void setorigin(){
	for(int i=0;i<22;i++){
		if(ships[i].resthealth<=0){
			pos[ships[i].ox][ships[i].oy].sh=null;
			remove(ships[i].bt);	
		}
		ships[i].ox=ships[i].x;
		ships[i].oy=ships[i].y;
		ships[i].moved=false;
	}
	for(int i=0;i<2;i++){
		ships[i].energy++;
		if(ships[i].energy>2){
			ships[i].energy=2;
		}
	}
	System.out.println("ori");
	clearselection();
}
public static void clearselection() {
	for(int i=0;i<22;i++){
		ships[i].selected=false;
		ships[i].clicked=false;
	}
	shipselected=false;
}
public boolean canmove(Ship sh,int x,int y){
	if(start){
		if(sh.side){
			if(x<7&&x>=0&&y<12&&y>=0){
				if(pos[x][y].sh==null){
					return true;
				}
				else if(pos[x][y].sh.unlocked){
					System.out.println("unlocked");
					return true;
				}
			}
		}
		else{
			if(x>=9&&x<=15&&y<12&&y>=0){
				if(pos[x][y].sh==null){
					return true;
				}
				else if(pos[x][y].sh.unlocked){
					return true;
				}
			}
		}
	}
	else{
		if(x<16&&x>=0&&y<12&&y>=0&&(!sh.moved)){
			if(distance(x,y,sh.ox,sh.oy)<=sh.speed+0.1){
			if(pos[x][y].sh==null){
				return true;
			}
			else if(pos[x][y].sh.unlocked&&(distance(sh.x,sh.y,pos[x][y].sh.ox,pos[x][y].sh.oy)<=pos[x][y].sh.speed+0.1)){
				return true;
			}
			}
		}
	}
 return false;
}
public static double distance(int x1,int y1,int x2,int y2){
	return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
}
public static Ship getselected(){
	for(int i=0;i<22;i++){
		if(ships[i].selected){
			return ships[i];
		}
	}
	return null;
}
public static int getantiair(boolean side,int x,int y){
	int n=0;
	for(int i=x-1;i<=x+1;i++){
		for(int j=y-1;j<=y+1;j++){
			if(i<0||i>15||j<0||j>11){
			System.out.println("case1"+i+" "+j);
			}
			else if(pos[i][j].sh==null){
				System.out.println("case2"+i+" "+j);
			}
			else{
				if(side!=turn){
				if(pos[i][j].sh.aaarea+0.1>=distance(i,j,x,y)){
				n=n+pos[i][j].sh.energy/2;System.out.print("add");
				}
				}
			}
		}
	}
	return n;
}
}
