package chess;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Ship {
Button bt;
Boolean side;
int ox,oy,x,y,xr,yr;
Boolean clicked;
double range;
int health;
int resthealth;
int width;
int length;
String category;
double speed;
Boolean antisub;
double aaarea;
int att;
boolean moved;
boolean unlocked;
boolean selected;
int energy;
public Ship(){
	x=8;y=6;
	ox=0;oy=0;
	clicked=false;
	moved=false;
	side=false;
	unlocked=false;
	selected=false;
	energy=2;
}
public void getreal(){
	xr=85*x+60-5*y+10;
	yr=60*(y)+10;
}
public void toposition(int x1,int y1){
	x=x1;y=y1;
	getreal();
	bt.setBounds(new Rectangle(xr-length/2,yr-width/2,length,width));
}
public void setside(){
	if(side){
		bt.setBackground(Color.white);
		bt.setForeground(Color.black);
	}
	else
	{
		bt.setBackground(Color.black);
		bt.setForeground(Color.white);
	}
}
public void addlistener(){
	bt.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(unlocked){
				
			if(Battle.shipselected){
				if(selected){
					selected=false;Battle.shipselected=false;
					clicked=false;
				}
				else{
					Battle.clearselection();
					selected=true;Battle.shipselected=true;
					clicked=false;
				}
			}
			else{
				selected=true;Battle.shipselected=true;
				clicked=false;
			}
		}
			else{
				if(Battle.shipselected){
						Ship sh=Battle.getselected();
						if(canbeattacked(sh)){
							attacked(sh);
						}
				}
			}
		}	
		
	});
}
public boolean canbeattacked(Ship sh){
	if((!Battle.start)&&(!sh.moved)&&(sh.side!=side)){
	if(!((x==0&&y==0)||(x==15&&y==11))){
		if((sh.x==sh.ox&&sh.y==sh.oy)||(sh.category.equals("DD")&&category.equals("SS")
				&&Battle.distance(sh.x, sh.y, sh.ox, sh.oy)<=sh.range+0.1)){
			if(Battle.distance(x, y, sh.x, sh.y)<=sh.range+0.1){
				if(category.equals("SS")){
					if((!sh.category.equals("CV"))&&(!sh.category.equals("BB"))){
						return true;
					}
				}
				else{
					if(sh.category.equals("CV")){
						if(sh.energy>=2){
							return true;
						}
					}
					else{
						return true;
					}
				}
			}
		}
	}
	}
	return false;
}
public void attacked(Ship sh){
	if(sh.category.equals("CV")){
		double db=Battle.random();
		if(1.0-0.2*Battle.getantiair(side,x, y)>db){
			resthealth-=sh.att;
		}
		else{
			sh.energy=0;
		}
	}
	else{
		resthealth-=sh.att;
		}
	if(resthealth<=0){
		bt.setVisible(false);bt.disable();//toposition(x+1400,y+700);
		Battle.pos[x][y].sh=null;
	}
	else if(resthealth<health){
		bt.setForeground(Color.red);
	}
	sh.moved=true;
}
}
