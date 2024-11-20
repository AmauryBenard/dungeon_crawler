import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Moteur graphique chargé de rendre les éléments du jeu.
 * Hérite de JPanel pour intégrer l'affichage dans l'interface Swing.
 */
public class RenderEngine extends JPanel implements Engine{
    private final ArrayList<Displayable> renderList;

    /**
     * Constructeur du moteur graphique.
     * Initialise la liste des objets à rendre.
     *
     * @param jFrame La fenêtre principale où le moteur sera intégré.
     */
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    /**
     * Ajoute un objet affichable à la liste de rendu.
     */
    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    /**
     * Ajoute une liste d'objets affichables à la liste de rendu.
     */
    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    /**
     * Dessine tous les objets présents dans la liste de rendu.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);

        }
    }

    /**
     * Vide la liste des objets à rendre.
     */
    public void clearRenderList() {
        renderList.clear();
    }

    /**
     * Met à jour le moteur graphique.
     * Demande un rafraîchissement de l'affichage.
     */
    @Override
    public void update(){
        this.repaint();
    }
}
