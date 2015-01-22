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
public class Object {
    //color of this object
    protected Color objectColor;
    //scale of this object
    protected Scale objectScale;
    //postion of this object
    protected float x;
    protected float y;
    protected float z;
    
    public Object(Color color, Scale scale, float x, float y, float z)
    {
        objectColor = new Color(color.getR(), color.getG(), color.getB());
        objectScale = new Scale(scale.getScaleX(), scale.getScaleY(), scale.getScaleZ());
        setX(x);
        setY(y);
        setZ(z);
    }
    
    //getter
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getZ()
    {
        return z;
    }
    
    //setter
    public void setX(float x)
    {
        this.x = x;// /objectScale.getScaleX();
    }
    public void setY(float y)
    {
        this.y = y;// / objectScale.getScaleY();
    }
    public void setZ(float z)
    {
        this.z = z;// / objectScale.getScaleZ();
    }
    
    
    public void renderObject()
    {
        glPushMatrix();
        objectColor.setColor();
        objectScale.changeObjectScale();
        //スケールによってオブジェクトによって1の大きさが違うので、正規化する必要がある
        glTranslatef(x /objectScale.getScaleX(), y / objectScale.getScaleY(), z  / objectScale.getScaleZ());
        glBegin(GL_QUADS);
        for(Face face : Face.values())
            face.draw();
        glEnd();
        glPopMatrix();
    }
}
