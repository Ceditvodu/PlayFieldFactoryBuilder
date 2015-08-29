/**
 * @author Ivan Kaduk
 * 
 * Pet project that contain two types of design patterns: builder, observer 
 * and it based on openGl lib.
 * 
 * email: ceditvodu@gmail.com
 */
package playfieldfactorybuilder;
//interface for hero and enemies it will watching by them
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
