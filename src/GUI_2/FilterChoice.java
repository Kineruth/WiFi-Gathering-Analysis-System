package GUI_2;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class FilterChoice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilterChoice frame = new FilterChoice();
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
	public FilterChoice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 788, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLocation = new JButton("location");
		Image img10=new ImageIcon(this.getClass().getResource("/location.png")).getImage();
		btnLocation.setIcon(new ImageIcon(img10));
		btnLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location_F f=new Location_F();
				f.setVisible(true);	
			}
		});
		btnLocation.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		btnLocation.setForeground(new Color(128, 0, 0));
		btnLocation.setBackground(Color.WHITE);
		btnLocation.setBounds(24, 192, 170, 91);
		contentPane.add(btnLocation);
		
		JButton btnTime = new JButton("time");
		Image img11=new ImageIcon(this.getClass().getResource("/time.png")).getImage();
		btnTime.setIcon(new ImageIcon(img11));
		btnTime.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnTime.setForeground(new Color(128, 0, 0));
		btnTime.setBackground(Color.WHITE);
		btnTime.setBounds(513, 196, 170, 82);
		contentPane.add(btnTime);
		
		JButton btnId = new JButton("id");
		Image img12=new ImageIcon(this.getClass().getResource("/id.png")).getImage();
		btnId.setIcon(new ImageIcon(img12));
		btnId.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnId.setForeground(new Color(128, 0, 0));
		btnId.setBackground(Color.WHITE);
		btnId.setBounds(252, 196, 170, 82);
		contentPane.add(btnId);
		
//		JButton btnBack = new JButton("back");
//		Image img13=new ImageIcon(this.getClass().getResource("/back2.png")).getImage();
//		btnBack.setIcon(new ImageIcon(img13));
//		btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//			}
//		});
//		btnBack.setBounds(12, 13, 108, 25);
//		contentPane.add(btnBack);
	}
}
