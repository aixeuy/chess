package chess;

import java.awt.Button;
import java.awt.Rectangle;

import javax.swing.JButton;

public class C extends Ship {
	public C(){
		range=Math.sqrt(2);
		health=2;
		resthealth=health;
		width=40;
		length=70;
		category="C";
		speed=Math.sqrt(2);
		bt=new Button("C");
		antisub=true;
		aaarea=Math.sqrt(2);
		att=1;
		addlistener();
		//bt.setBounds(new Rectangle(x+length/2,y+width/2,length,width));
	}

	}
