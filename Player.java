import java.awt.*;

public class Player {
    Rectangle hitbox;
    int score=0;
    int upKey;
    int downKey;
    boolean goingUp=false;
    boolean goingDown=false;
    int upperbound;
    int lowerbound;
    private static final int constantVel=10;

    public Player(int x,int y,int up,int down){
        hitbox=new Rectangle(x,y,20,150);
        upKey=up;
        downKey=down;
        upperbound=90;
        lowerbound=GameDriver.GAME_HEIGHT-(upperbound+hitbox.height);
    }
    public void drawSelf(Graphics g){
        movementWithBounds();
        g.setColor(Color.WHITE);
        g.fillRect(hitbox.x,hitbox.y,hitbox.width,hitbox.height);
    }
    public void movementWithBounds(){
        if(goingUp&&goingDown){
            return;
        }
        else if(goingUp){
            int nextY=hitbox.y-constantVel;
            if(nextY<=upperbound){
                hitbox.y-=(hitbox.y-upperbound);
            }
            else{
                hitbox.y-=constantVel;
            }
        }
        else if(goingDown){
            int nextY=hitbox.y+constantVel;
            if(nextY>=lowerbound){
                hitbox.y+=(lowerbound-hitbox.y);
            }
            else{
                hitbox.y+=constantVel;
            }
        }
    }
    public void hitBallCheck(Ball b){
        if(hitbox.intersects(b.hitbox)){
            b.bouncePaddle();
        }
    }
    public void move(int key){
        if(key==upKey){
            goingUp=true;
        }
        if(key==downKey){
            goingDown=true;
        }
    }
    public void stopMove(int key){
        if(key==upKey){
            goingUp=false;
        }
        if(key==downKey){
            goingDown=false;
        }
    }
}
