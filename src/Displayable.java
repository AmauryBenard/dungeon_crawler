import java.awt.*;

/**
 * Interface représentant un objet pouvant être affiché à l'écran.
 * Les classes qui implémentent cette interface doivent définir une méthode pour dessiner l'objet.
 */
public interface Displayable {

    /**
     * Dessine l'objet à l'écran en utilisant les graphiques fournis.
     */
    void draw(Graphics g);
}
