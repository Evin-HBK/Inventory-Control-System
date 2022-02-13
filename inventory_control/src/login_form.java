import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_form extends JDialog{
    private JPanel login;
    private JLabel Unamelbl;
    private JLabel phnolbl;
    private JTextField unametb;
    private JTextField phnotb;
    private JButton lgnbtn;
    public login_form(JFrame parent)
    {
        super(parent);
        setTitle("Login Page");
        setContentPane(login);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lgnbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
