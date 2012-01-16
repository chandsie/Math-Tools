import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GcdCalculator extends JFrame implements KeyListener, ActionListener {
	
	private JTextField inputField;
	private JLabel gcdLabel, lcmLabel, errorLabel;
	private JTextArea workArea;
	
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
		JPanel errorPanel = new JPanel();
		errorPanel.add(errorLabel);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(inputPanel);
		topPanel.add(errorPanel);
		
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
		
		JPanel bottomPanel = new JPanel();
		workArea = new JTextArea();
		workArea.setEditable(false);
		
		JScrollPane pane = new JScrollPane(workArea);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setSize(450, 100);
		pane.setPreferredSize(new Dimension(450,100));
		bottomPanel.add(pane);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		southPanel.add(midPanel);
		southPanel.add(bottomPanel);
		
		getContentPane().add(BorderLayout.SOUTH, southPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 230));
		setSize(500, 230);
		setResizable(false);
		setTitle("GCD and LCM");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev) {
		workArea.setText("");
		String input = inputField.getText();
		String numStrings[] = input.split(",");
		
		boolean error = false;
		
		if(input.length() == 0) {
			error = true;
			errorLabel.setText("Please enter something!");
		}else if (input.matches(".*[^0-9,]+.*")){
			error = true;
			errorLabel.setText("Only positive numbers and commas are allowed!");
		}else if (numStrings.length == 1) {
			error = true;
			errorLabel.setText("Enter more than one number!");
		}else {
			for(int i = 0; i != numStrings.length; i++) {
				if(Integer.parseInt(numStrings[i]) <= 0){
					error = true;
					errorLabel.setText("Only positive numbers are allowed!");
				}
			}
		}
		
		if(error){
			errorLabel.setForeground(Color.RED);
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
		
		long product = 1;
		for(int i = 1; i < nums.size(); i++) {
			product *= nums.get(i);
		}
		
		lcmLabel.setText(String.valueOf(product/gcd));
		
		
	}
			
	private int gcd(int n, int m) {
		int oldN, oldM;
		while (m>0) {
			int r = n % m;
			
			oldN = n;
			oldM = m;
			
			n = m;
			m = r;
			if(m != 0) {
				workArea.append("gcd(" + oldN + ", " + oldM + ") = gcd(" + n + ", " + m + ") since " + oldN + " = " + oldM + "(" + Integer.valueOf(oldN/oldM) + ") + " + r + "\n");
			}else {
				workArea.append("gcd(" + oldN + ", " + oldM + ") = gcd(" + n + ", " + m + ") = " + n + "\n");
			}
		}
		return n;
		
	}
	
	private long lcm(ArrayList<Integer> nums) {
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
		if(e.getKeyCode() != KeyEvent.VK_ENTER){
			errorLabel.setText("");
			workArea.setText("");
		}
	}

	public void keyTyped(KeyEvent e) {}
}
