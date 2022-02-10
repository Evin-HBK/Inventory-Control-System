import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class signup_form extends JDialog{
    private JPanel signup;
    private JLabel Usernamelbl;
    private JLabel First_Namelbl;
    private JLabel Last_Namelbl;
    private JLabel Addresslbl;
    private JLabel Phone_Nolbl;
    private JButton Submitsignup;
    private JTextField fnametb;
    private JTextField usernametb;
    private JTextField lnametb;
    private JTextField addresstb;
    private JTextField phnotb;
    public signup_form(JFrame parent)
    {
        super(parent);
        setTitle("Create a new account");
        setContentPane(signup);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        Submitsignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
