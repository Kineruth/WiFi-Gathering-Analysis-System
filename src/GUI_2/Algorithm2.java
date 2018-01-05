package GUI_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.Wraper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Algorithm2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Algorithm2 frame = new Algorithm2();
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
	public Algorithm2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 748, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChooseToEnter = new JLabel("Choose To Enter :  ");
		lblChooseToEnter.setFont(new Font("Sitka Text", Font.BOLD, 25));
		lblChooseToEnter.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseToEnter.setForeground(SystemColor.activeCaptionText);
		lblChooseToEnter.setBackground(SystemColor.menu);
		lblChooseToEnter.setBounds(11, 16, 252, 78);
		contentPane.add(lblChooseToEnter);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("A Sample : ");
		rdbtnNewRadioButton.setForeground(SystemColor.desktop);
		rdbtnNewRadioButton.setBackground(SystemColor.menu);
		rdbtnNewRadioButton.setFont(new Font("Sitka Text", Font.BOLD, 21));
		rdbtnNewRadioButton.setBounds(11, 106, 155, 29);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Up To 3 Macs & Signals :");
		rdbtnNewRadioButton_1.setFont(new Font("Sitka Text", Font.BOLD, 20));
		rdbtnNewRadioButton_1.setBounds(11, 212, 290, 29);
		contentPane.add(rdbtnNewRadioButton_1);

		JLabel lblMac = new JLabel("Enter Mac :");
		lblMac.setHorizontalAlignment(SwingConstants.CENTER);
		lblMac.setFont(new Font("Sitka Text", Font.BOLD, 20));
		lblMac.setBounds(365, 223, 125, 31);
		contentPane.add(lblMac);

		JLabel lblSignal = new JLabel("Enter Signal : ");
		lblSignal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignal.setFont(new Font("Sitka Text", Font.BOLD, 20));
		lblSignal.setBounds(536, 223, 155, 31);
		contentPane.add(lblSignal);

		JCheckBox chckbxMac = new JCheckBox("Mac 1:");
		chckbxMac.setFont(new Font("Sitka Text", Font.BOLD, 19));
		chckbxMac.setBounds(221, 267, 112, 29);
		contentPane.add(chckbxMac);

		JCheckBox chckbxMac_1 = new JCheckBox("Mac 2:");
		chckbxMac_1.setFont(new Font("Sitka Text", Font.BOLD, 19));
		chckbxMac_1.setBounds(221, 323, 112, 29);
		contentPane.add(chckbxMac_1);

		JCheckBox chckbxMac_2 = new JCheckBox("Mac 3:");
		chckbxMac_2.setFont(new Font("Sitka Text", Font.BOLD, 19));
		chckbxMac_2.setBounds(221, 378, 112, 29);
		contentPane.add(chckbxMac_2);

		textField = new JTextField();
		textField.setBounds(11, 158, 701, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(349, 266, 160, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(349, 322, 160, 29);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(349, 377, 160, 29);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(559, 268, 104, 29);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(559, 323, 104, 29);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(559, 378, 104, 29);
		contentPane.add(textField_6);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnNewRadioButton);
		group1.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton.setSelected(true);

		JButton btnGetLocation = new JButton("Get Location");
		btnGetLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mac1 = "", signal1 = "", mac2 = "", signal2 = "", mac3 = "", signal3 = "";
				boolean flag = false;
				// send to location
				if (rdbtnNewRadioButton.isSelected()) {
					if (textField.getText().isEmpty())
						JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Sample!");
					else {
						Wraper.createAlgo2(Wraper.convertToSample(textField.getText()));
					}
				}
				if (rdbtnNewRadioButton_1.isSelected()) {
					if (chckbxMac.isSelected()) {
						mac1 = textField_1.getText();
						signal1 = textField_4.getText();
						if (mac1.isEmpty() || signal1.isEmpty())
							JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Values!");
						else
							flag = true;
					}
					if (chckbxMac_1.isSelected()) {
						mac2 = textField_2.getText();
						signal2 = textField_5.getText();
						if (mac2.isEmpty() || signal2.isEmpty())
							JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Values!");
						else
							flag = true;
					}
					if (chckbxMac_2.isSelected()) {
						mac3 = textField_3.getText();
						signal3 = textField_6.getText();
						if (mac3.isEmpty() || signal3.isEmpty())
							JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Values!");
						else
							flag = true;
					}
					if (flag)
						Wraper.createAlgo2(Wraper.convertMacsToSample(mac1, mac2, mac3, signal1, signal2, signal3));
					flag = false;
				}
			}
		});
		btnGetLocation.setBackground(new Color(211, 211, 211));
		btnGetLocation.setForeground(new Color(47, 79, 79));
		btnGetLocation.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGetLocation.setBounds(252, 450, 170, 29);
		contentPane.add(btnGetLocation);
	}
}
