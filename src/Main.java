import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe principale du jeu Dungeon Crawler.
 * Gère l'initialisation des moteurs de jeu, l'interface graphique et le déroulement des niveaux.
 */
public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    Playground playground;

    CardLayout cardLayout;
    GamePanel gamePanel;
    Timer renderTimer, gameTimer, physicTimer;

    List<Playground> levels;
    int currentLevelIndex = 0;
    Playground currentLevel;
    DynamicSprite hero;

    /**
     * Constructeur principal du jeu.
     * Initialise les moteurs, les niveaux, l'interface graphique et les minuteurs.
     *
     * @throws Exception si une erreur se produit lors du chargement des ressources.
     */
    public Main() throws Exception {
        displayZoneFrame = new JFrame("Dungeon Crawler");
        displayZoneFrame.setSize(1150,600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        cardLayout = new CardLayout();
        displayZoneFrame.getContentPane().setLayout(cardLayout);

        // Title Screen
        JPanel titleScreen = createTitleScreen();
        displayZoneFrame.add(titleScreen, "title");

        // Victory Screen
        JPanel victoryScreen = createVictoryScreen();
        displayZoneFrame.add(victoryScreen, "victory");

        // Game Over Screen
        JPanel gameOverScreen = createGameOverScreen();
        displayZoneFrame.add(gameOverScreen, "gameOver");

        hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, this);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(playground, hero);



        renderTimer = new Timer(25, (time) -> {
            renderEngine.update();
            displayZoneFrame.repaint(); // Pour s'assurer que le jeu se redessine
        });
        gameTimer = new Timer(25, (time) -> gameEngine.update());
        physicTimer = new Timer(25, (time) -> physicEngine.update());

        levels = new ArrayList<>();
        levels.add(new Playground("./data/level1.txt")); // Niveau 1
        levels.add(new Playground("./data/level2.txt")); // Niveau 2
        levels.add(new Playground("./data/level3.txt")); // Niveau 2

        loadLevel(currentLevelIndex);

        renderEngine.addToRenderList(currentLevel.getSpriteList());
        renderEngine.addToRenderList(hero);

        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(currentLevel.getSolidSpriteList());

        // Met à jour le moteur de jeu avec le niveau
        gameEngine.setPlayground(currentLevel);

        // Configure l'interface graphique
        displayZoneFrame.getContentPane().add(renderEngine);
        gamePanel = new GamePanel(renderEngine);
        displayZoneFrame.add(gamePanel, "game");
        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.setVisible(true);

    }


    /**
     * Démarre le jeu et en lance les minuteurs.
     */
    private void startGame() {
        cardLayout.show(displayZoneFrame.getContentPane(), "game");
        displayZoneFrame.requestFocusInWindow();
        stopTimers();
        currentLevelIndex = 0;
        loadLevel(currentLevelIndex);
        startTimers();

    }

    private JPanel createTitleScreen() {
        JPanel titleScreen = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Java Dungeon Crawler", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(_ -> startGame());
        titleScreen.add(titleLabel, BorderLayout.CENTER);
        titleScreen.add(startButton, BorderLayout.SOUTH);
        return titleScreen;
    }
    private JPanel createGameOverScreen() {
        JPanel gameOverScreen = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton replayButton = new JButton("Rejouer");
        replayButton.addActionListener(_ -> startGame());
        gameOverScreen.add(gameOverLabel, BorderLayout.CENTER);
        gameOverScreen.add(replayButton, BorderLayout.SOUTH);
        return gameOverScreen;
    }
    private JPanel createVictoryScreen() {
        JPanel victoryScreen = new JPanel(new BorderLayout());
        JLabel victoryLabel = new JLabel("Victory!", SwingConstants.CENTER);
        victoryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        victoryScreen.add(victoryLabel, BorderLayout.CENTER);
        return victoryScreen;
    }

    private void startTimers() {
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
    }
    private void stopTimers() {
        renderTimer.stop();
        gameTimer.stop();
        physicTimer.stop();
    }

    /**
     * Affiche l'écran de victoire et arrête les minuteurs.
     */
    public void showVictoryScreen() {
        cardLayout.show(displayZoneFrame.getContentPane(), "victory");
        stopTimers(); // Stop les timers quand on gagne
    }

    /**
     * Affiche l'écran de Game Over et arrête les minuteurs.
     */
    public void showGameOverScreen() {
        cardLayout.show(displayZoneFrame.getContentPane(), "gameOver");
        stopTimers();
    }

    static class GamePanel extends JPanel {
        private final RenderEngine renderEngine;

        /**
         * Constructeur du GamePanel.
         *
         * @param renderEngine Le moteur graphique à utiliser pour le rendu.
         */
        public GamePanel(RenderEngine renderEngine) {
            this.renderEngine = renderEngine;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            renderEngine.paint(g);
        }
    }




    private void loadLevel(int levelIndex) {
        if (levelIndex >= levels.size()) {
            showVictoryScreen(); // Si tous les niveaux sont terminés, affiche l'écran de victoire
            return;
        }


        currentLevel = levels.get(levelIndex); // Met à jour le niveau actuel

        hero.setPosition(200, 300);

        // Nettoie et configure le moteur graphique
        renderEngine.clearRenderList();
        renderEngine.addToRenderList(currentLevel.getSpriteList());
        renderEngine.addToRenderList(hero);

        // Configure le moteur physique
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(currentLevel.getSolidSpriteList());

        // Met à jour le moteur de jeu avec le nouveau niveau
        gameEngine.setPlayground(currentLevel);
    }

    /**
     * Passe au niveau suivant et met à jour les moteurs.
     */
    public void nextLevel() {
        stopTimers();
        currentLevelIndex++;
        loadLevel(currentLevelIndex);
        startTimers();
    }

    /**
     * Point d'entrée principal du programme.
     *
     * @param args Arguments de ligne de commande (non utilisés).
     * @throws Exception si une erreur se produit lors de l'exécution.
     */
    public static void main (String[] args) throws Exception {
        new Main();
    }
}
