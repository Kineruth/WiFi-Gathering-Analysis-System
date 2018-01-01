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

public class filter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					filter frame = new filter();
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
	public filter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
			}
		});
		btnLocation.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		btnLocation.setForeground(new Color(128, 0, 0));
		btnLocation.setBackground(Color.WHITE);
		btnLocation.setBounds(39, 74, 156, 57);
		contentPane.add(btnLocation);
		
		JButton btnTime = new JButton("time");
		Image img11=new ImageIcon(this.getClass().getResource("/time.png")).getImage();
		btnTime.setIcon(new ImageIcon(img11));
		btnTime.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnTime.setForeground(new Color(128, 0, 0));
		btnTime.setBackground(Color.WHITE);
		btnTime.setBounds(233, 74, 156, 57);
		contentPane.add(btnTime);
		
		JButton btnId = new JButton("id");
		Image img12=new ImageIcon(this.getClass().getResource("/id.png")).getImage();
		btnId.setIcon(new ImageIcon(img12));
		btnId.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		btnId.setForeground(new Color(128, 0, 0));
		btnId.setBackground(Color.WHITE);
		btnId.setBounds(139, 156, 156, 54);
		contentPane.add(btnId);
	}

}
