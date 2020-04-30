
package com.nihal.android.popularmovies.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import com.nihal.android.popularmovies.R;

import static com.nihal.android.popularmovies.utilities.Constant.DEFAULT_VALUE;

/**
 * The SettingsFragment serves as the display for all of the user's settings.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // Add movie preferences, defined in the XML file in res->xml->pref_movie
        addPreferencesFromResource(R.xml.pref_movie);

        // Get shared preferences
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        // Get the preference screen
        PreferenceScreen prefScreen = getPreferenceScreen();
        // Get the number of preferences
        int count = prefScreen.getPreferenceCount();

        // Go through all of the preferences, and set up their preference summary.
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);

            String value = sharedPreferences.getString(p.getKey(), DEFAULT_VALUE);
            setPreferenceSummary(p, value);
        }
    }

    /**
     * Called when a shared preference is changed
     *
     * @param sharedPreferences The SharedPreferences that received the change
     * @param key The key of the preference that was changed
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Figure out which preference was changed
        Preference preference = findPreference(key);
        if (null != preference) {
            // Updates the summary for the preference
            String value = sharedPreferences.getString(preference.getKey(), DEFAULT_VALUE);
            setPreferenceSummary(preference, value);
        }
    }

    /**
     * Updates the summary for the preference
     *
     * @param preference The preference to be updated
     * @param value       The value that the preference was updated to
     */
    private void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the OnSharedPreferenceChange listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the OnSharedPreferenceChange listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
