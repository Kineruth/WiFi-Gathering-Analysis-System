package GUI_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Time_F extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Time_F frame = new Time_F();
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
	public Time_F() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnFilterWithoutTime = new JRadioButton("Filter without time\r\n");
		rdbtnFilterWithoutTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFilterWithoutTime.setBounds(12, 368, 219, 25);
		contentPane.add(rdbtnFilterWithoutTime);

		JRadioButton rdbtnFilterWithTime = new JRadioButton("Filter with time\r\n");
		rdbtnFilterWithTime.setSelected(true);
		rdbtnFilterWithTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnFilterWithTime.setBounds(12, 329, 195, 25);
		contentPane.add(rdbtnFilterWithTime);

		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(12, 405, 195, 29);
		contentPane.add(button);

		JButton button_1 = new JButton("Save Current Filter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBackground(new Color(0, 102, 102));
		button_1.setBounds(12, 444, 196, 29);
		contentPane.add(button_1);

		JRadioButton radioButton_2 = new JRadioButton("Or Location Filter");
		radioButton_2.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_2.setBounds(486, 447, 177, 25);
		contentPane.add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("Or Time Filter");
		radioButton_3.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_3.setBounds(486, 419, 153, 25);
		contentPane.add(radioButton_3);

		JRadioButton radioButton_4 = new JRadioButton("Or Device Filter");
		radioButton_4.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_4.setBounds(486, 391, 177, 25);
		contentPane.add(radioButton_4);

		JRadioButton rdbtnAddLocationFilter = new JRadioButton("Add Location Filter");
		rdbtnAddLocationFilter.setSelected(true);
		rdbtnAddLocationFilter.setFont(new Font("Dialog", Font.BOLD, 17));
		rdbtnAddLocationFilter.setBounds(486, 358, 177, 25);
		contentPane.add(rdbtnAddLocationFilter);

		JRadioButton radioButton_6 = new JRadioButton("Add Device Filter");
		radioButton_6.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_6.setBounds(486, 329, 166, 25);
		contentPane.add(radioButton_6);

		JButton button_2 = new JButton("Next");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_2.setBackground(new Color(0, 102, 102));
		button_2.setBounds(683, 448, 97, 25);
		contentPane.add(button_2);

		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.BOLD, 18));
		spinner.setModel(
				new SpinnerDateModel(new Date(1514757600000L), new Date(1514757600000L), null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(278, 154, 184, 42);
		contentPane.add(spinner);

		JLabel lblNewLabel = new JLabel("Time format: HH:MM  DD/MM/YY\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblNewLabel.setBounds(77, 68, 586, 73);
		contentPane.add(lblNewLabel);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(rdbtnFilterWithoutTime);
		group1.add(rdbtnFilterWithTime);
		rdbtnFilterWithoutTime.setSelected(true);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(radioButton_2);
		group2.add(radioButton_3);
		group2.add(radioButton_4);
		group2.add(rdbtnAddLocationFilter);
		group2.add(radioButton_6);
		radioButton_2.setSelected(true);
	}

}
