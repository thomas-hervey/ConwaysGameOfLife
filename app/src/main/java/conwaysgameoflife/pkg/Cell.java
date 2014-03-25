package conwaysgameoflife.pkg;

import android.content.Context;
import android.graphics.Color;

/**
 * 			  This class helps mainactivity.java by generating a cell
 * 			  and setting its context. Cell operations including setting
 * 			  cell life, generating a new generation, resetting the board
 * 			  and generating templates.
 *
 * 			  This project was created as a class final project with the
 * 			  help of quesucede.com.
 *
 * @version   Completed Nov 15, 2013
 * @author    Thomas Hervey <thomasahervey@gmail.com>
 */
public class Cell {
	
	// current context
	Context context;
	
	// dimension of all cells on the board
	public int cell_dimensions;
	public int cell_starting_color;
	public int generation_speed;
	public int cell_changing_color;
	
	// static board height and width
	public final int VIEWPORT_HEIGHT;
	public final int VIEWPORT_WIDTH;
	
	// arrays that'll hold the life of the current and next generations
	public int[][] currentGeneration;
	public int[][] nextGeneration;
	
	// cell life rules
	public final int REBORN			 = 2;
	public final int OVERPOPULATION  = 4;
	public final int UNDERPOPULATION = 1;

    /**
     *  Default constructor for a cell board
     *
     *  @param  -number of desired template
     *  @return none
     */
	public Cell() {
		
		// defaul values
		this.cell_dimensions = 5;
		this.cell_starting_color = (int)Color.BLUE;
		this.generation_speed = 100;
		this.cell_changing_color = 1;
		
		System.out.println("Newly Created Cell Dimensions: " + this.cell_dimensions);
		System.out.println("Newly Created Cell Color: " + this.cell_starting_color);
		System.out.println("Newly Created Cell Speed: " + this.generation_speed);
		
		// set viewport size
		VIEWPORT_HEIGHT = 800 / this.cell_dimensions;
		VIEWPORT_WIDTH  = 800 / this.cell_dimensions;
		
		// initialize 2D array
		currentGeneration = new int[VIEWPORT_HEIGHT][VIEWPORT_WIDTH];
		nextGeneration    = new int[VIEWPORT_HEIGHT][VIEWPORT_WIDTH];
		
		resetBoard();
	}
	
	/**
	 *  Constructor for a cell board
     *
	 *  @param
	 *  @return none
	 */
	public Cell(Context context) {
		this.context = context;
		
		this.cell_dimensions     = Integer.parseInt(SettingsActivity.getCellSize(this.context));
		this.cell_starting_color = Integer.parseInt(SettingsActivity.getCellColor(this.context));
		this.generation_speed = Integer.parseInt(SettingsActivity.getGenerationSpeed(this.context));
		this.cell_changing_color = Integer.parseInt(SettingsActivity.isChangeColor(this.context));
		
		System.out.println("Newly Created New Cell Dimensions: " + this.cell_dimensions);
		System.out.println("Newly Created New Cell Color: " + this.cell_starting_color);
		System.out.println("Newly Created New Cell Speed: " + this.generation_speed);
		
		VIEWPORT_HEIGHT = 800 / this.cell_dimensions;
		VIEWPORT_WIDTH  = 800 / this.cell_dimensions;
		
		currentGeneration = new int[VIEWPORT_HEIGHT][VIEWPORT_WIDTH];
		nextGeneration    = new int[VIEWPORT_HEIGHT][VIEWPORT_WIDTH];
		
		resetBoard();
	}
	
	/**
	 *  Constructor for a cell board
     *
	 *  @param  template  -number of desired template
	 *  @return none
	 */
	public Cell(Context context, int template) {
		
		this.context = context;
		this.cell_dimensions     = Integer.parseInt(SettingsActivity.getCellSize(this.context));
		this.cell_starting_color = Integer.parseInt(SettingsActivity.getCellColor(this.context));
		this.generation_speed = Integer.parseInt(SettingsActivity.getGenerationSpeed(this.context));
		this.cell_changing_color = Integer.parseInt(SettingsActivity.isChangeColor(this.context));
		
		System.out.println("Newly Created Template Cell Dimensions: " + this.cell_dimensions);
		System.out.println("Newly Created Template Cell Color: " + this.cell_starting_color);
		System.out.println("Newly Created Template Cell Speed: " + this.generation_speed);
		
		VIEWPORT_HEIGHT = 800 / this.cell_dimensions;
		VIEWPORT_WIDTH  = 800 / this.cell_dimensions;
		
		currentGeneration = new int[VIEWPORT_HEIGHT][VIEWPORT_WIDTH];
		nextGeneration    = new int[VIEWPORT_HEIGHT][VIEWPORT_WIDTH];
		
		resetBoard();
		boardTemplate(template);
	}
	
