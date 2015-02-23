/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

/**
 *
 * @author tomoki
 */
public class FieldObject extends Object{
    int ID;
    public FieldObject(Color color, Scale scale, float x, float y, float z, int id) {
        super(color, scale, x, y, z);
        ID = id;
    }
}
