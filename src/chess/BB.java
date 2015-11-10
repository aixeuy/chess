package chess;

import java.awt.Button;
import java.awt.Rectangle;

import javax.swing.JButton;

public class BB extends Ship {
	public BB(){
		range=2*Math.sqrt(2);
		health=2;
		resthealth=health;
		width=50;
		length=80;
		category="BB";
		speed=1;
		bt=new Button("BB");
		antisub=false;
		aaarea=Math.sqrt(2);
		att=1;
		addlistener();
		//bt.setBounds(new Rectangle(x+length/2,y+width/2,length,width));
	}

	}
