
package com.nihal.android.popularmovies.data;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import com.nihal.android.popularmovies.R;

public class MoviePreferences {

    /**
     * Returns the sort criteria currently set in Preferences. The default sort criteria this method
     * will return is "popular".
     *
     * @param context Context used to get the SharedPreferences
     * @return Sort Criteria The current user has set in SharedPreferences. Will default to
     * "popular" if SharedPreferences have not been implemented yet i.e if it isn't load it in mobile cache.
     */
    public static String getPreferredSortCriteria(Context context) {
        // Get all of the values from shared preferences to set it up
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        // String for the key
        String keyForSortBy = context.getString(R.string.pref_sort_by_key);
        // String for the default value
        String defaultSortBy = context.getString(R.string.pref_sort_by_default);
        return prefs.getString(keyForSortBy, defaultSortBy);
    }

}
