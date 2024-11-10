import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = 20;
    private static final int PIPE_WIDTH = 80;
    private static final int PIPE_GAP = 200;
    private static final int PIPE_SPEED = 5;

    private int birdY = HEIGHT / 2;
    private int birdVelocity = 0;
    private int score = 0;
    private boolean gameOver = false;
    private ArrayList<Rectangle> pipes;
    private Timer timer;

    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
                    birdVelocity = -JUMP_STRENGTH;
                }
                if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    restart();
                }
            }
        });

        pipes = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
        spawnPipe();
    }

    private void spawnPipe() {
        int pipeHeight = new Random().nextInt(HEIGHT - PIPE_GAP - 100) + 50;
        pipes.add(new Rectangle(WIDTH, 0, PIPE_WIDTH, pipeHeight));
        pipes.add(new Rectangle(WIDTH, pipeHeight + PIPE_GAP, PIPE_WIDTH, HEIGHT - pipeHeight - PIPE_GAP));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            birdVelocity += GRAVITY;
            birdY += birdVelocity;

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                pipe.x -= PIPE_SPEED;

                if (pipe.x + PIPE_WIDTH < 0) {
                    pipes.remove(i);
                    i--;
                    if (pipe.y == 0) {
                        score++;
                    }
                }
            }

            if (birdY > HEIGHT || birdY < 0 || checkCollision()) {
                gameOver = true;
                timer.stop();
            }

            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).x < WIDTH - 300) {
                spawnPipe();
            }

            repaint();
        }
    }

    private boolean checkCollision() {
        Rectangle birdRect = new Rectangle(50, birdY, 20, 20);
        for (Rectangle pipe : pipes) {
            if (birdRect.intersects(pipe)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillRect(50, birdY, 20, 20);

        g.setColor(Color.GREEN);
        for (Rectangle pipe : pipes) {
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", WIDTH / 2 - 150, HEIGHT / 2);
            g.drawString("Press R to Restart", WIDTH / 2 - 200, HEIGHT / 2 + 50);
        }
    }

    public void restart() {
        birdY = HEIGHT / 2;
        birdVelocity = 0;
        score = 0;
        gameOver = false;
        pipes.clear();
        spawnPipe();
        timer.start();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}