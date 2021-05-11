import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

class LoginFrame extends JFrame {
	String id = "admin";
	String password = "1234qwer";
	JTextField idField;
	JPasswordField pwField;
	
	LoginFrame(){
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		JLabel label = new JLabel("ID");
		label.setPreferredSize(new Dimension(80,10));
		
		idField = new JTextField(20);
		
		JLabel label1 = new JLabel("Password");
		label1.setPreferredSize(new Dimension(80,10));
		
		pwField = new JPasswordField(20);
		
		JButton button = new JButton("login");
		
		button.setPreferredSize(new Dimension(300,30));
		button.addActionListener(new ButtonClickListener());
		
		panel.add(label);
		panel.add(idField);
		panel2.add(label1);
		panel2.add(pwField);
		panel3.add(button);
		
		Container c = getContentPane();
		c.add(panel);
		c.add(panel2);
		c.add(panel3);
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		LoginFrame frame = new LoginFrame();
	}
	
	class ButtonClickListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String tempId = idField.getText();
			String tempPassword = pwField.getText();
			
			if(tempId.equals(id) && tempPassword.contentEquals(password)) {
				JOptionPane.showMessageDialog(null, "Success");
			}else {
				JOptionPane.showMessageDialog(null, "Fail");
			}
			
		}
	}
	
}