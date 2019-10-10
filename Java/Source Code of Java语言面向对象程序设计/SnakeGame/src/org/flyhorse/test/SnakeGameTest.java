package org.flyhorse.test;

import javax.swing.JFrame;

import org.flyhorse.control.Controller;
import org.flyhorse.entity.Food;
import org.flyhorse.entity.Snake;
import org.flyhorse.entity.Wall;
import org.flyhorse.util.Global;
import org.flyhorse.view.GamePanel;

public class SnakeGameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Snake snake=new Snake();
        Food food=new Food();
        Wall wall=new Wall();
        
        GamePanel gamePanel=new GamePanel(snake,food,wall);
        
        Controller c=new Controller(snake,food,wall,gamePanel);
        snake.addSnakeListener(c);
        gamePanel.addKeyListener(c);
        
        JFrame frame=new JFrame("贪吃蛇 version 1.0");
        frame.setSize(Global.CELL_SIZE*Global.WIDTH+20,Global.CELL_SIZE*Global.HEIGHT+40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        gamePanel.setFocusable(true);
        frame.add(gamePanel);

        c.startGame();
        frame.setVisible(true);
	}

}
