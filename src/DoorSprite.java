import java.awt.*;

/**
 * Sous-classe de SolidSprite représentant une porte.
 * Les portes peuvent être utilisées pour interagir ou changer de niveau.
 */
public class DoorSprite extends SolidSprite {

    /**
     * Constructeur de la classe DoorSprite.
     *
     * @param x      La position x de la porte.
     * @param y      La position y de la porte.
     * @param image  L'image associée à la porte.
     * @param width  La largeur de la porte.
     * @param height La hauteur de la porte.
     */
    public DoorSprite(int x, int y, Image image, int width, int height) {
        super(x, y, image, width, height);
    }
}