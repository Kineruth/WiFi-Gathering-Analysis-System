package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.AndFilter;
import GUI_Filter.DataBase;
import GUI_Filter.DeviceFilter;
import GUI_Filter.Filter;
import GUI_Filter.LocationFilter;
import GUI_Filter.NotFilter;
import GUI_Filter.OrFilter;
import GUI_Filter.OriginalFilter;
import GUI_Filter.SamplesPredicate;
import GUI_Filter.Wraper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Device extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7687617504704647259L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Device frame = new Device();
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
	public Device() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Select A Device :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Sitka Text", Font.BOLD, 27));
		label.setBackground(SystemColor.menu);
		label.setBounds(105, 16, 224, 97);
		contentPane.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(115, 129, 208, 37);
		contentPane.add(textField);

		JRadioButton radioButton = new JRadioButton("Filter with device\r\n");
		radioButton.setSelected(true);
		radioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		radioButton.setBounds(115, 206, 195, 25);
		contentPane.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("Filter without device\r\n");
		radioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		radioButton_1.setBounds(115, 245, 219, 25);
		contentPane.add(radioButton_1);

		Filter f = DataBase.getCurrentFilter();
		Filter f2 = new DeviceFilter(textField.getText());

		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Device!");
				} else {
					if (radioButton.isSelected()) {
						// original
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new AndFilter(f, new OriginalFilter(f2)));
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter( new AndFilter(f, new NotFilter(f2)));
					}
					if (radioButton_1.isSelected()) {
						// not
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new OrFilter(f, new OriginalFilter(f2)));
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter( new OrFilter(f, new NotFilter(f2)));
					}
					SamplesPredicate.filterWithPredicate(DataBase.getCurrentFilter());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(115, 282, 195, 29);
		contentPane.add(button);

		JButton button_1 = new JButton("Save Current Filter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter A Device!");
				} else {
					if (radioButton.isSelected()) {
						// original
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter( new AndFilter(f, new OriginalFilter(f2)));
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new AndFilter(f, new NotFilter(f2)));
					}
					if (radioButton_1.isSelected()) {
						// not
						if (DataBase.getFilterChoice().equals("add"))
							DataBase.setCurrentFilter(new OrFilter(f, new OriginalFilter(f2)));
						if (DataBase.getFilterChoice().equals("add"))
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
		button_1.setBounds(115, 321, 196, 29);
		contentPane.add(button_1);

		ButtonGroup group1 = new ButtonGroup();
		group1.add(radioButton);
		group1.add(radioButton_1);
		radioButton.setSelected(true);
	}

}
