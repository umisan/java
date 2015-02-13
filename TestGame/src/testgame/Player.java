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
public class Player extends MoveObject{
    private int hitPoint;
    private Weapon playerWeapon;
    
    public Player(Color color, Scale scale, float x, float y, float z, int direction) {
        super(color, scale, x, y, z, direction);
    }
    
    //getter
    public int getHitPoint()
    {
        return hitPoint;
    }
    public Weapon getWeapon()
    {
        return playerWeapon;
    }
    
    //setter
    public void setHitPoint(int hp)
    {
        if(hp >= 0)
        {
            hitPoint = hp;
        }
    }
    public void setWeapon(Weapon weapon)
    {
        playerWeapon = weapon;
    }
    
    //攻撃
    public void attack()
    {
        playerWeapon.attack();
    }
    
    @Override
    public void renderObject()
    {
        float angle = 45.0f * getDirection();
        glPushMatrix();
        objectColor.setColor();
        glTranslatef(x, y, z + objectScale.getScaleZ());
        //↑オブジェクトが地面にめり込まないように
        glRotatef(angle, 0, 0, 1);//0度から何度回転させるかなので、差分をとる必要がない
        objectScale.changeObjectScale();
        glBegin(GL_QUADS);
            draw();
        glEnd();
        glPopMatrix();
        //持っている武器の描画
        playerWeapon.renderObject(x, y, z);
    }
}
