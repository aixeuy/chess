package chess;

import java.awt.Button;
import java.awt.Rectangle;

import javax.swing.JButton;

public class CV extends Ship {
int energy;
public CV(){
	energy=2;
	range=100;
	health=2;
	resthealth=health;
	width=55;
	length=85;
	category="CV";
	speed=1;
	bt=new Button("CV");
	antisub=false;
	aaarea=0;
	att=1;
	addlistener();
	//bt.setBounds(new Rectangle(x+length/2,y+width/2,length,width));
}

}
