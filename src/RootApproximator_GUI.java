import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RootApproximator_GUI {
	
	JLabel label;
	JTextField field;
	
	public static double AppRt2(int n) {
		if(n > 0) {
			return 1 + 1/(1 + AppRt2(n-1));
		}else {
			return 1;
		}
	}
	
	public static void main(String[] args) {
		new RootApproximator_GUI().go();
	}
	public void go() {
		label = new JLabel(" ");
		JPanel labelPane = new JPanel();
		labelPane.add(label);
		
		field = new JTextField(10);
		field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					label.setText(Double.toString(AppRt2(Integer.parseInt(field.getText()))));
				}catch(Exception ex) {
					label.setText("Not a Valid Number");
				}
			}
		});
		JPanel fieldPane = new JPanel();
		fieldPane.add(field);
		
		JPanel promptPane = new JPanel();
		promptPane.add(new JLabel("Enter the precision factor:"));
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(promptPane, BorderLayout.NORTH);
		frame.getContentPane().add(fieldPane, BorderLayout.CENTER);
		frame.getContentPane().add(labelPane, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
}
