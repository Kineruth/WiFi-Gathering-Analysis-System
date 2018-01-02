package GUI_2;
import java.awt.EventQueue;
import sun.audio.*;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import GUI_Filter.DataBase;
import GUI_Filter.Wraper;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame {
	
//	private DataBase db = new DataBase();
	private Wraper wraper = new Wraper();
	private JFrame frame;
	private JTextField txtSamples;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		  * Window parameters:
		  */
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.getContentPane().setFont(new Font("Arial", Font.BOLD, 28));
		frame.setBounds(500, 200, 951, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		  * Main image parameters:
		  */
		JLabel label = new JLabel("");
		Image img7=new ImageIcon(this.getClass().getResource("/database.png")).getImage();
		label.setIcon(new ImageIcon(img7));
		label.setBounds(406, 48, 176, 149);
		frame.getContentPane().add(label);
		
		/*
		  * Add Folder button:
		  */
		
		JButton btnAddWigleFolder = new JButton("Add WiGLE Folder");
		Image img=new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAddWigleFolder.setIcon(new ImageIcon(img));
		btnAddWigleFolder.setForeground(new Color(128, 0, 0));
		btnAddWigleFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(arg0.getSource().equals(btnAddWigleFolder))
				{
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("Choose Folder");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
						wraper.folderAdded(chooser.getSelectedFile().getAbsolutePath());						
					}	
				}
			}
		});
		
		btnAddWigleFolder.setBackground(new Color(255, 255, 255));
		btnAddWigleFolder.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddWigleFolder.setBounds(40, 261, 267, 41);
		frame.getContentPane().add(btnAddWigleFolder);
		
		/*
		  * Add File button:
		  */
		JButton btnAddMergedFile = new JButton("Add Merged File");
		btnAddMergedFile.setIcon(new ImageIcon(img));
		btnAddMergedFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Choose Csv File");
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						System.out.println(chooser.getSelectedFile().getAbsolutePath());
						try {
							wraper.mergedFileAdded(chooser.getSelectedFile().getAbsolutePath());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				}
			}
		});
		btnAddMergedFile.setForeground(new Color(128, 0, 0));
		btnAddMergedFile.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddMergedFile.setBackground(Color.WHITE);
		btnAddMergedFile.setBounds(40, 318, 267, 41);
		frame.getContentPane().add(btnAddMergedFile);
				
		 /*
		  * Restore button:
		  */
		JButton btnRestore = new JButton("Restore");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get samples from database before filtering
				//need to add function
			}
		});
		Image img4=new ImageIcon(this.getClass().getResource("/restore.png")).getImage();
		btnRestore.setIcon(new ImageIcon(img4));
		btnRestore.setForeground(new Color(128, 0, 0));
//		btnRestore.setHorizontalTextPosition(SwingContants.CENTER);
		btnRestore.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRestore.setBackground(new Color(255, 255, 255));
		btnRestore.setBounds(636, 377, 267, 41); 
		frame.getContentPane().add(btnRestore);
		
		
		/*
		  * Save CSV button:
		  */
		JButton btnSaveAsMerge = new JButton("Save As CSV");
		Image img5=new ImageIcon(this.getClass().getResource("/csv.png")).getImage();
		btnSaveAsMerge.setIcon(new ImageIcon(img5));
		btnSaveAsMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wraper.saveMergedCSV();
			}
		});
		btnSaveAsMerge.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSaveAsMerge.setForeground(new Color(128, 0, 0));
		btnSaveAsMerge.setBackground(new Color(255, 255, 255)); 
		btnSaveAsMerge.setBounds(40, 434, 267, 41); 
		frame.getContentPane().add(btnSaveAsMerge);
		
		/*
		  * Save KML button:
		  */
		JButton btnCreatKml = new JButton("Save As KML");
		Image img3=new ImageIcon(this.getClass().getResource("/kml.png")).getImage();
		btnCreatKml.setIcon(new ImageIcon(img3));
		btnCreatKml.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCreatKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					wraper.saveAsKML();
				} catch (FileNotFoundException | MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreatKml.setForeground(new Color(128, 0, 0));
		btnCreatKml.setBackground(new Color(255, 255, 255));
		btnCreatKml.setBounds(40, 375, 267, 43);  
		frame.getContentPane().add(btnCreatKml);


		/*
		  * Filter buttons:
		  */
		JButton btnFilter = new JButton("Filter By Time");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FilterChoice filt=new FilterChoice();
				filt.setVisible(true);	
			}
		});
		Image img6=new ImageIcon(this.getClass().getResource("/filter.png")).getImage();
		btnFilter.setIcon(new ImageIcon(img6));
		btnFilter.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFilter.setForeground(new Color(128, 0, 0));
		btnFilter.setBackground(new Color(255, 255, 255));
		btnFilter.setBounds(350, 318, 246, 41);
		frame.getContentPane().add(btnFilter);
		
		JButton btnFilterByDevice = new JButton("Filter By Device");
		btnFilterByDevice.setIcon(new ImageIcon(img6));
		btnFilterByDevice.setForeground(new Color(128, 0, 0));
		btnFilterByDevice.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFilterByDevice.setBackground(Color.WHITE);
		btnFilterByDevice.setBounds(350, 376, 246, 41);
		frame.getContentPane().add(btnFilterByDevice);
		
		JButton btnFilterByLocation = new JButton("Filter By Location");
		btnFilterByLocation.setIcon(new ImageIcon(img6));
		btnFilterByLocation.setForeground(new Color(128, 0, 0));
		btnFilterByLocation.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFilterByLocation.setBackground(Color.WHITE);
		btnFilterByLocation.setBounds(350, 434, 246, 41);
		frame.getContentPane().add(btnFilterByLocation);
		
		/*
		  * Clear Data button:
		  */
		JButton btnClearAll = new JButton("Clearance");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wraper.clearance();
			}
		});
		
		Image img2=new ImageIcon(this.getClass().getResource("/clear.png")).getImage();
		btnClearAll.setIcon(new ImageIcon(img2));
		btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClearAll.setForeground(new Color(128, 0, 0));
		btnClearAll.setBackground(new Color(255, 255, 255));
		btnClearAll.setBounds(636, 434, 267, 41);
		frame.getContentPane().add(btnClearAll);	
		
		txtSamples = new JTextField();
		txtSamples.setBackground(UIManager.getColor("Button.background"));
		txtSamples.setForeground(new Color(25, 25, 112));
		txtSamples.setHorizontalAlignment(SwingConstants.LEFT);
//		txtSamples.setDropMode(DropMode.ON);
		txtSamples.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSamples.setText("Samples: ");
		txtSamples.setBounds(66, 532, 146, 26);
		frame.getContentPane().add(txtSamples);
		txtSamples.setColumns(10);
		
	}
}
