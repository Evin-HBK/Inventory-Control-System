import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
                String uname=unametb.getText();
                String phno=phnotb.getText();
                user= getAuthenticatedUser(uname,phno);
                if(user!=null)
                {
                    dispose();
                    productpage myproducts=new productpage(uname);
                }
                else
                {
                    JOptionPane.showMessageDialog(login_form.this,"Username/Phone Number invalid","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }
    public User user;
    private User getAuthenticatedUser(String uname, String phno) {
        User user=null;
        try
        {
            Connection myconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
            Statement stmt=myconnection.createStatement();
            String sql= "SELECT * FROM customer WHERE Customer_ID=? AND Phone_number=?";
            PreparedStatement mystatement= myconnection.prepareStatement(sql);
            mystatement.setString(1,uname);
            mystatement.setString(2,phno);
            ResultSet myresult = mystatement.executeQuery();
            if(myresult.next())
            {
                user=new User();
                user.uname=myresult.getString("Customer_ID");
                user.fname=myresult.getString("First_name");
                user.lname=myresult.getString("Last_name");
                user.address=myresult.getString("Address");
                user.phno=myresult.getString("Phone_number");
            }
            stmt.close();
            myconnection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
