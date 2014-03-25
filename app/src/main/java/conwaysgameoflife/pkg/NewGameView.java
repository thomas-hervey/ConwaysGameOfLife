package conwaysgameoflife.pkg;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * 			  This class helps mainactivity.java by generating a new game
 * 			  and allowing for altering the game state (play/pause) and
 * 			  initializing a click listener for adding alive cells.
 *
 * 			  This project was created as a class final project with the
 * 			  help of quesucede.com.
 *
 * @version   Completed Nov 15, 2013
 * @author    Thomas Hervey <thomasahervey@gmail.com>
 */
public class NewGameView extends View {
	
	// random color generator
	Random rnd;
	
	// game play || pause state
	int gameState; 
	
	// current context
	Context context;
	
	// cell object
	private Cell cellLife;
	
	// settings object
	public SettingsActivity mySettings;
	
	// generation refresh handler
	private RefreshHandler myRedrawHandler;
	
	// drawing components
	private int generationCount = 0;
	private int colorCount = 0;
	private Paint viewBackground = new Paint();
	private Paint paintCell = new Paint();
	private Paint generationCounter = new Paint();
	
	/**
	 * Constructor for a new game board view
     *
	 * @param  context - the current context
	 * @return none
	 */
	public NewGameView(Context context) {
		super(context);
		this.context = context;
		mySettings = new SettingsActivity();
		myRedrawHandler = new RefreshHandler();
	}
	
	/**
	 * Constructor for a new template game board view
     *
	 * @param  context  -context focus
	 * @param  buttonChoice -user game button option
	 * @return none
	 */
	public NewGameView(Context context, int buttonChoice) {
		super(context);
		this.context = context;
		mySettings = new SettingsActivity();
		myRedrawHandler = new RefreshHandler();
		createLife(buttonChoice);
	}
	
	/**
	 * Constructor for menu attributes
     *
	 * @param  context    -context focus
	 * @param  attrs -custom game view attributes
	 * @return none
	 */
	public NewGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mySettings = new SettingsActivity();
		myRedrawHandler = new RefreshHandler();
	}
	
	/**
	 * Set up initial blank or template board
     *
	 * @param  buttonChoice -user game button option
     * @return none
	 */
	private void createLife(int buttonChoice) {
		
    	// if the user want's a fresh new game
    	if(buttonChoice == 1) {
    		cellLife = new Cell(context);
    	}
    	// if the user want's a template
    	else if(buttonChoice >= 2 && buttonChoice <= 5) {
    		System.out.println("creating life from template");
    		cellLife = new Cell(context, buttonChoice);
    	}
    	// otherwise, there's an issue if the default is passed
    	else if(buttonChoice == 0) { System.out.println("Default button value from intent --> 0" ); }
    	// otherwise, there's an issue
    	else { System.out.println("We have an issue: " + buttonChoice); }
    }
	
	/**
	 * View drawing handler creates background & board
	 *
	 * @param  canvas -current drawing canvas
	 * @return none
	 */
	@Override 
	public void onDraw(Canvas canvas)
	{
		System.out.println("in onDraw()");
		
		// draw view background
		viewBackground.setColor(Color.DKGRAY);
		// if the user want's the color to change with generation
		if(cellLife.cell_changing_color == 2) {
			paintCell.setColor(cellLife.cell_starting_color + colorCount);
		}
		else if(cellLife.cell_changing_color == 3) {
			rnd = new Random();
			paintCell.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			
		}
		else { paintCell.setColor(cellLife.cell_starting_color); }
		canvas.drawRect(0, 0, getWidth(), getHeight(), viewBackground);
		
		canvas.drawText("Generation #: " + generationCount, 15, 15, generationCounter);
		colorCount += 100;
		System.out.println(colorCount);
		generationCount++;
		
		drawBoard(canvas);
	}
	
	/**
	 * onDraw helper that draws all alive currentGeneration cells
	 *
	 * @param  canvas
	 * @return none
	 */
	private void drawBoard(Canvas canvas) {
		
		// for each cell in the view
		for (int y = 0; y < cellLife.VIEWPORT_HEIGHT; y++) {
            for (int x = 0; x < cellLife.VIEWPORT_WIDTH; x++) {
            	// if the cell is alive, draw it!
                if (cellLife.getCurrentGen()[y][x] != 0) {
                    canvas.drawRect(
                         x * cellLife.cell_dimensions, 
                         y * cellLife.cell_dimensions, 
                        (x * cellLife.cell_dimensions) + (cellLife.cell_dimensions -1),
                        (y * cellLife.cell_dimensions) + (cellLife.cell_dimensions -1),
                        paintCell);
                }
            }
        }
	}
	
	/**
	 * Handler for drawing a custom cell on user click
	 * <p>
	 * @param  yCoord - click y coordinate
	 * @param  xCoord - click x coordinate
	 * @return none
	 */
	public void drawCellHandler(float yCoord, float xCoord) {
		int y = (int)yCoord / cellLife.cell_dimensions;
		int x = (int)xCoord / cellLife.cell_dimensions;
		cellLife.currentGeneration[y][x] = 1;
		this.invalidate();
	}
	
	/**
	 * Loop function with myRedrawHandler class to update board 
	 * handling initial board update and sleep functions
	 *
	 * @param
	 * @return none
	 */
    public void update() {
    	// if the game is playing
    	if(gameState == 1) {
    		// determine & set next generation
    		cellLife.setNextGeneration();
    		// sleep then redraw new generation
        	myRedrawHandler.sleep(cellLife.generation_speed);	
    	}
    	// if the game is paused
    	else if(gameState == 0) {
    		
    	}
    	//otherwise, we have an issue with the game state
    	else { System.out.println("There is an issue with the game state: " + gameState); }
    }
    
    /**
     * Refresh handler for generation update, sleep, and redraw iterations
     *
     * @author thomashervey
     * @param
     * @return none
     */
    private class RefreshHandler extends Handler {

    	/**
    	 * Message handler
    	 *
    	 * @param  msg -handler message
    	 * @return none
    	 */
        @Override
        public void handleMessage(Message msg) {
        	NewGameView.this.update();
        	NewGameView.this.invalidate();
        }

        /**
         * Sets the sleep delay between generation draws
         *
         * @param  delayMillis -time delay
         * @return none
         */
        public void sleep(long delayMillis) {
        	this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
