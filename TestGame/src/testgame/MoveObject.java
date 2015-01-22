/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static org.lwjgl.opengl.GL11.*;
import java.math.*;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author tomoki
 */
public class MoveObject extends Object {
    private boolean jumpTriger = false;
    private boolean jumpState = true;
    private float zbuf;
    private int direction;
    //ジャンプの高さのプロパティが必要
    public MoveObject(Color color, Scale scale, float x, float y, float z, 
                        int directrion) {
        super(color, scale, x, y, z);
        this.direction = directrion;
    }
    
    //getter
    public boolean isObjectJump()
    {
        return jumpTriger;
    }
    public float getZBuffer()
    {
        return zbuf;
    }
    public int getDirection()
    {
        return direction;
    }
    //setter
    public void setJumpTriger(boolean state)
    {
        jumpTriger = state;
    }
    public void setZBuffer(float z)
    {
        zbuf = z;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    //change direction
    public void changeDirection(int direction)
    {
        setDirection(direction);
    }
    //move each direction
    //スケールで座標が変換されているので、座標を1ずらすにはスケール分与えなければならない
    public void moveUp()
    {
        setY(getY() + (objectScale.getScaleY() / 100));
    }
    public void moveDown()
    {
        setY(getY() - (objectScale.getScaleY() / 100));
    }
    public void moveRight()
    {
        setX(getX() + (objectScale.getScaleX() / 100));
    }
    public void moveLeft()
    {
        setX(getX() - (objectScale.getScaleX() / 100));
    }
    
    //jump
    public void jump()
    {
        float temp = getZ() - zbuf;
        if(isObjectJump())
        {
            if(temp <= 50 && jumpState)
            {
                setZ(getZ() + (objectScale.getScaleZ() / 100));
            }else{
                jumpState = false;
            }
            if(!jumpState && temp >= zbuf)//将来的には衝突判定の結果による
            {
                setZ(getZ() - (objectScale.getScaleZ() / 100));
            }else if(!jumpState){
                jumpState = true;
                setJumpTriger(false);
            }
        }
    }
    
    @Override
    public void renderObject()
    {
        float angle = 45.0f * getDirection();
        glPushMatrix();
        objectColor.setColor();
        objectScale.changeObjectScale();
        //スケールによってオブジェクトによって1の大きさが違うので、正規化する必要がある
        glTranslatef(x /objectScale.getScaleX(), y / objectScale.getScaleY(), z  / objectScale.getScaleZ());
        glRotatef(angle, 0, 0, 1);//0度から何度回転させるかなので、差分をとる必要がない
        glBegin(GL_QUADS);
        for(Face face : Face.values())
            face.draw();
        glEnd();
        glPopMatrix();
    }
    
    
}
