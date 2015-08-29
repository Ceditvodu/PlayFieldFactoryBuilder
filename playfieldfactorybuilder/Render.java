/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;

import static com.jogamp.opengl.GL.GL_FRONT_AND_BACK;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2GL3.GL_FILL;
import static com.jogamp.opengl.GL2GL3.GL_LINE;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.KeyListener;
//main render of app
public class Render implements GLEventListener, KeyListener{
	private static GraphicsEnvironment graphicsEnvironment;
	private static boolean isFullScreen = false;
	public static DisplayMode dm, dm_old;
	public static Dimension xgraphic;
	public static Point point = new Point(0,0);
	public float up = 0;
        public float LEFT = 0;
	private GLU glu = new GLU();
        public int[] rand1 = new int[6];
        public int[] rand2 = new int[6];
       

    public Render() {
        for(int i = 0; i < this.rand1.length; i++){
                this.rand1[i] = (int)(Math.random()*6); // random number of 0..6
                //System.out.println(this.rand1[i]);
            }
        for(int i = 0; i < this.rand2.length; i++){
            this.rand2[i] = (int)(Math.random()*6); // random number of 0..6
        }     
    }

	@Override
	public void display(GLAutoDrawable drawable) {

            final GL2 gl = drawable.getGL().getGL2();
            //assing an blueprint render 
            gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            // Clear The Screen And The Depth Buffer
	    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);     
	    gl.glLoadIdentity();       
	    
	    gl.glTranslatef(-1.5f,0.0f,-6.0f);                 
            
            //adding a floor
            for(int i = 0; i < 6; i++){
                for(int j = 6; j > 0; j--){
                       new Blocks.Builder(drawable, (-10+j)/2.5f, 1,(i)/2.5f )
                               .r(0.0f)
                               .g(1.0f)
                               .b(0.0f).build(); 
                }
            }
            //assing an fullfill render
            gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
            //adding some bad guys
            for(int j = 1; j < 3; j++){
                if ((this.rand1[j]!=0)||(this.rand2[j]!=0)){
                    Enemies BadGuy  =  new Enemies.Builder(drawable, (-10+this.rand1[j])/2.5f, 1.4f,(this.rand2[j])/2.5f ).r(0.0f).g(1.0f).b(0.0f).build();
                    //System.out.println(this.rand1[i]);
                    //System.out.println(BadGuy.getP11()+" "+BadGuy.getP12()+" "+BadGuy.getP13());

                }        
            }
            //HERO appires in this code
            Hero GreenLantern = new Hero.Builder(drawable, (-10+1+this.up)/2.5f, 1.4f, 0+this.LEFT/2.5f).r(0.0f).g(1.0f).b(0.0f).build(); 
            //System.out.println(GreenLantern.getP11()+" "+GreenLantern.getP12()+" "+GreenLantern.getP13());
            //end of scene listing
            gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
	}
        //perspective manipulation
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if(height <=0)
			height = 1;
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();	
	}
	//render setuping
	public void startRender(){
		//canvas setup
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		//the canvas 
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Render r = new Render();
		glcanvas.addGLEventListener(r);
                glcanvas.addKeyListener(r);
		glcanvas.setSize(400, 400);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		final JFrame frame = new JFrame("Some Holly War That NO ONE CAN`T STAND!!!");
		frame.getContentPane().add(glcanvas);
		
		//shutdown
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent E){
				if(animator.isStarted()){
					animator.stop();
					System.exit(0);
				}
				
			}
		});
		
		frame.setSize(frame.getContentPane().getPreferredSize());
		
		
		//  window start
		
		graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
				
		GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();
		
		dm_old = devices[0].getDisplayMode();
		dm = dm_old;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int windowX = Math.max(0 ,(screenSize.width - frame.getWidth())/2);
		int windowY = Math.max(0 ,(screenSize.height - frame.getHeight())/2);
		
		frame.setLocation(windowX, windowY );
		//
		
		frame.setVisible(true);
		
		
		// time to add buttonControle
		
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(0,0));
		frame.add(p, BorderLayout.SOUTH);
		keyBindings(p, frame, r);
		animator.start();
		
		
		
	}
        // som not working button experiment dont pay attention 
	private void keyBindings(JPanel p,final JFrame frame,final Render r) {
		ActionMap actionMap = p.getActionMap();
		InputMap inputMap = p.getInputMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "UP");
		actionMap.put("UP", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fullScreen(frame);
			}
		});
                
                inputMap.put(KeyStroke.getKeyStroke("b"),"b");
                actionMap.put("b", new AbstractAction(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        System.out.print(e);
//                        up = false;
                    
                    }
                    
                    
                });
                
	}
// full screen function. Soon i will come back and end it!
	protected static void fullScreen(JFrame f) {
		if(!isFullScreen){
			f.dispose();
			f.setUndecorated(true);
			f.setVisible(true);
			f.setResizable(false);
			xgraphic = f.getSize();
			point = f.getLocation();
			f.setLocation(0, 0);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			f.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight() );
			isFullScreen = true;
			
		}else{
			f.dispose();
			f.setUndecorated(false);
			f.setVisible(true);
			f.setResizable(true);
			f.setLocation(point);
			f.setSize(xgraphic);
			
			isFullScreen = false;
		}
	}

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    // key controls for your hero! UP DOWN LEFT RIGHT
    @Override
    public void keyPressed(KeyEvent e) {
        
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            //System.out.println("dsfdsf");
            this.up += 1f;
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            this.up -= 1f;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            this.LEFT -= 1f;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            this.LEFT += 1f;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
