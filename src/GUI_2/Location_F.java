package GUI_2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.LocationFilter;
import GUI_Filter.NotFilter;
import GUI_Filter.OriginalFilter;
import GUI_Filter.SamplesPredicate;
import GUI_Filter.TimeFilter;
import GUI_Filter.Wraper;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Location_F extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137241468746623260L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location_F frame = new Location_F();
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
	public Location_F() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(textField.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
				}
			}
		});

		textField.setBounds(112, 119, 166, 50);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(textField_1.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
				}

			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(335, 119, 166, 50);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(textField_2.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
				}

			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(559, 119, 166, 50);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(textField_3.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
				}

			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(112, 220, 166, 50);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(textField_4.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
				}

			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(335, 220, 166, 50);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(textField_5.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
				}
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(559, 220, 166, 50);
		contentPane.add(textField_5);

		JButton btnSaveFilter = new JButton("Save Current Filter");
		btnSaveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Wraper.writeCurrentFilter(MainFrame.no1Filter);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveFilter.setBackground(new Color(0, 102, 102));
		btnSaveFilter.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSaveFilter.setBounds(14, 451, 196, 29);
		contentPane.add(btnSaveFilter);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Filter with range");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton.setBounds(14, 336, 195, 25);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnWithoutRange = new JRadioButton("Filter without range");
		rdbtnWithoutRange.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnWithoutRange.setBounds(14, 375, 219, 25);
		contentPane.add(rdbtnWithoutRange);

		JRadioButton rdbtnAddTimeFilter = new JRadioButton("Add Time Filter");
		rdbtnAddTimeFilter.setFont(new Font("Dialog", Font.BOLD, 17));
		rdbtnAddTimeFilter.setBounds(488, 365, 166, 25);
		contentPane.add(rdbtnAddTimeFilter);

		JRadioButton rdbtnOrTimeFilter = new JRadioButton("Or Time Filter");
		rdbtnOrTimeFilter.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnOrTimeFilter.setBounds(488, 426, 153, 25);
		contentPane.add(rdbtnOrTimeFilter);

		JRadioButton rdbtnAddDeviceFilter = new JRadioButton("Add Device Filter");
		rdbtnAddDeviceFilter.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnAddDeviceFilter.setBounds(488, 336, 166, 25);
		contentPane.add(rdbtnAddDeviceFilter);

		JRadioButton rdbtnOrDeviceFilter = new JRadioButton("Or Device Filter");
		rdbtnOrDeviceFilter.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnOrDeviceFilter.setBounds(488, 398, 177, 25);
		contentPane.add(rdbtnOrDeviceFilter);

		JRadioButton rdbtnOrLocationFilter = new JRadioButton("Or Location Filter");
		rdbtnOrLocationFilter.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnOrLocationFilter.setBounds(488, 454, 177, 25);
		contentPane.add(rdbtnOrLocationFilter);
		
				
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAddTimeFilter.isSelected()) {
					Time t = new Time("add");
					t.setVisible(true);

				}
				if (rdbtnOrTimeFilter.isSelected()) {
					Time t = new Time("or");
					t.setVisible(true);
				}
				if (rdbtnAddDeviceFilter.isSelected()) {
					Device d = new Device("add");
					d.setVisible(true);
				}
				if (rdbtnOrDeviceFilter.isSelected()) {
					Device d = new Device("or");
					d.setVisible(true);
				}
				if (rdbtnOrLocationFilter.isSelected()) {
					Location l = new Location("or");
					l.setVisible(true);
				}
			}
		});
		btnNext.setBackground(new Color(0, 102, 102));
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNext.setBounds(685, 455, 97, 25);
		contentPane.add(btnNext);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnNewRadioButton);
		group1.add(rdbtnWithoutRange);
		rdbtnNewRadioButton.setSelected(true);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnAddTimeFilter);
		group2.add(rdbtnOrTimeFilter);
		group2.add(rdbtnAddDeviceFilter);
		group2.add(rdbtnOrDeviceFilter);
		group2.add(rdbtnOrLocationFilter);
		rdbtnAddTimeFilter.setSelected(true);

		JLabel lblNewLabel = new JLabel("LONGITUDE");
		lblNewLabel.setBackground(UIManager.getColor("Button.shadow"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblNewLabel.setBounds(346, 83, 140, 20);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("LATITUDE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label.setBackground(SystemColor.controlShadow);
		label.setBounds(128, 83, 140, 20);
		contentPane.add(label);

		JLabel lblAltitude = new JLabel("ALTITUDE");
		lblAltitude.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltitude.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblAltitude.setBackground(SystemColor.controlShadow);
		lblAltitude.setBounds(570, 83, 140, 20);
		contentPane.add(lblAltitude);

		JLabel lblMax = new JLabel("MAX : ");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblMax.setBackground(SystemColor.controlShadow);
		lblMax.setBounds(14, 134, 75, 20);
		contentPane.add(lblMax);

		JLabel lblMin = new JLabel("MIN : ");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblMin.setBackground(SystemColor.controlShadow);
		lblMin.setBounds(14, 235, 75, 20);
		contentPane.add(lblMin);

		JButton btnNewButton = new JButton("Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (textField_3.getText().equals("") || textField.getText().equals("")
//						|| textField_4.getText().equals("") || textField_1.getText().equals("")
//						|| textField_5.getText().equals("") || textField_2.getText().equals("")) {
//					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Values!");
//				}
				if (Double.parseDouble(textField_3.getText()) > Double.parseDouble(textField.getText())
						|| Double.parseDouble(textField_4.getText()) > Double.parseDouble(textField_1.getText())
						|| Double.parseDouble(textField_5.getText()) > Double.parseDouble(textField_2.getText())) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Correct Max/Min Values!");
				} else {
					if (rdbtnNewRadioButton.isSelected()) {
						// Original filter
						MainFrame.filter1 = new OriginalFilter(new LocationFilter(
								Double.parseDouble(textField.getText()), Double.parseDouble(textField_3.getText()),
								Double.parseDouble(textField_1.getText()), Double.parseDouble(textField_4.getText()),
								Double.parseDouble(textField_2.getText()), Double.parseDouble(textField_5.getText())));
					}
					if (rdbtnWithoutRange.isSelected()) {
						// Not filter
						MainFrame.filter1 = new NotFilter(new LocationFilter(Double.parseDouble(textField.getText()),
								Double.parseDouble(textField_3.getText()), Double.parseDouble(textField_1.getText()),
								Double.parseDouble(textField_4.getText()), Double.parseDouble(textField_2.getText()),
								Double.parseDouble(textField_5.getText())));
					}
					SamplesPredicate.filterWithPredicate(MainFrame.no1Filter);
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(14, 412, 195, 29);
		contentPane.add(btnNewButton);

	}
}
