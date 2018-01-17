package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.DataBase;
import SQL.Data;
import SQL.DataBaseTable;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Table extends JFrame {

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
					Table frame = new Table();
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
	public Table() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(213, 81, 232, 32);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(213, 147, 232, 32);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(213, 210, 232, 32);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(213, 269, 232, 32);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(213, 335, 232, 32);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(213, 398, 232, 32);
		contentPane.add(textField_5);

		JLabel lblNewLabel = new JLabel("Fill In Information");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(182, 0, 289, 82);
		contentPane.add(lblNewLabel);

		JLabel lblIp = new JLabel("IP : ");
		lblIp.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblIp.setBounds(65, 87, 69, 20);
		contentPane.add(lblIp);

		JLabel lblPort = new JLabel("Port : ");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblPort.setBounds(65, 147, 69, 20);
		contentPane.add(lblPort);

		JLabel lblUser = new JLabel("User : ");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblUser.setBounds(65, 216, 69, 20);
		contentPane.add(lblUser);

		JLabel lblUrl = new JLabel("Password : ");
		lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrl.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblUrl.setBounds(65, 275, 110, 20);
		contentPane.add(lblUrl);

		JLabel lblUrl_1 = new JLabel("URL : ");
		lblUrl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrl_1.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblUrl_1.setBounds(65, 341, 69, 20);
		contentPane.add(lblUrl_1);

		JLabel lblTableName = new JLabel("Table Name : ");
		lblTableName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableName.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblTableName.setBounds(48, 404, 127, 20);
		contentPane.add(lblTableName);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(213, 454, 232, 32);
		contentPane.add(textField_6);

		JLabel lblDatabaseName = new JLabel("DataBase Name : ");
		lblDatabaseName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseName.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lblDatabaseName.setBounds(27, 460, 148, 20);
		contentPane.add(lblDatabaseName);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
						|| textField_3.getText().isEmpty() || textField_4.getText().isEmpty()
						|| textField_5.getText().isEmpty() || textField_6.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Fill Information!");
				} else {
					Data d = new Data(textField.getText(), textField_1.getText(), textField_6.getText(),
							textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText());
					new Thread(new Runnable() {

						@Override
						public void run() {
							while(true)
							{
								try {
									
									DataBaseTable.readTable(d);
								} catch (ClassNotFoundException e1) {
									JOptionPane.showMessageDialog(new JFrame(), "Couldn't Upload Table!");
									e1.printStackTrace();
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
							}

						}
					}).start();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Sitka Text", Font.BOLD, 20));
		btnNewButton.setBounds(268, 533, 115, 29);
		contentPane.add(btnNewButton);
	}
}
