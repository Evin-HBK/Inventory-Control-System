import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class checkout extends JDialog{
    private JPanel Main1;
    private JTextField txtdate;
    private JTextField txtamt;
    private JTable table2;
    private JButton backButton;
    private JButton PAYButton;
    private JTextField orderidtf;
    private JTextField customeridtf;
    Connection con;
    PreparedStatement pst;
    void table_load(String uname){
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
            pst=con.prepareStatement("select Product_Id,Brand_name,Product_name,Quantity,Price from cart where Customer_ID=?");
            pst.setString(1,uname);
            ResultSet rs=pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace( );
        }
    }
    void date(){
        Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        txtdate.setText(year+"/"+(month+1)+"/"+day);
    }
    String getSum()
    {
        double sum=0,x=0;
        for(int i=0; i< table2.getRowCount(); i++)
        {
            x=Double.parseDouble(table2.getValueAt(i,3).toString())*Double.parseDouble(table2.getValueAt(i,4).toString());
            sum=sum+x;
        }
        txtamt.setText(Double.toString(sum));
        return(Double.toString(sum));
    }
    public checkout(String uname) {
        setTitle("Checkout");
        setContentPane(Main1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(750,500));
        table_load(uname);
        String oid,total;
        customeridtf.setText(uname);
        oid=orderid();
        date();
        total=getSum();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                cart mycart=new cart(uname);
            }
        });
        setVisible(true);
        PAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
                    pst=con.prepareStatement("insert into checkout values(?,?,?)");
                    pst.setString(1,oid);
                    pst.setString(2,uname);
                    pst.setString(3,total);
                    pst.executeUpdate();
                    pst=con.prepareStatement("delete from cart where Customer_ID=?");
                    pst.setString(1,uname);
                    pst.executeUpdate();
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                dispose();
                JOptionPane.showMessageDialog(null, "Thank you for shopping with us!!!!");
            }
        });
    }
    private String orderid() {
        String uniqueID = UUID.randomUUID().toString();
        orderidtf.setText(uniqueID);
        return(uniqueID);
    }
}
