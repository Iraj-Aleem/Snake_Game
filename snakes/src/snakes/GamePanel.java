package snakes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600,SCREEN_HEIGHT = 600, UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    final int x[] = new int[GAME_UNITS], y[] = new int[GAME_UNITS];
    int delay=125, bodyParts = 1, Eaten, appleX, appleY, ObsticalX, ObsticalY;
    char direction = 'R';
    boolean running = false;
    String reason;
    Timer timer;
    Random random;
    
    ImageIcon img = new ImageIcon("obstical.PNG");
    JLabel l = new JLabel(img); 
    JPanel p=new JPanel();
     
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setForeground(Color.black);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());        
        add(p);
        startGame();
    }
    
    public void startGame() {
        newApple();       
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
       if(running) {
            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) { g.setColor(Color.DARK_GRAY);
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
	//g.setXORMode(Color.darkGray);
        //g.setColor(Color.YELLOW);g.fillRect(ObsticalX, ObsticalY, UNIT_SIZE, UNIT_SIZE);
        p.setBounds(ObsticalX,ObsticalY,30,30); p.setBackground(Color.GRAY);p.add(l);
        g.setColor(Color.RED);g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        
        for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+Eaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+Eaten))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }
    public void newObstical(){
        ObsticalX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        ObsticalY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;        
    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        newObstical();
    }
    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction) {
            case 'U':y[0] = y[0] - UNIT_SIZE;break;
            case 'D':y[0] = y[0] + UNIT_SIZE;break;
            case 'L':x[0] = x[0] - UNIT_SIZE;break;
            case 'R':x[0] = x[0] + UNIT_SIZE;break;
        }
    }
    public void checkApple() {
        if(appleY == y[0] && x[0] == appleX ) {bodyParts++; delay=delay-10;  Eaten++; newApple(); }
    }
    public void checkCollisions() {
        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) { running = false; reason="head collides with a body part"; }
        }
        if((x[0] == ObsticalX) && (y[0] == ObsticalY)) { running = false; 
//        reason="head collision with obstical";
        }
        if(x[0] < 0) {running = false;
//        reason="head collision with left border";}
        if(x[0] > SCREEN_WIDTH) {running = false;
//        reason="head collision with right border";
        }}
        if(y[0] < 0) {running = false;
//        reason="head collision with top border";
        }
        if(y[0] > SCREEN_HEIGHT) {running = false;
//        reason="head collision with bottom border";
        }
        if(!running) {timer.stop();}
    }
    public void gameOver(Graphics g) {    
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics0 = getFontMetrics(g.getFont());
        
        g.drawString("Name: "+NameEntry.name,(SCREEN_WIDTH - metrics0.stringWidth("Score: "))/27, g.getFont().getSize());
        
       
        //Score
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+Eaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+Eaten))/1, g.getFont().getSize());
        //Game Over text
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {  move();   checkApple();  checkCollisions(); }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R')direction = 'L';break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') direction = 'R';break;
                case KeyEvent.VK_UP:
                    if(direction != 'D')direction = 'U';break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') direction = 'D';break;
            }
        }
    }
}