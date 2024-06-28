package employee.management.system;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {
    Choice choiceEMPID;
    JButton delete,Back;
    RemoveEmployee()
    {
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("serif", Font.BOLD,15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200,50,150,30);
        add(choiceEMPID);

        try
        {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while(resultSet.next())
            {
                choiceEMPID.add(resultSet.getString("empID"));
            }

        }catch (Exception n)
        {
            n.printStackTrace();
        }

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50,100,100,30);
        labelName.setFont(new Font("serif", Font.BOLD,15));
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(200,100,100,30);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50,150,100,30);
        add(labelPhone);

        JLabel textphone= new JLabel();
        textphone.setBounds(200,150,100,30);
        add(textphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50,200,100,30);
        add(labelemail);

        JLabel textemail= new JLabel();
        textemail.setBounds(200,200,150,30);
        add(textemail);

        try {
            conn c = new conn();
            ResultSet resultset = c.statement.executeQuery("select * from employee where empID = '"+choiceEMPID.getSelectedItem()+"'");
            while(resultset.next())
            {
                textName.setText(resultset.getString("name"));
                textphone.setText(resultset.getString("phone"));
                textemail.setText(resultset.getString("email"));
            }

        }catch (Exception p)
        {
            p.printStackTrace();
        }

        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try
                {
                    conn c = new conn();
                    ResultSet resultset = c.statement.executeQuery("select * from employee where empID = '"+choiceEMPID.getSelectedItem()+"'");
                    while(resultset.next())
                    {
                        textName.setText(resultset.getString("name"));
                        textphone.setText(resultset.getString("phone"));
                        textemail.setText(resultset.getString("email"));
                    }

                }catch (Exception E)
                {
                    E.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        Back = new JButton("Back");
        Back.setBounds(220,300,100,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.white);
        Back.addActionListener(this);
        add( Back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(700,80,200,200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel img1 = new JLabel(i33);
        img1.setBounds(0,0,1120,630);
        add(img1);








        setSize(1000,400);
        setLocation(300,150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == delete)
        {
            try
            {
                conn c = new conn();
                String query = "delete from employee where empID = '"+choiceEMPID.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Data Deleted Successfully");
            }catch(Exception f)
            {
                f.printStackTrace();
            }
        }
        else
        {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}