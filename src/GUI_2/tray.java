package GUI_2;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JSlider;

public class tray extends JFrame {
	private JTextField txtAddNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tray frame = new tray();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tray() {
		getContentPane().setBackground(new Color(153, 255, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		txtAddNumber = new JTextField();
		txtAddNumber.setBackground(new Color(102, 255, 255));
		txtAddNumber.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		txtAddNumber.setText("add number");
		txtAddNumber.setBounds(144, 33, 108, 33);
		getContentPane().add(txtAddNumber);
		txtAddNumber.setColumns(10);
		
		JSlider slider = new JSlider();
		slider.setBounds(112, 120, 200, 26);
		getContentPane().add(slider);
	}
}
