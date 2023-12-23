package snakes;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//
//public class login{
//    private String name,code;
//    JFrame f =new JFrame();
//    JTextField text=new JTextField(30);
//    JPasswordField password=new JPasswordField(30);
//    JButton save_start =new JButton("Save and Start");
//    
//    login(){
//        f.setLayout(new FlowLayout(FlowLayout.LEFT,40,20));
//        f.add(new JLabel("Player Name :")); f.add(text);    
//        f.add(new JLabel("Password : ")); f.add(password);f.add(save_start);
//        save_start.setBackground(Color.green);f.add(save_start);
//        save_start.addActionListener( new ActionListener(){
//            public void actionPerformed(ActionEvent e) {new NameEntry().save_start(f);}           
//        });
//        f.setTitle("Snake Game Login  Page");
//        f.setSize(600,600);f.setResizable(false);
//        f.setLocationRelativeTo(null);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//    }
//}
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class login{
    String name,code;
    JFrame f =new JFrame();
    JTextField text=new JTextField(30);
    JPasswordField password=new JPasswordField(30);
    JButton save_start =new JButton("Save and Start");
    
    login(){
        f.setLayout(new FlowLayout(FlowLayout.LEFT,40,20));
        f.add(new JLabel("Player Name :")); f.add(text);    
        f.add(new JLabel("Password : ")); 
        f.add(password);
        f.add(save_start);
        save_start.setBackground(Color.green);f.add(save_start);
        save_start.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {File_writer();new NameEntry().save_start(f);}           
        });
        f.setTitle("Snake Game Login  Page");
        f.setSize(600,600);f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void File_writer(){
        System.out.print(name+","+password.getText());
        File obj=new File(name+"login.txt");     //creates file
        System.out.println("====File created====");
        try {
            try (FileWriter wr = new FileWriter(name+"ii.txt")) {
                wr.write(name+","+password.getText());
            }
            System.out.println("Data has been entered in "+name+"ii.txt");
        }
        catch(IOException e) {System.out.println("*   Error  *");}
    }
    public String File_Reader(){        //reading name
        String player[]=new String[2];
        try{
            System.out.println("\tFinding file:\t "+name+".txt");
            byte[] datainBytes;
            try (DataInputStream dis = new DataInputStream (new FileInputStream (name+"ii.txt"))) {
                datainBytes = new byte[dis.available()];		       
                dis.readFully(datainBytes);
            }
            String content = new String(datainBytes, 0, datainBytes.length);
            player = content.split(",");
            System.out.println(player[0]);
        }
        catch(IOException ex){System.out.println("===Error===");}
        return player[0];
    } 
}