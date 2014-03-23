package com.example.mymazegame; 
 
import android.os.Bundle; 
import android.app.Activity; 
import android.view.Menu; 
 
public class MainActivity extends Activity { 
 @Override 
 protected void onCreate(Bundle savedInstanceState) { 
 super.onCreate(savedInstanceState); 
// setContentView(R.layout.activity_main); 
 
 // Use helper class for creating the Maze 
 Maze maze = MazeCreator.getMaze(); 
 GameView view = new GameView(this, maze); 
 setContentView(view); 
 } 
 @Override 
 public boolean onCreateOptionsMenu(Menu menu) { 
 // Inflate the menu; this adds items to the action bar if it is present. 
 getMenuInflater().inflate(R.menu.main, menu); 
 return true; 
 } 
} 

