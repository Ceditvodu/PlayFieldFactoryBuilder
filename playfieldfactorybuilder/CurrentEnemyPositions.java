/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;
//observer of enemy position
public class CurrentEnemyPositions implements Observer {
    private float p11;
    private float p12;
    private float p13;
    
    private NormalEnemy normalEnemy;
    
    // enemy position registration
    public CurrentEnemyPositions(NormalEnemy normalEnemy){
        this.normalEnemy = normalEnemy;
        normalEnemy.registerObserver(this);
    }

    @Override
    public void update(float p11, float p12, float p13) {
        this.p11 = p11;
        this.p12 = p12;
        this.p13 = p13;
        //fuf
        display();
    }
    
        public float getP11(){
            return this.p11;
        }
        public float getP12(){
            return this.p12;
        }
        public float getP13(){
            return this.p13;
        }
    //displaing enemy position in console
    private void display() {
        System.out.println("sdsdsd"+p11+" "+p12+" "+p13);
//        float[] s = new float[3];
//        s[0] = p11;
//        s[1] = p12;
//        s[2] = p13;
//        return s;
    }
    
    
    
}