	/**
	 *  Sets up the board with predefined templates
     *
	 *  @param  template  -number key of desired template
	 *  @return none
	 */
	private void boardTemplate(int template) {
		System.out.println("The template being setup is:" + template);
		switch(template) {	
			// Still lives
			case 2:
				System.out.println("Stills");
				// block
				currentGeneration[10][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[10][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[11][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[11][(VIEWPORT_WIDTH/4) + 1] = 1;
				
				// behive
				currentGeneration[30][(VIEWPORT_WIDTH/3) ]    = 1;
				currentGeneration[30][(VIEWPORT_WIDTH/3) + 1] = 1;
				currentGeneration[31][(VIEWPORT_WIDTH/3) - 1] = 1;
				currentGeneration[31][(VIEWPORT_WIDTH/3) + 2] = 1;
				currentGeneration[32][(VIEWPORT_WIDTH/3) ]    = 1;
				currentGeneration[32][(VIEWPORT_WIDTH/3) + 1] = 1;
				
				// loaf
				currentGeneration[40][(VIEWPORT_WIDTH/4) ]     = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/4) + 1]  = 1;
				currentGeneration[41][(VIEWPORT_WIDTH/4) - 1 ] = 1;
				currentGeneration[41][(VIEWPORT_WIDTH/4) + 2 ] = 1;
				currentGeneration[42][(VIEWPORT_WIDTH/4) ]     = 1;
				currentGeneration[42][(VIEWPORT_WIDTH/4) + 2 ] = 1;
				currentGeneration[43][(VIEWPORT_WIDTH/4) + 1 ] = 1;
				
				// boat
				currentGeneration[60][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[60][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[61][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[61][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[62][(VIEWPORT_WIDTH/4) + 1] = 1;
				
				break;
				
			// oscillators
			case 3:
				
				System.out.println("oscillators");
				// blinkers
				currentGeneration[60][(VIEWPORT_WIDTH/4) ] = 1;
				currentGeneration[61][(VIEWPORT_WIDTH/4) ] = 1;
				currentGeneration[62][(VIEWPORT_WIDTH/4) ] = 1;
				
				currentGeneration[80][(VIEWPORT_WIDTH/2) ] = 1;
				currentGeneration[81][(VIEWPORT_WIDTH/2) ] = 1;
				currentGeneration[82][(VIEWPORT_WIDTH/2) ] = 1;
				
				// toad
				currentGeneration[30][(VIEWPORT_WIDTH/4) ] = 1;
				currentGeneration[30][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[30][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[31][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[31][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[31][(VIEWPORT_WIDTH/4) + 3] = 1;
				
				currentGeneration[40][(VIEWPORT_WIDTH/2) ] = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/2) + 1] = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/2) + 2] = 1;
				currentGeneration[41][(VIEWPORT_WIDTH/2) + 1] = 1;
				currentGeneration[41][(VIEWPORT_WIDTH/2) + 2] = 1;
				currentGeneration[41][(VIEWPORT_WIDTH/2) + 3] = 1;
				
				// beacon
				currentGeneration[10][(VIEWPORT_WIDTH/3) ]    = 1;
				currentGeneration[10][(VIEWPORT_WIDTH/3) + 1] = 1;
				currentGeneration[11][(VIEWPORT_WIDTH/3) ]    = 1;
				currentGeneration[11][(VIEWPORT_WIDTH/3) + 1] = 1;
				currentGeneration[12][(VIEWPORT_WIDTH/3) + 3] = 1;
				currentGeneration[12][(VIEWPORT_WIDTH/3) + 2] = 1;
				currentGeneration[13][(VIEWPORT_WIDTH/3) + 3] = 1;
				currentGeneration[13][(VIEWPORT_WIDTH/3) + 2] = 1;
				
				break;
			
			// gliders
			case 4:
				System.out.println("gliders");
				currentGeneration[10][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[10][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[10][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[9][(VIEWPORT_WIDTH/4) + 2]  = 1;
				currentGeneration[8][(VIEWPORT_WIDTH/4) + 1]  = 1;
				
				currentGeneration[40][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[39][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[38][(VIEWPORT_WIDTH/4) + 1] = 1;
				
				currentGeneration[40][(VIEWPORT_WIDTH/5) ]    = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/5) + 1] = 1;
				currentGeneration[40][(VIEWPORT_WIDTH/5) + 2] = 1;
				currentGeneration[39][(VIEWPORT_WIDTH/5) + 2] = 1;
				currentGeneration[38][(VIEWPORT_WIDTH/5) + 1] = 1;
				
				currentGeneration[20][(VIEWPORT_WIDTH/3) ]    = 1;
				currentGeneration[20][(VIEWPORT_WIDTH/3) + 1] = 1;
				currentGeneration[20][(VIEWPORT_WIDTH/3) + 2] = 1;
				currentGeneration[19][(VIEWPORT_WIDTH/3) + 2] = 1;
				currentGeneration[18][(VIEWPORT_WIDTH/3) + 1] = 1;
				
				currentGeneration[35][(VIEWPORT_WIDTH/4) ]    = 1;
				currentGeneration[35][(VIEWPORT_WIDTH/4) + 1] = 1;
				currentGeneration[35][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[34][(VIEWPORT_WIDTH/4) + 2] = 1;
				currentGeneration[33][(VIEWPORT_WIDTH/4) + 1] = 1;
				
				break;
			
			// random setup
			case 5:
				break;
				
			default:
				break;
		}
	}
	
	/**
	 *  Gets the contents of the current generation array
     *
	 *  @param
	 *  @return (int[][])currentGeneration  -the global current generation
	 * 
	 */
	public int[][] getCurrentGen() {
		return currentGeneration;
	}
	
	/**
	 *  Creates a new generation based on alive neighbors compared to cell 
	 *  life rules. This generation will be alive in the next generation.
     *
	 *  @param
	 *  @return none
	 */
	public void setNextGeneration() {
//		System.out.println("in setNextGeneration");
		int numAliveNeighbors = 0;
		
		// for each vertical cell on the board
		for (int y = 0; y < VIEWPORT_HEIGHT; y++) {
			// for each horizontal cell on the board
            for (int x = 0; x < VIEWPORT_WIDTH; x++) {
            	
            	// get that cell's alive neighbor count
            	numAliveNeighbors = numberOfAliveNeighbors(y, x);
            	
            	// set cell's life state based on neighbors
            	setCellLife(y, x, numAliveNeighbors);
            }
        }
		resetBoard();
		newGeneration();
	}
	
	/**
	 *   setNextGeneration helper method that looks at a cell's six surrounding
	 *   neighbors and increases a living counter for each live one
     *
	 *   @param  x				   -cell of interest's x-coordinate
	 *   @param  y				   -cell of interest's y-coordinate
	 *   @return numAliveNeighbors -# of the cell's living neighbors
	 */
	private int numberOfAliveNeighbors(int y, int x) {
		// get fresh number of alive neighbors
		int numAliveNeighbors = 0;
        // for each y neighbor
        for (int yNeighbor = -1; yNeighbor <= +1; yNeighbor++) {
        	// for each x neighbor
            for (int xNeighbor = -1; xNeighbor <= +1; xNeighbor++) {
                // if the cell at that location is alive
            	if (currentGeneration
            			[(VIEWPORT_HEIGHT + (y + yNeighbor)) % VIEWPORT_HEIGHT]
            			[(VIEWPORT_WIDTH  + (x + xNeighbor)) % VIEWPORT_WIDTH] != 0) {
            		// add one alive neighbor
                	numAliveNeighbors++;
                }
            }
        }
        // subtract the current cell's life from neighbor
        numAliveNeighbors -=1; 
        return numAliveNeighbors;
    }
	
	/**
	 *  Copies and resets the current generation by setting the living state to
	 *  based on the number of living neighbors
     *
	 *  @return none
	 */
	private void newGeneration() {
		// for each cell on the board
		for (int y = 0; y < VIEWPORT_HEIGHT; y++) {
            for (int x = 0; x < VIEWPORT_WIDTH; x++) {
            	// set the current generation to the next generation
            	currentGeneration[y][x] = nextGeneration[y][x];
            }
        }
	}
	
	/**
	 *  Resets the board of all living current generation cells
     *
	 *  @param
	 *  @return none
	 */
	private void resetBoard() {
		// for each cell on the board
		for (int y = 0; y < VIEWPORT_HEIGHT; y++) {
            for (int x = 0; x < VIEWPORT_WIDTH; x++) {
            	currentGeneration[y][x] = 0;
            }
        }
	}
	
	/**
	 * Based off of number of alive neighbors, decides if
	 * the observed cell will be alive or dead in the next generation
     *
	 * @param  y                 -y coordinate of current board iteration
	 * @param  x                 -x coordinate of current board iteration
	 * @param  numAliveNeighbors -number of that cell's alive neighbors
	 * @return none
	 */
	private void setCellLife(int y, int x, int numAliveNeighbors) {

        // if that cell is currently alive
        if (currentGeneration[y][x] == 1) {
            // if that cell isn't overcrowded and isn't lonely
            if ((numAliveNeighbors > UNDERPOPULATION) && (numAliveNeighbors < OVERPOPULATION)) {
                // keep that cell alive
                nextGeneration[y][x] = 1;
            }
            // otherwise, kill the cell
            else {
                nextGeneration[y][x] = 0;
            }
        }
        // if that cell is currently dead
        else if (currentGeneration[y][x] == 0) {
            // if that cell can be reborn
            if (numAliveNeighbors == REBORN) {
                // bring that cell to life
                nextGeneration[y][x] = 1;
//            	System.out.println(x + ":" + y);
            }
            // otherwise, keep that cell dead
            else {
                nextGeneration[y][x] = 0;
            }
        }
        // unexpected cell state error
        else {
            System.out.println("Not right life state: " + currentGeneration[y][x]);
        }
    }
}
