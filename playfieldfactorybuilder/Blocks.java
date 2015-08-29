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
//builder that making a blocks wich are construct a floor
public class Blocks {
    private final GLAutoDrawable drawable;
    private final float p11;
    private final float p12;
    private final float p13;
    private final float r;
    private final float g;
    private final float b;
    
    public static class Builder {
        private final GLAutoDrawable drawable;
        private final float p11; 
        private final float p12; 
        private final float p13;
        private float r = 0.0f;
        private float g = 0.0f;
        private float b = 0.0f;
        
        public Builder(GLAutoDrawable drawable, float p11,float p12,float p13){
            this.drawable = drawable;
            this.p11 = p11; 
            this.p12 = p12;
            this.p13 = p13;
        }
        
        public Builder r(float val){
            r = val;
            return this;
        }
        
        public Builder g(float val){
            g = val;
            return this;
        }
        public Builder b(float val){
            b = val;
            return this;
        }
        
        public Blocks build(){
            return new Blocks(this);
        }
    }
    
    private Blocks(Builder builder){
        drawable = builder.drawable;
        p11 = builder.p11;
        p12 = builder.p12;
        p13 = builder.p13;
        r = builder.r;
        g = builder.g;
        b = builder.b;
        
        //macking a one blocke 
        Block s = new NormalBlock();
        s.make(drawable,p11,p12,p13,r,g,b);

    }
    
}

