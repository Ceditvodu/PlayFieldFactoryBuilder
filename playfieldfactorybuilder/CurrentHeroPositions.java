/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;
//observer of hero position
public class CurrentHeroPositions implements Observer {
    private float p11;
    private float p12;
    private float p13;
    
    private NormalHero normalHero;
    // enemy position registration
    public CurrentHeroPositions(NormalHero normalHero){
        this.normalHero = normalHero;
        normalHero.registerObserver(this);
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
    

    @Override
    public void update(float p11, float p12, float p13) {
        this.p11 = p11;
        this.p12 = p12;
        this.p13 = p13;
        display();
    }

    private void display() {
        //displaing hero position in console
        System.out.println("Hero:"+p11+" "+p12+" "+p13);
    }
    
}
