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
//enemy actor builder
public class Enemies {
        private final GLAutoDrawable drawable;
        private final float p11;
        private final float p12;
        private final float p13;
        private final float r;
        private final float g;
        private final float b;
        
        public float getP11(){
            return this.p11;
        }
        public float getP12(){
            return this.p12;
        }
        public float getP13(){
            return this.p13;
        }

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

            public Enemies build(){
                return new Enemies(this);
            }
        }
        
        private Enemies(Builder builder){
        drawable = builder.drawable;
        p11 = builder.p11;
        p12 = builder.p12;
        p13 = builder.p13;
        r = builder.r;
        g = builder.g;
        b = builder.b;
        
        //adding to render model of enemy
        
        Block s = new NormalEnemy();
        
        CurrentEnemyPositions currentenemypos = new CurrentEnemyPositions((NormalEnemy) s);

        s.make(drawable,p11,p12,p13,r,g,b);
        

    }
}
