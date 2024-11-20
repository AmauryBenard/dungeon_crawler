import java.util.ArrayList;

/**
 * Classe représentant le moteur physique, responsable de gérer les mouvements des sprites dynamiques.
 * Elle prend en compte les collisions avec l'environnement.
 */
public class PhysicEngine implements Engine{
    private final ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;

    /**
     * Constructeur par défaut du moteur physique.
     */
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    /**
     * Ajoute un sprite statique à l'environnement.
     */
    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    /**
     * Définit l'environnement du moteur physique avec une liste de sprites.
     *
     * @param environment La liste des sprites représentant l'environnement.
     */
    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    /**
     * Ajoute un sprite dynamique (capable de se déplacer) au moteur physique.
     */
    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    /**
     * Met à jour le moteur physique en déplaçant les sprites dynamiques en fonction de l'environnement.
     */
    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);
        }
    }
}
