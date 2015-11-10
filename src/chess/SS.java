package chess;

import java.awt.Button;
import java.awt.Rectangle;

import javax.swing.JButton;

public class SS extends Ship {
	public SS(){
		range=Math.sqrt(2);
		health=1;
		resthealth=health;
		width=20;
		length=50;
		category="SS";
		speed=Math.sqrt(2);
		bt=new Button("SS");
		antisub=true;
		aaarea=0;
		att=2;
		addlistener();
		//bt.setBounds(new Rectangle(x+length/2,y+width/2,length,width));
	}

	}
