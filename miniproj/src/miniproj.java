import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miniproj {
    private JPanel Main;
    private JButton backButton;
    private JButton nextButton;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("miniproj");
        frame.setContentPane(new miniproj().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public miniproj() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
