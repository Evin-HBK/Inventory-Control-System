import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signup_form extends JDialog{
    private JPanel signup;
    private JLabel Username;
    private JLabel First_Name;
    private JLabel Last_Name;
    private JLabel Address;
    private JLabel Phone_No;
    private JButton Submitsignup;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    public signup_form(JFrame parent){
     super(parent);
     setTitle("Create a new account");
     setContentPane(signup);
     setMinimumSize(new Dimension(450,474));
     setModal(true);
     setLocationRelativeTo(parent);
     setVisible(true);
        Submitsignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupfunc();
            }
        });
    }

    private void signupfunc() {
    }

    public static void main(String[] args) {
        signup_form mysignup = new signup_form(null);
    }
}
