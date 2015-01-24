/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import com.sun.jmx.snmp.internal.SnmpSecuritySubSystem;
import static org.lwjgl.opengl.GL11.*;
import java.math.*;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author tomoki
 */
public class MoveObject extends Object {
    protected boolean jumpTriger = false;
    protected boolean jumpState = true;
    protected float zbuf;
    protected int direction;
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
    
    //for debug
    public void debug()
    {
        System.out.println(objectScale.getScaleX());
        System.out.println(objectScale.getScaleY());
        System.out.println(objectScale.getScaleZ());
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }
    //change direction
    public void changeDirection(int direction)
    {
        setDirection(direction);
    }
    //move each direction
    //スケールで座標が変換されているので、座標を1ずらすにはスケール分与えなければならない
    public void moveUp(int direction)
    {
        changeDirection(direction);
        setY(getY() + 0.1f);
    }
    public void moveDown(int direction)
    {
        changeDirection(direction);
        setY(getY() - 0.1f);
    }
    public void moveRight(int direction)
    {
        changeDirection(direction);
        setX(getX() + 0.1f);
    }
    public void moveLeft(int direction)
    {
        changeDirection(direction);
        setX(getX() - 0.1f);
    }
    
    //jump
    public void jump()
    {
        float temp = getZ() - zbuf;
        if(isObjectJump())
        {
            if(temp <= 50 && jumpState)
            {
                setZ(getZ() + 0.1f);
            }else{
                jumpState = false;
            }
            if(!jumpState && temp >= zbuf)//将来的には衝突判定の結果による
            {
                setZ(getZ() - 0.1f);
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
        glTranslatef(x /objectScale.getScaleX(), 
                     y / objectScale.getScaleY(), 
                    (z + objectScale.getScaleZ())  / objectScale.getScaleZ());
        //↑オブジェクトが地面にめり込まないように
        glRotatef(angle, 0, 0, 1);//0度から何度回転させるかなので、差分をとる必要がない
        glBegin(GL_QUADS);
        for(Face face : Face.values())
            face.draw();
        glEnd();
        glPopMatrix();
    }
    
    
}
