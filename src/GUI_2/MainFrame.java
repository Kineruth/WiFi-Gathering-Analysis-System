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
import GUI_Filter.DeviceFilter;
import GUI_Filter.Filter;
import GUI_Filter.LocationFilter;
import GUI_Filter.TimeFilter;
import GUI_Filter.Wraper;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class MainFrame {

	private JFrame frame;
	public static Filter filter1, filter2, filter3, filter4,filter5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
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
		frame.setBounds(500, 200, 954, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * Main image parameters:
		 */
		JLabel label = new JLabel("");
		Image img7 = new ImageIcon(this.getClass().getResource("/database.png")).getImage();
		label.setIcon(new ImageIcon(img7));
		label.setBounds(406, 48, 176, 149);
		frame.getContentPane().add(label);

		/*
		 * Add Folder button:
		 */

		JButton btnAddWigleFolder = new JButton("Add WiGLE Folder");
		Image img = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAddWigleFolder.setIcon(new ImageIcon(img));
		btnAddWigleFolder.setForeground(new Color(128, 0, 0));
		btnAddWigleFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource().equals(btnAddWigleFolder)) {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("Choose Folder");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						Wraper.folderAdded(chooser.getSelectedFile().getAbsolutePath());
					}
				}
			}
		});

		btnAddWigleFolder.setBackground(new Color(255, 255, 255));
		btnAddWigleFolder.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddWigleFolder.setBounds(40, 261, 266, 41);
		frame.getContentPane().add(btnAddWigleFolder);

		/*
		 * Add File button:
		 */
		JButton btnAddMergedFile = new JButton("  Add Merged File");
		btnAddMergedFile.setIcon(new ImageIcon(img));
		btnAddMergedFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Choose Csv File");
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					try {
						Wraper.mergedFileAdded(chooser.getSelectedFile().getAbsolutePath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddMergedFile.setForeground(new Color(128, 0, 0));
		btnAddMergedFile.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddMergedFile.setBackground(Color.WHITE);
		btnAddMergedFile.setBounds(40, 318, 266, 41);
		frame.getContentPane().add(btnAddMergedFile);

		/*
		 * Restore button:
		 */
		JButton btnRestore = new JButton("Restore");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataBase.restoreData();
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/restore.png")).getImage();
		btnRestore.setIcon(new ImageIcon(img4));
		btnRestore.setForeground(new Color(128, 0, 0));
		// btnRestore.setHorizontalTextPosition(SwingContants.CENTER);
		btnRestore.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRestore.setBackground(new Color(255, 255, 255));
		btnRestore.setBounds(636, 377, 266, 41);
		frame.getContentPane().add(btnRestore);

		/*
		 * Save CSV button:
		 */
		JButton btnSaveAsMerge = new JButton("Save As CSV");
		Image img5 = new ImageIcon(this.getClass().getResource("/csv.png")).getImage();
		btnSaveAsMerge.setIcon(new ImageIcon(img5));
		btnSaveAsMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wraper.saveMergedCSV();
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
		Image img3 = new ImageIcon(this.getClass().getResource("/kml.png")).getImage();
		btnCreatKml.setIcon(new ImageIcon(img3));
		btnCreatKml.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCreatKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Wraper.saveAsKML();
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
		JButton btnTimeFilter = new JButton("Filter By Time");
		btnTimeFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Time_F time = new Time_F();
				time.setVisible(true);

			}
		});
		Image img6 = new ImageIcon(this.getClass().getResource("/filter.png")).getImage();
		btnTimeFilter.setIcon(new ImageIcon(img6));
		btnTimeFilter.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTimeFilter.setForeground(new Color(128, 0, 0));
		btnTimeFilter.setBackground(new Color(255, 255, 255));
		btnTimeFilter.setBounds(350, 318, 246, 41);
		frame.getContentPane().add(btnTimeFilter);

		JButton btnFilterByDevice = new JButton("Filter By Device");
		btnFilterByDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Device_F device = new Device_F();
				device.setVisible(true);
			}
		});
		btnFilterByDevice.setIcon(new ImageIcon(img6));
		btnFilterByDevice.setForeground(new Color(128, 0, 0));
		btnFilterByDevice.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFilterByDevice.setBackground(Color.WHITE);
		btnFilterByDevice.setBounds(350, 377, 246, 41);
		frame.getContentPane().add(btnFilterByDevice);

		JButton btnFilterByLocation = new JButton("Filter By Location");
		btnFilterByLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location_F loc = new Location_F();
				loc.setVisible(true);
			}
		});
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
				Wraper.clearance();
			}
		});

		Image img2 = new ImageIcon(this.getClass().getResource("/clear.png")).getImage();
		btnClearAll.setIcon(new ImageIcon(img2));
		btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClearAll.setForeground(new Color(128, 0, 0));
		btnClearAll.setBackground(new Color(255, 255, 255));
		btnClearAll.setBounds(636, 434, 266, 41);
		frame.getContentPane().add(btnClearAll);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(50, 537, 237, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Samples : ");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(15, 0, 92, 36);
		panel.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		label_1.setBackground(new Color(240, 230, 140));
		label_1.setFont(new Font("Sitka Text", Font.BOLD, 17));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(122, 0, 100, 36);
		panel.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(250, 235, 215));
		panel_1.setBounds(368, 537, 201, 41);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMacs = new JLabel("Macs : ");
		lblMacs.setHorizontalAlignment(SwingConstants.CENTER);
		lblMacs.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblMacs.setBounds(0, 0, 92, 36);
		panel_1.add(lblMacs);

		JLabel label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Sitka Text", Font.BOLD, 17));
		label_2.setBounds(86, 0, 100, 36);
		panel_1.add(label_2);

		JButton btnAlgorithm = new JButton("Algorithm 2 - Samples");
		btnAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Algorithm2 a = new Algorithm2();
				a.setVisible(true);
			}
		});
		btnAlgorithm.setForeground(new Color(128, 0, 0));
		btnAlgorithm.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnAlgorithm.setBackground(Color.WHITE);
		btnAlgorithm.setBounds(636, 318, 266, 41);
		frame.getContentPane().add(btnAlgorithm);

		JButton btnAlgorithm_1 = new JButton("Algorithm 1 - Macs");
		btnAlgorithm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// send to ger all macs -algo1 and show on map
			}
		});
		btnAlgorithm_1.setForeground(new Color(128, 0, 0));
		btnAlgorithm_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnAlgorithm_1.setBackground(Color.WHITE);
		btnAlgorithm_1.setBounds(636, 261, 266, 41);
		frame.getContentPane().add(btnAlgorithm_1);
		 
		//Save original dataBase before making any changes
		if(btnAlgorithm_1.isSelected() || btnAlgorithm.isSelected() || btnFilterByLocation.isSelected() || btnFilterByDevice.isSelected() || btnTimeFilter.isSelected()){
			DataBase.setCopyDataBase();
		}

	}
}
