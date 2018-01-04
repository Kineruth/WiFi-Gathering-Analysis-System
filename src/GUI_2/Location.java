package GUI_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.AndFilter;
import GUI_Filter.LocationFilter;
import GUI_Filter.NotFilter;
import GUI_Filter.OrFilter;
import GUI_Filter.OriginalFilter;
import GUI_Filter.SamplesPredicate;

import javax.swing.JRadioButton;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Location extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private static String filterChoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location frame = new Location(filterChoice);
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
	public Location(String i) {
		Location.filterChoice = i;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 814, 544);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 797, 489);
		getContentPane().add(panel);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(112, 119, 166, 50);
		panel.add(textField);
//		try {
//			Double.parseDouble(textField.getText());
//		} catch (NumberFormatException e1) {
//			JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
//		}

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(335, 119, 166, 50);
		panel.add(textField_1);
//		try {
//			Double.parseDouble(textField_1.getText());
//		} catch (NumberFormatException e1) {
//			JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
//		}

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(559, 119, 166, 50);
		panel.add(textField_2);
//		try {
//			Double.parseDouble(textField_2.getText());
//		} catch (NumberFormatException e1) {
//			JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
//		}

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(112, 220, 166, 50);
		panel.add(textField_3);
//		try {
//			Double.parseDouble(textField_3.getText());
//		} catch (NumberFormatException e1) {
//			JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
//		}

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(335, 220, 166, 50);
		panel.add(textField_4);
//		try {
//			Double.parseDouble(textField_4.getText());
//		} catch (NumberFormatException e1) {
//			JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
//		}

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(559, 220, 166, 50);
		panel.add(textField_5);
//		try {
//			Double.parseDouble(textField_5.getText());
//		} catch (NumberFormatException e1) {
//			JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Double Number");
//		}

		JButton button = new JButton("Save Current Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// send to be saved as serialized txt
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBackground(new Color(0, 102, 102));
		button.setBounds(267, 425, 196, 29);
		panel.add(button);

		JRadioButton radioButton = new JRadioButton("Filter with range");
		radioButton.setSelected(true);
		radioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		radioButton.setBounds(267, 310, 195, 25);
		panel.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("Filter without range");
		radioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		radioButton_1.setBounds(267, 349, 219, 25);
		panel.add(radioButton_1);

		JLabel label = new JLabel("LONGITUDE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label.setBackground(SystemColor.controlShadow);
		label.setBounds(346, 83, 140, 20);
		panel.add(label);

		JLabel label_1 = new JLabel("LATITUDE");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label_1.setBackground(SystemColor.controlShadow);
		label_1.setBounds(128, 83, 140, 20);
		panel.add(label_1);

		JLabel label_2 = new JLabel("ALTITUDE");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label_2.setBackground(SystemColor.controlShadow);
		label_2.setBounds(570, 83, 140, 20);
		panel.add(label_2);

		JLabel label_3 = new JLabel("MAX : ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label_3.setBackground(SystemColor.controlShadow);
		label_3.setBounds(14, 134, 75, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("MIN : ");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label_4.setBackground(SystemColor.controlShadow);
		label_4.setBounds(14, 235, 75, 20);
		panel.add(label_4);

			MainFrame.filter2 = new LocationFilter(Double.parseDouble(textField.getText()),
				Double.parseDouble(textField_3.getText()), Double.parseDouble(textField_1.getText()),
				Double.parseDouble(textField_4.getText()), Double.parseDouble(textField_2.getText()),
				Double.parseDouble(textField_5.getText()));
		

		JButton button_2 = new JButton("Filter");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(textField_3.getText()) > Double.parseDouble(textField.getText())
						|| Double.parseDouble(textField_4.getText()) > Double.parseDouble(textField_1.getText())
						|| Double.parseDouble(textField_5.getText()) > Double.parseDouble(textField_2.getText())) 
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Correct Max/Min Values!");
				else{
				if (radioButton.isSelected()) {
					// original
					if (Location.filterChoice.equals("add"))
						MainFrame.connectFilter = new AndFilter(MainFrame.filter1,
								new OriginalFilter(MainFrame.filter2));
					if (Location.filterChoice.equals("or"))
						MainFrame.connectFilter = new OrFilter(MainFrame.filter1,
								new OriginalFilter(MainFrame.filter2));
				}
				if (radioButton_1.isSelected()) {
					// not
					if (Location.filterChoice.equals("add"))
						MainFrame.connectFilter = new AndFilter(MainFrame.filter1, new NotFilter(MainFrame.filter2));

					if (Location.filterChoice.equals("or"))
						MainFrame.connectFilter = new OrFilter(MainFrame.filter1, new NotFilter(MainFrame.filter2));
				}
				SamplesPredicate.filterWithPredicate(MainFrame.connectFilter);
			}
				
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_2.setBounds(267, 386, 195, 29);
		panel.add(button_2);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(radioButton);
		group1.add(radioButton_1);
		radioButton.setSelected(true);
	}

}
