package GUI_2;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class add extends JFrame {
	
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add frame = new add();
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
	public add() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnWigleFolder = new JButton("wigle folder");
		Image img8=new ImageIcon(this.getClass().getResource("/folder.png")).getImage();
		btnWigleFolder.setIcon(new ImageIcon(img8));
		btnWigleFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnWigleFolder.setBackground(new Color(204, 255, 255));
		btnWigleFolder.setForeground(new Color(128, 0, 0));
		btnWigleFolder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnWigleFolder.setBounds(12, 99, 186, 62);
		contentPane.add(btnWigleFolder);
		
		JButton btnMergeCsv = new JButton("merge csv");
		Image img9=new ImageIcon(this.getClass().getResource("/merge2.png")).getImage();
		btnMergeCsv.setIcon(new ImageIcon(img9));
		btnMergeCsv.setForeground(new Color(128, 0, 0));
		btnMergeCsv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMergeCsv.setBackground(new Color(204, 255, 255));
		btnMergeCsv.setBounds(374, 99, 186, 62);
		contentPane.add(btnMergeCsv);
		
		JLabel label = new JLabel("");
		label.setBounds(118, 161, 207, 79);
		contentPane.add(label);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			}
		});
		Image img12=new ImageIcon(this.getClass().getResource("/back2.png")).getImage();
		btnBack.setIcon(new ImageIcon(img12));
		btnBack.setBounds(22, 13, 112, 34);
		contentPane.add(btnBack);
	}
}
