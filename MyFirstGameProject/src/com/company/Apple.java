package com.company;


public class Apple {

    private SnakeBu snake = new SnakeBu();
    private int foodX;
    private int foodY;


    private final int RANDOMPOSITION =40;

    public void createFood() {


        int location = (int) (Math.random() * RANDOMPOSITION);
        foodX = ((location * PanelOfGame.getDotSize()));

        location = (int) (Math.random() * RANDOMPOSITION);
        foodY = ((location * PanelOfGame.getDotSize()));

        if ((foodX == snake.getSnakeX(0)) && (foodY == snake.getSnakeY(0))) {
            createFood();
        }
    }

    public int getFoodX() {

        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }
}