package GUI_2;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class LocationFilter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField txtLat;
	private JTextField txtLon;
	private JTextField txtAlt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocationFilter frame = new LocationFilter();
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
	public LocationFilter() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 788, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(91, 119, 166, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(316, 119, 166, 50);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(551, 119, 166, 50);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 220, 166, 50);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(316, 220, 166, 50);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(551, 220, 166, 50);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("max");
		textField_6.setColumns(10);
		textField_6.setBounds(41, 119, 38, 22);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText("max");
		textField_7.setColumns(10);
		textField_7.setBounds(266, 119, 38, 22);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText("max");
		textField_8.setColumns(10);
		textField_8.setBounds(501, 119, 38, 22);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("min");
		textField_9.setColumns(10);
		textField_9.setBounds(41, 220, 38, 22);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("min");
		textField_10.setColumns(10);
		textField_10.setBounds(269, 220, 38, 22);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("min");
		textField_11.setColumns(10);
		textField_11.setBounds(501, 220, 38, 22);
		contentPane.add(textField_11);
		
		txtLat = new JTextField();
		txtLat.setText("lat\r\n");
		txtLat.setBounds(117, 74, 116, 22);
		contentPane.add(txtLat);
		txtLat.setColumns(10);
		
		txtLon = new JTextField();
		txtLon.setText("lon");
		txtLon.setColumns(10);
		txtLon.setBounds(340, 74, 116, 22);
		contentPane.add(txtLon);
		
		txtAlt = new JTextField();
		txtAlt.setText("alt");
		txtAlt.setColumns(10);
		txtAlt.setBounds(561, 74, 116, 22);
		contentPane.add(txtAlt);
		
		JButton btnSaveFilter = new JButton("save current filter");
		btnSaveFilter.setBounds(37, 382, 145, 25);
		contentPane.add(btnSaveFilter);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("with range");
		rdbtnNewRadioButton.setBounds(38, 317, 127, 25);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnWithoutRange = new JRadioButton("without range");
		rdbtnWithoutRange.setBounds(38, 348, 127, 25);
		contentPane.add(rdbtnWithoutRange);
		
		JRadioButton rdbtnAddAnotherFilter = new JRadioButton("add another filter");
		rdbtnAddAnotherFilter.setBounds(635, 382, 127, 25);
		contentPane.add(rdbtnAddAnotherFilter);
		
		JRadioButton rdbtnOrAnotherFilter = new JRadioButton("or another filter");
		rdbtnOrAnotherFilter.setBounds(635, 412, 127, 25);
		contentPane.add(rdbtnOrAnotherFilter);
		
		JButton btnNext = new JButton("next");
		btnNext.setBounds(645, 454, 97, 25);
		contentPane.add(btnNext);
	}
}
