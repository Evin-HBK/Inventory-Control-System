import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class signup_form extends JDialog{
    private JPanel signup;
    private JLabel Usernamelbl;
    private JLabel First_Namelbl;
    private JLabel Last_Namelbl;
    private JLabel Addresslbl;
    private JLabel Phone_Nolbl;
    private JButton Submitsignup;
    private JTextField unametb;
    private JTextField fnametb;
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Submitsignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registeruser();
            }
        });
        setVisible(true);
    }

    private void registeruser() {
        String uname= unametb.getText();
        String fname= fnametb.getText();
        String lname= lnametb.getText();
        String address= addresstb.getText();
        String phno= phnotb.getText();
        long x=99999L;
        long y=10000000000L;
        if(Long.parseLong(phno)<x||Long.parseLong(phno)>y)
        {
            JOptionPane.showMessageDialog(this,"Please enter a valid number","Try Again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i=0;i<phno.length();++i)
        {
            if(!Character.isDigit(phno.charAt(i)))
            {
                JOptionPane.showMessageDialog(this,"Please enter a valid number","Try Again",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if(uname.isEmpty()||fname.isEmpty()||lname.isEmpty()||address.isEmpty()||phno.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try Again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        user= addUsertoDatabase(uname,fname,lname,address,phno);
        if(user!=null)
        {
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Failed to register new user","try again",JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User addUsertoDatabase(String uname, String fname, String lname, String address, String phno) {
        User user= null;
        try
        {
            Connection myconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
            Statement stmt=myconnection.createStatement();
            String sql= "INSERT INTO customer (Customer_ID, First_name, Last_name, Address, Phone_number)"+ "VALUES(?,?,?,?,?)";
            PreparedStatement mystatement= myconnection.prepareStatement(sql);
            mystatement.setString(1,uname);
            mystatement.setString(2,fname);
            mystatement.setString(3,lname);
            mystatement.setString(4,address);
            mystatement.setString(5,phno);
            int addedrows=mystatement.executeUpdate();
            if(addedrows>0)
            {
                user=new User();
                user.uname=uname;
                user.fname=fname;
                user.lname=lname;
                user.address=address;
                user.phno=phno;
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
