import java.awt.*;

public class Wall {
    Rectangle wall;
    public Wall(int x,int y,int width,int height){
        wall=new Rectangle(x,y,width,height);
    }
    public void drawSelf(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(wall.x,wall.y,wall.width,wall.height);
    }
    public void hitBallCheck(Ball b){
        if(wall.intersects(b.hitbox)){
            b.bounceWall();
        }
    }
    
}
