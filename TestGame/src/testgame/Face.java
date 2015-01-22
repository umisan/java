/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import org.lwjgl.opengl.ARBVertexType2_10_10_10_REV;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glColor3f;
import static testgame.Point.*;

/**
 *
 * @author tomoki
 */
public enum Face {
    F_FRONT(new Point[] {
            P_FRONT_TOP_RIGHT,
            P_FRONT_TOP_LEFT,
            P_FRONT_BOTTOM_LEFT,
            P_FRONT_BOTTOM_RIGHT
    }),
    F_BACK(new Point[] {
            P_BACK_TOP_LEFT,
            P_BACK_TOP_RIGHT,
            P_BACK_BOTTOM_RIGHT,
            P_BACK_BOTTOM_LEFT,
    }),
    F_LEFT(new Point[] {
            P_FRONT_TOP_LEFT,
            P_BACK_TOP_LEFT,
            P_BACK_BOTTOM_LEFT,
            P_FRONT_BOTTOM_LEFT
    }),
    F_RIGHT(new Point[] {
            P_BACK_TOP_RIGHT,
            P_FRONT_TOP_RIGHT,
            P_FRONT_BOTTOM_RIGHT,
            P_BACK_BOTTOM_RIGHT
    }),
    F_TOP(new Point[] {
            P_BACK_TOP_RIGHT,
            P_BACK_TOP_LEFT,
            P_FRONT_TOP_LEFT,
            P_FRONT_TOP_RIGHT           
    }),
    F_BOTTOM(new Point[] {
            P_FRONT_BOTTOM_RIGHT,
            P_FRONT_BOTTOM_LEFT,
            P_BACK_BOTTOM_LEFT,
            P_BACK_BOTTOM_RIGHT
    });
 
    private final Point[]  points;
     
    private Face(Point[] points) {
        this.points = points;
    }
 
    public void draw() {
        points[0].point();
        points[1].point();
        glColor3f(0.45f, 0.45f, 0.45f);
        points[2].point();
        points[3].point();
    }
}
