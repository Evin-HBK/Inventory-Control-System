import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class checkout {
    private JPanel Main1;
    private JTextField txtdate;
    private JTextField txtamt;
    private JTable table2;
    private JButton backButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("checkout");
        frame.setContentPane(new checkout().Main1);
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

    void getSum()
    {
        double sum=0,x=0;
        for(int i=0; i< table2.getRowCount(); i++)
        {
            x=Double.parseDouble(table2.getValueAt(i,2).toString())*Double.parseDouble(table2.getValueAt(i,3).toString());
            sum=sum+x;
        }
        txtamt.setText(Double.toString(sum));
    }
    public checkout() {
        connect();
        table_load();
        date();
        getSum();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
