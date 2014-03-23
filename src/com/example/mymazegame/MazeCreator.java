package com.example.mymazegame;

public class MazeCreator { 
	 public static Maze getMaze() { 
	 Maze maze = null; 
	 
	 maze = new Maze(); 
	 boolean[][] vLines = new boolean[][]{ 
	 {true ,false,false,false,true ,false,false}, 
	 {true ,false,false,true ,false,true ,true },
	 {false,true ,false,false,true ,false,false}, 
	 {false,true ,true ,false,false,false,true }, 
	 {true ,false,false,false,true ,true ,false}, 
	 {false,true ,false,false,true ,false,false}, 
	 {false,true ,true ,true ,true ,true ,false}, 
	 {false,false,false,true ,false,false,false} }; 
	 
	 boolean[][] hLines = new boolean[][]{ 
	 {false,false,true ,true ,false,false,true ,false}, 
	 {false,false,true ,true ,false,true ,false,false}, 
	 {true ,true ,false,true ,true ,false,true ,true }, 
	 {false,false,true ,false,true ,true ,false,false}, 
	 {false,true ,true ,true ,true ,false,true ,true }, 
	 {true ,false,false,true ,false,false,true ,false}, 
	 {false,true ,false,false,false,true ,false,true } }; 
	 
	 maze.setVerticalLines(vLines); 
	 maze.setHorizontalLines(hLines); 
	 maze.setStartPosition(0, 0); 
	 maze.setFinalPosition(7, 7); 
	 
	 return maze; 
	 } 
	} 
	 
	