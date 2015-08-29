/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;


public class PlayFieldFactoryBuilder  {
    public static void main(String[] args) {
        Render s = new Render(); // init the openGl loop
        s.startRender();//starting the loop
    } 
}
