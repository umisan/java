/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.util.ArrayList;
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
    public Weapon(Color color, Scale scale) {
        super(color, scale, 0.0f, 0.0f, 0.0f, 0);
    }
    //共通の攻撃メソッド。オーバーライドする必要あり
    public void attack()
    {
        
    }
            
    //playerの座標を受け取り、決まった位置に描画する
    public void renderObject(float x, float y, float z)
    {
        float angle = 45.0f * getDirection();
        this.x = x;
        this.y = y;
        this.z = z;
        glPushMatrix();
        objectColor.setColor();
//        objectScale.changeObjectScale();
        switch(getDirection())
        {
            case 0:
                y = y - 20.0f;
                break;
            case 2:
                x = x + 20.0f;
                break;
            case 4:
                y = y + 20.0f;
                break;
            case 6:
                x = x - 20.0f;
                break;
        }
        glTranslatef(x, y, z + objectScale.getScaleZ());
        //glRotatef(45.0f, 1, 0, 0);
        objectScale.changeObjectScale();
        glBegin(GL_QUADS);
//        for(Face face : Face.values())
//            face.draw();
            draw();
        glEnd();
        glPopMatrix();
    }
    
}
