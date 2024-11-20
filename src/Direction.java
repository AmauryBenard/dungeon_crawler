
/**
 * Enumération représentant les directions cardinales disponibles pour le mouvement ou l'orientation d'un objet.
 * Chaque direction est associée à un numéro de ligne spécifique pour des animations ou des frames.
 */
public enum Direction {
    NORTH(2),SOUTH(0),EAST(3),WEST(1);
    private final int frameLineNumber;

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    /**
     * Retourne le numéro de ligne des frames associé à cette direction.
     */
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}
