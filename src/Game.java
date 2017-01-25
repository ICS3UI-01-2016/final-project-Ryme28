
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author messr2578
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int[] xpoints = {320, 460, 540, 250};
    int[] ypoints = {100, 100, 400, 400};
    int[] guttLx = {290, 320, 250, 220};
    int[] guttLy = {100, 100, 400, 400};
    int[] guttRx = {460, 490, 570, 540};
    int[] guttRy = {100, 100, 400, 400};
    int ballSize = 50;
    int ballX = 300;
    int ballY = 350;
    boolean ballMovement = false;
    boolean gutter = false;
    Color lightBlue = new Color(141, 221, 247);
    Color blue = new Color(46, 88, 255);
    Color brown = new Color(145, 66, 17);
    Color lightBrown = new Color(237, 102, 5);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.setColor(brown);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // draw the gutter
        g.setColor(Color.BLACK);
        g.fillRect(320, 50, 140, 50);
        g.fillPolygon(guttLx, guttLy, 4);
        g.fillPolygon(guttRx, guttRy, 4);
        //colour the lane
        g.setColor(lightBrown);
        g.fillPolygon(xpoints, ypoints, 4);
        //draw the pins
        g.setColor(blue);
        g.fillRect(320, 60, 20, 40);
        g.fillRect(350, 60, 20, 40);
        g.fillRect(380, 60, 20, 40);
        g.fillRect(410, 60, 20, 40);
        g.fillRect(440, 60, 20, 40);
        g.setColor(brown);
        g.fillRect(335, 70, 20, 40);
        g.fillRect(365, 70, 20, 40);
        g.fillRect(395, 70, 20, 40);
        g.fillRect(425, 70, 20, 40);
        g.setColor(Color.GREEN);
        g.fillRect(350, 80, 20, 40);
        g.fillRect(380, 80, 20, 40);
        g.fillRect(410, 80, 20, 40);
        g.setColor(Color.YELLOW);
        g.fillRect(365, 90, 20, 40);
        g.fillRect(395, 90, 20, 40);
        g.setColor(Color.MAGENTA);
        g.fillRect(380, 100, 20, 40);
        // drawing the ball
        g.setColor(Color.CYAN);
        g.fillRect(ballX, ballY, ballSize, ballSize);
        //drawing the scoreboard
        g.setColor(Color.BLACK);
        g.fillRect(19, 4, 71, 72);
        g.fillRect(160, 4, 71, 72);
        g.fillRect(300, 4, 71, 72);
        g.fillRect(440, 4, 71, 72);
        g.fillRect(580, 4, 71, 72);
        g.fillRect(90, 4, 71, 72);
        g.fillRect(230, 4, 71, 72);
        g.fillRect(370, 4, 71, 72);
        g.fillRect(510, 4, 71, 72);
        g.fillRect(650, 4, 71, 72);

        g.setColor(lightBlue);
        g.fillRect(20, 5, 70, 70);
        g.fillRect(160, 5, 70, 70);
        g.fillRect(300, 5, 70, 70);
        g.fillRect(440, 5, 70, 70);
        g.fillRect(580, 5, 70, 70);
        g.setColor(blue);
        g.fillRect(90, 5, 70, 70);
        g.fillRect(230, 5, 70, 70);
        g.fillRect(370, 5, 70, 70);
        g.fillRect(510, 5, 70, 70);
        g.fillRect(650, 5, 70, 70);


        //create the boxes

        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if (ballMovement && ballY > 75&&!gutter) {
                if(!gutter){
                ballY = ballY - 1;
                if (ballX <= 240) {
                    gutter = true;
                    ballMovement=false;
                }
                if (ballX >= 460) {
                    gutter = true;
                    ballMovement=false;
                }
                if (gutter) {
                    ballY = 350;
                    ballSize = 50;
                    gutter = true;
                }
                }
                
                if (ballY < 200) {
                    ballSize = 25;
                }
                

            }

            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        Game game = new Game();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // add the key listener 
        frame.addKeyListener(game);
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // starts my game loop
        game.run();

    }

    /**
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            ballMovement = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            if (ballX > 280) {
                ballX = ballX - 1;
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (ballX < 460) {
                ballX = ballX + 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
        }
    }
}