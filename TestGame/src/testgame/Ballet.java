/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import org.lwjgl.opengl.ARBShadingLanguageInclude;
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
public class Ballet extends Item{
    private int damageVolume;
    private float range;
    
    public Ballet(Color color, Scale scale, int volume, int range) {
        super(color, scale, 0, 0, 0, 0);
        setDamageVolume(volume);
        this.range = range;
    }
    
    //getter
    public int getDamageVolume()
    {
        return damageVolume;
    }
    
    //setter
    public void setDamageVolume(int volume)
    {
        if(volume >= 0)
        {
            damageVolume = volume;
        }
    }
    
    @Override
    public void renderObject()
    {
        if((x <= range) && (y <= range) && (x >= -range) && (y >= -range))
        {
            float angle = 45.0f * getDirection();
            //playerの向きによって、発射の向きを変える
            switch(getDirection())
            {
                case 0:
                    x = x + 0.3f;
                    break;
                case 2:
                    y = y + 0.3f;
                    break;
                case 4:
                    x = x - 0.3f;
                    break;
                case 6:
                    y = y - 0.3f;
                    break;
            }
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
        }
    }
}
