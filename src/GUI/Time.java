package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.AndFilter;
import GUI_Filter.DataBase;
import GUI_Filter.Filter;
import GUI_Filter.NotFilter;
import GUI_Filter.OrFilter;
import GUI_Filter.OriginalFilter;
import GUI_Filter.SamplesPredicate;
import GUI_Filter.TimeFilter;
import GUI_Filter.Wraper;

import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;

public class Time extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7974167552892378616L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Time frame = new Time();
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
	public Time() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("MAX : ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label.setBackground(SystemColor.controlShadow);
		label.setBounds(15, 134, 75, 20);
		contentPane.add(label);

		JLabel label_1 = new JLabel("MIN : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label_1.setBackground(SystemColor.controlShadow);
		label_1.setBounds(15, 195, 75, 20);
		contentPane.add(label_1);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner.setBounds(125, 120, 55, 45);
		contentPane.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 60, 1));
		spinner_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_1.setBounds(195, 120, 55, 45);
		contentPane.add(spinner_1);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		spinner_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_2.setBounds(125, 181, 55, 45);
		contentPane.add(spinner_2);

		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(1, 1, 60, 1));
		spinner_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_3.setBounds(195, 181, 55, 45);
		contentPane.add(spinner_3);

		JLabel label_2 = new JLabel("HH:MM              DD/MM/YY");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Sitka Text", Font.BOLD, 24));
		label_2.setBounds(115, 79, 370, 25);
		contentPane.add(label_2);

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinner_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_4.setBounds(305, 120, 55, 45);
		contentPane.add(spinner_4);

		JSpinner spinner_5 = new JSpinner();
		spinner_5.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_5.setBounds(375, 120, 55, 45);
		contentPane.add(spinner_5);

		JSpinner spinner_6 = new JSpinner();
		spinner_6.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
		spinner_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_6.setBounds(445, 120, 87, 45);
		contentPane.add(spinner_6);

		JSpinner spinner_7 = new JSpinner();
		spinner_7.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
		spinner_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_7.setBounds(445, 181, 87, 45);
		contentPane.add(spinner_7);

		JSpinner spinner_8 = new JSpinner();
		spinner_8.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_8.setBounds(375, 181, 55, 45);
		contentPane.add(spinner_8);

		JSpinner spinner_9 = new JSpinner();
		spinner_9.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinner_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_9.setBounds(305, 181, 55, 45);
		contentPane.add(spinner_9);

		JLabel label_3 = new JLabel("Time format: \r\n");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Sitka Text", Font.BOLD, 24));
		label_3.setBounds(125, 0, 293, 73);
		contentPane.add(label_3);

		JRadioButton radioButton = new JRadioButton("Filter with time\r\n");
		radioButton.setSelected(true);
		radioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		radioButton.setBounds(177, 265, 195, 25);
		contentPane.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("Filter without time\r\n");
		radioButton_1.setSelected(true);
		radioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		radioButton_1.setBounds(177, 304, 219, 25);
		contentPane.add(radioButton_1);

		Filter f = DataBase.getCurrentFilter();
		Filter f2 = new TimeFilter((int) spinner_6.getValue(), (int) spinner_5.getValue(), (int) spinner_4.getValue(),
				(int) spinner_7.getValue(), (int) spinner_8.getValue(), (int) spinner_9.getValue(),
				(int) spinner.getValue(), (int) spinner_1.getValue(), (int) spinner_2.getValue(),
				(int) spinner_3.getValue());

		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Wraper.checkDateMinMax((int) spinner_6.getValue(), (int) spinner_5.getValue(), (int) spinner_4.getValue(),
						(int) spinner_7.getValue(), (int) spinner_8.getValue(), (int) spinner_9.getValue(),
						(int) spinner.getValue(), (int) spinner_1.getValue(), (int) spinner_2.getValue(),
						(int) spinner_3.getValue()))
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Correct Max/Min Values!");
				else {
					if (radioButton.isSelected()) {
						// filter with range
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new AndFilter(f, new OriginalFilter(f2)));
						if (DataBase.getFilterChoice().equals("or"))
							DataBase.setCurrentFilter(new OrFilter(f, new OriginalFilter(f2)));
					}
					if (radioButton_1.isSelected()) {
						// without
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new AndFilter(f, new NotFilter(f2)));

						if (DataBase.getFilterChoice().equals("or"))
							DataBase.setCurrentFilter(new OrFilter(f, new NotFilter(f2)));
					}
					SamplesPredicate.filterWithPredicate(DataBase.getCurrentFilter());
				}

			}

		});
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(177, 341, 195, 29);
		contentPane.add(button);

		JButton button_1 = new JButton("Save Current Filter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Wraper.checkDateMinMax((int) spinner_6.getValue(), (int) spinner_5.getValue(), (int) spinner_4.getValue(),
						(int) spinner_7.getValue(), (int) spinner_8.getValue(), (int) spinner_9.getValue(),
						(int) spinner.getValue(), (int) spinner_1.getValue(), (int) spinner_2.getValue(),
						(int) spinner_3.getValue()))
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Correct Max/Min Values!");
				else {
					if (radioButton.isSelected()) {
						// filter with range
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new AndFilter(f, new OriginalFilter(f2)));
						if (DataBase.getFilterChoice().equals("or"))
							DataBase.setCurrentFilter(new OrFilter(f, new OriginalFilter(f2)));
					}
					if (radioButton_1.isSelected()) {
						// without
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new AndFilter(f, new NotFilter(f2)));

						if (DataBase.getFilterChoice().equals("or"))
							DataBase.setCurrentFilter(new OrFilter(f, new NotFilter(f2)));
					}
					try {
						Wraper.writeCurrentFilter(DataBase.getCurrentFilter());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBackground(new Color(0, 102, 102));
		button_1.setBounds(177, 380, 196, 29);
		contentPane.add(button_1);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(radioButton);
		group1.add(radioButton_1);
		radioButton.setSelected(true);

	}
}
