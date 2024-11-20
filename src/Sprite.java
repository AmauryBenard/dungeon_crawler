import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Classe de base pour tous les objets affichables dans le jeu.
 * Gère la position, les dimensions, l'image associée et la détection des collisions.
 */
public class Sprite implements Displayable{
    protected double x;
    protected double y;
    protected final Image image;
    protected final double width;
    protected final double height;

    /**
     * Constructeur de la classe Sprite.
     *
     * @param x      La position x du sprite.
     * @param y      La position y du sprite.
     * @param image  L'image associée au sprite.
     * @param width  La largeur du sprite.
     * @param height La hauteur du sprite.
     */
    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * Retourne la position x du sprite.
     */
    public double getX() {
        return x;
    }

    /**
     * Retourne la position y du sprite.
     */
    public double getY() {
        return y;
    }

    /**
     * Retourne l'image associée au sprite.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Retourne la largeur du sprite.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du sprite.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Retourne la hitbox (zone de collision) du sprite.
     *
     * @return La hitbox sous forme de Rectangle2D. Double.
     */
    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    /**
     * Vérifie si le sprite intersecte un autre rectangle.
     *
     * @param other Un rectangle à tester.
     * @return Vrai si le sprite intersecte le rectangle, faux sinon.
     */
    public boolean intersect(Rectangle2D.Double other) {
        return getHitBox().intersects(other);
    }

    /**
     * Dessine le sprite à l'écran.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x,(int)y,null);
    }
}
