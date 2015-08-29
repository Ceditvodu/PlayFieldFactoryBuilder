/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;

import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2GL3.GL_QUADS;
import com.jogamp.opengl.GLAutoDrawable;
import java.util.ArrayList;
import java.util.List;
//realisation of abstract class: block, that contain a model of enemy and it have a coordinates
public class NormalEnemy extends Block implements Observable {

    private List<Observer> observers ; 
    private float p11;
    private float p12;
    private float p13;
    

    public NormalEnemy(){
        observers = new ArrayList<Observer>(); 
    }
    @Override
    public void make(GLAutoDrawable drawable, float p11, float p12, float p13, float r, float g, float b) {
            //first coordinate
            this.p11 = p11;
            this.p12 = p12;
            this.p13 = p13;
            //coordinates generation
            float p21 = p11+(0.2f*(1));
            float p22 = p12+(0.2f*(1));
            float p23 = p13-(0.2f*(1));
            float p31 = p11-(0.2f*(1));
            float p32 = p12+(0.2f*(1));
            float p33 = p13-(0.2f*(1));
            float p41 = p11-(0.2f*(1));
            float p42 = p12+(0.2f*(1));
            float p43 = p13+(0.2f*(1));
            float p51 = p11+(0.2f*(1));
            float p52 = p12-(0.2f*(1));
            float p53 = p13+(0.2f*(1));
            float p61 = p11+(0.2f*(1));
            float p62 = p12-(0.2f*(1));
            float p63 = p13-(0.2f*(1));
            float p71 = p11-(0.2f*(1));
            float p72 = p12-(0.2f*(1));
            float p73 = p13-(0.2f*(1));
            float p81 = p11-(0.2f*(1));
            float p82 = p12-(0.2f*(1));
            float p83 = p13+(0.2f*(1));            
            p11 = p11+(0.2f*(1));
            p12 = p12+(0.2f*(1));
            p13 = p13+(0.2f*(1));

		final GL2 gl = drawable.getGL().getGL2();
         // adding some propertis: rotation and translation  
         gl.glLoadIdentity();									
         gl.glTranslatef(1.5f,0.0f,-9.0f);						
         gl.glRotatef(25.0f,1.0f,0.0f,0.0f);					
         gl.glRotatef(42.5f,0.0f,1.0f,0.0f);					
         gl.glBegin(GL_QUADS);									
         
         gl.glColor3f(0.0f,0.0f,1.0f);						
         // Set The Color To Blue
         gl.glVertex3f( p21, p22, p23);					
         gl.glVertex3f( p31, p32, p33);					
         gl.glVertex3f( p71, p72, p73);					
         gl.glVertex3f( p61, p62, p63);					 
         
            gl.glColor3f(1.0f,1.0f,0.0f);
        // Set The Color To Yellow						
         gl.glVertex3f( p11, p12, p13);					 
         gl.glVertex3f( p21, p22, p23);					
         gl.glVertex3f( p61, p62, p63);					
         gl.glVertex3f( p51, p52, p53);	
         // Set The Color To green  
         gl.glColor3f(0.0f,1.0f,0.0f);						
         gl.glVertex3f( p11, p12, p13);					 
         gl.glVertex3f( p21, p22, p23);					
         gl.glVertex3f( p31, p32, p33);					
         gl.glVertex3f( p41, p42, p43);					 
         // Set The Color To Orange
         gl.glColor3f(1.0f,0.5f,0.0f);						
         gl.glVertex3f( p51, p52, p53);					 
         gl.glVertex3f( p61, p62, p63);					
         gl.glVertex3f( p71, p72, p73);					
         gl.glVertex3f( p81, p82, p83);					 
         // Set The Color To Red ?
         gl.glColor3f(1.0f,0.0f,0.0f);						
         gl.glVertex3f( p41, p42, p43);					
         gl.glVertex3f( p31, p32, p33);					
         gl.glVertex3f( p71, p72, p73);					
         gl.glVertex3f( p81, p82, p83);					 
         // Set The Color To Violet
         gl.glColor3f(1.0f,0.0f,1.0f);						
         
         gl.glVertex3f( p41, p42, p43);					
         gl.glVertex3f( p11, p12, p13);					 
         gl.glVertex3f( p51, p52, p53);					
         gl.glVertex3f( p81, p82, p83);					 
         
         gl.glEnd();
         
         notifyObservers();
    }
    //observervable realisation
    @Override
    public void registerObserver(Observer o) {
        
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        
        observers.remove(o);
        
    }

    @Override
    public void notifyObservers() {
        
        for(Observer observer : observers){
            observer.update(p11, p12, p13);
        }
    }
    
}
