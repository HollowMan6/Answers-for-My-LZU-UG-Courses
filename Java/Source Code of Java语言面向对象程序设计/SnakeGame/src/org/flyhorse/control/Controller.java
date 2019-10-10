package org.flyhorse.control;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import org.flyhorse.entity.Food;
import org.flyhorse.entity.Snake;
import org.flyhorse.entity.Wall;
import org.flyhorse.util.Global;
import org.flyhorse.view.GamePanel;
import org.flyhorse.listener.*;

public class Controller extends KeyAdapter implements SnakeListener {
	private Snake snake;
	private Food food;
	private Wall wall;
	private GamePanel gamePanel;

	public Controller(Snake snake, Food food, Wall wall, GamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.wall = wall;
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}

	}

	@Override
	public void snakeMoved() {
		System.out.println("判断蛇是否碰到东西");

		if (food.isEatBySnake(snake)) {
			snake.eatFood(food);
			// 食物被吃掉，就应该有新的食物产生
			food.addFood(getPoint());
		}
		if (wall.isEatBySnake(snake)||snake.isEatSelf()) {
			snake.setLife(false);
			JOptionPane.showConfirmDialog(null, "Game Over");
			System.exit(0);
		}
		gamePanel.display(snake, food, wall);

	}

	public void startGame() {
		snake.start();
		food.addFood(getPoint());
	}

	// 设置食物点的位置
	public Point getPoint() {
		int x, y;
		do {
			x = new Random().nextInt(Global.WIDTH);
			y = new Random().nextInt(Global.HEIGHT);
		} while ((wall.inWall(x,y)||snake.inSnake(x,y)));
		return new Point(x, y);
	}

}
