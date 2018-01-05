package GUI_2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI_Filter.DataBase;
import GUI_Filter.SamplesPredicate;
import GUI_Filter.Wraper;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UploadFilter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6736302074444064266L;
	private JPanel contentPane;
	private static boolean uploaded = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadFilter frame = new UploadFilter();
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
	public UploadFilter() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnUploadFilter = new JButton("Upload Filter");
		btnUploadFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Choose Csv File");
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					try {
						DataBase.setCurrentFilter(Wraper.readFilterFile(chooser.getSelectedFile().getAbsolutePath()));
					} catch (ClassNotFoundException | IOException e1) {
						JOptionPane.showMessageDialog(new JFrame(), "Error :: Could Not Upload Given File!");
						e1.printStackTrace();
					}
				}
				UploadFilter.uploaded = true;
			}
		});
		btnUploadFilter.setFont(new Font("Sitka Text", Font.BOLD, 24));
		btnUploadFilter.setBounds(104, 74, 201, 66);
		contentPane.add(btnUploadFilter);

		JButton btnClickToFilter = new JButton("Click To Filter");
		btnClickToFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UploadFilter.uploaded == false)
					JOptionPane.showMessageDialog(new JFrame(), "Please Upload Filter");
				else {
					DataBase.setCopyDataBase();
					SamplesPredicate.filterWithPredicate(DataBase.getCurrentFilter());
					UploadFilter.uploaded = false;
				}
			}
		});
		btnClickToFilter.setFont(new Font("Sitka Text", Font.BOLD, 24));
		btnClickToFilter.setBounds(79, 174, 239, 66);
		contentPane.add(btnClickToFilter);
	}

}
