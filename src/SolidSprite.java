import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Sous-classe de Sprite représentant des objets solides (non traversables).
 */
public class SolidSprite extends Sprite{

    /**
     * Constructeur de la classe SolidSprite.
     *
     * @param x      La position x de l'objet solide.
     * @param y      La position y de l'objet solide.
     * @param image  L'image associée à l'objet solide.
     * @param width  La largeur de l'objet solide.
     * @param height La hauteur de l'objet solide.
     */
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    /**
     * Retourne la hitbox (zone de collision) de l'objet solide.
     *
     * @return La hitbox sous forme de Rectangle2D. Double.
     */
    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x,y, width, height);
    }

    /**
     * Vérifie si l'objet solide intersecte un autre rectangle.
     *
     * @param hitBox Un rectangle à tester.
     * @return Vrai si l'objet solide intersecte le rectangle, faux sinon.
     */
    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }
}