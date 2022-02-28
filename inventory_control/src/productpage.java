import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class productpage extends JDialog{
    private JPanel Main;
    private JTextField txtno;
    private JButton insertButton;
    private JButton goToCartButton;
    private JTable table1;
    private JTextField txtid;
    private JLabel productPageLabel;
    Connection con;
    PreparedStatement pst;
    void table_load(){
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
            pst=con.prepareStatement("select * from product where Availability>0");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace( );
        }
    }
    public productpage(String uname){
        setTitle("Product Page");
        setContentPane(Main);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(475,500));
        table_load();
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtno.getText().equals("") || txtid.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please Enter All Fields!","Try Again",JOptionPane.ERROR_MESSAGE);
                }
                else
                    addtocart();
            }
            private void addtocart() {
                String no,id,brand,prod_name,price,availability,no1;
                no=txtno.getText();
                id=txtid.getText();
                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","toor");
                    pst=con.prepareStatement("Select Brand_Name,Product_Name,Price,Availability from product where Product_ID =?");
                    pst.setString(1,id);
                    ResultSet rs=pst.executeQuery();
                    int flag=0;
                    while (rs.next())
                    {
                        brand=rs.getString("Brand_Name");
                        prod_name=rs.getString("Product_Name");
                        price= rs.getString("Price");
                        availability=rs.getString("Availability");
                        if(Integer.parseInt(availability)<Integer.parseInt(no))
                        {
                            flag=-1;
                            break;
                        }
                        pst=con.prepareStatement("Select * from cart where Customer_ID=? and Product_ID=?");
                        pst.setString(1,uname);
                        pst.setString(2,id);
                        ResultSet rs1=pst.executeQuery();
                        while (rs1.next())
                        {
                            no1 = rs1.getString("Quantity");
                            pst = con.prepareStatement("delete from cart where Product_ID = ? and Customer_ID=?");
                            pst.setString(1, id);
                            pst.setString(2,uname);
                            pst.executeUpdate();
                            pst = con.prepareStatement("update product set availability=availability+? where Product_ID=?");
                            pst.setString(1, no1);
                            pst.setString(2, id);
                            pst.executeUpdate();
                        }
                        pst=con.prepareStatement("insert into cart values(?,?,?,?,?,?)");
                        pst.setString(1, id);
                        pst.setString(2, brand);
                        pst.setString(3,prod_name);
                        pst.setString(4,no);
                        pst.setString(5,price);
                        pst.setString(6,uname);
                        pst.executeUpdate();
                        pst=con.prepareStatement("update product set availability=availability-? where Product_ID=?");
                        pst.setString(1,no);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        flag=1;
                    }
                    if(flag==-1)
                    {
                        JOptionPane.showMessageDialog(null,"Product Unavailable or Try Smaller Amounts","Try Again",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(flag==1)
                    {
                        JOptionPane.showMessageDialog(null, "Added to Cart!");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Invalid Product ID!","Try Again",JOptionPane.ERROR_MESSAGE);
                    table_load();
                    txtno.setText("");
                    txtid.setText("");
                    txtno.requestFocus();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        setVisible(true);
        goToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                cart mycart=new cart(uname);
            }
        });
    }
}
