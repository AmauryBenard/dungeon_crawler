import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Classe représentant un terrain de jeu (Playground) contenant des sprites statiques et interactifs.
 * Le terrain est initialisé à partir d'un fichier texte décrivant sa disposition.
 */
public class Playground {
    private final ArrayList<Sprite> environment = new ArrayList<>();

    /**
     * Constructeur qui initialise le terrain en lisant un fichier texte et en chargeant les sprites correspondants.
     *
     * @param pathName Le chemin du fichier texte décrivant le terrain.
     */
    public Playground (String pathName){
        try{

            Image imageTree = ImageIO.read(new File("./img/tree.png"));
            Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            Image imageRock = ImageIO.read(new File("./img/rock.png"));
            Image imageTrap = ImageIO.read(new File("./img/trap.png"));
            Image imageDoor = ImageIO.read(new File("./img/door.png"));


            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);

            final int imageDoorWidth = imageDoor.getWidth(null);
            final int imageDoorHeight = imageDoor.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight, imageTree, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ' : environment.add(new Sprite(columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                        case 'P' : environment.add(new TrapSprite(columnNumber*imageTrapWidth,
                                lineNumber*imageTrapHeight, imageTrap, imageTrapWidth, imageTrapHeight));
                           break;
                        case 'D' : environment.add(new DoorSprite(columnNumber*imageDoorWidth,
                                lineNumber*imageDoorHeight, imageDoor, imageDoorWidth, imageDoorHeight));
                        break;
                    }
                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Retourne une liste des sprites solides (non traversables) du terrain.
     */
    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    /**
     * Retourne une liste des sprites affichables du terrain.
     *
     * @return Une liste d'objets implémentant l'interface Displayable.
     */
    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }

}
