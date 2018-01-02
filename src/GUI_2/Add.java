package GUI_2;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI_Filter.Wraper;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Add extends JFrame {

	private JPanel contentPane;
	private Wraper wraper ;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add();
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
	public Add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 788, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnWigleFolder = new JButton("WiGLE Folder:");
		Image img8=new ImageIcon(this.getClass().getResource("/folder.png")).getImage();
		btnWigleFolder.setIcon(new ImageIcon(img8));
		btnWigleFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnWigleFolder))
				{
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("Choose Folder");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
						wraper.folderAdded(chooser.getSelectedFile().getPath());
				}
			}
		});
		btnWigleFolder.setBackground(new Color(204, 255, 255));
		btnWigleFolder.setForeground(new Color(128, 0, 0));
		btnWigleFolder.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnWigleFolder.setBounds(22, 150, 240, 80);
		contentPane.add(btnWigleFolder);
		
		JButton btnMergeCsv = new JButton("Merged CSV File:");
		btnMergeCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Choose Csv File");
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						wraper.mergedFileAdded(chooser.getSelectedFile());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Image img9=new ImageIcon(this.getClass().getResource("/merge2.png")).getImage();
		btnMergeCsv.setIcon(new ImageIcon(img9));
		btnMergeCsv.setForeground(new Color(128, 0, 0));
		btnMergeCsv.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMergeCsv.setBackground(new Color(204, 255, 255));
		btnMergeCsv.setBounds(22, 250, 240, 80);
		contentPane.add(btnMergeCsv);
		
		JLabel label = new JLabel("");
		label.setBounds(118, 161, 207, 79);
		contentPane.add(label);
		
//		JButton btnBack = new JButton("return");
//		btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			setVisible(false);
//			}
//		});
//		Image img12=new ImageIcon(this.getClass().getResource("/back2.png")).getImage();
//		btnBack.setIcon(new ImageIcon(img12));
//		btnBack.setBounds(22, 13, 112, 34);
//		contentPane.add(btnBack);
	}
}
