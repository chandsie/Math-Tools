import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GcdCalculator extends JFrame implements KeyListener, ActionListener {
	
	private JTextField inputField;
	private JLabel gcdLabel, lcmLabel, errorLabel;
	
	public static void main (String [] args) {
		new GcdCalculator();
	}
	
	public GcdCalculator() {
		JPanel inputPanel = new JPanel();
		inputPanel.add(new JLabel("Inputs:"));
		
		inputField = new JTextField(15);
		inputField.addActionListener(this);
		inputField.addKeyListener(this);
		inputPanel.add(inputField);
		
		errorLabel = new JLabel();
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(inputPanel);
		topPanel.add(errorLabel);
		
		getContentPane().add(BorderLayout.NORTH, topPanel);
		
		JPanel gcdPanel = new JPanel();
		JPanel lcmPanel = new JPanel();
		
		gcdPanel.add(new JLabel("GCD: "));
		gcdPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gcdLabel = new JLabel("");
		gcdPanel.add(gcdLabel);
		
		lcmPanel.add(new JLabel("LCM: "));
		lcmPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lcmLabel = new JLabel("");
		lcmPanel.add(lcmLabel);
		
		
		JPanel midPanel = new JPanel();
		midPanel.add(gcdPanel);
		midPanel.add(lcmPanel);
		
		getContentPane().add(BorderLayout.SOUTH, midPanel);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("GCD and LCM");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev) {
		String input = inputField.getText();
		String numStrings[] = input.split(",");
		
		boolean error = false;
		
		if(input.length() == 0) {
			error = true;
			errorLabel.setText("please enter something!");
		}else if (input.matches(".*[^0-9,]+.*")){
			error = true;
			errorLabel.setText("only numbers and commas are allowed!");
		}else if (numStrings.length == 1) {
			error = true;
			errorLabel.setText("enter more than one number!");
		}
			
		
		if(error){
			pack();
			return;
		}
		
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		for(int i = 0; i < numStrings.length; i++) {
			nums.add(Math.abs(Integer.valueOf(numStrings[i].trim())));
		}
		
		if(nums.size() < 2) return;
		
		int gcd = nums.get(0);
		for(int i = 1; i < nums.size(); i++) {
			gcd = gcd(Math.max(gcd, nums.get(i)), Math.min(gcd, nums.get(i)));
		}
		
		gcdLabel.setText(String.valueOf(gcd));
		
		lcmLabel.setText(String.valueOf(lcm(nums)));
		
		pack();
	}
			
	private static int gcd(int m, int n) {
		
		while (m>0) {	
			int r = n % m;
			n = m;
			m = r;
		}
		return n;
		
	}
	
	private static long lcm(ArrayList<Integer> nums) {
		Integer max = Collections.max(nums);
		nums.remove(max);
		
		for(long i = 1; ; i++) {
			long mult = max * i;
			
			boolean okay = true;
			for(Integer n : nums) {
				if(mult % n != 0) {
					okay = false;
					break;
				}
			}
			
			if(okay) return mult;
		}
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() != KeyEvent.VK_ENTER)
			errorLabel.setText("");
	}

	public void keyTyped(KeyEvent e) {}
}
