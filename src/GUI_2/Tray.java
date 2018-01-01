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
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JRadioButton;

public class Tray extends JFrame {
	private JTextField txtAddNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tray frame = new Tray();
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
	public Tray() {
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(137, 174, 6, 22);
		getContentPane().add(textPane);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(185, 75, 132, 80);
		getContentPane().add(rdbtnNewRadioButton);
	}
}
