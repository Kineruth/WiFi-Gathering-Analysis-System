package GUI_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Filter.Wraper;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Algorithm1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Algorithm1 frame = new Algorithm1();
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
	public Algorithm1() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEnterMacTo = new JLabel("Enter Mac To Get Location : ");
		lblEnterMacTo.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblEnterMacTo.setBounds(50, 60, 359, 20);
		contentPane.add(lblEnterMacTo);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 19));
		textField.setBounds(97, 126, 215, 46);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty())
					JOptionPane.showMessageDialog(new JFrame(), "Error :: Must Enter Mac!");

				else {
					Wraper.createAlgo1(textField.getText());
				}
			}
		});
		btnGo.setFont(new Font("Sitka Text", Font.BOLD, 20));
		btnGo.setBounds(151, 228, 115, 29);
		contentPane.add(btnGo);
	}

}
