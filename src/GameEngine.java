import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Moteur de jeu chargé de la logique principale et de la gestion des entrées clavier.
 * Implémente Engine pour les mises à jour du jeu et KeyListener pour écouter les touches pressées.
 */
public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;
    Playground playground;

    /**
     * Constructeur du moteur de jeu.
     *
     * @param playground Le terrain de jeu actuel.
     * @param hero        Le personnage contrôlé par le joueur.
     */
    public GameEngine(Playground playground, DynamicSprite hero) {
        this.playground = playground;
        this.hero = hero;
    }

    /**
     * Met à jour la logique du jeu.
     * Actuellement vide, mais peut être étendu pour ajouter des fonctionnalités.
     */
    @Override
    public void update() {

    }

    /**
     * Géré lorsqu'une touche est tapée. Actuellement non utilisé.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Gère les pressions de touches pour déplacer le héros.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

        }
    }

    /**
     * Géré lorsqu'une touche est relâchée. Actuellement non utilisé.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Met à jour le terrain de jeu actuel.
     */
    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
}
