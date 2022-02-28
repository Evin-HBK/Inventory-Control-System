import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class cart extends JDialog {
    private JPanel Main;
    private JButton goToCheckoutButton;
    private JButton backToProductPageButton;
    private JTable table1;
    private JTextField txtid;
    private JButton deleteButton;
    Connection con;
    PreparedStatement pst;
    void table_load(String uname){
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
            pst=con.prepareStatement("select Product_Id,Brand_name,Product_name,Quantity,Price from cart where Customer_ID=?");
            pst.setString(1,uname);
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace( );
        }
    }
    public cart(String uname) {
        setTitle("cart");
        setContentPane(Main);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(475,500));
        table_load(uname);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtid.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please Enter All Fields!","Try Again",JOptionPane.ERROR_MESSAGE);
                }
                else
                    removefromcart();
            }
            private void removefromcart() {
                String id,n;
                id=txtid.getText();
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
                    int flag=0;
                    pst=con.prepareStatement("select Product_Id,Brand_name,Product_name,Quantity,Price from cart where Customer_ID=? and Product_ID=?");
                    pst.setString(1,uname);
                    pst.setString(2,id);
                    ResultSet rs=pst.executeQuery();
                    while (rs.next())
                    {
                        n=rs.getString("Quantity");
                        pst=con.prepareStatement("delete from cart where Product_ID = ?");
                        pst.setString(1, id);
                        pst.executeUpdate();
                        pst=con.prepareStatement("update product set availability=availability+? where Product_ID=?");
                        pst.setString(1,n);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        flag=1;
                    }
                    if(flag==1)
                        JOptionPane.showMessageDialog(null, "Product Deleted!!!!!");
                    else
                        JOptionPane.showMessageDialog(null,"Product Not in Cart!!","Try Again",JOptionPane.ERROR_MESSAGE);
                    table_load(uname);
                    txtid.setText("");
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        setVisible(true);
        backToProductPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                productpage myproductpage=new productpage(uname);
            }
        });
        goToCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                checkout mycheckout=new checkout(uname);
            }
        });
    }
}
