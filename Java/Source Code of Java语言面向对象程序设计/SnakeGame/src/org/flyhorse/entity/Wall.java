package org.flyhorse.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.flyhorse.util.Global;

public class Wall {
	private int[][] rocks=new int[Global.WIDTH][Global.HEIGHT];
	public Wall(){
		for(int y=0;y<Global.HEIGHT;y++){
			for(int x=0;x<Global.WIDTH;x++){
				if(y==00||y==Global.HEIGHT-1||x==0||x==Global.WIDTH-1){
					rocks[y][x]=1;
				}
			}
		}
	}
    public boolean inWall(int r,int c){
       	for(int x=0;x<Global.WIDTH;x++){
    		for(int y=0;y<Global.HEIGHT;y++){
    			if(rocks[r][c]==1){
    			    return true;	
    			}
    		}
    	}
    	return false;
    }
    public boolean isEatBySnake(Snake snake){
    	Point head=snake.getHead();
    	for(int x=0;x<Global.WIDTH;x++){
    		for(int y=0;y<Global.HEIGHT;y++){
    			if(rocks[x][y]==1&&head.x==x&&head.y==y){
    			    return true;	
    			}
    		}
    	}
    	return false;
    }
    public void drawMe(Graphics g){
    	System.out.println("墙正在画出自己");
    	g.setColor(Color.cyan);
		for(int y=0;y<Global.HEIGHT;y++){
			for(int x=0;x<Global.WIDTH;x++){
				if(rocks[y][x]==1){
					g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
    }
}
