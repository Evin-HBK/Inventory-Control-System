import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class sign_login extends JDialog{
    private JPanel sign_login;
    private JLabel Welcomelbl;
    private JButton logbtn;
    private JButton signbtn;
    public sign_login(JFrame parent)
    {
        super(parent);
        setTitle("Welcome page");
        setContentPane(sign_login);
        setMinimumSize(new Dimension(900,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        signbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup_form mysignup=new signup_form(parent);
            }
        });
        logbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_form mylogin=new login_form(parent);
                dispose();
            }
        });
        setVisible(true);
    }
}
