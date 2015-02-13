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
public class Gun extends Weapon{
    private Ballet ballet[];
    private int balletIndex;
    
    public Gun(Color color, Scale scale) {
        super(color, scale);
        balletIndex = 0;
    }
    
    //弾をセットする
    public void setBallet(Color color, Scale scale, int count, int volume, int range)
    {
        ballet = new Ballet[count];
        for(int i = 0; i < count; i++)
        {
            ballet[i] = new Ballet(color, scale, volume, range);
        }
    }
    
    //弾を打つ
    @Override
    public void attack()
    {
        ballet[balletIndex].setDirection(getDirection());
        ballet[balletIndex].setX(this.x);
        ballet[balletIndex].setY(this.y);
        ballet[balletIndex].setZ(this.z);
        if(balletIndex < (ballet.length -1))
            balletIndex++;
    }
    
    @Override
    public void renderObject(float x, float y, float z)
    {
        float angle = angle = 45.0f * getDirection();
        this.x = x;
        this.y = y;
        this.z = z;
        glPushMatrix();
        objectColor.setColor();
        switch(getDirection())
        {
            case 0:
                this.y = this.y - 20.0f;
                break;
            case 2:
                this.x = this.x + 20.0f;
                break;
            case 4:
                this.y = this.y + 20.0f;
                break;
            case 6:
                this.x = this.x - 20.0f;
                break;
        }
        glTranslatef(this.x, this.y, this.z + objectScale.getScaleZ());
        glRotatef(angle + 90.0f, 0, 0, 1);
        objectScale.changeObjectScale();
        glBegin(GL_QUADS);
            draw();
        glEnd();
        glPopMatrix();
        //弾を発射数分描画
        for(int i = 0; i < balletIndex; i++)
            ballet[i].renderObject();
    }
    
    
}
