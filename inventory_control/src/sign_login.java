import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class sign_login extends JDialog{
    private JPanel sign_login;
    private JLabel Welcomelbl;
    private JButton logbtn;
    private JButton signbtn;
    public sign_login()
    {
        JFrame frame=new JFrame("Welcome Page");
        frame.setContentPane(sign_login);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(750,500));
        signbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup_form mysignup=new signup_form();
            }
        });
        logbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_form mylogin=new login_form();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        frame.setVisible(true);
    }
}
