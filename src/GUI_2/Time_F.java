package GUI_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.NotFilter;
import GUI_Filter.OriginalFilter;
import GUI_Filter.SamplesPredicate;
import GUI_Filter.TimeFilter;
import GUI_Filter.Wraper;

import javax.swing.JRadioButton;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.SystemColor;

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

		
		JLabel lblHhmmDdmmyy = new JLabel("HH:MM              DD/MM/YY");
		lblHhmmDdmmyy.setHorizontalAlignment(SwingConstants.CENTER);
		lblHhmmDdmmyy.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblHhmmDdmmyy.setBounds(192, 116, 370, 25);
		contentPane.add(lblHhmmDdmmyy);
		
		int maxY,  maxM,  maxD,  minY,  minM,  minD,  maxH,  maxMin,  minH,  minMin;
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner.setBounds(202, 157, 55, 45);
		contentPane.add(spinner);
		maxH = (int) spinner.getValue();
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 60, 1));
		spinner_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_1.setBounds(272, 157, 55, 45);
		contentPane.add(spinner_1);
		maxMin =(int) spinner_1.getValue();
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinner_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_2.setBounds(382, 157, 55, 45);
		contentPane.add(spinner_2);
		maxD =(int) spinner_2.getValue();
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_3.setBounds(452, 157, 55, 45);
		contentPane.add(spinner_3);
		maxM =(int) spinner_3.getValue();
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
		spinner_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_4.setBounds(522, 157, 87, 45);
		contentPane.add(spinner_4);
		maxY =(int) spinner_4.getValue();
		
		JSpinner spinner_5 = new JSpinner();
		spinner_5.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		spinner_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_5.setBounds(202, 218, 55, 45);
		contentPane.add(spinner_5);
		minH =(int) spinner_5.getValue();
		
		JSpinner spinner_6 = new JSpinner();
		spinner_6.setModel(new SpinnerNumberModel(1, 1, 60, 1));
		spinner_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_6.setBounds(272, 218, 55, 45);
		contentPane.add(spinner_6);
		minMin =(int) spinner_6.getValue();

		JSpinner spinner_7 = new JSpinner();
		spinner_7.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinner_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_7.setBounds(382, 218, 55, 45);
		contentPane.add(spinner_7);
		minD =(int) spinner_7.getValue();

		JSpinner spinner_8 = new JSpinner();
		spinner_8.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		spinner_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_8.setBounds(452, 218, 55, 45);
		contentPane.add(spinner_8);
		minM=(int) spinner_8.getValue();

		JSpinner spinner_9 = new JSpinner();
		spinner_9.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
		spinner_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinner_9.setBounds(522, 218, 87, 45);
		contentPane.add(spinner_9);
		minY =(int) spinner_9.getValue();

		JLabel label = new JLabel("MAX : ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label.setBackground(SystemColor.controlShadow);
		label.setBounds(92, 171, 75, 20);
		contentPane.add(label);

		JLabel label_1 = new JLabel("MIN : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Sitka Text", Font.BOLD, 22));
		label_1.setBackground(SystemColor.controlShadow);
		label_1.setBounds(92, 232, 75, 20);
		contentPane.add(label_1);

		JLabel lblNewLabel = new JLabel("Time format: \r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblNewLabel.setBounds(235, 37, 293, 73);
		contentPane.add(lblNewLabel);

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
				if(minY>maxY || minM > maxM || minD>maxD || minH > maxH || minMin>maxMin){
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Correct Max/Min Values!");
				}
				else{
					if (rdbtnFilterWithTime.isSelected()) { // original filter
						MainFrame.filter1 = new OriginalFilter(new TimeFilter(maxY, maxM, maxD, minY, minM, minD, maxH, maxMin, minH, minMin));
						SamplesPredicate.filterWithPredicate(MainFrame.filter1);
					}
					if (rdbtnFilterWithoutTime.isSelected()) { // not filter
						MainFrame.filter1 = new NotFilter(new TimeFilter(maxY, maxM, maxD, minY, minM, minD, maxH, maxMin, minH, minMin));
						SamplesPredicate.filterWithPredicate(MainFrame.filter1);
					}
				}
				
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(12, 405, 195, 29);
		contentPane.add(button);

		JButton button_1 = new JButton("Save Current Filter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Wraper.writeCurrentFilter(MainFrame.filter1) ;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBackground(new Color(0, 102, 102));
		button_1.setBounds(12, 444, 196, 29);
		contentPane.add(button_1);

		JRadioButton radioButton_2 = new JRadioButton("Or Location Filter");
		radioButton_2.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_2.setBounds(486, 424, 177, 25);
		contentPane.add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("Or Time Filter");
		radioButton_3.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_3.setBounds(486, 452, 153, 25);
		contentPane.add(radioButton_3);

		JRadioButton radioButton_4 = new JRadioButton("Or Device Filter");
		radioButton_4.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_4.setBounds(486, 395, 177, 25);
		contentPane.add(radioButton_4);

		JRadioButton rdbtnAddLocationFilter = new JRadioButton("Add Location Filter");
		rdbtnAddLocationFilter.setSelected(true);
		rdbtnAddLocationFilter.setFont(new Font("Dialog", Font.BOLD, 17));
		rdbtnAddLocationFilter.setBounds(486, 362, 177, 25);
		contentPane.add(rdbtnAddLocationFilter);

		JRadioButton radioButton_6 = new JRadioButton("Add Device Filter");
		radioButton_6.setFont(new Font("Dialog", Font.BOLD, 16));
		radioButton_6.setBounds(486, 329, 166, 25);
		contentPane.add(radioButton_6);

		JButton button_2 = new JButton("Next");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAddLocationFilter.isSelected()) {
					Location l = new Location(0);
					l.setVisible(true);

				}
				if (radioButton_2.isSelected()) {
					Location l = new Location(1);
					l.setVisible(true);
				}
				if (radioButton_6.isSelected()) {
					Device d = new Device(0);
					d.setVisible(true);
				}
				if (radioButton_4.isSelected()) {
					Device d = new Device(1);
					d.setVisible(true);
				}
				if (radioButton_3.isSelected()) {
					Time t = new Time(1);
					t.setVisible(true);
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_2.setBackground(new Color(0, 102, 102));
		button_2.setBounds(683, 448, 97, 25);
		contentPane.add(button_2);

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

		JLabel lblChooseASecond = new JLabel("Choose A Second Filter : ");
		lblChooseASecond.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblChooseASecond.setBounds(487, 295, 238, 20);
		contentPane.add(lblChooseASecond);


	}
}
