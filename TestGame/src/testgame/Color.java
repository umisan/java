/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glColor3f;

/**
 *
 * @author tomoki
 */
public class Color {
    private float r;
    private float g;
    private float b;

    public Color(float r, float g, float b) {
        setR(r);
        setG(g);
        setB(b);
    }
    
    //getter
    public float getR()
    {
        return r;
    }
    public float getG()
    {
        return g;
    }
    public float getB()
    {
        return b;
    }
    
    //setter
    public void setR(float r)
    {
        if(r >= 0.0f)
        {
            this.r = r;
        }
    }
    public void setG(float g)
    {
        if(g >= 0.0f)
        {
            this.g = g;
        }
    }
    public void setB(float b)
    {
        if(b >= 0.0f)
        {
            this.b = b;
        }
    }
    
    //set color to vertex
    public void setColor()
    {
        glColor3f(r, g, b);
    }
}
