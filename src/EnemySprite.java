import java.awt.*;

/**
 * Sous-classe de Sprite représentant un ennemi dans le jeu.
 * Peut être étendue pour inclure des comportements spécifiques (ex. : IA, mouvement).
 */
public class EnemySprite extends Sprite {  // EnemySprite INHERITS from Sprite

    /**
     * Constructeur de la classe EnemySprite.
     *
     * @param x      La position x de l'ennemi.
     * @param y      La position y de l'ennemi.
     * @param image  L'image associée à l'ennemi.
     * @param width  La largeur de l'ennemi.
     * @param height La hauteur de l'ennemi.
     */
    public EnemySprite(int x, int y, Image image, int width, int height) {
        super(x, y, image, width, height);
    }
}