package com.example.mymazegame;
import android.app.Activity; 
import android.app.AlertDialog; 
import android.content.Context; 
import android.content.DialogInterface; 
import android.graphics.Canvas; 
import android.graphics.Color; 
import android.graphics.Paint; 
import android.hardware.Sensor; 
import android.hardware.SensorEvent; 
import android.hardware.SensorEventListener; 
import android.hardware.SensorManager; 
import android.view.View; 
 
public class GameView extends View implements SensorEventListener { 
 // Width and height of the whole maze and width of lines which make the walls 
 private int width, height, lineWidth; 
 
 // Size of the maze i.e. number of cells in it 
 private int mazeSizeX, mazeSizeY; 
 
 // Width and height of cells in the maze 
 float cellWidth, cellHeight; 
 
 // Store result of cellWidth+lineWidth and cellHeight+lineWidth respectively 
 float totalCellWidth, totalCellHeight; 
 
 // The finishing point of the maze 
 private int mazeFinishX, mazeFinishY; 
 private Maze maze; 
 private Activity context; 
 private Paint line, red, background; 
 boolean dragging = false; 
 
 // Sensor for motion detection 
 public SensorManager mSensorManager; 
 public Sensor mSensor;
 public GameView(Context context, Maze maze) { 
	 super(context); 
	 this.context = (Activity)context; 
	 this.maze = maze; 
	 mazeFinishX = maze.getFinalX(); 
	 mazeFinishY = maze.getFinalY(); 
	 mazeSizeX = maze.getMazeWidth(); 
	 mazeSizeY = maze.getMazeHeight(); 
	 line = new Paint(); 
	 line.setColor(Color.BLACK); 
	 red = new Paint(); 
	 red.setColor(Color.RED); 
	 background = new Paint(); 
	 background.setColor(Color.GRAY); 
	 setFocusable(true); 
	 this.setFocusableInTouchMode(true); 
	 
	 // Get an instance of the sensor service, and use that to get an instance of 
	 // a particular sensor. 
	 mSensorManager = (SensorManager) 
	 context.getSystemService(Context.SENSOR_SERVICE); 
	 mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR); 
	 mSensorManager.registerListener(this, mSensor, 
	 SensorManager.SENSOR_DELAY_NORMAL); 
	 } 
	 
	 protected void onSizeChanged(int w, int h, int oldw, int oldh) { 
	 width = (w < h)?w:h; 
	 height = width; // For now square mazes 
	 lineWidth = 1; // For now 1 pixel wide walls 
	 cellWidth = (width - ((float)mazeSizeX*lineWidth)) / mazeSizeX; 
	 totalCellWidth = cellWidth+lineWidth; 
	 cellHeight = (height - ((float)mazeSizeY*lineWidth)) / mazeSizeY; 
	 totalCellHeight = cellHeight+lineWidth; 
	 red.setTextSize(cellHeight*0.75f); 
	 super.onSizeChanged(w, h, oldw, oldh); 
	 } 
	 protected void onDraw(Canvas canvas) { 
		 // Fill in the background 
		 canvas.drawRect(0, 0, width, height, background); 
		 
		 boolean[][] hLines = maze.getHorizontalLines(); 
		 boolean[][] vLines = maze.getVerticalLines(); 
		 
		 // Iterate over the boolean arrays to draw walls 
		 for(int i = 0; i < mazeSizeX; i++) { 
		 for(int j = 0; j < mazeSizeY; j++){ 
		 float x = j * totalCellWidth; 
		 float y = i * totalCellHeight; 
		 if (j < mazeSizeX - 1 && vLines[i][j]) { 
		 // we'll draw a vertical line 
		 canvas.drawLine(x + cellWidth, // Start X 
		 y, // Start Y 
		 x + cellWidth, // Stop X 
		 y + cellHeight, // Stop Y 
		 line); 
		 } 
		 if (i < mazeSizeY - 1 && hLines[i][j]) { 
		 // We'll draw a horizontal line 
		 canvas.drawLine(x, // StartX 
		 y + cellHeight, // StartY 
		 x + cellWidth, // StopX 
		 y + cellHeight, // StopY 
		 line); 
		 } 
		 } 
		 } 
		 
		 int currentX = maze.getCurrentX(),currentY = maze.getCurrentY(); 
		 
		 // Draw the ball 
		 canvas.drawCircle((currentX * totalCellWidth)+(cellWidth/2), // x 
		 (currentY * totalCellHeight)+(cellWidth/2), // y 
		 (cellWidth*0.45f), // Radius 
		 red);
		// Draw the finishing point indicator 
		 canvas.drawText("F", 
		 (mazeFinishX * totalCellWidth)+(cellWidth*0.25f), 
		 (mazeFinishY * totalCellHeight)+(cellHeight*0.75f), 
		 red); 
		 } 
		 
		 @Override 
		 public void onAccuracyChanged(Sensor sensor, int accuracy) { 
		 } 
		 
		 @Override 
		 public void onSensorChanged(SensorEvent event) { 
		 if (true) { 
		 // Either X or Y changed 
		 boolean moved = false; 
		 
		 // Obtain the rotate direction 
		 if (event.values[0] < 0 && event.values[1] > 0) { 
		 moved = maze.move(Maze.UP); 
		 } else if (event.values[0] > 0 && event.values[1] < 0) { 
		 moved = maze.move(Maze.DOWN); 
		 } else if (event.values[0] < 0 && event.values[1] < 0) { 
		 moved = maze.move(Maze.LEFT); 
		 } else if (event.values[0] > 0 && event.values[1] > 0) { 
		 moved = maze.move(Maze.RIGHT); 
		 } 
		 
		 if (moved) { 
		 // The ball was moved so we'll redraw the view 
		 invalidate(); 
		 if(maze.isGameComplete()) { 
		 // Creating alert Dialog with one Button 
		 AlertDialog alertDialog1 = new 
		 AlertDialog.Builder(context).create(); 
		 
		 // Setting Dialog Title 
		 alertDialog1.setTitle("Level Complete");
		// Setting Dialog Message 
		 alertDialog1.setMessage("Press [OK] to quit"); 
		 
		 // Setting OK Button 
		 alertDialog1.setButton("OK", new 
		 DialogInterface.OnClickListener() { 
		 public void onClick(DialogInterface dialog, 
		 int which) { 
		 // Handle button click 
		 context.finish(); 
		 } 
		 }); 
		 
		 // Showing Alert Message 
		 alertDialog1.show(); 
		 } 
		 } 
		 } 
		 } 
		}

