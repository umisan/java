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
public class Item extends MoveObject{
    protected String ItemName;
    protected int ItemID;
    
    public Item(Color color, Scale scale, float x, float y, float z, int direction) {
        super(color, scale, x, y, z, direction);   
    }
    
    //getter
    public String getItemName()
    {
        return ItemName;
    }
    
    //setter
    public void setItemName(String name)
    {
        ItemName = name;
    }
    
}
