/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import static org.lwjgl.util.glu.GLU.gluLookAt;
 /*
 * @author tomoki
 */
public class TestGame {

    /**
     * @param args the command line arguments
     */
    private int width = 1000;
    private int height = 600;
    private float cameraPostionX = 0.0f;
    private float cameraPostionY = -200.0f;
    private float cameraPostionZ = 300.0f;
    private float cameraLookAtX = 0.0f;
    private float cameraLookAtY = 0.0f;
    private float cameraLookAtZ = 0.0f;
    private FieldObject wall;
    private MoveObject player;
    private Weapon sword;
    public void start()
    {
        wall = new FieldObject(new Color(0.5f, 0.5f, 0.5f), new Scale(100.0f, 20.0f, 20.0f), 600.0f, 0.0f, 0.0f);
        player = new MoveObject(new Color(1.0f, 0.0f, 0.0f), new Scale(10.0f, 10.0f, 10.0f), 0.0f, 0.0f, 0.0f, 0);
        sword = new Weapon(new Color(0.3f, 0.3f, 0.3f), new Scale(2.0f, 2.0f, 30.0f), 0);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("world");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            return;
        }
        
        try {
            //ポリゴンの表のみ表示
            glEnable(GL_CULL_FACE);
            glEnable(GL_DEPTH_TEST);
            glCullFace(GL_BACK);
            
            glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
            
//            //カメラ用の設定変更を宣言する
//            glMatrixMode(GL_PROJECTION);
//            glLoadIdentity();
//            GLU.gluPerspective(30.0f, (float)width/(float)height, 0.1f, 1000.0f);
//            gluLookAt(cameraPostionX, cameraPostionY, cameraPostionZ, 
//                    0.0f, 0.0f, 20.0f, 
//                    0.0f, 0.0f, 1.0f);
            
            while(!Display.isCloseRequested())
            {
                //カメラ用の設定変更を宣言する
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                GLU.gluPerspective(30.0f, (float)width/(float)height, 0.1f, 1000.0f);
                gluLookAt(cameraPostionX, cameraPostionY, cameraPostionZ, 
                    cameraLookAtX, cameraLookAtY, cameraLookAtZ, 
                    0.0f, 0.0f, 1.0f);
                //モデル用の設定変更を宣言する
                glMatrixMode(GL_MODELVIEW);
                //オフスクリーンの初期化
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                
                //キーボード入力処理
                update();
                //オフスクリーンに描画する
                render();
                
                //スクリーンに反映する
                Display.update();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Display.destroy();
        }
    }
    
    private void update()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            cameraPostionY+= 0.1f;
            cameraLookAtY+=0.1f;
            player.moveUp(2);
            //sword.moveUp(2);
            sword.changeDirection(2);
            System.out.println("up");
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            cameraPostionY-= 0.1f;
            cameraLookAtY-= 0.1f;
            player.moveDown(6);
            //sword.moveDown(6);
            sword.changeDirection(6);
            System.out.println("down");
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            cameraPostionX+= 0.1f;
            cameraLookAtX+= 0.1f;
            player.moveRight(0);
            //sword.moveRight(0);
            sword.changeDirection(0);
            System.out.println("right");
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            cameraPostionX-= 0.1f;
            cameraLookAtX-= 0.1f;
            player.moveLeft(4);
            //sword.moveLeft(0);
            sword.changeDirection(4);
            System.out.println("left");
        }        
        while(Keyboard.next())
        {
            if(Keyboard.getEventKeyState())
            {
                if(Keyboard.getEventKey() == Keyboard.KEY_SPACE)
                {
                    player.setJumpTriger(true);
                }
                if(Keyboard.getEventKey() == Keyboard.KEY_S)
                {
                    
                }
            }
        }
        player.jump();
        
    }
    
    private void render()
    {
        float ground_maxx = 600.0f;
        float ground_maxy = 600.0f;
        //設定を初期化する
        glLoadIdentity();
        //glLineWidth(5.0f);
        glColor3f(0.8f, 0.8f, 0.8f);
        glBegin(GL_LINES);
            for(float y = -ground_maxy; y <= ground_maxy; y+=20.0f)
            {
                glVertex3f(-ground_maxx, y, 0.0f);
                glVertex3f(ground_maxx, y, 0.0f);
            }
            for(float x = -ground_maxx; x <= ground_maxx; x+=20.0f)
            {
                glVertex3f(x, ground_maxy, 0.0f);
                glVertex3f(x, -ground_maxy, 0.0f);
            }
        glEnd();
        
        wall.renderObject();
        player.renderObject();
        sword.renderObject(player.getX() , player.getY(), player.getZ() );
        System.out.println("player");
        player.debug();
        System.out.println("sword");
        sword.debug();

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new TestGame().start();
    }
    
}
