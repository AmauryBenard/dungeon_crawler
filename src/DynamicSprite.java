import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Classe représentant un sprite dynamique qui peut se déplacer et interagir avec son environnement.
 * Hérite de la classe {@link SolidSprite}.
 */
public class DynamicSprite extends SolidSprite{
    private Direction direction = Direction.EAST;
    private final double speed = 5;
    private final boolean isWalking =true;

    private final Main main;

    /**
     * Constructeur de la classe DynamicSprite.
     *
     * @param x      Position initiale en X.
     * @param y      Position initiale en Y.
     * @param image  Image associée au sprite.
     * @param width  Largeur du sprite.
     * @param height Hauteur du sprite.
     * @param main   Instance principale pour gérer les transitions globales (changement de niveau, écran de fin, etc.).
     */
    public DynamicSprite(double x, double y, Image image, double width, double height, Main main) {
        super(x, y, image, width, height);
        this.main = main;
    }

    /**
     * Vérifie si le sprite entre en collision avec un autre objet rectangulaire.
     *
     * @param other Rectangle représentant l'objet avec lequel vérifier l'intersection.
     * @return {@code true} si une intersection se produit, sinon {@code false}.
     */
    public boolean intersect(Rectangle2D.Double other) {
        return getHitBox().intersects(other);
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment){
            if ((s instanceof DoorSprite) && (s!=this)){
                if (s.intersect(moved)){
                    main.nextLevel();
                    return false;
                }

            } else if ((s instanceof TrapSprite) && (s!=this)){

                    if (s.intersect(moved)){
                        main.showGameOverScreen();
                        return false;
                    }

            } else if ((s instanceof SolidSprite) && (s!=this)){
                if (s.intersect(moved)){
                    return false;
                }


            }

        }
        return true;
    }

    /**
     * Modifie la direction actuelle du sprite.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void move(){
        switch (direction){
            case NORTH -> this.y-=speed;
            case SOUTH -> this.y+=speed;
            case EAST -> this.x+=speed;
            case WEST -> this.x-=speed;
        }
    }

    /**
     * Déplace le sprite dans la direction actuelle si aucun obstacle ne l'empêche.
     *
     * @param environment Liste des sprites représentant l'environnement.
     */
    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    /**
     * Définit une nouvelle position pour le sprite.
     *
     * @param x Nouvelle position en X.
     * @param y Nouvelle position en Y.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Dessine le sprite à l'écran avec la frame d'animation correspondant à la direction et au timing.
     */
    @Override
    public void draw(Graphics g) {
        double timeBetweenFrame = 250;
        int spriteSheetNumberOfColumn = 10;
        int index= (int) (System.currentTimeMillis()/ timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
    }
}
