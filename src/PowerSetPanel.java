import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class PowerSetPanel extends JPanel implements ActionListener {
	private JTextArea output;
	private JTextField input;
	
	public PowerSetPanel() {
		JPanel panel = new JPanel();
		input = new JTextField(30);
		input.addActionListener(this);
		panel.add(input);
		panel.setBorder(BorderFactory.createEmptyBorder(2,10,2,10));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Input:"));
		add(panel);
		
		output = new JTextArea(10, 30);
		JScrollPane outputPane = new JScrollPane(output);
		
		panel = new JPanel();
		panel.add(outputPane);
		panel.setBorder(BorderFactory.createEmptyBorder(2,10,2,10));
		
		add(new JLabel("Output:"));
		add(panel);
	}
	
	public void actionPerformed(ActionEvent ev) {
		String in = input.getText();
		output.setText(generatePowerSet(Arrays.asList(in.split(","))));
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Power Set Generator");
		frame.add(new PowerSetPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// Shreyas, put your stuff here.
	private String generatePowerSet(List<String> items) {
		String result = "";
		for(int i = 0; i != Math.pow(2, items.size()); i++) {
			String bin = Integer.toBinaryString(i);
			
			String set = "{";
			
			while(bin.length() != items.size()){
				bin = "0" + bin;
			}
			
			for(int j = 0; j < items.size(); j++) {
				if(bin.charAt(j) == '1') {
					set += items.get(j);
					set += ", ";
				}
			}
			
			if(set.length() > 1){
				set = set.substring(0, set.length()-2);
			}
			set += "}\n";
			
			result += set;
		}
		
		return result;
	}
}
