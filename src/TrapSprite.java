import java.awt.*;

/**
 * Sous-classe de SolidSprite représentant un piège.
 * Les pièges peuvent infliger des dégâts ou entraîner des conséquences négatives pour le joueur.
 */
public class TrapSprite extends SolidSprite {

    /**
     * Constructeur de la classe TrapSprite.
     *
     * @param x      La position x du piège.
     * @param y      La position y du piège.
     * @param image  L'image associée au piège.
     * @param width  La largeur du piège.
     * @param height La hauteur du piège.
     */
    public TrapSprite(int x, int y, Image image, int width, int height) {
        super(x, y, image, width, height);
    }
}