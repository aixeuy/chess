package chess;

import java.awt.Button;
import java.awt.Rectangle;

import javax.swing.JButton;

public class DD extends Ship {
	boolean canatt;
	public DD(){
		range=Math.sqrt(2);
		health=1;
		resthealth=health;
		width=30;
		length=60;
		category="DD";
		speed=2*Math.sqrt(2);
		bt=new Button("DD");
		antisub=true;
		aaarea=0;
		canatt=true;
		att=1;
		addlistener();
		//bt.setBounds(new Rectangle(x+length/2,y+width/2,length,width));
	}

	}
