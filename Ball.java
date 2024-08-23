import java.awt.*;

public class Ball {
    double velX;
    double velY;
    Rectangle hitbox;
    public Ball(int x,int y,int diameter){
        hitbox=new Rectangle(x,y,diameter,diameter);
    }
    public void drawSelf(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(hitbox.x+=velX,hitbox.y+=velY,hitbox.height,hitbox.width);
    }
    public void bouncePaddle(){
        velY=-velY;
        velX=-(velX*1.1);
    }
    public void bounceWall(){
        velY=-velY;
    }
    public void begin(){
        velX=-5;
        velY=-5;
    }
    public void reset(){
        hitbox.x=GameDriver.GAME_WIDTH/2;
        hitbox.y=GameDriver.GAME_HEIGHT/2-50;
        velX=0;
        velY=0;
    }
}
