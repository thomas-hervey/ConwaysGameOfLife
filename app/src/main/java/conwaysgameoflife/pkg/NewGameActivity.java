package conwaysgameoflife.pkg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

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
public class NewGameActivity extends Activity {
	
	// game view	
	NewGameView newGameView;
	
	/**
	 * Create and set a new game view and set game mode as play
     *
	 * @param  savedInstanceState  -application state
	 * @return none
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		int buttonChoice = (int)intent.getDoubleExtra("buttonChoice", 0);
		System.out.println(buttonChoice);
		
		newGameView = new NewGameView(this, buttonChoice);
		
		setContentView(newGameView);
		newGameView.setOnTouchListener(new MyTouchListener());
		
		newGameView.gameState = 1;
		newGameView.update();
	}
	
	/**
	 * Creates Android menu options
     *
	 * @param  menu  -the menu accessible by device hardware
	 * @return true        -created the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		//return true;
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * Menu item selector manager
     *
	 * @param  item -selected menu item
	 * @return none
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
			case R.id.backButton:
				newGameView.gameState = 0;
				Intent welcomeActivityIntent = new Intent(this, MainActivity.class);
				startActivity(welcomeActivityIntent);
				break;
			
			case R.id.statusButton:
				switchGameState();
				if(newGameView.gameState == 0) { this.setTitle("Paused"); }
				else if(newGameView.gameState == 1) { this.setTitle("Running"); }
			
				break;
		}
		
		return true;
	}
	
	/**
	 * Switch the games current play state
     *
	 * @param
	 * @return none
	 */
	public void switchGameState() {
	    
	    if (newGameView.gameState == 0) {
	    	newGameView.gameState = 1;
	    	newGameView.update();
	    }
	    else if(newGameView.gameState == 1) {
	    	newGameView.gameState = 0;
	    }
	    else { System.out.println("ERROR: In switchGameState. In game mode other than 0 or 1"); }
	}
	
	/**
	 * Return the current game state
     *
	 * @param
	 * @return newGameView.gameState -current game state
	 */
	public int getGameState() {
		return newGameView.gameState;
	}
	
	/**
	 * Touch listener for adding custom cells during game pause
     *
	 * @author thomashervey
	 * @param
	 * @return none
	 */
	private class MyTouchListener implements OnTouchListener {
		
		/**
		 * Listener for touch event
         *
		 * @param newGameView   -view observed
		 * @param event -touch event
		 */
		@Override
		public boolean onTouch(View newGameView, MotionEvent event) {
			int gameState = getGameState();
			
			switch(event.getAction() & MotionEvent.ACTION_MASK) {		        

		        // on click release
		        case MotionEvent.ACTION_UP:
		            // if the game is paused and user clicks once for a while
		            if(gameState == 0) {
		            	float yCoord = event.getY();
		        		float xCoord = event.getX();
		        		System.out.println("Drawing new cell");
		        		NewGameActivity.this.newGameView.drawCellHandler(yCoord, xCoord);
		            }
	        }
			return true; 
		}
    }
}
