import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GameDriver extends JComponent implements KeyListener,MouseListener,MouseMotionListener{
    static final int GAME_WIDTH=1400;
    static final int GAME_HEIGHT=(int)(1400*(5.0/9));

    Player player1=new Player(100,100,87,83);
    Player player2=new Player(GAME_WIDTH-100-30,100,38,40);
    Wall topWall=new Wall(0,0,GAME_WIDTH,30);
    Wall botWall=new Wall(0,GAME_HEIGHT-65,GAME_WIDTH,30);
    Ball ball=new Ball(GAME_WIDTH/2,GAME_HEIGHT/2-50,50);

    boolean gameOngoing=false;
    public GameDriver(){
        JFrame gui=new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Pong Game");
        gui.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        gui.setResizable(false);
        gui.getContentPane().setBackground(Color.black);
        gui.getContentPane().add(this);

        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        gui.addKeyListener(this);
        gui.addMouseListener(this);
        gui.addMouseMotionListener(this);
    }
    public void makePoint(){
        if(ball.hitbox.x<0){
            player2.score++;
            ball.reset();
            gameOngoing=false;
        }
        else if(ball.hitbox.x>GameDriver.GAME_WIDTH){
            player1.score++;
            ball.reset();
            gameOngoing=false;
        }
    }
    public void paintComponent(Graphics g){
        player1.drawSelf(g);
        player2.drawSelf(g);
        topWall.drawSelf(g);
        botWall.drawSelf(g);
        ball.drawSelf(g);

        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,32));
        g.drawString(""+player1.score,50,60);
        g.drawString(""+player2.score,GAME_WIDTH-100,60);
    }
    public void loop(){
        player1.hitBallCheck(ball);
        player2.hitBallCheck(ball);
        topWall.hitBallCheck(ball);
        botWall.hitBallCheck(ball);

        makePoint();
        repaint();

    }
    public void start(final int ticks){
        Thread gameThread=new Thread(()->{
            while(true){
                loop();
                try{
                    Thread.sleep(1000/ticks);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }
    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
        player1.move(key);
        player2.move(key);
        if(key==32){
            begin();
        }
    }
    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();
        player1.stopMove(key);
        player2.stopMove(key);
    }
    public void keyTyped(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
    public void begin(){
        if(!gameOngoing){
            gameOngoing=true;
            ball.begin();
        }

    }
    public static void main(String[] args){
        GameDriver g=new GameDriver();
        g.start(60);
    }
    
}
