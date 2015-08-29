/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;
import com.jogamp.opengl.GLAutoDrawable;
//abstract class that will be extended by every actors in app
public abstract class Block {
    //function that create an "phisical" object
    abstract  public  void make(GLAutoDrawable drawable, float p11, float p12,float p13,float r,float g,float b);    
}
