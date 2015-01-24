/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author tomoki
 */
public class Weapon extends Item{

    public Weapon(Color color, Scale scale,int direction) {
        super(color, scale, 0.0f, 0.0f, 0.0f, direction);
    }
    
    public void renderObject(float x, float y, float z)
    {
        float angle = 45.0f * getDirection();
        this.x = x;
        this.y = y;
        this.z = z;
        glPushMatrix();
        objectColor.setColor();
        objectScale.changeObjectScale();
        //glRotatef(angle, 0, 0, 1);
        switch(getDirection())
        {
            case 0:
                y = y - 20.0f;
                glTranslatef(x / objectScale.getScaleX(), 
                             y / objectScale.getScaleY(), 
                            (z + objectScale.getScaleZ())  / objectScale.getScaleZ());
                break;
            case 2:
                x = x + 20.0f;
                glTranslatef( x / objectScale.getScaleX(),
                              y / objectScale.getScaleY(), 
                              (z + objectScale.getScaleZ())  / objectScale.getScaleZ());
                break;
            case 4:
                y = y + 20.0f;
                glTranslatef(x / objectScale.getScaleX(),
                             y / objectScale.getScaleY(), 
                             (z + objectScale.getScaleZ())  / objectScale.getScaleZ());
                break;
            case 6:
                x = x - 20.0f;
                glTranslatef(x  / objectScale.getScaleX(),
                             y / objectScale.getScaleY(), 
                             (z + objectScale.getScaleZ())  / objectScale.getScaleZ());
                break;
        }
        //スケールによってオブジェクトによって1の大きさが違うので、正規化する必要がある
       // glTranslatef(0.0f, -10.0f, 5.0f);
//        glRotatef(angle, 0, 0, 1);//0度から何度回転させるかなので、差分をとる必要がない
//        glTranslatef(playerPositonX /objectScale.getScaleX(), 
//               playerPostionY / objectScale.getScaleY(), 
//               playerPostionZ / objectScale.getScaleZ());
        //glTranslatef(playerPositonX, playerPostionY, playerPostionZ);
        glBegin(GL_QUADS);
        for(Face face : Face.values())
            face.draw();
        glEnd();
        glPopMatrix();
    }
    
}
