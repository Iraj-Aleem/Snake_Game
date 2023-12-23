package snakes; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class NameEntry{
    static String name,code;
    JFrame f =new JFrame();
    JTextField text=new JTextField(30);
    JPasswordField password=new JPasswordField(30);
    JButton save_start =new JButton("Save and start");
    JButton demo =new JButton("Demo Game");
    JButton login =new JButton("Login");
    //ImageIcon img = new ImageIcon("arrows.JPG");
    File obj=new File("Player Info.txt");     //creates file
    
    NameEntry(){
        f.setLayout(new FlowLayout(FlowLayout.LEFT,40,20));
        f.add(new JLabel("Player Name :")); f.add(text);    
        f.add(new JLabel("    Password : ")); f.add(password);
        demo.setBackground(Color.yellow); f.add(demo);
        demo.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {demo();}           
        });
        save_start.setBackground(Color.green);f.add(save_start);
        save_start.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {save_start(f);}           
        });
        login.setBackground(Color.pink); f.add(login);
        login.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {login();}           
        });
        f.setTitle("Snake Game Sign up Page");
        f.setSize(600,600);f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        //f.add(new JLabel("User Guide:"));
        //f.add(new JLabel("",img,JLabel.CENTER));
    }
    public void demo(){ f.dispose(); new GameFrame();}
    public void save_start(JFrame f){
        name=text.getText();
        code=password.getText();
        fileHandeling();
        System.out.println(name);
        System.out.println(code); f.dispose(); new GameFrame();
    }
    public void login(){f.dispose(); new login();}
    
    
    public void fileHandeling(){
        System.out.println("====File created====File name:Player Info.txt====");
        try (FileWriter wr = new FileWriter("Player Info.txt")) {
                wr.write("Player Name: "+name);
                wr.write("\nPassword: "+code);
                wr.close();
                System.out.println("Data has been entered in myfile.txt");
            }
        catch(IOException e) {
            System.out.println("===Error==="+e);
            e.printStackTrace();
        }
    }        
}