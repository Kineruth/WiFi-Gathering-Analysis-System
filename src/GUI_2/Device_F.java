package GUI_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.DataBase;
import GUI_Filter.DeviceFilter;
import GUI_Filter.NotFilter;
import GUI_Filter.OriginalFilter;
import GUI_Filter.SamplesPredicate;
import GUI_Filter.Wraper;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Device_F extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3335203380013471318L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Device_F frame = new Device_F();
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
	public Device_F() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelectADevice = new JLabel("Select A Device :");
		lblSelectADevice.setBackground(new Color(240, 240, 240));
		lblSelectADevice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectADevice.setFont(new Font("Sitka Text", Font.BOLD, 27));
		lblSelectADevice.setBounds(254, 61, 224, 97);
		contentPane.add(lblSelectADevice);

		textField = new JTextField();
		textField.setBounds(264, 174, 208, 37);
		contentPane.add(textField);
		textField.setColumns(10);

		JRadioButton rdbtnFilterWithDevice = new JRadioButton("Filter with device\r\n");
		rdbtnFilterWithDevice.setSelected(true);
		rdbtnFilterWithDevice.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFilterWithDevice.setBounds(21, 341, 195, 25);
		contentPane.add(rdbtnFilterWithDevice);

		JRadioButton rdbtnFilterWithoutDevice = new JRadioButton("Filter without device\r\n");
		rdbtnFilterWithoutDevice.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFilterWithoutDevice.setBounds(21, 380, 219, 25);
		contentPane.add(rdbtnFilterWithoutDevice);

		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Device!");
				} else {
					if (rdbtnFilterWithDevice.isSelected()) {
						MainFrame.filter1 = new OriginalFilter(new DeviceFilter(textField.getText()));
					}
					if (rdbtnFilterWithoutDevice.isSelected()) {
						MainFrame.filter1 = new NotFilter(new DeviceFilter(textField.getText()));
					}
					DataBase.setCopyDataBase();
					SamplesPredicate.filterWithPredicate(MainFrame.filter1);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(21, 417, 195, 29);
		contentPane.add(button);

		JButton button_1 = new JButton("Save Current Filter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty())
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Device!");
				else {
					if (rdbtnFilterWithDevice.isSelected())
						MainFrame.filter1 = new OriginalFilter(new DeviceFilter(textField.getText()));

					if (rdbtnFilterWithoutDevice.isSelected())
						MainFrame.filter1 = new NotFilter(new DeviceFilter(textField.getText()));
					try {
						Wraper.writeCurrentFilter(MainFrame.no1Filter);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBackground(new Color(0, 102, 102));
		button_1.setBounds(21, 456, 196, 29);
		contentPane.add(button_1);

		JRadioButton rdbtnAddLocationFilter = new JRadioButton("Add Location Filter");
		rdbtnAddLocationFilter.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnAddLocationFilter.setBounds(495, 341, 166, 25);
		contentPane.add(rdbtnAddLocationFilter);

		JRadioButton radioButton_3 = new JRadioButton("Add Time Filter");
		radioButton_3.setSelected(true);
		radioButton_3.setFont(new Font("Dialog", Font.BOLD, 17));
		radioButton_3.setBounds(495, 369, 166, 25);
		contentPane.add(radioButton_3);

		JRadioButton radioButton_4 = new JRadioButton("Or Device Filter");
		radioButton_4.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_4.setBounds(495, 459, 177, 25);
		contentPane.add(radioButton_4);

		JRadioButton radioButton_5 = new JRadioButton("Or Time Filter");
		radioButton_5.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_5.setBounds(495, 431, 153, 25);
		contentPane.add(radioButton_5);

		JRadioButton radioButton_6 = new JRadioButton("Or Location Filter");
		radioButton_6.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_6.setBounds(495, 398, 177, 25);
		contentPane.add(radioButton_6);

		JButton button_2 = new JButton("Next");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Device!");
				} else {
					if (rdbtnFilterWithDevice.isSelected())
						MainFrame.filter1 = new OriginalFilter(new DeviceFilter(textField.getText()));

					if (rdbtnFilterWithoutDevice.isSelected())
						MainFrame.filter1 = new NotFilter(new DeviceFilter(textField.getText()));
					if (rdbtnAddLocationFilter.isSelected()) {
						Location l = new Location("add");
						l.setVisible(true);
					}
					if (radioButton_6.isSelected()) {
						Location l = new Location("or");
						l.setVisible(true);
					}
					if (radioButton_3.isSelected()) {
						Time t = new Time("add");
						t.setVisible(true);
					}
					if (radioButton_5.isSelected()) {
						Time t = new Time("or");
						t.setVisible(true);
					}
					if (radioButton_4.isSelected()) {
						Device d = new Device("or");
						d.setVisible(true);
					}
				}

			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_2.setBackground(new Color(0, 102, 102));
		button_2.setBounds(692, 460, 97, 25);
		contentPane.add(button_2);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnFilterWithDevice);
		group1.add(rdbtnFilterWithoutDevice);
		rdbtnFilterWithDevice.setSelected(true);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnAddLocationFilter);
		group2.add(radioButton_3);
		group2.add(radioButton_4);
		group2.add(radioButton_5);
		group2.add(radioButton_6);
		rdbtnAddLocationFilter.setSelected(true);
	}
}
