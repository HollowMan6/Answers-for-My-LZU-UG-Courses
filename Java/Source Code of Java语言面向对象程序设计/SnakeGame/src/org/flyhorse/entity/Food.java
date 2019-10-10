package org.flyhorse.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.flyhorse.util.Global;

public class Food extends Point{
	/*
	 * 要得到蛇头
	 * 判断蛇头坐标和食物坐标是否重合
	 */
    public boolean isEatBySnake(Snake snake){
    	Point head=snake.getHead();
    	if(this.equals(head)){
    		return true;
    	}
    	return false;
    }
    public void drawMe(Graphics g){
    	System.out.println("食物正在画出自己");
    	g.setColor(Color.red);
    	g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE,Global.CELL_SIZE, true);
    }
    public void addFood(Point p){
    	this.x=p.x;
    	this.y=p.y;
    	
    }
}
