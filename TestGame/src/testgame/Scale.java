/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author tomoki
 */
public class Scale {
    private float scaleX;
    private float scaleY;
    private float scaleZ;

    public Scale(float x, float y, float z) {
        setScaleX(x);
        setScaleY(y);
        setScaleZ(z);
    }
    
    //getter
    public float getScaleX()
    {
        return scaleX;
    }
    public float getScaleY()
    {
        return scaleY;
    }
    public float getScaleZ()
    {
        return scaleZ;
    }
    
    //setter
    public void setScaleX(float x)
    {
        if(x > 0.0f)
        {
            scaleX = x;
        }
    }
    public void setScaleY(float y)
    {
        if(y > 0.0f)
        {
            scaleY = y;
        }
    }
    public void setScaleZ(float z)
    {
        if(z > 0.0f)
        {
            scaleZ = z;
        }
    }
    
    //change object scale
    public void changeObjectScale()
    {
        glScalef(scaleX, scaleY, scaleZ);
    }
}
