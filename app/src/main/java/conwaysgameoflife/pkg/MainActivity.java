package conwaysgameoflife.pkg;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	// game choice options
	protected Button newGame;
	protected TextView temp_head;
	protected Button still_temp;
	protected Button osc_temp;
	protected Button glider_temp;
	protected Button exitGame;
	
	protected Button settings;

    /**
     * 			  This class is the main activity that controls the app
     * 			  and displays the initial home page. Here, the user has
     * 			  the option to begin a new blank game, begin a template
     * 			  game, change options, and learning about the app.
     *
     * 			  This project was created as a class final project with the
     * 			  help of quesucede.com.
     *
     * @version   Completed Nov 15, 2013
     * @author    Thomas Hervey <thomasahervey@gmail.com>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize buttons & text
		newGame     = (Button)findViewById(R.id.newGame_button);
		temp_head   = (TextView)findViewById(R.id.template_header);
		still_temp  = (Button)findViewById(R.id.still_template_button);
		osc_temp    = (Button)findViewById(R.id.osc_template_button);
		glider_temp = (Button)findViewById(R.id.glider_template_button);
		exitGame    = (Button)findViewById(R.id.exitGame_button);
		
		// set click listeners
		newGame.setOnClickListener(this);
		still_temp.setOnClickListener(this);
		osc_temp.setOnClickListener(this);
		glider_temp.setOnClickListener(this);
		exitGame.setOnClickListener(this);
	}

	/**
	 * Creates Android menu options
	 *
	 * @param  (Menu)menu -the menu accessible by device hardware
	 * @return true       -created the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
		//return true;
	}
	
	/**
	 * Menu item selector manager
	 *
	 * @param  (MenuItem)item -selected menu item
	 * @return none
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()) {
			case R.id.menuButton:
				System.out.println("Menu");
				break;
			
			// click about menu button
			case R.id.aboutButton:
				Toast.makeText(this.getApplicationContext(), "Conway's game of life is a cellular automaton devised by the British mathematician John Horton Conway in 1970." +
						"The game is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input." +
						"One interacts with the Game of Life by creating an initial configuration and observing how it evolves.", Toast.LENGTH_LONG).show();
				break;
			
			// click settings menu button
			case R.id.settingsButton:
				Intent settingsActivityIntent = new Intent(this, SettingsActivity.class);
				startActivity(settingsActivityIntent);
				break;
			
			default :
				return super.onOptionsItemSelected(item);
		}
		return true;
	}

	/**
	 * Button handler for game choice options calls according activity
	 *
	 * @param  (View)view - the view that's being listened to
	 * @return none
	 */
	@Override
	public void onClick(View view) {
		
        Intent newGameIntent = new Intent(this, NewGameActivity.class);
		
		// get the id of the clicked button
		switch (view.getId()) {
			
			// newGame button clicked
			case R.id.newGame_button:
				newGameIntent.putExtra("buttonChoice", 1.0);
	            startActivity(newGameIntent);
	            break;
	            
	        // Template options 
	        // still_template button clicked
			case R.id.still_template_button:
				newGameIntent.putExtra("buttonChoice", 2.0);
	            startActivity(newGameIntent);
	            break;
	        // osc_template button clicked    
			case R.id.osc_template_button:
				newGameIntent.putExtra("buttonChoice", 3.0);
	            startActivity(newGameIntent);
	            break;
	         // glider_template button clicked    
 			case R.id.glider_template_button:
 				newGameIntent.putExtra("buttonChoice", 4.0);
 	            startActivity(newGameIntent);
 	            break;
	          
	        // exitGame button clicked
			case R.id.exitGame_button:
	            finish();
	            System.exit(0);
	            
	        default:
	        	System.out.println("Button clicking error");
		}
	}
}
