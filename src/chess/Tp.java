package chess;

import java.awt.Button;
import java.awt.Rectangle;

import javax.swing.JButton;

public class Tp extends Ship {
	public Tp(){
		range=0;
		health=1;
		resthealth=health;
		width=45;
		length=65;
		category="Tp";
		speed=Math.sqrt(2);
		bt=new Button("Tp");
		antisub=false;
		aaarea=-1;
		att=0;
		addlistener();
		//bt.setBounds(new Rectangle(x+length/2,y+width/2,length,width));
	}

	}
