package com.company;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class PanelOfGame extends JPanel implements ActionListener {


    private final static int WIDTH = 1000;
    private final static int HEIGHT = 980;


    private final static int size = 25;


    private final static int Area = (WIDTH * HEIGHT) / (size * size);


    private boolean inGame = true;


    private Timer timer;


    private static int speed = 25;


    private SnakeBu snake = new SnakeBu();
    private Apple food = new Apple();

    public PanelOfGame() {

        addKeyListener(new Keys());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        initializeGame();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }


    void draw(Graphics g) {

        if (inGame == true) {
            g.setColor(Color.green);
            g.fillRect(food.getFoodX(), food.getFoodY(), size, size); // food


            for (int i = 0; i < snake.getJoints(); i++) {

                if (i == 0) {
                    g.setColor(Color.RED);
                    g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                            size, size);

                } else {
                    g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                            size, size);
                }
            }


            Toolkit.getDefaultToolkit().sync();
        } else {

            endGame(g);
        }
    }

    void initializeGame() {
        snake.setJoints(3);
        for (int i = 0; i < snake.getJoints(); i++) {
            snake.setSnakeX(WIDTH / 2);
            snake.setSnakeY(HEIGHT / 2);
        }

        snake.setMovingRight(true);


        food.createFood();


        timer = new Timer(speed, this);
        timer.start();
    }


    void checkFoodCollisions() {

        if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
                && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {

            System.out.println("intersection");

            snake.setJoints(snake.getJoints() + 1);

            food.createFood();
        }
    }


    void checkCollisions() {


        for (int i = snake.getJoints(); i > 0; i--) {


            if ((i > 5)
                    && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                    .getSnakeY(0) == snake.getSnakeY(i)))) {
                inGame = false; // then the game ends
            }
        }


        if (snake.getSnakeY(0) >= HEIGHT) {
            inGame = false;
        }

        if (snake.getSnakeY(0) < 0) {
            inGame = false;
        }

        if (snake.getSnakeX(0) >= WIDTH) {
            inGame = false;
        }

        if (snake.getSnakeX(0) < 0) {
            inGame = false;
        }


        if (!inGame) {
            timer.stop();
        }
    }

    void endGame(Graphics g) {


        String message = "Game over";


        Font font = new Font("Times New Roman", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(font);


        g.setColor(Color.red);
        g.setFont(font);


        g.drawString(message, (WIDTH - metrics.stringWidth(message)) / 2,
                HEIGHT / 2);

        System.out.println("Game Ended");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame == true) {

            checkFoodCollisions();
            checkCollisions();
            snake.move();

            System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                    + " " + food.getFoodX() + ", " + food.getFoodY());
        }

        repaint();
    }

    private class Keys extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
                snake.setMovingLeft(true);
                snake.setMovingUp(false);
                snake.setMovingDown(false);
            }

            if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
                snake.setMovingRight(true);
                snake.setMovingUp(false);
                snake.setMovingDown(false);
            }

            if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
                snake.setMovingUp(true);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
            }

            if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
                snake.setMovingDown(true);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
            }

            if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

                inGame = true;
                snake.setMovingDown(false);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
                snake.setMovingUp(false);

                initializeGame();
            }
        }
    }

    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }

    public static int getAllDots() {
        return Area;
    }

    public static int getDotSize() {
        return size;
    }


}