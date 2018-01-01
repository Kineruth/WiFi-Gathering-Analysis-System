package GUI_2;
import javax.swing.*;
import java.awt.*;
 

public class OpenWindow extends JFrame {
	private JLabel label;
	public OpenWindow (){
		label=new JLabel("              yakir amar");
		add(label);
		label.setBounds(40, 40, 100, 20);
	}
	public static void main(String args[]){
		OpenWindow first= new OpenWindow();
		first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		first.setSize(400,400);
		first.setTitle("name");
		first.setVisible(true);
		first.setBackground(null);
	
		first.setBounds(50, 50, 200, 200);
	}

}
