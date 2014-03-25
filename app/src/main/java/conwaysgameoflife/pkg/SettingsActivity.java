package conwaysgameoflife.pkg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 			  This class helps mainactivity.java by generating a settings
 * 			  activity accessible by action bar. The settings allow the
 * 			  user to customize running games including game speed, cell color,
 * 			  cell generation color change and cell size.
 *
 * 			  This project was created as a class final project with the
 * 			  help of quesucede.com.
 *
 * @version   Completed Nov 15, 2013
 * @author    Thomas Hervey <thomasahervey@gmail.com>
 */
public class SettingsActivity extends PreferenceActivity {
	
	// cell color choice
	private static final String CELL_COLOR = "CELL_COLOR_CHOICE";
    private static final String CELL_COLOR_DEFAULT = "BLUE";
    
    // cell size choice
    private static final String CELL_SIZE = "CELL_SIZE_CHOICE";
    private static final String CELL_SIZE_DEFAULT = "5";
    
    // generation speed choice 
    private static final String GENERATION_SPEED = "GENERATION_SPEED_CHOICE";
    private static final String GENERATION_SPEED_DEFAULT = "250";
    
    private static final String CHANGE_COLOR = "COLOR_CHANGING_OPTIONS";
    private static final String CHANGE_COLOR_DEFAULT = "1";
	
    /**
	 * Links to settings.xml and preference options
	 *
	 * @param  savedInstanceState  -application state
	 * @return none
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

	/**
	 * Creates Android menu options
	 *
	 * @param  menu -the menu accessible by device hardware
	 * @return true -created the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
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
				Intent welcomeActivityIntent = new Intent(this, MainActivity.class);
				startActivity(welcomeActivityIntent);
				break;
		}
		return true;
	}
	
	/**
	 * Gets the user defined generation cell color preference
	 *
	 * @param  context -the current context
	 * @return String of context
	 */
	public static String getCellColor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(CELL_COLOR, CELL_COLOR_DEFAULT);
    }
	
	/**
	 * Gets the user defined cell size preference
	 *
	 * @param  context -the current context
	 * @return String of context
	 */
	public static String getCellSize(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(CELL_SIZE, CELL_SIZE_DEFAULT);
    }
	
	/**
	 * Gets the user defined generation speed preference
	 *
	 * @param  context -the current context
	 * @return String of context
	 */
	public static String getGenerationSpeed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(GENERATION_SPEED, GENERATION_SPEED_DEFAULT);
    }

    /**
     * Gets the user defined choice if the color should change
     *
     * @param  context -the current context
     * @return String of context
     */
	public static String isChangeColor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(CHANGE_COLOR, CHANGE_COLOR_DEFAULT);
    }

}
