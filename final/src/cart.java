import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class cart {
    private JPanel Main;
    private JTextField txtno;
    private JButton updateButton;
    private JButton backButton;
    private JButton nextButton;
    private JTable table1;
    private JTextField txtid;
    private JButton deleteButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("cart");
        frame.setContentPane(new cart().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trial", "root", "@Kolkata34");
            System.out.println("Success");
        }
        catch(ClassNotFoundException ex)
        {
              ex.printStackTrace();
        }
        catch (SQLException ex)
        {
              ex.printStackTrace();
        }
    }


    void table_load(){
        try
        {
            pst=con.prepareStatement("select * from cart");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace( );
        }
    }

    public cart() {
        connect();
        table_load();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no,id;

                no=txtno.getText();
                id=txtid.getText();
                try{
                    pst=con.prepareStatement("update cart set NoOfproducts = ? where id = ?");
                    pst.setString(1, no);
                    pst.setString(2, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Product Updated!!!!!!");
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1;
                id1=txtid.getText();

                try {
                    pst=con.prepareStatement("delete from cart where id = ?");
                    pst.setString(1, id1);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Product Deleted!!!!!");
                    table_load();
                    txtid.setText("");
                    txtno.setText("");
                    txtno.requestFocus();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }
}
