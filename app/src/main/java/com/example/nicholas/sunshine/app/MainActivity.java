package com.example.nicholas.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        if (id == R.id.action_view_map)
        {
            openPreferredLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap()
    {
        // Get the shared preferences for the app.
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        // Get the location (postal code) preference from shared prefs.
        String location = sharedPrefs.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        // Create a Uri with the geolocation query that we will
        // pass to the application to handle the map viewing.
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        // Create the intent with the ACTION_VIEW and then set the
        // data that we will pass to the geolocation data
        Intent viewMapIntent = new Intent(Intent.ACTION_VIEW);
        viewMapIntent.setData(geoLocation);

        // Check to make sure an app exists that can handle the intent
        // request, and then launch the intent if so. Otherwise log error.
        // reference: https://www.udacity.com/course/viewer#!/c-ud853/l-1474559101/e-1480808722/m-1480808723
        if (viewMapIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(viewMapIntent);
        }
        else
        {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no appropriate app");
        }

    }

}
