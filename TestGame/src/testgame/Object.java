/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.util.ArrayList;
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
    //each vartex 
    protected Float P_FRONT_TOP_LEFT[]      = {-1.0f, 1.0f, 1.0f};
    protected Float P_FRONT_TOP_RIGHT[]     = {1.0f, 1.0f, 1.0f};
    protected Float P_FRONT_BOTTOM_LEFT[]   = {-1.0f, -1.0f, 1.0f};
    protected Float P_FRONT_BOTTOM_RIGHT[]  = {1.0f, -1.0f, 1.0f};
    protected Float P_BACK_TOP_LEFT[]       = {-1.0f, 1.0f, -1.0f};
    protected Float P_BACK_TOP_RIGHT[]      = {1.0f, 1.0f, -1.0f};
    protected Float P_BACK_BOTTOM_LEFT[]    = {-1.0f, -1.0f, -1.0f};
    protected Float P_BACK_BOTTOM_RIGHT[]   = {1.0f, -1.0f, -1.0f};
    
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
    public ArrayList<Float[]> getVertexList()
    {
        ArrayList<Float[]> vertexList = new ArrayList<Float[]>();
        vertexList.add(P_FRONT_TOP_LEFT);
        vertexList.add(P_FRONT_TOP_RIGHT);
        vertexList.add(P_FRONT_BOTTOM_LEFT);
        vertexList.add(P_FRONT_BOTTOM_RIGHT);
        vertexList.add(P_BACK_TOP_LEFT);
        vertexList.add(P_BACK_TOP_RIGHT);
        vertexList.add(P_BACK_BOTTOM_LEFT);
        vertexList.add(P_BACK_BOTTOM_RIGHT);
        return vertexList;
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
    public void setVertex(ArrayList<Float[]> vertexList)
    {
        P_FRONT_TOP_LEFT     = vertexList.get(0);
        P_FRONT_TOP_RIGHT    = vertexList.get(1);
        P_FRONT_BOTTOM_LEFT  = vertexList.get(2);
        P_FRONT_BOTTOM_RIGHT = vertexList.get(3);
        P_BACK_TOP_LEFT      = vertexList.get(4);
        P_BACK_TOP_RIGHT     = vertexList.get(5);
        P_BACK_BOTTOM_LEFT   = vertexList.get(6);
        P_BACK_BOTTOM_RIGHT  = vertexList.get(7);
    }
    
    
    public void renderObject()
    {
        glPushMatrix();
        objectColor.setColor();
        //objectScale.changeObjectScale();
        //スケールによってオブジェクトによって1の大きさが違うので、正規化する必要がある
//        glTranslatef(x /objectScale.getScaleX(), 
//                     y / objectScale.getScaleY(), 
//                    (z+ objectScale.getScaleZ())  / objectScale.getScaleZ());
        glTranslatef(x, y, z + objectScale.getScaleZ());
        //↑オブジェクトが地面にめり込まないように
        objectScale.changeObjectScale();
        glBegin(GL_QUADS);
//        for(Face face : Face.values())
//            face.draw();
            draw();
        glEnd();
        glPopMatrix();
    }
    
    public void draw()
    {
        //FFront
        glVertex3f(P_FRONT_TOP_RIGHT[0], P_FRONT_TOP_RIGHT[1], P_FRONT_TOP_RIGHT[2]);
        glVertex3f(P_FRONT_TOP_LEFT[0], P_FRONT_TOP_LEFT[1], P_FRONT_TOP_LEFT[2]);
        glVertex3f(P_FRONT_BOTTOM_LEFT[0], P_FRONT_BOTTOM_LEFT[1], P_FRONT_BOTTOM_LEFT[2]);
        glVertex3f(P_FRONT_BOTTOM_RIGHT[0], P_FRONT_BOTTOM_RIGHT[1], P_FRONT_BOTTOM_RIGHT[2]);
        //FBack
        glVertex3f(P_BACK_TOP_LEFT[0], P_BACK_TOP_LEFT[1], P_BACK_TOP_LEFT[2]);
        glVertex3f(P_BACK_TOP_RIGHT[0], P_BACK_TOP_RIGHT[1], P_BACK_TOP_RIGHT[2]);
        glVertex3f(P_BACK_BOTTOM_RIGHT[0], P_BACK_BOTTOM_RIGHT[1], P_BACK_BOTTOM_RIGHT[2]);
        glVertex3f(P_BACK_BOTTOM_LEFT[0], P_BACK_BOTTOM_LEFT[1], P_BACK_BOTTOM_LEFT[2]);
        //FLeft
        glVertex3f(P_FRONT_TOP_LEFT[0], P_FRONT_TOP_LEFT[1], P_FRONT_TOP_LEFT[2]);
        glVertex3f(P_BACK_TOP_LEFT[0], P_BACK_TOP_LEFT[1], P_BACK_TOP_LEFT[2]);
        glVertex3f(P_BACK_BOTTOM_LEFT[0], P_BACK_BOTTOM_LEFT[1], P_BACK_BOTTOM_LEFT[2]);
        glVertex3f(P_FRONT_BOTTOM_LEFT[0], P_FRONT_BOTTOM_LEFT[1], P_FRONT_BOTTOM_LEFT[2]);
        //FRight
        glVertex3f(P_BACK_TOP_RIGHT[0], P_BACK_TOP_RIGHT[1], P_BACK_TOP_RIGHT[2]);
        glVertex3f(P_FRONT_TOP_RIGHT[0], P_FRONT_TOP_RIGHT[1], P_FRONT_TOP_RIGHT[2]);
        glVertex3f(P_FRONT_BOTTOM_RIGHT[0], P_FRONT_BOTTOM_RIGHT[1], P_FRONT_BOTTOM_RIGHT[2]);
        glVertex3f(P_BACK_BOTTOM_RIGHT[0], P_BACK_BOTTOM_RIGHT[1], P_BACK_BOTTOM_RIGHT[2]);
        //FTop
        glVertex3f(P_BACK_TOP_RIGHT[0], P_BACK_TOP_RIGHT[1], P_BACK_TOP_RIGHT[2]);
        glVertex3f(P_BACK_TOP_LEFT[0], P_BACK_TOP_LEFT[1], P_BACK_TOP_LEFT[2]);
        glVertex3f(P_FRONT_TOP_LEFT[0], P_FRONT_TOP_LEFT[1], P_FRONT_TOP_LEFT[2]);
        glVertex3f(P_FRONT_TOP_RIGHT[0], P_FRONT_TOP_RIGHT[1], P_FRONT_TOP_RIGHT[2]);
        //FBottom
        glVertex3f(P_FRONT_BOTTOM_RIGHT[0], P_FRONT_BOTTOM_RIGHT[1], P_FRONT_BOTTOM_RIGHT[2]);
        glVertex3f(P_FRONT_BOTTOM_LEFT[0], P_FRONT_BOTTOM_LEFT[1], P_FRONT_BOTTOM_LEFT[2]);
        glVertex3f(P_BACK_BOTTOM_LEFT[0], P_BACK_BOTTOM_LEFT[1], P_BACK_BOTTOM_LEFT[2]);
        glVertex3f(P_BACK_BOTTOM_RIGHT[0], P_BACK_BOTTOM_RIGHT[1], P_BACK_BOTTOM_RIGHT[2]);
    }
}
